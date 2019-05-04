package com.score.bean;

import java.util.Date;

public class Relife {
    private Integer redaojuNo;

    private String redaojuName;

    private Integer machineId;

    private Integer redaojuState;

    private Integer restaffId;

    private Date durTime;

    private Float useTime;

    private Float restTime;

    private  Integer flag;

    public Integer getRedaojuNo() {
        return redaojuNo;
    }

    public void setRedaojuNo(Integer redaojuNo) {
        this.redaojuNo = redaojuNo;
    }

    public String getRedaojuName() {
        return redaojuName;
    }

    public void setRedaojuName(String redaojuName) {
        this.redaojuName = redaojuName == null ? null : redaojuName.trim();
    }

    public Integer getMachineId() {
        return machineId;
    }

    public void setMachineId(Integer machineId) {
        this.machineId = machineId;
    }

    public Integer getRedaojuState() {
        return redaojuState;
    }

    public void setRedaojuState(Integer redaojuState) {
        this.redaojuState = redaojuState;
    }

    public Integer getRestaffId() {
        return restaffId;
    }

    public void setRestaffId(Integer restaffId) {
        this.restaffId = restaffId;
    }

    public Date getDurTime() {
        return durTime;
    }

    public void setDurTime(Date durTime) {
        this.durTime = durTime;
    }

    public Float getUseTime() {
        return useTime;
    }

    public void setUseTime(Float useTime) {
        this.useTime = useTime;
    }

    public Float getRestTime() {
        return restTime;
    }

    public void setRestTime(Float restTime) {
        this.restTime = restTime;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

}
