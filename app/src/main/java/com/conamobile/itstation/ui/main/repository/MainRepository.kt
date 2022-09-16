package com.conamobile.itstation.ui.main.repository

import com.conamobile.itstation.ui.main.db.ApiService
import com.conamobile.itstation.ui.main.models.MockApiModel
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val apiService: ApiService,
) {

    suspend fun getITStationImages(): Response<List<MockApiModel>> = apiService.getITStationImages()

}