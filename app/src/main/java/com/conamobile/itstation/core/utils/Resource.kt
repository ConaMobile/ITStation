package com.conamobile.itstation.core.utils

open class Resource<out T> constructor(
    val status: ResourceState,
    val data: T?,
    val message: String?,
    val check: String?,
) {

    companion object {

        fun <T> success(data: T): Resource<T> {
            return Resource(ResourceState.SUCCESS, data, null, null)
        }

        fun <T> error(message: String?): Resource<T> {
            return Resource(ResourceState.ERROR, null, message, null)
        }

        fun <T> loading(): Resource<T> {
            return Resource(ResourceState.LOADING, null, null, null)
        }

        fun <T> check(check: String?): Resource<T> {
            return Resource(ResourceState.CHECK, null, null, check)
        }
    }
}

enum class ResourceState {
    LOADING, SUCCESS, ERROR, CHECK
}