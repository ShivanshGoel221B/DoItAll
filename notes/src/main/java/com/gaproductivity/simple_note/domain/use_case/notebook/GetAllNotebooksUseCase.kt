package com.gaproductivity.simple_note.domain.use_case.notebook

import com.gaproductivity.database.entity.NoteBook
import com.gaproductivity.simple_note.domain.repository.NotebookRepository
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