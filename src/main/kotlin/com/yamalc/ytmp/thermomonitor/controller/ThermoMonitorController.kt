package com.yamalc.ytmp.thermomonitor.controller

import com.yamalc.ytmp.thermomonitor.component.ThermoComponent
import com.yamalc.ytmp.thermomonitor.fw.security.LoginUser
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class ThermoMonitorController(private val thermoCompo: ThermoComponent) {
    @RequestMapping("/top")
    fun top(@AuthenticationPrincipal loginUser: LoginUser): String {
        return "top"
    }
}