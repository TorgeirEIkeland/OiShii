package com.example.oishii.database

import androidx.room.Entity
import androidx.room.PrimaryKey

//table for checkoutFragment
@Entity(tableName = "Cart")
class DishObject(
    @PrimaryKey val dishName: String,
    val description: String?,
    val alergies: String?,
    val price: Int,
    var amount: Int
) {
    fun isTheSame(dish: DishObject): Boolean {
        return (dish.dishName == dishName)
    }
}