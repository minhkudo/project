/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.vin.demo.service;

import com.dev.vin.demo.dao.CheckDao;
import com.dev.vin.demo.model.Check;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MinhKudo
 */
@Service
public class CheckService {
    
    @Autowired
    private CheckDao checkDao;
    
    public ArrayList<Check> list(int page, int row) {
        return checkDao.list(page, row);
    }

    public int count() {
        return checkDao.count();
    }

    public boolean add(Check check) {
        return checkDao.add(check);
    }

    public Check find(int id) {
        return checkDao.find(id);
    }

    public boolean edit(Check check) {
        return checkDao.edit(check);
    }

    public boolean delete(int id) {
        return checkDao.delete(id);
    }
    
    public ArrayList<Check> findListCheck(int id) {
        return checkDao.findListCheck(id);
    }
}
