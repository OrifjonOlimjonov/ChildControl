package uz.orifjon.childcontrol.fragments.mainscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import uz.orifjon.childcontrol.R
import uz.orifjon.childcontrol.databinding.FragmentMainBinding
import uz.orifjon.childcontrol.models.UserForFirebase

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var userForFirebase: UserForFirebase
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        requireActivity().onBackPressedDispatcher.addCallback(requireActivity(), callback)
        userForFirebase = arguments?.getSerializable("user") as UserForFirebase
        initialSetting()
        return binding.root
    }

    private fun initialSetting() {
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference = firebaseDatabase.getReference("users")
    }

    val callback: OnBackPressedCallback =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finishAffinity()
            }
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()

        addChild()

        clickProfile()

    }

    private fun getData() {
        reference.child(userForFirebase.uid).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(UserForFirebase::class.java)

                userForFirebase = value?:userForFirebase

            }

            override fun onCancelled(error: DatabaseError) {
                println("Failed to read value: ${error.toException()}")
            }
        })
    }

    private fun clickProfile() {
        binding.btnProfile.setOnClickListener {

        }
    }

    private fun addChild() {
        binding.btnAddChild.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("user", userForFirebase)
            findNavController().navigate(R.id.addingChildFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}