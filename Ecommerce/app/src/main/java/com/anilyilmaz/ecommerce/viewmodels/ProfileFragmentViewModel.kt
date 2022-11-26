package com.anilyilmaz.ecommerce.viewmodels

import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ProfileFragmentViewModel : ViewModel(){
    var count = 0

    fun profileFunction(db: FirebaseFirestore, auth: FirebaseAuth, textView: TextView){
        count = 0

        val productsRef = db.collection("Card").whereEqualTo("auth",auth.currentUser?.email.toString())
        productsRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                task.result?.let {
                    for (snapshot in it) {
                        count++
                    }
                }
                print("count: $count")
                textView.text = count.toString()
            } else {
                task.exception?.message?.let {
                    print(it)
                }
                textView.text = count.toString()
            }
        }
    }
}