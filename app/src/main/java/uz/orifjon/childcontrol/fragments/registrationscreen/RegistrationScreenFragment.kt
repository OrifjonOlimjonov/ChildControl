package uz.orifjon.childcontrol.fragments.registrationscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.orifjon.childcontrol.R
import uz.orifjon.childcontrol.databinding.FragmentRegistrationScreenBinding
import uz.orifjon.childcontrol.models.User


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
            val bundle = Bundle()
            bundle.putSerializable("number", getUser().phoneNumber)
            findNavController().navigate(R.id.verificationSMSFragment, bundle)
        }

    }


    fun getUser(): User {
        val userName = binding.editTextName.text.toString()
        val phoneNumber = binding.editTextPhoneNumber.text.toString()
        val password = binding.editTextPassword.text.toString()

        return User(0, userName, phoneNumber, password)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}