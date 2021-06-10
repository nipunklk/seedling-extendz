package com.hrandika.seedling.spring.modules.sales_representative

import org.springframework.beans.factory.annotation.Value
import org.springframework.data.rest.core.config.Projection


@Projection(name = "name", types = [SalesRepresentative::class])
interface SalesRepresentativeNameProjection {
    fun getFirstName(): String
    fun getLastName(): String
}

@Projection(name = "dataTable", types = [SalesRepresentative::class])
interface SalesRepresentativeDataTableProjection : SalesRepresentativeNameProjection {

    @Value("#{@phoneService.getFormattedNumber(target.telephone)}")
    fun getAlternativeContactNumber(): Any

    @Value("#{@phoneService.getFormattedNumber(target.mobile)}")
    fun getMobile(): Any

    fun getEmail(): String
}