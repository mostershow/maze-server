package com.ck567.mazeserver.server.entity

import java.util.*
import kotlin.math.floor

data class Maze(
    var userId: String,
    var roomId: String,
    var width: Int,
    var height: Int,
    var grids: Array<Int>,
    var walkHistory: Stack<Int>,
    var walkHistory2: Stack<Int>
) {

    constructor(width: Int, height: Int,userId: String,roomId: String) : this(userId, roomId, width, height, Array(width * height) { 0 }, Stack(), Stack())

    // 遍历整图
    fun walk(startPos: Int) {
        var currentPos = startPos
        while (this.getNext0() != -1) {
            // 当还有格子未到达过

            val (flag, current) = this.step(currentPos)
            currentPos = current
            if (!flag) break
        }
    }

    private fun step(currentPos: Int): Pair<Boolean, Int> {
        val targetSteps = getTargetSteps(currentPos)
        if (noStep(targetSteps)) {
            // 如果无路可走，退回上一步
            val tmp: Int = this.walkHistory.pop() ?: return Pair(false, 0)
            walkHistory2.push(tmp)
            return step(tmp)
        }
        var r = floor(Math.random() * 4).toInt()

        val grids = this.grids
        while (targetSteps[r] == -1) {
            r = floor(Math.random() * 4).toInt()
        }
        // 从当前格子可到达的领居中随机选取一个
        val nextPos = targetSteps[r]
        var isCross = false
        if (grids[nextPos] != 0) {
            isCross = true
        }
        when (r) {
            0 -> {
                grids[currentPos] = grids[currentPos] xor 1
                grids[nextPos] = grids[nextPos] xor 4
            }

            1 -> {
                grids[currentPos] = grids[currentPos] xor 2
                grids[nextPos] = grids[nextPos] xor 8
            }

            2 -> {
                grids[currentPos] = grids[currentPos] xor 4
                grids[nextPos] = grids[nextPos] xor 1
            }

            3 -> {
                grids[currentPos] = grids[currentPos] xor 8
                grids[nextPos] = grids[nextPos] xor 2
            }
        }
        this.grids = grids
        this.walkHistory.push(currentPos)
        return if (isCross) Pair(false, 0) else Pair(true, nextPos)
    }

    private fun noStep(targetSteps: Stack<Int>): Boolean {
        for (i in 0 until targetSteps.size) {
            if (targetSteps[i] != -1) {
                return false
            }
        }
        return true
    }

    // 获取上下左右的可通行情况
    private fun getTargetSteps(currentPos: Int): Stack<Int> {
        val result = Stack<Int>()
        var p = currentPos - this.width
        if (p > 0 && this.grids[p] == 0 && !this.isRepeating(p))
            result.push(p)
        else
            result.push(-1)

        // 判断当前格子右方的格子是否可在下一次遍历到达
        p = currentPos + 1
        if (p % this.width != 0 && this.grids[p] == 0 && !this.isRepeating(p))
            result.push(p);
        else
            result.push(-1)

        // 判断当前格子下方的格子是否可在下一次遍历到达
        p = currentPos + this.width
        if (p < this.grids.size && this.grids[p] == 0 && !this.isRepeating(p))
            result.push(p)
        else
            result.push(-1)

        // 判断当前格子左方的格子是否可在下一次遍历到达
        p = currentPos - 1
        if (currentPos % this.width != 0 && this.grids[p] == 0 && !this.isRepeating(p))
            result.push(p)
        else
            result.push(-1)

        return result


    }

    private fun isRepeating(p: Int): Boolean {
        val a = this.walkHistory
        val a2 = this.walkHistory2
        val l = a.size
        val l2 = a2.size
        for (i in 0 until l) {
            if (a[i] == p) return true
        }
        for (i in 0 until l2) {
            if (a2[i] == p) return true
        }
        return false
    }

    // 得到地图上下一个未到达的格子
    fun getNext0(): Int {
        val l: Int = this.grids.size
        for (i in 0 until l) {
            if (grids[i] == 0) return i
        }

        return -1
    }
}

//fun main() {
//    val maze = Maze(5, 5,"","")
//    println(maze.grids.size)
//    maze.walk(20)
//    println(maze.grids.contentToString())
//}