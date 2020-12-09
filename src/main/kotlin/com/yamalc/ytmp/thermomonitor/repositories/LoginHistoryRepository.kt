package com.yamalc.ytmp.thermomonitor.repositories

import com.yamalc.ytmp.thermomonitor.domain.LoginHistory
import org.socialsignin.spring.data.dynamodb.repository.EnableScan
import org.springframework.data.repository.CrudRepository

@EnableScan
interface LoginHistoryRepository : CrudRepository<LoginHistory, String> {
    fun findByUserId(userId: String): List<LoginHistory>
}