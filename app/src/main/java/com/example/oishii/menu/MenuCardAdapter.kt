package com.example.oishii.menu

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.oishii.database.DishObject
import com.example.oishii.R


class MenuCardAdapter(var dataSet: List<MenuCardObject>, var context: Context, var callback: (DishObject) -> Unit) :
    RecyclerView.Adapter<MenuCardAdapter.ViewHolder>() {

    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var dishType: TextView
        var linearLayout: LinearLayout

        init {
            // Define click listener for the ViewHolder's View.

            dishType = view.findViewById(R.id.dishName_textView)
            linearLayout = view.findViewById(R.id.menu_LL)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.menu_card, viewGroup, false)



        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {


        viewHolder.dishType.text = dataSet[position].dishType


        viewHolder.linearLayout.removeAllViews()

        //Loops trough list of dishes to make new views in the cards
        for (dish in dataSet[position].dishList) {
            val newDishView = DishView(context)
            newDishView.setText(dish)
            newDishView.addToCartButton(dish, callback)

            viewHolder.linearLayout.addView(newDishView)

            newDishView.requestLayout()
        }


        viewHolder.linearLayout.requestLayout()
        viewHolder.view.requestLayout()


    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
}