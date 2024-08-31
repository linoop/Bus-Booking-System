package com.bbs.bookabus.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.NotBlank

@Entity
data class Bus(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
    val busNumber: String,
    val busName: String,
    val travelCompany: String,
    val capacity: Int,
    val source: String,
    val destination: String
)

