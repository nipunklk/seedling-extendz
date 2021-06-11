package com.hrandika.seedling.spring.core.common.phone

import com.hrandika.seedling.spring.modules.country.Country
import javax.persistence.*

@Embeddable
data class Phone(
    var number: String,

    @ManyToOne(cascade = [CascadeType.DETACH], fetch = FetchType.EAGER)
    @JoinColumn(name = "code", insertable = false, updatable = false)
    var country: Country? = null
)