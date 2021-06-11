package com.hrandika.seedling.spring.modules.sales_order_charge

import com.hrandika.seedling.spring.core.common.base_entity.BaseEntity
import com.hrandika.seedling.spring.core.common.price.Price
import com.hrandika.seedling.spring.modules.sales_charge_type.SalesChargeType
import com.hrandika.seedling.spring.modules.sales_order.SalesOrder
import org.springframework.data.repository.PagingAndSortingRepository
import org.hibernate.annotations.Type
import javax.persistence.*

@Entity
@Table(name = "sales_order_charge", uniqueConstraints = [UniqueConstraint(columnNames = ["line_number", "sales_order_id"])])
data class SalesOrderCharge(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long? = null,

    @Column(name = "line_number", nullable = false)
    var lineNumber: Long? = null,

    var description: String? = null,

    @Embedded
    @Column(nullable = false)
    var amount: Price? = null,

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    var type: SalesChargeType? = null,

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    var salesOrder: SalesOrder? = null

) : BaseEntity()

interface SalesOrderChargeRepository : PagingAndSortingRepository<SalesOrderCharge, Long>
