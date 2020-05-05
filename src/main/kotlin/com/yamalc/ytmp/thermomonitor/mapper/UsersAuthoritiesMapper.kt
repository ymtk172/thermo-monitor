package com.yamalc.ytmp.thermomonitor.mapper

import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper

@Mapper
interface UsersAuthoritiesMapper {
    @Insert("INSERT INTO users_authorities " +
            "(authority_id, user_id) " +
            "values " +
            "(2, #{userId})")
    fun insertRoleUser(userId: String): Int
}