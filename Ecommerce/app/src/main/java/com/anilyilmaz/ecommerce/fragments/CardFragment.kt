package com.anilyilmaz.ecommerce.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.anilyilmaz.ecommerce.databinding.FragmentCardBinding
import com.anilyilmaz.ecommerce.models.ProductNumberModel
import com.anilyilmaz.ecommerce.viewmodels.CardFragmentViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class CardFragment : Fragment() {
    private var _binding: FragmentCardBinding? = null
    private lateinit var db : FirebaseFirestore
    private lateinit var auth : FirebaseAuth

    private val viewmodel: CardFragmentViewModel by viewModels()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        _binding = FragmentCardBinding.inflate(inflater, container, false)

        viewmodel.getDataFromFirestore(db, auth, binding.cardRv)
        binding.buttonBuying.text = viewmodel.buttonText

        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        lateinit var productModelsCard: ArrayList<ProductNumberModel>
    }
}