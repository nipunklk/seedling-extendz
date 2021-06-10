package com.hrandika.seedling.spring.modules.sales_order_charge

import com.hrandika.seedling.spring.core.common.price.Price
import com.hrandika.seedling.spring.modules.sales_charge_type.SalesChargeTypeNameProjection
import org.springframework.data.rest.core.config.Projection

@Projection(name = "product", types = [SalesOrderCharge::class])
interface SalesOrderProductProjection {
    fun getLineNumber(): Long
    fun getDescription(): String
    fun getAmount(): Price
    fun getType(): SalesChargeTypeNameProjection
}