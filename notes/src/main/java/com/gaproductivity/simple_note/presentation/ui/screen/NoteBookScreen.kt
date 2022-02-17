package com.gaproductivity.simple_note.presentation.ui.screen

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.widget.TextView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import jp.wasabeef.richeditor.RichEditor


@Composable
fun NoteBookScreen(
    navigator: DestinationsNavigator,
    topBar: @Composable () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = topBar,
        floatingActionButton = {
            FloatingActionButton(onClick = {

            }) {
                Column {
                    AndroidView(
                        modifier = Modifier,
                        factory = { context ->
                            RichEditor(context).apply {
                                setOnTextChangeListener {
                                    this.setItalic()
                                }
                            }
                        }
                    )
                }
            }
        }
    ) {

    }
}