package com.example.quizapp.utils

import com.example.quizapp.R

object IconPicker {
    val icons = arrayOf(
        R.drawable.ic_iconc,
        R.drawable.ic_iconhtml,
        R.drawable.ic_iconjava,
        R.drawable.ic_iconpython,
        R.drawable.ic_iconjavascript,
        R.drawable.ic_iconnodejs
    )

    var currIcon = -1
    fun getIcon(): Int {
        currIcon = (currIcon + 1) % icons.size
        return icons[currIcon]
    }
}