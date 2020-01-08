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
@Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue
    @Column(name = "ID", length = 11, nullable = false)
    private int id;

    @Column(name = "CODE", length = 255)
    private String code;

    @Column(name = "NAME", length = 255)
    private String name;

    @Column(name = "NUMBER", length = 11)
    private int number;

    @Column(name = "YEAR", length = 11)
    private int year;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
