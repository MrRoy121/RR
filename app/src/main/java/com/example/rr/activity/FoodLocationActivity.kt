package com.example.rr.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rr.R


class FoodLocationActivity : AppCompatActivity() {

    private lateinit var next: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_location)

        val name=intent.getStringExtra("name")
        val c1 = intent.getBooleanExtra("c1",false)
        val c2 = intent.getBooleanExtra("c2",false)
        val c3 = intent.getBooleanExtra("c3",false)
        val ListView = findViewById<ListView>(R.id.item)
        val data = ArrayList<String>()
        if(c1){
            data.add("ASD")
        }else{
            data.remove("ASD")
        }
        if(c2){
            data.add("ASD")
        }else{
            data.remove("ASD")
        }
        if(c3){
            data.add("ASD")
        }else{
            data.remove("ASD")
        }
        val items: ArrayAdapter<String> = ArrayAdapter<String>(this, R.layout.food_item,R.id.text, data)
        ListView.adapter = items


        next = findViewById(R.id.next) as CardView
        next.setOnClickListener(object : View.OnClickListener{
            override fun onClick(view: View?) {
                val intent = Intent(this@FoodLocationActivity,PaymentActivity::class.java)
                intent.putExtra("name",name)
                intent.putExtra("c1",c1)
                intent.putExtra("c2",c2)
                intent.putExtra("c3",c3)
                startActivity(intent)
            }
        })
    }
}