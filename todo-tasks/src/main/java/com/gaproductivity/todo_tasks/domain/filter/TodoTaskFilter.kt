package com.gaproductivity.todo_tasks.domain.filter

sealed class TodoTaskFilter {
    data class FilterByGroup(val groupId: Int): TodoTaskFilter()
    data class FilterByReminder(val isReminder: Boolean): TodoTaskFilter()
    data class FilterByPending(val isPending: Boolean): TodoTaskFilter()
    data class FilterByMissed(val isMissed: Boolean): TodoTaskFilter()
}
