package com.example.rr.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.rr.R
import com.example.rr.activity.fragments.fav
import com.example.rr.activity.fragments.hot
import com.example.rr.activity.fragments.profile
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private val activity = this@MainActivity
    private val sharedPrefFile = "user"

    lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        val sharedIdValue = sharedPreferences.getBoolean("is",false)
        if (!sharedIdValue){
            val i = Intent(this, LoginActivity::class.java)
            finish()
            startActivity(i)
        }

        loadFragment(hot())
        bottomNav = findViewById(R.id.bottomnav) as BottomNavigationView
        bottomNav.setOnItemSelectedListener { it ->
            when (it.itemId) {
                R.id.love -> {
                    loadFragment(fav())
                }
                R.id.hot -> {
                    loadFragment(hot())
                }
                R.id.profile -> {
                    loadFragment(profile())
                }
            }
            true
        }
    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}
