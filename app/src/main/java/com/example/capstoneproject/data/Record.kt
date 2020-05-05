package com.example.capstoneproject.data

import java.util.*


data class Record @JvmOverloads constructor(
    var name: String = "",
    var age: Int = 0,
    var sex: String = "",
    var id: String = UUID.randomUUID().toString()
) {

    /**
     * True if the task is completed, false if it's active.
     */
    var isCompleted = false

    val isActive
        get() = !isCompleted

}