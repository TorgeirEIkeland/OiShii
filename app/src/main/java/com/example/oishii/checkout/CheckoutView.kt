package com.example.oishii.checkout

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.oishii.database.DishObject
import com.example.oishii.R
import com.example.oishii.database.AppDatabase

class CheckoutView(context: Context) : ConstraintLayout(context) {

    var checkoutDishTextView: TextView
    var priceTextView: TextView
    var plusButton: TextView
    var minusButton: TextView
    var amountTextView: TextView
    lateinit var thisItem: DishObject

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.checkout_view, this)

        checkoutDishTextView = view.findViewById(R.id.checkout_dish_textView)
        priceTextView = view.findViewById(R.id.checkout_price_textView)
        amountTextView = view.findViewById(R.id.amountTextView)
        minusButton = view.findViewById(R.id.minusButton)
        plusButton = view.findViewById(R.id.plusButton)
    }

    fun setText(item: DishObject, callback: (DishObject, String) -> Unit) {
        thisItem = item
        checkoutDishTextView.text = thisItem.dishName

        updateTextViews()
        buttons(callback)
    }

    fun buttons(callback: (DishObject, String) -> Unit) {
        minusButton.setOnClickListener {
            thisItem.amount = thisItem.amount - 1

            if (thisItem.amount <= 0) {
                callback(thisItem, "remove")
            } else {
                updateTextViews()
                callback(thisItem, "update")
            }

        }

        plusButton.setOnClickListener {
            thisItem.amount = thisItem.amount + 1

            updateTextViews()
            callback(thisItem, "update")
        }
    }

    fun updateTextViews() {
        amountTextView.text = thisItem.amount.toString()

        val price = thisItem.price * thisItem.amount
        priceTextView.text = price.toString()

    }

























}