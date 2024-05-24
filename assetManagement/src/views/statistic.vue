<template>
  <div class="box" ref="pdfContent" id="pdfContent">
    <el-aside width="200px" class="aside">
      <Aside :menu-item="4"></Aside>
    </el-aside>
    <el-main>
      <Topbar title="统计" />
      
      
      <div class="container">
        <div class="select">
          
          <el-button
            :class="{ active: this.check === 'expend' }"
            @click="getExpendChart"
          >
            支出
          </el-button>
          <el-button
            :class="{ active: this.check === 'income' }"
            @click="getIncomeChart"
          >
            收入
          </el-button>
          <el-button
            :class="{ active: this.check === 'compare' }"
            @click="getCompareChart"
          >
            对比
          </el-button>
          <div
     
          >

       
          </div>
          <div style="position:absolute;top:3%;left:85%">
          <el-button
          @click="exportToPdf('pdfContent', '报告可视化')" 
          >
          下载
          </el-button>
        </div>

          



        </div>
        
        <div class="count-card">
          <div style="font-size: large; text-align: center; font-weight: bold">
            {{ this.chartTitle }}
          </div>
          <template>
            <echartsZhexian
              v-if="check === 'expend'"
              :option="this.zhexianOption"
            />
            <template v-else-if="check === 'income'">
              <echartsZhexian2 :option="this.zhexianOption3" />
            </template>
            <template v-else>
              <echartsZhexian3 :option="this.zhexianOption2" />
            </template>
          </template>
        </div>
        <!--<InfoCard />-->
        <div class="select2">
          <el-button
            :class="{ active: this.check2 === 'rank' }"
            @click="check2 = 'rank'"
          >
            支出排行
          </el-button>
          <el-button
            :class="{ active: this.check2 === 'distribution' }"
            @click="check2 = 'distribution'"
            style="margin-left:3rem;"
          >
            资产分布
          </el-button>

          



    
        </div>


        <div class="expenditure-top">
          <template>
            <div v-if="check2 === 'rank'">
              <InfoCard />
              <div style="height: 180px; overflow-y: scroll">
                <div class="item-container">
                  <div
                    v-for="(i, idx) in categoryValue"
                    :key="idx"
                    class="item"
                  >
                    <div class="icon">
                      <svg class="icon-font" aria-hidden="true">
                        <use :xlink:href="`#icon-${i.icon}`" />
                      </svg>
                    </div>
                    <div class="item-right">
                      <div class="base-title">
                        {{ Type[i.name].name }}&nbsp;&nbsp;{{ i.value * 100 }}%
                      </div>
                      <div class="process-bar">
                        <div
                          class="bar"
                          :style="{ width: i.value * 100 + '%' }"
                        >
                          <div></div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <template v-else>
              <div style="height: 280px">
                <div class="item-container">
                  <echartsBing :option="this.BingOption" />
                </div>
              </div>
            </template>
          </template>
        </div>
      </div>
    </el-main>
  </div>
</template>
  
  <script>
import * as echarts from "echarts";
import { categoryes } from "../types/types"; //只导入特定的属性，变量
import dayjs from "dayjs";
import axios from "axios";
import Aside from "../components/Aside2.vue";
import Topbar from "../components/Topbar.vue";
import InfoCard from "../components/InfoCard.vue";
import BillContainer from "../components/BillContainer.vue";
import echartsBing from "../components/echartsBing.vue";
import echartsZhexian from "../components/echartsZhexian.vue";
import echartsZhexian2 from "../components/echartsZhexian2.vue";
import echartsZhexian3 from "../components/echartsZhexian3.vue";
import html2pdf from 'html2pdf.js';


