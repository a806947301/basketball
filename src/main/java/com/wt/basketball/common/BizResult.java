package com.wt.basketball.common;

public class BizResult<T> {
    boolean succ;

    T result;

    String msg;

    public boolean isSucc() {
        return succ;
    }

    public void setSucc(boolean succ) {
        this.succ = succ;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public static<R> BizResult<R> succ(R result) {
        return new BizResult<R>(true, result);
    }

    public static BizResult fall(String result) {
        return new BizResult(false, null, result);
    }

    public BizResult(boolean succ, T result) {
        this.succ = succ;
        this.result = result;
    }

    public BizResult(boolean succ, T result, String msg) {
        this.succ = succ;
        this.result = result;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
