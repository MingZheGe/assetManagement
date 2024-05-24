package com.example.service;

import com.example.dao.BillEntityMapper;
import com.example.entity.Bill;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BillService {
    @Autowired
    private BillEntityMapper billEntityMapper;

    public int insertBill(Bill bill){
        int result = billEntityMapper.insertBill(bill);
        if(result!=0){
            return 3;
        }else {
            return result;
        }
    }

    public List<Bill> SearchTodayBill(Bill bill){
        List<Bill> result = billEntityMapper.SearchTodayBill(bill);

        return result;
    }

    public float getTotalAmount(){
        float result = billEntityMapper.getTotalAmount();

        return result;
    }

    public float getDayAverageBill(){
        float result = billEntityMapper.getDayAverageBill();

        return result;
    }

    public int getBillCount(){
        int result = billEntityMapper.getBillCount();

        return result;
    }

    public int deleteBill(Bill bill){
        int result = billEntityMapper.DeleteBill(bill);
        return result;
    }

    public List<Map<String, Object>> getTotalIncomeExpenseByTypeAsJson() throws JsonProcessingException {
        List<Map<String, Object>> results = billEntityMapper.getTotalIncomeExpenseByType();

        return results;
    }

    public List<Map<String, Object>> getTotalIncomeExpenseByMonth() throws JsonProcessingException {
        List<Map<String, Object>> results = billEntityMapper.getTotalIncomeExpenseByMonth();
        return results;
    }

    public List<Bill> getAllBill(){
        List<Bill> result = billEntityMapper.GetAllBill();

        return result;
    }

    public List<Bill> searchBill(Bill bill){
        List<Bill> result = billEntityMapper.searchBill(bill);
        return result;
    }


    public List<Map<String, Object>> getExpenseByCategory() throws JsonProcessingException {
        List<Map<String, Object>> results = billEntityMapper.getExpenseByCategory();
        return results;
    }


    public List<Map<String, Object>> getIncomeByType() throws JsonProcessingException {
        List<Map<String, Object>> results = billEntityMapper.getIncomeByType();
        return results;
    }


    public List<Map<String, Object>> getTotalBillByMonth() throws JsonProcessingException {
        List<Map<String, Object>> results = billEntityMapper.getTotalBillByMonth();
        return results;
    }


}
