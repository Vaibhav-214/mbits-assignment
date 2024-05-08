package dev.vaibhav.mbits.data

import dev.vaibhav.mbits.domain.BreathingToolApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface BreathingToolApiService {
    @GET("tools/breathing/get/")
    suspend fun getBreathingData(
        @Query("uid") userId: String,
        @Query("date") dateInMillis: Long
    ): Response<BreathingToolApiResponse>
}