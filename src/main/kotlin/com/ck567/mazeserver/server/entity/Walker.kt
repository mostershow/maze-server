package com.ck567.mazeserver.server.entity

import java.util.*
import kotlin.math.floor
import kotlin.math.pow

data class Walker(
    var mg: Maze,
    var pos: Int=0,
    var history: Stack<Int>,
    var history2: Stack<Int>,
    var isMoving: Boolean=false,
    var lastMove: Date,
    var finished: Boolean = false,

    ) {
    constructor(mg: Maze):this(mg,0,Stack(),Stack(),false,Date(),false)
    fun move(d: Int){
        if (this.finished){
            return
        }
        val v = this.mg.grids[this.pos]
        if ((v and 2.0.pow(d.toDouble()).toInt()) != 0) {
            if (d == 0) this.moveTo(pos - mg.width)
            if (d == 1) this.moveTo(pos + 1)
            if (d == 2) this.moveTo(pos + mg.width)
            if (d == 3) this.moveTo(pos - 1)

        }


    }
    private fun moveTo(p: Int){
        val x = p % this.mg.width
        val y = floor(p.toDouble() / this.mg.width).toInt()
        println("x:$x,y:$y")
        this.pos = p
        if((x == this.mg.width -1) && (y == this.mg.height -1)){
            this.finished = true
        }
    }
}
fun main() {

    val maze = Maze(3, 3)
    maze.walk(2)
    println(maze.grids.contentToString())
    val walker = Walker(maze)
    println("输入方向")
    while (!walker.finished){
        val d: Int =Integer.valueOf(readLine())
        walker.move(d)
    }
    println("结束了")

}