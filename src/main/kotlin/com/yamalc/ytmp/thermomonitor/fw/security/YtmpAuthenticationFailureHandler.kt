package com.yamalc.ytmp.thermomonitor.fw.security

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Spring Securityの認証失敗時に呼ばれるハンドラクラス
 * ※現実装ではどこからも呼ばれていない
 */
class YtmpAuthenticationFailureHandler : AuthenticationFailureHandler {
    @Throws(IOException::class, ServletException::class)
    override fun onAuthenticationFailure(
            httpServletRequest: HttpServletRequest,
            httpServletResponse: HttpServletResponse,
            authenticationException: AuthenticationException) {
//        var errorMessage = ""
//        if (authenticationException is BadCredentialsException) {
//            errorMessage = "400"
//        }

        // ログイン画面にリダイレクトする
        httpServletResponse.sendRedirect(httpServletRequest.contextPath + "/")
    }
}