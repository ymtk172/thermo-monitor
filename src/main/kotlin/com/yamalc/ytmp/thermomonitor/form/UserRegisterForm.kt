package com.yamalc.ytmp.thermomonitor.form

import org.jetbrains.annotations.NotNull
import javax.validation.constraints.Size

class UserRegisterForm : java.io.Serializable {

    @NotNull
    @Size(min = 1, max = 80)
    var userId: String = ""

    @NotNull
    @Size(min = 1, max = 20)
    var loginPassword: String = ""

    @NotNull
    @Size(min = 1, max = 20)
    var displayName: String = ""
}