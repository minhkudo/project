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
@Table(name = "sub_teach_student")
public class Sub_teach_student {

    @Id
    @GeneratedValue
    @Column(name = "ID", length = 11, nullable = false)
    private int id;

    @Column(name = "CODE_SUB", length = 255)
    private String code_sub;

    @Column(name = "CODE_TEACH", length = 255)
    private String code_teach;

    @Column(name = "CODE_STUDENT", length = 255)
    private String code_student;

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

    public String getCode_student() {
        return code_student;
    }

    public void setCode_student(String code_student) {
        this.code_student = code_student;
    }

}
