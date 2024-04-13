package uz.orifjon.childcontrol.fragments.loginscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import uz.orifjon.childcontrol.R
import uz.orifjon.childcontrol.databinding.FragmentLoginScreenBinding
import uz.orifjon.childcontrol.models.User
import uz.orifjon.childcontrol.models.UserDatabase
import java.util.HashMap


class LoginScreenFragment : Fragment() {

    private var _binding: FragmentLoginScreenBinding? = null
    private lateinit var auth: FirebaseAuth

    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private val binding get() = _binding!!
    private lateinit var user: User
    private var k = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginScreenBinding.inflate(inflater, container, false)
        val userLocal = UserDatabase.getDatabase(requireContext()).userDao().getUser() ?: 0
        initialSetting()
        if (userLocal != 0) {
            val bundle = Bundle()
            bundle.putSerializable("user", userLocal)
            findNavController().navigate(R.id.mainFragment, bundle)
        }
        return binding.root
    }

    private fun initialSetting() {
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference = firebaseDatabase.getReference("users")
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        onRegister()

        onLogin()

        onCheckExistUser()
    }

    private fun onCheckExistUser() {
        binding.editTextPhoneNumber.addTextChangedListener { it ->
            val text = it.toString()
            if (text.length == 13) {
                reference.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val children = snapshot.children
                        children.forEach {
                            val value = it.getValue(User::class.java)
                            if (value != null && value.phoneNumber == binding.editTextPhoneNumber.text.toString()
                                    .trim() && value.password == binding.editTextPassword.text.toString()
                                    .trim()
                            ) {
                                user = value
                                k = true
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {}
                })
            }
        }
    }

    private fun onLogin() {
        binding.btnLogin.setOnClickListener {
            if (k) {
                val bundle = Bundle()
                bundle.putSerializable("user", user)
                findNavController().navigate(R.id.mainFragment, bundle)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Bunday user ro'yxatdan o'tmagan!!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun onRegister() {
        binding.tvRegister.setOnClickListener {
            findNavController().navigate(R.id.registrationScreenFragment)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }


}