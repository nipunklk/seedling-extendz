package com.hrandika.seedling.spring.config


import com.hrandika.seedling.spring.modules.country.Country
import com.hrandika.seedling.spring.modules.currency.Currency
import com.hrandika.seedling.spring.modules.customer.Customer
import com.hrandika.seedling.spring.modules.delivery_order.DeliveryOrder
import com.hrandika.seedling.spring.modules.product.Product
import com.hrandika.seedling.spring.modules.sales_order.SalesOrder
import com.hrandika.seedling.spring.modules.sales_order_line.SalesOrderLine
import com.hrandika.seedling.spring.modules.unit_of_measure.UnitOfMeasure
import org.springframework.context.annotation.Configuration
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer
import org.springframework.web.servlet.config.annotation.CorsRegistry

/***
 * @author Randika Hapugoda
 */
@Configuration
class GlobalRepositoryRestConfigurer() : RepositoryRestConfigurer {

    override fun configureRepositoryRestConfiguration(config: RepositoryRestConfiguration?, cors: CorsRegistry) {
        config?.exposeIdsFor(
            Currency::class.java,
            Customer::class.java,
            UnitOfMeasure::class.java,
            Country::class.java,
            Product::class.java,
            DeliveryOrder::class.java,
            SalesOrder::class.java,
            SalesOrderLine::class.java
        )
        cors.addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("*")
            .allowedHeaders("*")
    }


}
