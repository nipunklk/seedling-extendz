package com.hrandika.seedling.spring.core.common.price

import org.springframework.stereotype.Service

@Service
class PriceService {

    fun getPrice(price: Price?): String {
        return "${price?.currency?.code} ${price?.value}"
    }
}