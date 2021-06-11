package com.hrandika.seedling.spring.modules.product

import com.hrandika.seedling.spring.core.common.price.Price
import com.hrandika.seedling.spring.core.common.weight.Weight
import com.hrandika.seedling.spring.modules.category.CategoryNameProjection
import org.springframework.data.rest.core.config.Projection
import java.math.BigDecimal

@Projection(name = "name", types = [Product::class])
interface ProductNameProjection {
    fun getName(): String
}

@Projection(name = "dataTable", types = [Product::class])
interface ProductDataTableProjection : ProductNameProjection {
    fun getId(): String
    fun getImage(): String
    // TODO calculate from batch get totalQuantity

}

@Projection(name = "entity", types = [Product::class])
interface ProductEntityProjection : ProductDataTableProjection {
    fun getDescription(): String
    fun getCategory(): CategoryNameProjection
    fun getUnitPrice(): Price
    fun getCost(): Price
    fun getMinimumQuantity(): Int
    fun getUnitsPerSquareFeet(): BigDecimal
    fun getWeight(): Weight
    fun getNote(): String
    fun getActive(): Boolean
    fun getExpirable(): Boolean
    fun getSalesPart(): Boolean
    fun getPurchasePart(): Boolean
}