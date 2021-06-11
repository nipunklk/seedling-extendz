package com.hrandika.seedling.spring.modules.category

import com.hrandika.seedling.spring.core.common.base_entity.BaseEntity
import com.hrandika.seedling.spring.modules.product.Product
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
@Table(name = "category")
data class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "add_seq")
    @GenericGenerator(
        name = "add_seq",
        parameters = [
            Parameter(name = StringSequenceStyleGenerator.VALUE_PREFIX_PARAMETER, value = "CAT"),
            Parameter(name = StringSequenceStyleGenerator.NUMBER_FORMAT_PARAMETER, value = "-%05d"),
        ],
        strategy = "com.hrandika.seedling.spring.utils.StringSequenceStyleGenerator"
    )
    var id: String? = null,

    @Column(nullable = false)
    var name: String? = null,

    var description: String? = null,

    var active: Boolean? = true,

    @OneToMany(fetch = FetchType.LAZY)
    var products: List<Product>? = null

) : BaseEntity()

@Repository
interface CategoryRepository : PagingAndSortingRepository<Category, String>
