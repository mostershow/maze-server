package com.ck567.mazeserver.message

import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber

@Serializable
data class DeliveryMessage(
    @ProtoNumber(1)
    val oid: String,
    @ProtoNumber(2)
    val goodsId: String,
    @ProtoNumber(3)
    val props: List<PropMessage>
)

@Serializable
data class PropMessage(
    @ProtoNumber(1)
    val propCode: Int,
    @ProtoNumber(2)
    val propNum: Int
)