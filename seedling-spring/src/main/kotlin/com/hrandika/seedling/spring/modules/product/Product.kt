package com.hrandika.seedling.spring.modules.product

import com.hrandika.seedling.spring.core.common.base_entity.BaseEntity
import com.hrandika.seedling.spring.core.common.price.Price
import com.hrandika.seedling.spring.core.common.weight.Weight
import com.hrandika.seedling.spring.modules.batch.Batch
import com.hrandika.seedling.spring.modules.category.Category
import com.hrandika.seedling.spring.utils.StringSequenceStyleGenerator
import com.querydsl.core.types.dsl.StringExpression
import com.vladmihalcea.hibernate.type.json.JsonBinaryType
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Parameter
import org.hibernate.annotations.TypeDef
import org.hibernate.annotations.TypeDefs
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer
import org.springframework.data.querydsl.binding.QuerydslBindings
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "product")
@TypeDefs(TypeDef(name = "jsonb", typeClass = JsonBinaryType::class))
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "add_seq")
    @GenericGenerator(
        name = "add_seq",
        parameters = [
            Parameter(name = StringSequenceStyleGenerator.VALUE_PREFIX_PARAMETER, value = "PRO"),
            Parameter(name = StringSequenceStyleGenerator.NUMBER_FORMAT_PARAMETER, value = "-%05d"),
        ],
        strategy = "com.hrandika.seedling.spring.utils.StringSequenceStyleGenerator"
    )
    var id: String? = null,

    @Column(nullable = false)
    var name: String? = null,

    @Column(name = "minimum_quantity")
    var minimumQuantity: Int? = 1,

    @Column(name = "expirable")
    var expirable: Boolean? = false,

    @Embedded
    @AttributeOverrides(
        AttributeOverride(name = "value", column = Column(name = "unit_price_value"))
    )
    @AssociationOverrides(
        AssociationOverride(name = "currency", joinColumns = [JoinColumn(name = "unit_price_currency_code")])
    )
    var unitPrice: Price? = null,

    @Embedded
    @AttributeOverrides(
        AttributeOverride(name = "value", column = Column(name = "cost_value"))
    )
    @AssociationOverrides(
        AssociationOverride(name = "currency", joinColumns = [JoinColumn(name = "cost_currency_code")])
    )
    var cost: Price? = null,

    @Embedded
    @AttributeOverrides(
        AttributeOverride(name = "value", column = Column(name = "weight_value"))
    )
    var weight: Weight? = null,

    @Column(name = "units_per_square_feet")
    var unitsPerSquareFeet: BigDecimal? = null,

    var note: String? = null,
    var image: String? = null,
    var active: Boolean? = true,
    var description: String? = null,

    @Column(name = "sales_part")
    var salesPart: Boolean? = false,

    @Column(name = "purchase_part")
    var purchasePart: Boolean? = false,

    @OneToOne(fetch = FetchType.EAGER)
    var category: Category? = null,

    @OneToMany
    var batches: List<Batch>? = null

) : BaseEntity()

@Repository
interface ProductRepository : PagingAndSortingRepository<Product, String>, QuerydslPredicateExecutor<Product>,
    QuerydslBinderCustomizer<QProduct> {

    @JvmDefault
    override fun customize(bindings: QuerydslBindings, root: QProduct) {
        bindings.bind(String::class.java)
            .first { path: StringExpression, value: String -> path.containsIgnoreCase(value) }
    }
}
