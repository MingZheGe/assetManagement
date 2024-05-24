package com.example.controller;

import com.example.bean.HttpResponseEntity;
import com.example.entity.funds;
import com.example.service.FundsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api")
public class fundsController {
    @Autowired
    private FundsService fundsService;

    @RequestMapping("/searchAllFunds")
    public HttpResponseEntity searchAllFunds(){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            List<funds> result = fundsService.searchAllFunds();
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

    @RequestMapping("/addFunds")
    public HttpResponseEntity addFunds(@RequestBody funds funds){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            int result = fundsService.addFunds(funds);
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

    @RequestMapping("/deleteFunds")
    public HttpResponseEntity deleteFunds(@RequestBody funds funds){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            int result = fundsService.deleteFunds(funds);
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

    @RequestMapping("/searchFunds")
    public HttpResponseEntity searchFunds(@RequestBody funds funds){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            List<funds> hasUser = fundsService.searchFunds(funds);
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


    @RequestMapping("/getTotalBuyingPriceForFunds")
    public HttpResponseEntity getTotalBuyingPriceForFunds(){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            double result = fundsService.getTotalBuyingPriceForFunds();
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