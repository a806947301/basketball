package com.wt.basketball.common;

public class AppException extends RuntimeException {
    private String msg;

    public AppException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
