/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.vin.demo.controller;

import com.dev.vin.demo.commons.RequestTool;
import com.dev.vin.demo.config.MyConfig;
import com.dev.vin.demo.model.AngularModel;
import com.dev.vin.demo.model.Result;
import com.dev.vin.demo.model.Student;
import com.dev.vin.demo.model.Sub_teach;
import com.dev.vin.demo.model.Subject;
import com.dev.vin.demo.model.Teach;
import com.dev.vin.demo.service.StudentService;
import com.dev.vin.demo.service.SubTeachService;
import com.dev.vin.demo.service.SubjectService;
import com.dev.vin.demo.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping(value = "/pageAdmin")
@Api(value = "Page Admin")
public class PageAdminController {

    @Autowired
    private TeacherService teachService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SubTeachService subTeachSerivce;

    @Autowired
    private SubjectService subjectService;

    @GetMapping(value = "/index")
    public String getIndexAdmin() {
        return "pageAdmin/index";
    }

    //Giao Vien (Teach) start
    @GetMapping(value = {"/teach", "/teach/view"})
    public String listTeach() {
        return "teach/view";
    }

//    @ApiOperation(value = "Get all user", responseContainer = "List")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "OK"),
//            @ApiResponse(code = 403, message = "Forbidden"),
//            @ApiResponse(code = 404, message = "Not found"),
//            @ApiResponse(code = 500, message = "Internal Server Error"),
//    })
    @PostMapping(value = {"/teach", "/teach/view"})
    @ResponseBody
    public ResponseEntity<AngularModel<Teach>> listDataTeach(Model model, HttpServletRequest request) {
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
        int count = teach.size();
        AngularModel<Teach> ngModel = new AngularModel<>();
        ngModel.setListObject(teach);
        ngModel.setTotalRow(count);
        if (teach == null || teach.isEmpty()) {
            ngModel.setResult(new Result(Result.WARNING, "Danh sách trống"));
        } else {
            ngModel.setResult(new Result(Result.SUCCESS, ""));
        }
        return new ResponseEntity<>(ngModel, HttpStatus.OK);
    }

    @GetMapping(value = "/teach/addRest")
    public String addRestGetTeach(Model model) {
        return "teach/addRest";
    }

