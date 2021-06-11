package com.hrandika.seedling.spring.modules.delivery_order

import com.hrandika.seedling.spring.core.common.base_entity.BaseEntity
import com.hrandika.seedling.spring.modules.delivery_order_line.DeliveryOrderLine
import com.hrandika.seedling.spring.modules.delivery_vehicle.DeliveryVehicle
import com.hrandika.seedling.spring.utils.StringSequenceStyleGenerator
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Parameter
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@Table(name = "delivery_order")
data class DeliveryOrder(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "add_seq")
    @GenericGenerator(
        name = "add_seq",
        parameters = [
            Parameter(name = StringSequenceStyleGenerator.VALUE_PREFIX_PARAMETER, value = "DEL"),
            Parameter(name = StringSequenceStyleGenerator.NUMBER_FORMAT_PARAMETER, value = "-%05d"),
        ],
        strategy = "com.hrandika.seedling.spring.utils.StringSequenceStyleGenerator"
    )
    var id: String? = null,

    @Column(name = "dispatch_date", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    var dispatchDate: OffsetDateTime? = null,

    var note: String? = null,

    @Column(name = "delivery_status", nullable = false)
    var status: DeliveryStatus? = null,

    @ManyToOne(optional = false)
    var vehicle: DeliveryVehicle? = null,

    @OneToMany(mappedBy = "deliveryOrder")
    var orderLines: List<DeliveryOrderLine>? = null

) : BaseEntity()

enum class DeliveryStatus {
    Planned,
    OnRoute,
    Closed
}

@Repository
interface DeliveryOrderRepository : PagingAndSortingRepository<DeliveryOrder, String>
