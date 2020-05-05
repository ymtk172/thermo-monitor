package com.yamalc.ytmp.thermomonitor.mapper

import com.yamalc.ytmp.thermomonitor.domain.User
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface UsersMapper {
    @Select("SELECT * " +
            "FROM users " +
            "where user_id = #{userId}")
    fun select(userId: String): User

    @Insert("INSERT INTO users " +
            "(user_id, password) " +
            "values " +
            "(#{userId}, #{password})")
    fun insertUser(userId: String, password: String): Int
}
