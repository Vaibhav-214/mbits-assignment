package dev.vaibhav.mbits.data

import android.util.Log
import dev.vaibhav.mbits.domain.BreathingToolRepository
import dev.vaibhav.mbits.domain.Data
import dev.vaibhav.mbits.domain.Response
import java.util.Date
import javax.inject.Inject

class BreathingToolRepositoryImpl @Inject constructor(
    private val apiService: BreathingToolApiService,
) : BreathingToolRepository {

    override suspend fun getBreathingToolData(userId: String, date: Date): Response<Data> {
        return try {
            val apiResponse = apiService.getBreathingData(userId = userId, dateInMillis = date.time)
            val body = apiResponse.body()
            if (body != null) {
                val data = body.data
                Response.Success(data)
            } else {
                val error = apiResponse.errorBody().toString()
                Log.i("API_ERROR", error)
                throw Exception("error occured")
            }
        } catch (e: Exception) {
            Log.i("API_ERROR", "${e.message}")
            Response.Error(e)
        }
    }


}