package com.ck567.mazeserver.config


import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.*

@Configuration
//@EnableWebMvc
class WebConfig : WebMvcConfigurer{

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**").allowedOrigins("*")
    }
}

