<template>
  <div class="bill-list">
      <div class="info-bar">
        <div class="info" style="font-size: 1rem; color: #000; ">
          <span style="font-weight: bold;">共计支出  </span> 
          <span class="amount">{{this.amountCount.expend}}</span>
          <span class="amount">元/月 </span> 
        </div>
      </div>

    <div :style="containerStyle">
      <BillItem
        v-for="item in data"
        :key="item.id"
        :data="item"
        :billtype="'loan'"
        @lookBill="getBill"
      />
    </div>
  </div>
</template>

<script>
import dayjs from "dayjs";
import BillItem from "./BillItem.vue"; // Import your BillItem component
import { sumCount } from "../compute/amountFormat";

export default {
  components: {
    BillItem,
    amountCount:Object,
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
  },
  mounted() {
    console.log("hahahahah", this.data);
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

  methods:{
    
    getBill(data){
      this.$emit('getBill',data)
      console.log("container收到为",data)

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
    gap: 0.5rem;
    align-items: flex-end;
    font-size: 0.8rem;
    color: #000;
    .amount {
      color: @gray-1;
     // margin: 0 0.5rem 0 0.2rem;
    }
  }
}
</style>