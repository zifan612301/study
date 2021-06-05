package com.hq.secondhand_book.util.execption;


import com.hq.secondhand_book.util.resp.ResponseErr;

public class ApiException extends RuntimeException {

    private int err;

    private String msg;

    private ResponseErr responseEnum;

    public ApiException(int err, String message) {
        this.err = err;
        this.msg = message;
    }

    public ApiException(ResponseErr responseEnum) {
        this.err = responseEnum.getErr();
        this.msg = responseEnum.getMsg();
        this.responseEnum = responseEnum;
    }

    public ApiException(ResponseErr responseEnum, String message) {
        this.err = responseEnum.getErr();
        this.msg = message;
        this.responseEnum = responseEnum;
    }

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public String getMessage() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResponseErr getResponseEnum() {
        return responseEnum;
    }

    public void setResponseEnum(ResponseErr responseEnum) {
        this.responseEnum = responseEnum;
    }
}
