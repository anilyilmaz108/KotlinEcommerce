package com.anilyilmaz.ecommerce.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anilyilmaz.ecommerce.R
import com.anilyilmaz.ecommerce.adapters.ViewPagerAdapter
import com.anilyilmaz.ecommerce.databinding.ActivityMainBinding
import com.anilyilmaz.ecommerce.fragments.LoginFragment
import com.anilyilmaz.ecommerce.fragments.RegisterFragment
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        firebaseAuth = FirebaseAuth.getInstance()

        val currentUser = firebaseAuth.currentUser

        if (currentUser != null) {
            val intent = Intent(applicationContext,BaseActivity::class.java)
            startActivity(intent)
            finish()
        }

        setUpTabs()

    }

    private fun setUpTabs(){
        val loginFragment = LoginFragment()
        val registerFragment = RegisterFragment()

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(loginFragment,"Login")
        adapter.addFragment(registerFragment,"Register")

        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)

        binding.tabs.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_login)
        binding.tabs.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_register)

    }
}