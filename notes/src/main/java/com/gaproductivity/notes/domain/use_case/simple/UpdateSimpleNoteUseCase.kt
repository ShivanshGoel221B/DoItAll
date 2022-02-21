package com.gaproductivity.notes.domain.use_case.simple

import com.gaproductivity.database.entity.SimpleNote
import com.gaproductivity.notes.domain.repository.SimpleNoteRepository
import javax.inject.Inject

class UpdateSimpleNoteUseCase
@Inject constructor(
    private val repository: SimpleNoteRepository
) {

    suspend operator fun invoke(simpleNote: SimpleNote) {
        repository.updateSimpleNote(simpleNote)
    }

}