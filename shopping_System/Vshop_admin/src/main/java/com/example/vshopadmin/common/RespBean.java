package com.example.vshopadmin.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespBean {
    private Integer code;
    private String message;
    private Object responseData;
    private Exception errData;

    public static RespBean success() {
        return new RespBean(0, "操作成功", null, null);
    }

    public static RespBean success(Object data) {
        return new RespBean(0, "操作成功", data, null);
    }

    public static RespBean fail() {
        return new RespBean(-9999, "操作失败，请稍后重试", null, null);
    }

    public static RespBean fail(int code, String msg) {
        return new RespBean(code, msg, null, null);
    }

    public static RespBean fail(int code, String msg, Exception err) {
        return new RespBean(code, msg, null, err);
    }

    public static RespBean fail(int code, Exception err) {
        return new RespBean(code, err.getMessage(), null, err);
    }
}