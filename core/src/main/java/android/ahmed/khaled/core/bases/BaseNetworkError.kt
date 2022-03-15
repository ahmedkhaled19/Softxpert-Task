package android.ahmed.khaled.core.bases

import android.ahmed.khaled.core.R
import android.ahmed.khaled.core.common.MessageWrapper
import android.util.Log
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class BaseNetworkError @Inject constructor() {

    private val tagName = BaseNetworkError::class.simpleName

    private val messageWrapper: MessageWrapper by lazy {
        MessageWrapper()
    }

    fun getCloudErrorMessage(throwable: Throwable): MessageWrapper {
        when (throwable) {
            // if throwable is an instance of HttpException
            // then attempt to parse error data from response body
            is HttpException -> {
                // handle UNAUTHORIZED situation (when token expired)
                when {
                    throwable.code() == 401 ->
                        messageWrapper.messageId = R.string.auth_error
                    else ->
                        getHttpError(throwable.response()?.errorBody())
                }
            }

            // handle api call timeout error
            is SocketTimeoutException -> {
                messageWrapper.messageId = R.string.time_out
            }

            // handle connection error
            is IOException -> {
                messageWrapper.messageId = R.string.check_connection
            }

            is UnknownHostException -> {
                messageWrapper.messageId = R.string.check_connection
            }
            else -> {
                Log.e(tagName, throwable.localizedMessage!!)
                messageWrapper.messageId = R.string.something_went_wrong
            }
        }
        return messageWrapper
    }

    private fun getHttpError(errorBody: ResponseBody?) {
        try {
            val jObjError = JSONObject(errorBody!!.string())
            var error = jObjError.getString("errors").replace("[", "")
            error = error.replace("]", "")
            error = error.substring(1, error.length - 1)
            messageWrapper.message = error
        } catch (e: IOException) {
            throw RuntimeException(e)
        } catch (e: JSONException) {
            messageWrapper.messageId = R.string.something_went_wrong
        }
    }

}