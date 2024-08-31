package com.bbs.bookabus.repository

import com.bbs.bookabus.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepo : JpaRepository<User, String>
