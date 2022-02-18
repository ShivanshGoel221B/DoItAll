package com.gaproductivity.editor

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import android.widget.LinearLayout
import androidx.compose.ui.platform.ComposeView
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaproductivity.editor.components.SimpleToolBar
import com.gaproductivity.editor.util.CustomColors
import com.gaproductivity.editor.viewmodel.SimpleViewModel


class SimpleTextEditor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attr, defStyleAttr) {

    private val editor: EditText
    private var colorPalette: ThemeColors = ThemeColors.DarkColors

    private lateinit var viewModel: SimpleViewModel

    init {
        inflate(context, R.layout.simple_text_editor, this)
        findViewById<ComposeView>(R.id.toolbarView).run {
            setContent {
                viewModel = hiltViewModel()
                SimpleToolBar()
            }
        }
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
