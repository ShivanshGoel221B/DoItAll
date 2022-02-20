package com.gaproductivity.components.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gaproductivity.components.presentation.theme.cardColor
import com.gaproductivity.components.presentation.theme.textColor

@Composable
fun PinnedNoteRow(
    modifier: Modifier = Modifier,
    noteIdNamesMap: Map<Int, String>,
    onClick: (Int) -> Unit
) {
    LazyRow(modifier = modifier.padding(
        horizontal = 4.dp,
        vertical = 2.dp
    )) {
        noteIdNamesMap.forEach { (id, name) ->
            item {
                Spacer(modifier = Modifier.width(2.dp))
                Card(
                    shape = MaterialTheme.shapes.small,
                    backgroundColor = MaterialTheme.colors.cardColor,
                    modifier = Modifier.size(80.dp)
                        .clickable {
                            onClick(id)
                        },
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = name,
                            color = MaterialTheme.colors.textColor,
                            fontSize = 18.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp),
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Spacer(modifier = Modifier.width(4.dp))
            }
        }
    }
}