/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.vin.demo.model;

/**
 *
 * @author MinhKudo
 */
public class Result {

    public static final int ERROR = 0;
    public static final int SUCCESS = 1;
    public static final int WARNING = 2;
    public static final int NO_RIGHT = 3;
    public static final int NO_LOGIN = -1;
    public static final String MODEL_NAME = "result";

    int code;
    String message;

    public Result(int code, String mess) {
        this.code = code;
        this.message = mess;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
    
    
}
