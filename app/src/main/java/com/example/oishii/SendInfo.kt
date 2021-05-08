package com.example.oishii

interface SendInfo {
    fun insert(dao: OishiiDAO, dish: DishObject)
}