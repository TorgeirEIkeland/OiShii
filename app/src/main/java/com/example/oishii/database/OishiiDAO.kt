package com.example.oishii.database

import androidx.room.*

@Dao
interface OishiiDAO {
    @Delete
    fun deleteItem(item: DishObject)

    @Query("DELETE FROM Cart")
    fun deleteAll()

    @Update
    fun updateItem(item: DishObject)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(item: DishObject)

    @Query("SELECT * FROM Cart")
    fun getCart() : List<DishObject>


}