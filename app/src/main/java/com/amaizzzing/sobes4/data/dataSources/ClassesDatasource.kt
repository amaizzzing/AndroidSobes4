package com.amaizzzing.sobes4.data.dataSources

import com.amaizzzing.sobes4.data.entities.ClassEntity
import com.amaizzzing.sobes4.data.entities.ClassTypes
import com.amaizzzing.sobes4.data.entities.HomeworkEntity
import com.amaizzzing.sobes4.toBoolean

class ClassesDatasource: IClassesDatasource {
    val classes = mutableListOf<ClassEntity>()

    val teachersList = listOf("Ivanova I.I.", "Petrova P.P.", "Sidorovich S.S.", "Pupkina P.P.", "Shtor S.S.", "Smith T.H.")

    init {
        populateData()
    }

    private fun populateData() {
        classes.addAll(
            listOf(
                ClassEntity(
                    type = ClassTypes.getRandomClassType(),
                    timeStart = 1637463600000L,
                    canSkype = (0..1).random().toBoolean(),
                    teacher = teachersList.random()
                ),
                ClassEntity(
                    type = ClassTypes.getRandomClassType(),
                    timeStart = 1637467200000L,
                    canSkype = (0..1).random().toBoolean(),
                    teacher = teachersList.random()
                ),
                ClassEntity(
                        type = ClassTypes.getRandomClassType(),
                timeStart = 1637470800000,
                canSkype = (0..1).random().toBoolean(),
                teacher = teachersList.random()
            ),
                    ClassEntity(
                    type = ClassTypes.getRandomClassType(),
            timeStart = 1637481600000,
            canSkype = (0..1).random().toBoolean(),
            teacher = teachersList.random()
        ),
                ClassEntity(
                    type = ClassTypes.getRandomExtraClassType(),
                    timeStart = 1637485200000,
                    timeEnd = 1637490600000,
                    canSkype = (0..1).random().toBoolean(),
                    teacher = teachersList.random(),
                    description = "this is random sport!!!!!"
                ),


        ClassEntity(
            type = ClassTypes.getRandomClassType(),
            timeStart = 1637550000000,
            canSkype = (0..1).random().toBoolean(),
            teacher = teachersList.random()
        ),
        ClassEntity(
            type = ClassTypes.getRandomClassType(),
            timeStart = 1637553600000,
            canSkype = (0..1).random().toBoolean(),
            teacher = teachersList.random()
        ),
        ClassEntity(
            type = ClassTypes.getRandomClassType(),
            timeStart = 1637560800000,
            canSkype = (0..1).random().toBoolean(),
            teacher = teachersList.random()
        ),
                ClassEntity(
                type = ClassTypes.getRandomExtraClassType(),
                timeStart = 1637566200000,
                timeEnd = 1637573400000,
                canSkype = (0..1).random().toBoolean(),
                teacher = teachersList.random(),
                description = "this is random sport!!!!!"
            ),
                ClassEntity(
                    type = ClassTypes.getRandomExtraClassType(),
                    timeStart = 1637586000000,
                    timeEnd = 1637589600000,
                    canSkype = (0..1).random().toBoolean(),
                    teacher = teachersList.random(),
                    description = "this is random sport!!!!!"
                ),
            )
        )
    }

    override fun getClassesList(): List<ClassEntity> = classes
}