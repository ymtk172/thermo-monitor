package com.yamalc.ytmp.thermomonitor.fw.security

import com.yamalc.ytmp.thermomonitor.domain.User
import org.springframework.security.core.authority.AuthorityUtils

class LoginUser
/**
 * コンストラクタ
 * @param user
 */
// スーパークラスのusername、passwordに値をセットし、認証を行う
(private val user: User) : org.springframework.security.core.userdetails.User(user.user_id, user.password,
        AuthorityUtils.createAuthorityList("ROLE_USER")) {
}