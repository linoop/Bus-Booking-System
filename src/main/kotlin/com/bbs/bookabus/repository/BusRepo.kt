package com.bbs.bookabus.repository

import com.bbs.bookabus.entity.Bus

import org.springframework.data.jpa.repository.JpaRepository

interface BusRepo : JpaRepository<Bus, Long> {
    fun findBySourceAndDestination(source: String, destination: String): List<Bus>
}
