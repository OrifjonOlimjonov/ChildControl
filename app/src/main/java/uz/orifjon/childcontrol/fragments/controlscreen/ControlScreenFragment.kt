package uz.orifjon.childcontrol.fragments.controlscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.orifjon.childcontrol.databinding.FragmentControlScreenBinding
import uz.orifjon.childcontrol.models.ChildrenForFirebase
import uz.orifjon.childcontrol.models.UserForFirebase

class ControlScreenFragment : Fragment() {

    private var _binding: FragmentControlScreenBinding? = null
    private val binding get() = _binding!!

    private lateinit var userForFirebase: UserForFirebase
    private lateinit var childrenForFirebase: ChildrenForFirebase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentControlScreenBinding.inflate(inflater, container, false)
        userForFirebase = arguments?.getSerializable("user") as UserForFirebase
        childrenForFirebase = arguments?.getSerializable("child") as ChildrenForFirebase
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvChildName.text = childrenForFirebase.name

        onClickButtonGetLocation()
        onClickButtonUsedApps()
        onClickButtonDailyTasks()
        onClickButtonScheduleSMS()


        requireActivity().onBackPressedDispatcher.addCallback(requireActivity(), callback)
    }

       val callback: OnBackPressedCallback =
                   object : OnBackPressedCallback(true) {
                       override fun handleOnBackPressed() {
                         findNavController().popBackStack()
                       }
                   }

    private fun onClickButtonGetLocation() {
        binding.btnGetLocation.setOnClickListener {

        }
    }
    private fun onClickButtonUsedApps() {
        binding.btnUsedApps.setOnClickListener {

        }
    }

    private fun onClickButtonDailyTasks() {
        binding.btnDailyTasks.setOnClickListener {

        }
    }
    private fun onClickButtonScheduleSMS() {
        binding.btnScheduleSMS.setOnClickListener {

        }
    }







}