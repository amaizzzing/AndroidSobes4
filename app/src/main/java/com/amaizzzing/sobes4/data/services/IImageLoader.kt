package com.amaizzzing.sobes4.data.services

interface IImageLoader<T> {
    fun loadInto(source: String, container: T)

    fun loadIntoWithRoundCorners(source: String, container: T, corner: Int)

    fun loadIntoWithRoundCornersFromResource(id: Int, container: T, corner: Int)
}