
<template>
  <div class="box">
    <el-aside width="200px" class="aside">
      <Aside />
    </el-aside>
    <el-main>
      <Topbar title="基金记录管理" style="color: white" />
      <Form
        :ifVisible="this.controlForm"
        @dialog-form-visible-changed="handleDialogFormVisibleChanged"
        :title="'bill'"
      />

      <div class="bill">
        <div style="font-size: small; margin-bottom: 0.5rem">下午好,</div>
        <div style="font-weight: bold">
       我自横刀向天笑
        </div>
        <div class="count-card">
          <div class="item-container">
            <div class="item">
              <div>今日盈亏</div>
              <div
                class="amount"
                :style="{ color: data.type === 'expend' ? '' : '#ed5a65' }"
              >
                <!--:style="{ color: data.type === 'expend' ? '' : '#ed5a65' }"-->
                <span>{{ data.type === "expend" ? "-" : "+" }}</span>

                <span>{{this.profit}}</span>
                <span style="color: white">¥</span>
              </div>
              <!--<div class="money"></div>-->
            </div>
            <div class="item">
              <div>持仓盈亏</div>
              <div
                class="amount"
                :style="{ color: data.type === 'expend' ? '' : '#ed5a65' }"
              >
                <span>{{ data.type === "expend" ? "-" : "+" }}</span>
                <span>{{this.profit}}</span>
                <span style="color: white">¥</span>
              </div>
            </div>
          </div>
        </div>
        <div class="bill-container">
          <!---
          <div class="select">
            <el-button
              :class="{ active: this.sortord === 'day' }"
              @click="sortord = 'day'"
            >
              导入股票记录
            </el-button>
          </div>
        -->

          <div style="display: flex;
  justify-content: flex-end;  /* 将按钮推到容器的右侧 */">
            <input
              v-show="false"
              ref="fileRef"
              type="file"
              @change="fileChange"
            />

       
            <el-button
              @click="uploadFile">导入基金记录</el-button>
      
          </div>

          <BillContainer
            :data="this.data"
            :amountCount="this.amountCount"
            :showType="'fund'"
            id="bill"
           
          />
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
import BillContainer from "../components/ProductContainer.vue";
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
      profit:'',
      sortord: "month",
      controlForm: false,
      showDrawer: false,
      stockData:[],
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
      data: [
        //今天的账单
        {
          id: 1,
          type: "expend",
          amount: 100.32,
          date: "2023-10-01",
          category: "stock",
          remark: "Lunch",
        },
        {
          id: 2,
          type: "income",
          amount: 200.21,
          date: "2023-10-02",
          category: "stock",
        },
        {
          id: 3,
          type: "expend",
          amount: 50,
          date: "2023-10-03",
          category: "stock",
          remark: "Bus fare",
        },
       
      ],
      */

      data: [],

      idea: {
        date: "2023-11-06",
      },

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

      // monthRecord: [],
      amountCount: {
        //单日总支出总收入
        income: 200,
        expend: 109.32,
      },
    };
  },
  mounted() {
    // this.init();
    this.openLoading();
  },
  watch: {
    sortord: {
      handler(newSortord, oldSortord) {
        console.log(`sortord changed from ${oldSortord} to ${newSortord}`);
      },
      immediate: true, // This will make the watch handler run immediately on component creation.
    },
  },
  computed: {
    sum() {
      //支出收入求和
      return sumCount(this.items);
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
    uploadFile() {
      this.$refs.fileRef.dispatchEvent(new MouseEvent("click")); //弹出选择本地文件
    },
    fileChange(data) {
      console.log("嗨嗨嗨", data);
      axios.post("/batchInsertFunds", {}).then((response) => {
        console.log(response.data);
        this.openLoading();
      });
    },

    newState(data) {
      console.log("收到drawer状态", data);
      this.showDrawer = data;
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
        loading.close();
      }, 2000);
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

    init() {
      axios
        .post("/searchAllFunds") //event_id是envent_id
        .then((response) => {
          console.log("这里是/GetAllFund");
          console.log(response.data);
          this.data = response.data.data;
          //this.billData = response.data.data[0];
        });
        axios
        .post("/getFundProfit") //event_id是envent_id
        .then((response) => {
          console.log("/getFundProfi");
          console.log(response.data);
          this.profit = response.data.data;
          //this.billData = response.data.data[0];
        });
    },
    selectFile() {
      // 触发原生文件选择器
      this.$refs.fileInput.click();
    },

    // 处理原生文件选择器的change事件
    handleFileChange(event) {
      console.log("aaaaaaaaaaaaa", event);
      const fileInput = event.target;
      const files = fileInput.files;

      // 将选择的文件传递给上传组件
      this.$refs.upload.submit(files);

      // 清空文件选择器的值，以便下一次选择相同文件时触发change事件
      fileInput.value = null;
    },

    // 处理文件上传成功事件
    handleSuccess(response, file, fileList) {
      // 处理上传成功的逻辑
      console.log("上传成功", response);
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
          span:nth-child(3) {
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
