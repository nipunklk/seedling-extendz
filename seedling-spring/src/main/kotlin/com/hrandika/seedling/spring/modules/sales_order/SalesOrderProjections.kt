package com.hrandika.seedling.spring.modules.sales_order

import com.hrandika.seedling.spring.core.common.price.Price
import com.hrandika.seedling.spring.modules.customer.CustomerNameProjection
import com.hrandika.seedling.spring.modules.customer_payment.CustomerPaymentProductProjection
import com.hrandika.seedling.spring.modules.delivery_order_line.DeliveryOrderLineSalesOrderProjection
import com.hrandika.seedling.spring.modules.project.ProjectIdProjection
import com.hrandika.seedling.spring.modules.sales_order_charge.SalesOrderProductProjection
import com.hrandika.seedling.spring.modules.sales_order_line.SalesOrderLineProductProjection
import com.hrandika.seedling.spring.modules.sales_representative.SalesRepresentativeNameProjection
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.rest.core.config.Projection
import java.time.Instant
import java.time.OffsetDateTime

@Projection(name = "link", types = [SalesOrder::class])
interface SalesOrderLinkProjection {}

@Projection(name = "entity", types = [SalesOrder::class])
interface SalesEntityOrderProjections {
    fun getId(): String
    fun getCustomer(): CustomerNameProjection

    @Value("#{@salesOrderService.getDeliveryAddress(target)}")
    fun getDeliveryAddress(): String

    fun getWantedDeliveryDate(): OffsetDateTime
    fun getSalesRepresentative(): SalesRepresentativeNameProjection
    fun getDiscountType(): DiscountType
    fun getDiscount(): Price

    fun getTotalAmount(): Price
    fun getTotalPaid(): Price
    fun getBalancePayment(): Price
    fun getCreatedDate(): Instant

    fun getOrderLines(): List<SalesOrderLineProductProjection>
    fun getCharges(): List<SalesOrderProductProjection>
    fun getPayments(): List<CustomerPaymentProductProjection>

    @Value("#{@salesOrderService.getDeliveries(target)}")
    fun getDeliveries(): List<DeliveryOrderLineSalesOrderProjection>

    fun getProject(): ProjectIdProjection
    fun getStatus(): OrderStatus

}

@Projection(name = "dataTable", types = [SalesOrder::class])
interface SalesOrderDataTableProjections {
    fun getId(): String

    @Value("#{target.customer == null ? '' : target.customer.firstName+' '+target.customer.lastName }")
    fun getCustomer(): String

    fun getWantedDeliveryDate(): OffsetDateTime
    fun getStatus(): OrderStatus

}