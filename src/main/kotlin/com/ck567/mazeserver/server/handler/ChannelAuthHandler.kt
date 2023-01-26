package com.ck567.mazeserver.server.handler

import com.google.gson.Gson
import com.ck567.mazeserver.server.service.LoginService
import com.ck567.mazeserver.server.session.SessionFactory
import com.ck567.mazeserver.util.logger
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.handler.codec.http.FullHttpRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@ChannelHandler.Sharable
class ChannelAuthHandler : SimpleChannelInboundHandler<FullHttpRequest>() {

    override fun channelRead0(ctx: ChannelHandlerContext?, msg: FullHttpRequest?) {
        logger.debug("==========ChannelAuthHandler")
        val uri = msg!!.uri()
        val temp: List<String> = uri.split("/")

        val userId = temp[1]

        logger.debug("userId:$userId")

        val channelId = ctx!!.channel().id().asLongText()
        // 绑定appid_userId 对应
        SessionFactory.getSession().bind(ctx!!.channel(), userId)
        SessionFactory.getSession().setAttribute(ctx!!.channel(), "channelId", channelId)
        msg.uri = "/"
        // 让消息继续往流水线下游走，如果没有这句则会报错
        ctx!!.fireChannelRead(msg!!.retain())
        // 在本channel上移除这个handler消息处理，即只处理一次，鉴权通过与否
        ctx!!.pipeline().remove(ChannelAuthHandler::class.java)


    }

    companion object {
        private val logger = logger<ChannelAuthHandler>()
    }
}