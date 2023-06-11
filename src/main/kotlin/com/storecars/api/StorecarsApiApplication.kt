package com.storecars.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StorecarsApiApplication

fun main(args: Array<String>) {
	runApplication<StorecarsApiApplication>(*args)
}
