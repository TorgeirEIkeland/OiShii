package com.example.oishii.checkout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.oishii.MainActivity
import com.example.oishii.database.DishObject
import com.example.oishii.R
import com.example.oishii.database.AppDatabase
import java.util.concurrent.Executor

class CheckoutFragment : Fragment() {

    lateinit var checkoutLL: LinearLayout
    lateinit var paybutton: TextView
    lateinit var checkoutViewModel: CheckoutViewModel
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        biometricStuff()
        checkoutButton()

    }

    fun fillCheckout() {
        var oishiiDB = AppDatabase.getDatabase(requireContext()).OishiiDAO()
        var itemList = mutableListOf<DishObject>()


        checkoutViewModel.getAllItems(oishiiDB) {


            activity?.runOnUiThread {
                checkoutLL.removeAllViews()

                for (item in it) {
                    val itemView = CheckoutView(requireContext())
                    itemView.setText(item)

                    checkoutLL.addView(itemView)
                }
            }

        }
    }

        fun checkoutButton(){
            paybutton.setOnClickListener {
                biometricPrompt.authenticate(promptInfo)
            }
        }

        fun biometricStuff(){
            executor = ContextCompat.getMainExecutor(context)
            biometricPrompt = BiometricPrompt(this, executor,
                object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationError(errorCode: Int,
                                                       errString: CharSequence) {
                        super.onAuthenticationError(errorCode, errString)
                        Toast.makeText(context,
                            "Canceled", Toast.LENGTH_SHORT)
                            .show()
                    }

                    override fun onAuthenticationSucceeded(
                        result: BiometricPrompt.AuthenticationResult) {
                        super.onAuthenticationSucceeded(result)
                        Toast.makeText(context,
                            "Authentication succeeded!", Toast.LENGTH_SHORT)
                            .show()

                        (activity as MainActivity).notificationManager.sendNotification("paid", "du har betalt")
                    }

                    override fun onAuthenticationFailed() {
                        super.onAuthenticationFailed()
                        Toast.makeText(context, "Authentication failed",
                            Toast.LENGTH_SHORT)
                            .show()
                    }
                })

            promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric login for my app")
                .setSubtitle("Log in using your biometric credential")
                .setNegativeButtonText("Cancel")
                .build()
        }
}