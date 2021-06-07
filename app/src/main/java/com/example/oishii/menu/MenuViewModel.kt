package com.example.oishii.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oishii.database.DishObject
import com.example.oishii.database.OishiiDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MenuViewModel : ViewModel() {
    fun getCart(dao: OishiiDAO, callBack: (List<DishObject>) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val list = dao.getCart()
            callBack(list)
        }
    }

    fun insertItem(dao: OishiiDAO, dish: DishObject){
        viewModelScope.launch(Dispatchers.IO){
            dao.insertItem(dish)
        }
    }
}