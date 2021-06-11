package com.hrandika.seedling.spring.modules.sales_charge_type

import com.hrandika.seedling.spring.core.common.price.Price
import com.hrandika.seedling.spring.modules.currency.Currency
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.rest.core.config.Projection

@Projection(name = "name", types = [SalesChargeType::class])
interface SalesChargeTypeNameProjection {
    fun getName(): String
}

@Projection(name = "dataTable", types = [SalesChargeType::class])
interface SalesChargeTypeDataTableProjection : SalesChargeTypeNameProjection {

    @Value("#{@priceService.getPrice(target.defaultAmount)}")
    fun getDefaultAmount(): String
}