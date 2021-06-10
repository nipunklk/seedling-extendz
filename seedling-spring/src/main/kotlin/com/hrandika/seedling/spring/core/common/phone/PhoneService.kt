package com.hrandika.seedling.spring.core.common.phone

import org.springframework.stereotype.Service

@Service
class PhoneService {
    fun getFormattedNumber(phone: Phone?): String {
        phone?.number.let { return phone?.number ?: "" }
    }
}