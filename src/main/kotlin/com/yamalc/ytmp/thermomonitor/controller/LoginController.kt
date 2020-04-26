package com.yamalc.ytmp.thermomonitor.controller

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
    @GetMapping("/")
    fun displayLoginTop(loginForm: LoginForm): String {
        return "loginTop"
    }

    //Spring Securityの認証処理はサーブレットフィルターで行われ、Bean ValidationはDispatcherServletで行われるため
    //単項目（Bean Validation）エラー時にも、先にSpring Securityの認証処理によるDBアクセスが発生する。
    @PostMapping("/loginFailure")
    fun loginFailure(model: Model,@Validated form: LoginForm, result: BindingResult): String {
        model["errorMessage"] = "正しいログインID・パスワードを入力してください。"
        return "loginTop"
    }
}