package com.example.oishii.checkout

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.oishii.database.DishObject
import com.example.oishii.R

class CheckoutView(context: Context) : ConstraintLayout(context) {

    var checkoutDishTextView: TextView
    var priceTextView: TextView
    var plusButton: TextView
    var minusButton: TextView
    var amountTextView: TextView
    lateinit var currentItem: DishObject

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.checkout_view, this)

        checkoutDishTextView = view.findViewById(R.id.checkout_dish_textView)
        priceTextView = view.findViewById(R.id.checkout_price_textView)
        amountTextView = view.findViewById(R.id.amountTextView)
        minusButton = view.findViewById(R.id.minusButton)
        plusButton = view.findViewById(R.id.plusButton)
    }

    fun setText(item: DishObject, callback: (DishObject, String) -> Unit) {
        currentItem = item
        checkoutDishTextView.text = currentItem.dishName

        updateTextViews()
        clickListners(callback)
    }

    fun updateTextViews() {
        amountTextView.text = currentItem.amount.toString()

        val price = currentItem.price * currentItem.amount
        priceTextView.text = price.toString()
    }

    fun clickListners(callback: (DishObject, String) -> Unit) {
        var itemAmount = currentItem.amount

        minusButton.setOnClickListener {
            itemAmount -= 1
            if (itemAmount <= 0) {
                callback(currentItem, "remove")
            } else {
                updateTextViews()
                callback(currentItem, "update")
            }
        }

        plusButton.setOnClickListener {
            itemAmount += 1
            updateTextViews()
            callback(currentItem, "update")
        }
    }



























}