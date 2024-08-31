package com.bbs.bookabus.dto

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class BookingDto(
    val busId: Long,

    @field:Min(value = 1, message = "seats field must be greater than 0")
    @field:NotNull(message = "seats field is required")
    @field:Max(value = 10, message = "seats field must be less than 10")
    val seats: Int,

    @field:NotBlank(message = "source field is required") val source: String,
    @field:NotBlank(message = "destination field is required") val destination: String,
)
