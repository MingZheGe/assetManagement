package com.example;

import com.example.dao.*;
import com.example.entity.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

@SpringBootTest
class DemoApplicationTest {
//    @Test
//    void contextLoads() {

    //    }
    Logger log = Logger.getLogger(DemoApplicationTest.class);
    //@Test
    public void login() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        //调用userMapper的方法
        User user = new User();
        List<User> list = userEntityMapper.login(user);
        if(CollectionUtils.isEmpty(list)){
            System.out.println("xixixi");
            log.info(">>登录方法测试成功");
            // 记录error级别的信息
        }else{
            System.out.println(list);
            System.out.println("hahaha");
            // 记录info级别的信息
            log.info(">>登录方法测试成功");
        }
    }

    //@Test
    public void erollUserInfo() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        //调用userMapper的方法
        User user = new User();
        user.setUsername("admin111");
        user.setPassword("1232222");
        int list = userEntityMapper.eroll(user);
        if(list == 0){
            // 记录error级别的信息
        }else{
            System.out.println(list);
            // 记录info级别的信息
            log.info(">>用户注册测试成功");
        }
    }

    @Test
    public void insertBill() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        BillEntityMapper billEntityMapper = sqlSession.getMapper(BillEntityMapper.class);
        //调用billMapper的方法
        Bill bill = new Bill();
        bill.setType("income");
        bill.setAmount(65);
        String dateString = "2023-11-20";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(dateString);

        bill.setDate(date);
        bill.setCategory("life");
        bill.setRemark("apple");
        bill.setId("56");

        int i = billEntityMapper.insertBill(bill);
        if(i==0){
            // 记录error级别的信息
        }else{
            System.out.println(i);
            // 记录info级别的信息
            log.info(">>账单插入测试成功");
        }
    }

    @Test
    public void SearchTodayBill() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        BillEntityMapper billEntityMapper = sqlSession.getMapper(BillEntityMapper.class);
        Bill bill = new Bill();
        String dateString = "2023-11-20";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(dateString);        bill.setDate(date);

        List<Bill> list= billEntityMapper.SearchTodayBill(bill);
        if(CollectionUtils.isEmpty(list)){
            // 记录error级别的信息
        }else{
            System.out.println(list);
            // 记录info级别的信息
            log.info(">>查找今日账单方法测试成功");
        }
    }
    @Test
    public void getTotalAmount() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BillEntityMapper billEntityMapper = sqlSession.getMapper(BillEntityMapper.class);
        Bill bill = new Bill();
        float result = billEntityMapper.getTotalAmount();

        if(result==0){
            // 记录error级别的信息
        }else{
            System.out.println(resource);
            // 记录info级别的信息
            log.info("账单总收益测试成功");
        }
    }

    @Test
    public void getDayAverageBill() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BillEntityMapper billEntityMapper = sqlSession.getMapper(BillEntityMapper.class);
        Bill bill =new Bill();
        float result = billEntityMapper.getDayAverageBill();
        if(result == 0){
            // 记录error级别的信息
        }else{
            System.out.println(result);
            // 记录info级别的信息
            log.info(">>账单日均收益测试成功");
        }
    }

    @Test
    public void getBillCount() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BillEntityMapper billEntityMapper = sqlSession.getMapper(BillEntityMapper.class);
        int i = billEntityMapper.getBillCount();
        if(i < 0){
            // 记录error级别的信息
        }else{
            System.out.println(i);
            // 记录info级别的信息
            log.info(">>计算账单数量测试成功");
        }
    }

    @Test
    public void deleteBill() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BillEntityMapper billEntityMapper = sqlSession.getMapper(BillEntityMapper.class);
        Bill bill =new Bill();
        bill.setId("1");
        int i = billEntityMapper.DeleteBill(bill);
        if(i == 0){
            // 记录error级别的信息
        }else{
            System.out.println(i);
            // 记录info级别的信息
            log.info(">>删除账单测试成功");
        }
    }

    @Test
    public void getAllBill() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BillEntityMapper billEntityMapper = sqlSession.getMapper(BillEntityMapper.class);
        List<Bill> result = billEntityMapper.GetAllBill();
        if(CollectionUtils.isEmpty(result)){
            // 记录error级别的信息
        }else{
            System.out.println(resource);
            // 记录info级别的信息
            log.info(">>展示账单测试成功");
        }
    }

    @Test
    public void searchBill() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BillEntityMapper billEntityMapper = sqlSession.getMapper(BillEntityMapper.class);
        Bill bill = new Bill();
        bill.setId("3");
        List<Bill> result = billEntityMapper.searchBill(bill);
        if(CollectionUtils.isEmpty(result)){
            // 记录error级别的信息
        }else{
            System.out.println(resource);
            // 记录info级别的信息
            log.info(">>查找账单测试成功");
        }
    }

    @Test
    public void GetAllStocks() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StocksEntityMapper stocksEntityMapper = sqlSession.getMapper(StocksEntityMapper.class);
        List<stocks> result = stocksEntityMapper.GetAllStocks();
        if(CollectionUtils.isEmpty(result)){
            // 记录error级别的信息
        }else{
            System.out.println(resource);
            // 记录info级别的信息
            log.info(">>查找所有股票测试成功");
        }
    }

    @Test
    public void insertStock() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        StocksEntityMapper stocksEntityMapper = sqlSession.getMapper(StocksEntityMapper.class);

        stocks stocks = new stocks();
        stocks.setStock_code("21131");
        stocks.setCorporate_name("绿色能源");
        stocks.setBuying_price(12);
        stocks.setStock_market_prices(14);
        stocks.setHoldings(199);
        String dateString = "2023-11-20";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(dateString);
        stocks.setDate(date);

        int i = stocksEntityMapper.insertStock(stocks);
        if(i==0){
            // 记录error级别的信息
        }else{
            System.out.println(i);
            // 记录info级别的信息
            log.info(">>股票插入测试成功");
        }
    }


    @Test
    public void SearchStocks() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        StocksEntityMapper stocksEntityMapper = sqlSession.getMapper(StocksEntityMapper.class);

        stocks stocks = new stocks();
        stocks.setStock_code("21131");

        List<stocks> result = stocksEntityMapper.SearchStocks(stocks);
        if(CollectionUtils.isEmpty(result)){
            // 记录error级别的信息
        }else{
            System.out.println(resource);
            // 记录info级别的信息
            log.info(">>查找股票测试成功");
        }
    }

    @Test
    public void DeleteStocks() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        StocksEntityMapper stocksEntityMapper = sqlSession.getMapper(StocksEntityMapper.class);

        stocks stocks = new stocks();
        stocks.setStock_code("21131");

        int i = stocksEntityMapper.DeleteStocks(stocks);
        if(i==0){
            // 记录error级别的信息
        }else{
            System.out.println(i);
            // 记录info级别的信息
            log.info(">>股票删除测试成功");
        }
    }



    @Test
    public void searchAllFunds() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        FundsEntityMapper fundsEntityMapper = sqlSession.getMapper(FundsEntityMapper.class);

        List<funds> result = fundsEntityMapper.searchAllFunds();
        if(CollectionUtils.isEmpty(result)){
            // 记录error级别的信息
        }else{
            System.out.println(resource);
            // 记录info级别的信息
            log.info(">>查找所有基金测试成功");
        }
    }

    @Test
    public void addFunds() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        FundsEntityMapper fundsEntityMapper = sqlSession.getMapper(FundsEntityMapper.class);

        funds funds = new funds();
        funds.setFund_code("23112");
        funds.setFund_name("新能源");
        funds.setBuying_price(33);
        funds.setFund_market_prices(90);
        String dateString = "2023-11-20";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(dateString);
        funds.setDate(date);

        int i = fundsEntityMapper.addFunds(funds);
        if(i==0){
            // 记录error级别的信息
        }else{
            System.out.println(i);
            // 记录info级别的信息
            log.info(">>基金插入测试成功");
        }
    }

    @Test
    public void deleteFunds() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        FundsEntityMapper fundsEntityMapper = sqlSession.getMapper(FundsEntityMapper.class);

        funds funds = new funds();
        funds.setFund_code("23112");

        int i = fundsEntityMapper.deleteFunds(funds);
        if(i==0){
            // 记录error级别的信息
        }else{
            System.out.println(i);
            // 记录info级别的信息
            log.info(">>基金删除测试成功");
        }
    }

    @Test
    public void searchFunds() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        FundsEntityMapper fundsEntityMapper = sqlSession.getMapper(FundsEntityMapper.class);
        funds funds = new funds();
        funds.setFund_code("1");
        List<funds> result = fundsEntityMapper.searchFunds(funds);

        if(CollectionUtils.isEmpty(result)){
            // 记录error级别的信息
        }else{
            System.out.println(resource);
            // 记录info级别的信息
            log.info(">>查找基金测试成功");
        }
    }


    @Test
    public void ShowAllLoan() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        LoanEntityMapper loanEntityMapper = sqlSession.getMapper(LoanEntityMapper.class);

        List<loan> result = loanEntityMapper.ShowAllLoan();
        if(CollectionUtils.isEmpty(result)){
            // 记录error级别的信息
        }else{
            System.out.println(resource);
            // 记录info级别的信息
            log.info(">>查找所有贷款测试成功");
        }
    }

    @Test
    public void deleteLoan() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        LoanEntityMapper loanEntityMapper = sqlSession.getMapper(LoanEntityMapper.class);

        loan loan = new loan();
        loan.setId("1");

        int i = loanEntityMapper.deleteLoan(loan);
        if(i==0){
            // 记录error级别的信息
        }else{
            System.out.println(i);
            // 记录info级别的信息
            log.info(">>贷款删除测试成功");
        }
    }

    @Test
    public void addLoan() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        LoanEntityMapper loanEntityMapper = sqlSession.getMapper(LoanEntityMapper.class);

        loan loan = new loan();

        loan.setId("1");
        loan.setMonthPayment(400);
        loan.setTotalInterestPaid(44);
        loan.setTotalRepaymentAmount(255);
        loan.setLoanType("住房贷款");
        loan.setLoanAmount(500);
        loan.setLoanTerm("nnn");
        loan.setRepaymentMethod("...");
        loan.setAnnualInterestRate(33);
        String dateString = "2023-11-20";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(dateString);
        loan.setLoanDate(date);
        loan.setId("1");
        loan.setCategory("111");
        loan.setRemark("12312");

        int i = loanEntityMapper.addLoan(loan);
        if(i==0){
            // 记录error级别的信息
        }else{
            System.out.println(i);
            // 记录info级别的信息
            log.info(">>贷款插入测试成功");
        }
    }

    @Test
    public void searchLoan() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        LoanEntityMapper loanEntityMapper = sqlSession.getMapper(LoanEntityMapper.class);
        loan loan = new loan();
        loan.setId("1");
        List<loan> result = loanEntityMapper.searchLoan(loan);

        if(CollectionUtils.isEmpty(result)){
            // 记录error级别的信息
        }else{
            System.out.println(resource);
            // 记录info级别的信息
            log.info(">>查找贷款测试成功");
        }
    }


}
