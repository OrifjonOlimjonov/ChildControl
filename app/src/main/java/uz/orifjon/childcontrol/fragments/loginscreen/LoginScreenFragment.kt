package uz.orifjon.childcontrol.fragments.loginscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import uz.orifjon.childcontrol.R
import uz.orifjon.childcontrol.databinding.FragmentLoginScreenBinding
import uz.orifjon.childcontrol.models.User
import uz.orifjon.childcontrol.models.UserDatabase
import java.util.HashMap


class LoginScreenFragment : Fragment() {

    private var _binding: FragmentLoginScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginScreenBinding.inflate(inflater, container, false)
        val user = UserDatabase.getDatabase(requireContext()).userDao().getUser(0) ?: 0

        if (user != 0) {
            val bundle = Bundle()
            bundle.putSerializable("user", user)
            findNavController().navigate(R.id.mainFragment, bundle)
        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.btnLogin.setOnClickListener {
//            val userPhoneNumber = binding.editTextPhoneNumber.text.toString()
//            val userPassword = binding.editTextPassword.text.toString()
//
//
//            if (binding.editTextPhoneNumber.text.toString().isNotEmpty()) {
//                    if (user.password == userPassword) {
//                        val bundle = Bundle()
////                        bundle.putSerializable("key", user)
//                        val hashMap = HashMap<String, String>()
//                        hashMap["status"] = "online"
////                        reference.child(user.uid).updateChildren(hashMap as Map<String, String>)
////                        UserDatabase.getDatabase(requireContext()).userDao().addUser(user)
////                        findNavController().navigate(com.google.firebase.database.R.id.mainFragment, bundle)
//                    } else {
//                        Toast.makeText(
//                            requireContext(),
//                            "Parolingizni qayta tekshiring!!",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                } else {
//                    Toast.makeText(
//                        requireContext(),
//                        "Bu raqam ro'yxatdan o'tmagan!!",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }

        binding.tvRegister.setOnClickListener {
            findNavController().navigate(R.id.registrationScreenFragment)
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }


}