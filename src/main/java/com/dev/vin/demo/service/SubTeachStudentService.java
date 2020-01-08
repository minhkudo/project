/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.vin.demo.service;

import com.dev.vin.demo.dao.SubTeachStudentDao;
import com.dev.vin.demo.model.Sub_teach_student;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MinhKudo
 */
@Service
public class SubTeachStudentService {

    @Autowired
    private SubTeachStudentDao subTeachStudentDao;

    public ArrayList<Sub_teach_student> list(int page, int row, String codeSub, String codeTeach, String codeStudent) {
        return subTeachStudentDao.list(page, row, codeSub, codeTeach, codeStudent);
    }

    public int count(String codeSub, String codeTeach, String codeStudent) {
        return subTeachStudentDao.count(codeSub, codeTeach, codeStudent);
    }

    public boolean add(Sub_teach_student sts) {
        return subTeachStudentDao.add(sts);
    }

    public Sub_teach_student find(int id) {
        return subTeachStudentDao.find(id);
    }

    public Sub_teach_student findSTS(String codeSub, String codeTeach, String codeStudent) {
        return subTeachStudentDao.findSTS(codeSub, codeTeach, codeStudent);
    }

    public boolean edit(Sub_teach_student sts) {
        return subTeachStudentDao.edit(sts);
    }

    public boolean delete(String codeSub, String codeTeach, String codeStudent) {
        return subTeachStudentDao.delete(codeSub, codeTeach, codeStudent);
    }

    
}
