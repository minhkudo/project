/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.vin.demo.controller;

import com.dev.vin.demo.commons.RequestTool;
import com.dev.vin.demo.commons.Tool;
import com.dev.vin.demo.config.MyConfig;
import com.dev.vin.demo.model.AngularModel;
import com.dev.vin.demo.model.Check;
import com.dev.vin.demo.model.Result;
import com.dev.vin.demo.model.Sub_teach;
import com.dev.vin.demo.model.Sub_teach_student;
import com.dev.vin.demo.service.CheckService;
import com.dev.vin.demo.service.SubTeachService;
import com.dev.vin.demo.service.SubTeachStudentService;
import com.dev.vin.demo.util.Share;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author MinhKudo
 */
@Controller
@RequestMapping(value = "/pageStudent")
public class PageStudentController {

    @Autowired
    private SubTeachService subTeachSerivce;

    @Autowired
    private SubTeachStudentService subTeachStudentSerivce;

    @Autowired
    private CheckService checkService;

    @GetMapping(value = "/index")
    public String getIndexStudent(Model model, HttpSession session, HttpServletRequest request) {
        System.out.println("session: " + session.getId());
        System.out.println("check: " + Share.checkSessionStudent(session.getId()));
        if (!Share.checkSessionStudent(session.getId())) {
            return "redirect:/loginStudent";
        }
        model.addAttribute("token", request.getSession().getAttribute(session.getId()));
        return "pageStudent/index";
    }

    @GetMapping(value = "/DoStuTeaSub")
    public String getReigStudent(Model model, HttpSession session, HttpServletRequest request) {
        System.out.println("session: " + session.getId());
        System.out.println("check: " + Share.checkSessionStudent(session.getId()));
        if (!Share.checkSessionStudent(session.getId())) {
            return "redirect:/loginStudent";
        }
        model.addAttribute("token", request.getSession().getAttribute(session.getId()));
        return "student/DoStuTeaSub";
    }

    @PostMapping(value = {"/DoStuTeaSub"})
    @ResponseBody
    public ResponseEntity<AngularModel<Sub_teach>> listDataDoReig(Model model, HttpServletRequest request) {
        String codeSub = RequestTool.getString(request, "codeSub");
        String codeTeach = RequestTool.getString(request, "codeTeach");
        int status = RequestTool.getInt(request, "status", -1);
        int maxRow = RequestTool.getInt(request, "maxRow", MyConfig.ADMIN_MAX_ROW);
        int crPage = RequestTool.getInt(request, "crPage", 1);
//        System.out.println("codeSub: " + codeSub);
//        System.out.println("codeTeach: " + codeTeach);
//        System.out.println("maxRow: " + maxRow);
//        System.out.println("crPage: " + crPage);
        // Ví dụ lấy thông tin người thực hiện tác vụ createUser từ trong SecurityContext
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // Đây là lấy ra username là email
        String codeStudent = (String) auth.getPrincipal();
        // Để lấy ra các thông tin khác vd như userId thì yêu cầu phải lưu trong thông tin vào object
        // và object được lưu trong Credentials. Gọi hàm getCredentials() để lấy ra object rồi tiếp tục lấy ra các property khác
        // auth.getCredentials()
        // Đây là lấy ra username là email
        System.out.println("codeStudent: " + codeStudent);
        ArrayList<Sub_teach> sub_teach = subTeachSerivce.listDoReig(crPage, maxRow, codeSub, codeTeach, codeStudent);
        int count = sub_teach.size();
        System.out.println("count: " + count);
        AngularModel<Sub_teach> ngModel = new AngularModel<>();
        ngModel.setListObject(sub_teach);
        ngModel.setTotalRow(count);
        if (sub_teach == null || sub_teach.isEmpty()) {
            ngModel.setResult(new Result(Result.WARNING, "Danh sách trống"));
        } else {
            ngModel.setResult(new Result(Result.SUCCESS, ""));
        }
        return new ResponseEntity<>(ngModel, HttpStatus.OK);
    }

