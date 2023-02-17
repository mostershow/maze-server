package com.ck567.mazeserver.server.service

import com.ck567.mazeserver.message.Message
import com.ck567.mazeserver.message.JsonOperateType
import com.ck567.mazeserver.server.controller.InitReq
import com.ck567.mazeserver.server.entity.Walker
import com.ck567.mazeserver.server.entity.Maze
import com.ck567.mazeserver.server.session.GroupSessionFactory
import com.ck567.mazeserver.server.session.SessionFactory
import org.springframework.stereotype.Service

@Service
class MazeService {
    fun init(req: InitReq): Walker {
        val res = GroupSessionFactory.getSession().createGroup(req.roomId, hashSetOf(req.userId))
        if (res != null) {
            GroupSessionFactory.getSession().joinMember(req.roomId, req.userId)
        }

        val maze = Maze(req.width, req.height, req.userId, req.roomId)
        maze.walk(2)
        println(maze.grids.contentToString())
        val walker = Walker(maze)
        var roomMap = userMaze[req.roomId]
        if (roomMap != null) {
            roomMap[req.userId] = walker
        } else {
            roomMap = hashMapOf(req.userId to walker)
        }
        userMaze[req.roomId] = roomMap
        noticeMaze(req.roomId)
        return walker
    }
//    fun move(req: MoveReq): Walker {
//        val walker = userMaze[getRoom(req.userId)]!![req.userId]
//        walker!!.move(req.direction)
//        return walker
//    }

    companion object {
        var userMaze: HashMap<String, HashMap<String, Walker>> = HashMap()
        fun move(userId: String, direction: Int): Walker {
            val walker = userMaze[getRoom(userId)]!![userId]
            walker!!.move(direction)
            noticeMove(userId, walker.pos)
            return walker
        }

        fun noticeMaze(roomId: String) {
            val members = GroupSessionFactory.getSession().getMembers(roomId)
            println("房间内：${members.toString()}")
            userMaze[roomId]?.forEach { (t, u) ->
                members?.forEach {
                    if (t != it) {
                        println("通知给$it, 发送$t 的房间数据")
                        val chan = SessionFactory.getSession().getChannel(it)
                        chan?.writeAndFlush(Message(JsonOperateType.MazeRes.type, u))
                    }
                }
            }


        }

        fun noticeMove(userId: String, pos: Int) {
            val channel = SessionFactory.getSession().getChannel(userId)!!
            val roomId = SessionFactory.getSession().getAttribute(channel, "roomId").toString()
            val members = GroupSessionFactory.getSession().getMembers(roomId)
            println("检查到组内成员：$members, 通知消息")
            members!!.forEach {
                if (userId != it) {
                    println("通知用户$it,$userId 位置 $pos")
                    val chan = SessionFactory.getSession().getChannel(it)
                    chan?.writeAndFlush(Message(JsonOperateType.MoveRes.type, MoveResMessage(userId, pos)))
                }
            }
        }

        fun getRoom(userId: String): String {
            val channel = SessionFactory.getSession().getChannel(userId)
            return SessionFactory.getSession().getAttribute(channel!!, "roomId").toString()
        }
    }
}

data class MoveResMessage(
    val userId: String,
    val pos: Int
)