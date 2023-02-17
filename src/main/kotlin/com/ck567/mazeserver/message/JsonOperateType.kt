package com.ck567.mazeserver.message

import com.ck567.mazeserver.server.entity.Walker
import com.ck567.mazeserver.server.service.MoveResMessage

enum class JsonOperateType(
    val type: Short
) {
    // 搞几个枚举放在这里
    HeartBeatReq(1),
    HeartBeatRes(2),
    MoveReq(11),
    MazeRes(12),
    MoveRes(13),

    ForceLogout(4),
    OrderNotice(6),
    ;

    companion object {

        fun getSerializer(type:Short): Class<out Any> {
            // 拿对应的序列化对象
            return when(type) {
                HeartBeatReq.type -> HeartBeatRequestMessage::class.java
                HeartBeatRes.type -> HeartBeatResponseMessage::class.java
                ForceLogout.type -> ForceLogoutMessage::class.java
                OrderNotice.type -> OrderNoticeMessage::class.java
                MoveReq.type -> MoveRequestMessage::class.java
                MazeRes.type -> Walker::class.java
                MoveRes.type -> MoveResMessage::class.java
                else -> {
                    throw Exception("wrong serializer type")
                }
            }
        }
    }
}
