/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.vin.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author MinhKudo
 */
@Entity
@Table(name = "map_sub_teach")
public class Sub_teach {
    
    @Id
    @GeneratedValue
    @Column(name = "ID", length = 11)
    private int id;
    
    @Column(name = "CODE_SUB", length = 255)
    private String code_sub;
    
    
    @Column(name = "CODE_TEACH", length = 255)
    private String code_teach;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode_sub() {
        return code_sub;
    }

    public void setCode_sub(String code_sub) {
        this.code_sub = code_sub;
    }

    public String getCode_teach() {
        return code_teach;
    }

    public void setCode_teach(String code_teach) {
        this.code_teach = code_teach;
    }
    
    
}
