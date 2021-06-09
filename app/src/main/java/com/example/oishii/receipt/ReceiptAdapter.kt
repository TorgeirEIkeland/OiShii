package com.example.oishii.receipt

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.oishii.R
import com.example.oishii.database.DishObject
import com.example.oishii.database.ReceiptObject

class ReceiptAdapter(var dataSet: List<ReceiptObject>, var context: Context):
    RecyclerView.Adapter<ReceiptAdapter.ViewHolder>() {

    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var dishType: TextView
        var linearLayout: LinearLayout

        init {
            dishType = view.findViewById(R.id.side_textView)
            linearLayout = view.findViewById(R.id.oishii_LL)
        }
    }


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.oishii_card, viewGroup, false)
        return ReceiptAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int){
        viewHolder.linearLayout.removeAllViews()

        //Loops trough list of dishes to make new views in the cards
        for (dish in dataSet[position].dishList) {
            val newDishView = ReceiptView(context)
            newDishView.setView(dish)

            viewHolder.linearLayout.addView(newDishView)
            newDishView.requestLayout()
        }
        viewHolder.linearLayout.requestLayout()
        viewHolder.view.requestLayout()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    fun updateAdapter(newData: List<ReceiptObject>) {
        dataSet = newData
        notifyDataSetChanged()
    }
}