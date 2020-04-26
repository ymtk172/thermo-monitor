package com.yamalc.ytmp.thermomonitor

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


@Autowired
var passwordEncoder: PasswordEncoder? = BCryptPasswordEncoder()

@Bean
fun passwordEncoder(): PasswordEncoder? {
	return BCryptPasswordEncoder()
}

@SpringBootApplication
class ThermoMonitorApplication

fun main(args: Array<String>) {
	val password = "pass"
	val digest = passwordEncoder!!.encode(password)
	println("ハッシュ値 = $digest")
	val digest2 = passwordEncoder!!.encode(password)
	println("ハッシュ値 = $digest2")
	if (passwordEncoder!!.matches(password, digest)) {
		println("一致")
	}
	if (passwordEncoder!!.matches(password, digest2)) {
		println("一致")
	}
	runApplication<ThermoMonitorApplication>(*args)
}

