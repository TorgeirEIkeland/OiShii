package com.example.oishii.database

import android.content.SearchRecentSuggestionsProvider
import androidx.room.*

@Dao
interface ReceiptDAO {
    @Query("SELECT * FROM receipts" )
    fun getReceipts() : List<ReceiptObject>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertReceipt(receipt: ReceiptObject)
}

