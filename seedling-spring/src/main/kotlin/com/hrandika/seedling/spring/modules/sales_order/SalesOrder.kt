package com.hrandika.seedling.spring.modules.sales_order

import com.hrandika.seedling.spring.core.common.base_entity.BaseEntity
import com.hrandika.seedling.spring.core.common.price.Price
import com.hrandika.seedling.spring.modules.customer.Customer
import com.hrandika.seedling.spring.modules.customer_payment.CustomerPayment
import com.hrandika.seedling.spring.modules.project.Project
import com.hrandika.seedling.spring.modules.sales_order_charge.SalesOrderCharge
import com.hrandika.seedling.spring.modules.sales_order_line.SalesOrderLine
import com.hrandika.seedling.spring.modules.sales_representative.SalesRepresentative
import com.hrandika.seedling.spring.utils.StringSequenceStyleGenerator
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Parameter
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@Table(name = "sales_order")
data class SalesOrder(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "add_seq")
    @GenericGenerator(
        name = "add_seq",
        parameters = [
            Parameter(name = StringSequenceStyleGenerator.VALUE_PREFIX_PARAMETER, value = "SAL"),
            Parameter(name = StringSequenceStyleGenerator.NUMBER_FORMAT_PARAMETER, value = "-%05d"),
        ],
        strategy = "com.hrandika.seedling.spring.utils.StringSequenceStyleGenerator"
    )
    var id: String? = null,

    var note: String? = null,

    @Enumerated(EnumType.STRING)
    var status: OrderStatus? = OrderStatus.Planned,

    @Column(name = "wanted_delivery_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    var wantedDeliveryDate: OffsetDateTime? = null,

    @Embedded
    @AttributeOverrides(AttributeOverride(name = "value", column = Column(name = "total_amount_value")))
    @AssociationOverrides(
        AssociationOverride(
            name = "currency",
            joinColumns = [JoinColumn(name = "total_amount_currency")]
        )
    )
    var totalAmount: Price? = null,

    @Embedded
    @AttributeOverrides(AttributeOverride(name = "value", column = Column(name = "total_paid_value")))
    @AssociationOverrides(
        AssociationOverride(
            name = "currency",
            joinColumns = [JoinColumn(name = "total_paid_currency")]
        )
    )
    var totalPaid: Price? = null,

    @Embedded
    @AttributeOverrides(AttributeOverride(name = "value", column = Column(name = "discount_value")))
    @AssociationOverrides(
        AssociationOverride(
            name = "currency",
            joinColumns = [JoinColumn(name = "discount_currency")]
        )
    )
    var discount: Price? = null,

    @Embedded
    @AttributeOverrides(AttributeOverride(name = "value", column = Column(name = "balance_payment_value")))
    @AssociationOverrides(
        AssociationOverride(
            name = "currency",
            joinColumns = [JoinColumn(name = "balance_payment_currency")]
        )
    )
    var balancePayment: Price? = null,

    @Column(name = "discount_type")
    val discountType: DiscountType? = null,

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    var customer: Customer? = null,

    @OneToOne(fetch = FetchType.LAZY)
    var salesRepresentative: SalesRepresentative? = null,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "salesOrder")
    var orderLines: List<SalesOrderLine>? = null,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "salesOrder")
    var charges: List<SalesOrderCharge>? = null,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "salesOrder")
    var payments: List<CustomerPayment>? = null,

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "salesOrder")
    var project: Project? = null,

    ) : BaseEntity()

enum class DiscountType {
    Price,
    Percentage
}

enum class OrderStatus {
    Planned,
    Released,
    PartiallyReserved,
    Reserved,
    PartiallyDelivered,
    Delivered,
    PaidClosed,
    Cancelled
}

@Repository
interface SalesOrderRepository : PagingAndSortingRepository<SalesOrder, String>
