package com.example.oishii.database

import android.content.SearchRecentSuggestionsProvider
import androidx.room.*
import java.lang.Exception

@Dao
interface OishiiDAO {
    @Delete
    fun deleteItemFromDB(item: DishObject)

    @Query("DELETE FROM Cart")
    fun deleteAll()

    @Update
    fun updateItemInDB(item: DishObject)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItemToDB(item: DishObject)

    @Query("SELECT * FROM Cart")
    fun getCartFromDB() : List<DishObject>



}