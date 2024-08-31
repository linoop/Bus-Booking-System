package com.bbs.bookabus.controller

import com.bbs.bookabus.config.JwtUtils
import com.bbs.bookabus.dto.AuthRequest
import com.bbs.bookabus.dto.AuthResponse
import com.bbs.bookabus.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import javax.naming.AuthenticationException

@RestController
@RequestMapping("/api/")
class AuthController(
        private val authenticationManager: AuthenticationManager,
        private val userService: UserService,
        private val jwtUtils: JwtUtils
) {
    /**
     * POST
     * http://localhost:8080/api/authentication
     *  {
     *     "username":"admin",
     *     "password":"admin"
     * }
     */
    @PostMapping("/authentication")
    fun login(@RequestBody authRequest: AuthRequest): ResponseEntity<AuthResponse> {
//        try {
//            authenticationManager.authenticate(
//                UsernamePasswordAuthenticationToken(authRequest.username, authRequest.password)
//            )
//        } catch (ex: AuthenticationException) {
//            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
//        }

        val user = userService.getUser(authRequest.username)
        val token = jwtUtils.generateToken(user)
        return ResponseEntity.status(HttpStatus.OK).body(AuthResponse(token))

    }
}
