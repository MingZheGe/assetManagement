
<template>
    <div
                ref="main2"
                class="echarts"
                style="width: 100%; height: 300px"
              ></div>
   
  </template>
  <script>
  import * as echarts from "echarts";

  export default {
    props: {
    option: Object,
  },
    data() {
      return {
      chart: null, // 初始化为null

     
     
      }
    },
    watch: {
		option: {
      handler(newValue, oldValue) {
        console.log("饼状图感受到了")
        console.log(newValue)
        console.log(oldValue)
        this.init();
	　
	},
  immediate:true
		},
	},

  
  
    computed: {
     
    },
    methods: {
      init(){
        this.$nextTick(() => {
      this.chart = echarts.init(this.$refs.main2);
      this.chart.setOption(this.option); // 修正为 this.option
    });
      }
     
    },
  }
  </script>
  
  
  <style scoped lang="less">

.echarts {
    width: 100%;
    height: 200px;
  }

  </style>
  