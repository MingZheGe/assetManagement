
<template>
  <div class="box">
    <el-aside width="200px" class="aside">
      <Aside></Aside>
    </el-aside>
    <el-main>
      <Topbar title="新建账单" style="color: white" />
      <div style="padding: 1rem">
        <div class="count-card2">
          <el-radio-group v-model="labelPosition" size="normal" class="buttonGroup">
            <el-radio-button  label="income">收入</el-radio-button>
            <el-radio-button  label="expend">支出</el-radio-button>
          </el-radio-group>
          <div style="margin: 20px"></div>
          <div v-show="labelPosition === 'income'">
            <Form :type="this.labelPosition" />
          </div>
          <div v-show="labelPosition === 'expend'">
            <Form :type="this.labelPosition" />
          </div>
        </div>
      </div>
    </el-main>
  </div>
</template>
    
    
    <script>
import Aside from "../components/Aside2.vue";
import Topbar from "../components/Topbar.vue";
import Form from "../components/form.vue";
import axios from "axios";

export default {
  components: {
    Aside,
    Topbar,
    Form,
  },
  name: "Corporate-Contacts",
  data() {
    return {
      labelPosition: "income",
    };
  },
  methods: {},
};
</script>
    
    <style lang="less" scoped>
@import "../styles/common.less";
@import "../styles/common.css";
@import "../styles/main.css";
.box {
  background: linear-gradient(30deg,#baebe7, #14c4b8);
  background-size: 100% 15rem;
  background-repeat: no-repeat;
  padding-bottom: 2rem;
  box-sizing: border-box;
  display: flex;
}

.buttonGroup{
  display: flex; 
  justify-content: center; 
  margin-top: 20px;
  width: 3rem;
        border: none; /* 去除边框 */
        background: transparent; /* 去除背景色 */

  .el-radio-button__inner {
        width: 3rem;
        border: none; /* 去除边框 */
        background: transparent; /* 去除背景色 */
      }

  

}

.count-card2 {
  display: flex;
  flex-direction: column; /* 垂直方向布局 */
  align-items: center; /* 内部内容垂直居中 */
  justify-content: center; /* 内部内容水平居中 */
  padding: 2rem;
  background-color: #50e2d6;
  padding: 1rem;
  margin-top: 2rem;
  border-radius: 0.4rem;
  font-size: @font-size-sm;
  box-shadow: 0 2px 10px #429691;
  border: 1px solid rgb(107, 151, 134);
}

</style>
    