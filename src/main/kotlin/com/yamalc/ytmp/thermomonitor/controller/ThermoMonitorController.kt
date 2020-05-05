package com.yamalc.ytmp.thermomonitor.controller

import com.yamalc.ytmp.thermomonitor.component.ThermoComponent
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class ThermoMonitorController(private val thermoCompo: ThermoComponent) {

    @RequestMapping("/top")
    fun top(): String {
        return "top"
    }
}