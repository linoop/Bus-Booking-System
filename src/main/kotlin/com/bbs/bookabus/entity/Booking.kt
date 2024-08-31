package com.bbs.bookabus.entity

import jakarta.persistence.*
import java.util.*

@Entity
data class Booking(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
        val bookingNumber: String,
        @ManyToOne
    @JoinColumn(name = "bus_id")
    val bus: Bus,
        val bookingDate: Date,
        val source: String,
        val destination: String,
        val userId: Long = 0,
        val bookingStatus: String,
        val seats: Int
)