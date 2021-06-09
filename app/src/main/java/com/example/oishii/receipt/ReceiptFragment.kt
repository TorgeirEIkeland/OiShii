package com.example.oishii.receipt

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oishii.R
import com.example.oishii.database.AppDatabase
import com.example.oishii.database.ReceiptDAO
import kotlin.concurrent.thread

class ReceiptFragment : Fragment() {

    companion object {
        fun newInstance() = ReceiptFragment()
    }

    var customAdapter: ReceiptAdapter? = null
    lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: ReceiptViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.receipt_fragment, container, false)
        viewModel = ViewModelProvider(this).get(ReceiptViewModel::class.java)
        recyclerView = view.findViewById(R.id.recyclerView)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        initRecyclerView()
        viewModel.getReceipts(){ receipts ->
            customAdapter?.updateAdapter(receipts)
        }
    }

    private fun initRecyclerView() {
        customAdapter = ReceiptAdapter(
            listOf(), requireContext()
        )
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = customAdapter
    }

}