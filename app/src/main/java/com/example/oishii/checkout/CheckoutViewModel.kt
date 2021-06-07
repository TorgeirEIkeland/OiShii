package com.example.oishii.checkout

import androidx.lifecycle.ViewModel
import com.example.oishii.database.DishObject
import com.example.oishii.database.OishiiDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CheckoutViewModel: ViewModel() {

    fun getAllItems(dao: OishiiDAO, callback: (List<DishObject>) -> Unit){
        CoroutineScope(Dispatchers.IO).launch {
            val cartList = dao.getCart()
            callback(cartList)
        }
    }
}