package com.hrandika.seedling.spring.utils

import com.hrandika.seedling.spring.core.common.phone.Phone
import com.hrandika.seedling.spring.core.common.price.Price
import com.hrandika.seedling.spring.modules.address.Address
import com.hrandika.seedling.spring.modules.address.AddressRepository
import com.hrandika.seedling.spring.modules.country.Country
import com.hrandika.seedling.spring.modules.country.CountryRepository
import com.hrandika.seedling.spring.modules.currency.Currency
import com.hrandika.seedling.spring.modules.currency.CurrencyRepository
import com.hrandika.seedling.spring.modules.customer.Customer
import com.hrandika.seedling.spring.modules.customer.CustomerRepository
import com.hrandika.seedling.spring.modules.delivery_order.DeliveryOrder
import com.hrandika.seedling.spring.modules.delivery_order.DeliveryOrderRepository
import com.hrandika.seedling.spring.modules.delivery_order.DeliveryStatus
import com.hrandika.seedling.spring.modules.delivery_order_line.DeliveryOrderLine
import com.hrandika.seedling.spring.modules.delivery_order_line.DeliveryOrderLineRepository
import com.hrandika.seedling.spring.modules.delivery_vehicle.DeliveryVehicle
import com.hrandika.seedling.spring.modules.delivery_vehicle.DeliveryVehicleRepository
import com.hrandika.seedling.spring.modules.product.Product
import com.hrandika.seedling.spring.modules.product.ProductRepository
import com.hrandika.seedling.spring.modules.sales_charge_type.SalesChargeType
import com.hrandika.seedling.spring.modules.sales_charge_type.SalesChargeTypeRepository
import com.hrandika.seedling.spring.modules.sales_order.SalesOrder
import com.hrandika.seedling.spring.modules.sales_order.SalesOrderRepository
import com.hrandika.seedling.spring.modules.sales_order_line.OrderLineStatus
import com.hrandika.seedling.spring.modules.sales_order_line.SalesOrderLine
import com.hrandika.seedling.spring.modules.sales_order_line.SalesOrderLineRepository
import com.hrandika.seedling.spring.modules.system_user.SystemUser
import com.hrandika.seedling.spring.modules.system_user.SystemUserRepository
import com.hrandika.seedling.spring.modules.unit_of_measure.UnitOfMeasure
import com.hrandika.seedling.spring.modules.unit_of_measure.UnitOfMeasureRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.OffsetDateTime
import javax.annotation.PostConstruct

@Service
class InitData(
    var systemUserRepository: SystemUserRepository,
    var currencyRepository: CurrencyRepository,
    var unitOfMeasureRepository: UnitOfMeasureRepository,
    var countryRepository: CountryRepository,
    var customerRepository: CustomerRepository,
    var addressRepository: AddressRepository,
    var productRepository: ProductRepository,
    var salesOrderRepository: SalesOrderRepository,
    var salesOrderLineRepository: SalesOrderLineRepository,
    var deliveryOrderRepository: DeliveryOrderRepository,
    var salesChargeTypeRepository: SalesChargeTypeRepository,
    var deliveryOrderLineRepository: DeliveryOrderLineRepository,
    val deliveryVehicleRepository: DeliveryVehicleRepository,
    var passwordEncoder: PasswordEncoder
) {

    @PostConstruct
    fun init() {
        // admin user
        var admin =
            SystemUser("admin@seedling.lk", this.passwordEncoder.encode("gadgetM@N12x89"), mutableListOf("ROLE_ADMIN"))
        this.systemUserRepository.save(admin)

        // country
        var sriLanka = Country(code = "LKR", phoneCode = "+94")
        this.countryRepository.save(sriLanka)

        // currency
        var currency = Currency(code ="LKR", name = "Rs")
        this.currencyRepository.save(currency)

        // sale order charge
        var delivery =
            SalesChargeType(
                name = "Delivery",
                defaultAmount = Price(value = BigDecimal.valueOf(10), currency = currency)
            )
        this.salesChargeTypeRepository.save(delivery)

        var unitOfMeasure = UnitOfMeasure(code = "KG")
        this.unitOfMeasureRepository.save(unitOfMeasure)

        // product
        var product = Product(name = "Test Product")
        product.minimumQuantity = 1
        this.productRepository.save(product)

        var address = Address()
        address.line1 = "123,Thulhiriya"
        address.line2 = "Mihihana"
        address.city = "Kalawana"
        address.delivery = true
        addressRepository.save(address)

        // customer
        var customer = Customer(firstName = "Randika", lastName = "Hapugoda")
        customer.telephone = Phone(number = "071 199 7224", country = sriLanka)
        customer.addresses = mutableListOf(address)
        this.customerRepository.save(customer)

        // sales order
        var saleOrder = SalesOrder()
        saleOrder.customer = customer;
        saleOrder.wantedDeliveryDate = OffsetDateTime.now()
        salesOrderRepository.save(saleOrder)

        // sales order line
        var saleOrderLine = SalesOrderLine()
        saleOrderLine.lineNumber = 1
        saleOrderLine.quantity = 100
        saleOrderLine.deliveredQuantity = 20
        saleOrderLine.status = OrderLineStatus.Planned
        saleOrderLine.product = product
        saleOrderLine.salesOrder = saleOrder

        salesOrderLineRepository.saveAll(mutableListOf(saleOrderLine, saleOrderLine, saleOrderLine))

        // vehicle
        var vehicle = DeliveryVehicle()
        vehicle.registerNumber = "WP CAC-1212"
        deliveryVehicleRepository.save(vehicle)

        // delivery order
        var deliveryOrder = DeliveryOrder()
        deliveryOrder.dispatchDate = OffsetDateTime.now()
        deliveryOrder.vehicle = vehicle
        deliveryOrder.status = DeliveryStatus.Planned
        deliveryOrderRepository.save(deliveryOrder)

        // delivery order line
        for (i in 1..10) {
            var deliveryOrderLine = DeliveryOrderLine()
            deliveryOrderLine.lineNumber = i.toLong()
            deliveryOrderLine.quantity = i.toLong()
            deliveryOrderLine.salesOrderLine = saleOrderLine
            deliveryOrderLine.deliveryOrder = deliveryOrder
            deliveryOrderLineRepository.save(deliveryOrderLine)
        }
    }

}