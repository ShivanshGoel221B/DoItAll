package com.gaproductivity.editor

import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaproductivity.editor.viewmodel.SimpleViewModel

@Composable
fun SimpleTextEditor(
    viewModel: SimpleViewModel = hiltViewModel()
) {
    
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            value = viewModel.currentString.value,
            onValueChange = viewModel::updateCurrentString
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(text = buildAnnotatedString {

        })

    }
    
}