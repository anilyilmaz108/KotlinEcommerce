package com.anilyilmaz.ecommerce.fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.anilyilmaz.ecommerce.databinding.FragmentHomeBinding
import com.anilyilmaz.ecommerce.viewmodels.HomeFragmentViewModel
import com.facebook.shimmer.ShimmerFrameLayout
import kotlinx.coroutines.CoroutineExceptionHandler


class HomeFragment : Fragment(){

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var shimmerLayout: ShimmerFrameLayout
    private lateinit var loadingLayout: LinearLayout
    private val viewmodel: HomeFragmentViewModel by viewModels()

    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error: ${throwable.localizedMessage}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        shimmerLayout = binding.shimmerLayout
        loadingLayout = binding.activityMainLoadingLayout

        start()
        val view = binding.root
        return view
    }



    private fun start(){
        shimmerLayout.startShimmer()
        Handler().postDelayed({
            shimmerLayout.hideShimmer()
            loadingLayout.visibility = View.GONE
            binding.homeRv.visibility = View.VISIBLE
            viewmodel.loadData(exceptionHandler,binding.homeRv)
        }, 3500)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}