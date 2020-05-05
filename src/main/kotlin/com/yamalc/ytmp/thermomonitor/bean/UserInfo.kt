package com.yamalc.ytmp.thermomonitor.bean

import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.stereotype.Component
import org.springframework.web.context.WebApplicationContext

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
class UserInfo : java.io.Serializable {
    private val serialVersionUID = 1L

    var userId: String = ""
    var displayName: String = ""
}