package com.hq.secondhand_book.util.resp;

public enum ResponseErr {

    SUCCESS(0, "ok"),
    PARAM_ERROR(1000, "缺少参数"),
    DATA_ERROR(2000, "数据不存在"),
    DATA_RANGE_ERROR(2001, "数据不在范围"),
    SERVER_ERROR(3000, "服务器发生错误"),
    AUTH_ERROR(4000, "用户验证出错"),
    OPERATE_ERROR(4001, "用户操作出错"),
    PERMISSION_ERROR(4002, "权限不足");

    ResponseErr(int err, String msg) {
        this.err = err;
        this.msg = msg;
    }

    private int err;

    private String msg;

    public int getErr() {
        return err;
    }

    public String getMsg() {
        return msg;
    }
}
