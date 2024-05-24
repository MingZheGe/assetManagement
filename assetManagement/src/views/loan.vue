
<template>
  <div class="box">
    <el-aside width="200px" class="aside">
      <Aside :menuItem="1"></Aside>
    </el-aside>
    <el-main>
      <Topbar title="贷款记录管理" style="color: white" />
      <Form
        :ifVisible="this.controlForm"
        @dialog-form-visible-changed="handleDialogFormVisibleChanged"
        @submitForm="reload"
        :title="'loan'"
        :id="this.arrayLength"
      />
      <Navbar @showForm="showForm2" />
      <drawer
        :ifshowDrawer="this.showDrawer"
        :type="'loan'"
        :loanData="this.loanData"
        @drawerFalse="newState"
        @deleteLoan="deleteLoan"
      />
      <div class="bill">
        <div style="font-size: small; margin-bottom: 0.5rem">对象已经两天没回我消息了</div>
        <div style="font-weight: bold">
          女孩，为了你掏空口袋
        </div>
        <div class="count-card">
          <div class="item-container">
            <div class="item">
              <div>下次还款日期</div>
              <div style="font-size: 1.25rem">
                <span>2023年11月31日</span>
              </div>
            </div>
            <div class="item">
              <div>下次还款金额</div>
              <div class="money amount">
                <span>{{ expend[0] }}</span>
                <span>.{{ expend[1] }}</span>
              </div>
            </div>
          </div>
        </div>
        <div class="bill-container">
          <BillContainer :data="this.loanRecord" @getBill="check" :amountCount="this.sum" id="loan"/>
        </div>
      </div>
    </el-main>
  </div>
</template>


<script>
import Aside from "../components/Aside2.vue";
import Topbar from "../components/Topbar.vue";
import Navbar from "../components/Navbar.vue";
import Form from "../components/loanForm.vue";
import drawer from "../components/drawer.vue";
import BillContainer from "../components/BillContainer2.vue";
import { amountToArray } from "../compute/amountFormat";
import { sumCountLoan } from "../compute/amountFormat";
import axios  from "axios";

