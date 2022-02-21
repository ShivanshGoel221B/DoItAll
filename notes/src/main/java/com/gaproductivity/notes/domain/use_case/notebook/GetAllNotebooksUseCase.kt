package com.gaproductivity.notes.domain.use_case.notebook

import com.gaproductivity.database.entity.NoteBook
import com.gaproductivity.notes.domain.repository.NotebookRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllNotebooksUseCase
@Inject constructor(
    private val repository: NotebookRepository
) {

    operator fun invoke(): Flow<List<NoteBook>> {
        return repository.getAllNotebooks()
    }

}