package com.gaproductivity.notes.domain.use_case.simple

import com.gaproductivity.database.entity.SimpleNote
import com.gaproductivity.notes.domain.repository.SimpleNoteRepository
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