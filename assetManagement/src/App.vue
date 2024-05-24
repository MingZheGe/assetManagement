
<template>
  <main text="gray-700 dark:gray-200">
    <router-view />
    <!-- <Footer /> -->
    <!-- <Navbar /> -->
  </main>
</template>


<style lang="less" scoped>
@import './styles/common.less';
@import './styles/common.css';
@import './styles/main.css';

main{
  font-family: "Noto Sans SC";
  background-color: @gray-bg;
  height: 100%;
  position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    overflow: hidden;
}



  #app {
    
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    overflow: hidden;
  }
  .aside{
    background-color: #545c64;
  }
  @import "assets/css/reset.less";
</style>
