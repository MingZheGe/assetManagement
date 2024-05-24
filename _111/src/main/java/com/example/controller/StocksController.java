package com.example.controller;


import com.example.bean.HttpResponseEntity;
import com.example.entity.stocks;
import com.example.service.StocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api")
public class StocksController {
    @Autowired
    private StocksService stocksService;

    @RequestMapping("/GetAllStocks")
    public HttpResponseEntity GetAllStocks(){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            List<stocks> hasUser = stocksService.GetAllStocks();
            if (hasUser.isEmpty()) {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(null);
                httpResponseEntity.setMessage("fail");
            } else {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(hasUser);
                httpResponseEntity.setMessage("success");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            httpResponseEntity.setCode("500"); // Internal server error
            httpResponseEntity.setMessage("An error occurred");
        }
        return httpResponseEntity;

    }

    @RequestMapping("/insertStocks")
    public HttpResponseEntity insertStocks(@RequestBody stocks stocks){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            int result = stocksService.insertStocks(stocks);
            if ( result!=0 ) {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("success");
            } else {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("fail");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            httpResponseEntity.setCode("500"); // Internal server error
            httpResponseEntity.setMessage("An error occurred");
        }
        return httpResponseEntity;


    }

    @RequestMapping("/SearchStocks")
    public HttpResponseEntity SearchStocks(@RequestBody stocks stocks){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            List<stocks> result = stocksService.SearchStocks(stocks);
            if (result != null ) {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("success");
            } else {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("fail");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            httpResponseEntity.setCode("500"); // Internal server error
            httpResponseEntity.setMessage("An error occurred");
        }
        return httpResponseEntity;
    }

    @RequestMapping("/getTotalStockValue")
    public HttpResponseEntity getTotalStockValue(){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            double result = stocksService.getTotalStockValue();
            if ( result!=0 ) {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("success");
            } else {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("fail");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            httpResponseEntity.setCode("500"); // Internal server error
            httpResponseEntity.setMessage("An error occurred");
        }
        return httpResponseEntity;
    }

    @RequestMapping("/import")
    public ResponseEntity<String> importStocks() {
        try {
            // 从 classpath:resources/json/stocks.json 读取文件
            Resource resource = new ClassPathResource("data/test_import.json");
            InputStream inputStream = resource.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            // 读取文件内容
            StringBuilder jsonString = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }

            // 调用 StockService 中的方法将 JSON 字符串转换为 Stock 对象并插入数据库
            stocksService.importStocks(jsonString.toString());

            return ResponseEntity.ok("Stocks imported successfully");
        } catch (IOException e) {
            e.printStackTrace();
            // 处理异常
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error importing stocks");
        }
    }


    @RequestMapping("/getStocksProfit")
    public HttpResponseEntity getStocksProfit(){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            double result = stocksService.getStocksProfit();
            if ( result!=0 ) {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("success");
            } else {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("fail");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            httpResponseEntity.setCode("500"); // Internal server error
            httpResponseEntity.setMessage("An error occurred");
        }
        return httpResponseEntity;
    }





}
