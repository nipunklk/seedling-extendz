package com.hrandika.seedling.spring.modules.purchase_order

import com.hrandika.seedling.spring.core.common.base_entity.BaseEntity
import com.hrandika.seedling.spring.modules.purchase_order_line.PurchaseOrderLine
import com.hrandika.seedling.spring.modules.supplier.Supplier
import com.hrandika.seedling.spring.utils.StringSequenceStyleGenerator
import com.vladmihalcea.hibernate.type.json.JsonBinaryType
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Parameter
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import org.hibernate.annotations.TypeDefs
import javax.persistence.*

@Entity
@Table(name = "purchase_order")
@TypeDefs(TypeDef(name = "jsonb", typeClass = JsonBinaryType::class))
data class PurchaseOrder(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "add_seq")
    @GenericGenerator(
        name = "add_seq",
        parameters = [
            Parameter(name = StringSequenceStyleGenerator.VALUE_PREFIX_PARAMETER, value = "PAO"),
            Parameter(name = StringSequenceStyleGenerator.NUMBER_FORMAT_PARAMETER, value = "-%05d"),
        ],
        strategy = "com.hrandika.seedling.spring.utils.StringSequenceStyleGenerator"
    )
    var id: String? = null,

    var note: String? = null,

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    var supplier: Supplier? = null,

    @OneToMany(fetch = FetchType.EAGER)
    var purchaseOrderLine: List<PurchaseOrderLine>? = null

) : BaseEntity()

@Repository
interface PurchaseOrderRepository : PagingAndSortingRepository<PurchaseOrder, String>
