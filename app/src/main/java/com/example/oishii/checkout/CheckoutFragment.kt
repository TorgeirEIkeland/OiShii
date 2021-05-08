package com.example.oishii.checkout

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.oishii.DishObject
import com.example.oishii.R
import com.example.oishii.database.AppDatabase
import com.example.oishii.menu.DishView
import com.example.oishii.menu.MenuViewModel

class CheckoutFragment : Fragment() {

    lateinit var checkoutLL: LinearLayout
    lateinit var paybutton: TextView
    lateinit var checkoutViewModel: CheckoutViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_checkout, container, false)
        // Inflate the layout for this fragment

        checkoutViewModel = ViewModelProvider(this).get(CheckoutViewModel::class.java)


        checkoutLL = view.findViewById(R.id.checkout_LL)
        paybutton = view.findViewById(R.id.pay_button)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fillCheckout()
    }

    fun fillCheckout(){
        var oishiiDB = AppDatabase.getDatabase(requireContext()).OishiiDAO()
        var itemList=  mutableListOf<DishObject>()
        checkoutViewModel.getAllItems(oishiiDB){

            tryThis(it)

        }

        //Loops trough list of dishes to make new views in the cards

    }

    fun tryThis(it: List<DishObject>){
        checkoutLL.removeAllViews()

        for (item in it) {
            val itemView = CheckoutView(requireContext())
            itemView.setText(item)

            checkoutLL.addView(itemView)

            itemView.requestLayout()

        }
    }

}