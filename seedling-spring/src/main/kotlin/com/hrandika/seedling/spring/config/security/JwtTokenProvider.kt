package com.hrandika.seedling.spring.config.security

import com.hrandika.seedling.spring.modules.system_user.SystemUser
import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.*
import java.util.stream.Collector
import java.util.stream.Collectors
import java.util.stream.Collectors.joining
import javax.annotation.PostConstruct
import javax.crypto.SecretKey


@Component
class JwtTokenProvider(val jwtProperties: JwtProperties) {
    private var secretKey: SecretKey? = null

    @PostConstruct
    fun init() {
        val secret = Base64.getEncoder().encodeToString(jwtProperties.secretKey.toByteArray())
        secretKey = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))
    }

    fun createToken(authentication: Authentication): String {
        val username = authentication.name
        val authorities = authentication.authorities
        val claims = Jwts.claims().setSubject(username)
        if (!authorities.isEmpty()) {
            claims[AUTHORITIES_KEY] = authorities.stream().map { obj: GrantedAuthority -> obj.authority }
                .collect(Collectors.toList())
        }
        val now = Date()
        val validity = Date(now.time + jwtProperties.validityInMs)
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact()
    }

    fun getAuthentication(token: String?): Authentication {
        val claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).body
        val authoritiesClaim = claims[AUTHORITIES_KEY]
        val authorities: Collection<GrantedAuthority> =
            if (authoritiesClaim == null) AuthorityUtils.NO_AUTHORITIES else AuthorityUtils.commaSeparatedStringToAuthorityList(
                authoritiesClaim.toString()
            )
        val principal = SystemUser(claims.subject, "", authorities)
        return UsernamePasswordAuthenticationToken(principal, token, authorities)
    }

    fun validateToken(token: String?): Boolean {
        try {
            val claims = Jwts
                .parserBuilder().setSigningKey(secretKey).build()
                .parseClaimsJws(token)
            //  parseClaimsJws will check expiration date. No need do here.
//            log.info("expiration date: {}", claims.body.expiration)
            return true
        } catch (e: JwtException) {
//            log.error("Invalid JWT token: {}", e.message)
        } catch (e: IllegalArgumentException) {
//            log.error("Invalid JWT token: {}", e.message)
        }
        return false
    }

    companion object {
        private const val AUTHORITIES_KEY = "roles"
    }
}