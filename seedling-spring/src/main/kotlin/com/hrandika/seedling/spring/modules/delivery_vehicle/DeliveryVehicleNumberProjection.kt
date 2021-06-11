package com.hrandika.seedling.spring.modules.delivery_vehicle

import org.springframework.data.rest.core.config.Projection

@Projection(name = "number", types = [DeliveryVehicle::class])
interface DeliveryVehicleRegisterNumberProjection {
    fun getRegisterNumber(): String
}