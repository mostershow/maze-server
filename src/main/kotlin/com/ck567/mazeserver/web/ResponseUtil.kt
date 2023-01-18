package com.ck567.mazeserver.web

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import javax.servlet.http.HttpServletResponse

fun responseJsonWriter(response: HttpServletResponse, responseEntity: ResponseEntity<Any?>) {

    if (response.isCommitted) {
        logger.debug("Response has already been committed")
        return
    }
    response.status = responseEntity.statusCode.value()
    response.characterEncoding = "utf-8"
    response.contentType = MediaType.APPLICATION_JSON_VALUE
    val objectMapper = ObjectMapper()
    val resBody = objectMapper.writeValueAsString(responseEntity.body)
    val printWriter = response.writer
    printWriter.print(resBody)
    printWriter.flush()
    printWriter.close()
}

private val logger = LoggerFactory.getLogger("ResponseUtil")
