package com.gome.project.entity;

import java.util.Date;

public class User {
    private Long id;

    private String phone;

    private String password;

    private String nicheng;

    private Byte sex;

    private Long money;

    private Date updtime;

    private Date createtime;

    /**
     * 使用状态，Y：可用，N：删除状态，不可用
     */
    private String syzt ;

    private String username;

    private String address;

    private String email;

    private String salt;

    private String yaoqingma;

    public User(){
        //新建用户默认可以使用
        //this.syzt = "Y";

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getNicheng() {
        return nicheng;
    }

    public void setNicheng(String nicheng) {
        this.nicheng = nicheng == null ? null : nicheng.trim();
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Date getUpdtime() {
        return updtime;
    }

    public void setUpdtime(Date updtime) {
        this.updtime = updtime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public void setSyzt(String syzt) {
        this.syzt = syzt;
    }

    public String getSyzt() {
        return syzt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSalt() {
        return salt;
    }

    public void setYaoqingma(String yaoqingma) {
        this.yaoqingma = yaoqingma;
    }

    public String getYaoqingma() {
        return yaoqingma;
    }
}