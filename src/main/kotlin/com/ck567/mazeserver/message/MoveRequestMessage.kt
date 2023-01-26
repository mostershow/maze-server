package com.ck567.mazeserver.message

import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber

@Serializable
data class MoveRequestMessage(

    @ProtoNumber(1)
    val userId: String,
    @ProtoNumber(2)
    val direction: Int
) {
}
