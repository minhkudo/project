/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.vin.demo.dao;

import com.dev.vin.demo.commons.Tool;
import com.dev.vin.demo.config.Constants;
import com.dev.vin.demo.model.Sub_teach;
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
public class SubTeachDao {

    @Autowired
    @PersistenceContext(unitName = Constants.JPA_UNIT_NAME_1)
    private EntityManager entityManager;

    public ArrayList<Sub_teach> list(int page, int row, String codeSub, String codeTeach) {
        ArrayList<Sub_teach> list = new ArrayList<>();
        String sql = "Select * from map_sub_teach where 1 = 1 ";
        if (!Tool.checkNull(codeSub)) {
            sql += " AND CODE_SUB like ? ";
        }
        if (!Tool.checkNull(codeTeach)) {
            sql += " AND CODE_TEACH like ? ";
        }
        sql += " ORDER BY ID DESC LIMIT ? , ?";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, Sub_teach.class);
            int i = 1;
            if (!Tool.checkNull(codeSub)) {
                query.setParameter(i++, "%" + codeSub + "%");
            }
            if (!Tool.checkNull(codeTeach)) {
                query.setParameter(i++, "%" + codeTeach + "%");
            }
            query.setParameter(i++, (page - 1) * row);
            query.setParameter(i++, row);
            list = (ArrayList) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int count(String codeSub, String codeTeach) {
        int count = 0;
        String sql = "Select count(*) from map_sub_teach where 1 = 1 ";
        if (!Tool.checkNull(codeSub)) {
            sql += " AND CODE_SUB like ? ";
        }
        if (!Tool.checkNull(codeTeach)) {
            sql += " AND CODE_TEACH like ? ";
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
            count = query.getFirstResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    public boolean add(Sub_teach subTeach) {
        boolean rs = false;
        String sql = "INSERT INTO map_sub_teach (CODE_SUB,CODE_TEACH) ";
        sql += "                         VALUES (    ?   ,    ?     ) ";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, Sub_teach.class);
            int i = 1;
            query.setParameter(i++, subTeach.getCode_sub());
            query.setParameter(i++, subTeach.getCode_teach());
            rs = query.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    public Sub_teach find(int id) {
        Sub_teach subTeach = null;
        String sql = "Select * from map_sub_teach where ID = ? ";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, Sub_teach.class);
            int i = 1;
            query.setParameter(i++, id);
            subTeach = (Sub_teach) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return subTeach;
    }

    public boolean edit(Sub_teach subTeach) {
        boolean rs = false;
        String sql = "UPDATE map_sub_teach SET CODE_SUB = ? , CODE_TEACH = ? WHERE ID = ?";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, Sub_teach.class);
            int i = 1;
            query.setParameter(i++, subTeach.getCode_sub());
            query.setParameter(i++, subTeach.getCode_teach());
            query.setParameter(i++, subTeach.getId());
            rs = query.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    public boolean delete(int id) {
        boolean rs = false;
        String sql = "DELETE FROM map_sub_teach WHERE ID = ?";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, Sub_teach.class);
            int i = 1;
            query.setParameter(i++, id);
            rs = query.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ArrayList<Sub_teach> listNotReig(int page, int row, String codeSub, String codeTeach, String codeStudent) {
        ArrayList<Sub_teach> list = new ArrayList<>();
        String sql = "SELECT * FROM map_sub_teach \n"
                + "WHERE CODE_TEACH IS NOT NULL \n"
                + "AND CODE_SUB NOT IN (SELECT CODE_SUB FROM sub_teach_student WHERE CODE_STUDENT = ?) \n"
                + "AND CODE_TEACH NOT IN (SELECT CODE_TEACH FROM sub_teach_student WHERE CODE_STUDENT = ?) ";
        if (!Tool.checkNull(codeSub)) {
            sql += " AND CODE_SUB like ? ";
        }
        if (!Tool.checkNull(codeTeach)) {
            sql += " AND CODE_TEACH like ? ";
        }
        sql += " ORDER BY ID DESC LIMIT ? , ?";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, Sub_teach.class);
            int i = 1;
            query.setParameter(i++, codeStudent);
            query.setParameter(i++, codeStudent);
            if (!Tool.checkNull(codeSub)) {
                query.setParameter(i++, "%" + codeSub + "%");
            }
            if (!Tool.checkNull(codeTeach)) {
                query.setParameter(i++, "%" + codeTeach + "%");
            }
            query.setParameter(i++, (page - 1) * row);
            query.setParameter(i++, row);
            list = (ArrayList) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Sub_teach> listDoReig(int page, int row, String codeSub, String codeTeach, String codeStudent) {
        ArrayList<Sub_teach> list = new ArrayList<>();
        String sql = "SELECT * FROM map_sub_teach \n"
                + "WHERE CODE_TEACH IS NOT NULL \n"
                + "AND CODE_SUB IN (SELECT CODE_SUB FROM sub_teach_student WHERE CODE_STUDENT = ?) \n"
                + "OR CODE_TEACH IN (SELECT CODE_TEACH FROM sub_teach_student WHERE CODE_STUDENT = ?) ";
        if (!Tool.checkNull(codeSub)) {
            sql += " AND CODE_SUB like ? ";
        }
        if (!Tool.checkNull(codeTeach)) {
            sql += " AND CODE_TEACH like ? ";
        }
        sql += " ORDER BY ID DESC LIMIT ? , ?";
        System.out.println("sql: " + sql);
        try {
            Query query = entityManager.createNativeQuery(sql, Sub_teach.class);
            int i = 1;
            query.setParameter(i++, codeStudent);
            query.setParameter(i++, codeStudent);
            if (!Tool.checkNull(codeSub)) {
                query.setParameter(i++, "%" + codeSub + "%");
            }
            if (!Tool.checkNull(codeTeach)) {
                query.setParameter(i++, "%" + codeTeach + "%");
            }
            query.setParameter(i++, (page - 1) * row);
            query.setParameter(i++, row);
            list = (ArrayList) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
