package com.example.oishii.menu

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.example.oishii.*
import com.example.oishii.database.OishiiDAO

class DishView(context: Context) : ConstraintLayout(context) {

    private var dishTitle: TextView
    private var descrition: TextView
    private var alergies: TextView
    private var price: TextView
    private var textButton: TextView


    init {
        val view = LayoutInflater.from(context).inflate(R.layout.dish_view, this)

        dishTitle = view.findViewById(R.id.dish_name_textView)
        descrition = view.findViewById(R.id.description_textView)
        alergies = view.findViewById(R.id.alergies_textView)
        price = view.findViewById(R.id.price_textView)
        textButton = view.findViewById(R.id.addToCart)

    }

    fun setText(dish: DishObject){
        dishTitle.text = dish.dishName

        if(dish.description != null) {
            descrition.text = dish.description
        } else descrition.isVisible = false

        if(dish.alergies != null) {
            alergies.text = dish.alergies
        } else alergies.isVisible = false

        price.text = dish.price.toString() + "kr"
    }

    fun addToCartButton(dish: DishObject, callback: (DishObject) -> Unit){
        textButton.setOnClickListener{
            callback(dish)
        }
    }
}