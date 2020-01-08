/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.vin.demo.service;

import com.dev.vin.demo.config.Constants;
import com.dev.vin.demo.dao.SubjectDao;
import com.dev.vin.demo.model.Subject;
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
public class SubjectService {

    @Autowired
    private SubjectDao subjectDao;

    @Autowired
    @PersistenceContext(unitName = Constants.JPA_UNIT_NAME_1)
    private EntityManager entityManager;

    public ArrayList<Subject> list(int page, int row, String code, String name) {
        return subjectDao.list(page, row, code, name);
    }

    public int count(String code, String name) {
        return subjectDao.count(code, name);
    }

    public boolean add(Subject sub) {
        return subjectDao.add(sub);
    }

    public Subject find(int id) {
        return subjectDao.find(id);
    }

    public boolean edit(Subject sub) {
        return subjectDao.edit(sub);
    }

    public boolean delete(int id) {
        return subjectDao.delete(id);
    }

    public boolean checkCode(String code) {
        boolean rs = false;
        Subject subject = null;
        String sql = "Select * from subject where CODE = ? ";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, Subject.class);
            int i = 1;
            query.setParameter(i++, code);
            subject = (Subject) query.getSingleResult();
            if (subject != null) {
                rs = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }
}
