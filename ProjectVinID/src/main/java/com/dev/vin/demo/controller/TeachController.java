/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.vin.demo.controller;

import com.dev.vin.demo.commons.RequestTool;
import com.dev.vin.demo.config.MyConfig;
import com.dev.vin.demo.model.Check;
import com.dev.vin.demo.model.RequestJsonClient;
import com.dev.vin.demo.model.RequestJsonClient.arrayObject;
import com.dev.vin.demo.model.Sub_teach;
import com.dev.vin.demo.model.Sub_teach_student;
import com.dev.vin.demo.model.Teach;
import com.dev.vin.demo.service.CheckService;
import com.dev.vin.demo.service.SubTeachService;
import com.dev.vin.demo.service.SubTeachStudentService;
import com.dev.vin.demo.service.TeacherService;
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
@RequestMapping("teach")
public class TeachController {

    @Autowired
    private TeacherService teachService;

    @Autowired
    private SubTeachService subTeachSerivce;

    @Autowired
    private SubTeachStudentService subTeachStudentSerivce;

    @Autowired
    private CheckService checkService;

    @GetMapping(value = {"/", "/view"})
    public String listTeach() {
        return "teach/view";
    }

    @PostMapping(value = {"/", "/view"})
    @ResponseBody
    public ArrayList<Teach> listDataTeach(Model model, HttpServletRequest request) {
        String code = RequestTool.getString(request, "code");
        String name = RequestTool.getString(request, "name");
        int status = RequestTool.getInt(request, "status", -1);
        int maxRow = RequestTool.getInt(request, "maxRow", MyConfig.ADMIN_MAX_ROW);
        int crPage = RequestTool.getInt(request, "crPage", 1);
        System.out.println("code: " + code);
        System.out.println("name: " + name);
        System.out.println("status: " + status);
        System.out.println("maxRow: " + maxRow);
        System.out.println("crPage: " + crPage);
        ArrayList<Teach> teach = teachService.list(crPage, maxRow, code, name, status);
        int count = teachService.count(code, name, status);
        System.out.println("count: " + count);
        return teach;
    }

//    @GetMapping(value = "/add")
//    public String addGetTeach(Model model) {
//        Teach teach = new Teach();
//        model.addAttribute("teach", teach);
//        return "teach/add";
//    }
//
//    @PostMapping(value = "/add")
//    public String addPostTeach(Model model, HttpServletRequest request, Teach teach, RedirectAttributes rdrAtt) {
//        System.out.println("teach: " + teach);
//        if (teachService.checkCode(teach.getCode())) {
//            model.addAttribute("messing", "Mã Sinh Viên Đã Tồn Tại");
//            model.addAttribute("teach", teach);
//            return "teach/add";
//        } else {
//            if (teachService.add(teach)) {
//                rdrAtt.addFlashAttribute("messing", "Thêm Mới Thành Công");
//                return "redirect:/teach/view";
//            } else {
//                rdrAtt.addFlashAttribute("messing", "Thêm Mới Thất Bại");
//                model.addAttribute("messing", "Cập Nhật Thất Bại");
//                model.addAttribute("teach", teach);
//                return "teach/add";
//            }
//        }
//    }
    @GetMapping(value = "/addRest")
    public String addRestGetTeach(Model model) {
        Teach teach = new Teach();
        model.addAttribute("teach", teach);
        return "teach/addRest";
    }

//    @PostMapping(value = "/addRest")
//    public ResponseEntity<?> addRestPostTeach(Model model, @RequestBody Teach teachForm) {
//        System.out.println("teach,getCode(): " + teachForm.getCode());
//        System.out.println("teach,getName(): " + teachForm.getName());
//        System.out.println("teach,getPassword(): " + teachForm.getPassword());
//        System.out.println("teach,getNoti(): " + teachForm.getNoti());
//        System.out.println("teach,getStatus(): " + teachForm.getStatus());
//        if (teachService.checkCode(teachForm.getCode())) {
//            teachService.checkCode(teachForm.getCode());
//        } else {
//            teachService.add(teachForm);
//        }
//        return ResponseEntity.ok("{'message' : 'Thêm Thành Công'}");
//    }
    @PostMapping(value = "/addRest")
    @ResponseBody
    public boolean addRestPostTeach(Model model, @RequestBody Teach teachForm) {
        System.out.println("teach,getCode(): " + teachForm.getCode());
        System.out.println("teach,getName(): " + teachForm.getName());
        System.out.println("teach,getPassword(): " + teachForm.getPassword());
        System.out.println("teach,getNoti(): " + teachForm.getNoti());
        System.out.println("teach,getStatus(): " + teachForm.getStatus());
        if (teachService.checkCode(teachForm.getCode())) {
            return teachService.checkCode(teachForm.getCode());
        } else {
            return teachService.add(teachForm);
        }
    }

//    @GetMapping(value = "/edit")
//    public String editGetTeach(Model model, @RequestParam(value = "id") int id) {
//        Teach teach = teachService.find(id);
//        model.addAttribute("teach", teach);
//        return "teach/edit";
//    }
//
//    @PostMapping(value = "/edit")
//    public String editPostTeach(Model model, HttpServletRequest request, Teach teach, RedirectAttributes rdrAtt) {
//        System.out.println("teach: " + teach);
//        if (teachService.edit(teach)) {
//            rdrAtt.addFlashAttribute("messing", "Cập Nhật Thành Công");
//            return "redirect:/teach/view";
//        } else {
//            rdrAtt.addFlashAttribute("messing", "Cập Nhật Thất Bại");
//            model.addAttribute("messing", "Cập Nhật Thất Bại");
//            model.addAttribute("teach", teach);
//            return "teach/edit";
//        }
//    }
    @GetMapping(value = "/editRest")
    public String editRestGetTeach(Model model, @RequestParam(value = "id") int id) {
        Teach teach = teachService.find(id);
        model.addAttribute("teach", teach);
        model.addAttribute("id", id);
        return "teach/editRest";
    }

