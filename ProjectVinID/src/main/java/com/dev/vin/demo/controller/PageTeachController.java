/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.vin.demo.controller;

import com.dev.vin.demo.commons.RequestTool;
import com.dev.vin.demo.config.MyConfig;
import com.dev.vin.demo.model.AngularModel;
import com.dev.vin.demo.model.Check;
import com.dev.vin.demo.model.RequestJsonClient;
import com.dev.vin.demo.model.Result;
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

/**
 *
 * @author MinhKudo
 */
@Controller
@RequestMapping(value = "/pageTeach")
public class PageTeachController {

    @Autowired
    private SubTeachService subTeachSerivce;

    @Autowired
    private SubTeachStudentService subTeachStudentSerivce;

    @Autowired
    private CheckService checkService;

    @GetMapping(value = "/index")
    public String getIndexTeach() {
        return "pageTeach/index";
    }

    @GetMapping(value = {"/list"})
    public String list() {
        return "teach/list";
    }

    @PostMapping(value = {"/list"})
    @ResponseBody
    public ResponseEntity<AngularModel<Sub_teach>> listSubjectTeach(Model model, HttpServletRequest request) {
        String codeSub = RequestTool.getString(request, "codeSub");
        String codeTeach = RequestTool.getString(request, "codeTeach");
        int maxRow = RequestTool.getInt(request, "maxRow", MyConfig.ADMIN_MAX_ROW);
        int crPage = RequestTool.getInt(request, "crPage", 1);
        System.out.println("codeSub: " + codeSub);
        System.out.println("codeTeach: " + codeTeach);
        System.out.println("maxRow: " + maxRow);
        System.out.println("crPage: " + crPage);
        ArrayList<Sub_teach> subTeach = subTeachSerivce.list(crPage, maxRow, codeSub, "ad");
        int count = subTeach.size();
        System.out.println("count: " + count);
        AngularModel<Sub_teach> ngModel = new AngularModel<>();
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
    public String getCheck(Model model, @RequestParam(value = "codeSub") String codeSub) {
        model.addAttribute("codeSub", codeSub);
        System.out.println("codeSub: " + codeSub);
        return "teach/check";
    }

    @PostMapping(value = {"/check"})
    @ResponseBody
    public ResponseEntity<AngularModel<Sub_teach_student>> postCheck(Model model, HttpServletRequest request) {
        String codeSub = RequestTool.getString(request, "codeSub");
        String codeTeach = RequestTool.getString(request, "codeTeach");
        int maxRow = RequestTool.getInt(request, "maxRow", MyConfig.ADMIN_MAX_ROW);
        int crPage = RequestTool.getInt(request, "crPage", 1);
        System.out.println("codeSub: " + codeSub);
        System.out.println("codeTeach: " + codeTeach);
        System.out.println("maxRow: " + maxRow);
        System.out.println("crPage: " + crPage);
        ArrayList<Sub_teach_student> subTeachStu = subTeachStudentSerivce.list(crPage, maxRow, codeSub, "ad", null);
        int count = subTeachStu.size();
        System.out.println("count: " + count);
        AngularModel<Sub_teach_student> ngModel = new AngularModel<>();
        ngModel.setListObject(subTeachStu);
        ngModel.setTotalRow(count);
        if (subTeachStu == null || subTeachStu.isEmpty()) {
            ngModel.setResult(new Result(Result.WARNING, "Danh sách trống"));
        } else {
            ngModel.setResult(new Result(Result.SUCCESS, ""));
        }
        return new ResponseEntity<>(ngModel, HttpStatus.OK);
    }

    @PutMapping(value = {"/check"})
    @ResponseBody
    public ResponseEntity<Result> putCheck(Model model, @RequestBody ArrayList<RequestJsonClient.arrayObject> data) {
        Check check = null;
        Result rs = new Result(Result.SUCCESS, "Điểm Danh Thất Bại");
        for (RequestJsonClient.arrayObject object : data) {
            check = new Check();
            System.out.println("1: " + object.getId());
            System.out.println("1: " + object.getStatus());
            check.setId_sts(object.getId());
            check.setStatus(object.getStatus());
            try {
                if (!checkService.add(check)) {
                    rs = new Result(Result.SUCCESS, "Điểm Danh Thành Công");
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }
}
