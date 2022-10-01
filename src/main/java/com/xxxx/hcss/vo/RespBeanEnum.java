package com.xxxx.hcss.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum RespBeanEnum {
    SUCCESS(200,"SUCCESS"),
    ERROR(500,"服务器异常"),
    LOGIN_ERROR(500210,"用户名或密码不正确"),
    MOBILE_ERROR(10086,"手机号码错误"),
    EMPTY_STOCK(5000500,"库存不足"),
    REPEATE_ERROR(500501,"该商品每人限购一件");
    private final Integer code;
    private final String message;
}
