package com.iscas.sdas.dto;

import java.util.Date;

import com.iscas.sdas.common.BaseDto;

public class FileLogDto extends BaseDto{
    private Integer id;

    private String filename;

    private String type;

    private Date starttime;

    private Date endtime;

    private Date methodstart;

    private Date methodend;

    private Long alltime;

    private Integer result;// 1是成功；0是失败

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Date getMethodstart() {
        return methodstart;
    }

    public void setMethodstart(Date methodstart) {
        this.methodstart = methodstart;
    }

    public Date getMethodend() {
        return methodend;
    }

    public void setMethodend(Date methodend) {
        this.methodend = methodend;
    }

    public Long getAlltime() {
        return alltime;
    }

    public void setAlltime(Long alltime) {
        this.alltime = alltime;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}