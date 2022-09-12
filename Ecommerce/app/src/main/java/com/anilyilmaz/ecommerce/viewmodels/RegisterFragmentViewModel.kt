package com.anilyilmaz.ecommerce.viewmodels

import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.anilyilmaz.ecommerce.activities.BaseActivity
import com.google.firebase.auth.FirebaseAuth

class RegisterFragmentViewModel : ViewModel(){

    var phone = ""

    fun registerFunction(auth: FirebaseAuth,email: String, password: String, fragmentActivity: FragmentActivity){

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(fragmentActivity,"Welcome: ${auth.currentUser?.email.toString()}",
                    Toast.LENGTH_LONG).show()
                val intent = Intent(fragmentActivity, BaseActivity::class.java)
                fragmentActivity.startActivity(intent)
            }

        }.addOnFailureListener { exception ->
            if (exception != null) {
                Toast.makeText(fragmentActivity,exception.localizedMessage.toString(), Toast.LENGTH_LONG).show()
            }
        }

        val intent = Intent (fragmentActivity, BaseActivity::class.java)
        fragmentActivity.startActivity(intent)
    }
}