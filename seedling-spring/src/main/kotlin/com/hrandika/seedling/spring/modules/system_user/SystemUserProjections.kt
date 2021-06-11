package com.hrandika.seedling.spring.modules.system_user

import org.springframework.data.rest.core.config.Projection

@Projection(name = "dataTable", types = [SystemUser::class])
interface ProductProjections {
    fun getUsername()
}
