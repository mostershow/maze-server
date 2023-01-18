package com.ck567.mazeserver.server.handler

import com.ck567.mazeserver.message.LoginRequestMessage
import com.ck567.mazeserver.message.Message
import com.ck567.mazeserver.server.service.ServiceFactory
import com.ck567.mazeserver.server.session.SessionFactory
import com.ck567.mazeserver.util.logger
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@ChannelHandler.Sharable
class LoginRequestHandler : SimpleChannelInboundHandler<LoginRequestMessage>() {

    override fun channelRead0(ctx: ChannelHandlerContext, msg: LoginRequestMessage) {


    }

    companion object {
        private val logger = logger<LoginRequestHandler>()
    }
}