package com.example.shop_admin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shop_admin.R
import com.example.shop_admin.databinding.ItemCategoryLayoutBinding
import com.example.shop_admin.model.CategoryModel


class CategoryAdapter(var context: Context,val list:ArrayList<CategoryModel>)
    :RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>(){

    inner class CategoryViewHolder(view:View):RecyclerView.ViewHolder(view){
        var binding = ItemCategoryLayoutBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(LayoutInflater.from(context).inflate(R.layout.item_category_layout,parent,false))

    }

    override fun getItemCount(): Int {
        return list.size

    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.binding.textView.text = list[position].cat
        Glide.with(context).load(list[position].img).into(holder.binding.imageView2)

    }
}




