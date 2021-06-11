package com.hrandika.seedling.spring.modules.sales_charge_type

import com.hrandika.seedling.spring.core.common.base_entity.BaseEntity
import com.hrandika.seedling.spring.core.common.price.Price
import com.hrandika.seedling.spring.utils.StringSequenceStyleGenerator
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Parameter
import org.springframework.data.repository.PagingAndSortingRepository
import org.hibernate.annotations.Type
import javax.persistence.*

@Entity
@Table(name = "sales_charge_type")
data class SalesChargeType(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "add_seq")
    @GenericGenerator(
        name = "add_seq",
        parameters = [
            Parameter(name = StringSequenceStyleGenerator.VALUE_PREFIX_PARAMETER, value = "SAC"),
            Parameter(name = StringSequenceStyleGenerator.NUMBER_FORMAT_PARAMETER, value = "-%05d"),
        ],
        strategy = "com.hrandika.seedling.spring.utils.StringSequenceStyleGenerator"
    )
    var id: String? = null,

    @Column(nullable = false)
    var name: String? = null,

    @Column(name = "default_amount", nullable = false)
    var defaultAmount: Price? = null,

    var active: Boolean? = null

) : BaseEntity()

interface SalesChargeTypeRepository : PagingAndSortingRepository<SalesChargeType, String>
