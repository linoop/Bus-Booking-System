package com.bbs.bookabus.service

import com.bbs.bookabus.BusNotFoundException
import com.bbs.bookabus.entity.Bus
import com.bbs.bookabus.repository.BusRepo
import org.springframework.stereotype.Service

@Service
class BusService(
    private val busRepo: BusRepo
) {
    fun createBus(bus: Bus): Bus {
        try {
            return busRepo.save(bus)
        } catch (ex: Exception) {
            throw Exception("Bus Creation Error ${ex.message}")
        }
    }

    fun updateBus(id: Long, bus: Bus): Bus {
        val existingBus = busRepo.findById(id).orElseThrow { BusNotFoundException(id) }
        return busRepo.save(
            existingBus.copy(
                busNumber = bus.busNumber,
                capacity = bus.capacity,
                source = bus.source,
                travelCompany = bus.travelCompany,
                busName = bus.busName,
                destination = bus.destination
            )
        )
    }

    fun fetchBus(id: Long): Bus = busRepo.findById(id).orElseThrow { BusNotFoundException(id) }

}
