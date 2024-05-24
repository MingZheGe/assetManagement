
<template>
  <el-drawer
    :title="descriptionTitle"
    :visible.sync="showDrawer"
    direction="rtl"
    :size="this.drawerSize"
  >
    <!--
   <el-table :data="gridData">
      <el-table-column property="date" label="日期" width="150"></el-table-column>
      <el-table-column property="name" label="姓名" width="200"></el-table-column>
      <el-table-column property="address" label="地址"></el-table-column>
    </el-table>
  -->
    <div v-if="type === 'bill'" style="margin-top: 30%; padding: 1rem">
      <el-descriptions :column="1" border>
        <el-descriptions-item label="账单类型">
          <span v-if="this.billData.type === 'expend'">支出</span>
          <span v-else-if="this.billData.type === 'income'">收入</span>
          <span v-else>其他类型</span
          ><!--{{ this.billData.type }}--></el-descriptions-item
        >

        <el-descriptions-item label="账单金额">{{
          this.billData.amount
        }}</el-descriptions-item>
        <el-descriptions-item label="类型"
          ><!--{{ this.billData.category }}-->{{
            this.category[this.billData.category].name
          }}</el-descriptions-item
        >
        <el-descriptions-item label="创建账单日期">{{
          this.billData.date
        }}</el-descriptions-item>
        <el-descriptions-item label="备注" :span="2">{{
          this.billData.remark
        }}</el-descriptions-item>
      </el-descriptions>
      <div style="display: flex; justify-content: center; margin-top: 20px">
        <el-button type="danger" round @click="deleteBill">删除</el-button>
      </div>
    </div>

    <div v-if="type === 'loan'" style="margin-top: 10%; padding: 1rem">
      <el-descriptions :column="1" border>
        <el-descriptions-item label="每月支付本息"
          >{{ this.loanData.monthPayment }}元</el-descriptions-item
        >
        <el-descriptions-item label="累计支付利息"
          >{{ this.loanData.totalInterestPaid }}元</el-descriptions-item
        >
        <el-descriptions-item label="累计还款总额"
          >{{ this.loanData.totalRepaymentAmount }}元</el-descriptions-item
        >

        <el-descriptions-item label="贷款类型">{{
          this.loanData.loanType
        }}</el-descriptions-item>
        <el-descriptions-item label="贷款金额">{{
          this.loanData.loanAmount
        }}</el-descriptions-item>
        <el-descriptions-item label="贷款期限">{{
          this.loanData.loanTerm
        }}</el-descriptions-item>
        <el-descriptions-item label="还款方式">{{
          this.loanData.repaymentMethod
        }}</el-descriptions-item>
        <el-descriptions-item label="年化利率">{{
          this.loanData.annualInterestRate
        }}</el-descriptions-item>
        <el-descriptions-item label="贷款日期">{{
          this.loanData.loanDate
        }}</el-descriptions-item>
      </el-descriptions>
      <div style="display: flex; justify-content: center; margin-top: 20px">
        <el-button type="danger" round @click="deleteLoan">删除</el-button>
      </div>
    </div>

    <div v-if="type === 'stock'" style="padding: 1rem">
      <div
        style="
          display: flex;
          flex-direction: column;
          justify-content: center;
          align-items: center;
    
        "
      >
        <div style="font-size: 20px;font-weight: bold; color:#ffffff"><span>{{
          this.stockData.corporate_name
        }}</span><span> {{
          this.stockData.stock_code
        }}</span></div>
        <div style="font-size: 1rem; color:#ffffff">今日盈亏</div>
        <div style="font-size: 20px; color:rgb(255, 0, 0); font-weight: bold;">+2685.00</div>
        <div style="font-size: 1rem; color:#ffffff">持仓盈亏</div>
        <div style="font-size: 20px; color:rgb(255, 0, 0); font-weight: bold;">+2685.00</div>
      </div>
      <div style=" padding: 3rem; display: flex;margin-left: 40px;">
        <div class="info">
          <div class="name">
            <span style="color:rgb(255, 255, 255);">10.95</span
            ><!---<span style="color: rgb(162, 162, 167); font-weight: normal; margin-left: 10px">{{ data.remark || this.label }}</span>-->
          </div>
          <div style="color:rgb(255, 0, 0);">+1.86%</div>
        </div>
        <div class="ooo">
          <div>
            <span>最高价</span><span>10.98</span>
            <div style="margin-top: 1rem">
              <span>最低价</span><span>10.66</span>
            </div>
          </div>
          <div>
            <span>开盘价</span><span>10.77</span>
            <div style="margin-top: 1rem">
              <span>换手率</span><span>0.84%</span>
            </div>
          </div>
          <div>
            <span>成交量</span><span>26.28万手</span>
            <div style="margin-top: 1rem">
              <span>成交额</span><span>2.85亿</span>
            </div>
          </div>
        </div>
        </div>
        <div
          id="echartContainer"
          ref="echartContainer"        
          style="
          height: 400px; 
          width:90%;
          margin-left: 40px;

        "
        >
        <k
        ref="k"
        :kdata="this.stock"
      ></k>
        </div>
    
    </div>
  </el-drawer>
