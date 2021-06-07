package com.example.oishii.checkout

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.oishii.database.DishObject
import com.example.oishii.R

class CheckoutView(context: Context): ConstraintLayout(context) {

    var checkoutDishTextView: TextView
    var priceTextView: TextView

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.checkout_view, this)

        checkoutDishTextView = view.findViewById(R.id.checkout_dish_textView)
        priceTextView = view.findViewById(R.id.checkout_price_textView)
    }

    fun setText(item: DishObject){
        checkoutDishTextView.text = item.dishName
        priceTextView.text = item.price.toString()
    }


}