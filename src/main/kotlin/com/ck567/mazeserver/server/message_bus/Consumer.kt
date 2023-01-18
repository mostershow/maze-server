//package com.ck567.mazeserver.server.message_bus
//
//import com.google.gson.Gson
//import com.ck567.mazeserver.message.DeliveryMessage
//import com.ck567.mazeserver.server.service.LoginService
//import com.ck567.mazeserver.util.logger
//import kotlinx.serialization.Serializable
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.kafka.annotation.KafkaListener
//import org.springframework.kafka.support.Acknowledgment
//import org.springframework.stereotype.Component
//
//@Component
//class Consumer {
//
//    @Autowired
//    lateinit var service: LoginService
//
//    @KafkaListener(topics = ["\${kafka.topics}"], groupId = "\${kafka.group-id}"+"#{T(java.util.UUID).randomUUID().toString()}")
//    fun dealUser(msg: KafkaMessage, ack: Acknowledgment) {
//        logger.debug("接收到消息：$msg")
//        val des = MessageType.getSerializer(msg.type)
//        val gson = Gson()
//        val body = gson.fromJson(msg.body, des)
//        if(msg.type == "1"){
//            service.notice(body as NoticeDTO)
//        }else if(msg.type == "2"){
//            service.loginMessage(body as UserLogin)
//        }
//        ack.acknowledge()
//    }
//
//    companion object {
//        private val logger = logger<Consumer>()
//    }
//}
//data class KafkaMessage(
//    val type: String,
//    val body: String
//)
//
//@Serializable
//data class DeliveryDTO(
//    val appId: String,
//    val simbaId: String,
//    val msg: DeliveryMessage
//)
//
//@Serializable
//data class NoticeDTO(
//    val orderId: String,
//    val simbaId: String
//)