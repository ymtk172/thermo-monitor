package com.yamalc.ytmp.thermomonitor.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class ThermoMonitorController {
    @RequestMapping("/")
    fun login(): String {
        return "login"
    }
    @RequestMapping("/top")
    fun top(model: Model): String {
        model["username"] = "TARO"
        return "top"
    }
}