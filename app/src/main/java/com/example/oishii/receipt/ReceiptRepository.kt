package com.example.oishii.receipt

import com.example.oishii.OishiiApplication
import com.example.oishii.database.AppDatabase
import com.example.oishii.database.ReceiptObject

class ReceiptRepository {

    private val receiptDAO =
        AppDatabase.getDatabase(OishiiApplication.application.applicationContext).ReceiptDAO()

    fun getReceiptsFromDAO(): List<ReceiptObject> {
        return receiptDAO.getReceipts()
    }
}