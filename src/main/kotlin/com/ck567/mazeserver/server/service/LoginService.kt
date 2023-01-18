package com.ck567.mazeserver.server.service


import com.ck567.mazeserver.server.session.Session

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LoginService {
    @Autowired
    lateinit var session: Session

//    fun delivery(msg: DeliveryDTO) {
//        var channel = SessionFactory.getSession().getChannel(msg.simbaId)
//        // if this user channel in this pod, deliver props
//        if (channel != null) {
//            logger.debug("delivery:$msg")
//            channel!!.writeAndFlush(Message(8, msg.msg))
//        }
//    }
//
//    fun loginMessage(userLogin: UserLogin) {
//        Thread.sleep(1000)
//        val channel = SessionFactory.getSession().getChannel(userLogin.userId)
//        if (channel != null) {
//            val channelId = SessionFactory.getSession().getAttribute(channel, "channelId")
//            if (channelId != userLogin.channelId) {
//                forceLogout(userLogin)
//            }
//        }
//    }
//
//    fun notice(noticeDTO: NoticeDTO) {
//        var channel = SessionFactory.getSession().getChannel(noticeDTO.simbaId)
//        // if this user channel in this pod, deliver props
//        if (channel != null) {
//            channel!!.writeAndFlush(Message(OperateType.OrderNotice.type, OrderNoticeMessage(noticeDTO.orderId)))
//        }
//    }
//
//    companion object {
//
//        fun forceLogout(userLogin: UserLogin) {
//            val channel = SessionFactory.getSession().getChannel(userLogin.userId)
//            if (channel != null) {
//                val logout = ForceLogoutMessage(userLogin.userId, userLogin.device)
//                channel.writeAndFlush(Message(OperateType.ForceLogout.type, logout))
////                SessionFactory.getSession().unbind(channel)
//                channel.close()
//            }
//        }
//
//        private val logger: Logger = logger<SessionMemoryImpl>()
//    }
}