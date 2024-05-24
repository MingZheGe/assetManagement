package com.example.dao;

import com.example.entity.loan;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface LoanEntityMapper {
    //展示所有贷款
    List<loan> ShowAllLoan();

    //删除贷款
    int deleteLoan(loan loan);

    //增加贷款
    int addLoan(loan loan);

    //查找贷款
    List<loan> searchLoan(loan loan);

}
