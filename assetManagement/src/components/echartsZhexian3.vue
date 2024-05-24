
<template>
   <div
            ref="main"
            class="echarts"
            style="width: 100%; height: 200px"
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
        this.init();
	　
	},
  immediate:true,
  deep: true
		},
	},

  
  
    computed: {
     
    },
    methods: {
      init(){
        this.$nextTick(() => {
      this.chart = echarts.init(this.$refs.main);
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
  