package com.example.fakeapp.base.states

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import java.lang.IllegalStateException
import java.util.HashMap

class ViewState private constructor(contentView: View) {

    companion object State {
        private const val CONTENT = "content"
        private const val LOADING = "loading"
        private const val ERROR = "error"
    }

    class Builder(private val contentView: View) {
        private val viewState = ViewState(contentView)
        private val states = States(viewState)

        fun load(): Builder {
            val loadeView = states.createLoadView(contentView)
            viewState.addState(LOADING, loadeView)
            return this
        }

        fun error(): Builder {
            val errorView = states.createErrorView(contentView.context)
            viewState.addState(ERROR, errorView)
            return this
        }

        fun state(state: String, target: View): Builder {
            viewState.addState(state, target)
            return this
        }

        fun build() = viewState
    }

    private val states = HashMap<String, View>()
    private val parent: ViewGroup
    private var currentView = contentView

    /** TextView that is used when error state massage display */
    var tvError: TextView? = null

    /** Button that is used when error state is display */
    var btnRetry: Button? = null

    init {
        states[CONTENT] = contentView
        parent = contentView.parent as ViewGroup
    }

    private fun addState(state: String, targetView: View) {
        if (state in states) {
            throw  IllegalStateException("State $state already added")
        }
        targetView.isVisible = false
        states[state] = targetView
    }

    fun content() {
        state(CONTENT)
    }

    fun load() {
        state(LOADING)
    }

    fun error(error: CharSequence) {
        tvError?.text = error
        state(ERROR)
    }

    fun state(state: String) {
        val target = states[state] ?: throw IllegalStateException("State $state was not added yet")

        if (target.parent != parent) {
            parent.addView(target)
        }

        if (currentView != target) {
            currentView.isVisible = false
            target.isVisible = true
            currentView = target
        }
    }
}