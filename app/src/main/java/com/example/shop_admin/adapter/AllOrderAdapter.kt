package com.example.shop_admin.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.shop_admin.databinding.AllOrderItemLayoutBinding
import com.example.shop_admin.model.AllOrderModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class AllOrderAdapter(val list :ArrayList<AllOrderModel> , val context: Context)
    :RecyclerView.Adapter<AllOrderAdapter.AllOrderViewHolder>() {

    inner class AllOrderViewHolder(val binding : AllOrderItemLayoutBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllOrderViewHolder {
        return AllOrderViewHolder(
            AllOrderItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AllOrderViewHolder, position: Int) {
        holder.binding.productTitle.text = list[position].name
        holder.binding.productPrice.text = list[position].price
        holder.binding.cancelButton.setOnClickListener {
            holder.binding.proceedButton.text = "Canceled"

            updateStatus("Canceled",list[position].orderId!!)

        }
        when(list[position].status){
            "ordered" -> {
                holder.binding.proceedButton.text = "Ordered"

                holder.binding.proceedButton.setOnClickListener {
                    updateStatus("Dispatched",list[position].orderId!!)
                }
            }

            "Dispatched" -> {
                holder.binding.proceedButton.text = "Dispatched"
                holder.binding.cancelButton.setOnClickListener {
                    updateStatus("Canceled",list[position].orderId!!)

                }

            }

            "Delivered" -> {
                holder.binding.cancelButton.visibility = GONE
                holder.binding.proceedButton.text = "Delivered"
                holder.binding.cancelButton.setOnClickListener {
                    updateStatus("Canceled",list[position].orderId!!)

                }
            }
            "Canceled" ->{
                holder.binding.proceedButton.visibility = GONE
            }
        }

    }
    fun updateStatus(str:String , doc:String){
        val data = hashMapOf<String,Any>()

        data["string"] = str
        Firebase.firestore.collection("allOrders")
            .document(doc).update(data).addOnSuccessListener {
                Toast.makeText(context, "Status Updated", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener {

            }
    }


}