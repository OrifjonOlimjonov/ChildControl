package uz.orifjon.childcontrol.fragments.verificationscreen

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import uz.orifjon.childcontrol.R
import uz.orifjon.childcontrol.databinding.FragmentVerificationSMSBinding
import java.util.concurrent.TimeUnit

class VerificationSMSFragment : Fragment() {

    private lateinit var binding: FragmentVerificationSMSBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var number: String
    private var storedVerificationId: String? = null
    private var resendToken: PhoneAuthProvider.ForceResendingToken? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVerificationSMSBinding.inflate(inflater, container, false)

        number = arguments?.getString("number")!!
        auth = FirebaseAuth.getInstance()
        timerOne()
        binding.tv1.text = "Bir martalik kod  $number\nraqamiga yuborildi"
        startPhoneNumberVerification(number)
        binding.editText.addTextChangedListener {
            val verification = it.toString()
            if (verification.length == 6) {
                val credential =
                    PhoneAuthProvider.getCredential(storedVerificationId ?: "", verification)
                signInWithPhoneAuthCredential(credential)
            }
        }
        binding.btnResend.setOnClickListener {
            reSendPhoneNumberVerification(number)
            timerOne()
            binding.editText.setText("")
            binding.btnResend.visibility = View.INVISIBLE
        }

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finishAffinity()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(requireActivity(), callback)
        return binding.root
    }

    fun timerOne() {
        countDownTimer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.tv3.text = "00 : " + String.format("%02d", millisUntilFinished / 1000)
            }

            override fun onFinish() {
                binding.tv3.text = "00:00"
                binding.btnResend.visibility = View.VISIBLE
            }
        }.start()
    }


    private fun startPhoneNumberVerification(phoneNumber: String) {

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireActivity()) // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)

    }

    private fun reSendPhoneNumberVerification(phoneNumber: String) {

        val options = resendToken?.let {
            PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(requireActivity())
                .setCallbacks(callbacks)
                .setForceResendingToken(it)
                .build()
        }

        if (options != null) {
            PhoneAuthProvider.verifyPhoneNumber(options)
        }
    }

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            Log.d("TAG", "onVerificationCompleted:$credential")
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            if (e is FirebaseAuthInvalidCredentialsException) {
            } else if (e is FirebaseTooManyRequestsException) {
            }
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            storedVerificationId = verificationId
            resendToken = token
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    val user = task.result?.user
                    val bundle = Bundle()
                    bundle.putString("number", number)
                    findNavController().navigate(R.id.mainFragment, bundle)
                } else {
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(
                            requireContext(),
                            task.exception.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
    }

}
