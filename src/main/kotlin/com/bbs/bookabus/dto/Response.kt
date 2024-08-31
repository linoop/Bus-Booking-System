package com.bbs.bookabus.dto

data class Response<T>(
    val status: String,
    val message: String,
    val data: T? = null
)