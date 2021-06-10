package com.hrandika.seedling.spring.modules.delivery_order_line

import com.hrandika.seedling.spring.core.common.base_entity.BaseEntity
import com.hrandika.seedling.spring.modules.delivery_order.DeliveryOrder
import com.hrandika.seedling.spring.modules.sales_order_line.SalesOrderLine
import org.springframework.data.repository.PagingAndSortingRepository
import javax.persistence.*

@Entity
@Table(
    name = "delivery_order_line",
    uniqueConstraints = [UniqueConstraint(columnNames = ["line_number  ", "delivery_order_id"])]
)
data class DeliveryOrderLine(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long? = null,

    @Column(name = "line_number", nullable = false)
    var lineNumber: Long? = null,

    @Column(nullable = false)
    var quantity: Long? = null,

    @ManyToOne(optional = false)
    var salesOrderLine: SalesOrderLine? = null,

    @ManyToOne(optional = false)
    var deliveryOrder: DeliveryOrder? = null

) : BaseEntity()

interface DeliveryOrderLineRepository : PagingAndSortingRepository<DeliveryOrderLine, Long>
