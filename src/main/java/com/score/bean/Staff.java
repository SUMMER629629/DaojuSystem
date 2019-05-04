package com.score.bean;

public class Staff {
    private Integer staffNo;
    private String staffName;
    private String staffSex;
    private String staffAdr;
    private String staffTel;
    private String staffState;

    public Integer getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(Integer staffNo) {
        this.staffNo = staffNo;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName == null ? null : staffName.trim();
    }

    public String getStaffSex() {
        return staffSex;
    }

    public void setStaffSex(String staffSex) {
        this.staffSex = staffSex == null ? null : staffSex.trim();
    }

    public String getStaffAdr() {
        return staffAdr;
    }

    public void setStaffAdr(String staffAdr) {
        this.staffAdr = staffAdr == null ? null : staffAdr.trim();
    }

    public String getStaffTel() {
        return staffTel;
    }

    public void setStaffTel(String staffTel) { this.staffTel = staffTel == null ? null : staffTel.trim(); }

    public String getStaffState() {
        return staffState;
    }

    public void setStaffState(String staffState) {
        this.staffState = staffState == null ? null : staffState.trim();
    }
}
