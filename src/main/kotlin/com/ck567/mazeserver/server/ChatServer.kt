package com.ck567.mazeserver.server

//import com.ck567.mazeserver.message.entity.LoginProto
import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.*
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.util.ResourceLeakDetector
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.net.InetSocketAddress
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

/**
 * 服务端基本配置，通过一个静态单例类，保证启动时候只被加载一次
 */
@Component
class ChatServer {
    @Value("\${wss.port}")
    private val wssPort = 0
    private val BOSS: EventLoopGroup = NioEventLoopGroup()

    private val WORKER: EventLoopGroup = NioEventLoopGroup()

    @Autowired
    lateinit var serverHandlerInitializer: ChatServerInitializer

    private var channel: Channel? = null




    @PostConstruct
    @Throws(InterruptedException::class)
    fun start() {
        val bootstrap = ServerBootstrap()
        bootstrap.group(BOSS, WORKER) // 指定Channel
            .channel(NioServerSocketChannel::class.java) // 使用指定服务端口
            .localAddress(InetSocketAddress(wssPort)) // 服务端可连接队列数,对应TCP/IP协议listen函数中backlog参数
            .option(ChannelOption.SO_BACKLOG, 1024) // 设置TCP长连接
            .childOption(ChannelOption.SO_KEEPALIVE, true) // 将小的数据包包装成更大的帧进行传送，提高网络的负载
            .childOption(ChannelOption.TCP_NODELAY, true) // 初始化通信协议并进行业务逻辑处理
            .childHandler(serverHandlerInitializer)
        //  JVM
        ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.ADVANCED)
        val future = bootstrap.bind().sync()
        if (future.isSuccess) {
            channel = future.channel()
            logger.info("im-server started")
        }
    }

    /**
     * @description: 场景服务端销毁
     */
    @PreDestroy
    @Throws(InterruptedException::class)
    fun destroy() {
        if (channel != null) {
            channel!!.close()
        }
        BOSS.shutdownGracefully().sync()
        WORKER.shutdownGracefully().sync()
        logger.info("im-server shutdown")
    }

    companion object {
        private val logger : Logger = LoggerFactory.getLogger(ChatServer::class.java)
    }

}


