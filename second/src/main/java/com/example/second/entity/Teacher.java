package com.example.second.entity;

import javax.validation.constraints.*;



public class Teacher {
    private Long uid;

    @NotNull(message="姓名不能为空")
    @Pattern(regexp="^[\\u4e00-\\u9fa5]+$",message = "请输入不含空格的中文名")
    @Size(min = 2, message = "姓名不能少于2个字")
    @Size(max = 5, message = "姓名不能多于5个字")
    private String name;

    @NotNull(message = "年龄不能为空")
    @Min(value = 20, message = "年龄必须大于20岁")
    @Max(value = 60, message = "年龄必须小于60岁")
    private Integer age;

    private String sex;

    @NotNull(message = "学科不能为空")
    private String subject;

    @NotNull(message = "班级不能为空")
    private String teach_class;

    @NotNull(message = "班级人数不能为空")
    private Integer num;      //班级人数

    @NotNull(message = "职位不能为空")
    private String post;        //职位

    private String evaluate;      //评价

    private String ed;       //入职日期

    @NotNull(message = "民族不能为空")
    private String folk;     //民族

    @NotNull(message = "联系电话不能为空")
    @Pattern(regexp = "^1[3456789]\\d{9}$",message = "请输入正确的手机号码")
    private String tel;

    @NotNull(message = "电子邮件不能为空")
    @Email
    private String email;

    @NotNull(message = "籍贯不能为空")
    private String np;     //籍贯

    @NotNull(message = "身份证号码不能为空")
    @Pattern(regexp = "\\d{17}[\\d|x]|\\d{15}")
    private String identify;

    @NotNull(message = "家庭住址不能为空")
    private String address;

    private String college;     //就职学院及专业

    @NotNull(message = "毕业学院不能为空")
    private String graduate;    //毕业学校

    private String ps;       //政治面貌

    private String ms;     //婚姻状况

    private Integer passrate;     //班级及格率

    private String hobby;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTeach_class() {
        return teach_class;
    }

    public void setTeach_class(String teach_class) {
        this.teach_class = teach_class;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public String getEd() {
        return ed;
    }

    public void setEd(String ed) {
        this.ed = ed;
    }

    public String getFolk() {
        return folk;
    }

    public void setFolk(String folk) {
        this.folk = folk;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNp() {
        return np;
    }

    public void setNp(String np) {
        this.np = np;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getGraduate() {
        return graduate;
    }

    public void setGraduate(String graduate) {
        this.graduate = graduate;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getMs() {
        return ms;
    }

    public void setMs(String ms) {
        this.ms = ms;
    }

    public Integer getPassrate() {
        return passrate;
    }

    public void setPassrate(Integer passrate) {
        this.passrate = passrate;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
