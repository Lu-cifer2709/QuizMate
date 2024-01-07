package com.example.quizapp.utils

object ColorPicker {
    val colors = arrayOf(
        "#3EB9DF",
        "#3685BC",
        "#D36280",
        "#E44F55",
        "#FA8056",
        "#818BCA",
        "#7D659F",
        "#51BAB3",
        "#4FB66C",
        "#E3AD17"
    )

    var currColorIndex = 0
    fun getColor(): String {
        currColorIndex = (currColorIndex + 1) % colors.size
        return colors[currColorIndex]
    }
}