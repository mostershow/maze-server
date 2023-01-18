package com.ck567.mazeserver.string

enum class Code(val code: Int, val message: String) {
    AuthenticationTokenExpired(-1001, "AuthenticationTokenExpired"),
    AuthenticationTokenInvalid(-1002,"AuthenticationTokenInvalid"),
    NotInTest(-1003,"NotInTest"),
    ResourceNotFound(404,"ResourceNotFound"),
    VIPCodeDoesNotExist(20001,"兑换码不存在"),
    UserInconsistency(20002,"用户不一致"),
    BindingTimesLimit(20003,"该VIP码绑定次数已达上限"),
    TheDayBindingTimesLimit(20004,"该VIP码今日绑定次数已达上限"),
    OrderVerificationFailure(20005,"订单号校验失败"),
    UnbundledPerWeekLimit(20006,"该账号每周解绑次数已达上限"),
    InsufficientPoints(20007,"积分不足"),
    BindingsNotPresent(20008,"绑定关系不存在"),
    BindingsAlreadyExist(20009,"绑定关系已存在"),

    ;

}