package com.ck567.mazeserver.server.service

abstract class ServiceFactory {
    companion object {
        private val loginService: LoginService = LoginService()

        open fun getUserService(): LoginService {
            return loginService
        }
    }
}