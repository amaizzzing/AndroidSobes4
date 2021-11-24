package com.amaizzzing.sobes4.data.repositories

import com.amaizzzing.sobes4.atEndOfDay
import com.amaizzzing.sobes4.atStartOfDay
import com.amaizzzing.sobes4.data.dataSources.IClassesDatasource
import com.amaizzzing.sobes4.ui.models.ClassesModel
import java.util.*

class ClassesRepository(
    private val classesDatasource: IClassesDatasource
): IClassesRepository {
    override fun getTodaySortedClasses(needExtra: Boolean): List<ClassesModel> {
        var resultList = classesDatasource.getClassesList()

        resultList = if (!needExtra) {
            resultList.filter { !it.type.isExtra }
        } else {
            resultList
        }
        val time = resultList.random().timeStart
        val dayStart = Date().atStartOfDay(time)
        val dayEnd = Date().atEndOfDay(time)

        return resultList
            .filter { it.timeStart >= dayStart && it.timeEnd <= dayEnd }
            .sortedBy { it.timeStart }
            .map { ClassesModel(it) }
    }
}