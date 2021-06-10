package com.hrandika.seedling.spring.modules.unit_of_measure

import org.springframework.data.repository.PagingAndSortingRepository
import javax.persistence.*

@Entity
@Table(name = "unit_of_measure")
data class UnitOfMeasure(
    @Id
    var code: String? = null,

    var description: String? = null,
    var active: Boolean? = null,
)

interface UnitOfMeasureRepository : PagingAndSortingRepository<UnitOfMeasure, String>