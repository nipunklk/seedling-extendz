package com.hrandika.seedling.spring.modules.batch


import org.springframework.beans.factory.annotation.Value
import org.springframework.data.rest.core.config.Projection
import java.time.OffsetDateTime

@Projection(name = "dataTable", types = [Batch::class])
interface BatchDataTableProjection {


    @Value("#{target.product == null ? '': target.product.name }")
    fun getProduct(): String

    fun getId(): String
    fun getInitialQuantity(): Long
    fun getManufacturedDate(): OffsetDateTime
    fun getNote(): String
}