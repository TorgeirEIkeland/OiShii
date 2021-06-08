package com.example.oishii.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oishii.*
import com.example.oishii.database.AppDatabase
import com.example.oishii.database.DishObject

class MenuFragment : Fragment() {

    var customAdapter: MenuCardAdapter? = null
    lateinit var recyclerView: RecyclerView
    lateinit var checkoutButton: ImageView
    lateinit var menuViewModel: MenuViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu, container, false)
        menuViewModel = ViewModelProvider(this).get(MenuViewModel::class.java)

        recyclerView = view.findViewById(R.id.recyclerView)
        checkoutButton = view.findViewById(R.id.checkout_button)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

        setOnClickListners()

    }

    fun setOnClickListners() {

        val options = navOptions {
            anim {
                enter = R.anim.fragment_fade_enter
                exit = R.anim.fragment_fade_exit
                popEnter = R.anim.fragment_fade_enter
                popExit = R.anim.fragment_fade_exit
            }
        }

        checkoutButton.setOnClickListener {
            findNavController().navigate(R.id.checkoutFragment, null, options)
        }


    }

    private fun initRecyclerView() {
        customAdapter = MenuCardAdapter(
            getMenuList(), requireContext()
        ) { dish ->
            var oishiiDB = AppDatabase.getDatabase(requireContext()).OishiiDAO()
            menuViewModel.getCart(oishiiDB){ list ->

                var check = list.none{item ->
                    item.isTheSame(dish)
                }

                if(check){
                    menuViewModel.insertItem(oishiiDB, dish)
                }
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = customAdapter
    }

    fun getMenuList(): List<MenuCardObject> {
        return listOf(
            MenuCardObject(
                "RAMEN",
                listOf(
                    DishObject(
                        "vegan ramen",
                        "Fresh ramen noodles, tofu, scallionsm shitake mushrooms, leeks, bamboo shots, pak choi in spicy dobanjiang soup.",
                        "allergens: hvete, soya, sesam nøtter",
                        70,
                        1
                    ), DishObject(
                        "spicy vegan ramen",
                        "Fresh ramen noodles, tofu, scallions, shitake mushrooms, leeks, bamboo shoots, pak choi in spicy dobanjian soup.",
                        "allergens: hvete, soya, sesam nøtter",
                        70,
                        1
                    )
                )
            ), MenuCardObject(
                "WOK",
                listOf(
                    DishObject(
                        "vegan pad thai prik",
                        "Rice noodle sticks with tofu, bean sprouts and Chinese chives in chilli sauce. Topped with cashew and lime.",
                        "allergens: nøtter, soya",
                        70,
                        1
                    ), DishObject(
                        "vegan teriyaki noodles",
                        "Wheat noodles woked with tofu, pak choi, bell peppers, red onions and broccoli in teriyaki",
                        "allergens: hvete soya",
                        70,
                        1
                    )
                )
            ), MenuCardObject(
                "DRINKS",
                listOf(
                    DishObject(
                        "sakura ramune",
                        null,
                        null,
                        30,
                        1
                    ), DishObject(
                        "coffe",
                        null,
                        null,
                        20,
                        1
                    ), DishObject(
                        "soda",
                        "Coca-cola \n Coca-cola zero \n sparkling water",
                        null,
                        35,
                        1
                    )
                )
            )
        )

    }
}