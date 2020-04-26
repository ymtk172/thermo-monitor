package com.yamalc.ytmp.thermomonitor.domain

import java.util.*

class User : java.io.Serializable {
    var user_id: String = ""
    var password: String = ""
    var insert_date: Date = Date()
    var update_date: Date = Date()
}