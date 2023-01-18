package com.ck567.mazeserver.web

import com.ck567.mazeserver.string.Code.ResourceNotFound
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import com.icesimba.simba.exception.notFoundError

class HttpResponseBuilder {
    companion object{
        val Ok: ResponseEntity<Nothing> = ResponseEntity<Nothing>(null, HttpStatus.OK)
        fun ok(vararg pList: Pair<String, Any>): ResponseEntity<Map<String, Any>> {
            return ResponseEntity.ok(pList.toMap())
        }

        fun notFoundError(msg: String): Nothing = notFoundError(ResourceNotFound.code, msg)
    }
}

