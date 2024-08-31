package com.bbs.bookabus.controller

import com.bbs.bookabus.dto.BusDto
import com.bbs.bookabus.entity.Bus
import com.bbs.bookabus.service.BusService
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/bus")
class BusController(
    private val busService: BusService
) {

    /**
     * POST
     * http://localhost:8080/api/bus/
     *     {
     *     "busNumber":"ABC1234",
     *     "capacity": 50,
     *     "source":"Kochi",
     *     "destination":"Trivandrum",
     *     "busName":"XYZ",
     *     "travelCompany":"XYZ"
     *     }
     */
    @PostMapping
    fun createBus(@RequestBody @Validated busDto: BusDto): ResponseEntity<Bus> {
        val bus = busService.createBus(
                Bus(
                        busNumber = busDto.busNumber,
                        capacity = busDto.capacity,
                        source = busDto.source,
                        destination = busDto.destination,
                        busName = busDto.busName,
                        travelCompany = busDto.travelCompany
                )
        )
        return ResponseEntity.ok(bus)
    }

    /**
     * PUT
     * http://localhost:8080/api/bus/1
     *     {
     *     "busNumber":"ABC1234",
     *     "capacity": 50,
     *     "source":"Kochi",
     *     "destination":"Trivandrum",
     *     "busName":"XYZ",
     *     "travelCompany":"XYZ"
     *     }
     */
    @PutMapping("/{id}")
    fun updateBus(@PathVariable id: Long, @RequestBody busDto: BusDto): ResponseEntity<Bus> {
        val bus = busService.updateBus(
            id, Bus(
                busNumber = busDto.busNumber,
                capacity = busDto.capacity,
                source = busDto.source,
                destination = busDto.destination,
                busName = busDto.busName,
                travelCompany = busDto.travelCompany
        )
        )
        return ResponseEntity.ok(bus)
    }

    /**
     * GET
     * http://localhost:8080/api/bus/1
     */
    @GetMapping("/{id}")
    fun fetchBus(@PathVariable id: Long): ResponseEntity<Bus> {
        val bus = busService.fetchBus(id)
        return ResponseEntity.ok(bus)
    }
}
