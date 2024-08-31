package com.bbs.bookabus.repository

import org.springframework.data.jpa.repository.JpaRepository

interface BookingRepo : JpaRepository<com.bbs.bookabus.entity.Booking, Long>
