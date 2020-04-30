package com.yamalc.ytmp.thermomonitor.fw.security

import com.yamalc.ytmp.thermomonitor.domain.Authority
import com.yamalc.ytmp.thermomonitor.domain.User
import com.yamalc.ytmp.thermomonitor.mapper.AuthoritiesMapper
import com.yamalc.ytmp.thermomonitor.mapper.UsersMapper
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

/**
 * UserDetailsServiceの実装クラス
 * Spring Securityでのユーザー認証に使用する
 */
@Suppress("SENSELESS_COMPARISON")
@Component
class UserDetailsServiceImpl(
        val usersMapper: UsersMapper,
        val authoritiesMapper: AuthoritiesMapper) : UserDetailsService {
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(loginId: String): UserDetails {
        // loginIdが空の場合はDBアクセスを省略する
        if (loginId.isEmpty()) throw UsernameNotFoundException("missing login ID")
        val user: User?
        val authorities: List<Authority>
        try {
            user = usersMapper.select(loginId)
        } catch (e: Exception) {
            throw UsernameNotFoundException("It can not be acquired User")
        }

        // ユーザーがDBに存在しなかった場合
        // usersMapper.select()はnullを返すことがあるのでnull判定が必要
        if (user == null) {
            throw UsernameNotFoundException("User not found for login id: $loginId")
        }

        try {
            authorities = authoritiesMapper.selectByUserId(loginId)
        } catch (e: Exception) {
            println(e.printStackTrace())
            throw UsernameNotFoundException("It can not be acquired User")
        }

        // 権限が１つも与えられていなかった場合
        if (authorities == null) {
            throw UsernameNotFoundException("Authority not found for login id: $loginId")
        }

        // Spring Securityで認証できる形で戻す
        return LoginUser(user, authorities)
    }
}