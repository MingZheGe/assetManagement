package com.example.service;

import com.example.dao.FundsEntityMapper;
import com.example.entity.funds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
