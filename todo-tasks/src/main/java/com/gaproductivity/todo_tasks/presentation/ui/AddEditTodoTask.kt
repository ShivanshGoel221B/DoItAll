package com.gaproductivity.todo_tasks.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaproductivity.components.presentation.theme.primaryTranslucent
import com.gaproductivity.components.presentation.theme.textColor
import com.gaproductivity.components.presentation.theme.translucentGrayColor
import com.gaproductivity.components.presentation.ui.FormInputError
import com.gaproductivity.components.presentation.ui.datePickerDialog
import com.gaproductivity.components.presentation.ui.timePickerDialog
import com.gaproductivity.core.domain.Converters
import com.gaproductivity.core.domain.UiEvents
import com.gaproductivity.core.domain.Validators
import com.gaproductivity.database.entity.TodoTask
import com.gaproductivity.todo_tasks.presentation.event.TodoTaskEvent
import com.gaproductivity.todo_tasks.presentation.viewmodel.TodoTaskViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collect
import java.util.*

@Composable
fun AddEditTodoTask(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator,
    initialTodoTask: TodoTask? = null,
    todoTaskGroupId: Int,
    viewModel: TodoTaskViewModel = hiltViewModel(),
    titleBar: @Composable () -> Unit
) {
    val onEvent = viewModel::onEvent
    LaunchedEffect(key1 = true) {
        viewModel.initAddEditTodoTask(
            initialTodoTask,
            todoTaskGroupId
        )
        viewModel.uiEvents.collect { event ->
            if (event is UiEvents.PopBackStack)
                navigator.popBackStack()
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = titleBar,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(TodoTaskEvent.SubmitTodoTask)
                },
            ) {
                Text(
                    text = if(initialTodoTask == null) "CREATE" else "UPDATE",
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 18.dp),
                )
            }
        }
    ) {
        TodoTaskForm(viewModel = viewModel)
    }
}

