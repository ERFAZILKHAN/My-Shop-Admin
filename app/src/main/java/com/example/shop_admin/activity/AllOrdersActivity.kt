package com.example.shop_admin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.shop_admin.R
import com.example.shop_admin.adapter.AllOrderAdapter
import com.example.shop_admin.model.AllOrderModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject

class AllOrdersActivity : AppCompatActivity() {

    private lateinit var list :ArrayList<AllOrderModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_orders)
        list = ArrayList()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        Firebase.firestore.collection("allOrders").get().addOnSuccessListener {
            list.clear()
            for (doc in it){
                val data = doc.toObject(AllOrderModel::class.java)
                list.add(data)
            }
        }

        recyclerView.adapter = AllOrderAdapter(list,this)
    }
}