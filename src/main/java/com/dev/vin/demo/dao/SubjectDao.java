/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.vin.demo.dao;

import com.dev.vin.demo.commons.Tool;
import com.dev.vin.demo.config.Constants;
import com.dev.vin.demo.model.Subject;
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
public class SubjectDao {

    @Autowired
    @PersistenceContext(unitName = Constants.JPA_UNIT_NAME_1)
    private EntityManager entityManager;

    public ArrayList<Subject> list(int page, int row, String code, String name) {
        ArrayList<Subject> list = new ArrayList<>();
        String sql = "Select * from subject where 1 = 1 ";
        if (!Tool.checkNull(code)) {
            sql += " AND CODE like ? ";
        }
        if (!Tool.checkNull(name)) {
            sql += " AND NAME like ? ";
        }
        sql += " ORDER BY ID DESC LIMIT ? , ?";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, Subject.class);
            int i = 1;
            if (!Tool.checkNull(code)) {
                query.setParameter(i++, "%" + code + "%");
            }
            if (!Tool.checkNull(name)) {
                query.setParameter(i++, "%" + name + "%");
            }
            query.setParameter(i++, (page - 1) * row);
            query.setParameter(i++, row);
            list = (ArrayList) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int count(String code, String name) {
        int count = 0;
        String sql = "Select count(*) from subject where 1 = 1 ";
        if (!Tool.checkNull(code)) {
            sql += " AND CODE like ? ";
        }
        if (!Tool.checkNull(name)) {
            sql += " AND NAME like ? ";
        }
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql);
            int i = 1;
            if (!Tool.checkNull(code)) {
                query.setParameter(i++, "%" + code + "%");
            }
            if (!Tool.checkNull(name)) {
                query.setParameter(i++, "%" + name + "%");
            }
            count = query.getFirstResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    public boolean add(Subject sub) {
        boolean rs = false;
        String sql = "INSERT INTO subject (CODE,NAME,NUMBER,YEAR) ";
        sql += "                   VALUES (  ? , ?  ,  ?   , ?  ) ";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, Subject.class);
            int i = 1;
            query.setParameter(i++, sub.getCode());
            query.setParameter(i++, sub.getName());
            query.setParameter(i++, sub.getNumber());
            query.setParameter(i++, sub.getYear());
            rs = query.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    public Subject find(int id) {
        Subject subject = null;
        String sql = "Select * from subject where ID = ? ";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, Subject.class);
            int i = 1;
            query.setParameter(i++, id);
            subject = (Subject) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return subject;
    }

    public boolean edit(Subject sub) {
        boolean rs = false;
        String sql = "UPDATE subject SET CODE = ? , NAME = ? ";
        sql += " ,NUMBER = ? , YEAR = ? WHERE ID = ?";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, Subject.class);
            int i = 1;
            query.setParameter(i++, sub.getCode());
            query.setParameter(i++, sub.getName());
            query.setParameter(i++, sub.getNumber());
            query.setParameter(i++, sub.getYear());
            query.setParameter(i++, sub.getId());
            rs = query.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    public boolean delete(int id) {
        boolean rs = false;
        String sql = "DELETE FROM subject WHERE ID = ?";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, Subject.class);
            int i = 1;
            query.setParameter(i++, id);
            rs = query.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
