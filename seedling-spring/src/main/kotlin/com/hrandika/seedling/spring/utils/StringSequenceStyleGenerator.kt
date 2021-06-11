package com.hrandika.seedling.spring.utils

import org.hibernate.HibernateException
import org.hibernate.MappingException
import org.hibernate.engine.spi.SharedSessionContractImplementor
import org.hibernate.id.Configurable
import org.hibernate.id.enhanced.SequenceStyleGenerator
import org.hibernate.internal.util.config.ConfigurationHelper
import org.hibernate.service.ServiceRegistry
import org.hibernate.type.LongType
import org.hibernate.type.Type
import java.io.Serializable
import java.util.*


class StringSequenceStyleGenerator : SequenceStyleGenerator(), Configurable {

    companion object {
        const val VALUE_PREFIX_PARAMETER = "valuePrefix"
        const val NUMBER_FORMAT_PARAMETER = "numberFormat"
    }


    private val VALUE_PREFIX_DEFAULT = ""
    private var valuePrefix: String? = null


    val NUMBER_FORMAT_DEFAULT = "%d"
    private var numberFormat: String? = null

    @Throws(HibernateException::class)
    override fun generate(
        session: SharedSessionContractImplementor?,
        `object`: Any?
    ): Serializable? {
        return valuePrefix + String.format(
            numberFormat!!,
            super.generate(session, `object`)
        )
    }

    @Throws(MappingException::class)
    override fun configure(
        type: Type?, params: Properties?,
        serviceRegistry: ServiceRegistry?
    ) {
        super.configure(LongType.INSTANCE, params, serviceRegistry)
        valuePrefix = ConfigurationHelper.getString(
            VALUE_PREFIX_PARAMETER,
            params, VALUE_PREFIX_DEFAULT
        )
        numberFormat = ConfigurationHelper.getString(
            NUMBER_FORMAT_PARAMETER,
            params, NUMBER_FORMAT_DEFAULT
        )
    }
}