package com.example.rr.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.rr.R
import com.example.rr.sql.DatabaseHelper

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private val activity = this@LoginActivity
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var login: CardView
    private lateinit var register: CardView
    private lateinit var databaseHelper: DatabaseHelper

    private val sharedPrefFile = "user"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        email = findViewById<View>(R.id.email) as EditText
        password = findViewById<View>(R.id.password) as EditText
        login = findViewById<View>(R.id.login) as CardView
        register = findViewById<View>(R.id.register) as CardView

        login.setOnClickListener(this)
        register.setOnClickListener(this)

        databaseHelper = DatabaseHelper(activity, null)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.login -> verifyFromSQLite()
            R.id.register -> {
                val intentRegister = Intent(applicationContext, RegisterActivity::class.java)
                startActivity(intentRegister)
            }
        }
    }

    private fun verifyFromSQLite() {
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        if (email.text.toString().length==0){
            Toast.makeText(activity,"Enter The Email!!",Toast.LENGTH_SHORT).show();
            return
        }
        else if (password.text.toString().length==0){
            Toast.makeText(activity,"Enter The Password!!",Toast.LENGTH_SHORT).show();
            return
        }
        else if (databaseHelper.checkUser(email.text.toString().trim { it <= ' ' },
                password.text.toString().trim { it <= ' ' })) {
            val i = Intent(activity, MainActivity::class.java)
            val editor:SharedPreferences.Editor =  sharedPreferences.edit()
            editor.putString("name",databaseHelper.getUsername(email.text.toString().trim { it <= ' ' }))
            editor.putBoolean("is",true)
            editor.putString("email", email.text.toString().trim { it <= ' ' })
            editor.putString("pass", password.text.toString().trim { it <= ' ' })
            editor.putBoolean("0",false)
            editor.putBoolean("1",false)
            editor.putBoolean("2",false)
            editor.apply()
            startActivity(i)
            finish()

        } else {
            Toast.makeText(activity,"Wrong Email And Password!!",Toast.LENGTH_SHORT).show();
        }
    }
}
