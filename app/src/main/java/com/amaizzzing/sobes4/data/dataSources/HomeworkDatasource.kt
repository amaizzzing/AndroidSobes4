package com.amaizzzing.sobes4.data.dataSources

import com.amaizzzing.sobes4.data.entities.ClassTypes
import com.amaizzzing.sobes4.data.entities.HomeworkEntity

class HomeworkDatasource(): IHomeworkDatasource {
    var homeworks: MutableList<HomeworkEntity> = mutableListOf()
    private var descriptionList = listOf(
        "Упражнения 1,2 стр. 10",
        "Прочитать книгу",
        "Написать сочинение",
        "Провести опыт",
        "Решить пример",
        "Посмотреть урок"
    )

    init {
        populateData()
    }

    override fun getHomeworkList(): List<HomeworkEntity> = homeworks

    private fun populateData() {
        homeworks.clear()
        (0..7).forEach {
            homeworks.add(
                HomeworkEntity(
                    classTypes = ClassTypes.getRandomClassType(),
                    expireDate = (1..2592000).random().toLong(),
                    description = descriptionList.random()
                )
            )
        }
    }
}