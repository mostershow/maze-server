package com.ck567.mazeserver.web

/**
 * The Response Body for exceptions or failures.
 */
class FailureResponseBody<T>(
        val identifier: String = "",
        val msg: String = "",
        data: T
): ResponseBody<T>(data)

open class ResponseBody<T>(
        val data: T
) {
}