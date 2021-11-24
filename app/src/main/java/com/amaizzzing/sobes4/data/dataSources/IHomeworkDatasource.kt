package com.amaizzzing.sobes4.data.dataSources

import com.amaizzzing.sobes4.data.entities.HomeworkEntity

interface IHomeworkDatasource {
    fun getHomeworkList(): List<HomeworkEntity>
}