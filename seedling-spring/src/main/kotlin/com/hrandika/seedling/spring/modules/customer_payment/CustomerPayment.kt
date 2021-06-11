package com.hrandika.seedling.spring.modules.customer_payment

import com.hrandika.seedling.spring.core.common.base_entity.BaseEntity
import com.hrandika.seedling.spring.core.common.price.Price
import com.hrandika.seedling.spring.modules.sales_order.SalesOrder
import org.springframework.data.repository.PagingAndSortingRepository
import org.hibernate.annotations.Type
import javax.persistence.*

@Entity
@Table(
    name = "customer_payment",
    uniqueConstraints = [UniqueConstraint(columnNames = ["line_number", "sales_order_id"])]
)
data class CustomerPayment(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long? = null,

    var note: String? = null,
    var description: String? = null,

    @Column(name = "line_number", nullable = false)
    var lineNumber: Long? = null,

    @Embedded
    @Column(nullable = false)
    var amount: Price? = null,

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    var salesOrder: SalesOrder? = null

) : BaseEntity()

interface CustomerPaymentRepository : PagingAndSortingRepository<CustomerPayment, Long>
