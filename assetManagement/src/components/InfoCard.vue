<template>
  <!-- class="info-card"-->
  <div class="info-card">
    <div class="info-container">
      <div class="info">
        <div class="info-item">
          <div>共计支出</div>
          <div class="base-amount">
            <span>{{ this.gongji }}</span>
          </div>
        </div>
        <div class="info-item">
          <div>记账笔数</div>
          <div class="base-amount">
            {{ this.bishu }}
          </div>
        </div>
        <div class="info-item">
          <div>日均支出</div>
          <div class="base-amount">
            <span>{{ this.zhichu }}</span>
          </div>
        </div>
      </div>
    </div>
    <!--
    <div class="proportion">
      <div class="percentage">{{ this.num }}%</div>
      <svg width="100px" height="100px" class="svg">
      
        <circle
          cx="50"
          cy="50"
          r="30"
          stroke="#daf4ee"
          stroke-width="12"
          fill="none"
        />
       
        <circle
          cx="50"
          cy="50"
          r="26"
          stroke="#3BCEB1"
          stroke-width="12"
          fill="none"
          :stroke-dasharray="dashArrayWidth.end"
          stroke-linecap="round"
        >
       
          <animate
            attributeName="stroke-dasharray"
            :from="dashArrayWidth.start"
            :to="dashArrayWidth.end"
            dur=".5s"
          />
      -->
          <!-- <animate attributeName="stroke" from="#3BCEB1" to="#53d74c" dur="1s" /> -->
          <!--
        </circle>
      </svg>
    </div>
  -->
  </div>
</template>
  
  <script>
import { mapGetters } from "vuex";
import axios from "axios";

export default {
  mounted(){

    axios.post('/getTotalAmount')//event_id是envent_id
  .then(response => {
    if (response.data) {
      // 如果成功获取到数据
      this.gongji = response.data.data
    } 
    })

    axios.post('/getBillCount')//event_id是envent_id
  .then(response => {
    if (response.data) {
      // 如果成功获取到数据
      console.log(response.data)
      this.bishu = response.data.data
    } 
    })

    axios.post('/getDayAverageBill')//event_id是envent_id
  .then(response => {
    if (response.data) {
      // 如果成功获取到数据
      this.zhichu = response.data.data
    } 
    })
    axios.post('/getTotalIncomeExpenseByTypeAsJson')//event_id是envent_id
  .then(response => {
    if (response.data) {
      // 如果成功获取到数据
      console.log(response.data)
    } 
    })
  


  },
  computed: {
    ...mapGetters("mainStore", {
      usuallyRecord: "usuallyRecord",
    }),
    totalExpenses() {
      const res = this.usuallyRecord.filter((r) => r.type === "expend");
      return res.reduce((acc, cur) => acc + cur.amount, 0).toFixed(2);
    },
    averageExpend() {
      return (this.totalExpenses / new Date().getMonth()).toFixed(2);
    },
  },
  data() {
    return {
      //num: Math.floor(Math.random() * 100),
      num: 34,
      gongji: "共计支出没写",
      bishu: "",
      zhichu: "",
    };
  },
  computed: {
    line() {
      return 2 * Math.PI * 26;
    },
    dashArrayWidth() {
      return {
        start: `0,${this.line}`,
        end: `${this.line * (this.num / 100)},${
          this.line * ((100 - this.num) / 100)
        }`,
      };
    },
  },
  methods: {
    init() {
      this.myChart = echarts.init(this.$refs.main);
      const option = {
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "cross",
            label: {
              backgroundColor: "#6a7985",
            },
          },
        },
        xAxis: {
          data: ["1月", "2月", "4月", "5月", "6月"],
          boundaryGap: false,
        },
        yAxis: {
          show: false,
        },
        series: [
          {
            data: [100, 200, 160, 80, 300],
            type: "line",
            smooth: true,
            lineStyle: {
              width: 0,
            },
            showSymbol: false,
            areaStyle: {
              opacity: 0.8,
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                {
                  offset: 0,
                  color: "rgb(128, 255, 165)",
                },
                {
                  offset: 1,
                  color: "rgb(1, 191, 236)",
                },
              ]),
            },
          },
        ],
      };
      this.myChart.setOption(option);
    },

 
  },
  created() {
    this.init();
  },
};
</script>
  
  <style scoped lang="less">
@import "../styles/common.less";
.info-card {
  display: flex;
  align-items: center;
  background-color: #fff;
  border-radius: 0.2rem;
  margin-bottom: 1rem;
  //padding: 1rem;
  .info-container {
    flex: 1;
    .info {
      display: flex;
      justify-content: space-between;
      .info-item {
        font-size: 0.8rem;
        color: @gray-1;
        text-align: center;
      }
    }
  }
  .proportion {
    width: 6rem;
    height: 100%;
    position: relative;
    .percentage {
      position: absolute;
      left: 50%;
      top: 50%;
      width: auto;
      height: auto;
      color: @primary-0;
      font-weight: bold;
      transform: translate(-50%, -50%);
    }
    .svg-container {
      position: absolute;
      left: 50%;
      top: 50%;
      transform: translate(-50%, -50%);
    }
    .circle-container {
      position: relative;
      width: 100px;
      height: 100px;
      .circle-outer {
        border-radius: 50%;
        width: 100px;
        height: 100px;
        border: 6px solid #daf4ee;
        box-sizing: border-box;
        position: absolute;
      }
      .circle-inner {
        border-radius: 50%;
        width: 100px;
        height: 100px;
        border: 6px solid #3bceb1;
        box-sizing: border-box;
        position: absolute;
        transform-origin: center;
        transform: rotate(-90deg);
        stroke-dasharray: 0, 100%;
        stroke-linecap: round;
      }
    }
  }
}
</style>
  