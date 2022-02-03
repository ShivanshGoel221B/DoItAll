package com.gaproductivity.core.domain

import java.util.*

object Validators {

    private const val validNameChars = "qwertyuiopasdfghjklzxcvbnm 1234567890"
    private const val MAX_NAME_LENGTH = 20

    fun validateName(
        name: String
    ) {
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
}