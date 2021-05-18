package com.example.gyt

import com.google.gson.annotations.SerializedName

data class Response(@SerializedName("success") var success:Boolean, @SerializedName("message") var message:String, @SerializedName("data") var data:HashMap<String,String>)