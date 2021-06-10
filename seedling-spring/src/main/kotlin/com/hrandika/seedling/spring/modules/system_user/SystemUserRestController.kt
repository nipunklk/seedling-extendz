package com.hrandika.seedling.spring.modules.system_user

import org.springframework.data.rest.webmvc.RepositoryRestController
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

data class User(
    val username: String,
    val password: String
)

data class Credential(
    val email: String,
    val password: String,
    val tenant: String
)

@CrossOrigin
//@RepositoryRestController
class UserRestController(val userRepository: SystemUserRepository) {

    @PostMapping("systemUsers")
    private fun loginWithCredential(@RequestBody user: User): ResponseEntity<SystemUser> {
        val systemUser = SystemUser(user.username, user.password)
        userRepository.save(systemUser)
        return ResponseEntity.ok(systemUser)
    }

}