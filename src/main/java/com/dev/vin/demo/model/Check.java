/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.vin.demo.model;

import java.sql.Timestamp;
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
@Table(name = "check")
public class Check {

    @Id
    @GeneratedValue
    @Column(name = "ID", length = 11, nullable = false)
    private int id;

    @Column(name = "ID_STS", length = 11)
    private int id_sts;

    @Column(name = "TIME")
    private Timestamp time;

    @Column(name = "STATUS", length = 11)
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_sts() {
        return id_sts;
    }

    public void setId_sts(int id_sts) {
        this.id_sts = id_sts;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    

}
