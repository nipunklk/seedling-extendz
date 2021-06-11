package com.hrandika.seedling.spring.core.common.unit

import java.math.BigDecimal
import javax.persistence.Embeddable

@Embeddable
data class Unit(var unit: String, val value: BigDecimal)