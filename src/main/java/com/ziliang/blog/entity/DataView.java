package com.ziliang.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 可视化数据的实体类，目前包括一个日期，与一个count值
 */
public class DataView {

    private Date date;
    private Integer count;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "DataView{" +
                "date=" + getDate() +
                ", count=" + getCount() +
                '}';
    }
}
