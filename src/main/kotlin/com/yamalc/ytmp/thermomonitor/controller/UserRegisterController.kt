package com.yamalc.ytmp.thermomonitor.controller

import com.yamalc.ytmp.grpc.client.UserApiClient
import com.yamalc.ytmp.grpc.user.BusinessErrorCode
import com.yamalc.ytmp.grpc.user.UserInfo
import com.yamalc.ytmp.grpc.user.UserInfoRequest
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
        // TODO: 現状ユーザID変更機能は考慮していない
        userRegisterServiceImpl.registerUser(userRegisterForm.userId, passwordEncoder.encode(userRegisterForm.loginPassword))
        // Domain化
        val userInfo = UserInfo.newBuilder()
                .setId(userRegisterForm.userId)
                .setDisplayName(userRegisterForm.displayName)
                .build()
        // APIの複数化
        val userInfoRequest = UserInfoRequest.newBuilder()
                .addUserInfo(userInfo)
                .build()
        val res = userClient.registerUserInfoResponse(userInfoRequest)
        if (null != res.businessErrorDetail) {
            val businessErrorDetail = res.businessErrorDetail
            val errorDetail = res.businessErrorDetail
            val errorCode = errorDetail.code
            if (BusinessErrorCode.VALIDATION_ERROR == errorCode) {
                val errorInfoList = errorDetail.errorInfoList
                if (errorInfoList.isNotEmpty()) { //UserInfoが入っていれば詳細ログ出力
                    errorInfoList.forEach {
                        val errorLineNumber = it.errorLineNumber
                        val errorFieldName = UserInfo.getDefaultInstance().descriptorForType.findFieldByNumber(it.errorField)
                        val errorMessage = it.errorDescription
                        println("API Validation Failed at \"$errorFieldName\" of $errorLineNumber th(from 0) bean with message \"$errorMessage\"")
                    }
                } else {
                    println("API Validation Failed with no ErrorInfo.")
                }
            } else {
                println("Unexpected error occurred with ErrorCode = $errorCode")
            }
            return "redirect:/register"
        }
        sessionStatus.setComplete()
        model["generalMessage"] = "ユーザを登録しました。登録した情報でログインしてください。"
        return "redirect:/top"
    }
}