package com.example.rr.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.rr.R
import com.example.rr.model.UserModel
import com.example.rr.sql.DatabaseHelper

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private val activity = this@RegisterActivity


    private lateinit var name: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var register: CardView
    private lateinit var login: CardView
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)



        name = findViewById<View>(R.id.name) as EditText
        email = findViewById<View>(R.id.email) as EditText
        password = findViewById<View>(R.id.password) as EditText

        register = findViewById<View>(R.id.register) as CardView
        login = findViewById<View>(R.id.login) as CardView


        register.setOnClickListener(this)
        login.setOnClickListener(this)

        databaseHelper = DatabaseHelper(activity, null)
    }
    override fun onClick(v: View) {
        when (v.id) {

            R.id.register -> postDataToSQLite()
            R.id.login -> finish()
        }
    }

    private fun postDataToSQLite() {

        if (name.text.toString().length==0){
            Toast.makeText(activity,"Enter The Username!!",Toast.LENGTH_SHORT).show();
            return
        }else if (email.text.toString().length==0){
            Toast.makeText(activity,"Enter The Email!!",Toast.LENGTH_SHORT).show();
            return
        }
        else if (password.text.toString().length==0){
            Toast.makeText(activity,"Enter The Password!!",Toast.LENGTH_SHORT).show();
            return
        } else if (!databaseHelper.checkUsers(email.text.toString().trim())) {

            val user = UserModel(name = name.text.toString().trim(),
                    email = email.text.toString().trim(),
                    password = password.text.toString().trim())

            databaseHelper.addUser(user)

            Toast.makeText(activity,"Registration Successful!!",Toast.LENGTH_SHORT).show();
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()


        } else {
            Toast.makeText(activity,"Email Already Exists!!", Toast.LENGTH_SHORT).show();
        }
    }

}
