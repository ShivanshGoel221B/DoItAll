package com.gaproductivity.core.domain.constants

object CardColors {

    private val todoColors = listOf(
        0x4D00FF00,
        0x4DFF0000,
        0x4D0000FF
    )

    val GREEN = todoColors[0]
    val RED = todoColors[1]
    val BLUE = todoColors[2]

    fun getRandomColor() = todoColors.random()
}
