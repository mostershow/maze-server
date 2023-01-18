package com.ck567.mazeserver.server.session

import com.ck567.mazeserver.util.logger
import io.netty.channel.Channel
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class SessionMemoryImpl : Session{
    private val userIdChannelMap: ConcurrentHashMap<String, Channel> = ConcurrentHashMap()
    private val channelUserIdMap: ConcurrentHashMap<Channel, String> = ConcurrentHashMap()
    private val channelAttributesMap: ConcurrentHashMap<Channel, ConcurrentHashMap<String, Any>> = ConcurrentHashMap()

    override fun bind(channel: Channel, userId: String) {
        logger.debug("bind-----> $userId")
        userIdChannelMap[userId] = channel
        channelUserIdMap[channel] = userId
        channelAttributesMap[channel] = ConcurrentHashMap()
        logger.debug("bind---channelAttributesMap.size--> ${channelAttributesMap.size}")
        logger.debug("bind---channelUserIdMap.size--> ${channelUserIdMap.size}")
        logger.debug("bind---userIdChannelMap.size--> ${userIdChannelMap.size}")
    }

    override fun unbind(channel: Channel) {
        logger.debug("unbind-----> $channel")
        val userId = channelUserIdMap[channel]
        userIdChannelMap.remove(userId)
        channelUserIdMap.remove(channel)
        channelAttributesMap.remove(channel)
        logger.debug("unbind-----> ${channelAttributesMap.size}")
        logger.debug("unbind-----> ${channelUserIdMap.size}")
        logger.debug("unbind-----> ${userIdChannelMap.size}")
    }

    override fun getAttribute(channel: Channel, name: String): Any? {
        return channelAttributesMap[channel]?.get(name)
    }

    override fun setAttribute(channel: Channel, name: String, value: Any) {
        channelAttributesMap[channel]?.set(name, value)
    }

    override fun getChannel(userId: String): Channel? {
        return userIdChannelMap[userId]
    }

    companion object {
        private val logger  = logger<SessionMemoryImpl>()
    }

}