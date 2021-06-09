package com.example.oishii.checkout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.biometric.BiometricManager.Authenticators.*
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.oishii.MainActivity
import com.example.oishii.R
import com.example.oishii.database.*
import java.util.concurrent.Executor

class CheckoutFragment : Fragment() {

    //Views
    lateinit var checkoutLL: LinearLayout
    lateinit var paybutton: TextView
    lateinit var checkoutViewModel: CheckoutViewModel


    //biometric stuff
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    lateinit var oishiiDB: OishiiDAO
    lateinit var cartList: List<DishObject>
    lateinit var receiptDB: ReceiptDAO

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
        oishiiDB = AppDatabase.getDatabase(requireContext()).OishiiDAO()
        receiptDB = AppDatabase.getDatabase(requireContext()).ReceiptDAO()

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViews()
        checkoutButton()
        fingerprintSettup()
    }

    //Creating x amount of views == to database list
    //Updating items in database if amount has changed
    //if amount is set to 0 or less it gets removed from database
    fun setViews() {
        checkoutViewModel.getAllItems(oishiiDB) { dishList ->
            activity?.runOnUiThread {
                cartList = dishList

                checkoutLL.removeAllViews()

                for (item in cartList) {
                    val itemView = CheckoutView(requireContext())
                    itemView.setText(item) { item, task ->
                        updateDB(item, task, itemView)
                    }
                    checkoutLL.addView(itemView)
                }
            }
        }
    }

    fun updateDB(item: DishObject, task: String, itemView: CheckoutView) {
        if (task == "remove") {
            checkoutViewModel.removeItem(oishiiDB, item)
            checkoutLL.removeView(itemView)
        } else {
            checkoutViewModel.updateItem(oishiiDB, item)
        }
    }

    //runs the biometric scan to check if user authentication passes and "buys" the items if passed
    fun checkoutButton() {
        paybutton.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }
    }

    fun fingerprintSettup() {
        executor = ContextCompat.getMainExecutor(context)
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)

                    Toast.makeText(
                        context,
                        "Canceled", Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    checkoutViewModel.addToReceipts(receiptDB, ReceiptObject(cartList))
                    checkoutViewModel.resetDatabase(oishiiDB)
                    checkoutLL.removeAllViews()

                    Toast.makeText(
                        context,
                        "Authentication succeeded!", Toast.LENGTH_SHORT
                    ).show()

                    (activity as MainActivity).notificationManager.sendNotification(
                        "paid",
                        "du har betalt"
                    )
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()

                    Toast.makeText(
                        context, "Authentication failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        )
        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login for my app")
            .setSubtitle("Log in using your biometric credential")
            .setAllowedAuthenticators(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)
            .build()

    }
}