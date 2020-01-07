/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.vin.demo.commons;

/**
 *
 * @author Private
 */
public class UserActionConst {

    protected final String REDIRECT_INDEX = "redirect:/admin/index";
//    protected final String REDIRECT_INDEX = "redirect:"+MyContext.Context+"/admin/index";

    public enum TYPE {
        VIEW,
        ADD,
        EDIT,
        DEL,
        ROLLBACK,
        LOGIN,
        LOGOUT,
        EXPORT,
        UPLOAD, //--
        ;
    }

    public enum RESULT {
        SUCCESS,
        FAIL,
        EXCEPTION,
        REJECT,;

    }
}
