package com.yamalc.ytmp.thermomonitor.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class ThermoMonitorController {

    @RequestMapping("/top")
    fun top(): String {
        return "top"
    }
}