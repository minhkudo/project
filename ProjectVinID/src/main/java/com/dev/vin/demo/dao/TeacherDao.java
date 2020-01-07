/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.vin.demo.dao;

import com.dev.vin.demo.commons.Tool;
import com.dev.vin.demo.config.Constants;
import com.dev.vin.demo.model.Teach;
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
public class TeacherDao {

    @Autowired
    @PersistenceContext(unitName = Constants.JPA_UNIT_NAME_1)
    private EntityManager entityManager;

    public ArrayList<Teach> list(int page, int row, String code, String name, int status) {
        ArrayList<Teach> list = new ArrayList<>();
        String sql = "Select * from teach where 1 = 1 ";
        if (!Tool.checkNull(code)) {
            sql += " AND CODE like ? ";
        }
        if (!Tool.checkNull(name)) {
            sql += " AND NAME like ? ";
        }
        if (status != -1) {
            sql += " AND STATUS = ? ";
        }
        sql += " ORDER BY ID DESC LIMIT ? , ?";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, Teach.class);
            int i = 1;
            if (!Tool.checkNull(code)) {
                query.setParameter(i++, "%" + code + "%");
            }
            if (!Tool.checkNull(name)) {
                query.setParameter(i++, "%" + name + "%");
            }
            if (status != -1) {
                query.setParameter(i++, status);
            }
            query.setParameter(i++, (page - 1) * row);
            query.setParameter(i++, row);
            list = (ArrayList) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public int count(String code, String name, int status) {
        int count = 0;
        String sql = "Select count(*) from teach where 1 = 1 ";
        if (!Tool.checkNull(code)) {
            sql += " AND CODE like ? ";
        }
        if (!Tool.checkNull(name)) {
            sql += " AND NAME like ? ";
        }
        if (status != -1) {
            sql += " AND STATUS = ? ";
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
            if (status != -1) {
                query.setParameter(i++, status);
            }
            count = query.getFirstResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    public boolean add(Teach teach) {
        boolean rs = false;
        String sql = "INSERT INTO teach (CODE,NAME,PASSWORD,NOTI,STATUS) ";
        sql += "                    VALUES(  ? , ?  ,    ?   , ?  ,  ?   ) ";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, Teach.class);
            int i = 1;
            query.setParameter(i++, teach.getCode());
            query.setParameter(i++, teach.getName());
            query.setParameter(i++, teach.getPassword());
            query.setParameter(i++, teach.getNoti());
            query.setParameter(i++, teach.getStatus());
            rs = query.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    public Teach find(int id) {
        Teach teach = null;
        String sql = "Select * from teach where ID = ? ";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, Teach.class);
            int i = 1;
            query.setParameter(i++, id);
            teach = (Teach) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return teach;
    }

    public boolean edit(Teach teach) {
        boolean rs = false;
        String sql = "UPDATE teach SET CODE = ? , NAME = ? ";
        if (!Tool.checkNull(teach.getPassword())) {
            sql += " ,PASSWORD = ? ";
        }
        sql += " ,NOTI = ? , STATUS = ? WHERE ID = ?";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, Teach.class);
            int i = 1;
            query.setParameter(i++, teach.getCode());
            query.setParameter(i++, teach.getName());
            if (!Tool.checkNull(teach.getPassword())) {
                query.setParameter(i++, teach.getPassword());
            }
            query.setParameter(i++, teach.getNoti());
            query.setParameter(i++, teach.getStatus());
            query.setParameter(i++, teach.getId());
            rs = query.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    public boolean delete(int id) {
        boolean rs = false;
        String sql = "DELETE FROM teach WHERE ID = ?";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, Teach.class);
            int i = 1;
            query.setParameter(i++, id);
            rs = query.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }
}
