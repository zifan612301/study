package com.hq.secondhand_book.util.resp;

import com.alibaba.fastjson.JSON;

public class ResultResp<T extends Object> {

    private int err;

    private String msg;

    private T data;

    public ResultResp() {
    }

    public ResultResp(T result) {
        this.err = ResponseErr.SUCCESS.getErr();
        this.msg = ResponseErr.SUCCESS.getMsg();
        this.data = result;
    }

    public ResultResp(int code, String message) {
        this.err = code;
        this.msg = message;
        this.data = (T) new Object();
    }

    public ResultResp(ResponseErr responseCode) {
        this.err = responseCode.getErr();
        this.msg = responseCode.getMsg();
        this.data = (T) new Object();
    }

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JSON.toJSON(this).toString();
    }
}
