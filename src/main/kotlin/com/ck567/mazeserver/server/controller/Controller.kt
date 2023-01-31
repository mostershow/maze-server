package com.ck567.mazeserver.server.controller

import com.ck567.mazeserver.server.entity.Walker
import com.ck567.mazeserver.server.service.MazeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("")
class Controller {
    @Autowired
    private lateinit var service: MazeService
    @RequestMapping("/init")
    fun initMaze(
        @RequestBody req: InitReq
    ): ResponseEntity<Walker> {
        return ResponseEntity.ok(service.init(req))
    }
//    @RequestMapping("/move")
//    fun move(
//        @RequestBody req: MoveReq
//    ): ResponseEntity<Walker> {
//        return ResponseEntity.ok(service.move(req))
//    }
}
data class InitReq(
    val userId: String,
    var width: Int,
    var height: Int,
    var roomId: String
)
data class MoveReq(
    val userId: String,
    val direction: Int
)