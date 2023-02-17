package com.ck567.mazeserver.message


enum class ProtobufOpreateType(
    val type: Short
) {
    // 搞几个枚举放在这里
    HeartBeatReq(1),
    HeartBeatRes(2),
    ForceLogout(4),
    OrderNotice(6),
    ;

    companion object {

        fun getSerializer(type:Short): Any {
            // 拿对应的序列化对象
            return when(type) {
                HeartBeatReq.type -> HeartBeatRequestMessage.serializer()
                HeartBeatRes.type -> HeartBeatResponseMessage.serializer()
                ForceLogout.type -> ForceLogoutMessage.serializer()
                OrderNotice.type -> OrderNoticeMessage.serializer()

                else -> {
                    throw Exception("wrong serializer type")
                }
            }
        }
    }
}
