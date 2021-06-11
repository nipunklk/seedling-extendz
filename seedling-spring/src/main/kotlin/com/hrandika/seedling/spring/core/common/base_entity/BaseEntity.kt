package com.hrandika.seedling.spring.core.common.base_entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.vladmihalcea.hibernate.type.json.JsonBinaryType
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import org.hibernate.annotations.TypeDefs
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant
import javax.persistence.*


@MappedSuperclass
@EntityListeners(value = [AuditingEntityListener::class])
@TypeDefs(TypeDef(name = "jsonb", typeClass = JsonBinaryType::class))
abstract class BaseEntity {

    @CreatedBy
    @JsonIgnore
    @Column(name = "created_by", nullable = true, length = 50, updatable = false)
    protected open var createdBy: String? = null

    @CreatedDate
    @Column(name = "created_date", nullable = true)
    protected open var createdDate = Instant.now()

    @LastModifiedBy
    @JsonIgnore
    @Column(name = "last_modified_by", length = 50)
    protected open var lastModifiedBy: String? = null

    @LastModifiedDate
    @JsonIgnore
    @Column(name = "last_modified_date")
    protected open var lastModifiedDate = Instant.now()

}