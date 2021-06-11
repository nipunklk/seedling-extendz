package com.hrandika.seedling.spring.core.controllers.auth

import com.hrandika.seedling.spring.config.security.JwtTokenProvider
import com.hrandika.seedling.spring.modules.system_user.SystemUserRepository
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.*


data class AuthenticationRequest(val email: String, val password: String)

@CrossOrigin
@RestController
@RequestMapping(value = ["\${spring.data.rest.base-path}/signin"])
class AuthController(
    var authenticationManager: AuthenticationManager,
    var jwtTokenProvider: JwtTokenProvider,
    var systemUserRepository: SystemUserRepository
) {

    @PostMapping()
    fun signin(@RequestBody data: AuthenticationRequest): ResponseEntity<*>? {
        return try {
            val username: String = data.email
            val authentication =
                authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, data.password))
            val token = jwtTokenProvider!!.createToken(authentication)
            val model: MutableMap<Any, Any> = HashMap()
            model["username"] = username
            model["access_token"] = token
            ok(model)
        } catch (e: AuthenticationException) {
            throw BadCredentialsException("Invalid username/password supplied")
        }
    }
}