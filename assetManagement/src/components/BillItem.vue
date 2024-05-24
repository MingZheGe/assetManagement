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
      <svg class="icon-font" aria-hidden="true"  xmlns="http://www.w3.org/2000/svg">
        <use :xlink:href="`#icon-${this.type[data.category].icon}`" />
      </svg>
    </div>
    <div class="info">
      <div class="name">
        <span>{{ this.type[data.category].name}}</span><span style="color: rgb(162, 162, 167); font-weight: normal; margin-left: 10px">{{ data.remark || this.label }}</span>
      </div>
      <div>{{ data.date }}</div>
    </div>
    <div class="amount" :style="{ color: data.type === 'income' ? '#ed5a65' : '' }">
      <span>{{data.type === 'income' ? '+' : '-'}}</span>
      <span>{{amount[0]}}</span>
      <span class="decimal">.{{amount[1]}}</span>
      <span v-if="billtype === 'loan'">/月</span>
    </div>
  </div>
</template>
<script>
import dayjs from 'dayjs'
import {categoryes} from '../types/types'//只导入特定的属性，变量
import { amountToArray } from '../compute/amountFormat'
export default {
  data() {
    return {
      type: categoryes,
      label:'(无备注)'
   
    }
  },
  mounted(){
    console.log("啊啊啊",categoryes)
    console.log("这里",this.data)
   
    
  },
  props: {
    data: Object,
    billtype: String,
  },

  computed: {
    amount() {
      if (this.billtype === 'loan') {
    return amountToArray(this.data.loanAmount);
  } else if (this.billtype === 'bill') {
    return amountToArray(this.data.amount);
  } else {
    // 处理其他情况，例如默认返回一个空数组
    return [];
  }
    },
  },
  methods: {
    formatDate(date) {
      return dayjs(date).format('M-D')
    },
    look(){
      console.log("我被点击了")
      console.log(this.data.id)
      this.$emit('lookBill',this.data)

    }
  },
}
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
@import '../styles/common.less';
.bill-item{
  
  display: flex;
  align-items: center;
  padding: .5rem;
  gap: .8rem;
  cursor: pointer;
  .icon{
    // margin: .5rem;
    // width: 2rem;
    // height: 2rem;
    border: .5rem;
    background-color: @primary-8;
    border-radius: .5rem;
    .iconfont{
      font-size: 2rem;
    }
  }
  .info{
    flex:1;
    color: @gray-1;
    font-size: @font-size-sm;
    .name{
      font-size: @font-size-base;
      color: @gray-10;
      font-weight: 600;
    }
  }
  .amount{
    font-size: @font-size-lg;
    color: @primary-0;
    .decimal{
      font-size: @font-size-sm;
    }
  }
}
</style>
