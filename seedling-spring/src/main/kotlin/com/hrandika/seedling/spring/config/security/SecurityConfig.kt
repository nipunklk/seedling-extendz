package com.hrandika.seedling.spring.config.security

import com.hrandika.seedling.spring.modules.system_user.SystemUserRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import java.lang.String.format


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig(var jwtTokenProvider: JwtTokenProvider) :
    WebSecurityConfigurerAdapter() {

    @Value("\${spring.data.rest.base-path}")
    private val basePath: String? = null

    override fun configure(webSecurity: WebSecurity) {
        webSecurity.ignoring()
            .antMatchers(HttpMethod.POST, format("%s/signin", basePath))
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
            .httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .exceptionHandling(Customizer {
                it.authenticationEntryPoint(HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
            })
            .cors().and()
            .authorizeRequests(Customizer { r ->
                r
                    .antMatchers("/**").permitAll()
                    .antMatchers(HttpMethod.GET, basePath).authenticated()
                    .anyRequest().authenticated()
            }).addFilterBefore(
                JwtTokenAuthenticationFilter(jwtTokenProvider),
                UsernamePasswordAuthenticationFilter::class.java
            )
    }

    @Bean
    protected fun corsConfigurationSource(): CorsConfigurationSource? {
        val configuration = CorsConfiguration()
        configuration.addAllowedOrigin("*")
        configuration.addAllowedMethod("*")
        configuration.addAllowedHeader("*")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

    @Bean
    fun customUserDetailsService(users: SystemUserRepository): UserDetailsService? {
        return UserDetailsService { username: String ->
            users.findByUsername(username)
                .orElseThrow { UsernameNotFoundException("Username: $username not found") }
        }
    }

    @Bean
    fun customAuthenticationManager(
        userDetailsService: UserDetailsService,
        encoder: PasswordEncoder
    ): AuthenticationManager? {
        return AuthenticationManager { authentication: Authentication ->
            val username = authentication.principal.toString() + ""
            val password = authentication.credentials.toString() + ""
            val user = userDetailsService.loadUserByUsername(username)
            if (!encoder.matches(password, user.password)) {
                throw BadCredentialsException("Bad credentials")
            }
            if (!user.isEnabled) {
                throw DisabledException("User account is not active")
            }
            UsernamePasswordAuthenticationToken(username, null, user.authorities)
        }
    }



}