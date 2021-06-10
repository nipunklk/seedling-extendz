package com.hrandika.seedling.spring.core.services.entity_converter

import org.springframework.core.convert.TypeDescriptor
import org.springframework.data.mapping.context.MappingContext
import org.springframework.data.mapping.context.PersistentEntities
import org.springframework.data.repository.support.Repositories
import org.springframework.data.repository.support.RepositoryInvokerFactory
import org.springframework.data.rest.core.UriToEntityConverter
import org.springframework.hateoas.Link
import org.springframework.stereotype.Service
import java.net.URI
import java.util.*


@Service
class EntityConverter(
    val mappingContext: MappingContext<*, *>?,
    val invokerFactory: RepositoryInvokerFactory,
    val repositories: Repositories,
) {
    fun <T> convert(link: Link, target: Class<T>): T {
        val entities = PersistentEntities(Collections.singletonList(mappingContext))
        val converter = UriToEntityConverter(entities, invokerFactory, repositories)
        val uri: URI = convert(link)
        val o = converter.convert(uri, TypeDescriptor.valueOf(URI::class.java), TypeDescriptor.valueOf(target))
        return target.cast(o)
            ?: throw IllegalArgumentException(String.format("%s '%s' was not found.", target.simpleName, uri))
    }

    private fun convert(link: Link): URI {
        return try {
            URI(link.href)
        } catch (e: Exception) {
            throw IllegalArgumentException("URI from link is invalid", e)
        }
    }
}