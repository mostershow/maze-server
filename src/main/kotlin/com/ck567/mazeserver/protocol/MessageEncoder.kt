package com.ck567.mazeserver.protocol

import com.ck567.mazeserver.message.Message
import com.ck567.mazeserver.message.OperateType
import com.ck567.mazeserver.message.OperateType2
import com.google.gson.GsonBuilder
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageEncoder
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame
import io.netty.handler.codec.http.websocketx.WebSocketFrame
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.protobuf.ProtoBuf
import org.springframework.stereotype.Component
import java.lang.Exception
import java.nio.charset.StandardCharsets

@Component
@ChannelHandler.Sharable
class MessageEncoder : MessageToMessageEncoder<Message>() {
    @Throws(Exception::class)
    override fun encode(ctx: ChannelHandlerContext, msg: Message, out: MutableList<Any>) {
        val outBuf = ctx.alloc().buffer()
        val type: Short = msg.type
//        val serialization = OperateType2.getSerializer(type)
        val clazz = OperateType.getSerializer(type)
        val gson = GsonBuilder().registerTypeAdapter(Class::class.java, ClassCodec()).create()
        val json = gson.toJson(msg.msg,clazz)
//        var a = json.toByteArray(StandardCharsets.UTF_8)

//        val data: ByteArray = ProtoBuf.encodeToByteArray(serialization as SerializationStrategy<Any>,msg.msg)
        // 写入操作数
        var data = json.toByteArray(StandardCharsets.UTF_8)
        outBuf.writeShort(type.toInt())
        outBuf.writeInt(data.size)
        // 写入数据体
        outBuf.writeBytes(data)
        val frame: WebSocketFrame = BinaryWebSocketFrame(outBuf)
        out.add(frame)
    }
}