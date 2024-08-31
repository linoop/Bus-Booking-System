package com.bbs.bookabus.config

import com.bbs.bookabus.entity.User
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtils {

    @Value("\${jwt.secret}")
    private lateinit var jwtSecret: String

    @Value("\${jwt.expiration}")
    private var jwtExpiration: Long = 0

    fun generateToken(userDetails: User): String {
        val claims: Map<String, Any> = HashMap()
        return doGenerateToken(claims, userDetails.username)
    }

    private fun doGenerateToken(claims: Map<String, Any>, username: String?): String {
        val key = Keys.hmacShaKeyFor(jwtSecret.toByteArray())
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + jwtExpiration))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()

    }

    fun validateToken(token: String, user: UserDetails): Boolean {
        val username = extractUsername(token)
        return username == user.username && !isTokenExpired(token)
    }

    fun extractUsername(token: String): String {
        return getClaimFromToken(token) { it.subject }
    }

    private fun getClaimFromToken(token: String): Claims {
        val key = Keys.hmacShaKeyFor(jwtSecret.toByteArray())
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build().parseClaimsJws(token).body
    }

    private fun <T> getClaimFromToken(token: String, claimsResolver: (Claims) -> T): T {
        val claims = getClaimFromToken(token)
        return claimsResolver(claims)
    }

    private fun isTokenExpired(token: String): Boolean {
        val expiration = getClaimFromToken(token) { it.expiration }
        return expiration.before(Date())
    }
}
