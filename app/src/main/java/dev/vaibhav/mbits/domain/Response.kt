package dev.vaibhav.mbits.domain

sealed class Response<out T> {
    data class Success<T>(val data: T): Response<T>()
    data class Error(val error: Exception): Response<Nothing>()
    object Loading: Response<Nothing>()
}