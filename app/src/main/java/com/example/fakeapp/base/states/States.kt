package com.example.fakeapp.base.states

import android.content.Context
import android.content.res.Resources
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import com.example.fakeapp.R

class States(private val viewState: ViewState) {


    fun createLoadView(contentView: View): View {
        return ProgressBar(contentView.context, null, android.R.attr.progressBarStyle).apply {
            layoutParams = when (contentView.parent) {
                is FrameLayout -> FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
                ).apply { gravity = Gravity.CENTER }
                is RelativeLayout -> RelativeLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                ).apply {
                    addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)
                }
                is CoordinatorLayout -> CoordinatorLayout.LayoutParams(
                    CoordinatorLayout.LayoutParams.MATCH_PARENT,
                    CoordinatorLayout.LayoutParams.WRAP_CONTENT,
                ).apply {
                    gravity = Gravity.CENTER
                }
                is ConstraintLayout -> ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ).apply {
                    topToTop = ConstraintLayout.LayoutParams.PARENT_ID
                    bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
                    verticalBias = 0.5f
                    horizontalBias = 0.5f
                }
                else -> ViewGroup.MarginLayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                )
            }
            indeterminateDrawable.colorFilter = PorterDuffColorFilter(
                ContextCompat.getColor(context, R.color.design_default_color_secondary),
                PorterDuff.Mode.SRC_IN
            )
        }
    }

    fun createErrorView(
        context: Context
    ): View {
        return LinearLayout(context).apply {
            layoutParams = ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
            )
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            viewState.tvError = TextView(context).apply {
                setPadding(16f.dp, 16f.dp, 16f.dp, 16f.dp)
                gravity = Gravity.CENTER
                textSize = 16f
                setTextColor(ContextCompat.getColor(context, R.color.black))
            }
            viewState.tvError!!.text = "При загрузке данных произошла ошибка.\\nПроверьте Ваше подключение к сети."
            addView(viewState.tvError)

            viewState.btnRetry = Button(context).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                )
                text = "Повторить попытку"
            }
            addView(viewState.btnRetry)
        }
    }
}

val Float.dp: Int
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    ).toInt()