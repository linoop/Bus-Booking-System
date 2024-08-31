package com.bbs.bookabus.controller

import com.bbs.bookabus.dto.BookingDto
import com.bbs.bookabus.entity.Booking
import com.bbs.bookabus.entity.Bus
import com.bbs.bookabus.service.BookingService
import com.bbs.bookabus.service.UserService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/system")
class BookingController(
        private val bookingService: BookingService,
        private val userService: UserService,
) {

    /**
     * POST
     * http://localhost:8080/api/system/book
     * Authorization: Bearer
     */

    @PostMapping("/book")
    fun bookBus(
        @AuthenticationPrincipal user: User,
        @RequestBody @Valid BookingDto: BookingDto
    ): ResponseEntity<Booking> {
        val booking = bookingService.bookBus(
            userService.getUser(user.username),
            BookingDto.busId,
            BookingDto.seats,
            BookingDto.source,
            BookingDto.destination
        )
        return ResponseEntity.ok(booking)
    }


    /**
     * GET
     * http://localhost:8080/api/system/search?source=Kochi&destination=Trivandrum
     * Authorization: Bearer
     */

    @GetMapping("/search")
    fun searchBuses(
        @AuthenticationPrincipal user: User,
        @RequestParam source: String,
        @RequestParam destination: String
    ): ResponseEntity<List<Bus>> {
        val buses = bookingService.searchBuses(source, destination)
        return ResponseEntity.ok(buses)
    }
}
