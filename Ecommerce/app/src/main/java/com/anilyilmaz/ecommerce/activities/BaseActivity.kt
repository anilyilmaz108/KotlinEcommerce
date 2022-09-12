package com.anilyilmaz.ecommerce.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.anilyilmaz.ecommerce.R
import com.anilyilmaz.ecommerce.databinding.ActivityBaseBinding
import com.google.firebase.auth.FirebaseAuth

class BaseActivity : AppCompatActivity(){
    private lateinit var binding: ActivityBaseBinding
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = FirebaseAuth.getInstance()

        binding.toolbar.title = "Ecommerce"
        setSupportActionBar(binding.toolbar)

        binding.logoutButton.setOnClickListener(View.OnClickListener {
            auth.signOut()
            val intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
            finish()
        })

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bottomNav, navHostFragment.navController)

    }
}