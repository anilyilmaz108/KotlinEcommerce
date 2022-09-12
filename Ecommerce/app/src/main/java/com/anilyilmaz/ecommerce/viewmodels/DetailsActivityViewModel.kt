package com.anilyilmaz.ecommerce.viewmodels

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.anilyilmaz.ecommerce.fragments.CardFragment
import com.anilyilmaz.ecommerce.models.ProductNumberModel
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class DetailsActivityViewModel : ViewModel(){
    var index: Int = 1

    fun addItem(intent: Intent, auth: FirebaseAuth, db: FirebaseFirestore, context: Context, unit: Unit){

        val product = ProductNumberModel(
            Timestamp.now().toString(),"Maybelline",intent.getStringExtra("productName").toString(),
            intent.getStringExtra("productPrice").toString(),intent.getStringExtra("productImage").toString(),
            intent.getStringExtra("productDescription").toString(),intent.getFloatExtra("productRating",3.0F),
            "",index.toString())
        CardFragment.productModelsCard.add(product)

        Toast.makeText(context, "${product.name} Eklendi", Toast.LENGTH_SHORT).show()

        val postMap = hashMapOf<String,Any>()
        postMap.put("auth", auth.currentUser!!.email.toString())
        postMap.put("brand","Maybelline")
        postMap.put("name",intent.getStringExtra("productName").toString())
        postMap.put("price",intent.getStringExtra("productPrice").toString())
        postMap.put("image_link", intent.getStringExtra("productImage").toString())
        postMap.put("description",intent.getStringExtra("productDescription").toString())
        postMap.put("rating",intent.getFloatExtra("productRating",3.0F))
        postMap.put("product_type", "")
        postMap.put("product_number", index.toString())
        postMap.put("date", Timestamp.now())


        db.collection( "Card").add(postMap).addOnCompleteListener{task ->

            if (task.isComplete && task.isSuccessful) {
                //back
                unit
            }

        }.addOnFailureListener{exception ->
            Toast.makeText(context,exception.localizedMessage.toString(),
                Toast.LENGTH_LONG).show()
        }
    }

}