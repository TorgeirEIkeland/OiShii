package com.example.oishii.database

import androidx.lifecycle.viewModelScope
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Entity(tableName = "Cart")
class DishObject(
    @PrimaryKey val dishName: String,
    val description: String?,
    val alergies: String?,
    val price: Int?,
    var amount: Int?
) {
    fun isTheSame(dish: DishObject): Boolean {
        return (dish.dishName == dishName)
    }
}