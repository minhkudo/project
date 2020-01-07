/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.vin.demo.dao;

import com.dev.vin.demo.commons.Tool;
import com.dev.vin.demo.config.Constants;
import com.dev.vin.demo.model.Check;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MinhKudo
 */
@Repository
@Transactional
public class CheckDao {

    @Autowired
    @PersistenceContext(unitName = Constants.JPA_UNIT_NAME_1)
    private EntityManager entityManager;

    public ArrayList<Check> list(int page, int row) {
        ArrayList<Check> list = new ArrayList<>();
        String sql = "Select * from `check` where 1 = 1 ";

        sql += " ORDER BY ID DESC LIMIT ? , ?";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, Check.class);
            int i = 1;

            query.setParameter(i++, (page - 1) * row);
            query.setParameter(i++, row);
            list = (ArrayList) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int count() {
        int count = 0;
        String sql = "Select count(*) from `check` where 1 = 1 ";

        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql);
            int i = 1;

            count = query.getFirstResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    public boolean add(Check check) {
        boolean rs = false;
        String sql = "INSERT INTO `check` (ID_STS,TIME,STATUS) ";
        sql += "                   VALUES (   ?  ,NOW(),  ?  ) ";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, Check.class);
            int i = 1;
            query.setParameter(i++, check.getId_sts());
            query.setParameter(i++, check.getStatus());
            rs = query.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    public Check find(int id) {
        Check check = null;
        String sql = "Select * from `check` where ID = ? ";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, Check.class);
            int i = 1;
            query.setParameter(i++, id);
            check = (Check) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return check;
    }

    public boolean edit(Check check) {
        boolean rs = false;
        String sql = "UPDATE `check` SET STATUS = ? WHERE ID = ?";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, Check.class);
            int i = 1;
            query.setParameter(i++, check.getStatus());
            rs = query.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    public boolean delete(int id) {
        boolean rs = false;
        String sql = "DELETE FROM `check` WHERE ID = ?";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, Check.class);
            int i = 1;
            query.setParameter(i++, id);
            rs = query.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ArrayList<Check> findListCheck(int id) {
        ArrayList<Check> check = null;
        String sql = "Select * from `check` where ID_STS = ? ";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, Check.class);
            int i = 1;
            query.setParameter(i++, id);
            check = (ArrayList) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return check;
    }
}
