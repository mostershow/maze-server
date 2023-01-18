package com.ck567.mazeserver.server.handler

import com.ck567.mazeserver.message.HeartBeatRequestMessage
import com.ck567.mazeserver.message.HeartBeatResponseMessage
import com.ck567.mazeserver.message.Message
import com.ck567.mazeserver.message.OperateType
import com.ck567.mazeserver.server.session.SessionFactory
import com.ck567.mazeserver.util.logger
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler


@ChannelHandler.Sharable
class HeartBeatHandler : SimpleChannelInboundHandler<HeartBeatRequestMessage>() {
    override fun channelRead0(ctx: ChannelHandlerContext, msg: HeartBeatRequestMessage) {
        logger.debug("收到心跳消息")
        ctx.writeAndFlush(Message(OperateType.HeartBeatRes.type, HeartBeatResponseMessage()))
    }

    override fun handlerRemoved(ctx: ChannelHandlerContext?) {
        if (ctx != null) {
            SessionFactory.getSession().unbind(ctx!!.channel())
            logger.debug("客户端断开了连接")
        }

        super.handlerRemoved(ctx)
    }

    companion object {
        private val logger = logger<HeartBeatHandler>()
    }
}