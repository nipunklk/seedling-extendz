package com.hrandika.seedling.spring.config.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.security.core.context.SecurityContextHolder
import java.util.*

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
class JpaAuditingConfiguration {
    @Bean
    fun auditorProvider(): AuditorAware<String> {
        return CustomAuditorAware()
    }
}

private class CustomAuditorAware : AuditorAware<String> {
    override fun getCurrentAuditor(): Optional<String> {
        return if (SecurityContextHolder.getContext().authentication != null) {
            val context = SecurityContextHolder.getContext().authentication
            Optional.of("Unknown")
        } else {
            Optional.of("Unknown")
        }
    }
}