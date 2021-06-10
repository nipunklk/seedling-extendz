package com.hrandika.seedling.spring.modules.customer

import com.hrandika.seedling.spring.core.common.phone.Phone
import com.hrandika.seedling.spring.modules.address.AddressCityProjection
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.rest.core.config.Projection

@Projection(name = "name", types = [Customer::class])
interface CustomerNameProjection {
    fun getFirstName(): String
    fun getLastName(): String
}

@Projection(name = "dataTable", types = [Customer::class])
interface CustomerDataTableProjection : CustomerNameProjection {

    fun getId(): String

    @Value("#{@phoneService.getFormattedNumber(target.telephone)}")
    fun getTelephone(): Any

    @Value("#{@phoneService.getFormattedNumber(target.mobile)}")
    fun getMobile(): Any

    fun getEmail(): String
}

@Projection(name = "entity", types = [Customer::class])
interface CustomerEntityProjection : CustomerNameProjection {
    fun getId(): String
    fun getActive(): Boolean
    fun getAddresses(): List<AddressCityProjection>
    fun getTelephone(): Phone
    fun getMobile(): Phone
    fun getEmail(): String
}