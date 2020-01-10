/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.vin.demo.service;

import com.dev.vin.demo.config.Constants;
import com.dev.vin.demo.dao.StudentDao;
import com.dev.vin.demo.model.Student;
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
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    @PersistenceContext(unitName = Constants.JPA_UNIT_NAME_1)
    private EntityManager entityManager;

    public ArrayList<Student> list(int page, int row, String code, String name, int status) {
        return studentDao.list(page, row, code, name, status);
    }

    public int count(String code, String name, int status) {
        return studentDao.count(code, name, status);
    }

    public boolean add(Student student) {
        return studentDao.add(student);
    }

    public Student find(int id) {
        return studentDao.find(id);
    }

    public boolean edit(Student student) {
        return studentDao.edit(student);
    }

    public boolean delete(int id) {
        return studentDao.delete(id);
    }

    public boolean checkCode(String code) {
        boolean rs = false;
        Student student = null;
        String sql = "Select * from student where CODE = ? ";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, Student.class);
            int i = 1;
            query.setParameter(i++, code);
            student = (Student) query.getSingleResult();
            if (student != null) {
                rs = true;
            }
        } catch (Exception e) {
            rs = false;
            System.out.println("Chưa tồn tại và có thể thêm mới");
        }
        return rs;
    }

    public String login(String code, String password) {
//        boolean rs = false;
        String jwt = null;
        Student student = null;
        String sql = "Select * from student where CODE = ? AND PASSWORD = ? ";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, Student.class);
            int i = 1;
            query.setParameter(i++, code);
            query.setParameter(i++, password);
            student = (Student) query.getSingleResult();
            if (student != null) {
                jwt = JwtUltis.generateToken(code, "MEMBER");
            }
            
        } catch (Exception e) {
            System.out.println("Chưa tồn tại tài khoản");
            e.printStackTrace();
        }
        return jwt;
    }
}
