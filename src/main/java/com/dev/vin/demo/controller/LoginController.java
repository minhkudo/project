/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.vin.demo.controller;

import com.dev.vin.demo.commons.Tool;
import com.dev.vin.demo.model.Result;
import com.dev.vin.demo.service.StudentService;
import com.dev.vin.demo.service.TeacherService;
import com.dev.vin.demo.util.JwtUltis;
import com.dev.vin.demo.util.Share;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public ResponseEntity<Result> loginStudent(Model model,
            @RequestParam(value = "code", required = true) String code,
            @RequestParam(value = "password", required = true) String password,
            HttpServletRequest request,HttpServletResponse response) {
        System.out.println("code: " + code);
        System.out.println("password: " + password);
        String token = studentService.login(code, password);
        HttpSession session = request.getSession(false);
        Share.listSessionStudent.add(session.getId());
        System.out.println("id: " + session.getId());
        session.setAttribute(session.getId(), token);

        Cookie cookie = new Cookie("JSESSIONID", request.getSession().getId());
        response.addCookie(cookie);

        if (Tool.checkNull(token)) {
            return new ResponseEntity<>(new Result(Result.ERROR, "Đăng Nhập Thất Bại"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Result(Result.SUCCESS, "Đăng Nhập Thành Công"), HttpStatus.OK);
    }

    @GetMapping(value = "/loginTeach")
    public String getLoginTeach() {
        return "loginTeach";
    }

    @PostMapping(value = "/loginTeach")
    @ResponseBody
    public ResponseEntity<Result> loginTeach(Model model,
            @RequestParam(value = "code", required = true) String code,
            @RequestParam(value = "password", required = true) String password,
            HttpServletRequest request) {
        Share.token_teach = teachService.login(code, password);
        if (!Tool.checkNull(Share.token_teach)) {

            return new ResponseEntity<>(new Result(Result.SUCCESS, "Đăng Nhập Thành Công"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Result(Result.ERROR, "Đăng Nhập Thất Bại"), HttpStatus.OK);
    }

    @GetMapping(value = "/loginAdmin")
    public String getLoginAdmin() {
        return "loginAdmin";
    }

    @PostMapping(value = "/loginAdmin")
    @ResponseBody
    public ResponseEntity<Result> loginAdmin(Model model,
            @RequestParam(value = "code", required = true) String code,
            @RequestParam(value = "password", required = true) String password) {
        if (code.equals("1") && password.equals("1")) {
            Share.token_admin = JwtUltis.generateToken("1", "ADMIN");
        }
        if (Share.token_admin != null) {
            return new ResponseEntity<>(new Result(Result.SUCCESS, "Đăng Nhập Thành Công"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Result(Result.ERROR, "Đăng Nhập Thất Bại"), HttpStatus.OK);
    }

    @GetMapping(value = "/logoutAdmin")
    public String getIndexStudent() {
        Share.token_admin = null;
        return "redirect:/loginAdmin";
    }

    @GetMapping(value = "/logoutTeach")
    public String getIndexTeach() {
        Share.token_teach = null;
        return "redirect:/loginTeach";
    }

    @GetMapping(value = "/logoutStudent")
    public String getIndexAdmin() {
        Share.token_student = null;
        return "redirect:/loginStudent";
    }
}
