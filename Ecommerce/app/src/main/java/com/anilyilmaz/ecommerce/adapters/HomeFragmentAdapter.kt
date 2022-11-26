package com.anilyilmaz.ecommerce.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.anilyilmaz.ecommerce.R
import com.anilyilmaz.ecommerce.activities.DetailsActivity
import com.anilyilmaz.ecommerce.fragments.CardFragment
import com.anilyilmaz.ecommerce.models.ProductNumberModel
import com.anilyilmaz.makeup.models.ProductModel
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso



class HomeFragmentAdapter(private val productList : ArrayList<ProductModel>)
    : RecyclerView.Adapter<HomeFragmentAdapter.RowHolder>() {
    private lateinit var db : FirebaseFirestore
    private lateinit var auth : FirebaseAuth




    inner class RowHolder(view:View):RecyclerView.ViewHolder(view){
        var imageViewFilmResim: ImageView
        var textViewFilmBaslik: TextView
        var ratingBar: RatingBar
        var textViewFilmFiyat: TextView
        var buttonSepeteEkle: Button
        var cardViewProduct: CardView

        init {
            db = FirebaseFirestore.getInstance()
            auth = FirebaseAuth.getInstance()
            imageViewFilmResim = view.findViewById(R.id.imageViewlogo)
            textViewFilmBaslik = view.findViewById(R.id.textViewFilmBaslik)
            ratingBar = view.findViewById(R.id.ratingBar)
            textViewFilmFiyat = view.findViewById(R.id.textViewFilmFiyat)
            buttonSepeteEkle = view.findViewById(R.id.buttonSepeteEkle)
            cardViewProduct = view.findViewById(R.id.cardViewProduct)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_product,parent,false)
        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        val film = productList[position]

        holder.textViewFilmBaslik.text = film.name
        holder.textViewFilmFiyat.text = "${film.price} TL"
        holder.ratingBar.rating = film.rating
        Picasso.get().load(film.image_link).into(holder.imageViewFilmResim)
        CardFragment.productModelsCard = ArrayList<ProductNumberModel>()

        holder.cardViewProduct.setOnClickListener(View.OnClickListener {
            val intent = Intent(holder.cardViewProduct.context, DetailsActivity::class.java)
            intent.putExtra("productName",film.name)
            intent.putExtra("productPrice",film.price)
            intent.putExtra("productImage",film.image_link)
            intent.putExtra("productBrand",film.brand)
            intent.putExtra("productDescription",film.description)
            intent.putExtra("productRating",film.rating)

            holder.cardViewProduct.context.startActivity(intent)
        })


        holder.buttonSepeteEkle.setOnClickListener(View.OnClickListener {
            CardFragment.productModelsCard.add(ProductNumberModel(
                Timestamp.now().toString(),film.brand,film.name,film.price,film.image_link,film.description,film.rating,film.product_type,"1"
            ))
            Log.e("aa", "${CardFragment.productModelsCard[0].price}", )

            val postMap = hashMapOf<String,Any>()
            postMap.put("auth", auth.currentUser!!.email.toString())
            postMap.put("brand","Maybelline")
            postMap.put("name",film.name)
            postMap.put("price",film.price)
            postMap.put("image_link", film.image_link)
            postMap.put("description",film.description)
            postMap.put("rating",film.rating)
            postMap.put("product_type", "")
            postMap.put("product_number", "1")
            postMap.put("date", Timestamp.now())


            db.collection( "Card").add(postMap).addOnCompleteListener{task ->

                if (task.isComplete && task.isSuccessful) {
                    //back
                    Log.e("EKLE", "EKLENDIIII", )

                }

            }.addOnFailureListener{exception ->
                Log.e("EKLE", "EKLENMEDIIII", )
            }


        })
    }

    override fun getItemCount(): Int {
        return productList.size
    }


}
