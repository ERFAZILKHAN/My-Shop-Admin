package com.example.shop_admin.model

data class AddProductModel(

    val productName:String? = "",
    val productDescription:String? = "",
    val productCoverImg:String? = "",
    val productCategory:String? = "",
    val productId:String? = "",
    val productMrp:String? = "",
    val productSp:String? = "",
    val productImage:ArrayList<String>


)
