package com.yamalc.ytmp.thermomonitor.service

import com.yamalc.ytmp.thermomonitor.mapper.UsersAuthoritiesMapper
import com.yamalc.ytmp.thermomonitor.mapper.UsersMapper
import org.springframework.stereotype.Service

@Service
class UserRegisterServiceImpl(
        val usersMapper: UsersMapper,
        val usersAuthoritiesMapper: UsersAuthoritiesMapper) {
    fun registerUser(userId: String, password: String) {
        usersMapper.insertUser(userId, password)
        usersAuthoritiesMapper.insertRoleUser(userId)
    }
}