    @PostMapping(value = "/registerDo")
    public ResponseEntity<Model> registerPostStudent(Model model,
            @RequestParam(value = "codeSub") String codeSub,
            @RequestParam(value = "codeTeach") String codeTeach) {
        System.out.println("codeSub: " + codeSub);
        System.out.println("codeTeach: " + codeTeach);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // Đây là lấy ra username là email
        String codeStudent = (String) auth.getPrincipal();
        System.out.println("codeStudent: " + codeStudent);

        Sub_teach_student sts = new Sub_teach_student();
        sts.setCode_student(codeStudent);
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
    public String getNotReigStudent(Model model, HttpSession session, HttpServletRequest request) {
        System.out.println("session: " + session.getId());
        System.out.println("check: " + Share.checkSessionStudent(session.getId()));
        if (!Share.checkSessionStudent(session.getId())) {
            return "redirect:/loginStudent";
        }
        model.addAttribute("token", request.getSession().getAttribute(session.getId()));
        return "student/NotStuTeaSub";
    }

    @PostMapping(value = {"/NotStuTeaSub"})
    @ResponseBody
    public ResponseEntity<AngularModel<Sub_teach>> listDataNotReig(Model model, HttpServletRequest request) {
        String codeSub = RequestTool.getString(request, "codeSub");
        String codeTeach = RequestTool.getString(request, "codeTeach");
        int status = RequestTool.getInt(request, "status", -1);
        int maxRow = RequestTool.getInt(request, "maxRow", MyConfig.ADMIN_MAX_ROW);
        int crPage = RequestTool.getInt(request, "crPage", 1);
//        System.out.println("codeSub: " + codeSub);
//        System.out.println("codeTeach: " + codeTeach);
//        System.out.println("maxRow: " + maxRow);
//        System.out.println("crPage: " + crPage);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // Đây là lấy ra username là email
        String codeStudent = (String) auth.getPrincipal();
        System.out.println("codeStudent: " + codeStudent);

        ArrayList<Sub_teach> sub_teach = subTeachSerivce.listNotReig(crPage, maxRow, codeSub, codeTeach, codeStudent);
        int count = sub_teach.size();
        System.out.println("count: " + count);
        AngularModel<Sub_teach> ngModel = new AngularModel<>();
        ngModel.setListObject(sub_teach);
        ngModel.setTotalRow(count);
        if (sub_teach == null || sub_teach.isEmpty()) {
            ngModel.setResult(new Result(Result.WARNING, "Danh sách trống"));
        } else {
            ngModel.setResult(new Result(Result.SUCCESS, ""));
        }
        return new ResponseEntity<>(ngModel, HttpStatus.OK);
    }

    @PostMapping(value = "/registerNot")
    public ResponseEntity<Model> registerNotPostStudent(Model model,
            @RequestParam(value = "codeSub") String codeSub,
            @RequestParam(value = "codeTeach") String codeTeach) {
        System.out.println("codeSub: " + codeSub);
        System.out.println("codeTeach: " + codeTeach);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // Đây là lấy ra username là email
        String codeStudent = (String) auth.getPrincipal();
        System.out.println("codeStudent: " + codeStudent);

        if (subTeachStudentSerivce.delete(codeSub, codeTeach, codeStudent)) {
            model.addAttribute("messing", "Hủy Mon Học Thành Công");
            return new ResponseEntity<>(model, HttpStatus.OK);

        } else {
            model.addAttribute("messing", "Hủy Mon Học Thất Bại");
            return new ResponseEntity<>(model, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/list")
    public String getListStudent(Model model, HttpSession session, HttpServletRequest request) {
        if (!Share.checkSessionStudent(session.getId())) {
            return "redirect:/loginStudent";
        }
        model.addAttribute("token", request.getSession().getAttribute(session.getId()));
        return "student/list";
    }

    @PostMapping(value = {"/list"})
    @ResponseBody
    public ResponseEntity<AngularModel<Sub_teach_student>> listSubjectTeach(Model model, HttpServletRequest request) {
        String codeSub = RequestTool.getString(request, "codeSub");
        String codeTeach = RequestTool.getString(request, "codeTeach");
        int maxRow = RequestTool.getInt(request, "maxRow", MyConfig.ADMIN_MAX_ROW);
        int crPage = RequestTool.getInt(request, "crPage", 1);
//        System.out.println("codeSub: " + codeSub);
//        System.out.println("codeTeach: " + codeTeach);
//        System.out.println("maxRow: " + maxRow);
//        System.out.println("crPage: " + crPage);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // Đây là lấy ra username là email
        String codeStudent = (String) auth.getPrincipal();
        System.out.println("codeStudent: " + codeStudent);

        ArrayList<Sub_teach_student> subTeach = subTeachStudentSerivce.list(crPage, maxRow, codeSub, codeTeach, codeStudent);
        int count = subTeach.size();
        System.out.println("count: " + count);
        AngularModel<Sub_teach_student> ngModel = new AngularModel<>();
        ngModel.setListObject(subTeach);
        ngModel.setTotalRow(count);
        if (subTeach == null || subTeach.isEmpty()) {
            ngModel.setResult(new Result(Result.WARNING, "Danh sách trống"));
        } else {
            ngModel.setResult(new Result(Result.SUCCESS, ""));
        }
        return new ResponseEntity<>(ngModel, HttpStatus.OK);
    }

    @GetMapping(value = "/check")
    public String getCheck(Model model, @RequestParam(value = "id_sts") int id_sts) {
        if (Tool.checkNull(Share.token_student)) {
            return "redirect:/loginStudent";
        }
        model.addAttribute("id_sts", id_sts);
        model.addAttribute("token", Share.token_student);
        System.out.println("id_sts: " + id_sts);
        return "student/check";
    }

    @PostMapping(value = {"/check"})
    @ResponseBody
    public ResponseEntity<AngularModel<Check>> postCheck(Model model, HttpServletRequest request) {
        int id_sts = RequestTool.getInt(request, "id_sts");
        ArrayList<Check> check = checkService.findListCheck(id_sts);
        int count = check.size();
        System.out.println("count: " + count);
        AngularModel<Check> ngModel = new AngularModel<>();
        ngModel.setListObject(check);
        ngModel.setTotalRow(count);
        if (check == null || check.isEmpty()) {
            ngModel.setResult(new Result(Result.WARNING, "Chưa Điểm Danh"));
        } else {
            ngModel.setResult(new Result(Result.SUCCESS, ""));
        }
        return new ResponseEntity<>(ngModel, HttpStatus.OK);
    }
}
