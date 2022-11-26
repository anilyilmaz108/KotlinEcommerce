package com.anilyilmaz.ecommerce.viewmodels

import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.anilyilmaz.ecommerce.adapters.HomeFragmentAdapter
import com.anilyilmaz.ecommerce.utils.ApiUtils
import com.anilyilmaz.makeup.models.ProductModel
import kotlinx.coroutines.*

class HomeFragmentViewModel : ViewModel() {
    fun loadData(exceptionHandler: CoroutineExceptionHandler, homeRv : RecyclerView) {
        lateinit var productModels: ArrayList<ProductModel>
        lateinit var homeFragmentAdapter : HomeFragmentAdapter
        var job : Job? = null

        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {

            val response = ApiUtils.getMakeUpApi().getData()

            withContext(Dispatchers.Main){
                if(response.isSuccessful) {
                    response.body()?.let {

                        productModels = ArrayList(it)


                        productModels?.let {
                            homeRv.setHasFixedSize(true)
                            homeRv.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                            homeFragmentAdapter = HomeFragmentAdapter(it)
                            homeRv.adapter = homeFragmentAdapter
                        }
                    }
                }
            }
        }
    }
}