//package com.ck567.mazeserver.server.message_bus
//
//
//enum class MessageType(
//    val type: String,
//    val clazz: Class<out Any>
//) {
//    // 搞几个枚举放在这里
//    OrderNoticeMessage("1",NoticeDTO::class.java),
//    LoginMessage("2",UserLogin::class.java),
//
//    ;
//
//    companion object {
//
//        fun getSerializer(type:String): Class<out Any> {
//            values().iterator().forEach {
//                if (type == it.type) return it.clazz
//            }
//            throw IllegalStateException("")
//        }
//    }
//}
