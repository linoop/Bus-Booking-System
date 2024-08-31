package com.bbs.bookabus.dto

import jakarta.validation.constraints.NotBlank

data class BusDto(
    @field:NotBlank(message = "busNumber field is required") val busNumber: String,
    val capacity: Int,
    val source: String,
    val destination: String,
    @field:NotBlank(message = "busName field is required")  val busName: String,
    val travelCompany: String,
)
