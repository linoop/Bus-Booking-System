package com.bbs.bookabus.config

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtRequestFilter(
        private val jwtTokenProvider: JwtUtils,
        private val userDetailsService: UserDetailsService
) : OncePerRequestFilter() {

    @Throws(ServletException::class, java.io.IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val requestTokenHeader = request.getHeader("Authorization")

        var username: String? = null
        var jwtToken: String? = null

        // JWT Token is in the form "Bearer token". Remove Bearer word and get the token only
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7)
            try {
                username = jwtTokenProvider.extractUsername(jwtToken)
            } catch (e: Exception) {
                println("Unable to get JWT Token")
            }
        } else {
            println("JWT Token does not begin with Bearer String")
        }

        // Once we get the token, validate it
        if (username != null && SecurityContextHolder.getContext().authentication == null && jwtToken != null) {

            val userDetails: UserDetails = userDetailsService.loadUserByUsername(username)

            // If the token is valid, configure Spring Security to manually set authentication
            if (jwtTokenProvider.validateToken(jwtToken, userDetails)) {
                val authenticationToken =
                    UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                authenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authenticationToken
            }
        }

        filterChain.doFilter(request, response)
    }
}
