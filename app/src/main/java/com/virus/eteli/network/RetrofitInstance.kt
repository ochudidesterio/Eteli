package com.virus.eteli.network

import com.virus.eteli.api.Api
import com.virus.eteli.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api : Api by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASEURL)
            .build()
            .create(Api::class.java)
    }
}