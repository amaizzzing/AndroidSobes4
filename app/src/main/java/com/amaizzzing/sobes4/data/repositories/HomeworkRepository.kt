package com.amaizzzing.sobes4.data.repositories

import com.amaizzzing.sobes4.data.dataSources.HomeworkDatasource
import com.amaizzzing.sobes4.data.dataSources.IHomeworkDatasource
import com.amaizzzing.sobes4.ui.models.HomeworkModel

class HomeworkRepository(
    private val homeworkDatasource: IHomeworkDatasource
): IHomeworkRepository {
    override fun getSortedHomeworks(): List<HomeworkModel> =
        homeworkDatasource
            .getHomeworkList()
            .sortedBy { it.expireDate }
            .map {
                HomeworkModel(it)
            }
}