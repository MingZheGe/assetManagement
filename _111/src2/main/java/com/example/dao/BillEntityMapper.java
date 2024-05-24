package com.example.dao;


import com.example.entity.Bill;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface BillEntityMapper {
    //添加账单
    int insertBill(Bill bill);

    //查找今天的账单
    List<Bill> SearchTodayBill(Bill bill);

    //计算所有订单的总收入支出
    float getTotalAmount();

    //日均收入
    float getDayAverageBill();

    //记账笔数
    int getBillCount();

    //按照不同类型计算总收支
    List<Map<String, Object>> getTotalIncomeExpenseByType();

    //按照不同月份计算总收支
    List<Map<String, Object>> getTotalIncomeExpenseByMonth();

    //查找所有账单
    List<Bill> GetAllBill();

    //删除账单
    int DeleteBill(Bill bill);

    //查找账单
    List<Bill> searchBill(Bill bill);

       //按照类型返回支出占比
    List<Map<String, Object>> getExpenseByCategory();


    //按照类型返回收入占比
    List<Map<String, Object>> getIncomeByType();



}
