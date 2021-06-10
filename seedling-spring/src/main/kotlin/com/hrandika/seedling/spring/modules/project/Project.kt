package com.hrandika.seedling.spring.modules.project

import com.hrandika.seedling.spring.core.common.base_entity.BaseEntity
import com.hrandika.seedling.spring.core.common.price.Price
import com.hrandika.seedling.spring.modules.sales_order.SalesOrder
import com.hrandika.seedling.spring.modules.team.Team
import com.hrandika.seedling.spring.utils.StringSequenceStyleGenerator
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Parameter
import org.springframework.data.repository.PagingAndSortingRepository
import java.math.BigDecimal
import javax.persistence.*

enum class ProjectStatus {
    InProgress, OnHold, Completed
}

@Entity
@Table(name = "project")
data class Project(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "add_seq")
    @GenericGenerator(
        name = "add_seq",
        parameters = [
            Parameter(name = StringSequenceStyleGenerator.VALUE_PREFIX_PARAMETER, value = "PRJ"),
            Parameter(name = StringSequenceStyleGenerator.NUMBER_FORMAT_PARAMETER, value = "-%05d"),
        ],
        strategy = "com.hrandika.seedling.spring.utils.StringSequenceStyleGenerator"
    )
    var id: String? = null,

    var calculatedSquareFeet: BigDecimal? = null,
    var measureSquareFeet: BigDecimal? = null,
    var note: String? = null,

    @Embedded
    @AttributeOverrides(
        AttributeOverride(name = "value", column = Column(name = "project_revenue_value"))
    )
    @AssociationOverrides(
        AssociationOverride(name = "currency", joinColumns = [JoinColumn(name = "project_revenue_currency_code")])
    )
    var projectRevenue: Price? = null,

    @Embedded
    var unitPrice: Price? = null,

    @Embedded
    var status: ProjectStatus = ProjectStatus.InProgress,

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    var salesOrder: SalesOrder? = null,

    @ManyToOne
    var team: Team? = null,

    ) : BaseEntity()

interface ProjectRepository : PagingAndSortingRepository<Project, String>