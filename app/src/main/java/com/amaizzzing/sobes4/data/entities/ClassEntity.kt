package com.amaizzzing.sobes4.data.entities

data class ClassEntity(
    var type: ClassTypes,
    var timeStart: Long,
    var timeEnd: Long = timeStart,
    var canSkype: Boolean = false,
    var teacher: String,
    var description: String = ""
) {
    init {
        timeEnd= timeStart + type.duration
    }
}