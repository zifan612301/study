package com.hq.secondhand_book.util.resp;

import com.alibaba.fastjson.JSON;
import com.hq.secondhand_book.util.execption.ApiException;

public class Response {

    public static ResultResp<String> ok() {
        return new ResultResp<>(ResponseErr.SUCCESS);
    }

    public static <T> ResultResp<T> ok(T t) {
        return new ResultResp<>(t);
    }

    public static ResultResp<String> info(int err, String msg) {
        return new ResultResp<>(err, msg);
    }

    public static ResultResp<String> info(ResponseErr responseEnum) {
        return new ResultResp<>(responseEnum);
    }

    public static ResultResp<String> paramErr() {
        return new ResultResp<>(ResponseErr.PARAM_ERROR);
    }

    public static ResultResp<String> paramErr(String s) {
        return new ResultResp<>(ResponseErr.PARAM_ERROR.getErr(), s);
    }

    public static ResultResp<String> dataErr() {
        return new ResultResp<>(ResponseErr.DATA_ERROR);
    }

    public static ResultResp<String> dataErr(String s) {
        return new ResultResp<>(ResponseErr.DATA_ERROR.getErr(), s);
    }

    public static ResultResp<String> serverErr() {
        return new ResultResp<>(ResponseErr.SERVER_ERROR);
    }

    public static ResultResp<String> serverErr(String exMessage) {
        return new ResultResp<>(ResponseErr.SERVER_ERROR.getErr(), exMessage);
    }

    public static ResultResp<String> authErr() {
        return new ResultResp<>(ResponseErr.AUTH_ERROR);
    }

    public static ResultResp<String> authErr(String exMessage) {
        return new ResultResp<>(ResponseErr.AUTH_ERROR.getErr(), exMessage);
    }

    public static ResultResp<String> operateErr() {
        return new ResultResp<>(ResponseErr.OPERATE_ERROR);
    }

    public static ResultResp<String> operateErr(String exMessage) {
        return new ResultResp<>(ResponseErr.OPERATE_ERROR.getErr(), exMessage);
    }

    public static ResultResp<String> permissionErr() {
        return new ResultResp<>(ResponseErr.PERMISSION_ERROR);
    }

    public static ResultResp<String> permissionErr(String exMessage) {
        return new ResultResp<>(ResponseErr.PERMISSION_ERROR.getErr(), exMessage);
    }

    public static void throwExce(ResponseErr err) {
        throw new ApiException(err);
    }

    public static void throwExce(ResponseErr err, String msg) {
        throw new ApiException(err, msg);
    }

    public static void throwParamExce(String msg) {
        throw new ApiException(ResponseErr.PARAM_ERROR, msg);
    }

    public static void throwDataExce(String msg) {
        throw new ApiException(ResponseErr.DATA_ERROR, msg);
    }

    public static void throwServerExce(String msg) {
        throw new ApiException(ResponseErr.SERVER_ERROR, msg);
    }

    public static void throwAuthExce(String msg) {
        throw new ApiException(ResponseErr.AUTH_ERROR, msg);
    }

    public static void throwOperateExce(String msg) {
        throw new ApiException(ResponseErr.OPERATE_ERROR, msg);
    }

    public static void throwPermissionExce(String msg) {
        throw new ApiException(ResponseErr.PERMISSION_ERROR, msg);
    }

    public static boolean isSuccess(int err) {
        return err == ResponseErr.SUCCESS.getErr();
    }

    public static String json(ResultResp resp) {
        return JSON.toJSONString(resp);
    }

}
