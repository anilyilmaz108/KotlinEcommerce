package com.anilyilmaz.ecommerce.dagger

import android.util.Log
import javax.inject.Inject

class ProductMessage @Inject constructor (var productLog: ProductLog) {
    fun submitMessage(){
        Log.e("Result","==> ${productLog.ProductLogMessage} <==")
    }
}