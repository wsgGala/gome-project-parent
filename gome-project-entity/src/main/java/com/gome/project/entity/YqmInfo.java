package com.gome.project.entity;

public class YqmInfo {
    private String phone;

    private String yaoqingma;

    private Long usecount;

    private String zzzt;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getYaoqingma() {
        return yaoqingma;
    }

    public void setYaoqingma(String yaoqingma) {
        this.yaoqingma = yaoqingma == null ? null : yaoqingma.trim();
    }

    public Long getUsecount() {
        return usecount;
    }

    public void setUsecount(Long usecount) {
        this.usecount = usecount;
    }

    public void setZzzt(String zzzt) {
        this.zzzt = zzzt;
    }

    public String getZzzt() {
        return zzzt;
    }
}