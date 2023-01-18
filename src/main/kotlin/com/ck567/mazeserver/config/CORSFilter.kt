package com.ck567.mazeserver.config

import org.springframework.stereotype.Component
import javax.servlet.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CORSFilter : Filter {
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val res = response as HttpServletResponse
        res.addHeader("Access-Control-Allow-Credentials", "true")
        res.addHeader("Access-Control-Allow-Origin", "*")
        res.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
        res.addHeader("Access-Control-Allow-Headers", "Content-Type,X-CAF-Authorization-Token,sessionToken,X-TOKEN")
        if ((request as HttpServletRequest).method == "OPTIONS") {
            response.getWriter().println("ok")
            return
        }
        chain.doFilter(request, response)
    }

    override fun destroy() {}
    override fun init(filterConfig: FilterConfig?) {
    }
}