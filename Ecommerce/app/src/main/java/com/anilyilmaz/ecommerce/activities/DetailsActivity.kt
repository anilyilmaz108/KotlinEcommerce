package com.anilyilmaz.ecommerce.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.anilyilmaz.ecommerce.dagger.DaggerProductComponent
import com.anilyilmaz.ecommerce.dagger.ProductMessage
import com.anilyilmaz.ecommerce.databinding.ActivityDetailsBinding
import com.anilyilmaz.ecommerce.fragments.CardFragment
import com.anilyilmaz.ecommerce.models.ProductNumberModel
import com.anilyilmaz.ecommerce.viewmodels.DetailsActivityViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import javax.inject.Inject


class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var db : FirebaseFirestore
    private lateinit var auth : FirebaseAuth
    private val viewmodel: DetailsActivityViewModel by viewModels()
    @Inject
    lateinit var productMessage: ProductMessage
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

       DaggerProductComponent.builder().build().inject(this)

        CardFragment.productModelsCard = ArrayList<ProductNumberModel>()
        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        var firstPrice = intent.getStringExtra("productPrice")!!.toDouble()
        val realPrice = intent.getStringExtra("productPrice")!!.toDouble()


        Picasso.get().load(intent.getStringExtra("productImage")).into(binding.imageId)
        binding.toolbarLayout.title = intent.getStringExtra("productName")
        binding.description.text = intent.getStringExtra("productDescription")
        binding.price.text = intent.getStringExtra("productPrice") + "₺"
        binding.ratingBar.rating = intent.getFloatExtra("productRating", 3.0F)



        binding.add.setOnClickListener {
            viewmodel.index += 1
            binding.amount.text = viewmodel.index.toString()
            firstPrice += realPrice
            binding.price.text = String.format("%.2f ₺",firstPrice)
        }

        binding.remove.setOnClickListener {
            viewmodel.index -= 1
            if (viewmodel.index == 1) {
                viewmodel.index = 1
                firstPrice = realPrice
                binding.amount.text = viewmodel.index.toString()
                binding.price.text = intent.getStringExtra("productPrice") + "₺"
            } else if(viewmodel.index < 1){
                viewmodel.index = 1
                firstPrice = realPrice
            }

            else {
                firstPrice -= realPrice
                binding.price.text = String.format("%.2f ₺",firstPrice)
                binding.amount.text = viewmodel.index.toString()
            }
        }

        binding.addToCard.setOnClickListener {
            productMessage.submitMessage()
            viewmodel.addItem(intent,auth,db,this,finish())
        }
    }
}