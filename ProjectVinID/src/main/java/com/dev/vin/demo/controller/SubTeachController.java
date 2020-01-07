/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.vin.demo.controller;

import com.dev.vin.demo.commons.RequestTool;
import com.dev.vin.demo.config.MyConfig;
import com.dev.vin.demo.model.Sub_teach;
import com.dev.vin.demo.service.SubTeachService;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author MinhKudo
 */
@Controller
@RequestMapping("subTeach")
public class SubTeachController {

    @Autowired
    private SubTeachService subTeachSerivce;

    @GetMapping(value = {"/", "/view"})
    public String listSubTeach() {
        return "subTeach/view";
    }
    
    @PostMapping(value = {"/", "/view"})
    @ResponseBody
    public ArrayList<Sub_teach> listDataStudent(Model model, HttpServletRequest request) {
        String codeSub = RequestTool.getString(request, "codeSub");
        String codeTeach = RequestTool.getString(request, "codeTeach");
        int maxRow = RequestTool.getInt(request, "maxRow", MyConfig.ADMIN_MAX_ROW);
        int crPage = RequestTool.getInt(request, "crPage", 1);
        System.out.println("code: " + codeSub);
        System.out.println("name: " + codeTeach);
        System.out.println("maxRow: " + maxRow);
        System.out.println("crPage: " + crPage);
        ArrayList<Sub_teach> subTeach = subTeachSerivce.list(crPage, maxRow, codeSub, codeTeach);
        int count = subTeachSerivce.count(codeSub, codeTeach);
        System.out.println("count: " + count);
        return subTeach;
    }
    
    @GetMapping(value = "/editRest")
    public String editRestGetTeach(Model model, @RequestParam(value = "id") int id) {
        Sub_teach subTeach = subTeachSerivce.find(id);
        model.addAttribute("subTeach", subTeach);
        model.addAttribute("id", id);
        return "subTeach/editRest";
    }

    @PutMapping(value = "/editRest")
    @ResponseBody
    public Sub_teach editRestDataPostTeach(Model model, @RequestParam(value = "id") int id) {
        System.out.println("id: " + id);
        return subTeachSerivce.find(id);
    }

    @PostMapping(value = "/editRest")
    @ResponseBody
    public boolean editRestPostTeach(Model model, @RequestBody Sub_teach subTeach) {
        System.out.println("subTeach: " + subTeach);
        return subTeachSerivce.edit(subTeach);
    }
}
