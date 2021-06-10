package com.hrandika.seedling.spring.modules.customer_payment

import com.hrandika.seedling.spring.core.common.price.Price
import org.springframework.data.rest.core.config.Projection

@Projection(name = "product", types = [CustomerPayment::class])
interface CustomerPaymentProductProjection {
    fun getLineNumber(): Long
    fun getAmount(): Price
    fun getNote(): String
}