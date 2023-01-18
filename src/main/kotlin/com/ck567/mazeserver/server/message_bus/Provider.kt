//package com.ck567.mazeserver.server.message_bus
//
//import com.ck567.mazeserver.server.session.SessionMemoryImpl
//import com.ck567.mazeserver.util.logger
//import kotlinx.serialization.Serializable
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.kafka.core.KafkaTemplate
//import org.springframework.stereotype.Component
//import javax.annotation.PostConstruct
//import javax.annotation.Resource
//
//
//@Component
//class Provider {
//
//    @Value("\${kafka.topics}")
//    fun setName(kafkaTopics: String) {
//        Provider.kafkaTopics = kafkaTopics
//    }
//    @Resource
//    lateinit var kafkaTemplate: KafkaTemplate<String, KafkaMessage>
//
//    @PostConstruct
//    fun init() {
//        Provider.kafkaTemplate = kafkaTemplate
//    }
//
//    companion object {
//        private var kafkaTemplate:KafkaTemplate<String, KafkaMessage> ?= null
//        var kafkaTopics: String = ""
//        fun sendMessage(msg: KafkaMessage) {
//            kafkaTemplate!!.send(kafkaTopics, msg)
//            logger.debug("发送消息成功:$msg")
//        }
//        private val logger  = logger<Provider>()
//    }
//
//
//
//}
//@Serializable
//data class UserLogin(
//    val userId: String,
//    val channelId: String,
//    val device: String
//)