package com.hrandika.seedling.spring.modules.sales_order_line

import com.hrandika.seedling.spring.core.common.base_entity.BaseEntity
import com.hrandika.seedling.spring.core.common.price.Price
import com.hrandika.seedling.spring.modules.delivery_order_line.DeliveryOrderLine
import com.hrandika.seedling.spring.modules.product.Product
import com.hrandika.seedling.spring.modules.sales_order.SalesOrder
import org.springframework.data.repository.PagingAndSortingRepository
import javax.persistence.*

@Entity
@Table(
    name = "sales_order_line",
    uniqueConstraints = [UniqueConstraint(columnNames = ["line_number", "sales_order_id"])]
)
data class SalesOrderLine(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long? = null,

    @Column(name = "line_number", nullable = false)
    var lineNumber: Int? = null,

    @Column(nullable = false)
    var quantity: Long? = null,

    @Column(name = "delivered_quantity", nullable = false)
    var deliveredQuantity: Long? = null,

    @Embedded
    var price: Price? = null,

    @Column(name = "order_line_status", nullable = false)
    var status: OrderLineStatus? = null,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    var product: Product? = null,

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = SalesOrder::class)
    var salesOrder: SalesOrder? = null,

    @OneToMany(mappedBy = "salesOrderLine")
    var deliveryOrderLines: List<DeliveryOrderLine>? = null

) : BaseEntity()


enum class OrderLineStatus {
    Planned,
    Released,
    Reserved,
    PartiallyDelivered,
    Delivered
}

interface SalesOrderLineRepository : PagingAndSortingRepository<SalesOrderLine, Long>
