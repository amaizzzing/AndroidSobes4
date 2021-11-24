package com.amaizzzing.sobes4.data.repositories

import com.amaizzzing.sobes4.ui.models.HomeworkModel

interface IHomeworkRepository {
    fun getSortedHomeworks(): List<HomeworkModel>
}