package com.yamalc.ytmp.thermomonitor.controller

import com.yamalc.ytmp.grpc.client.UserApiClient
import com.yamalc.ytmp.grpc.user.UserInfoResponse
import com.yamalc.ytmp.thermomonitor.bean.UserInfo
import com.yamalc.ytmp.thermomonitor.form.LoginForm
import com.yamalc.ytmp.thermomonitor.fw.security.LoginUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpSession

@Controller
@RequestMapping("/")
class LoginController() {

    @Autowired
    lateinit var userInfo: UserInfo

    val userClient: UserApiClient = UserApiClient.create("localhost", 9081)

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

    @RequestMapping("/getUserInfo")
    fun top(@AuthenticationPrincipal loginUser: LoginUser, session: HttpSession): String {
        val userInfoResponse: UserInfoResponse = userClient.getUserInfo(loginUser.username)
        userInfo.userId = userInfoResponse.id
        userInfo.displayName = userInfoResponse.displayName
        session.setAttribute("userInfo", userInfo)
        println("logged in by: " + userInfo.displayName)
        return "redirect:/top"
    }
}