package com.example.service;

import com.example.dao.FundsEntityMapper;
import com.example.entity.funds;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class FundsService {
    @Autowired
    private FundsEntityMapper fundsEntityMapper;

    public List<funds> searchAllFunds(){
        List<funds> result = fundsEntityMapper.searchAllFunds();
        return result;
    }

    public int addFunds(funds funds){
        int result = fundsEntityMapper.addFunds(funds);
        return  result;
    }

    public int deleteFunds(funds funds){
        int result = fundsEntityMapper.deleteFunds(funds);
        return  result;
    }

    public List<funds> searchFunds(funds funds){
        List<funds> result = fundsEntityMapper.searchFunds(funds);
        return  result;
    }



    public double getTotalBuyingPriceForFunds(){
        double result = fundsEntityMapper.getTotalBuyingPriceForFunds();
        return  result;
    }



    public void batchInsertFunds(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            // 将 JSON 字符串转换为 Stock 对象数组
            funds[] stocks = objectMapper.readValue(jsonString, funds[].class);

            // 调用 StockMapper 中的方法将数据插入数据库
            fundsEntityMapper.batchInsertFunds(Arrays.asList(stocks));
        } catch (IOException e) {
            e.printStackTrace();
            // 处理异常
        }
    }


    public double getFundProfit(){
        double result = fundsEntityMapper.getFundProfit();
        return  result;
    }


}
