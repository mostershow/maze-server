package com.ck567.mazeserver.server.session

class GroupSessionFactory {
    companion object {
        private val session: GroupSession = GroupSessionMemoryImpl()

        open fun getSession(): GroupSession {
            return session
        }
    }
}