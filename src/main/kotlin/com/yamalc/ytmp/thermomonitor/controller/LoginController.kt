package com.yamalc.ytmp.thermomonitor.controller

import com.yamalc.ytmp.grpc.client.UserApiClient
import com.yamalc.ytmp.grpc.user.AuthenticateResponseType
import com.yamalc.ytmp.thermomonitor.form.LoginForm
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class LoginController() {
    @RequestMapping("/s/{filename}")
    fun shortcut(@PathVariable("filename") filename: String): String {
        println(filename)
        return "shortcut/$filename"
    }
    @RequestMapping("/")
    fun loginTop(loginForm: LoginForm): String {
        return "loginTop"
    }
    val userClient: UserApiClient = UserApiClient.create("localhost", 9081)

    @PostMapping("/login")
    fun login(model: Model, @Validated form: LoginForm, result: BindingResult): String {
        if (result.hasErrors()) return "loginTop"
        println("login logic")
        if (userClient.authenticate(form.loginId,form.loginPassword) != AuthenticateResponseType.OK) {
            model["errorMessage"] = "パスワードが間違っています。"
            return "loginTop"
        }
        return "redirect:/top"
    }
    @GetMapping("/logout")
    fun logout(): String {
        //TODO: delete session
        return "redirect:/"
    }
}