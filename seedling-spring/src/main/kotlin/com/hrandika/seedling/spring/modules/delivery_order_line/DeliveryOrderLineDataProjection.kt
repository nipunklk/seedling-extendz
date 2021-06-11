package com.hrandika.seedling.spring.modules.delivery_order_line

import com.hrandika.seedling.spring.modules.sales_order_line.SalesOrderLineDataTableProjection
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.rest.core.config.Projection
import java.time.OffsetDateTime

@Projection(name = "data", types = [DeliveryOrderLine::class])
interface DeliveryOrderLineDataProjection {
    fun getLineNumber(): Long

    fun getQuantity(): Long

    fun getSalesOrderLine(): SalesOrderLineDataTableProjection

}

@Projection(name = "sales-order", types = [DeliveryOrderLine::class])
interface DeliveryOrderLineSalesOrderProjection {
    @Value("#{target.lineNumber == null ? null: target.lineNumber }")
    fun getDeliveryLineNumber(): Long

    fun getQuantity(): Long

    @Value("#{target.deliveryOrder == null ? null: target.deliveryOrder.dispatchDate }")
    fun getDispatchDate(): OffsetDateTime

    @Value("#{target.deliveryOrder == null ? null: target.deliveryOrder.id }")
    fun getDeliveryOrder(): String
}