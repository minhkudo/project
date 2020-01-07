/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.vin.demo.model;

import java.util.ArrayList;

/**
 *
 * @author MinhKudo
 */
public final class AngularModel<T> {

    private ArrayList<T> listObject;
    private int totalRow;
    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public ArrayList<T> getListObject() {
        return listObject;
    }

    public void setListObject(ArrayList<T> listObject) {
        this.listObject = listObject;
    }

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }

}
