package com.hrandika.seedling.spring.modules.system_user

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class CustomUserDetailsService(val systemUserRepository: SystemUserRepository) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        return this.systemUserRepository.findByUsername(username)
            .orElseThrow { UsernameNotFoundException("Username: $username not found") }
    }

}