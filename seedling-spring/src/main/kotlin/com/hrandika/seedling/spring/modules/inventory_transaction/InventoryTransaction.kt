package com.hrandika.seedling.spring.modules.inventory_transaction

import com.hrandika.seedling.spring.core.common.base_entity.BaseEntity
import com.hrandika.seedling.spring.core.common.price.Price
import com.hrandika.seedling.spring.modules.product.Product
import com.hrandika.seedling.spring.utils.StringSequenceStyleGenerator
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Parameter
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import org.hibernate.annotations.Type
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@Table(name = "inventory_transaction")
data class InventoryTransaction(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "add_seq")
    @GenericGenerator(
        name = "add_seq",
        parameters = [
            Parameter(name = StringSequenceStyleGenerator.VALUE_PREFIX_PARAMETER, value = "TRA"),
            Parameter(name = StringSequenceStyleGenerator.NUMBER_FORMAT_PARAMETER, value = "-%05d"),
        ],
        strategy = "com.hrandika.seedling.spring.utils.StringSequenceStyleGenerator"
    )
    var id: String? = null,

    @Column(nullable = false)
    var reference: String? = null,

    @Column(name = "line_no", nullable = false)
    var lineNo: Long? = null,

    @Column(nullable = false)
    var quantity: Long? = null,

    @Column(name = "transaction_type", nullable = false)
    var transactionType: String? = null,

    @Column(name = "total_amount", nullable = false)
    val totalAmount: Price? = null,

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    var product: Product? = null

) : BaseEntity()

@Repository
interface InventoryTransactionRepository : PagingAndSortingRepository<InventoryTransaction, String>
