package com.example.oishii.checkout

import androidx.lifecycle.ViewModel
import com.example.oishii.database.DishObject
import com.example.oishii.database.OishiiDAO
import com.example.oishii.database.ReceiptDAO
import com.example.oishii.database.ReceiptObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CheckoutViewModel: ViewModel() {

    fun getAllItems(dao: OishiiDAO, callback: (List<DishObject>) -> Unit){
        CoroutineScope(Dispatchers.IO).launch {
            val cartList = dao.getCartFromDB()
            callback(cartList)
        }
    }

    fun updateItem(dao: OishiiDAO, item: DishObject){
        CoroutineScope(Dispatchers.IO).launch {
            dao.updateItemInDB(item)
        }
    }

    fun removeItem(dao: OishiiDAO, item: DishObject){
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteItemFromDB(item)
        }
    }

    fun resetDatabase(dao: OishiiDAO){
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteAll()
        }
    }

    fun addToReceipts(dao: ReceiptDAO, receipt: ReceiptObject){
        CoroutineScope(Dispatchers.IO).launch {
            dao.insertReceipt(receipt)
        }
    }
}