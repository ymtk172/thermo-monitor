package com.yamalc.ytmp.thermomonitor

import com.yamalc.ytmp.grpc.client.ThermoApiClient
import com.yamalc.ytmp.grpc.client.UserApiClient
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ThermoMonitorApplication

fun main(args: Array<String>) {
//	val userClient = UserApiClient.create("localhost", 8081)
//	userClient.authenticate("user1", "password1")
	runApplication<ThermoMonitorApplication>(*args)
}
