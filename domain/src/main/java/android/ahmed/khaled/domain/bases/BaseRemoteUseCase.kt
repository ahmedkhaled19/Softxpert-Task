package android.ahmed.khaled.domain.bases

import android.ahmed.khaled.core.bases.BaseNetworkError
import android.ahmed.khaled.core.common.MessageWrapper
import kotlinx.coroutines.*

/**
 * Created by Ahmed Khaled on 12/08/2021.
 */

typealias CompletionBlock<T> = BaseRemoteUseCase.RequestState<T>.() -> Unit

/**
 * @type in P for request parameters
 * @type R response from the result of the request
 * @type FR(final result) mapped response to local values
 */

abstract class BaseRemoteUseCase<P, R, FR>(private val baseErrorHandler: BaseNetworkError) {

    private lateinit var parentJob: Job

    protected abstract suspend fun executeRequest(parameters: P): R
    protected abstract suspend fun convert(remoteResponse: R): FR

    fun sendRequest(parameters: P, onComplete: CompletionBlock<FR>) {
        val response = RequestState<FR>().apply { onComplete() }
        parentJob = Job()
        CoroutineScope(Dispatchers.Main + parentJob).launch {
            response(true)
            try {
                val result = withContext(Dispatchers.IO) {
                    executeRequest(parameters)
                }
                response(convert(result))
                response(false)
            } catch (cancellationException: CancellationException) {
                response(cancellationException)
                response(false)
            } catch (e: Exception) {
                val error = baseErrorHandler.getCloudErrorMessage(e)
                response(error)
                response(false)
            }
        }
    }

    fun cancelCurrentJop() {
        parentJob.apply {
            cancelChildren()
            cancel()
        }
    }

    class RequestState<T> {
        private var isLoading: ((Boolean) -> Unit)? = null
        private var onComplete: ((T) -> Unit)? = null
        private var onError: ((MessageWrapper) -> Unit)? = null
        private var onCancel: ((CancellationException) -> Unit)? = null

        fun isLoading(isLoading: (Boolean) -> Unit) {
            this.isLoading = isLoading
        }

        fun onComplete(resultData: (T) -> Unit) {
            onComplete = resultData
        }

        fun onError(errorMessage: (MessageWrapper) -> Unit) {
            onError = errorMessage
        }

        fun onCancel(cancelException: (CancellationException) -> Unit) {
            onCancel = cancelException
        }

        operator fun invoke(loading: Boolean) {
            isLoading?.invoke(loading)
        }

        operator fun invoke(resultData: T) {
            onComplete?.invoke(resultData)
        }

        operator fun invoke(errorMessage: MessageWrapper) {
            onError?.invoke(errorMessage)
        }

        operator fun invoke(cancellationError: CancellationException) {
            onCancel?.invoke(cancellationError)
        }
    }
}