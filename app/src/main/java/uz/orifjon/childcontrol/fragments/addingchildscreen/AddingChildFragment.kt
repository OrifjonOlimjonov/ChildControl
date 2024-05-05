package uz.orifjon.childcontrol.fragments.addingchildscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import uz.orifjon.childcontrol.databinding.FragmentAddingChildBinding
import uz.orifjon.childcontrol.models.ChildLocation
import uz.orifjon.childcontrol.models.ChildrenForFirebase
import uz.orifjon.childcontrol.models.UserForFirebase

class AddingChildFragment : Fragment() {

    private var _binding: FragmentAddingChildBinding? = null

    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private val binding get() = _binding!!
    private lateinit var userForFirebase: UserForFirebase
    private lateinit var list: List<ChildrenForFirebase>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddingChildBinding.inflate(inflater, container, false)
        userForFirebase = arguments?.getSerializable("user") as UserForFirebase
        Toast.makeText(requireContext(), "$userForFirebase", Toast.LENGTH_SHORT).show()
        list = ArrayList()
        initialSetting()

        requireActivity().onBackPressedDispatcher.addCallback(requireActivity(), callback)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            Toast.makeText(requireContext(), "$userForFirebase", Toast.LENGTH_SHORT).show()
            btnAdd.setOnClickListener {
                val childList = userForFirebase.childList as ArrayList

                val name = binding.childNameEditText.text.toString()
                val age = binding.childUserAgeEditText.text.toString()
                if (name.isEmpty() or age.isEmpty()) {
                    Toast.makeText(requireContext(), "Maydonlarni to'ldiring!", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    childList.add(getChild(name, age))
                    Toast.makeText(requireContext(), "$userForFirebase", Toast.LENGTH_SHORT).show()
                    reference.child(userForFirebase.uid).child("childList/").setValue(childList)
                    findNavController().popBackStack()
                }
            }
        }

    }

    val callback: OnBackPressedCallback =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }


    private fun initialSetting() {
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference = firebaseDatabase.getReference("users")
    }

    fun getChild(name: String, age: String): ChildrenForFirebase {
        val reference = reference.push().key

        return ChildrenForFirebase(
            reference!!,
            name = name,
            age = age,
            ChildLocation(0.0, 0.0),
            arrayListOf()
        )
    }

}