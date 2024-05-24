
<template>
  <div class="box">
    <el-aside width="200px" class="aside">
      <Aside :menuItem="1"></Aside>
    </el-aside>
    <el-main>
      <Topbar title="账单记录管理" style="color: white" />
      <Form
        :ifVisible="this.controlForm"
        @dialog-form-visible-changed="handleDialogFormVisibleChanged"
        :title="'bill'"
        @submitForm="reload"
        :id="this.arrayLength"
      />
      <Navbar @showForm="showForm2" />
      <drawer
        :ifshowDrawer="this.showDrawer"
        :type="'bill'"
        @drawerFalse="newState"
        :billData="this.billData"
        @deleteBill="deleteBill"
      />
      <div class="bill">
        <div style="font-size: small; margin-bottom: 0.5rem">
          笑脏笑破不笑补
        </div>
        <div style="font-weight: bold">节约好比燕衔泥，浪费好比河决堤。</div>
        <div class="count-card">
          <div class="item-container">
            <div class="item">
              <div>今日总收入</div>
              <div class="money amount">
                <span>{{ income[0] }}</span>
                <span>.{{ income[1] }}</span>
              </div>
            </div>
            <div class="item">
              <div>今日总支出</div>
              <div class="money amount">
                <span>{{ expend[0] }}</span>
                <span>.{{ expend[1] }}</span>
              </div>
            </div>
          </div>
        </div>
        <div class="bill-container">
          <div class="select">
            <el-button
              :class="{ active: this.sortord === 'day' }"
              @click="sortord = 'day'"
            >
              最新
            </el-button>
            <el-button
              :class="{ active: this.sortord === 'month' }"
              @click="sortord = 'month'"
            >
              按月份
            </el-button>
          </div>

          <template>
            <BillContainer
              v-if="sortord === 'day'"
              :data="this.data"
              :amountCount="this.sum"
              id="bill"
              @getBill="check"
            />

            <template v-else>
              <div style="height: 350px; overflow-y: auto">
                <BillContainer
                  v-for="(item, index) in monthRecord"
                  :key="index"
                  :month="item.month"
                  :data="item.bills"
                  :amountCount="item.amountCount"
                  id="bill"
                  @getBill="check"
                />
              </div>
            </template>
          </template>
        </div>
      </div>
    </el-main>
  </div>
</template>


