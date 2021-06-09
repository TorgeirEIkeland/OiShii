package com.example.oishii.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "receipts")
class ReceiptObject(
    @PrimaryKey(autoGenerate = true)
    val dishList: List<DishObject>
) {
}