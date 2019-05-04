package com.score.bean;

public class Daoju {

    private Integer daojuNo;

    private String daojuName;

    private String daojuType;

    private String daojuQuality;

    private String daojuEdge;

    private String daojuShank;

    private Integer daojuTotlen;

    public Integer getDaojuNo() {
        return daojuNo;
    }

    public void setDaojuNo(Integer daojuNo) {
        this.daojuNo = daojuNo;
    }

    public String getDaojuName() {
        return daojuName;
    }

    public void setDaojuName(String daojuName) {
        this.daojuName = daojuName == null ? null : daojuName.trim();
    }

    public String getDaojuType() {
        return daojuType;
    }

    public void setDaojuType(String daojuType) {
        this.daojuType = daojuType == null ? null : daojuType.trim();
    }

    public String getDaojuQuality() {
        return daojuQuality;
    }

    public void setDaojuQuality(String daojuQuality) {
        this.daojuQuality = daojuQuality == null ? null : daojuQuality.trim();
    }

    public String getDaojuEdge() {
        return daojuEdge;
    }

    public void setDaojuEdge(String daojuEdge) {
        this.daojuEdge = daojuEdge == null ? null : daojuEdge.trim();
    }

    public String getDaojuShank() {
        return daojuShank;
    }

    public void setDaojuShank(String daojuShank) {
        this.daojuShank = daojuShank == null ? null : daojuShank.trim();
    }

    public Integer getDaojuTotlen() {
        return daojuTotlen;
    }

    public void setDaojuTotlen(Integer daojuTotlen) {
        this.daojuTotlen = daojuTotlen;
    }


}
