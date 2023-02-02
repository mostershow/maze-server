package com.ck567.mazeserver.server.handler

import com.ck567.mazeserver.message.MoveRequestMessage
import com.ck567.mazeserver.server.service.MazeService
import com.ck567.mazeserver.util.logger
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler

@ChannelHandler.Sharable
class MoveHandler : SimpleChannelInboundHandler<MoveRequestMessage>() {

    override fun channelRead0(ctx: ChannelHandlerContext?, msg: MoveRequestMessage) {
        logger.debug("==========move:$msg")
        MazeService.move(msg.userId,msg.direction)
    }

    companion object {
        private val logger = logger<MoveHandler>()
    }
}