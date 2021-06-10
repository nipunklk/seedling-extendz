package com.hrandika.seedling.spring.modules.address

import org.springframework.stereotype.Service

@Service
class AddressService {

    fun getAddress(a: Address?): String {
        return listOfNotNull(a!!.line1, a?.line2, a?.city).joinToString(",")
    }
}