</template>
      
      
      <script>
      import k from "./k2.vue";
import { categoryes } from "../types/types"; //只导入特定的属性，变量
import axios from "axios";
export default {
  components: {
    k,
  },
  props: {
    ifshowDrawer: Boolean,
    type: String,
    billData: Object,
    loanData: Object,
    stockData:Object,
    stock:Array,

  },
  watch: {
    ifshowDrawer(newValue, oldValue) {
      if (newValue !== oldValue) {
        console.log("向父组件传递改变的值原来", oldValue);
        console.log("向父组件传递改变的值现在", newValue);
        if (this.type == "stock") this.drawerSize = "60%";
        this.showDrawer = newValue;
        //this.$emit('dialog-form-visible-changed', newValue);
      } else {
        console.log("111");
      }
    },
    showDrawer(newValue, oldValue) {
      if (newValue !== oldValue) {
        console.log("drawer原来", oldValue);
        console.log("drawer现在", newValue);
        this.$emit("drawerFalse", newValue);
      }
    },






  },
  data() {
    return {
      category: categoryes,
      showDrawer: false,
      drawerSize: "30%",
      dialog: false,
      loading: false,
      gridData: [
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-04",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-01",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-03",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
      ],

      timer: null,
    };
  },
  methods: {
    handleClose(done) {
      if (this.loading) {
        return;
      }
      this.$confirm("确定要提交表单吗？")
        .then((_) => {
          this.loading = true;
          this.timer = setTimeout(() => {
            done();
            // 动画关闭需要一定的时间
            setTimeout(() => {
              this.loading = false;
            }, 400);
          }, 2000);
        })
        .catch((_) => {});
    },
    deleteBill() {
      axios
        .post("/deleteBill", this.billData) //event_id是envent_id
        .then((response) => {
          console.log(response.data);
          //this.billData=response.data.data[0]
          //this.$router.push('/bill')
          this.showDrawer = false;
          this.$emit("deleteBill", "true");
          this.$message({
            message: "删除成功",
            type: "success",
          });
        });
    },
    deleteLoan() {
      axios.post("/deleteLoan", this.loanData).then((response) => {
        console.log(response.data);
        this.showDrawer = false;
        //this.billData=response.data.data[0]
        //this.$router.push('/bill')
        this.$emit("deleteLoan", "true");
        this.$message({
          message: "删除成功",
          type: "success",
        });
      });
    },
    cancelForm() {
      this.loading = false;
      this.dialog = false;
      clearTimeout(this.timer);
    },
  },
  computed: {
    descriptionTitle() {
      return this.type === "loan"
        ? "贷款详情"
        : this.type === "bill"
        ? "账单详情"
        : this.type === "stock"
        ? "股票详情"
        : this.type === "fund"
        ? "基金详情"
        : "未知详情"; // 你可以根据需要添加更多类型
    },
  },
};
</script>
      
   <style lang="less" scoped>
@import "../styles/common.less";
/deep/.el-drawer__header {
  text-align: center;
  //background-color: #409EFF; /* 背景颜色 */
  color: #000000; /* 文字颜色 */
  font-size: 20px; /* 字体大小 */
  border-bottom: 1px solid #e0e0e0; /* 下边框线 */
  margin-bottom:0%;
}

.info {
  color: @gray-1;
  font-size: 20px;
  .name {
    font-size: 24px;
    color: @gray-10;
    font-weight: 600;
  }
}

.ooo {
  display: flex;
  width: 70%;
  //margin-left: 3rem;
  gap: 0.5rem;
  align-items: flex-start;
  justify-content: space-between;
  font-size:1rem;
  color: #ffffff;
  font-size: 1rem;

  font-weight: bold;
  margin-left: 5rem;
}

::v-deep .el-drawer {
  background: linear-gradient(30deg, #baebe7, #14c4b8) !important
}

</style>
      