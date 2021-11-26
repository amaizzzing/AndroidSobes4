package com.amaizzzing.sobes4.ui.models

import android.graphics.Color
import com.amaizzzing.sobes4.data.entities.HomeworkEntity

class HomeworkModel() {
    var classesName: String = ""
    var expires: String = ""
    var description: String = ""
    var image: String = ""
    var expiresTextColor: Int = 0x55FFFFFF

    constructor(homeworkEntity: HomeworkEntity): this() {
        this.classesName = homeworkEntity.classTypes.name
        this.expires = getExpireString(homeworkEntity.expireDate)
        this.description = homeworkEntity.description
        this.image = homeworkEntity.classTypes.image
    }

    private fun getExpireString(time: Long): String {
        val seconds = time % 60
        val minutes = (time / (60) % 60)
        val hours = (time / (60 * 60) % 24)
        val days = (time / (60 * 60 * 24) % 365)

        if (days < 3) {
            expiresTextColor = Color.RED
        }

        return when {
            days > 0 -> "$days days expired"
            hours > 0 -> "$hours hours expired"
            minutes > 0 -> "$minutes minues expired"
            else -> "$seconds seconds expired"
        }
    }
}