export default {
  components: {
    Aside,
    Topbar,
    InfoCard,
    BillContainer,
    echartsBing,
    echartsZhexian,
    echartsZhexian2,
    echartsZhexian3,
  },
  mounted() {
    axios.post("/getExpenseByCategory").then((response) => {
      console.log("/getExpenseByCategory");
      console.log(response.data);
      this.categoryValue = response.data.data;
    });

    axios.post("/getExpenseByCategory").then((response) => {
      console.log("/getExpenseByCategory");
      console.log(response.data);
      this.categoryValue = response.data.data;
    });
    axios.post("/getTotalBuyingPriceForFunds").then((response) => {
      this.totalFundAmount = response.data.data
      axios.post("/getTotalStockValue").then((response) => {
        this.totalStockAmount = response.data.data
        console.log("az",this.totalFundAmount)
        console.log("az",this.totalStockAmount)
      });
    });
    axios.post("/getTotalIncomeExpenseByMonth").then((response) => {
      console.log("  /getTotalIncomeExpenseByMonth");
     // this.categoryValue = response.data.data;
     console.log(response.data);
     this.getyAxis(response.data.data);
    });

  },
  data() {
    return {
      Type: categoryes,
      incomeY:[],
      expendY:[],
      totalFundAmount:'',
      totalStockAmount:'',
      check: "expend",
      check2: "rank",
      chartTitle: "支出折线图",
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
      myChart: null, // 初始化为null
      categoryValue: [],

      /*
      categoryValue: [
        {
          name: "餐饮",
          value: 30, // 假设占比为 30%
          icon: "shiwu", // 假设使用名为 "restaurant" 的图标
        },
        {
          name: "购物",
          value: 20, // 假设占比为 20%
          icon: "gouwudai", // 假设使用名为 "shopping" 的图标
        },
        {
          name: "购物",
          value: 20, // 假设占比为 20%
          icon: "gouwudai", // 假设使用名为 "shopping" 的图标
        },
        {
          name: "购物",
          value: 20, // 假设占比为 20%
          icon: "gouwudai", // 假设使用名为 "shopping" 的图标
        },
        {
          name: "购物",
          value: 20, // 假设占比为 20%
          icon: "gouwudai", // 假设使用名为 "shopping" 的图标
        },
        {
          name: "购物",
          value: 20, // 假设占比为 20%
          icon: "gouwudai", // 假设使用名为 "shopping" 的图标
        },
        {
          name: "购物",
          value: 20, // 假设占比为 20%
          icon: "gouwudai", // 假设使用名为 "shopping" 的图标
        },
        {
          name: "购物",
          value: 20, // 假设占比为 20%
          icon: "gouwudai", // 假设使用名为 "shopping" 的图标
        },
        // 其他项目
      ],

      */
      BingOption: {
        tooltip: {
          trigger: "item",
        },
        legend: {
          top: "5%",
          left: "center",
        },
        series: [
          {
            name: "资产分布",
            type: "pie",
            radius: ["40%", "70%"],
            avoidLabelOverlap: false,
            label: {
              show: false,
              position: "center",
            },
            emphasis: {
              label: {
                show: true,
                fontSize: 40,
                fontWeight: "bold",
              },
            },
            labelLine: {
              show: false,
            },
            data: [
              { value: this.totalFundAmount, name: "基金" },
              { value: this.totalStockAmount, name: "股票" },
              { value: 200000, name: "存款" },
            ],
          },
        ],
      },

      zhexianOption: {
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
          data: [
          "1月",
        "2月",
        "3月",
        "4月",
        "5月",
        "6月",
        "7月",
        "8月",
        "9月",
        "10月",
        "11月",
        "12月",
          ],
          boundaryGap: false,
        },
        yAxis: {
          show: false,
        },
        series: [
          {
            data: [100, 200, 160, 80, 300, 100, 200, 160, 80, 300],
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
      },

      zhexianOption2: "",

      zhexianOption3: {
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
          data: [
          "1月",
        "2月",
        "3月",
        "4月",
        "5月",
        "6月",
        "7月",
        "8月",
        "9月",
        "10月",
        "11月",
        "12月",
          ],
          boundaryGap: false,
        },
        yAxis: {
          show: false,
        },
        series: [
          {
            data: [100, 200, 160, 80, 300, 100, 200, 160, 80, 300],
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
            color: "rgb(255, 165, 128)",
          },
          {
            offset: 1,
            color: "rgb(236, 191, 1)",
          },
              ]),
            },
          },
        ],
      },
    };
  },
  computed: {
    totalExpenses() {
      const res = this.$store.state.mainStore.recordList.usually.filter(
        (r) => r.type === "expend"
      );
      return res.reduce((acc, cur) => acc + cur.amount, 0);
    },
    /*
      categoryValue() {
        const res = []
        for (const k in categoryes) {
          const amount = this.$store.state.mainStore.recordList.usually.reduce((count, item) => {
            if (item.category === k) count += item.amount
            return count
          }, 0)
          res.push({
            name: categoryes[k].name,
            value: Math.floor((amount / this.totalExpenses) * 100),
            icon: categoryes[k].icon,
          })
        }
        return res
      },
      */
  },
  watch:{
    totalFundAmount(n,v){
      console.log(" totalFundAmount变了")
      console.log(n)
      console.log(this.BingOption.series[0].data[0].value)
      this.BingOption.series[0].data[0].value = this.totalFundAmount

    },
    totalStockAmount(n,v){
      console.log("   totalStockAmount变了")
      console.log(n)
      console.log(this.BingOption.series[0].data[1].value)
      this.BingOption.series[0].data[1].value = this.totalStockAmount
    },
    expendY(n,v){
      console.log("expendY新值",n)
      this.getExpendChart()
    },
    incomeY(n,v){
      console.log("incomeY新值",n)
      this.getIncomeChart()
    }

  },
  methods: {
    exportToPDF() {
      // 获取导出的内容
      const content = this.$refs.pdfContent;


// 进行其他 Canvas 操作
// ...

      // 设置导出选项
      const options = {
        margin: 10,
        filename: 'exported-document.pdf',
        image: { type: 'jpeg', quality: 0.98 },
        html2canvas: { scale: 2 },
        jsPDF: { unit: 'mm', format: 'a4', orientation: 'portrait' },
      };

      // 使用 html2pdf 导出 PDF
      html2pdf().from(content).set(options).outputPdf(pdf => {
        // 下载 PDF
        const blob = new Blob([pdf], { type: 'application/pdf' });
        const link = document.createElement('a');
        link.href = window.URL.createObjectURL(blob);
        link.download = options.filename;
        link.click();
      });
    },


    getyAxis(expenses){
      // 假设你的数组为 expenses

// 初始化两个新数组
let incomeArray = Array(12).fill(0);  // 12 个月的 income 数组，初始值为 0
let expendArray = Array(12).fill(0);  // 12 个月的 expend 数组，初始值为 0

// 遍历原始数组
expenses.forEach(expense => {
  // 将 income 和 expend 的值加到对应月份的数组中
  const monthIndex = parseInt(expense.month, 10) - 1;  // 月份从 1 开始，数组索引从 0 开始
  incomeArray[monthIndex] += expense.income;
  expendArray[monthIndex] += expense.expense;
});

// 打印结果
console.log("Income Array:", incomeArray);
console.log("Expend Array:", expendArray);
this.expendY =  expendArray
this.incomeY = incomeArray


    },
    getExpendChart() {
      this.chartTitle = "支出折线图";
      this.check = "expend";
      this.zhexianOption.xAxis.data = [
      "1月",
        "2月",
        "3月",
        "4月",
        "5月",
        "6月",
        "7月",
        "8月",
        "9月",
        "10月",
        "11月",
        "12月",
      ];
      console.log( this.zhexianOption.series[0].data)
      this.zhexianOption.series[0].data=this.expendY;

      this.zhexianOption.series[0].areaStyle.color =
        new echarts.graphic.LinearGradient(0, 0, 0, 1, [
          {
            offset: 0,
            color: "rgb(128, 255, 165)",
          },
          {
            offset: 1,
            color: "rgb(1, 191, 236)",
          },
        ]);
    },
    getIncomeChart() {
      this.chartTitle = "收入折线图";
      this.check = "income";
      this.zhexianOption3.xAxis.data = [
      "1月",
        "2月",
        "3月",
        "4月",
        "5月",
        "6月",
        "7月",
        "8月",
        "9月",
        "10月",
        "11月",
        "12月",
      ];
      console.log( this.zhexianOption3.series[0].data)
      this.zhexianOption3.series[0].data=this.incomeY;

      this.zhexianOption3.series[0].areaStyle.color =
        new echarts.graphic.LinearGradient(0, 0, 0, 1, [
          {
            offset: 0,
            color: "rgb(255, 165, 128)",
          },
          {
            offset: 1,
            color: "rgb(236, 191, 1)",
          },
        ]);
    },
    getCompareChart() {
      this.chartTitle = "对比折线图";
      this.check = "compare";

      this.zhexianOption2 = {
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
          data: [
          "1月",
        "2月",
        "3月",
        "4月",
        "5月",
        "6月",
        "7月",
        "8月",
        "9月",
        "10月",
        "11月",
        "12月",
          ],
          boundaryGap: false,
        },
        yAxis: {
          show: false,
        },
        series: [
          {
            name: "折线1", // 第一条折线的名称
            data: this.expendY,
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

          {
            name: "折线2", // 第二条折线的名称
            data: this.incomeY,
            type: "line",
            smooth: true,
            lineStyle: {
              width: 0,
            },
            showSymbol: false,
            areaStyle: {
              opacity: 0.8,
              color: new echarts.graphic.LinearGradient(0.5, 0.5, 0, 1, [
                {
                  offset: 0,
                  color: "rgb(255, 128, 165)", // 自定义红色渐变起始颜色
                },
                {
                  offset: 1,
                  color: "rgb(191, 1, 236)", // 自定义红色渐变结束颜色
                },
              ]),
            },
          },
        ],
      };
    },
  },
};
</script>
  
  <style scoped lang="less">
