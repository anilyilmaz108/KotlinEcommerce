package com.anilyilmaz.ecommerce.viewmodels

import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anilyilmaz.ecommerce.adapters.CardFragmentAdapter
import com.anilyilmaz.ecommerce.fragments.CardFragment
import com.anilyilmaz.ecommerce.models.ProductNumberModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class CardFragmentViewModel : ViewModel(){
    lateinit var cardFragmentAdapter: CardFragmentAdapter
    var totalPrice = 0.0
    var buttonText = ""

    fun getDataFromFirestore(db: FirebaseFirestore, auth: FirebaseAuth, cardRv: RecyclerView) {

        db.collection("Card").whereEqualTo("auth",auth.currentUser?.email.toString()).orderBy("date",
            Query.Direction.DESCENDING).addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                //Toast.makeText(activity,exception.localizedMessage.toString(), Toast.LENGTH_LONG).show()
            } else {

                if (snapshot != null) {
                    if (!snapshot.isEmpty) {
                        CardFragment.productModelsCard.clear()
                        totalPrice = 0.0
                        buttonText = ""
                        val documents = snapshot.documents
                        for (document in documents) {
                            val id = document.get("image_link") as String
                            val brand = document.get("brand") as String
                            val name = document.get("name") as String
                            val price = document.get("price") as String
                            val image_link = document.get("image_link") as String
                            val description = document.get("description") as String
                            val rating = document.get("rating") as Double
                            val product_type = document.get("product_type") as String
                            val product_number = document.get("product_number") as String

                            totalPrice += price.toDouble() * product_number.toDouble()

                            val tempProduct = ProductNumberModel(id,brand,name,price,image_link,description, rating.toFloat(), product_type,product_number)
                            CardFragment.productModelsCard.add(tempProduct)

                            cardRv.apply {
                                val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(context)
                                cardRv.layoutManager = layoutManager

                            }

                            cardFragmentAdapter!!.notifyDataSetChanged()

                        }


                    }
                }

            }
        }

        cardFragmentAdapter = CardFragmentAdapter(CardFragment.productModelsCard)
        cardRv.adapter = cardFragmentAdapter
        buttonText = String.format("BUY NOW (%.2f ₺)", totalPrice)

    }
}