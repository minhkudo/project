/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.vin.demo.dao;

import com.dev.vin.demo.commons.Tool;
import com.dev.vin.demo.config.Constants;
import com.dev.vin.demo.model.Student;
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
public class StudentDao {

    @Autowired
    @PersistenceContext(unitName = Constants.JPA_UNIT_NAME_1)
    private EntityManager entityManager;

    public ArrayList<Student> list(int page, int row, String code, String name, int status) {
        ArrayList<Student> list = new ArrayList<>();
        String sql = "Select * from student where 1 = 1 ";
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
            Query query = entityManager.createNativeQuery(sql, Student.class);
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
        ArrayList<Student> list = new ArrayList<>();
        String sql = "Select * from student where 1 = 1 ";
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
            Query query = entityManager.createNativeQuery(sql, Student.class);
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
            list = (ArrayList) query.getResultList();
            count = list.size();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    public boolean add(Student student) {
        boolean rs = false;
        String sql = "INSERT INTO student (CODE,NAME,PASSWORD,NOTI,STATUS) ";
        sql += "                    VALUES(  ? , ?  ,    ?   , ?  ,  ?   ) ";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, Student.class);
            int i = 1;
            query.setParameter(i++, student.getCode());
            query.setParameter(i++, student.getName());
            query.setParameter(i++, student.getPassword());
            query.setParameter(i++, student.getNoti());
            query.setParameter(i++, student.getStatus());
            rs = query.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("rs: " + rs);
        return rs;
    }

    public Student find(int id) {
        Student student = null;
        String sql = "Select * from student where ID = ? ";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, Student.class);
            int i = 1;
            query.setParameter(i++, id);
            student = (Student) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return student;
    }

    public boolean edit(Student student) {
        boolean rs = false;
        String sql = "UPDATE student SET CODE = ? , NAME = ? ";
        if (!Tool.checkNull(student.getPassword())) {
            sql += " ,PASSWORD = ? ";
        }
        sql += " ,NOTI = ? , STATUS = ? WHERE ID = ?";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, Student.class);
            int i = 1;
            query.setParameter(i++, student.getCode());
            query.setParameter(i++, student.getName());
            if (!Tool.checkNull(student.getPassword())) {
                query.setParameter(i++, student.getPassword());
            }
            query.setParameter(i++, student.getNoti());
            query.setParameter(i++, student.getStatus());
            query.setParameter(i++, student.getId());
            rs = query.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    public boolean delete(int id) {
        boolean rs = false;
        String sql = "DELETE FROM student WHERE ID = ?";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, Student.class);
            int i = 1;
            query.setParameter(i++, id);
            rs = query.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }
}
