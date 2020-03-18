package com.yamalc.ytmp.thermomonitor

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ThermoMonitorApplication

fun main(args: Array<String>) {
	runApplication<ThermoMonitorApplication>(*args)
}
