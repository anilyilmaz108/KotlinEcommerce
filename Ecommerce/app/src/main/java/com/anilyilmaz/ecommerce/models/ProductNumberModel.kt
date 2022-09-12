package com.anilyilmaz.ecommerce.models

data class ProductNumberModel(
    var id: String = "id",
    var brand: String,
    var name: String,
    var price: String,
    var image_link: String,
    var description: String,
    var rating: Float,
    var product_type: String,
    var product_number: String = "1")
{}