package com.gaproductivity.core.domain

import java.util.*

object Validators {

    private const val validNameChars = "qwertyuiopasdfghjklzxcvbnm 1234567890"
    private const val MAX_NAME_LENGTH = 20
    const val MAX_DESCRIPTION_LENGTH = 200

    fun validateName(
        name: String
    ) {
        if(name.isEmpty())
            throw InputMismatchException(
                "Name should not be empty"
            )
        if (name.length > MAX_NAME_LENGTH)
            throw InputMismatchException(
                "Name length should not exceed 20 characters"
            )
        name.lowercase().forEach { char ->
            if (char !in validNameChars)
                throw InputMismatchException(
                    "Name should only contain alphabets or numbers"
                )
        }
    }

    fun validateTodoTaskDescription(
        name: String
    ) {
        if(name.isEmpty())
            throw InputMismatchException(
                "Name should not be empty"
            )
        if (name.length > MAX_DESCRIPTION_LENGTH)
            throw InputMismatchException(
                "Description length should not exceed 200 characters"
            )
    }
}