package com.anilyilmaz.ecommerce.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.anilyilmaz.ecommerce.databinding.FragmentLoginBinding
import com.anilyilmaz.ecommerce.viewmodels.LoginFragmentViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private lateinit var firebaseAuth : FirebaseAuth
    private val viewmodel: LoginFragmentViewModel by viewModels()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.loginButton.setOnClickListener(View.OnClickListener {

            activity?.let { it1 ->
                viewmodel.loginFunction(firebaseAuth,
                    it1,binding.loginEmail.text.toString(),binding.loginPassword.text.toString())
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