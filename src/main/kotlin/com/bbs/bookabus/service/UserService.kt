package com.bbs.bookabus.service

import com.bbs.bookabus.entity.User
import com.bbs.bookabus.repository.UserRepo
import org.hibernate.internal.util.collections.CollectionHelper.listOf
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
        private val userRepo: UserRepo,
        private val passwordEncoder: PasswordEncoder
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepo.findById(username).orElseThrow { UsernameNotFoundException("User not found") }
        return org.springframework.security.core.userdetails.User(
            user.username,
            user.password,
            listOf(SimpleGrantedAuthority(user.role.toString()))
        )
    }

    fun insertUser(user: User) {
        userRepo.save(user.copy(password = passwordEncoder.encode(user.password)))
    }

    fun getUser(username:String): User {
        return userRepo.findById(username).orElseThrow { UsernameNotFoundException("User not found") }
    }
}