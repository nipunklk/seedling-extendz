package com.hrandika.seedling.spring.core.common.weight

import com.hrandika.seedling.spring.modules.unit_of_measure.UnitOfMeasure
import java.math.BigDecimal
import javax.persistence.*

@Embeddable
data class Weight(
    val value: BigDecimal? = null,

    @ManyToOne(cascade = [CascadeType.DETACH], fetch = FetchType.EAGER)
    @JoinColumn(name = "code", insertable = false, updatable = false)
    var unitOfMeasure: UnitOfMeasure? = null
)