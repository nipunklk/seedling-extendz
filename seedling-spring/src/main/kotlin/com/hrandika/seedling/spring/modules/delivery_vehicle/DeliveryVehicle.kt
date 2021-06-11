package com.hrandika.seedling.spring.modules.delivery_vehicle

import com.hrandika.seedling.spring.core.common.base_entity.BaseEntity
import com.hrandika.seedling.spring.utils.StringSequenceStyleGenerator
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Parameter
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import org.hibernate.annotations.Type
import javax.persistence.*

@Entity
@Table(name = "delivery_vehicle")
data class DeliveryVehicle(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "add_seq")
    @GenericGenerator(
        name = "add_seq",
        parameters = [
            Parameter(name = StringSequenceStyleGenerator.VALUE_PREFIX_PARAMETER, value = "DEV"),
            Parameter(name = StringSequenceStyleGenerator.NUMBER_FORMAT_PARAMETER, value = "-%05d"),
        ],
        strategy = "com.hrandika.seedling.spring.utils.StringSequenceStyleGenerator"
    )
    var id: String? = null,

    @Column(name = "register_number", nullable = false)
    var registerNumber: String? = null,

    var description: String? = null,
    var active: Boolean? = true,

) : BaseEntity()

@Repository
interface DeliveryVehicleRepository : PagingAndSortingRepository<DeliveryVehicle, String>
