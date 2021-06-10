package com.hrandika.seedling.spring.modules.address

import org.springframework.data.rest.core.config.Projection

@Projection(name = "city", types = [Address::class])
interface AddressCityProjection {
    fun getLine1(): String
    fun getLine2(): String
    fun getCity(): String
}