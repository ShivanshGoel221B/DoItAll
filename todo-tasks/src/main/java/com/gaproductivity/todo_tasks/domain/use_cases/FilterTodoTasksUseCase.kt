package com.gaproductivity.todo_tasks.domain.use_cases

import com.gaproductivity.database.entity.TodoTask
import com.gaproductivity.database.entity.isMissed
import com.gaproductivity.todo_tasks.domain.filter.TodoTaskFilter
import java.util.*
import javax.inject.Inject

class FilterTodoTasksUseCase @Inject constructor() {

    operator fun invoke(
        allTodoTasks: List<TodoTask>,
        vararg filters: TodoTaskFilter
    ): List<TodoTask> {
        var filteredList: List<TodoTask> = allTodoTasks

        filters.forEach { filter ->
            when(filter) {
                is TodoTaskFilter.FilterByGroup -> {
                    filteredList = filteredList.filter { todoTask ->
                        todoTask.todoTaskGroupId == filter.groupId
                    }
                }
                is TodoTaskFilter.FilterByReminder -> {
                    filteredList = filteredList.filter { todoTask ->
                        todoTask.reminder != null
                    }
                }
                is TodoTaskFilter.FilterByPending -> {
                    filteredList = filteredList.filter { todoTask ->
                        (todoTask.isComplete != filter.isPending) && !todoTask.isMissed()
                    }
                }
                is TodoTaskFilter.FilterByMissed -> {
                    filteredList = filteredList.filter { todoTask ->
                        todoTask.isMissed() == filter.isMissed
                    }
                }
                is TodoTaskFilter.FilterByDeadline -> {
                    filteredList = filteredList.filter { todoTask ->
                        var check =
                        if (filter.hasDeadline)
                            todoTask.deadline != null
                        else
                            todoTask.deadline == null

                        if (filter.isUpcoming && todoTask.deadline != null) {
                            val isUpcoming = todoTask.deadline!! > Calendar.getInstance().timeInMillis
                            check = check && isUpcoming
                        }

                        check
                    }
                }
            }
        }
        return filteredList
    }

}