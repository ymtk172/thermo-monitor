package com.yamalc.ytmp.thermomonitor

import com.yamalc.ytmp.thermomonitor.grpc.UserApiClient
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ThermoMonitorApplication

fun main(args: Array<String>) {
	val simpleClient = UserApiClient.create("localhost", 8081)
	simpleClient.authenticate("user1", "password1")
	simpleClient.authenticate("user2", "password2")
	simpleClient.shutdown()
	runApplication<ThermoMonitorApplication>(*args)
}
