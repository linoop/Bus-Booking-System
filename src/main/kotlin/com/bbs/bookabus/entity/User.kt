package com.bbs.bookabus.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.validation.constraints.NotBlank

@Entity
data class User(
    val id: Long = 0,
    @Id val username: String,
    @field:NotBlank(message = "first name is required") val firstName: String,
    val lastName: String,
    val password: String,
    val role: Int
)

