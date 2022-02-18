package com.gaproductivity.editor

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import android.widget.LinearLayout


class SimpleTextEditor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attr, defStyleAttr) {

    private val editor: EditText
    private var colorPalette: ThemeColors = ThemeColors.DarkColors

    init {
        inflate(context, R.layout.simple_text_editor, this)
        editor = findViewById(R.id.editor)
    }

    fun setTheme(isDark: Boolean = true) {
        colorPalette = if (isDark)
            ThemeColors.DarkColors
        else
            ThemeColors.LightColors

        editor.setTextColor(colorPalette.textColor)
    }
}

sealed class ThemeColors(
    val textColor: Int,
    val background: Int
) {

    object LightColors: ThemeColors(
        textColor = CustomColors.textDark,
        background = CustomColors.backgroundLight
    )

    object DarkColors: ThemeColors(
        textColor = CustomColors.textLight,
        background = CustomColors.backgroundDark
    )
}
