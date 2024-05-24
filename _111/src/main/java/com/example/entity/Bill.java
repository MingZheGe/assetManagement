package com.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.awt.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Bill implements Serializable {
    private String  id;
    private String type;
    private float amount;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;
    private String category;
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", category='" + category + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
