package com.zxl.nacoslatest.nacosprovider.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultForm implements Serializable {

    private Integer code;
    private String message;
    private Object data;

    public ResultForm() {}

    public ResultForm(int code) {
        this.code = code;
    }

    public ResultForm(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultForm(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public ResultForm(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResultForm success() {
        return new ResultForm(ResponseEnum.SUCCESS.getCode());
    }

    public static ResultForm success(String message) {
        return new ResultForm(ResponseEnum.SUCCESS.getCode(), message);
    }

    public static ResultForm success(Object data) {
        return new ResultForm(ResponseEnum.SUCCESS.getCode(), data);
    }

    public static ResultForm success(String message, Object data) {
        return new ResultForm(ResponseEnum.SUCCESS.getCode(), message, data);
    }

    public static ResultForm fail() {
        return new ResultForm(ResponseEnum.FAIL.getCode());
    }

    public static ResultForm fail(String message) {
        return new ResultForm(ResponseEnum.FAIL.getCode(), message);
    }

    public static ResultForm exception() {
        return new ResultForm(ResponseEnum.EXCEPTION.getCode());
    }

    public static ResultForm exception(String message) {
        return new ResultForm(ResponseEnum.EXCEPTION.getCode(), message);
    }

    public static ResultForm exception(ResponseCode responseCode, Object object) {
        ResultForm resultForm = new ResultForm();
        resultForm.setCode(responseCode.getCode());
        resultForm.setMessage(responseCode.getValue());
        // 不向前端返回空的对象
        resultForm.setData(null == object?"exception":object);
        return resultForm;
    }

}