<script>
import Aside from "../components/Aside2.vue";
import Topbar from "../components/Topbar.vue";
import Navbar from "../components/Navbar.vue";
import Form from "../components/form.vue";
import drawer from "../components/drawer.vue";
import BillContainer from "../components/BillContainer.vue";
import { amountToArray } from "../compute/amountFormat";
import { sumCount } from "../compute/amountFormat";
import axios from "axios";
import Drawer from "../components/drawer.vue";

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
      sum2: "",
      sortord: "day",
      billData: "",
      controlForm: false,
      showDrawer: false,
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
      /*
      data: [//今天的账单
        { id: 1,type: 'expend', amount: 100.32, date: '2023-10-01', category: 'food', remark: 'Lunch' },
        { id: 2, type: 'income', amount: 200.21, date: '2023-10-02', category: 'food' },
        { id: 3, type: 'expend', amount: 50, date: '2023-10-03', category: 'transport', remark: 'Bus fare' },
        { id: 1, type: 'expend', amount: 100.32, date: '2023-10-01', category: 'food', remark: 'Lunch' },
        { id: 2, type: 'income', amount: 200.21, date: '2023-10-02', category: 'food' },
        { id: 3, type: 'expend', amount: 50, date: '2023-10-03', category: 'transport', remark: 'Bus fare' },
        { id: 1, type: 'expend', amount: 100.32, date: '2023-10-01', category: 'food', remark: 'Lunch' },
        { id: 2, type: 'income', amount: 200.21, date: '2023-10-02', category: 'food' },
        { id: 3, type: 'expend', amount: 50, date: '2023-10-03', category: 'transport', remark: 'Bus fare' },
        ],
        */

      data: [],
      arrayLength: "",

      idea: {
        date: "2023-11-13",
      },
      /*
      monthRecord: [
        {
          month: "一",
          bills: [
            {
              id: 3,
              type: "expend",
              amount: 50,
              date: "2023-10-03",
              category: "transport",
              remark: "Bus fare",
            },
            // 其他账单
          ],
          amountCount: {
            //按月总支出总收入
            income: 200,
            expend: 109.32,
          },
        },
        {
          month: "二",
          bills: [
            {
              id: 2,
              type: "income",
              amount: 200.21,
              date: "2023-10-02",
              category: "food",
            },
            // 其他账单
          ],
          amountCount: {
            //按月总支出总收入
            income: 200,
            expend: 109.32,
          },
        },
        // 其他月份数据
      ],
      */

      monthRecord: [],
      amountCount: {
        //单日总支出总收入
        income: 200,
        expend: 109.32,
      },
    };
  },
  mounted() {
    this.openLoading();

    axios
      .post("/getTotalIncomeExpenseByMonth") //event_id是envent_id
      .then((response) => {
        console.log("这里是/getTotalIncomeExpenseByMonth");
        console.log(response.data);
        //this.billData = response.data.data[0];
      });
  },
  watch: {
    sortord: {
      handler(newSortord, oldSortord) {
        console.log(`sortord changed from ${oldSortord} to ${newSortord}`);
      },
      immediate: true, // This will make the watch handler run immediately on component creation.
    },
    data(newValue, oldValue) {
      //this.openLoading();
    },
  },
  computed: {
    sum() {
      //支出收入求和
      if (this.data) {
        console.log("支出收入求和");
        console.log(this.data);
        console.log(sumCount(this.data));
        return sumCount(this.data);
      }
    },
    income() {
      //得到整数小数
      return amountToArray(this.sum.income);
    },
    expend() {
      //得到整数小数
      return amountToArray(this.sum.expend);
    },
  },
  methods: {
    sum3() {
      let expend = 0;
      let income = 0;
      if (this.data) {
        this.data.forEach((item) => {
          if (item.type === "expend") expend += item.amount;
          else income += item.amount;
        });
        return { expend, income };
      }
    },
    newState(data) {
      console.log("收到drawer状态", data);
      this.showDrawer = data;
    },
    reload(data) {
      console.log("重新加载", data);
      this.openLoading();
    },

    openLoading() {
      const loading = this.$loading({
        target: "#bill",
        lock: false,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgb(255, 255, 255)",
      });
      setTimeout(() => {
        this.init();
        this.sum2 = this.sum3();
        loading.close();
      }, 500);
    },

    showForm2(data) {
      this.controlForm = data;
      console.log("现在点击加号传给子组件值为", data);
    },
    check(data) {
      (this.showDrawer = true), console.log("父组件收到", data);
      axios
        .post("/SearchBill", data) //event_id是envent_id
        .then((response) => {
          console.log(response.data);
          this.billData = response.data.data[0];
        });
    },
    deleteBill(data) {
      console.log("收到了删除");
      this.openLoading();
    },

    handleDialogFormVisibleChanged(newValue) {
      console.log("收到了子组件值", newValue);
      // 在这里处理 dialogFormVisible 的变化
      this.controlForm = newValue;
    },

    init() {
      axios.post("/SearchTodayBill", this.idea).then((response) => {
        console.log("这里是/SearchTodayBill");
        console.log(response.data);
        this.data = response.data.data;
        //this.$router.push('/bill')
        this.arrayLength = this.data.length + 1;
      });

      axios
        .post("/GetAllBill") //event_id是envent_id
        .then((response) => {
          console.log("这里是getAliBill");
          console.log(response.data);
          // this.monthRecord = response.data;
          //this.$router.push('/bill')
        });

      axios
        .post("/getTotalBillByMonth") //event_id是envent_id
        .then((response) => {
          console.log("这里是getTotalBillByMonth")
           console.log(response.data);
           console.log(response.data.data)
          const arr = response.data.data;
          const records = {};
          // 遍历原始数据，按月份分组
          arr.forEach((bill) => {
            const month = new Date(bill.date).getMonth() + 1; // 月份是从 0 开始的，所以要加 1
            console.log("测试");
            console.log(bill.amount);

            if (!records[month]) {
              console.log("测试1");
              console.log(month);
              records[month] = {
                month: `${month}月`,
                bills: [],
                amountCount: {
                  income: 0,
                  expend: 0,
                },
              };
            }

            records[month].bills.push(bill);
            if (bill.type === "income") {
              records[month].amountCount.income += bill.amount;
            } else if (bill.type === "expend") {
              records[month].amountCount.expend += bill.amount;
            }
          });
          /*
         axios
        .post("/getTotalIncomeExpenseByMonth") //event_id是envent_id
        .then((response) => {
          console.log("这里是getAliBill")
          console.log(response.data);
          const arr2 = response.data.data
        
          records.forEach((bill) => {
});






        });
      */
          console.log("这里是getTotalBillByMonth");
          console.log(records);

          this.monthRecord = records;
          console.log("this.monthRecord");
          console.log(this.monthRecord);

          //this.$router.push('/bill')
        });
    },
  },
};
</script>


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
