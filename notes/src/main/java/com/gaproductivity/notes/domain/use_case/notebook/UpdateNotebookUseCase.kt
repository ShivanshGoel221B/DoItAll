package com.gaproductivity.notes.domain.use_case.notebook

import com.gaproductivity.database.entity.NoteBook
import com.gaproductivity.notes.domain.repository.NotebookRepository
import javax.inject.Inject

class UpdateNotebookUseCase
@Inject constructor(
    private val repository: NotebookRepository
) {

    suspend operator fun invoke(noteBook: NoteBook) {
        repository.updateNotebook(noteBook)
    }

}