<template>
  <div class="bill-list">
    <template   v-if="showType === 'stock'">
    <div class="info-bar">
      <div><span>公司名称/</span><span :style="{ color: '#999999' }">股票代码</span></div>
      <div><span>持股/</span><span :style="{ color: '#999999' }">可卖</span></div>
      <div><span>市场价/</span><span :style="{ color: '#999999' }">买入价</span></div>
    </div>
    </template>
    <template v-else> 
      <div class="info-bar">
      <div><span>基金名称/</span><span :style="{ color: '#999999' }">基金代码</span></div>
      <div><span>买入金额</span></div>
      <div><span>基金市场价格</span></div>
    </div>


    </template>



    <div :style="containerStyle">
      <ProductItem
        v-for="item in data"
        :key="item.id"
        :data="item"
        :billtype="'loan'"
        @lookBill="getBill"
        :showType="showType"
      />
    </div>
  </div>
</template>
  
  <script>
import dayjs from "dayjs";
import ProductItem from "./ProductItem.vue"; // Import your BillItem component
import { sumCount } from "../compute/amountFormat";
import { color } from "echarts";

export default {
  components: {
    ProductItem,
  },
  data() {
    return {
      week: [
        "星期日",
        "星期一",
        "星期二",
        "星期三",
        "星期四",
        "星期五",
        "星期六",
      ],
      income: "",
      expend: "",
    };
  },
  props: {
    month: String,
    data: Array,
    amountCount: Object,
    showType:String,
  },
  mounted() {
    if (this.amountCount) {
      this.income = this.amountCount.income;
      this.expend = this.amountCount.expend;
    }
    if (this.month) {
      console.log("hahahahah", this.month);
      console.log("hahahahah", this.data);
    }
  },
  computed: {
    today() {
      return dayjs().format("M月D日 " + this.week[dayjs().day()]);
    },
    containerStyle() {
      // 根据 month 的值返回不同的样式
      if (this.month) {
        return {
          // 样式属性
        };
      } else {
        // 可以定义其他 month 对应的样式
        return {
          height: "300px",
          width: "100%",
          overflowY: "auto",

          marginTop: "20px",

          // 其他样式属性
        };
      }
    },
  },

  methods: {
    getBill(data) {
      this.$emit("getBill", data);
      console.log("container收到为", data);
    },
  },
};
</script>
  
  <style scoped lang="less">
@import "../styles/common.less";
.bill-list {
  margin-top: 0.5rem;
  color: gray;
  padding: 1rem;
  background-color: #fff;

  .info-bar {
    display: flex;
    width: 90%;
    margin-left: 1rem;
    gap: 0.5rem;
    align-items: flex-end;
    justify-content: space-between;
    font-size: 0.8rem;
    color: #000;
    font-size: 1rem;
    color: #000;
    font-weight: bold;
    .amount {
      color: @gray-1;
      margin: 0 0.5rem 0 0.2rem;
    }
  }
}
</style>