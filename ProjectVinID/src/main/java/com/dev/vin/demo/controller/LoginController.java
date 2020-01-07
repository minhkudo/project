/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.vin.demo.controller;

import com.dev.vin.demo.service.StudentService;
import com.dev.vin.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author MinhKudo
 */
@Controller
public class LoginController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teachService;

    @GetMapping(value = "/loginStudent")
    public String getLoginStudent() {
        return "loginStudent";
    }

    @PostMapping(value = "/loginStudent")
    @ResponseBody
    public boolean loginStudent(Model model,
            @RequestParam(value = "code", required = true) String code,
            @RequestParam(value = "password", required = true) String password) {
        return studentService.login(code, password);
    }

    @GetMapping(value = "/loginTeach")
    public String getLoginTeach() {
        return "loginTeach";
    }

    @PostMapping(value = "/loginTeach")
    @ResponseBody
    public boolean loginTeach(Model model,
            @RequestParam(value = "code", required = true) String code,
            @RequestParam(value = "password", required = true) String password) {
        return teachService.login(code, password);
    }

    @GetMapping(value = "/loginAdmin")
    public String getLoginAdmin() {
        return "loginAdmin";
    }

    @PostMapping(value = "/loginAdmin")
    @ResponseBody
    public boolean loginAdmin(Model model,
            @RequestParam(value = "code", required = true) String code,
            @RequestParam(value = "password", required = true) String password) {
        if (code.equals("1") && password.equals("1")) {
            return true;
        } else {
            return false;
        }
    }
    
//    @GetMapping(value = "/pageStudent/index")
//    public String getIndexStudent() {
//        return "pageStudent/index";
//    }
//    @GetMapping(value = "/pageTeach/index")
//    public String getIndexTeach() {
//        return "pageTeach/index";
//    }
//    @GetMapping(value = "/pageAdmin/index")
//    public String getIndexAdmin() {
//        return "pageAdmin/index";
//    }
}
