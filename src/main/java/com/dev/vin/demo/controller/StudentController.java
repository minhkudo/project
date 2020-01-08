/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.vin.demo.controller;

import com.dev.vin.demo.commons.RequestTool;
import com.dev.vin.demo.config.MyConfig;
import com.dev.vin.demo.dao.StudentDao;
import com.dev.vin.demo.model.AngularModel;
import com.dev.vin.demo.model.Check;
import com.dev.vin.demo.model.Student;
import com.dev.vin.demo.model.Sub_teach;
import com.dev.vin.demo.model.Sub_teach_student;
import com.dev.vin.demo.service.CheckService;
import com.dev.vin.demo.service.StudentService;
import com.dev.vin.demo.service.SubTeachService;
import com.dev.vin.demo.service.SubTeachStudentService;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author MinhKudo
 */

@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private SubTeachService subTeachSerivce;

    @Autowired
    private SubTeachStudentService subTeachStudentSerivce;

    @Autowired
    private CheckService checkService;

    @GetMapping(value = {"/", "/view"})
    public String listStudent() {
        return "student/view";
    }

    @PostMapping(value = {"/", "/view"})
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
        return new ResponseEntity<>(ngModel, HttpStatus.OK);
    }

//    @GetMapping(value = "/add")
//    public String addGetStudent(Model model) {
//        Student student = new Student();
//        model.addAttribute("student", student);
//        return "student/add";
//    }
//
//    @PostMapping(value = "/add")
//    public String addPostStudent(Model model, HttpServletRequest request, Student student, RedirectAttributes rdrAtt) {
//        System.out.println("student: " + student);
//        if (studentService.checkCode(student.getCode())) {
//            model.addAttribute("messing", "Mã Sinh Viên Đã Tồn Tại");
//            model.addAttribute("student", student);
//            return "student/add";
//        } else {
//            if (studentService.add(student)) {
//                rdrAtt.addFlashAttribute("messing", "Thêm Mới Thành Công");
//                return "redirect:/student/view";
//            } else {
//                rdrAtt.addFlashAttribute("messing", "Thêm Mới Thất Bại");
//                model.addAttribute("messing", "Cập Nhật Thất Bại");
//                model.addAttribute("student", student);
//                return "student/add";
//            }
//        }
//    }
    @GetMapping(value = "/addRest")
    public String addRestGetStudent(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "student/addRest";
    }

    @PostMapping(value = "/addRest")
    public ResponseEntity<Model> addRestPostStudent(Model model, @RequestBody Student studentForm) {
        System.out.println("student,getCode(): " + studentForm.getCode());
        System.out.println("student,getName(): " + studentForm.getName());
        System.out.println("student,getPassword(): " + studentForm.getPassword());
        System.out.println("student,getNoti(): " + studentForm.getNoti());
        System.out.println("student,getStatus(): " + studentForm.getStatus());
        if (studentService.checkCode(studentForm.getCode())) {
            model.addAttribute("message", "Mã bị trùng");
        } else {
            if (studentService.add(studentForm)) {
                model.addAttribute("message", "Thêm mới thành công");
            } else {
                model.addAttribute("message", "Thêm mới thất bại");
            }
        }
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

//    @GetMapping(value = "/edit")
//    public String editGetStudent(Model model, @RequestParam(value = "id") int id) {
//        Student student = studentService.find(id);
//        model.addAttribute("student", student);
//        return "student/edit";
//    }
//
//    @PostMapping(value = "/edit")
//    public String editPostStudent(Model model, HttpServletRequest request, Student student, RedirectAttributes rdrAtt) {
//        System.out.println("student: " + student);
//        if (studentService.edit(student)) {
//            rdrAtt.addFlashAttribute("messing", "Cập Nhật Thành Công");
//            return "redirect:/student/view";
//        } else {
//            rdrAtt.addFlashAttribute("messing", "Cập Nhật Thất Bại");
//            model.addAttribute("messing", "Cập Nhật Thất Bại");
//            model.addAttribute("student", student);
//            return "student/edit";
//        }
//    }
    @GetMapping(value = "/editRest")
    public String editRestGetStudent(Model model, @RequestParam(value = "id") int id) {
        Student student = studentService.find(id);
        model.addAttribute("student", student);
        model.addAttribute("id", id);
        return "student/editRest";
    }

    @PutMapping(value = "/editRest")
    @ResponseBody
    public Student editRestDataPostStudent(Model model, @RequestParam(value = "id") int id) {
        System.out.println("id: " + id);
        return studentService.find(id);
    }

    @PostMapping(value = "/editRest")
    @ResponseBody
    public boolean editRestPostStudent(Model model, @RequestBody Student studentForm) {
        System.out.println("student: " + studentForm);
        return studentService.edit(studentForm);
    }

    @PostMapping(value = "/delete")
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

    @GetMapping(value = "/DoStuTeaSub")
    public String getReigStudent(Model model) {
        return "student/DoStuTeaSub";
    }

    @PostMapping(value = {"/DoStuTeaSub"})
    @ResponseBody
    public ArrayList<Sub_teach> listDataDoReig(Model model, HttpServletRequest request) {
        String codeSub = RequestTool.getString(request, "codeSub");
        String codeTeach = RequestTool.getString(request, "codeTeach");
        int status = RequestTool.getInt(request, "status", -1);
        int maxRow = RequestTool.getInt(request, "maxRow", MyConfig.ADMIN_MAX_ROW);
        int crPage = RequestTool.getInt(request, "crPage", 1);
        System.out.println("codeSub: " + codeSub);
        System.out.println("codeTeach: " + codeTeach);
        System.out.println("maxRow: " + maxRow);
        System.out.println("crPage: " + crPage);
        ArrayList<Sub_teach> Sub_teach = subTeachSerivce.listDoReig(crPage, maxRow, codeSub, codeTeach);
//        int count = subTeachSerivce.count(code, name, status);
//        System.out.println("count: " + count);
        return Sub_teach;
    }

    @PostMapping(value = "/registerDo")
    public ResponseEntity<Model> registerPostStudent(Model model,
            @RequestParam(value = "codeSub") String codeSub,
            @RequestParam(value = "codeTeach") String codeTeach) {
        System.out.println("codeSub: " + codeSub);
        System.out.println("codeTeach: " + codeTeach);
        Sub_teach_student sts = new Sub_teach_student();
        sts.setCode_student("ad");
        sts.setCode_sub(codeSub);
        sts.setCode_teach(codeTeach);
        if (subTeachStudentSerivce.add(sts)) {
            model.addAttribute("messing", "Đăng Ký Thành Công");
            return new ResponseEntity<>(model, HttpStatus.OK);

        } else {
            model.addAttribute("messing", "Đăng Ký Thất Bại");
            return new ResponseEntity<>(model, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/NotStuTeaSub")
    public String getNotReigStudent(Model model) {
        return "student/NotStuTeaSub";
    }

    @PostMapping(value = {"/NotStuTeaSub"})
    @ResponseBody
    public ArrayList<Sub_teach> listDataNotReig(Model model, HttpServletRequest request) {
        String codeSub = RequestTool.getString(request, "codeSub");
        String codeTeach = RequestTool.getString(request, "codeTeach");
        int status = RequestTool.getInt(request, "status", -1);
        int maxRow = RequestTool.getInt(request, "maxRow", MyConfig.ADMIN_MAX_ROW);
        int crPage = RequestTool.getInt(request, "crPage", 1);
        System.out.println("codeSub: " + codeSub);
        System.out.println("codeTeach: " + codeTeach);
        System.out.println("maxRow: " + maxRow);
        System.out.println("crPage: " + crPage);
        ArrayList<Sub_teach> Sub_teach = subTeachSerivce.listNotReig(crPage, maxRow, codeSub, codeTeach);
//        int count = subTeachSerivce.count(code, name, status);
//        System.out.println("count: " + count);
        return Sub_teach;
    }

    @PostMapping(value = "/registerNot")
    public ResponseEntity<Model> registerNotPostStudent(Model model,
            @RequestParam(value = "codeSub") String codeSub,
            @RequestParam(value = "codeTeach") String codeTeach) {
        System.out.println("codeSub: " + codeSub);
        System.out.println("codeTeach: " + codeTeach);
        if (subTeachStudentSerivce.delete(codeSub, codeTeach, "ad")) {
            model.addAttribute("messing", "Hủy Mon Học Thành Công");
            return new ResponseEntity<>(model, HttpStatus.OK);

        } else {
            model.addAttribute("messing", "Hủy Mon Học Thất Bại");
            return new ResponseEntity<>(model, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/list")
    public String getListStudent(Model model) {
        return "student/list";
    }

    @PostMapping(value = {"/list"})
    @ResponseBody
    public ArrayList<Sub_teach_student> listSubjectTeach(Model model, HttpServletRequest request) {
        String codeSub = RequestTool.getString(request, "codeSub");
        String codeTeach = RequestTool.getString(request, "codeTeach");
        int maxRow = RequestTool.getInt(request, "maxRow", MyConfig.ADMIN_MAX_ROW);
        int crPage = RequestTool.getInt(request, "crPage", 1);
        System.out.println("codeSub: " + codeSub);
        System.out.println("codeTeach: " + codeTeach);
        System.out.println("maxRow: " + maxRow);
        System.out.println("crPage: " + crPage);
        ArrayList<Sub_teach_student> subTeach = subTeachStudentSerivce.list(crPage, maxRow, codeSub, codeTeach, "ad");
//        int count = teachService.count(codeSub, codeSub, status);
//        System.out.println("count: " + count);
        return subTeach;
    }

    @GetMapping(value = "/check")
    public String getCheck(Model model, @RequestParam(value = "id_sts") int id_sts) {
        model.addAttribute("id_sts", id_sts);
        System.out.println("id_sts: " + id_sts);
        return "student/check";
    }

    @PostMapping(value = {"/check"})
    @ResponseBody
    public ArrayList<Check> postCheck(Model model, HttpServletRequest request) {
        int id_sts = RequestTool.getInt(request, "id_sts");
        ArrayList<Check> subTeach = checkService.findListCheck(id_sts);
//        int count = teachService.count(codeSub, codeSub, status);
//        System.out.println("count: " + count);
        return subTeach;
    }
}
