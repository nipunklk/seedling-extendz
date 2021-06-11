package com.hrandika.seedling.spring.modules.delivery_order

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.hrandika.seedling.spring.modules.delivery_order_line.DeliveryOrderLineDataProjection
import com.hrandika.seedling.spring.modules.delivery_vehicle.DeliveryVehicleRegisterNumberProjection
import org.springframework.data.rest.core.config.Projection
import java.time.OffsetDateTime

@Projection(name = "entity", types = [DeliveryOrder::class])
interface DeliveryOrderEntityProjection {
    fun getDispatchDate(): OffsetDateTime
    fun getVehicle(): DeliveryVehicleRegisterNumberProjection
    fun getNote(): String
    fun getStatus(): DeliveryStatus

    @JsonIgnoreProperties("deliveryOrder")
    fun getOrderLines(): List<DeliveryOrderLineDataProjection>
}