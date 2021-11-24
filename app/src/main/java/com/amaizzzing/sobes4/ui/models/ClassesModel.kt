package com.amaizzzing.sobes4.ui.models

import com.amaizzzing.sobes4.data.entities.ClassEntity
import com.amaizzzing.sobes4.millisToTimeString
import java.util.*

class ClassesModel(){
    var name: String = ""
    var time: String = ""
    var canSkype: Boolean = false
    var image: String = ""
    var teacher: String = ""
    var isExtra: Boolean = false
    var description: String = ""

    var timeStartInMillis: Long = 0
    var timeEndInMillis: Long = 0

    constructor(classEntity: ClassEntity): this() {
        this.name = classEntity.type.name
        this.time =
            "${Date().millisToTimeString(classEntity.timeStart)} - ${Date().millisToTimeString(classEntity.timeEnd)}"
        this.canSkype = classEntity.canSkype
        this.image = classEntity.type.image
        this.teacher = classEntity.teacher
        this.isExtra = classEntity.type.isExtra
        this.description = classEntity.description

        this.timeStartInMillis = classEntity.timeStart
        this.timeEndInMillis = classEntity.timeEnd
    }

}