package com.example.oishii.receipt

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oishii.database.DishObject
import com.example.oishii.database.OishiiDAO
import com.example.oishii.database.ReceiptDAO
import com.example.oishii.database.ReceiptObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReceiptViewModel : ViewModel() {

    private val receiptRepository = ReceiptRepository()

    val receiptsLiveData = MutableLiveData<List<ReceiptObject>>()

    fun getReceipts(callBack: (List<ReceiptObject>) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val receipts = receiptRepository.getReceiptsFromDAO()
            callBack(receipts)
        }
    }
}