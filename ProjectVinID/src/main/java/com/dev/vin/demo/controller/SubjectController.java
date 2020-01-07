/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.vin.demo.controller;

import com.dev.vin.demo.commons.RequestTool;
import com.dev.vin.demo.config.MyConfig;
import com.dev.vin.demo.model.Sub_teach;
import com.dev.vin.demo.model.Subject;
import com.dev.vin.demo.service.SubjectService;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author MinhKudo
 */
@Controller
@RequestMapping("subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;
    

    @GetMapping(value = {"/", "/view"})
    public String listSubject() {
        return "subject/view";
    }

    @PostMapping(value = {"/", "/view"})
    @ResponseBody
    public ArrayList<Subject> listDataSubject(Model model, HttpServletRequest request) {
        String code = RequestTool.getString(request, "code");
        String name = RequestTool.getString(request, "name");
        int maxRow = RequestTool.getInt(request, "maxRow", MyConfig.ADMIN_MAX_ROW);
        int crPage = RequestTool.getInt(request, "crPage", 1);
        System.out.println("code: " + code);
        System.out.println("name: " + name);
        System.out.println("maxRow: " + maxRow);
        System.out.println("crPage: " + crPage);
        ArrayList<Subject> subject = subjectService.list(crPage, maxRow, code, name);
        int count = subjectService.count(code, name);
        System.out.println("count: " + count);
        return subject;
    }

//    @GetMapping(value = "/add")
//    public String addGetSubject(Model model) {
//        Subject subject = new Subject();
//        model.addAttribute("subject", subject);
//        return "subject/add";
//    }
//
//    @PostMapping(value = "/add")
//    public String addPostSubject(Model model, HttpServletRequest request, Subject subject, RedirectAttributes rdrAtt) {
//        System.out.println("subject: " + subject);
//        if (subjectService.checkCode(subject.getCode())) {
//            model.addAttribute("messing", "Mã Sinh Viên Đã Tồn Tại");
//            model.addAttribute("subject", subject);
//            return "subject/add";
//        } else {
//            if (subjectService.add(subject)) {
//                Sub_teach subSubject = new Sub_teach();
//                subSubject.setCode_sub(subject.getCode());
//                subSubjectService.add(subSubject);
//                rdrAtt.addFlashAttribute("messing", "Thêm Mới Thành Công");
//                return "redirect:/subject/view";
//            } else {
//                rdrAtt.addFlashAttribute("messing", "Thêm Mới Thất Bại");
//                model.addAttribute("messing", "Cập Nhật Thất Bại");
//                model.addAttribute("subject", subject);
//                return "subject/add";
//            }
//        }
//    }
    
    @GetMapping(value = "/addRest")
    public String addRestGetSubject(Model model) {
        Subject subject = new Subject();
        model.addAttribute("subject", subject);
        return "subject/addRest";
    }
    
    @PostMapping(value = "/addRest")
    @ResponseBody
    public boolean addRestPostSubject(Model model, @RequestBody Subject subjectForm) {
        System.out.println("subject,getCode(): " + subjectForm.getCode());
        System.out.println("subject,getName(): " + subjectForm.getName());
        System.out.println("subject,getNumber(): " + subjectForm.getNumber());
        System.out.println("subject,getYear(): " + subjectForm.getYear());
        if (subjectService.checkCode(subjectForm.getCode())) {
            return subjectService.checkCode(subjectForm.getCode());
        } else {
            return subjectService.add(subjectForm);
        }
    }

//    @GetMapping(value = "/edit")
//    public String editGetSubject(Model model, @RequestParam(value = "id") int id) {
//        Subject subject = subjectService.find(id);
//        model.addAttribute("subject", subject);
//        return "subject/edit";
//    }
//
//    @PostMapping(value = "/edit")
//    public String editPostSubject(Model model, HttpServletRequest request, Subject subject, RedirectAttributes rdrAtt) {
//        System.out.println("subject: " + subject);
//        if (subjectService.edit(subject)) {
//            rdrAtt.addFlashAttribute("messing", "Cập Nhật Thành Công");
//            return "redirect:/subject/view";
//        } else {
//            rdrAtt.addFlashAttribute("messing", "Cập Nhật Thất Bại");
//            model.addAttribute("messing", "Cập Nhật Thất Bại");
//            model.addAttribute("subject", subject);
//            return "subject/edit";
//        }
//    }
    
    @GetMapping(value = "/editRest")
    public String editRestGetSubject(Model model, @RequestParam(value = "id") int id) {
        Subject subject = subjectService.find(id);
        model.addAttribute("subject", subject);
        model.addAttribute("id", id);
        return "subject/editRest";
    }

    @PutMapping(value = "/editRest")
    @ResponseBody
    public Subject editRestDataPostSubject(Model model, @RequestParam(value = "id") int id) {
        System.out.println("id: " + id);
        return subjectService.find(id);
    }

    @PostMapping(value = "/editRest")
    @ResponseBody
    public boolean editRestPostSubject(Model model, @RequestBody Subject subjectForm) {
        System.out.println("subject: " + subjectForm);
        return subjectService.edit(subjectForm);
    }

    @PostMapping(value = "/delete")
    public ResponseEntity<Model> deletePostSubject(Model model, HttpServletRequest request, @RequestParam(value = "id") int id, RedirectAttributes rdrAtt) {
        System.out.println("id: " + id);
        if (subjectService.delete(id)) {
            model.addAttribute("messing", "Xóa Thành Công");
            return new ResponseEntity<>(model, HttpStatus.OK);

        } else {
            model.addAttribute("messing", "Xóa Thất Bại");
            return new ResponseEntity<>(model, HttpStatus.OK);
        }
    }
}
