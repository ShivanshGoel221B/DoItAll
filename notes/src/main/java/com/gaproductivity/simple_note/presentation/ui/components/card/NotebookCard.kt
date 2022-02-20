package com.gaproductivity.simple_note.presentation.ui.components.card

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.rounded.MenuBook
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gaproductivity.components.presentation.theme.cardColor
import com.gaproductivity.components.presentation.theme.primaryTranslucent
import com.gaproductivity.components.presentation.theme.textColor
import com.gaproductivity.database.entity.NoteBook
import com.gaproductivity.simple_note.presentation.ui.components.util.NotebookCardOptions
import com.gaproductivity.simple_note.util.NotesNavigation

@Composable
fun NotebookCard(
    modifier: Modifier = Modifier,
    noteBook: NoteBook,
    notesNavigation: (NotesNavigation) -> Unit
) {

    var viewOptions by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = modifier,
        backgroundColor = Color.Transparent,
        shape = RoundedCornerShape(12.dp),
        elevation = 0.dp,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colors.cardColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxHeight()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colors.cardColor),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = noteBook.noteBookName,
                    modifier = Modifier.padding(
                        horizontal = 12.dp,
                        vertical = 6.dp
                    ),
                    fontSize = 20.sp,
                    color = MaterialTheme.colors.textColor,
                    overflow = TextOverflow.Ellipsis
                )
                IconButton(onClick = {
                    viewOptions = !viewOptions
                }) {
                    val icon =
                        if (viewOptions) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown
                    Icon(
                        imageVector = icon,
                        contentDescription = "Show More",
                        tint = MaterialTheme.colors.textColor
                    )
                }
            }
            AnimatedVisibility(visible = viewOptions) {
                Column {
                    NotebookCardOptions(
                        onDeleteClick = {

                        },
                        onEditClick = {
                            notesNavigation(
                                NotesNavigation.ToEditNoteBook(noteBook)
                            )
                        },
                        onMarkClick = {

                        }
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(MaterialTheme.colors.cardColor)
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.MenuBook,
                    contentDescription = "Book",
                    modifier = Modifier.size(48.dp),
                    tint = primaryTranslucent
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Test List of notes")
            }
        }
    }

}