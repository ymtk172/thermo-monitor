package com.yamalc.ytmp.thermomonitor.config

import com.yamalc.ytmp.thermomonitor.fw.security.UserDetailsServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

/**
 * Spring Security設定クラス.
 */
@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {
    @Throws(Exception::class)
    override fun configure(web: WebSecurity) {
        // セキュリティ設定を無視するリクエスト設定
        // 静的リソース(images、css、javascript)に対するアクセスはセキュリティ設定を無視する
        web.ignoring().antMatchers(
                "/images/**",
                "/css/**",
                "/javascript/**")
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        // 認可の設定
        http.authorizeRequests()
                .antMatchers("/","/").permitAll() // indexは全ユーザーアクセス許可
                .anyRequest().authenticated() // それ以外は全て認証無しの場合アクセス不許可

        // ログイン設定
        http.formLogin()
                .loginProcessingUrl("/") // 認証処理のパス
                //.loginPage("/").permitAll() // ログインフォームのパス。デフォルトはloginProcessingUrl()と同じ。permitAll()で許可しないと認証しようとして無限ループする。
                .failureForwardUrl("/loginFailure") // 認証失敗時に呼ばれるハンドラクラス
                //.failureUrl("/loginFailure")                // 認証失敗時にリダイレクトするURL
                //.failureHandler(YtmpAuthenticationFailureHandler()) // 認証失敗時に呼ばれるハンドラクラス
                //.successForwardUrl("/")                // 認証成功時にforwardするURL
                //.successHandler(YtmpAuthenticationFailureHandler()) // 認証成功時に呼ばれるハンドラクラス
                .defaultSuccessUrl("/top") // 認証成功時の遷移先
                .usernameParameter("loginId").passwordParameter("loginPassword") // ユーザー名、パスワードのパラメータ名
                .and()

        // ログアウト設定
        http.logout()
                .logoutRequestMatcher(AntPathRequestMatcher("/logout**")) // ログアウト処理のパス
                .logoutSuccessUrl("/") // ログアウト完了時のパス
    }

    @Configuration
    protected class AuthenticationConfiguration : GlobalAuthenticationConfigurerAdapter() {
        @Autowired
        var userDetailsService: UserDetailsServiceImpl? = null

        @Throws(Exception::class)
        override fun init(auth: AuthenticationManagerBuilder) {
            // 認証するユーザーを設定する
            auth.userDetailsService(userDetailsService) // 入力値をbcryptでハッシュ化した値でパスワード認証を行う
                    .passwordEncoder(BCryptPasswordEncoder())
        }
    }
}