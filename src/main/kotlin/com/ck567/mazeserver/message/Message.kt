package com.ck567.mazeserver.message

import java.io.Serializable

class Message(
    // 消息类型
    val type: Short,
    // 消息体， 对应各种消息
    val msg: Any
) : Serializable {

}
