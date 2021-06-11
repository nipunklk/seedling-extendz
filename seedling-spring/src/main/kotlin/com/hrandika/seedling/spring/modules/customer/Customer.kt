package com.hrandika.seedling.spring.modules.customer

import com.hrandika.seedling.spring.core.common.base_entity.BaseEntity
import com.hrandika.seedling.spring.core.common.phone.Phone
import com.hrandika.seedling.spring.modules.address.Address
import com.hrandika.seedling.spring.utils.StringSequenceStyleGenerator
import com.querydsl.core.types.dsl.StringExpression
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Parameter
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer
import org.springframework.data.querydsl.binding.QuerydslBindings
import org.springframework.data.repository.PagingAndSortingRepository
import javax.persistence.*

@Entity
@Table(name = "customer")
data class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @GenericGenerator(
        name = "customer_seq",
        parameters = [
            Parameter(name = StringSequenceStyleGenerator.VALUE_PREFIX_PARAMETER, value = "CUS"),
            Parameter(name = StringSequenceStyleGenerator.NUMBER_FORMAT_PARAMETER, value = "-%05d"),
        ],
        strategy = "com.hrandika.seedling.spring.utils.StringSequenceStyleGenerator"
    )
    var id: String? = null,

    @Embedded
    var telephone: Phone? = null,

    @Embedded
    @AttributeOverrides(
        AttributeOverride(name = "number", column = Column(name = "mobile_number")),
        AttributeOverride(name = "country", column = Column(name = "mobile_country"))
    )
    var mobile: Phone? = null,

    var email: String? = null,

    var active: Boolean? = true,

    @Column(name = "first_name", nullable = false)
    var firstName: String? = null,

    @Column(name = "last_name", nullable = false)
    var lastName: String? = null,

    @OneToMany(fetch = FetchType.EAGER)
    var addresses: List<Address>? = null

) : BaseEntity()

interface CustomerRepository : PagingAndSortingRepository<Customer, String>, QuerydslPredicateExecutor<Customer>,
    QuerydslBinderCustomizer<QCustomer> {

    @JvmDefault
    override fun customize(bindings: QuerydslBindings, root: QCustomer) {
        bindings.bind(String::class.java)
            .first { path: StringExpression, value: String -> path.containsIgnoreCase(value) }
    }
}
