package com.yamalc.ytmp.thermomonitor

import org.springframework.web.WebApplicationInitializer
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import javax.servlet.ServletContext
import javax.servlet.ServletException

class MyInitializer : WebApplicationInitializer {
    @Throws(ServletException::class)
    override fun onStartup(servletContext: ServletContext) {
//        val ctx = AnnotationConfigWebApplicationContext()
//        ctx.scan("com.yamalc.ytmp.thermomonitor.bean")
        //the rest of initialization
    }
}