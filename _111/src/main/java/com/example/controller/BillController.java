package com.example.controller;


import com.example.bean.HttpResponseEntity;
import com.example.entity.Bill;
import com.example.service.BillService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api")
public class BillController {
    @Autowired
    private BillService billService;

    @RequestMapping("/insert")
    public HttpResponseEntity insertBill(@RequestBody Bill bill) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            int result = billService.insertBill(bill);
            if (result !=0 ) {
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

    @RequestMapping("/SearchTodayBill")
    public HttpResponseEntity SearchTodayBill(@RequestBody Bill bill) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            List<Bill> result = billService.SearchTodayBill(bill);
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

    @RequestMapping("/SearchBill")
    public HttpResponseEntity SearchBill(@RequestBody Bill bill) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            List<Bill> result = billService.searchBill(bill);
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

    @RequestMapping("/getTotalAmount")
    public HttpResponseEntity getTotalAmount(){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            float result = billService.getTotalAmount();
            if (result !=0 ) {
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

    @RequestMapping("/getDayAverageBill")
    public HttpResponseEntity getDayAverageBill(){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            float result = billService.getDayAverageBill();
            if (result !=0 ) {
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

    @RequestMapping("/getBillCount")
    public HttpResponseEntity getBillCount(){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            int result = billService.getBillCount();
            if (result !=0 ) {
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

    @RequestMapping("/deleteBill")
    public HttpResponseEntity deleteBill(@RequestBody Bill bill){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            int result = billService.deleteBill(bill);
            if (result !=0 ) {
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

    @RequestMapping("/getTotalIncomeExpenseByTypeAsJson")
    @GetMapping
    public HttpResponseEntity getTotalIncomeExpenseByTypeAsJson() {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            List<Map<String, Object>> result = billService.getTotalIncomeExpenseByTypeAsJson();
            if (result != null && !result.isEmpty()) {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("success");
            } else {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData("");
                httpResponseEntity.setMessage("fail");
            }
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            httpResponseEntity.setCode("500"); // Internal server error
            httpResponseEntity.setMessage("An error occurred");
        }
        return httpResponseEntity;
    }

    @RequestMapping("/getTotalIncomeExpenseByMonth")
    @GetMapping
    public HttpResponseEntity getTotalIncomeExpenseByMonth() {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            List<Map<String, Object>> result = billService.getTotalIncomeExpenseByMonth();
            if (result != null && !result.isEmpty()) {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("success");
            } else {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData("");
                httpResponseEntity.setMessage("fail");
            }
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            httpResponseEntity.setCode("500"); // Internal server error
            httpResponseEntity.setMessage("An error occurred");
        }
        return httpResponseEntity;
    }

    @RequestMapping("/GetAllBill")
    public HttpResponseEntity GetAllBill() {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            List<Bill> hasUser = billService.getAllBill();
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



    @RequestMapping("/getExpenseByCategory")
    @GetMapping
    public HttpResponseEntity getExpenseByCategory() {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            List<Map<String, Object>> result = billService.getExpenseByCategory();
            if (result != null && !result.isEmpty()) {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("success");
            } else {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData("");
                httpResponseEntity.setMessage("fail");
            }
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            httpResponseEntity.setCode("500"); // Internal server error
            httpResponseEntity.setMessage("An error occurred");
        }
        return httpResponseEntity;
    }


    @RequestMapping("/getIncomeByType")
    @GetMapping
    public HttpResponseEntity getIncomeByType() {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            List<Map<String, Object>> result = billService.getIncomeByType();
            if (result != null && !result.isEmpty()) {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("success");
            } else {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData("");
                httpResponseEntity.setMessage("fail");
            }
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            httpResponseEntity.setCode("500"); // Internal server error
            httpResponseEntity.setMessage("An error occurred");
        }
        return httpResponseEntity;
    }


    @RequestMapping("/getTotalBillByMonth")
    @GetMapping
    public HttpResponseEntity getTotalBillByMonth() {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            List<Map<String, Object>> result = billService.getTotalBillByMonth();
            if (result != null && !result.isEmpty()) {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("success");
            } else {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData("");
                httpResponseEntity.setMessage("fail");
            }
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            httpResponseEntity.setCode("500"); // Internal server error
            httpResponseEntity.setMessage("An error occurred");
        }
        return httpResponseEntity;
    }

}
