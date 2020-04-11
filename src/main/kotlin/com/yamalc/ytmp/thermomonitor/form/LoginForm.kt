package com.yamalc.ytmp.thermomonitor.form

import org.jetbrains.annotations.NotNull
import javax.validation.constraints.Size

class LoginForm {

    @NotNull
    @Size(min = 1, max = 80)
    var loginId: String? = null

    @NotNull
    @Size(min = 1, max = 20)
    var loginPassword: String? = null
}