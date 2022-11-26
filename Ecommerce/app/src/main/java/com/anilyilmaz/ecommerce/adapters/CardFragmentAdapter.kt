package com.anilyilmaz.ecommerce.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.anilyilmaz.ecommerce.R
import com.anilyilmaz.ecommerce.fragments.CardFragment
import com.anilyilmaz.ecommerce.models.ProductNumberModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.squareup.picasso.Picasso


class CardFragmentAdapter(private val productList: ArrayList<ProductNumberModel>)
    : RecyclerView.Adapter<CardFragmentAdapter.RowHolder>() {

    private lateinit var db : FirebaseFirestore

    inner class RowHolder(view: View): RecyclerView.ViewHolder(view){
        var imageViewCard: ImageView
        var textName: TextView
        var textNumber: TextView
        var textPrice: TextView
        var buttonremove: ImageButton

        init {
            db = FirebaseFirestore.getInstance()
            CardFragment.productModelsCard = ArrayList<ProductNumberModel>()
            imageViewCard = view.findViewById(R.id.imageViewCard)
            textName = view.findViewById(R.id.text_name)
            textNumber = view.findViewById(R.id.text_number)
            textPrice = view.findViewById(R.id.text_price)
            buttonremove = view.findViewById(R.id.removeButton)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardFragmentAdapter.RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_product,parent,false)
        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: CardFragmentAdapter.RowHolder, position: Int) {
        val film = productList[position]

        holder.textName.text = film.name
        holder.textPrice.text = String.format("%.2f TL",film.price.toDouble() * film.product_number.toDouble())
        holder.textNumber.text = film.product_number
        Picasso.get().load(film.image_link).into(holder.imageViewCard)

        holder.buttonremove.setOnClickListener(View.OnClickListener {
            Log.e("Mesaj", "${holder.textName.text}")
            productList.removeAt(position)
            db.collection("Card").whereEqualTo("image_link", film.image_link).get().addOnCompleteListener(
                OnCompleteListener<QuerySnapshot> {
                    if(it.isSuccessful){
                        for (document in it.result) {
                            db.collection("Card").document(document.id).delete()
                        }
                    }
            })

            notifyItemRemoved(position)
        })


    }

    override fun getItemCount(): Int {
        return productList.size
    }
}