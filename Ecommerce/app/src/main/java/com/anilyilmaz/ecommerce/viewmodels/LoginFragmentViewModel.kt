package com.anilyilmaz.ecommerce.viewmodels

import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.anilyilmaz.ecommerce.activities.BaseActivity
import com.google.firebase.auth.FirebaseAuth

class LoginFragmentViewModel : ViewModel() {
    fun loginFunction(auth: FirebaseAuth, fragmentActivity: FragmentActivity, email: String, password: String){
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->

            if (task.isSuccessful) {
                Toast.makeText(fragmentActivity,"Welcome: ${auth.currentUser?.email.toString()}",
                    Toast.LENGTH_LONG).show()
                val intent = Intent (fragmentActivity, BaseActivity::class.java)
                fragmentActivity.startActivity(intent)
            }

        }.addOnFailureListener { exception ->
            Toast.makeText(fragmentActivity,exception.localizedMessage.toString(), Toast.LENGTH_LONG).show()
        }
    }
}