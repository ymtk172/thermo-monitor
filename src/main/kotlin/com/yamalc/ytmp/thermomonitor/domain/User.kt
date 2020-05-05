package com.yamalc.ytmp.thermomonitor.domain

import java.sql.Date

class User : java.io.Serializable {
    var user_id: String = ""
    var password: String = ""
    lateinit var insert_date: Date
    lateinit var update_date: Date

}