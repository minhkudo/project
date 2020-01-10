/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.vin.demo.service;

import com.dev.vin.demo.config.Constants;
import com.dev.vin.demo.dao.TeacherDao;
import com.dev.vin.demo.model.Teach;
import com.dev.vin.demo.util.JwtUltis;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MinhKudo
 */
@Service
public class TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    @PersistenceContext(unitName = Constants.JPA_UNIT_NAME_1)
    private EntityManager entityManager;

    public ArrayList<Teach> list(int page, int row, String code, String name, int status) {
        return teacherDao.list(page, row, code, name, status);
    }

    public int count(String code, String name, int status) {
        return teacherDao.count(code, name, status);
    }

    public boolean add(Teach teach) {
        return teacherDao.add(teach);
    }

    public Teach find(int id) {
        return teacherDao.find(id);
    }

    public boolean edit(Teach teach) {
        return teacherDao.edit(teach);
    }

    public boolean delete(int id) {
        return teacherDao.delete(id);
    }

    public boolean checkCode(String code) {
        boolean rs = false;
        Teach teach = null;
        String sql = "Select * from teach where CODE = ? ";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, Teach.class);
            int i = 1;
            query.setParameter(i++, code);
            teach = (Teach) query.getSingleResult();
            if (teach != null) {
                rs = true;
            }
        } catch (Exception e) {
            rs = false;
            e.printStackTrace();
        }

        return rs;
    }

    public String login(String code, String password) {
        String jwt = null;
        Teach teach = null;
        String sql = "Select * from teach where CODE = ? AND PASSWORD = ?";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, Teach.class);
            int i = 1;
            query.setParameter(i++, code);
            query.setParameter(i++, password);
            teach = (Teach) query.getSingleResult();
            if (teach != null) {
                jwt = JwtUltis.generateToken(code, "USER");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jwt;
    }
}
