package com.example.service;

import com.example.dao.LoanEntityMapper;
import com.example.entity.loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {
    @Autowired
    private LoanEntityMapper loanEntityMapper;

    public List<loan> ShowAllLoan(){
        List<loan> result = loanEntityMapper.ShowAllLoan();
        return  result;
    }

    public int deleteLoan(loan loan){
        int result = loanEntityMapper.deleteLoan(loan);
        return result;
    }
    public List<loan> searchLoan(loan loan){
        List<loan> result = loanEntityMapper.searchLoan(loan);
        return result;
    }

    public int addLoan(loan loan){
        int result = loanEntityMapper.addLoan(loan);
        return result;
    }

}
