package com.anilyilmaz.ecommerce.dagger

import dagger.Module
import dagger.Provides

@Module
class ProductModule {
    @Provides
    fun provideProductLog() : ProductLog{
        return ProductLog("ProductLog is Succeed, the selected product has been added to the cart.")
    }
}