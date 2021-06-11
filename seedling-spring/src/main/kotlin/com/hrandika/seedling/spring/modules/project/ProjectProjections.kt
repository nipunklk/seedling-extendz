package com.hrandika.seedling.spring.modules.project

import com.hrandika.seedling.spring.core.common.price.Price
import com.hrandika.seedling.spring.modules.sales_order.SalesOrderLinkProjection
import com.hrandika.seedling.spring.modules.sales_order_line.SalesOrderLineProductProjection
import com.hrandika.seedling.spring.modules.team.TeamNameProjection
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.rest.core.config.Projection
import java.math.BigDecimal

@Projection(name = "id", types = [Project::class])
interface ProjectIdProjection {
    fun getId(): String
}

@Projection(name = "entity", types = [Project::class])
interface ProjectEntityProjection : ProjectIdProjection {

    @Value("#{@salesRepresentativeService.getFullName(target.salesOrder.salesRepresentative)}")
    fun getSalesOrderRef(): String

    @Value("#{target.salesOrder.customer == null ? '': target.salesOrder.customer.firstName +' '+ target.salesOrder.customer.lastName  }")
    fun getCustomer(): String

    @Value("#{@salesOrderService.getDeliveryAddress(target.salesOrder)}")
    fun getAddress(): String

    fun getTeam(): TeamNameProjection

    fun getSalesOrder(): SalesOrderLinkProjection

    @Value("#{target.salesOrder == null ? null : target.salesOrder.orderLines}")
    fun getOrderLines(): List<SalesOrderLineProductProjection>

    fun getUnitPrice(): Price
    fun getProjectRevenue(): Price
    fun getNote(): String
    fun getStatus(): ProjectStatus
    fun getMeasureSquareFeet(): BigDecimal
    fun getCalculatedSquareFeet(): BigDecimal
}