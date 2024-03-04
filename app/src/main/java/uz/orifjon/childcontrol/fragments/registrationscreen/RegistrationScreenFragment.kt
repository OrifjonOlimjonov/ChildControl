package uz.orifjon.childcontrol.fragments.registrationscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.orifjon.childcontrol.R
import uz.orifjon.childcontrol.databinding.FragmentRegistrationScreenBinding


class RegistrationScreenFragment : Fragment() {

    private var _binding:FragmentRegistrationScreenBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationScreenBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}