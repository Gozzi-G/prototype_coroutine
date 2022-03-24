package com.example.moviesdbapp.base.states

sealed class StateLCE(open val animate: Boolean = false) {

    class Loading(
        override val animate: Boolean
    ) : StateLCE(animate)

    class Content(
        override val animate: Boolean
    ) : StateLCE(animate)

    class Error(
        val message: String = "",
        override val animate: Boolean
    ) : StateLCE(animate)
}