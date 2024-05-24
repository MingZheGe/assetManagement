package com.example.entity;

import java.io.Serializable;
import java.util.Date;

public class stocks implements Serializable {
    private String stock_code;
    private String corporate_name;
    private float buying_price;
    private float stock_market_prices;
    private int holdings;
    private Date date;

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public String getCorporate_name() {
        return corporate_name;
    }

    public void setCorporate_name(String corporate_name) {
        this.corporate_name = corporate_name;
    }

    public float getBuying_price() {
        return buying_price;
    }

    public void setBuying_price(float buying_price) {
        this.buying_price = buying_price;
    }

    public float getStock_market_prices() {
        return stock_market_prices;
    }

    public void setStock_market_prices(float stock_market_prices) {
        this.stock_market_prices = stock_market_prices;
    }

    public int getHoldings() {
        return holdings;
    }

    public void setHoldings(int holdings) {
        this.holdings = holdings;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "stocks{" +
                "stock_code='" + stock_code + '\'' +
                ", corporate_name='" + corporate_name + '\'' +
                ", buying_price=" + buying_price +
                ", stock_market_prices=" + stock_market_prices +
                ", holdings=" + holdings +
                ", date=" + date +
                '}';
    }
}
