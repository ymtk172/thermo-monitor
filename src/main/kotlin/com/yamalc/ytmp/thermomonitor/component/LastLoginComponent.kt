package com.yamalc.ytmp.thermomonitor.component

import com.yamalc.ytmp.thermomonitor.repositories.LoginHistoryRepository
import org.springframework.stereotype.Component

@Component
class LastLoginComponent(private val repository: LoginHistoryRepository) {
    fun getLastLoginInfo(userName: String): List<String> {
        return repository.findByUserId(userName).map { it.date }
    }
}