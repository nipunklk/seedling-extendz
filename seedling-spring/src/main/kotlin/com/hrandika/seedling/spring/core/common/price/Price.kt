package com.hrandika.seedling.spring.core.common.price

import com.hrandika.seedling.spring.modules.currency.Currency
import java.math.BigDecimal
import javax.persistence.*

@Embeddable
data class Price(
    val value: BigDecimal? = null,

    @ManyToOne(cascade = [CascadeType.DETACH], fetch = FetchType.EAGER)
    @JoinColumn(name = "currency_code", referencedColumnName = "code")
    var currency: Currency? = null
)