package com.hrandika.seedling.spring.modules.supplier

import com.hrandika.seedling.spring.core.common.base_entity.BaseEntity
import com.hrandika.seedling.spring.modules.address.Address
import com.hrandika.seedling.spring.utils.StringSequenceStyleGenerator
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Parameter
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import org.hibernate.annotations.Type
import javax.persistence.*

@Entity
@Table(name = "supplier")
data class Supplier(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "add_seq")
    @GenericGenerator(
        name = "add_seq",
        parameters = [
            Parameter(name = StringSequenceStyleGenerator.VALUE_PREFIX_PARAMETER, value = "SUP"),
            Parameter(name = StringSequenceStyleGenerator.NUMBER_FORMAT_PARAMETER, value = "-%05d"),
        ],
        strategy = "com.hrandika.seedling.spring.utils.StringSequenceStyleGenerator"
    )
    var id: String? = null,

    @Column(nullable = false)
    var name: String? = null,

    var telephone: String? = null,
    var email: String? = null,
    var active: Boolean? = true,

    @OneToMany(fetch = FetchType.LAZY)
    var addresses : List<Address>? = null

) : BaseEntity()

@Repository
interface SupplierRepository : PagingAndSortingRepository<Supplier, String>
