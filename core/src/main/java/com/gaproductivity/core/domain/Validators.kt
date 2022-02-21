package com.gaproductivity.core.domain

import java.util.*

object Validators {

    private const val validNameChars = "qwertyuiopasdfghjklzxcvbnm 1234567890"
    private const val MAX_NAME_LENGTH = 20
    const val MAX_DESCRIPTION_LENGTH = 200
    const val MAX_SIMPLE_NOTE_LENGTH = 2000

    fun validateName(
        name: String
    ) {
        if(name.isEmpty())
            throw InputMismatchException(
                "Name/Title should not be empty"
            )
        if (name.length > MAX_NAME_LENGTH)
            throw InputMismatchException(
                "Name/Title length should not exceed 20 characters"
            )
        name.lowercase().forEach { char ->
            if (char !in validNameChars)
                throw InputMismatchException(
                    "Name/Title should only contain alphabets or numbers"
                )
        }
    }

    fun validateTodoTaskDescription(description: String) {
        if(description.isEmpty())
            throw InputMismatchException(
                "Description should not be empty"
            )
        if (description.length > MAX_DESCRIPTION_LENGTH)
            throw InputMismatchException(
                "Description length should not exceed 200 characters"
            )
    }

    fun validateSimpleNoteContent(content: String) {
        if(content.isEmpty())
            throw InputMismatchException(
                "Content should not be empty"
            )
        if (content.length > MAX_SIMPLE_NOTE_LENGTH)
            throw InputMismatchException(
                "Content length should not exceed 2000 characters"
            )
    }
}