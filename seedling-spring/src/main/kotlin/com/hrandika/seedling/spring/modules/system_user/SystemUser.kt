package com.hrandika.seedling.spring.modules.system_user

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import java.util.stream.Collectors
import javax.persistence.*

@Entity
@Table(name = "system_user")
class SystemUser() : UserDetails {

    constructor(_userName: String, _password: String) : this() {
        this.username = _userName
        this.password = _password
    }

    constructor(_userName: String, _password: String, _roles: Collection<GrantedAuthority>) : this() {
        this.username = _userName
        this.password = _password
//        this.roles = _roles
    }

    constructor(_userName: String, _password: String, _roles: List<String>) : this() {
        this.username = _userName
        this.password = _password
        this.roles = _roles
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    private var username: String? = null
    private var password: String? = null

    @ElementCollection(fetch = FetchType.EAGER)
    var roles: List<String> = ArrayList()

    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return roles.stream().map { role: String? ->
            SimpleGrantedAuthority(role)
        }.collect(Collectors.toList())
    }

    fun getUserName() {
        this.username
    }

    override fun getPassword(): String? {
        return password
    }

    override fun getUsername(): String? {
        return username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}

interface SystemUserRepository : PagingAndSortingRepository<SystemUser, Long> {
    fun findByUsername(userName: String): Optional<SystemUser>
}