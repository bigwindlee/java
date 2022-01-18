package com.taikang.images;

import lombok.Data;

@Data
public class ReturnValue {
    private int code;
    private String msg;

    public ReturnValue(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
