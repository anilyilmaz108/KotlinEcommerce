package com.anilyilmaz.makeup.services

import com.anilyilmaz.makeup.models.ProductModel
import retrofit2.Response
import retrofit2.http.GET

interface MakeUpApi {
    @GET("api/v1/products.json?brand=maybelline")
    suspend fun getData(): Response<List<ProductModel>>
}