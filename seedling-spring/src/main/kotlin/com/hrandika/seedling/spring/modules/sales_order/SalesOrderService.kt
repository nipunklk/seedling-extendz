package com.hrandika.seedling.spring.modules.sales_order

import com.hrandika.seedling.spring.modules.delivery_order_line.DeliveryOrderLine
import org.springframework.stereotype.Service

@Service
class SalesOrderService {

    fun getDeliveries(salesOrder: SalesOrder): MutableList<DeliveryOrderLine> {
        var dels = mutableListOf<DeliveryOrderLine>()
        salesOrder.orderLines?.stream()?.forEach {
            it.deliveryOrderLines
            it.deliveryOrderLines?.let { it1 -> dels.addAll(it1) }
        }
        return dels
    }

    fun getDeliveryAddress(salesOrder: SalesOrder): String {
        val customer = salesOrder.customer
        if (customer != null && customer.addresses!!.isNotEmpty()) {
            val a = customer.addresses?.filter { address -> address.delivery }?.get(0)
            print(a.toString())
            return listOfNotNull(a!!.line1, a?.line2, a?.city).joinToString(",")
        }
        return ""
    }
}