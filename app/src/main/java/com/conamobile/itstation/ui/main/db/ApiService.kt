package com.conamobile.itstation.ui.main.db

import com.conamobile.itstation.ui.main.models.ImageModel
import com.conamobile.itstation.ui.main.models.MockApiModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("images")
    suspend fun getITStationImages(): Response<List<MockApiModel>>

    @GET("users")
    suspend fun getITStationTeachers(): Response<List<ImageModel>>
}