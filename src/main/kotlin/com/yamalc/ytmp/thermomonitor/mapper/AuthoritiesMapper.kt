package com.yamalc.ytmp.thermomonitor.mapper

import com.yamalc.ytmp.thermomonitor.domain.Authority
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface AuthoritiesMapper {
    @Select("SELECT a.authority_id, a.authority " +
            "FROM users_authorities ua " +
            "JOIN authorities a " +
            "ON ua.authority_id = a.authority_id " +
            "where ua.user_id = #{userId}")
    fun selectByUserId(userId: String): List<Authority>
}