package com.example.second.service.impl;

//import com.example.second.dao.TeacherCopyDao;
//import com.example.second.dao.TeacherCountDao;
import com.example.second.dao.TeacherDao;
import com.example.second.entity.Teacher;
import com.example.second.exception.ValidateException;
import com.example.second.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional(rollbackFor = Exception.class)
public class TeacherServiceBean implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;

//    @Autowired
//    private TeacherCountDao teacherCountDao;

//    @Autowired
//    TeacherCopyDao teacherCopyDao;

    public List<Teacher> findAllTeachers() {
        return this.teacherDao.findAllTeachers();
    }


    //返回错误数组
    public List<String> errors(Teacher teacher){
        List<String> errors = new ArrayList<>();
        if (teacher.getName() == null || teacher.getName().equals("") || teacher.getName().length()<2||teacher.getName().matches("^[\\u4e00-\\u9fa5]+$")){
            errors.add("名字输入有误");
        }
        if (teacher.getAge() == null || teacher.getAge()<20 || teacher.getAge()>60){
            errors.add("年龄错误");
        }
        if (teacher.getSex() == null){
            errors.add("性别错误");
        }
        if (teacher.getIdentify() == null || teacher.getIdentify().matches("\\d{17}[\\d|x]|\\d{15}")){
            errors.add("身份证号码格式错误");
        }
        if (teacher.getTel() == null || teacher.getTel().matches("^1[3456789]\\d{9}$")){
            errors.add("联系电话错误");
        }
        if (teacher.getEmail() == null || teacher.getEmail().matches("[\\w\\.\\-]+@[\\w\\-]+[\\w\\-]+")){
            errors.add("邮箱格式错误");
        }
        return errors;
    }

    @Transactional
    public void addTeacher(Teacher teacher) {
        if (errors(teacher).size() > 0){
            throw new ValidateException("-1", errors(teacher).toString());
        } else {
            this.teacherDao.addTeacher(teacher);
        }
    }

    public void deleteTeacherById(Long uid) {
        this.teacherDao.deleteTeacherById(uid);
        Integer count = this.teacherDao.getCountTeachers();
//        this.teacherCountDao.addCount(count);
    }

    public void updateTeacher(Teacher teacher) {
        this.teacherDao.updateTeacher(teacher);
    }

    public List<Teacher> findTeacherByName(String name) {
        return this.teacherDao.findTeacherByName(name);
    }

    public Integer deleteTeachersByIds(Integer[] ids) {
        return this.teacherDao.deleteTeachersByIds(ids);
    }

}
