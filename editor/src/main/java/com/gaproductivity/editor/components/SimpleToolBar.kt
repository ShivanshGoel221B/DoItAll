package com.gaproductivity.editor.components

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatBold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaproductivity.editor.viewmodel.SimpleViewModel

@Composable
fun SimpleToolBar(
    viewModel: SimpleViewModel = hiltViewModel()
) {
    LazyRow {
        item {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.FormatBold, contentDescription = "")
            }
        }
        item {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.FormatBold, contentDescription = "")
            }
        }
        item {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.FormatBold, contentDescription = "")
            }
        }
        item {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.FormatBold, contentDescription = "")
            }
        }
        item {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.FormatBold, contentDescription = "")
            }
        }
        item {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.FormatBold, contentDescription = "")
            }
        }
        item {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.FormatBold, contentDescription = "")
            }
        }
        item {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.FormatBold, contentDescription = "")
            }
        }
        item {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.FormatBold, contentDescription = "")
            }
        }
        item {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.FormatBold, contentDescription = "")
            }
        }
        item {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.FormatBold, contentDescription = "")
            }
        }
    }
}