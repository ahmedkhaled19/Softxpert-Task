package android.ahmed.khaled.core.common

import android.content.Context

class MessageWrapper {

    var messageId: Int? = null
    var message: String = ""

    fun getMessage(context: Context): String {
        val messageString: String
        if (messageId != null) {
            messageString = context.getString(messageId!!)
            messageId = null
        } else {
            messageString = message
            message = ""
        }
        return messageString
    }
}