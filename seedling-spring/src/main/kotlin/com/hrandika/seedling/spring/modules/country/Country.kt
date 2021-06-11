package com.hrandika.seedling.spring.modules.country

import com.hrandika.seedling.spring.core.common.base_entity.BaseEntity
import org.springframework.data.repository.PagingAndSortingRepository
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "country")
data class Country(
    @Id
    var code: String? = null,

    var phoneCode: String? = null,
    var name: String? = null
) : BaseEntity()

interface CountryRepository : PagingAndSortingRepository<Country, String>