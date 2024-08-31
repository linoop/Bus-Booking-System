package com.bbs.bookabus.service

import com.bbs.bookabus.BusNotFoundException
import com.bbs.bookabus.entity.Booking
import com.bbs.bookabus.entity.Bus
import com.bbs.bookabus.entity.User
import com.bbs.bookabus.repository.BookingRepo
import com.bbs.bookabus.repository.BusRepo
import org.springframework.stereotype.Service
import java.util.*

@Service
class BookingService(
        private val bookingRepo: BookingRepo,
        private val busRepo: BusRepo
) {

    fun bookBus(user: User, busId: Long, seats: Int, source: String, destination: String): Booking {
        val bus = busRepo.findById(busId).orElseThrow { RuntimeException("Bus not found") }
        if (seats > bus.capacity) {
            throw RuntimeException("Not enough seats available")
        }
        if (bus.source != source) {
            throw RuntimeException("Wrong Source for the selected Bus")
        }
        if (bus.destination != destination) {
            throw RuntimeException("Wrong Destination for the selected Bus")
        }
        val bookedData = bookingRepo.save(
                Booking(
                        bus = bus, userId = user.id, seats = seats,
                        bookingNumber = generateRandomAlphanumeric(5),
                        bookingDate = Date(System.currentTimeMillis()),
                        source = source,
                        destination = destination,
                        bookingStatus = "SUCCESS"
                )
        )

        val existingBus =
            busRepo.findById(bookedData.bus.id).orElseThrow {BusNotFoundException(bookedData.bus.id) }
        busRepo.save(
            existingBus.copy(
                capacity = (bus.capacity - seats),
            )
        )
        return bookedData
    }

    fun searchBuses(source: String, destination: String): List<Bus> =
        busRepo.findBySourceAndDestination(source, destination)

    fun generateRandomAlphanumeric(length: Int): String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
                .map { allowedChars.random() }
                .joinToString("")
    }
}

