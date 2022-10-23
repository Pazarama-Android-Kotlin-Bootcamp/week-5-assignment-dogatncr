package com.example.simple_socialmedia.data.remote.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//Api client for communication with jsonplaceholder services
class ApiClient {
    companion object {
        private lateinit var apiService: ApiService

        /** Initializing API service **/
        fun getApiService(): ApiService {

            if (!Companion::apiService.isInitialized) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                apiService = retrofit.create(ApiService::class.java)
            }
            return apiService
        }
    }
}