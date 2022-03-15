package android.ahmed.khaled.domain.bases

import kotlinx.coroutines.*

/**
 * Created by Ahmed Khaled on 15/08/2021.
 */

typealias CompleteBlock<F> = BaseLocalUseCase.OperationState<F>.() -> Unit

/**
 * @type T represents object type for inserting
 * @type R represents return value for selecting
 */

abstract class BaseLocalUseCase<T, R> {

    private var parentJob: Job = Job()

    protected abstract suspend fun executeOperation(parameters: T): R

    suspend fun execute(parameters: T, block: CompleteBlock<R>) {
        CoroutineScope(Dispatchers.IO + parentJob).async {
            val response = OperationState<R>().apply { block() }
            try {
                val result = withContext(Dispatchers.IO) {
                    executeOperation(parameters)
                }
                response(result)
            } catch (cancellationException: CancellationException) {
                response(cancellationException)
            }
        }.await()
    }

    class OperationState<T> {
        private var onComplete: ((T) -> Unit)? = null
        private var onCancel: ((CancellationException) -> Unit)? = null

        fun onComplete(resultData: (T) -> Unit) {
            onComplete = resultData
        }

        fun onCancel(cancelException: (CancellationException) -> Unit) {
            onCancel = cancelException
        }

        operator fun invoke(result: T) {
            onComplete?.invoke(result)
        }

        operator fun invoke(error: CancellationException) {
            onCancel?.invoke(error)
        }
    }
}