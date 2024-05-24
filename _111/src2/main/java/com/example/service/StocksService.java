package com.example.service;

import com.example.dao.StocksEntityMapper;
import com.example.entity.stocks;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class StocksService {
    @Autowired
    private StocksEntityMapper stocksEntityMapper;

    public List<stocks> GetAllStocks(){
        List<stocks> result = stocksEntityMapper.GetAllStocks();
        return result;
    }

    public int insertStocks(stocks stocks){
        int result = stocksEntityMapper.insertStock(stocks);
        return result;
    }

    public List<stocks> SearchStocks(stocks stocks){
        List<stocks> result = stocksEntityMapper.SearchStocks(stocks);
        return result;
    }

    public int DeleteStocks(stocks stocks){
        int result = stocksEntityMapper.DeleteStocks(stocks);

        return result;

    }


    public double getTotalStockValue(){
        double result = stocksEntityMapper.getTotalStockValue();

        return result;

    }

    public void importStocks(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            // 将 JSON 字符串转换为 Stock 对象数组
            stocks[] stocks = objectMapper.readValue(jsonString, stocks[].class);

            // 调用 StockMapper 中的方法将数据插入数据库
            stocksEntityMapper.batchInsertStocks(Arrays.asList(stocks));
        } catch (IOException e) {
            e.printStackTrace();
            // 处理异常
        }
    }
}
