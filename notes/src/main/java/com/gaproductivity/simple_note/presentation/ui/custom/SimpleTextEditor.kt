package com.gaproductivity.simple_note.presentation.ui.custom

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.LinearLayout
import com.gaproductivity.simple_note.R
import jp.wasabeef.richeditor.RichEditor

class SimpleTextEditor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attr, defStyleAttr) {

    private var editor: RichEditor
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

        editor.run {
            setEditorFontColor(colorPalette.textColor)
            setEditorBackgroundColor(colorPalette.background)
        }
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
