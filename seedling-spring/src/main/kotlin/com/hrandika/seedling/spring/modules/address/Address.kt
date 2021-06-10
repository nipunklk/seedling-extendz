package com.hrandika.seedling.spring.modules.address

import com.hrandika.seedling.spring.core.common.base_entity.BaseEntity
import com.hrandika.seedling.spring.utils.StringSequenceStyleGenerator
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Parameter
import org.springframework.data.repository.PagingAndSortingRepository
import javax.persistence.*

@Entity
@Table(name = "address")
data class Address(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "add_seq")
    @GenericGenerator(
        name = "add_seq",
        parameters = [
            Parameter(name = StringSequenceStyleGenerator.VALUE_PREFIX_PARAMETER, value = "ADD"),
            Parameter(name = StringSequenceStyleGenerator.NUMBER_FORMAT_PARAMETER, value = "-%05d"),
        ],
        strategy = "com.hrandika.seedling.spring.utils.StringSequenceStyleGenerator"
    )
    var id: String? = null,

    @Column(name = "line_1", nullable = false)
    var line1: String? = null,

    @Column(name = "line_2")
    var line2: String? = null,

    @Column(nullable = false)
    var city: String? = null,

    var delivery: Boolean = false,
    var billing: Boolean = false

) : BaseEntity()


interface AddressRepository : PagingAndSortingRepository<Address, String>
