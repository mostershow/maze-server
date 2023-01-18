package com.ck567.mazeserver.message

import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber

@Serializable
class OrderNoticeMessage(
    @ProtoNumber(1)
    val orderId: String,
) {

}