    @PostMapping(value = "/teach/addRest")
    @ResponseBody
    public ResponseEntity<Result> addRestPostTeach(Model model, @RequestBody Teach teachForm) {
        System.out.println("teach,getCode(): " + teachForm.getCode());
        System.out.println("teach,getName(): " + teachForm.getName());
        System.out.println("teach,getPassword(): " + teachForm.getPassword());
        System.out.println("teach,getNoti(): " + teachForm.getNoti());
        System.out.println("teach,getStatus(): " + teachForm.getStatus());

        if (teachService.checkCode(teachForm.getCode())) {
            return new ResponseEntity<>(new Result(Result.ERROR, "Mã Bị Trùng"), HttpStatus.OK);
        } else {
            if (teachService.add(teachForm)) {
                return new ResponseEntity<>(new Result(Result.SUCCESS, "Thêm Thành Công"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new Result(Result.ERROR, "Thêm Thất Bại"), HttpStatus.OK);
            }
        }
    }

    @GetMapping(value = "/teach/editRest")
    public String editRestGetTeach(Model model, @RequestParam(value = "id") int id) {
        Teach teach = teachService.find(id);
        model.addAttribute("id", id);
        return "teach/editRest";
    }

    @PostMapping(value = "/teach/editRest")
    @ResponseBody
    public Teach editRestDataPostTeach(Model model, @RequestParam(value = "id") int id) {
        System.out.println("id: " + id);
        return teachService.find(id);
    }

    @PutMapping(value = "/teach/editRest")
    @ResponseBody
    public ResponseEntity<Result> editRestPostTeach(Model model, @RequestBody Teach teachForm) {
        System.out.println("teach: " + teachForm);
//        if (teachService.checkCode(teachForm.getCode())) {
//            return new ResponseEntity<>(new Result(Result.ERROR, "Mã Bị Trùng"), HttpStatus.OK);
//        } else {
        if (teachService.edit(teachForm)) {
            return new ResponseEntity<>(new Result(Result.SUCCESS, "Sửa Thành Công"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Result(Result.ERROR, "Sửa Thất Bại"), HttpStatus.OK);
        }
//        }
    }

    @DeleteMapping(value = "/teach/delete")
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
    //Giao Vien (Teach) end

    //Hoc Sinh (Student) start
    @GetMapping(value = {"/student/", "/student/view"})
    public String listStudent() {
        return "student/view";
    }

    @PostMapping(value = {"/student/", "/student/view"})
    @ResponseBody
    public ResponseEntity<AngularModel<Student>> listDataStudent(Model model, HttpServletRequest request) {
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
        ArrayList<Student> student = studentService.list(crPage, maxRow, code, name, status);
        int count = studentService.count(code, name, status);
        AngularModel<Student> ngModel = new AngularModel<>();
        ngModel.setListObject(student);
        ngModel.setTotalRow(count);
        System.out.println("count: " + count);
        if (student == null || student.isEmpty()) {
            ngModel.setResult(new Result(Result.WARNING, "Danh sách trống"));
        } else {
            ngModel.setResult(new Result(Result.SUCCESS, ""));
        }
        return new ResponseEntity<>(ngModel, HttpStatus.OK);
    }

    @GetMapping(value = "/student/addRest")
    public String addRestGetStudent(Model model) {
        return "student/addRest";
    }

    @PostMapping(value = "/student/addRest")
    public ResponseEntity<Result> addRestPostStudent(Model model, @RequestBody Student studentForm) {
        System.out.println("student,getCode(): " + studentForm.getCode());
        System.out.println("student,getName(): " + studentForm.getName());
        System.out.println("student,getPassword(): " + studentForm.getPassword());
        System.out.println("student,getNoti(): " + studentForm.getNoti());
        System.out.println("student,getStatus(): " + studentForm.getStatus());

        if (studentService.checkCode(studentForm.getCode())) {
            return new ResponseEntity<>(new Result(Result.ERROR, "Mã Bị Trùng"), HttpStatus.OK);
        } else {
            if (studentService.add(studentForm)) {
                return new ResponseEntity<>(new Result(Result.SUCCESS, "Thêm Thành Công"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new Result(Result.ERROR, "Thêm Thất Bại"), HttpStatus.OK);
            }
        }
    }

    @GetMapping(value = "/student/editRest")
    public String editRestGetStudent(Model model, @RequestParam(value = "id") int id) {
        Student student = studentService.find(id);
        model.addAttribute("student", student);
        model.addAttribute("id", id);
        return "student/editRest";
    }

    @PostMapping(value = "/student/editRest")
    @ResponseBody
    public Student editRestDataPostStudent(Model model, @RequestParam(value = "id") int id) {
        System.out.println("id: " + id);
        return studentService.find(id);
    }

    @PutMapping(value = "/student/editRest")
    @ResponseBody
    public ResponseEntity<Result> editRestPostStudent(Model model, @RequestBody Student studentForm) {
        System.out.println("student: " + studentForm);
        if (studentService.edit(studentForm)) {
            return new ResponseEntity<>(new Result(Result.SUCCESS, "Thêm Thành Công"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Result(Result.ERROR, "Thêm Thất Bại"), HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/student/delete")
    public ResponseEntity<Model> deletePostStudent(Model model, @RequestParam(value = "id") int id) {
        System.out.println("id: " + id);
        if (studentService.delete(id)) {
            model.addAttribute("messing", "Xóa Thành Công");
            return new ResponseEntity<>(model, HttpStatus.OK);

        } else {
            model.addAttribute("messing", "Xóa Thất Bại");
            return new ResponseEntity<>(model, HttpStatus.OK);
        }
    }
    //Hoc Sinh (Student) end

    //Môn Học (Subject) start
    @GetMapping(value = {"/subject/", "/subject/view"})
    public String listSubject() {
        return "subject/view";
    }

    @PostMapping(value = {"/subject/", "/subject/view"})
    @ResponseBody
    public ResponseEntity<AngularModel<Subject>> listDataSubject(Model model, HttpServletRequest request) {
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
        AngularModel<Subject> ngModel = new AngularModel<>();
        ngModel.setListObject(subject);
        ngModel.setTotalRow(count);
        if (subject == null || subject.isEmpty()) {
            ngModel.setResult(new Result(Result.WARNING, "Danh sách trống"));
        } else {
            ngModel.setResult(new Result(Result.SUCCESS, ""));
        }
        return new ResponseEntity<>(ngModel, HttpStatus.OK);
    }

    @GetMapping(value = "/subject/addRest")
    public String addRestGetSubject(Model model) {
        return "subject/addRest";
    }

    @PostMapping(value = "/subject/addRest")
    @ResponseBody
    public ResponseEntity<Result> addRestPostSubject(Model model, @RequestBody Subject subjectForm) {
        System.out.println("subject,getCode(): " + subjectForm.getCode());
        System.out.println("subject,getName(): " + subjectForm.getName());
        System.out.println("subject,getNumber(): " + subjectForm.getNumber());
        System.out.println("subject,getYear(): " + subjectForm.getYear());
        if (subjectService.checkCode(subjectForm.getCode())) {
            return new ResponseEntity<>(new Result(Result.ERROR, "Mã Bị Trùng"), HttpStatus.OK);
        } else {
            if (subjectService.add(subjectForm)) {
                return new ResponseEntity<>(new Result(Result.SUCCESS, "Thêm Thành Công"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new Result(Result.ERROR, "Thêm Thất Bại"), HttpStatus.OK);
            }
        }
    }

    @GetMapping(value = "/subject/editRest")
    public String editRestGetSubject(Model model, @RequestParam(value = "id") int id) {
        model.addAttribute("id", id);
        return "subject/editRest";
    }

    @PostMapping(value = "/subject/editRest")
    @ResponseBody
    public Subject editRestDataPostSubject(Model model, @RequestParam(value = "id") int id) {
        System.out.println("id: " + id);
        return subjectService.find(id);
    }

    @PutMapping(value = "/subject/editRest")
    @ResponseBody
    public ResponseEntity<Result> editRestPostSubject(Model model, @RequestBody Subject subjectForm) {
        System.out.println("subject: " + subjectForm);
        if (subjectService.edit(subjectForm)) {
            return new ResponseEntity<>(new Result(Result.SUCCESS, "Sửa Thành Công"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Result(Result.ERROR, "Sửa Thất Bại"), HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/subject/delete")
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
    //Môn Học (Subject) end

    //Môn + Giáo Viên (Sub + teach) start
    @GetMapping(value = {"/subTeach/", "/subTeach/view"})
    public String listSubTeach() {
        return "subTeach/view";
    }

    @PostMapping(value = {"/subTeach/", "/subTeach/view"})
    @ResponseBody
    public ResponseEntity<AngularModel<Sub_teach>> listDataSubTeach(Model model, HttpServletRequest request) {
        String codeSub = RequestTool.getString(request, "codeSub");
        String codeTeach = RequestTool.getString(request, "codeTeach");
        int maxRow = RequestTool.getInt(request, "maxRow", MyConfig.ADMIN_MAX_ROW);
        int crPage = RequestTool.getInt(request, "crPage", 1);
        System.out.println("code: " + codeSub);
        System.out.println("name: " + codeTeach);
        System.out.println("maxRow: " + maxRow);
        System.out.println("crPage: " + crPage);
        ArrayList<Sub_teach> subTeach = subTeachSerivce.list(crPage, maxRow, codeSub, codeTeach);
        int count = subTeach.size();
        System.out.println("count: " + count);
        AngularModel<Sub_teach> ngModel = new AngularModel<>();
        ngModel.setListObject(subTeach);
        ngModel.setTotalRow(count);
        System.out.println("count: " + count);
        if (subTeach == null || subTeach.isEmpty()) {
            ngModel.setResult(new Result(Result.WARNING, "Danh sách trống"));
        } else {
            ngModel.setResult(new Result(Result.SUCCESS, ""));
        }
        return new ResponseEntity<>(ngModel, HttpStatus.OK);
    }

    @GetMapping(value = "/subTeach/editRest")
    public String editRestGetSubTeach(Model model, @RequestParam(value = "id") int id) {
        model.addAttribute("id", id);
        return "subTeach/editRest";
    }

    @PutMapping(value = "/subTeach/editRest")
    @ResponseBody
    public Sub_teach editRestDataPostSubTeach(Model model, @RequestParam(value = "id") int id) {
        System.out.println("id: " + id);
        return subTeachSerivce.find(id);
    }

    @PostMapping(value = "/subTeach/editRest")
    @ResponseBody
    public ResponseEntity<Result> editRestPostSubTeach(Model model, @RequestBody Sub_teach subTeach) {
        System.out.println("getCode_sub: " + subTeach.getCode_sub());
        System.out.println("getCode_teach: " + subTeach.getCode_teach());
        if (subTeachSerivce.edit(subTeach)) {
            return new ResponseEntity<>(new Result(Result.SUCCESS, "Sửa Thành Công"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Result(Result.ERROR, "Sửa Thất Bại"), HttpStatus.OK);
        }
    }
    //Môn + Giáo Viên (Sub + teach) end
}
