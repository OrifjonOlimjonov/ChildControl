package uz.orifjon.childcontrol.fragments.loginscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import uz.orifjon.childcontrol.R
import uz.orifjon.childcontrol.databinding.FragmentLoginScreenBinding
import uz.orifjon.childcontrol.models.UserForFirebase
import uz.orifjon.childcontrol.models.local.User
import uz.orifjon.childcontrol.models.database.UserDatabase


class LoginScreenFragment : Fragment() {

    private var _binding: FragmentLoginScreenBinding? = null
    private lateinit var auth: FirebaseAuth

    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private val binding get() = _binding!!
    private lateinit var user: UserForFirebase
    private var k = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginScreenBinding.inflate(inflater, container, false)
        val userLocal = UserDatabase.getDatabase(requireContext()).userDao().getUser()
        initialSetting()
        if (userLocal != null) {
            val bundle = Bundle()
            bundle.putSerializable(
                "user", UserForFirebase(
                    userLocal.uid,
                    userLocal.name,
                    userLocal.phoneNumber,
                    userLocal.password,
                    userLocal.userToken
                )
            )
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
    }

    private fun onCheckExistUser() {
        val phoneNumber = binding.editTextPhoneNumber.text.toString()
        val password = binding.editTextPassword.text.toString()
        if (phoneNumber.isNotEmpty()) {
            if (phoneNumber.length == 13) {
                if (password.isNotEmpty()) {
                    reference.addValueEventListener(object : ValueEventListener {

                        override fun onDataChange(snapshot: DataSnapshot) {
                            val children = snapshot.children
                            children.forEach {
                                val value = it.getValue(UserForFirebase::class.java)
                                if (value != null && value.phoneNumber == binding.editTextPhoneNumber.text.toString()
                                        .trim() && value.password == binding.editTextPassword.text.toString()
                                        .trim()
                                ) {
                                    user = value
                                    val bundle = Bundle()
                                    bundle.putSerializable("user", user)
                                    UserDatabase.getDatabase(requireContext()).userDao().insertUser(
                                        User(
                                            user.uid,
                                            user.name,
                                            user.phoneNumber,
                                            user.password,
                                            user.userToken
                                        )
                                    )
                                    findNavController().navigate(R.id.mainFragment, bundle)
                                    k = true
                                }
                            }
                            binding.btnLogin.isEnabled = true
                            binding.progressBar.visibility = View.INVISIBLE
                            if (!k) Toast.makeText(
                                requireContext(),
                                "Bunday user ro'yxatdan o'tmagan!!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        override fun onCancelled(error: DatabaseError) {

                        }
                    })
                } else {
                    Toast.makeText(requireContext(), "Parolingizni kiriting!!", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(
                    requireContext(),
                    "raqamingiz kodi +998 dan boshlanishi kerak!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            Toast.makeText(requireContext(), "Telefon raqamingizni kiriting!!", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun onLogin() {
        binding.btnLogin.setOnClickListener {
            binding.btnLogin.isEnabled = false
            binding.progressBar.visibility = View.VISIBLE
            onCheckExistUser()
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