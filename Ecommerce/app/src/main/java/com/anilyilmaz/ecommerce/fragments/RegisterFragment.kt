package com.anilyilmaz.ecommerce.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.anilyilmaz.ecommerce.databinding.FragmentRegisterBinding
import com.anilyilmaz.ecommerce.viewmodels.RegisterFragmentViewModel
import com.google.firebase.auth.FirebaseAuth

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private lateinit var firebaseAuth : FirebaseAuth
    private val viewmodel: RegisterFragmentViewModel by viewModels()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.loginButton.setOnClickListener(View.OnClickListener {
            viewmodel.phone = binding.phone.text.toString()
            activity?.let { it1 ->
                viewmodel.registerFunction(firebaseAuth,binding.registerEmail.text.toString(),binding.registerPassword.text.toString(),
                    it1
                )
            }
        })

        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

