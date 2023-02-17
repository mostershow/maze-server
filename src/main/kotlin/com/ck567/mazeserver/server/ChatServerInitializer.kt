package com.ck567.mazeserver.server

import com.ck567.mazeserver.protocol.JsonMessageDecoder
import com.ck567.mazeserver.protocol.JsonMessageEncoder
import com.ck567.mazeserver.server.handler.*
import io.netty.channel.*
import io.netty.handler.codec.http.HttpObjectAggregator
import io.netty.handler.codec.http.HttpServerCodec
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler
import io.netty.handler.stream.ChunkedWriteHandler
import org.springframework.beans.factory.annotation.Autowired
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@ChannelHandler.Sharable
@Component
class ChatServerInitializer :  ChannelInitializer<Channel>() {

    @Autowired
    lateinit var jsonMessageEncoder: JsonMessageEncoder
    @Autowired
    lateinit var jsonMessageDecoder: JsonMessageDecoder


    val heartBeat = HeartBeatHandler()
    val auth = ChannelAuthHandler()
    val move = MoveHandler()


    override fun initChannel(channel: Channel) {
        channel.pipeline()
            .addLast(ServerIdleStateHandler())
            .addLast(HttpServerCodec())
            .addLast(ChunkedWriteHandler())
            .addLast(HttpObjectAggregator(1024 * 64))
            .addLast(WebSocketServerCompressionHandler())
            .addLast(auth)

            .addLast(WebSocketServerProtocolHandler("/", null, true))
            .addLast(jsonMessageEncoder)
            .addLast(jsonMessageDecoder)
            .addLast(move)
            .addLast(heartBeat)


    }

    companion object {
        private val logger : Logger = LoggerFactory.getLogger(ChatServerInitializer::class.java)
    }
}