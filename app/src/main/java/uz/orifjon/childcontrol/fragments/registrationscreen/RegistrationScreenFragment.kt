package uz.orifjon.childcontrol.fragments.registrationscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.orifjon.childcontrol.R
import uz.orifjon.childcontrol.databinding.FragmentRegistrationScreenBinding
import uz.orifjon.childcontrol.models.local.User


class RegistrationScreenFragment : Fragment() {

    private var _binding: FragmentRegistrationScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnRegister.setOnClickListener {
            if (binding.editTextName.text.toString().isNotEmpty()) {
                if (binding.editTextPhoneNumber.text.toString().isNotEmpty()) {
                    if (binding.editTextPassword.text.toString().isNotEmpty()) {
                        val bundle = Bundle()
                        bundle.putSerializable("user", getUser())
                        findNavController().navigate(R.id.verificationSMSFragment, bundle)
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Parolingizni kiriting!!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Telefon raqamingizni kiriting!!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(requireContext(), "Ismingizni kiriting!!", Toast.LENGTH_SHORT).show()
            }

        }

    }


    fun getUser(): User {
        val userName = binding.editTextName.text.toString()
        val phoneNumber = binding.editTextPhoneNumber.text.toString()
        val password = binding.editTextPassword.text.toString()

        return User("", userName, phoneNumber, password)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}