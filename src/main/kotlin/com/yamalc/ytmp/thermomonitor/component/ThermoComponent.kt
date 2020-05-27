package com.yamalc.ytmp.thermomonitor.component

import com.yamalc.ytmp.grpc.client.ThermoApiClient
import org.springframework.stereotype.Component

@Component
class ThermoComponent {
    val thermoClient: ThermoApiClient = ThermoApiClient.create("localhost", 8082)

    fun latestThermo(userName: String): Double {
        return thermoClient.getLatestThermoInfo(userName)
    }
    fun latestHealthCheck(userName: String): Double {
        return thermoClient.getLatestHealthCheck(userName)
    }
    fun recentlyHealthCheck(userName: String): Double {
        return thermoClient.getRecentlyHealthCheck(userName)
    }
    fun recentlyThermo(userName: String): Double {
        return thermoClient.getNormalBodyTemperature(userName)
    }
}