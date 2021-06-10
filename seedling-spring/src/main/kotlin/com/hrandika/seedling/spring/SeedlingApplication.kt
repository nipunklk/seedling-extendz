package com.hrandika.seedling.spring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SeedlingApplication

fun main(args: Array<String>) {
    runApplication<SeedlingApplication>(*args)
}
