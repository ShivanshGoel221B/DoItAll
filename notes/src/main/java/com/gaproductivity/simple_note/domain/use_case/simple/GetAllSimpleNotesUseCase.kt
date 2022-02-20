package com.gaproductivity.simple_note.domain.use_case.simple

import com.gaproductivity.database.entity.SimpleNote
import com.gaproductivity.simple_note.domain.repository.SimpleNoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllSimpleNotesUseCase
@Inject constructor(
    private val repository: SimpleNoteRepository
) {

    operator fun invoke(): Flow<List<SimpleNote>> {
        return repository.getAllSimpleNotes()
    }

}