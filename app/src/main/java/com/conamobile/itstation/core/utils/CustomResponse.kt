package com.conamobile.itstation.core.utils

sealed class CustomResponse<out T> {
    data class SUCCESS<out T>(val data: List<T>) : CustomResponse<T>()
    data class ERROR(val message: String) : CustomResponse<Nothing>()
    object LOADING : CustomResponse<Nothing>()
}