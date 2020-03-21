package com.yamalc.ytmp.thermomonitor

import com.yamalc.ytmp.grpc.client.ThermoApiClient
import com.yamalc.ytmp.thermomonitor.grpc.UserApiClient
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ThermoMonitorApplication

fun main(args: Array<String>) {
	val userClient = UserApiClient.create("localhost", 8081)
	userClient.authenticate("user1", "password1")
	userClient.authenticate("user2", "password2")
	userClient.shutdown()
	val thermoClient = ThermoApiClient.create("localhost", 8082)
	thermoClient.getLatestThermoInfo("user1")
	thermoClient.getLatestHealthCheck("user1")
	thermoClient.getRecentlyHealthCheck("user1")
	thermoClient.getNormalBodyTemperature("user1")
	thermoClient.shutdown()
	runApplication<ThermoMonitorApplication>(*args)
}
