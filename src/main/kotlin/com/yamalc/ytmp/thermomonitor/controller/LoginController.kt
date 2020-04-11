package com.yamalc.ytmp.thermomonitor.controller

import com.yamalc.ytmp.thermomonitor.form.LoginForm
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class LoginController() {
    @RequestMapping("/")
    fun loginTop(loginForm: LoginForm): String {
        return "loginTop"
    }
    @PostMapping("/login")
    fun login(@Validated form: LoginForm, result: BindingResult): String {
        if (result.hasErrors()) return "loginTop"
        //TODO: check ID/PW
        println(form.loginId + " : " + form.loginPassword) //TODO: DELETE
        return "redirect:/top"
    }
    @PostMapping("/logout")
    fun logout(): String {
        //TODO: delete session
        return "redirect:/"
    }
}