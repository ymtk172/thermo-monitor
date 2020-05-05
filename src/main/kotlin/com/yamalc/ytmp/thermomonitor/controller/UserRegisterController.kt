package com.yamalc.ytmp.thermomonitor.controller

import com.yamalc.ytmp.grpc.client.UserApiClient
import com.yamalc.ytmp.thermomonitor.form.UserRegisterForm
import com.yamalc.ytmp.thermomonitor.service.UserRegisterServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.SessionAttributes
import org.springframework.web.bind.support.SessionStatus
import java.io.IOException

@Controller
@RequestMapping("/register")
@SessionAttributes("userRegisterForm")
class UserRegisterController(val userRegisterServiceImpl: UserRegisterServiceImpl) {

    val userClient: UserApiClient = UserApiClient.create("localhost", 9081)

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    @GetMapping
    fun input(model: Model, userRegisterForm: UserRegisterForm, sessionStatus: SessionStatus): String {
        model["userRegisterForm"] = UserRegisterForm()
        return "register"
    }
    @PostMapping(params = ["back"])
    fun back(userRegisterForm: UserRegisterForm): String {
        println("register now")
        return "register"
    }
    @PostMapping(params = ["confirm"])
    fun confirm(model: Model, @Validated userRegisterForm: UserRegisterForm, result: BindingResult): String {
        if (result.hasErrors()) {
            model["errorMessage"] = "正しく入力してください。"
            return "register"
        }
        return "registerConfirm"
    }
    @PostMapping(params = ["submit"])
    fun complete(model: Model, @Validated userRegisterForm: UserRegisterForm, result: BindingResult, sessionStatus: SessionStatus): String {
        if (result.hasErrors()) {
            model["errorMessage"] = "正しく入力してください。"
            return "redirect:/register"
        }
        try {
            // TODO: 現状ユーザID変更機能は考慮していない
            userRegisterServiceImpl.registerUser(userRegisterForm.userId, passwordEncoder.encode(userRegisterForm.loginPassword))
            userClient.registerUserInfoResponse(userRegisterForm.userId, userRegisterForm.displayName)
        } catch (e: IOException) {
            println("DB access error occurred")
            throw e
        }
        sessionStatus.setComplete()
        model["generalMessage"] = "ユーザを登録しました。登録した情報でログインしてください。"
        return "redirect:/top"
    }
}