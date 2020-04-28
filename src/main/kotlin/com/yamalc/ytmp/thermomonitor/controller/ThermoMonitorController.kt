package com.yamalc.ytmp.thermomonitor.controller

import com.yamalc.ytmp.thermomonitor.component.ThermoComponent
import com.yamalc.ytmp.thermomonitor.fw.security.LoginUser
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class ThermoMonitorController(private val thermoCompo: ThermoComponent) {
    @RequestMapping("/top")
    fun top(model: Model, @AuthenticationPrincipal loginUser: LoginUser): String {
        val userName = "user1"
        model["username"] = userName
        return "top"
    }
}