package com.example.dao;

import com.example.entity.stocks;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface StocksEntityMapper {
    //展示所有股票
    List<stocks> GetAllStocks();

    //插入股票
    int insertStock(stocks stocks);

    //查找股票
    List<stocks> SearchStocks(stocks stocks);

    //删除股票
    int DeleteStocks(stocks stocks);

    //计算股票价值
    double getTotalStockValue();

    //导入股票
    void batchInsertStocks(List<stocks> stocks);

    //计算股票总收益
    double getStocksProfit();



}
