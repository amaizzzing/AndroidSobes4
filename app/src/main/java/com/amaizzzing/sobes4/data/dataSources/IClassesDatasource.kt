package com.amaizzzing.sobes4.data.dataSources

import com.amaizzzing.sobes4.data.entities.ClassEntity

interface IClassesDatasource {
    fun getClassesList(): List<ClassEntity>
}