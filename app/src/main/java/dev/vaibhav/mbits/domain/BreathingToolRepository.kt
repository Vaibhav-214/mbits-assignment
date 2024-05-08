package dev.vaibhav.mbits.domain

import java.util.Date

interface BreathingToolRepository {
    suspend fun getBreathingToolData(userId: String, date: Date): Response<Data>
}