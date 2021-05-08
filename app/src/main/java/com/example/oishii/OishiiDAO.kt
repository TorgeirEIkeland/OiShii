package com.example.oishii

import androidx.room.*

@Dao
interface OishiiDAO {
    @Delete
    fun deleteItem(item: DishObject)

    @Update
    fun updateItem(item: DishObject)

    @Insert
    fun insertItem(item: DishObject)

    @Query("SELECT * FROM Cart")
    fun getCart() : List<DishObject>
}