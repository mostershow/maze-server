package com.ck567.mazeserver.server.handler

import com.ck567.mazeserver.server.session.SessionFactory
import com.ck567.mazeserver.util.logger
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.timeout.IdleStateEvent
import io.netty.handler.timeout.IdleStateHandler
import java.util.concurrent.TimeUnit

@ChannelHandler.Sharable
class ServerIdleStateHandler() :
    IdleStateHandler(READER_IDLE_TIME.toLong(), 0, 0, TimeUnit.SECONDS) {
    override fun channelIdle(ctx: ChannelHandlerContext, evt: IdleStateEvent) {
        logger.debug("{} 秒内没有读取到数据,关闭连接", READER_IDLE_TIME)
        SessionFactory.getSession().unbind(ctx.channel())
        ctx.channel().close()
    }

    companion object {
        /**
         * 空闲检测时间单位：秒
         */
        private const val READER_IDLE_TIME = 60
        private val logger = logger<ServerIdleStateHandler>()
    }
}
