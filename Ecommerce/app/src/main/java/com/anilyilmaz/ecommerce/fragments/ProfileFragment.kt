package com.anilyilmaz.ecommerce.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.anilyilmaz.ecommerce.databinding.FragmentProfileBinding
import com.anilyilmaz.ecommerce.viewmodels.ProfileFragmentViewModel
import com.anilyilmaz.ecommerce.viewmodels.RegisterFragmentViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private lateinit var auth : FirebaseAuth
    private lateinit var db : FirebaseFirestore
    private val viewmodel: ProfileFragmentViewModel by viewModels()
    private val vm: RegisterFragmentViewModel by viewModels()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        viewmodel.profileFunction(db, auth, binding.card)
        val username = auth.currentUser?.email.toString()

        binding.email.text = auth.currentUser?.email.toString()
        binding.username.text = username.split("@")[0]
        binding.phone.text = "+90 ${vm.phone}"    ////PHONE!!!
        binding.textView.text = username.split("@")[0]
        binding.textView2.text = auth.currentUser?.email.toString()

        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}