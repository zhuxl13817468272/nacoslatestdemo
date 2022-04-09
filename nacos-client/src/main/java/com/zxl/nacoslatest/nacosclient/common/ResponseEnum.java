package com.zxl.nacoslatest.nacosclient.common;

public enum ResponseEnum implements ResponseCode {

    SUCCESS(0, "success"),
    FAIL(1, "fail"),
    EXCEPTION(40000, "exception"),

    //--------------------自定义错误状态码-----------------------------
    USER_INFO_IS_NULL(803,"用户信息为空！");

    private Integer code;
    private String value;

    ResponseEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static String getValueByCode(Integer code) {
        if (null == code){
            return null;
        }
        for (ResponseEnum item : ResponseEnum.values()) {
            if(code.equals(item.getCode()))
            {
                return item.getValue();
            }
        }
        return null;
    }

    public static Integer getCodeByValue(String value) {
        if (null == value || value == ""){
            return null;
        }
        for (ResponseEnum item : ResponseEnum.values()) {
            if(value.equals(item.getValue()))
            {
                return item.getCode();
            }
        }
        return null;
    }

}
