package com.ck567.mazeserver.protocol

import com.ck567.mazeserver.message.OperateType
import com.google.gson.*
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import io.netty.handler.codec.http.websocketx.WebSocketFrame
import org.springframework.stereotype.Component
import java.lang.reflect.Type
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

@Component
@ChannelHandler.Sharable
class MessageDecoder : MessageToMessageDecoder<WebSocketFrame>() {

    @Throws(Exception::class)
    override fun decode(ctx: ChannelHandlerContext, msg: WebSocketFrame, out: MutableList<Any>) {
        val buf = msg.content()
        // 读取操作数
        val type = buf.readShort()
        // 读取消息长度
        val length = buf.readInt()
        // 读取数据体
        val data = ByteArray(length)
        buf.readBytes(data)
        // 编解码器的分发
        val res = String(data,StandardCharsets.UTF_8)
        val clazz = OperateType.getSerializer(type)
        val gson = GsonBuilder().serializeNulls()
            .serializeSpecialFloatingPointValues()
            .disableHtmlEscaping()
            .setLenient().registerTypeAdapter(Class::class.java, ClassCodec())
            .create()

        out.add(gson.fromJson(res, clazz))
    }
}
class ClassCodec : JsonSerializer<Class<*>>,
    JsonDeserializer<Class<*>> {
    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Class<*> {
        return try {
            val str = json.asString
            Class.forName(str)
        } catch (e: ClassNotFoundException) {
            throw JsonParseException(e)
        }
    }

    //   String.class
    override fun serialize(src: Class<*>, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        // class -> json
        return JsonPrimitive(src.name)
    }
}