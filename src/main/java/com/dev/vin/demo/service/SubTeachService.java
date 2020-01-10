/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.vin.demo.service;

import com.dev.vin.demo.dao.SubTeachDao;
import com.dev.vin.demo.model.Sub_teach;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MinhKudo
 */
@Service
public class SubTeachService {

    @Autowired
    private SubTeachDao subTeachDao;

    public ArrayList<Sub_teach> list(int page, int row, String codeSub, String codeTeach) {
        return subTeachDao.list(page, row, codeSub, codeTeach);
    }

    public int count(String codeSub, String codeTeach) {
        return subTeachDao.count(codeSub, codeTeach);
    }

    public boolean add(Sub_teach subTeach) {
        return subTeachDao.add(subTeach);
    }

    public Sub_teach find(int id) {
        return subTeachDao.find(id);
    }

    public boolean edit(Sub_teach subTeach) {
        return subTeachDao.edit(subTeach);
    }

    public boolean delete(int id) {
        return subTeachDao.delete(id);
    }

    public ArrayList<Sub_teach> listNotReig(int page, int row, String codeSub, String codeTeach, String codeStudent) {
        return subTeachDao.listNotReig(page, row, codeSub, codeTeach, codeStudent);
    }

    public ArrayList<Sub_teach> listDoReig(int page, int row, String codeSub, String codeTeach, String codeStudent) {
        return subTeachDao.listDoReig(page, row, codeSub, codeTeach, codeStudent);
    }

}
