package com.example.myapplication6

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface{

    @GET("posts")
    fun getposts():Call<JsonArray>

    @GET("posts")
   fun getpostsArray():Call<List<PostModel>>


    @GET("photos")
    fun getAllPhotos():Call<JsonArray>
}