package com.yamalc.ytmp.thermomonitor.fw.security

import com.yamalc.ytmp.thermomonitor.domain.Authority
import com.yamalc.ytmp.thermomonitor.domain.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

class LoginUser
/**
 * コンストラクタ
 * @param user
 */
// スーパークラスのusername、passwordに値をセットし、認証を行う
(private val user: User, private val authorities: List<Authority>) : org.springframework.security.core.userdetails.User(
        user.user_id,
        user.password,
//        AuthorityUtils.createAuthorityList("ROLE_USER")
        getAuthorities(authorities)
)

private fun getAuthorities(authorityList: List<Authority>): List<GrantedAuthority> {
    val authorities: MutableList<GrantedAuthority> = mutableListOf()
    authorityList.forEach {
        auth -> authorities.add(SimpleGrantedAuthority(auth.authority)) }
    return authorities
}