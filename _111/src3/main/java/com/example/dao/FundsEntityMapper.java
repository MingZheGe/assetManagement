package com.example.dao;

import com.example.entity.funds;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface FundsEntityMapper {
    //展示所有基金
    List<funds> searchAllFunds();

    //添加基金
    int addFunds(funds funds);

    //删除基金
    int deleteFunds(funds funds);

    //查找基金
    List<funds> searchFunds(funds funds);

    //基金共多少钱
    double getTotalBuyingPriceForFunds();

    //导入基金
    void batchInsertFunds(List<funds> stocks);

}
