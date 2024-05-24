<!--
<template>
  <div class="bill-item">
    <div class="icon">
      <svg class="icon-font" aria-hidden="true">
        <use :xlink:href="`#icon-${categoryes[data.category].icon}`" />
      </svg>
    </div>
    <div class="info">
      <div class="name">
        {{ props.data.remark || categoryes[data.category].name }}
      </div>
      <div>{{ formatDate(props.data.date) }}</div>
    </div>
    <div class="amount" :style="{ color: data.type === 'expend' ? '' : '#ed5a65' }">
      <span>{{ data.type === 'expend' ? '-' : '+' }}</span>
      <span>{{ amount[0] }}</span>
      <span class="decimal">.{{ amount[1] || '00' }}</span>
    </div>
  </div>
</template>
-->
<template>
  <div class="bill-item" @click="look">
    <div class="icon">
      <svg
        class="icon-font"
        aria-hidden="true"
        xmlns="http://www.w3.org/2000/svg"
      >
        <use :xlink:href="`#icon-Stocks`" />
      </svg>
    </div>
    <template   v-if="showType === 'stock'">
    <div class="ooo">
      <div class="info">
        <div class="name">
          <span>{{ this.data.corporate_name }}</span
          ><!---<span style="color: rgb(162, 162, 167); font-weight: normal; margin-left: 10px">{{ data.remark || this.label }}</span>-->
        </div>
        <div>{{ this.data.stock_code }}</div>
      </div>
      <div class="info">
        <div class="name">
          <span>{{ this.data.holdings }}</span
          ><!---<span style="color: rgb(162, 162, 167); font-weight: normal; margin-left: 10px">{{ data.remark || this.label }}</span>-->
        </div>
        <div>{{ this.data.holdings }}</div>
      </div>
      <div class="info">
        <div class="name">
          <span
            :class="{
              'red-text': data.stock_market_prices > data.buying_price,
              'green-text': data.stock_market_prices <= data.buying_price,
            }"
            >{{ data.stock_market_prices }}</span
          >
        </div>
        <div>{{ data.buying_price }}</div>
      </div>
    </div>
    </template>

    <template v-else> 
      <div class="ooo">
      <div class="info">
        <div class="name">
          <span>{{ this.data.fund_name }}</span
          ><!---<span style="color: rgb(162, 162, 167); font-weight: normal; margin-left: 10px">{{ data.remark || this.label }}</span>-->
        </div>
        <div>{{ this.data.funds_code }}</div>
      </div>
      <div class="info">
        <div class="name">
          <span>{{ this.data.buying_price }}.00</span
          ><!---<span style="color: rgb(162, 162, 167); font-weight: normal; margin-left: 10px">{{ data.remark || this.label }}</span>-->
        </div>
      </div>
      <div class="info">
        <div class="name">
          <span
            >{{ data.fund_market_prices }}.00</span
          >
        </div>
      </div>
    </div>
      </template>

  </div>
</template>
<script>
import dayjs from "dayjs";
import { categoryes } from "../types/types"; //只导入特定的属性，变量
import { amountToArray } from "../compute/amountFormat";
export default {
  data() {
    return {
      type: categoryes,
      label: "(无备注)",
    };
  },
  mounted() {
    console.log("啊啊啊", categoryes);
    console.log("这里", this.data.amount);
  },
  props: {
    data: Object,
    billtype: String,
    showType:String,
  },

  computed: {
    amount() {
      return amountToArray(this.data.amount);
    },
  },
  methods: {
    formatDate(date) {
      return dayjs(date).format("M-D");
    },
    look() {
      this.$emit("lookBill", this.data);
    },
  },
};
</script>
<!--
<style scoped lang="less">
@import '../styles/common.less';
.bill-item {
  display: flex;
  align-items: center;
  padding: 0.5rem;
  gap: 0.8rem;
  .icon {
    border-radius: 0.5rem;
    .icon-font {
      font-size: 2rem;
    }
  }
  .info {
    flex: 1;
    color: red;
    font-size: 14px;
    .name {
      font-size: 14px;
      color: gray;
      font-weight: 600;
    }
  }
  .amount {
    font-size: 14px;
    color: red;
    .decimal {
      font-size: 14px;
    }
  }
}
</style>
-->

<style scoped lang="less">
@import "../styles/common.less";
.bill-item {
  display: flex;
  align-items: center;
  padding: 0.5rem;
  gap: 0.8rem;
  cursor: pointer;
  .icon {
    // margin: .5rem;
    // width: 2rem;
    // height: 2rem;
    border: 0.5rem;
    background-color: @primary-8;
    border-radius: 0.5rem;
    .iconfont {
      font-size: 2rem;
    }
  }
  .info {
    color: @gray-1;
    font-size: @font-size-sm;
    .name {
      width: 140px;
      font-size: @font-size-base;
      color: @gray-10;
      font-weight: 600;
    }
  }
  .red-text {
    color: red;
  }

  .green-text {
    color: green;
  }

  .amount {
    font-size: @font-size-lg;
    color: @primary-0;
    .decimal {
      font-size: @font-size-sm;
    }
  }
  .ooo {
    display: flex;
    width: 100%;
    //margin-left: 3rem;
    gap: 0.5rem;
    align-items: flex-end;
    justify-content: space-between;
    font-size: 0.8rem;
    color: #000;
    font-size: 1rem;
    color: #000;
    font-weight: bold;
  }
}
</style>
