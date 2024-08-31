package com.bbs.bookabus

import com.bbs.bookabus.entity.User
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class BookABusApp {
    @Bean
    fun initializer(userService: com.bbs.bookabus.service.UserService) = ApplicationRunner {
        userService.insertUser(User(1, "admin", "admin", "Super", "Admin", 1))
    }
}

fun main(args: Array<String>) {
    runApplication<BookABusApp>(*args)
}