export default {
  components: {
    Aside,
    Topbar,
    Navbar,
    BillContainer,
    Form,
    drawer,
  },
  data() {
    return {
      showDrawer: false,
      controlForm: false,
      sortord: "day",
      items: [
        {
          id: 1,
          type: "expend",
          amount: 100.32,
          date: "2023-10-01",
          category: "food",
          remark: "Lunch",
        },
        {
          id: 2,
          type: "income",
          amount: 200.21,
          date: "2023-10-02",
          category: "food",
        },
        {
          id: 3,
          type: "expend",
          amount: 50,
          date: "2023-10-03",
          category: "transport",
          remark: "Bus fare",
        },
        {
          id: 4,
          type: "expend",
          amount: 100.32,
          date: "2023-10-01",
          category: "food",
          remark: "Lunch",
        },
        {
          id: 5,
          type: "income",
          amount: 200.21,
          date: "2023-10-02",
          category: "food",
        },
        {
          id: 6,
          type: "expend",
          amount: 50,
          date: "2023-10-03",
          category: "transport",
          remark: "Bus fare",
        },
      ],
      data: [
        //今天的账单
        {
          id: 1,
          type: "expend",
          amount: 100.32,
          date: "2023-10-01",
          category: "food",
          remark: "Lunch",
        },
        {
          id: 2,
          type: "income",
          amount: 200.21,
          date: "2023-10-02",
          category: "food",
        },
        {
          id: 3,
          type: "expend",
          amount: 50,
          date: "2023-10-03",
          category: "transport",
          remark: "Bus fare",
        },
        {
          id: 1,
          type: "expend",
          amount: 100.32,
          date: "2023-10-01",
          category: "food",
          remark: "Lunch",
        },
        {
          id: 2,
          type: "income",
          amount: 200.21,
          date: "2023-10-02",
          category: "food",
        },
        {
          id: 3,
          type: "expend",
          amount: 50,
          date: "2023-10-03",
          category: "transport",
          remark: "Bus fare",
        },
        {
          id: 1,
          type: "expend",
          amount: 100.32,
          date: "2023-10-01",
          category: "food",
          remark: "Lunch",
        },
        {
          id: 2,
          type: "income",
          amount: 200.21,
          date: "2023-10-02",
          category: "food",
        },
        {
          id: 3,
          type: "expend",
          amount: 50,
          date: "2023-10-03",
          category: "transport",
          remark: "Bus fare",
        },
      ],
      /*
      loanRecord: [
        {
          id: "1",
          type: "expend",
          annualInterestRate:0.0345,
          loanAmount:30000,
          loanType:"个人住房性贷款",
          loanTerm:"1年",
          monthPayment:"986.52",
          repaymentMethod:"等额本息还款",
          totalInterestPaid:"13436",
          totalRepaymentAmount:"36315",
          loanDate: "2023-10-01",
          category: "houseLoan",
          remark: "房贷",

        },
       
        // 其他月份数据
      ],
      */
     loanRecord:[],
     loanData:[],//详细数据
     arrayLength:'',
     
      amountCount: {
        //单日总支出总收入
        income: 200,
        expend: 109.32,
      },
    };
  },
  mounted() {
    console.log("挂载sum结果")
    console.log(this.sum)
    this.openLoading();
  },
  watch: {
    sortord: {
      handler(newSortord, oldSortord) {
        console.log(`sortord changed from ${oldSortord} to ${newSortord}`);
      },
      immediate: true, // This will make the watch handler run immediately on component creation.
    },
    loanRecord(newValue,oldValue){
      console.log("变了")
      console.log(newValue)

    }
  },
  computed: {
    sum() {
      //支出收入求和
      console.log("计算的",this.loanRecord)
      console.log(sumCountLoan(this.loanRecord))
      return sumCountLoan(this.loanRecord);
    },
    expend() {
      //得到整数小数
      return amountToArray(this.sum.expend);
    },
  },
  methods: {

    init(){
      axios.post('/ShowAllLoan')//event_id是envent_id
  .then(response => {
    console.log(response.data)
    this.loanRecord=response.data.data
    this.arrayLength = this.loanRecord.length+1;

    //this.$router.push('/bill')

  })

    },
    openLoading() {
      const loading = this.$loading({
        target: "#loan",
        lock: false,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgb(255, 255, 255)",
      });
      setTimeout(() => {
        this.init();
        loading.close();
      }, 500);
    },
    reload(data) {
      console.log("重新加载", data);
      this.openLoading();
    },

    deleteLoan(data){
      console.log("收到了删除")
      this.openLoading();

    },

    showForm2(data) {
      this.controlForm = data;
      console.log("现在点击加号传给子组件值为", data);
    },

    handleDialogFormVisibleChanged(newValue) {
      console.log("收到了子组件值", newValue);
      // 在这里处理 dialogFormVisible 的变化
      this.controlForm = newValue;
    },
    newState(data) {
      console.log("收到drawer状态", data);
      this.showDrawer = data;
    },
    check(data) {
      this.showDrawer = true;
      console.log("父组件收到", data);
      console.log(this.showDrawer);
      axios
        .post("/searchLoan", data) //event_id是envent_id
        .then((response) => {
          console.log(response.data);
          this.loanData = response.data.data[0];
          this.loanData.monthPayment= "986.52"
          this.loanData.totalInterestPaid = "13436"
          this.loanData.totalRepaymentAmount = "36315"
        });
    },
  },
};
</script>
  <!--
  <style lang="less">
  .box{
    background: linear-gradient(#429691,#2A7C76);
    background-size: 100% 15rem;
    background-repeat: no-repeat;
    padding-bottom: 2rem;
    box-sizing: border-box;
    display: flex;
  }
  .bill {
    padding: 1rem;
    color:#fff;
  
    .count-card{
      background-color: #429691;
      padding: 1rem;
      margin-top: 2rem;
      border-radius: .4rem;
      font-size: 14px;
      box-shadow: 0 2px 10px #429691;
      border: 1px solid rgb(107, 151, 134);
      .item-container{
        display: flex;
        justify-content: space-between;
        color: gray;
        padding: 1rem;
        padding-bottom: 0;
        .item{
          .amount{
            color: #fff;
            font-size: 14px;
            span:nth-child(2){
              font-size: 14px;
            }
          }
        }
      }
    }
    .bill-container{
      margin-top: 2rem;
      .select{
        color: gray-1;
        font-size: 14px;
        margin: .6rem 0;
        color: gray;
        button{
          width: 3rem;
        }
        .active{
          font-weight: 600;
          font-size: 14px;
          color: 14px;
        }
      }
    }
  }

  .aside{
    background-color: #545c64;
  }
  </style>
-->

<style lang="less" scoped>
@import "../styles/common.less";
@import "../styles/common.css";
@import "../styles/main.css";
.box {
  //background: linear-gradient(30deg,#baebe7, #2a7c76);
  background: linear-gradient(30deg, #baebe7, #14c4b8);
  //background:url("../assets/image/sbpk.jpg");
  background-size: 100% 15rem;
  background-repeat: no-repeat;
  padding-bottom: 2rem;
  box-sizing: border-box;
  display: flex;
}
.bill {
  padding: 1rem;
  color: #fff;

  .count-card {
    margin-bottom: 20px;
    //background: linear-gradient(30deg,#baebe7, #2a7c76);
    background-color: #50e2d6;
    padding: 1rem;
    margin-top: 2rem;
    border-radius: 0.4rem;
    font-size: @font-size-sm;
    box-shadow: 0 2px 10px #429691;
    border: 1px solid rgb(107, 151, 134);
    .item-container {
      display: flex;
      justify-content: space-between;
      color: #fff;
      padding: 1rem;
      padding-bottom: 0;
      .item {
        .amount {
          color: #fff;
          font-size: @font-size-lg;
          span:nth-child(2) {
            font-size: @font-size-sm;
          }
        }
      }
    }
  }
  .bill-container {
    margin-top: 50px;
    background-color: #545c64;
    background: none;
    margin-top: 0.5rem;
    .select {
      color: gray-1;
      font-size: @font-size-sm;
      margin: 0.6rem 0; //瞎tm晃不会改了艹
      color: gray;

      button {
        width: 3rem;
        border: none; /* 去除边框 */
        background: transparent; /* 去除背景色 */
      }
      .active {
        font-weight: 600;
        font-size: @font-size-base;
        color: @gray-10;
        margin: 0.6rem 0;
      }
    }
  }
}
</style>
