package com.anilyilmaz.ecommerce.utils

import com.anilyilmaz.ecommerce.services.RetrofitClient
import com.anilyilmaz.makeup.services.MakeUpApi

class ApiUtils {

    companion object{

        val BASE_URL = "https://makeup-api.herokuapp.com/"

        fun getMakeUpApi(): MakeUpApi {
            return RetrofitClient.getClient(BASE_URL).create(MakeUpApi::class.java)
        }
    }
}