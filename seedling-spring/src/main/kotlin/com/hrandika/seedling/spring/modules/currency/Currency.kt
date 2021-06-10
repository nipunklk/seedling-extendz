package com.hrandika.seedling.spring.modules.currency

import com.hrandika.seedling.spring.core.common.base_entity.BaseEntity
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.rest.core.config.Projection
import javax.persistence.*

@Entity
@Table(name = "currency")
data class Currency(
    @Id
    var code: String?,

    var name: String?,

) : BaseEntity()

interface CurrencyRepository : PagingAndSortingRepository<Currency, String>

@Projection(name = "code", types = [Currency::class])
interface CurrencyCodeProjection {
    fun getCode(): String
}
