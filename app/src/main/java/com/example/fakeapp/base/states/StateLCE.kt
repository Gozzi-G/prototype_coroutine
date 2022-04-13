package com.example.fakeapp.base.states

import java.lang.StringBuilder

sealed class StateLCE() {

    class Loading() : StateLCE()

    class Content() : StateLCE()

    class Error(
        private val throwable: Throwable?,
        private val customMessage: String = "",
    ) : StateLCE() {
        val message: CharSequence
            get() {
                val message = StringBuilder()
                if (customMessage.isNotBlank()) {
                    message.append(customMessage)
                } else {
                    message.append("При загрузке данных произошла ошибка.\\nПроверьте Ваше подключение к сети.")
                }
                return message
            }
    }
}