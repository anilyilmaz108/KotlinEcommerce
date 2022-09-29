package com.anilyilmaz.ecommerce.dagger

import com.anilyilmaz.ecommerce.activities.DetailsActivity
import dagger.Component

@Component(modules = [ProductModule::class])
interface ProductComponent {
    fun inject(activity: DetailsActivity)
}