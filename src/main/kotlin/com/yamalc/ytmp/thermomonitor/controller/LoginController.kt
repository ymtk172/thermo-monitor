package com.yamalc.ytmp.thermomonitor.controller

import com.yamalc.ytmp.thermomonitor.domain.Users
import com.yamalc.ytmp.thermomonitor.form.LoginForm
import com.yamalc.ytmp.thermomonitor.mapper.UsersMapper
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class LoginController(private val usersMapper: UsersMapper) {
    @RequestMapping("/")
    fun loginTop(loginForm: LoginForm): String {
        return "loginTop"
    }
    @PostMapping("/login")
    fun login(model: Model, @Validated form: LoginForm, result: BindingResult): String {
        if (result.hasErrors()) return "loginTop"
        println("login logic")
        //TODO: check ID/PW
        val user: Users = usersMapper.select(form.loginId)
        if (user == null) {
            model["errorMessage"] = "存在しないユーザIDです"
            return "loginTop"
        }
        if (form.loginPassword != user.password) {
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