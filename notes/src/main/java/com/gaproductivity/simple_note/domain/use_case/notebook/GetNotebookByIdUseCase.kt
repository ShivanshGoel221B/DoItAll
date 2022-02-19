package com.gaproductivity.simple_note.domain.use_case.notebook

import com.gaproductivity.database.entity.NoteBook
import com.gaproductivity.simple_note.domain.repository.NotebookRepository
import javax.inject.Inject

class GetNotebookByIdUseCase
@Inject constructor(
    private val repository: NotebookRepository
) {
    suspend operator fun invoke(notebookId: Int): NoteBook? {
        return repository.getNotebookById(notebookId)
    }

}