package com.amaizzzing.sobes4.data.entities

sealed class ClassTypes(
    var name: String,
    var image: String,
    var isExtra: Boolean = false,
    var duration: Long = 2_700_000L
) {
    class History(): ClassTypes("History", "history")
    class Literature(): ClassTypes("Literature", "literature")
    class Mathematic(): ClassTypes("Mathematic", "mathematics")
    class Physics(): ClassTypes("Physics", "physics")

    class Basketball(): ClassTypes("Basketball", "basketball", true)
    class Football(): ClassTypes("Football", "football", true)

    companion object {
        fun getRandomClassType(): ClassTypes =
            listOf(History(), Literature(), Mathematic(), Physics()).random()

        fun getRandomExtraClassType(): ClassTypes =
            listOf(Basketball(), Football()).random()
    }
}