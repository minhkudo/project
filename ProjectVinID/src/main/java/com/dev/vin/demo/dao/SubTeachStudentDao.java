/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.vin.demo.dao;

import com.dev.vin.demo.commons.Tool;
import com.dev.vin.demo.config.Constants;
import com.dev.vin.demo.model.Sub_teach_student;
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
public class SubTeachStudentDao {

    @Autowired
    @PersistenceContext(unitName = Constants.JPA_UNIT_NAME_1)
    private EntityManager entityManager;

    public ArrayList<Sub_teach_student> list(int page, int row, String codeSub, String codeTeach, String codeStudent) {
        ArrayList<Sub_teach_student> list = new ArrayList<>();
        String sql = "Select * from sub_teach_student where 1 = 1 ";
        if (!Tool.checkNull(codeSub)) {
            sql += " AND CODE_SUB like ? ";
        }
        if (!Tool.checkNull(codeTeach)) {
            sql += " AND CODE_TEACH like ? ";
        }
        if (!Tool.checkNull(codeStudent)) {
            sql += " AND CODE_STUDENT like ? ";
        }
        sql += " ORDER BY ID DESC LIMIT ? , ?";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, Sub_teach_student.class);
            int i = 1;
            if (!Tool.checkNull(codeSub)) {
                query.setParameter(i++, "%" + codeSub + "%");
            }
            if (!Tool.checkNull(codeTeach)) {
                query.setParameter(i++, "%" + codeTeach + "%");
            }
            if (!Tool.checkNull(codeStudent)) {
                query.setParameter(i++, "%" + codeStudent + "%");
            }
            query.setParameter(i++, (page - 1) * row);
            query.setParameter(i++, row);
            list = (ArrayList) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int count(String codeSub, String codeTeach, String codeStudent) {
        int count = 0;
        String sql = "Select count(*) from sub_teach_student where 1 = 1 ";
        if (!Tool.checkNull(codeSub)) {
            sql += " AND CODE_SUB like ? ";
        }
        if (!Tool.checkNull(codeTeach)) {
            sql += " AND CODE_TEACH like ? ";
        }
        if (!Tool.checkNull(codeStudent)) {
            sql += " AND CODE_STUDENT like ? ";
        }
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql);
            int i = 1;
            if (!Tool.checkNull(codeSub)) {
                query.setParameter(i++, "%" + codeSub + "%");
            }
            if (!Tool.checkNull(codeTeach)) {
                query.setParameter(i++, "%" + codeTeach + "%");
            }
            if (!Tool.checkNull(codeStudent)) {
                query.setParameter(i++, "%" + codeStudent + "%");
            }
            count = query.getFirstResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    public boolean add(Sub_teach_student sts) {
        boolean rs = false;
        String sql = "INSERT INTO sub_teach_student (CODE_SUB,CODE_TEACH,CODE_STUDENT) ";
        sql += "                             VALUES (    ?   ,    ?     ,     ?      ) ";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, Sub_teach_student.class);
            int i = 1;
            query.setParameter(i++, sts.getCode_sub());
            query.setParameter(i++, sts.getCode_teach());
            query.setParameter(i++, sts.getCode_student());
            rs = query.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    public Sub_teach_student find(int id) {
        Sub_teach_student sts = null;
        String sql = "Select * from sub_teach_student where ID = ? ";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, Sub_teach_student.class);
            int i = 1;
            query.setParameter(i++, id);
            sts = (Sub_teach_student) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sts;
    }

    public boolean edit(Sub_teach_student sts) {
        boolean rs = false;
        String sql = "UPDATE sub_teach_student SET CODE_SUB = ? , CODE_TEACH = ? , CODE_STUDENT = ? WHERE ID = ?";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, Sub_teach_student.class);
            int i = 1;
            query.setParameter(i++, sts.getCode_sub());
            query.setParameter(i++, sts.getCode_teach());
            query.setParameter(i++, sts.getCode_student());
            query.setParameter(i++, sts.getId());
            rs = query.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    public boolean delete(String codeSub, String codeTeach, String codeStudent) {
        boolean rs = false;
        Sub_teach_student sts = findSTS(codeSub, codeTeach, codeStudent);
        String sql = "DELETE FROM sub_teach_student WHERE ID = ?";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, Sub_teach_student.class);
            int i = 1;
            query.setParameter(i++, sts.getId());
//            query.setParameter(i++, codeSub);
//            query.setParameter(i++, codeTeach);
//            query.setParameter(i++, codeStudent);
            rs = query.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public Sub_teach_student findSTS(String codeSub, String codeTeach, String codeStudent) {
        Sub_teach_student sts = null;
        String sql = "SELECT * FROM sub_teach_student WHERE CODE_SUB = ? AND CODE_TEACH = ? AND CODE_STUDENT = ?  ";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, Sub_teach_student.class);
            int i = 1;
            query.setParameter(i++, codeSub);
            query.setParameter(i++, codeTeach);
            query.setParameter(i++, codeStudent);
            sts = (Sub_teach_student) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sts;
    }
    
    
}
