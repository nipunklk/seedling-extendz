package com.hrandika.seedling.spring.config.security

import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.util.StringUtils
import org.springframework.web.filter.GenericFilterBean
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest


class JwtTokenAuthenticationFilter(var jwtTokenProvider: JwtTokenProvider) : GenericFilterBean() {

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(req: ServletRequest, res: ServletResponse, filterChain: FilterChain) {
        val token = resolveToken(req as HttpServletRequest)
//        log.info("Extracting token from HttpServletRequest: {}", token)
        if (token != null && jwtTokenProvider!!.validateToken(token)) {
            val auth: Authentication = jwtTokenProvider.getAuthentication(token)
            if (auth != null && auth !is AnonymousAuthenticationToken) {
                SecurityContextHolder.getContext().authentication = auth
            }
        }
        filterChain.doFilter(req, res)
    }

    private fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION)
        return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(HEADER_PREFIX)) {
            bearerToken.substring(7)
        } else null
    }

    companion object {
        const val HEADER_PREFIX = "Bearer "
    }
}