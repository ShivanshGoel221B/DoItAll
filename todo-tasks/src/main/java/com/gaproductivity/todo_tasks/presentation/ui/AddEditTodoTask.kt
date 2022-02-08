package com.gaproductivity.todo_tasks.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaproductivity.components.presentation.theme.primaryColor
import com.gaproductivity.components.presentation.theme.primaryTranslucent
import com.gaproductivity.components.presentation.theme.textColor
import com.gaproductivity.components.presentation.theme.translucentGrayColor
import com.gaproductivity.core.domain.UiEvents
import com.gaproductivity.core.domain.Validators
import com.gaproductivity.core.domain.constants.CardColors
import com.gaproductivity.database.entity.TodoTask
import com.gaproductivity.todo_tasks.presentation.event.TodoTaskEvent
import com.gaproductivity.todo_tasks.presentation.viewmodel.TodoTaskViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collect

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
            Button(
                onClick = {
                    onEvent(TodoTaskEvent.SubmitTodoTaskGroup)
                },
                shape = RoundedCornerShape(25.dp)
            ) {
                Text(
                    text = "Create",
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 12.dp),
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
    val todoTaskEvent = viewModel::onEvent
    val todoTask = viewModel.addEditTodoTask.value

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 8.dp),
        contentPadding = PaddingValues(bottom = 110.dp),
    ) {

        //TodoTask Color
        item {
            Text(text = "Select a color for the task")
            Spacer(modifier = Modifier.height(4.dp))
            CardColorSelector(
                value = todoTask.todoTaskColor,
                onChange = { color ->
                    viewModel.updateTodoTaskForm(
                        todoTask.copy(
                            todoTaskColor = color
                        )
                    )
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

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
            viewModel.formTodoTaskTitleError.value?.let {errorMessage ->
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colors.error,
                    fontSize = 10.sp
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        //TodoTask Description
        item {
            Spacer(modifier = Modifier.height(4.dp))
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

            viewModel.formDescriptionError.value?.let {errorMessage ->
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colors.error,
                    fontSize = 10.sp
                )
            }
        }

    }
}

@Composable
fun CardColorSelector(
    value: Int,
    onChange: (Int) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        val modifierSelected = Modifier.border(
            width = 2.dp,
            color = primaryColor,
            shape = CircleShape
        )
        val redSelected = value == CardColors.RED
        val blueSelected = value == CardColors.BLUE
        val greenSelected = value == CardColors.GREEN
        val redButtonModifier = if (redSelected) modifierSelected else Modifier
        val blueButtonModifier = if (blueSelected) modifierSelected else Modifier
        val greenButtonModifier = if (greenSelected) modifierSelected else Modifier

        Button(
            onClick = {
                onChange(CardColors.RED)
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(CardColors.RED),
            ),
            shape = CircleShape,
            modifier = redButtonModifier.size(36.dp)
        ) {

        }
        Button(
            onClick = {
                onChange(CardColors.BLUE)
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(CardColors.BLUE)
            ),
            shape = CircleShape,
            modifier = blueButtonModifier.size(36.dp)
        ) {

        }
        Button(
            onClick = {
                onChange(CardColors.GREEN)
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(CardColors.GREEN)
            ),
            shape = CircleShape,
            modifier = greenButtonModifier.size(36.dp)
        ) {

        }
    }
}