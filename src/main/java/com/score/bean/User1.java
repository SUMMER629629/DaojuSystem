package com.score.bean;

import java.io.Serializable;
import java.util.Date;

public class User1 implements Serializable {
    private Integer id;

    private String name;

    private String phone;

    private String address;

    private Date enrolDate;

    private String des;

    private static final long serialVersionUID = 1L;

    public User1(Integer id, String name, String phone, String address, Date enrolDate, String des) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.enrolDate = enrolDate;
        this.des = des;
    }

    public User1() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Date getEnrolDate() {
        return enrolDate;
    }

    public void setEnrolDate(Date enrolDate) {
        this.enrolDate = enrolDate;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des == null ? null : des.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        User1 other = (User1) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
                && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
                && (this.getEnrolDate() == null ? other.getEnrolDate() == null : this.getEnrolDate().equals(other.getEnrolDate()))
                && (this.getDes() == null ? other.getDes() == null : this.getDes().equals(other.getDes()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getEnrolDate() == null) ? 0 : getEnrolDate().hashCode());
        result = prime * result + ((getDes() == null) ? 0 : getDes().hashCode());
        return result;
    }
}