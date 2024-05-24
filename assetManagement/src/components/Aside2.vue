<template>
  <div>
    <!---demo-->

    <el-menu
      :default-active="defaultActive"
      class="el-menu-vertical"
      @select="select"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
    >
      <h3 align="center">个人资产助手</h3>

      <el-menu-item
        index="1"
        :class="{ 'active-menu-item': activeMenuItem === '1' }"
      >
        <i class="el-icon-location"></i>
        <span>账单记录管理</span>
      </el-menu-item>

      <el-menu-item
        index="2"
        :class="{ 'active-menu-item': activeMenuItem === '2' }"
      >
        <i class="el-icon-menu"></i>
        <span>贷款记录管理</span>
      </el-menu-item>
<!--
      <el-menu-item
        index="3"
        :class="{ 'active-menu-item': activeMenuItem === '3' }"
      >
        <i class="el-icon-document"></i>
        <span>理财产品记录管理</span>
      </el-menu-item>
    -->
    <el-submenu index="3">
    <template slot="title">
          <i class="el-icon-location"></i>
          <span>理财产品记录管理</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="5">股票记录管理</el-menu-item>
          <el-menu-item index="6">基金记录管理</el-menu-item>
        </el-menu-item-group>
        </el-submenu>

      <el-menu-item
        index="4"
        :class="{ 'active-menu-item': activeMenuItem === '4' }"
      >
        <i class="el-icon-setting"></i>
        <span>数据可视化和报告</span>
      </el-menu-item>
 
  
    </el-menu>


    <!-- <div style="text-align: center" class="A">
      <el-button class="B" @click.native="updata_Database" :loading="status"
        >更新数据库</el-button
      >
    </div>
    -->
  
  </div>
</template>
  
  
    
  <style lang="less">
.no-link-style {
  text-decoration: none;
  color: inherit; /* 继承父元素的颜色 */
  cursor: pointer; /* 添加指针样式以表示可点击 */
}
/*
.el-button.is-loading {
  position: fixed;
    left:50px;
    bottom:0px;
}
*/

.A {
  .B {
    background: #30313e;
    border-color: #30313e;
    color: #ffffff;
    //   position: fixed;
    //    left:50px;
    bottom: 0px;
    //width:199.2px;
  }
}
.el-menu {
  //去掉白边
  border-right: none !important;
}

.el-menu-vertical:not(.el-menu--collapse) {
  width: 200px;
}
.el-menu-vertical {
  padding-top: 20px;
  height: 100vh;
  h3 {
    color: #fff;
    padding-bottom: 10px;
    letter-spacing: 0.2px;
  }
  span {
    //   min-width: 199.2px;
    vertical-align: middle;
  }
  .el-menu-item {
    vertical-align: 15%;
  }
  .el-submenu .el-menu-item {
    height: 45px;
    line-height: 45px;
    // min-width:199.2px;
  }
  .el-menu-item-group__title {
    padding: 0 0 0 20px;
  }
  .el-menu {
    background-color: #f8f8f8;
    border: none;
  }
  .active-menu-item {
    background-color: red; /* 设置你想要的颜色 */
    /* 可以添加其他样式，如字体加粗 */
  }
}
</style>
    
  <script>
export default {
  props: ["menuItem"],
  data() {
    return {
      isCollapse: false,
      status: false,
      code: null,
      msg: null,
      activeMenuItem: null, // 用于标记当前激活的菜单项
      defaultActive: "",
    };
  },
  watch: {
    menuItem(newValue) {
      // 监听activeMenuItem的变化
      console.log("我现在值是", newValue);
      // 更新defaultActive的值为activeMenuItem的值
      this.defaultActive = newValue;
      activeMenuItem = newValue;
    },
  },
  methods: {
    select(key, keyPath) {
      //this.defaultActive = key;
      if (key == 1) {
        //this.activeMenuItem = 1
        this.$router.push({ path: "/bill" });
      }
      if (key == 2) {
        //this.activeMenuItem = 2
        this.$router.push({ path: "/loan" });
      }

      if (key == 5) {
        //this.activeMenuItem = 3
        this.$router.push({ path: "/stock" });
      }
      if (key == 6) {
        //this.activeMenuItem = 3
        this.$router.push({ path: "/fund" });
      }

      if (key == 4) {
        //this.activeMenuItem = 4
        this.$router.push({ path: "/statistic" });
      }
    },
  },
};
</script>