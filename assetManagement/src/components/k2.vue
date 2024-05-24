<template>
  <div>
    <h1 align="center">{{ title }}</h1>
    <div
      id="echartContainer"
      ref="echartContainer"
      style="width: 100%; height: 400px"
    ></div>
  </div>
</template>
  
  <script>
var echarts = require("echarts");
export default {
  props: ["title", "kdata"],
  data() {
    return {
      charts: null,
      foundMatch: false,

      // k线配置项
    };
  },
  watch: {
    kdata(newv,oldv){
        if(oldv!='' && newv!=oldv)
        {
            console.log("变了111",newv);
            this.init();

        }
    },
  },

  mounted() {
    console.log("我挂载了");
    setTimeout(() => {
      console.log("出现");
      // 这里实现的是一个比较简单的，可以按照需求将函数移动到methods函数中
      var data0 = splitData(this.kdata);
      console.log(data0);
      console.log("kdata在这里");
      console.log(this.kdata); //没传过来
      // macd计算
      function splitData(rawData) {
        var categoryData = [];
        var values = [];
        var macds = [];
        var difs = [];
        var deas = [];
        var vol = [];
        for (var i = 0; i < rawData.length; i++) {
          categoryData.push(rawData[i].splice(0, 1)[0]);
          values.push(rawData[i]);
          macds.push(rawData[i][6]);
          difs.push(rawData[i][7]);
          deas.push(rawData[i][8]);
          vol.push(rawData[i][4]);
        }
        console.log("values在这里");
        console.log(vol);
        return {
          categoryData: categoryData,
          values: values,
          macds: macds,
          difs: difs,
          deas: deas,
          vol: vol,
        };
      }
      // ma均线函数
      function calculateMA(dayCount) {
        var result = [];
        for (var i = 0, len = data0.values.length; i < len; i++) {
          if (i < dayCount) {
            result.push("-");
            continue;
          }
          var sum = 0;
          for (var j = 0; j < dayCount; j++) {
            sum += data0.values[i - j][1];
          }
          result.push(sum / dayCount);
        }
        return result;
      }
      var foundMatch = false;
      var foundMatch2 = false;
      var data1 = "";
      var option = {
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "cross",
          },
        },
        grid: [
          {
            left: "3%",
            top: "0%",
            height: "70%",
          },
          /*
  {
    left: "3%",
    right: "10%",
    top: "75%",
    height: "15%"
  }
  */
        ],
        xAxis: [
          {
            type: "category",
            data: data0.categoryData,
            scale: true,
            boundaryGap: false,

            axisLabel: {
              //this.interval
              //  formatter: function (value, index) {

              // 判断是否进行了放大缩小
              //    if (value === data0.categoryData[index]) {
              /*
          console.log("疯了")
          console.log(value)
          // 未进行放大缩小时，显示每个月的01号
          const year = value.substr(0, 4);
        const month = value.substr(4, 2);
        const day = value.substr(6, 2);
       // return `${year}-${month}-${day}`;
          return year +  month + "01";
          */
              /*
        } 
        else {
          // 其他情况显示原始日期
          return value;
        } 
        return value;
      },
      */
              // let foundMatch = false, // 在外部作用域定义 foundMatch 变量

              interval: function (index, data) {
                const lastTwoDigits = data.substr(-4);

                const lastFourDigits = data.substr(-4);

                // 定义要查找的后四位数字的开头
                const targetStartsWith = ["03", "06", "09", "12"];
                const targetStartsWith2 = ["06", "09", "12"];
                if (data === data0.categoryData[index]) {
                  for (const targetStart of targetStartsWith) {
                    if (lastFourDigits.startsWith(targetStart) && !foundMatch) {
                      foundMatch = true; // 标记已找到匹配值
                      data1 = data;
                      return data;
                    }
                  }
                  /*
if(foundMatch==true)
{
  return data1
}
*/
                  for (const targetStart of targetStartsWith2) {
                    if (
                      lastFourDigits.startsWith(targetStart) &&
                      !foundMatch2
                    ) {
                      foundMatch2 = true; // 标记已找到匹配值
                      return data;
                    }
                  }
                } else {
                  // 如果没有找到匹配的值，返回0表示不显示刻度
                  return data;
                }
              },
              /*
    formatter: function (value, index) {
        // 显示刻度值的后四个数字
        return value;
      },
      */
            },
            axisLine: {
              onZero: false,
              lineStyle: {
                color: "red", //鼠标放上去动的线颜色
              },
            },
            splitLine: {
              show: false,
            },
            splitNumber: 20,
          },
          /*
  {
    type: "category",
    gridIndex: 1,
    data: data0.categoryData,
    axisLabel: { show: false }
  }
  */
        ],
        yAxis: [
          {
            scale: true,
            splitArea: {
              show: true,
            },
            axisLine: {
              lineStyle: {
                color: "red", //鼠标放上去动的线颜色
              },
            },
            position: "right",
          },
          /*
  {
    gridIndex: 1,
    splitNumber: 3,
    axisLine: { onZero: false },
    axisTick: { show: false },
    splitLine: { show: false },
    axisLabel: { show: true },
    axisLine: {
      lineStyle: {
        color: "red"
      }
    },
    position: "right"
  }
  */
        ],
        dataZoom: [
          {
            type: "inside",
            start: 0, //100,
            end: 100, //80
          },
          {
            show: true,
            type: "slider",
            y: "75%", //底下滑轮位置
            start: 0, //50,
            end: 100,
          },
          {
            show: false,
            xAxisIndex: [0, 1],
            type: "slider",
            start: 0, //20,
            end: 100,
          },
        ],
        series: [
          {
            name: this.title,
            type: "candlestick",
            data: data0.values,
            markPoint: {
              data: [
                {
                  coord: [
                    "2023-08-01",
                    data0.values[data0.categoryData.indexOf("20211015")],
                  ],
                  symbol: "circle", // 标注的形状
                  symbolSize: 8, // 标注的大小
                  itemStyle: {
                    color: "red", // 标注的颜色
                  },
                  label: {
                    show: true,
                    position: "top",
                    formatter: "20211015", // 标注的文本
                  },
                },
              ],
            },

            markLine: {
              silent: true,
              data: [
                {
                  yAxis: 2222,
                },
              ],
            },
          },

          {
            name: "MA5",
            type: "line",
            data: calculateMA(5),
            smooth: true,
            lineStyle: {
              normal: {
                opacity: 0.5,
              },
            },
          },
          {
            name: "MA10",
            type: "line",
            data: calculateMA(10),
            smooth: true,
            lineStyle: {
              normal: {
                opacity: 0.5,
              },
            },
          },
          {
            name: "MA20",
            type: "line",
            data: calculateMA(20),
            smooth: true,
            lineStyle: {
              normal: {
                opacity: 0.5,
              },
            },
          },
          {
            name: "MA30",
            type: "line",
            data: calculateMA(30),
            smooth: true,
            lineStyle: {
              normal: {
                opacity: 0.5,
              },
            },
          },
        ],
      };

      // 进行初始化
      this.charts = echarts.init(this.$refs.echartContainer);
      this.charts.setOption(option);
    }, 500);
  },
  methods: {
    init(){
        this.charts=''
        console.log("我挂载了");
    setTimeout(() => {
      console.log("出现");
      // 这里实现的是一个比较简单的，可以按照需求将函数移动到methods函数中
      var data0 = splitData(this.kdata);
      console.log(data0);
      console.log("kdata在这里");
      console.log(this.kdata); //没传过来
      // macd计算
      function splitData(rawData) {
        var categoryData = [];
        var values = [];
        var macds = [];
        var difs = [];
        var deas = [];
        var vol = [];
        for (var i = 0; i < rawData.length; i++) {
          categoryData.push(rawData[i].splice(0, 1)[0]);
          values.push(rawData[i]);
          macds.push(rawData[i][6]);
          difs.push(rawData[i][7]);
          deas.push(rawData[i][8]);
          vol.push(rawData[i][4]);
        }
        console.log("values在这里");
        console.log(vol);
        return {
          categoryData: categoryData,
          values: values,
          macds: macds,
          difs: difs,
          deas: deas,
          vol: vol,
        };
      }
      // ma均线函数
      function calculateMA(dayCount) {
        var result = [];
        for (var i = 0, len = data0.values.length; i < len; i++) {
          if (i < dayCount) {
            result.push("-");
            continue;
          }
          var sum = 0;
          for (var j = 0; j < dayCount; j++) {
            sum += data0.values[i - j][1];
          }
          result.push(sum / dayCount);
        }
        return result;
      }
      var foundMatch = false;
      var foundMatch2 = false;
      var data1 = "";
      var option = {
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "cross",
          },
        },
        grid: [
          {
            left: "3%",
            top: "0%",
            height: "70%",
          },
          /*
  {
    left: "3%",
    right: "10%",
    top: "75%",
    height: "15%"
  }
  */
        ],
        xAxis: [
          {
            type: "category",
            data: data0.categoryData,
            scale: true,
            boundaryGap: false,

            axisLabel: {
              //this.interval
              //  formatter: function (value, index) {

              // 判断是否进行了放大缩小
              //    if (value === data0.categoryData[index]) {
              /*
          console.log("疯了")
          console.log(value)
          // 未进行放大缩小时，显示每个月的01号
          const year = value.substr(0, 4);
        const month = value.substr(4, 2);
        const day = value.substr(6, 2);
       // return `${year}-${month}-${day}`;
          return year +  month + "01";
          */
              /*
        } 
        else {
          // 其他情况显示原始日期
          return value;
        } 
        return value;
      },
      */
              // let foundMatch = false, // 在外部作用域定义 foundMatch 变量

              interval: function (index, data) {
                const lastTwoDigits = data.substr(-4);

                const lastFourDigits = data.substr(-4);

                // 定义要查找的后四位数字的开头
                const targetStartsWith = ["03", "06", "09", "12"];
                const targetStartsWith2 = ["06", "09", "12"];
                if (data === data0.categoryData[index]) {
                  for (const targetStart of targetStartsWith) {
                    if (lastFourDigits.startsWith(targetStart) && !foundMatch) {
                      foundMatch = true; // 标记已找到匹配值
                      data1 = data;
                      return data;
                    }
                  }
                  /*
if(foundMatch==true)
{
  return data1
}
*/
                  for (const targetStart of targetStartsWith2) {
                    if (
                      lastFourDigits.startsWith(targetStart) &&
                      !foundMatch2
                    ) {
                      foundMatch2 = true; // 标记已找到匹配值
                      return data;
                    }
                  }
                } else {
                  // 如果没有找到匹配的值，返回0表示不显示刻度
                  return data;
                }
              },
              /*
    formatter: function (value, index) {
        // 显示刻度值的后四个数字
        return value;
      },
      */
            },
            axisLine: {
              onZero: false,
              lineStyle: {
                color: "red", //鼠标放上去动的线颜色
              },
            },
            splitLine: {
              show: false,
            },
            splitNumber: 20,
          },
          /*
  {
    type: "category",
    gridIndex: 1,
    data: data0.categoryData,
    axisLabel: { show: false }
  }
  */
        ],
        yAxis: [
          {
            scale: true,
            splitArea: {
              show: true,
            },
            axisLine: {
              lineStyle: {
                color: "red", //鼠标放上去动的线颜色
              },
            },
            position: "right",
          },
          /*
  {
    gridIndex: 1,
    splitNumber: 3,
    axisLine: { onZero: false },
    axisTick: { show: false },
    splitLine: { show: false },
    axisLabel: { show: true },
    axisLine: {
      lineStyle: {
        color: "red"
      }
    },
    position: "right"
  }
  */
        ],
        dataZoom: [
          {
            type: "inside",
            start: 0, //100,
            end: 100, //80
          },
          {
            show: true,
            type: "slider",
            y: "75%", //底下滑轮位置
            start: 0, //50,
            end: 100,
          },
          {
            show: false,
            xAxisIndex: [0, 1],
            type: "slider",
            start: 0, //20,
            end: 100,
          },
        ],
        series: [
          {
            name: this.title,
            type: "candlestick",
            data: data0.values,
            markPoint: {
              data: [
                {
                  coord: [
                    "2023-08-01",
                    data0.values[data0.categoryData.indexOf("20211015")],
                  ],
                  symbol: "circle", // 标注的形状
                  symbolSize: 8, // 标注的大小
                  itemStyle: {
                    color: "red", // 标注的颜色
                  },
                  label: {
                    show: true,
                    position: "top",
                    formatter: "20211015", // 标注的文本
                  },
                },
              ],
            },

            markLine: {
              silent: true,
              data: [
                {
                  yAxis: 2222,
                },
              ],
            },
          },

          {
            name: "MA5",
            type: "line",
            data: calculateMA(5),
            smooth: true,
            lineStyle: {
              normal: {
                opacity: 0.5,
              },
            },
          },
          {
            name: "MA10",
            type: "line",
            data: calculateMA(10),
            smooth: true,
            lineStyle: {
              normal: {
                opacity: 0.5,
              },
            },
          },
          {
            name: "MA20",
            type: "line",
            data: calculateMA(20),
            smooth: true,
            lineStyle: {
              normal: {
                opacity: 0.5,
              },
            },
          },
          {
            name: "MA30",
            type: "line",
            data: calculateMA(30),
            smooth: true,
            lineStyle: {
              normal: {
                opacity: 0.5,
              },
            },
          },
        ],
      };

      // 进行初始化
      this.charts = echarts.init(this.$refs.echartContainer);
      this.charts.setOption(option);
    }, 500);

    },
    interval(index, data) {
      const lastTwoDigits = data.substr(-4);

      // 判断最后两个数字是否为 "01"，如果是，则显示刻度
      // return lastTwoDigits === "01" ? 1 : 0; // 返回1表示显示刻度，返回0表示不显}

      const lastFourDigits = data.substr(-4);

      // 定义要查找的后四位数字的开头
      const targetStartsWith = ["03", "06", "09", "12"];

      // 定义变量来跟踪是否已经找到匹配值
      // let foundMatch = false;

      // 循环查找，找到第一个匹配的值就返回
      for (const targetStart of targetStartsWith) {
        if (lastFourDigits.startsWith(targetStart) && !foundMatch) {
          console.log(data);
          foundMatch = true; // 标记已找到匹配值
          return data;
        }
      }

      // 如果没有找到匹配的值，返回0表示不显示刻度
      return 0;
    },
  },
};
</script>
  
  <style scoped>
</style>
  