package android.ahmed.khaled.core.bases

import android.ahmed.khaled.core.common.MessageWrapper
import android.ahmed.khaled.core.common.SingleLiveEvent
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel() {

    private val messageWrapper: MessageWrapper by lazy {
        MessageWrapper()
    }
    val showLoadingProgressBar: SingleLiveEvent<Boolean> by lazy {
        SingleLiveEvent<Boolean>()
    }

    val showMessage: SingleLiveEvent<MessageWrapper> by lazy {
        SingleLiveEvent<MessageWrapper>()
    }

    val moveToIntent: SingleLiveEvent<Class<*>> by lazy {
        SingleLiveEvent<Class<*>>()
    }

    protected fun showMessageByStringId(stringId: Int) {
        messageWrapper.messageId = stringId
        showMessage.value = messageWrapper
    }

    protected fun showServerMessage(message: String) {
        messageWrapper.message = message
        showMessage.value = messageWrapper
    }

    protected fun showTheErrorMessage(errorWrapper: MessageWrapper) {
        messageWrapper.messageId = errorWrapper.messageId
        messageWrapper.message = errorWrapper.message
        showMessage.value = messageWrapper
    }
}

