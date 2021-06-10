package com.hrandika.seedling.spring.modules.sales_representative

import com.hrandika.seedling.spring.core.common.base_entity.BaseEntity
import com.hrandika.seedling.spring.core.common.phone.Phone
import com.hrandika.seedling.spring.utils.StringSequenceStyleGenerator
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Parameter
import org.springframework.data.repository.PagingAndSortingRepository
import org.hibernate.annotations.Type
import javax.persistence.*

@Entity
@Table(name = "sales_representative")
data class SalesRepresentative(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "add_seq")
    @GenericGenerator(
        name = "add_seq",
        parameters = [
            Parameter(name = StringSequenceStyleGenerator.VALUE_PREFIX_PARAMETER, value = "SAR"),
            Parameter(name = StringSequenceStyleGenerator.NUMBER_FORMAT_PARAMETER, value = "-%05d"),
        ],
        strategy = "com.hrandika.seedling.spring.utils.StringSequenceStyleGenerator"
    )
    var id: String? = null,

    @Column(name = "first_name", nullable = false)
    var firstName: String? = null,

    @Column(name = "last_name", nullable = false)
    var lastName: String? = null,

    @Embedded
    var telephone: Phone? = null,

    @Embedded
    @AttributeOverrides(
        AttributeOverride(name = "number", column = Column(name = "mobile_number")),
        AttributeOverride(name = "country", column = Column(name = "mobile_country"))
    )
    var mobile: Phone? = null,
    var email: String? = null,

    @Column(nullable = false)
    var nic: String? = null,
    var active: Boolean? = true,
    var nicFront: String? = null,
    var nicBack: String? = null

) : BaseEntity()

interface SalesRepresentativeRepository : PagingAndSortingRepository<SalesRepresentative, String>
