package com.example.entity;

import java.io.Serializable;
import java.util.Date;

public class funds implements Serializable {
    private String funds_code;
    private String fund_name;
    private float buying_price;
    private float fund_market_prices;
    private Date date;

    public String getFund_code() {
        return funds_code;
    }

    public void setFund_code(String fund_code) {
        this.funds_code = fund_code;
    }

    public String getFund_name() {
        return fund_name;
    }

    public void setFund_name(String fund_name) {
        this.fund_name = fund_name;
    }

    public float getBuying_price() {
        return buying_price;
    }

    public void setBuying_price(float buying_price) {
        this.buying_price = buying_price;
    }

    public float getFund_market_prices() {
        return fund_market_prices;
    }

    public void setFund_market_prices(float fund_market_prices) {
        this.fund_market_prices = fund_market_prices;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "funds{" +
                "fund_code='" + funds_code + '\'' +
                ", fund_name='" + fund_name + '\'' +
                ", buying_price=" + buying_price +
                ", fund_market_prices=" + fund_market_prices +
                ", date=" + date +
                '}';
    }
}
