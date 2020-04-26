package com.yamalc.ytmp.thermomonitor.mapper

import com.yamalc.ytmp.thermomonitor.domain.User
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface UsersMapper {
    @Select("SELECT * FROM users where user_id = #{userId}")
    fun select(userId: String): User
}