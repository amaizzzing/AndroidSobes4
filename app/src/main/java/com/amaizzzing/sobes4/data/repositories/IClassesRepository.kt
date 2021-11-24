package com.amaizzzing.sobes4.data.repositories

import com.amaizzzing.sobes4.ui.models.ClassesModel

interface IClassesRepository {
    fun getTodaySortedClasses(needExtra: Boolean): List<ClassesModel>
}