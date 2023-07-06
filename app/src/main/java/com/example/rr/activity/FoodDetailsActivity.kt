package com.example.rr.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.rr.R

class FoodDetailsActivity : AppCompatActivity() {

    private lateinit var names:TextView
    private lateinit var c1:CheckBox
    private lateinit var c2:CheckBox
    private lateinit var c3:CheckBox
    private lateinit var next:CardView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_details)

        names = findViewById(R.id.name) as TextView
        val name=intent.getStringExtra("name")
        names.setText(name)

        c1 = findViewById(R.id.c1) as CheckBox
        c2 = findViewById(R.id.c2) as CheckBox
        c3 = findViewById(R.id.c3) as CheckBox
        next = findViewById(R.id.next) as CardView
        next.setOnClickListener(object : View.OnClickListener{
            override fun onClick(view: View?) {
                val intent = Intent(this@FoodDetailsActivity,FoodLocationActivity::class.java)
                intent.putExtra("name",name)
                intent.putExtra("c1",c1.isChecked)
                intent.putExtra("c2",c2.isChecked)
                intent.putExtra("c3",c3.isChecked)
                startActivity(intent)
            }
        })
    }
}