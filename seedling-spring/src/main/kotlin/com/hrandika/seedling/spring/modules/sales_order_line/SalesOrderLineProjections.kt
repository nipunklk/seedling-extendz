package com.hrandika.seedling.spring.modules.sales_order_line

import com.hrandika.seedling.spring.core.common.price.Price
import com.hrandika.seedling.spring.modules.product.ProductNameProjection
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.rest.core.config.Projection

@Projection(name = "product", types = [SalesOrderLine::class])
interface SalesOrderLineProductProjection {
    fun getLineNumber(): Int
    fun getProduct(): ProductNameProjection
    fun getQuantity(): Long
    fun getDeliveredQuantity(): Long
    fun getPrice(): Price
    fun getStatus(): OrderLineStatus
}

@Projection(name = "dataTable", types = [SalesOrderLine::class])
interface SalesOrderLineDataTableProjection {
    fun getLineNumber(): Long
    fun getQuantity(): Long

    @Value("#{target.salesOrder == null ? '': target.salesOrder.id }")
    fun getSalesOrder(): String

    @Value("#{target.product == null ? '': target.product.name }")
    fun getProduct(): String
}