package com.hrandika.seedling.spring.modules.team

import org.springframework.data.rest.core.config.Projection

@Projection(name = "name", types = [Team::class])
interface TeamNameProjection {
    fun getName();
}