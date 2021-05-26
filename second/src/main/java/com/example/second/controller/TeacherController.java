package com.example.second.controller;


import com.example.second.entity.Teacher;
import com.example.second.service.MenuService;
import com.example.second.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/teacher")
@CrossOrigin(allowCredentials = "true")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    MenuService menuService;

    /**
     * 查询所有老师
     */
    @GetMapping("/findAllTeachers")
    public List<Teacher> findAllTeachers() {
        return this.teacherService.findAllTeachers();
    }

    /**
     * 增加老师
     */
    @PostMapping("/addTeacher")
    public List<FieldError> addTeacher( @RequestBody @Valid Teacher teacher, BindingResult result) {
        if (result.hasErrors()){
            List<FieldError> errors = result.getFieldErrors();
            return errors;
        }else {
            List<FieldError> errors = null;
            this.teacherService.addTeacher(teacher);
            return errors;
        }
    }

    /**
     * 根据老师id删除老师
     */
    @PostMapping("/deleteTeacher")
    public void deleteTeacherById(@RequestParam Long uid) {
        this.teacherService.deleteTeacherById(uid);
    }

    /**
     * 编辑老师
     */
    @PostMapping("/updateTeacher")
    public List<FieldError> updateTeacher(@RequestBody @Valid Teacher teacher, BindingResult result) {
        if (result.hasErrors()){
            List<FieldError> errors = result.getFieldErrors();
            return errors;
        }else {
            List<FieldError> errors = null;
            this.teacherService.updateTeacher(teacher);
            return errors;
        }
    }

    /**
     * 根据老师姓名查找老师
     */
    @GetMapping("/findTeacherByName")
    public List<Teacher> findTeacherByName(@RequestParam String name) {
        return this.teacherService.findTeacherByName(name);
    }

    /**
     * 批量删除老师
     */
    @PostMapping("/deleteTeachersByIds")
    public Integer deleteTeachersByIds(Integer[] ids) {
        return this.teacherService.deleteTeachersByIds(ids);
    }
}
