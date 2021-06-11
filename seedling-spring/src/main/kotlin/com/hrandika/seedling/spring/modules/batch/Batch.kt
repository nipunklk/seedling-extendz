package com.hrandika.seedling.spring.modules.batch

import com.hrandika.seedling.spring.core.common.base_entity.BaseEntity
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
@Table(name = "batch")
data class Batch(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "add_seq")
    @GenericGenerator(
        name = "add_seq",
        parameters = [
            Parameter(name = StringSequenceStyleGenerator.VALUE_PREFIX_PARAMETER, value = "BAT"),
            Parameter(name = StringSequenceStyleGenerator.NUMBER_FORMAT_PARAMETER, value = "-%05d"),
        ],
        strategy = "com.hrandika.seedling.spring.utils.StringSequenceStyleGenerator"
    )
    var id: String? = null,

    var note: String? = null,

    @Column(name = "initial_quantity", nullable = false)
    var initialQuantity: Long? = null,

    @Column(name = "on_hand_quantity", nullable = false)
    var onHandQuantity: Long? = 0,

    @Column(name = "reserved_quantity", nullable = false)
    var reservedQuantity: Long? = 0,

    @Column(name = "arrived_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    val arrivedDate: OffsetDateTime? = null,

    @Column(name = "expiry_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    val expiryDate: OffsetDateTime? = null,

    @Column(name = "manufactured_date", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    val manufacturedDate: OffsetDateTime? = null,

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    var product: Product? = null

) : BaseEntity()

@Repository
interface BatchRepository : PagingAndSortingRepository<Batch, String>
