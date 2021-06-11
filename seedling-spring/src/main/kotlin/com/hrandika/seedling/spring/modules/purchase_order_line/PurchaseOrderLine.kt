package com.hrandika.seedling.spring.modules.purchase_order_line

import com.hrandika.seedling.spring.core.common.base_entity.BaseEntity
import com.hrandika.seedling.spring.core.common.price.Price
import com.hrandika.seedling.spring.modules.product.Product
import com.hrandika.seedling.spring.modules.purchase_order.PurchaseOrder
import org.springframework.data.repository.PagingAndSortingRepository
import org.hibernate.annotations.Type
import javax.persistence.*

@Entity
@Table(name = "purchase_order_line", uniqueConstraints = [UniqueConstraint(columnNames = ["line_no", "purchase_order_id"])])
data class PurchaseOrderLine(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long? = null,

    @Column(name = "line_no", nullable = false)
    var lineNo: Long? = null,

    @Column(nullable = false)
    var quantity: Long? = null,

    @Embedded
    @Column(nullable = false)
    var price: Price? = null,

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    var product: Product? = null,

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    var purchaseOrder: PurchaseOrder? = null

) : BaseEntity()

interface PurchaseOrderLineRepository : PagingAndSortingRepository<PurchaseOrderLine, Long>