    @PutMapping(value = "/editRest")
    @ResponseBody
    public Teach editRestDataPostTeach(Model model, @RequestParam(value = "id") int id) {
        System.out.println("id: " + id);
        return teachService.find(id);
    }

    @PostMapping(value = "/editRest")
    @ResponseBody
    public boolean editRestPostTeach(Model model, @RequestBody Teach teachForm) {
        System.out.println("teach: " + teachForm);
        return teachService.edit(teachForm);
    }

    @PostMapping(value = "/delete")
    public ResponseEntity<Model> deletePostTeach(Model model, HttpServletRequest request, @RequestParam(value = "id") int id, RedirectAttributes rdrAtt) {
        System.out.println("id: " + id);
        if (teachService.delete(id)) {
            model.addAttribute("messing", "Xóa Thành Công");
            return new ResponseEntity<>(model, HttpStatus.OK);

        } else {
            model.addAttribute("messing", "Xóa Thất Bại");
            return new ResponseEntity<>(model, HttpStatus.OK);
        }
    }

    @GetMapping(value = {"/list"})
    public String list() {
        return "teach/list";
    }

    @PostMapping(value = {"/list"})
    @ResponseBody
    public ArrayList<Sub_teach> listSubjectTeach(Model model, HttpServletRequest request) {
        String codeSub = RequestTool.getString(request, "codeSub");
        String codeTeach = RequestTool.getString(request, "codeTeach");
        int maxRow = RequestTool.getInt(request, "maxRow", MyConfig.ADMIN_MAX_ROW);
        int crPage = RequestTool.getInt(request, "crPage", 1);
        System.out.println("codeSub: " + codeSub);
        System.out.println("codeTeach: " + codeTeach);
        System.out.println("maxRow: " + maxRow);
        System.out.println("crPage: " + crPage);
        ArrayList<Sub_teach> subTeach = subTeachSerivce.list(crPage, maxRow, codeSub, "ad");
//        int count = teachService.count(codeSub, codeSub, status);
//        System.out.println("count: " + count);
        return subTeach;
    }

    @GetMapping(value = "/check")
    public String getCheck(Model model, @RequestParam(value = "codeSub") String codeSub) {
        model.addAttribute("codeSub", codeSub);
        System.out.println("codeSub: " + codeSub);
        return "teach/check";
    }

    @PostMapping(value = {"/check"})
    @ResponseBody
    public ArrayList<Sub_teach_student> postCheck(Model model, HttpServletRequest request) {
        String codeSub = RequestTool.getString(request, "codeSub");
        String codeTeach = RequestTool.getString(request, "codeTeach");
        int maxRow = RequestTool.getInt(request, "maxRow", MyConfig.ADMIN_MAX_ROW);
        int crPage = RequestTool.getInt(request, "crPage", 1);
        System.out.println("codeSub: " + codeSub);
        System.out.println("codeTeach: " + codeTeach);
        System.out.println("maxRow: " + maxRow);
        System.out.println("crPage: " + crPage);
        ArrayList<Sub_teach_student> subTeach = subTeachStudentSerivce.list(crPage, maxRow, codeSub, "ad", null);
//        int count = teachService.count(codeSub, codeSub, status);
//        System.out.println("count: " + count);
        return subTeach;
    }

    @PutMapping(value = {"/check"})
    @ResponseBody
    public boolean putCheck(Model model, @RequestBody ArrayList<arrayObject> data) {
        Check check = null;
        boolean rs = false;
        for (arrayObject object : data) {
            check = new Check();
            System.out.println("1: " + object.getId());
            System.out.println("1: " + object.getStatus());
            check.setId_sts(object.getId());
            check.setStatus(object.getStatus());
            try {
                rs = checkService.add(check);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rs;
    }
}
