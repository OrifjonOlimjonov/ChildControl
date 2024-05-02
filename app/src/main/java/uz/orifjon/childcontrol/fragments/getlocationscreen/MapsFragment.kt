package uz.orifjon.childcontrol.fragments.getlocationscreen

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import uz.orifjon.childcontrol.R
import uz.orifjon.childcontrol.databinding.FragmentMapsBinding
import uz.orifjon.childcontrol.models.ChildLocation
import uz.orifjon.childcontrol.models.ChildrenForFirebase
import uz.orifjon.childcontrol.models.UserForFirebase

class MapsFragment : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->
        reference.child(userForFirebase.uid).child("childList").child(position.toString())
            .child("locationNow").addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val childLocation =
                        snapshot.getValue(ChildLocation::class.java) ?: ChildLocation(0.0, 0.0)
                    val child = LatLng(childLocation.lang, childLocation.long)
                    googleMap.addMarker(MarkerOptions().position(child).title("Child"))
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(child,16.0F))
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })


    }
    private var _binding: FragmentMapsBinding? = null
    private val binding get() = _binding!!


    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    private lateinit var userForFirebase: UserForFirebase
    private lateinit var childrenForFirebase: ChildrenForFirebase
    private var position = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapsBinding.inflate(inflater, container, false)
        initialSetting()

        userForFirebase = arguments?.getSerializable("user") as UserForFirebase
        childrenForFirebase = arguments?.getSerializable("child") as ChildrenForFirebase
        position = arguments?.getInt("position") ?: 0

        return binding.root
    }

    private fun initialSetting() {
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference = firebaseDatabase.getReference("users")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)


    }
}