@import "../styles/common.less";

.select {
  color: gray-1;
  font-size: @font-size-sm;
  margin: 0.6rem 0; //瞎tm晃不会改了艹
  color: gray;
  //background-color: black;
  //height: @font-size-base;
  height: 2rem;

  button {
    width: 3rem;
    border: none; /* 去除边框 */
    background: transparent; /* 去除背景色 */
  }
  .active {
    font-weight: 600;
    font-size: large;
    color: @gray-10;
    // margin: .6rem 0;
  }
}

.select2 {
  color: gray-1;
  font-size: @font-size-sm;
  margin: 1rem 0; //瞎tm晃不会改了艹
  color: gray;
  //background-color: black;
  //height: @font-size-base;
  height: 2rem;

  button {
    width: 3rem;
    border: none; /* 去除边框 */
    background: transparent; /* 去除背景色 */
  }
  .active {
    font-weight: 600;
    font-size: large;
    color: @gray-10;
    // margin: .6rem 0;
  }
}

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

.count-card {
  //background: linear-gradient(30deg,#baebe7, #2a7c76);
  background-color: #ffffff;
  padding: 1rem;
  margin-top: 1rem;
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
.container {
  overflow-y: auto; /* 允许垂直滚动 */
  padding: 1rem;

  .date {
    margin: 1rem 0;
    display: flex;
    font-size: small;
    justify-content: space-evenly;

    .date-item {
      color: @gray-2;
      padding: 0.3rem 0.8rem;
      border-radius: 0.6rem;
      background-color: rgb(237, 250, 248);
    }

    .month-select {
      color: @primary-0;
    }
  }

  .echarts {
    width: 100%;
    height: 200px;
  }

  .expenditure-top {
    // overflow-y: auto; /* 允许垂直滚动 */
    background-color: #fff;
    border-radius: 0.2rem;
    padding: 1em;
    margin-top: 1rem;
    border: 1px solid rgb(107, 151, 134);

    .item-container {
      .item {
        display: flex;
        align-items: center;
        padding-bottom: 0.4rem;
        gap: 0.5rem;

        .icon {
          border: 0.5rem;
          background-color: @primary-8;
          border-radius: 0.5rem;
        }

        .item-right {
          flex: 1;
        }

        .process-bar {
          position: relative;
          width: 100%;
          height: 10px;
          background-color: @primary-3;
          border-radius: 5px;

          .bar {
            height: 100%;
            div {
              width: 100%;
              height: 100%;
              border-radius: 5px;
              background-color: @primary-1;
              animation: load-bar 0.5s ease-out;
            }
          }

          @keyframes load-bar {
            0% {
              width: 0;
            }

            100% {
              width: 100%;
            }
          }
        }
      }
    }
  }
}
</style>
  