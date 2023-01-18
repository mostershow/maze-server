package com.ck567.mazeserver.server.session

abstract class SessionFactory {
    companion object {
        private val session: Session = SessionMemoryImpl()

        open fun getSession(): Session {
            return session
        }
    }
}