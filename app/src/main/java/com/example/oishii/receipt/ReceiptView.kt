package com.example.oishii.receipt

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.oishii.R
import com.example.oishii.database.DishObject

class ReceiptView(context: Context) : ConstraintLayout(context) {
    private var dishTitle: TextView
    private var priceTextView: TextView
    private var amountTextView: TextView


    init {
        val view = LayoutInflater.from(context).inflate(R.layout.receipt_view, this)

        dishTitle = view.findViewById(R.id.dishTitle_textView)
        amountTextView = view.findViewById(R.id.amount_textView)
        priceTextView = view.findViewById(R.id.price_textView)
    }

    fun setView(dish: DishObject){
        dishTitle.text = dish.dishName
        amountTextView.text = dish.amount.toString() + "stk"
        var price = dish.amount*dish.amount
        priceTextView.text = price.toString() + "kr"
    }
}