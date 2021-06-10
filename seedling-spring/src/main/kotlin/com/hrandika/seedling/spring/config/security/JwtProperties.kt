package com.hrandika.seedling.spring.config.security

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "jwt")
data class JwtProperties(
    open val secretKey: String = "rzxlszyykpbgqcflzxsqcysyhljt",
    val validityInMs: Long = 3600000 // 1h
)