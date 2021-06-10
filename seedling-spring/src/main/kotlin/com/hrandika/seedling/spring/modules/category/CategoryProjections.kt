package com.hrandika.seedling.spring.modules.category

import org.springframework.data.rest.core.config.Projection

@Projection(name = "name", types = [Category::class])
interface CategoryNameProjection {
    fun getName(): String
}