@Composable
private fun TodoTaskForm(
    viewModel: TodoTaskViewModel
) {
    val todoTask = viewModel.addEditTodoTask.value

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 8.dp),
        contentPadding = PaddingValues(bottom = 110.dp),
    ) {

        // TodoTask Title
        item {
            Text(text = "Enter the Task Title")
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = primaryTranslucent
                ),
                shape = MaterialTheme.shapes.medium,
                value = todoTask.todoTaskTitle,
                onValueChange = {
                    viewModel.updateTodoTaskForm(
                        todoTask.copy(todoTaskTitle = it)
                    )
                },
                isError = viewModel.formTodoTaskTitleError.value != null,
                textStyle = TextStyle(
                    color = MaterialTheme.colors.textColor,
                ),
                placeholder = {
                    Text("Enter Task Title")
                }
            )
            FormInputError(errorMessage = viewModel.formTodoTaskTitleError.value)
        }

        //TodoTask Description
        item {
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(text = "Enter the Task Description")
                Text(
                    text = "${todoTask.todoTaskDescription.length}/${Validators.MAX_DESCRIPTION_LENGTH}",
                    fontSize = 10.sp,
                    color = MaterialTheme.colors.primary
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 120.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = primaryTranslucent
                ),
                shape = MaterialTheme.shapes.medium,
                value = todoTask.todoTaskDescription,
                onValueChange = {
                    if (
                        it.length <= Validators.MAX_DESCRIPTION_LENGTH
                    )
                        viewModel.updateTodoTaskForm(
                            todoTask.copy(todoTaskDescription = it)
                        )
                },
                isError = viewModel.formDescriptionError.value != null,
                textStyle = TextStyle(
                    color = MaterialTheme.colors.textColor,
                ),
                placeholder = {
                    Text("Enter Task Description")
                },
                maxLines = 5
            )
            FormInputError(viewModel.formDescriptionError.value)
        }

        //Deadline
        item {
            val context = LocalContext.current
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AnimatedVisibility(
                    visible = todoTask.deadline == null
                ) {
                    OutlinedButton(
                        onClick = {
                            datePickerDialog(
                                context = context,
                                value = Calendar.getInstance(),
                                minDate = Calendar.getInstance().timeInMillis,
                                onChange = { selectedDate ->
                                    selectedDate[Calendar.HOUR_OF_DAY] = 23
                                    selectedDate[Calendar.MINUTE] = 59
                                    viewModel.updateTodoTaskForm(
                                        todoTask.copy(
                                            deadline = selectedDate.timeInMillis
                                        )
                                    )
                                }
                            )
                        },
                        shape = RoundedCornerShape(30.dp)
                    ) {
                        Text(
                            text = "Add a Deadline (Optional)",
                            modifier = Modifier.padding(
                                horizontal = 18.dp,
                                vertical = 6.dp
                            )
                        )
                    }
                }

                if(todoTask.deadline != null) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Deadline")
                        Spacer(modifier = Modifier.height(4.dp))
                        val calendar = Calendar.getInstance()
                        calendar.timeInMillis = todoTask.deadline!!
                        DateTimeCard(
                            value = calendar,
                            onChange = {
                                viewModel.updateTodoTaskForm(
                                    todoTask.copy(
                                        deadline = it.timeInMillis
                                    )
                                )
                            },
                            onDelete = {
                                viewModel.updateTodoTaskForm(
                                    todoTask.copy(
                                        deadline = null
                                    )
                                )
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }

        // Reminder
        item {
            val context = LocalContext.current
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AnimatedVisibility(
                    visible = todoTask.reminder == null
                ) {
                    OutlinedButton(
                        onClick = {
                            datePickerDialog(
                                context = context,
                                value = Calendar.getInstance(),
                                minDate = Calendar.getInstance().timeInMillis,
                                onChange = { selectedDate ->
                                    selectedDate[Calendar.HOUR_OF_DAY] = 8
                                    selectedDate[Calendar.MINUTE] = 0
                                    viewModel.updateTodoTaskForm(
                                        todoTask.copy(
                                            reminder = selectedDate.timeInMillis
                                        )
                                    )
                                }
                            )
                        },
                        shape = RoundedCornerShape(30.dp)
                    ) {
                        Text(
                            text = "Add a Reminder (Optional)",
                            modifier = Modifier.padding(horizontal = 18.dp, vertical = 6.dp)
                        )
                    }
                }

                if(todoTask.reminder != null) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Reminder")
                        Spacer(modifier = Modifier.height(4.dp))
                        val calendar = Calendar.getInstance()
                        calendar.timeInMillis = todoTask.reminder!!
                        DateTimeCard(
                            value = calendar,
                            onChange = {
                                viewModel.updateTodoTaskForm(
                                    todoTask.copy(
                                        reminder = it.timeInMillis
                                    )
                                )
                            },
                            onDelete = {
                                viewModel.updateTodoTaskForm(
                                    todoTask.copy(
                                        reminder = null
                                    )
                                )
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun DateTimeCard(
    value: Calendar,
    onChange: (Calendar) -> Unit,
    onDelete: () -> Unit
) {
    val context = LocalContext.current
    Card(
        border = BorderStroke(
            width = 1.dp,
            color = translucentGrayColor
        ),
        elevation = 0.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .padding(6.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = Converters.getFormattedDate(value.timeInMillis),
                        fontSize = 18.sp,
                        color = MaterialTheme.colors.textColor,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedButton(
                        onClick = {
                            datePickerDialog(
                                context = context,
                                value = value,
                                minDate = Calendar.getInstance().timeInMillis,
                                onChange = {
                                    it[Calendar.HOUR_OF_DAY] = value[Calendar.HOUR_OF_DAY]
                                    it[Calendar.MINUTE] = value[Calendar.MINUTE]
                                    onChange(it)
                                }
                            )
                        }
                    ) {
                        Text(
                            text = "Change",
                            modifier = Modifier.padding(horizontal = 6.dp)
                        )
                    }
                }

                Column {
                    Text(
                        text = Converters.getFormattedTime(value.timeInMillis),
                        fontSize = 18.sp,
                        color = MaterialTheme.colors.textColor,
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    OutlinedButton(
                        onClick = {
                            timePickerDialog(
                                context = context,
                                value = value,
                                onChange = {
                                    it[Calendar.YEAR] = value[Calendar.YEAR]
                                    it[Calendar.MONTH] = value[Calendar.MONTH]
                                    it[Calendar.DAY_OF_MONTH] = value[Calendar.DAY_OF_MONTH]
                                    onChange(it)
                                }
                            )
                        }
                    ) {
                        Text(
                            text = "Change",
                            modifier = Modifier.padding(horizontal = 6.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Divider()
            Spacer(modifier = Modifier.height(2.dp))
            OutlinedButton(
                onClick = onDelete,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent,
                    contentColor = Color.Red
                ),
                border = BorderStroke(width = 2.dp, color = Color.Red),
                shape = RoundedCornerShape(20.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Cancel",
                        tint = Color.Red
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "Remove",
                        color = Color.Red
                    )
                }
            }
        }
    }
}