package com.score.bean;

import java.util.Date;

public class Plc {

    private Integer plcId;

    private Integer plcdjNo;

    private Date plcTime;

    private Float spindleLoad;

    private Float plcx;

    private Float plcy;

    private Float plcz;

    public Plc(Integer plcId,Integer plcdjNo,Date plcTime,Float spindleLoad,Float plcx,Float plcy,Float plcz){
        this.plcId = plcId;
        this.plcdjNo = plcdjNo;
        this.plcTime = plcTime;
        this.spindleLoad = spindleLoad;
        this.plcx = plcx;
        this.plcy = plcy;
        this.plcz = plcz;
    }

    public Plc(){
        super();
    }

    public Integer getPlcId() {
        return plcId;
    }

    public void setPlcId(Integer plcId) {
        this.plcId = plcId;
    }

    public Integer getPlcdjNo(){return plcdjNo;}

    public void setPlcdjNo(Integer plcdjNo){this.plcdjNo = plcdjNo;}

    public Date getPlcTime(){ return plcTime;}

    public void setPlcTime(Date plcTime){ this.plcTime = plcTime;}

    public Float getSpindleLoad(){return spindleLoad;}

    public void setSpindleLoad(Float spindleLoad){this.spindleLoad = spindleLoad;}

    public Float getPlcx(){return plcx;}

    public void setPlcx(Float plcx){this.plcx = plcx;}

    public Float getPlcy(){return plcy;}

    public void setPlcy(Float plcy){this.plcy = plcy;}

    public Float getPlcz(){return plcz;}

    public void setPlcz(Float plcz){this.plcz = plcz;}

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
        Plc other = (Plc) that;
        return (this.getPlcId() == null ? other.getPlcId() == null : this.getPlcId().equals(other.getPlcId()))
                && (this.getPlcdjNo() == null ? other.getPlcdjNo() == null : this.getPlcdjNo().equals(other.getPlcdjNo()))
                && (this.getPlcTime() == null ? other.getPlcTime() == null : this.getPlcTime().equals(other.getPlcTime()))
                && (this.getSpindleLoad() == null ? other.getSpindleLoad() == null : this.getSpindleLoad().equals(other.getSpindleLoad()))
                && (this.getPlcx() == null ? other.getPlcx() == null : this.getPlcx().equals(other.getPlcx()))
                && (this.getPlcy() == null ? other.getPlcy() == null : this.getPlcy().equals(other.getPlcy()))
                && (this.getPlcz() == null ? other.getPlcz() == null : this.getPlcz().equals(other.getPlcz()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPlcId() == null) ? 0 : getPlcId().hashCode());
        result = prime * result + ((getPlcdjNo() == null) ? 0 : getPlcdjNo().hashCode());
        result = prime * result + ((getPlcTime() == null) ? 0 : getPlcTime().hashCode());
        result = prime * result + ((getSpindleLoad() == null) ? 0 : getSpindleLoad().hashCode());
        result = prime * result + ((getPlcx() == null) ? 0 : getPlcx().hashCode());
        result = prime * result + ((getPlcy() == null) ? 0 : getPlcy().hashCode());
        result = prime * result + ((getPlcz() == null) ? 0 : getPlcz().hashCode());
        return result;
    }



}
