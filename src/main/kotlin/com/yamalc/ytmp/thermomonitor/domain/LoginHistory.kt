package com.yamalc.ytmp.thermomonitor.domain

import com.amazonaws.services.dynamodbv2.datamodeling.*

@DynamoDBTable(tableName = "LOGIN_HISTORY")
data class LoginHistory(@DynamoDBHashKey var id: String = "",
                        @DynamoDBAttribute var userId: String = "",
                        @DynamoDBAttribute var date: String = "") : java.io.Serializable