package com.rival.tutorialloginregist.Scan

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("api")
    fun getApi(): Call<ArrayList<coffe>>

}