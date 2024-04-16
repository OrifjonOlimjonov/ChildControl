package uz.orifjon.childcontrol.fragments.mainscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import uz.orifjon.childcontrol.R
import uz.orifjon.childcontrol.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        requireActivity().onBackPressedDispatcher.addCallback(requireActivity(), callback)

        return binding.root
    }


    val callback: OnBackPressedCallback =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finishAffinity()
            }
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addChild()

        clickProfile()

    }

    private fun clickProfile() {
        binding.btnProfile.setOnClickListener {

        }
    }

    private fun addChild() {
         binding.btnAddChild.setOnClickListener {

         }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}