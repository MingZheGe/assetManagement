<template>
  <div  class="nav">
    <!--
    <router-link to="/" class="nav-item" >
      <div class="icon" i-carbon-align-box-top-center />
      <div>账单</div>
    </router-link>
    <router-link to="/manyBills" class="nav-item" >
      <div class="icon" i-carbon-fade />
      <div>多人账单</div>
    </router-link>
    -->
    <div class="add-btn" >
      <!--<div class="icon" :class="{ 'rote-icon': true }" i-carbon-add @click="add()"/><i class="el-icon-plus"></i>
      icon="el-icon-message"-->

      <i class="el-icon-circle-plus-outline"  style="font-size: 45px; color: white"  @click="add"></i>
      
    </div>
    <!--:class="{ 'rote-icon': true }" i-carbon-add
    <router-link to="/statistical" class="nav-item" >
      <div class="icon" i-carbon-chart-histogram />
      <div>统计</div>
    </router-link>
    <router-link to="/mine" class="nav-item" >
      <div class="icon" i-carbon-view-mode-2 />
      <div>我的</div>
    </router-link>
  -->
  </div>
</template>

<script>
export default {
  data() {
    return {
      option:true,

    };
  },
  methods: {
    go(path) {
      this.$router.push(path);
      this.showPopup = false;
    },
    add(){
      console.log("hahaha");
      this.$emit('showForm',this.option);
      console.log(this.option);
      //this.$router.push('/add')

    }
  },
};
</script>

<style scoped lang="less">
@import '../styles/common.less';


.nav{
  position: absolute;
  width:60%;
  height:20%;
  top:85%;
  left:27%;

  // height: 3rem;
  bottom: 0;
  display: flex;
  justify-content: center;
  align-items: center;

  padding-top: .2rem;
  .nav-item{
    font-size: .6rem;
    padding: 0 .2rem;
    color: @gray-0;
    display: flex;
    align-items: center;
    flex-direction: column;
    transition: color .1s;
    -webkit-tap-highlight-color:rgba(255,255,255,0);
    .icon{
      font-size: large;
      padding-bottom: .1rem;
    }
  }
  .active{
    color:@primary-1;
  }
  .add-btn, .left-btn,.right-btn{
    width: 3rem;
    height: 3rem;
    border-radius: 50%;
    transform: translateY(-1rem);
    box-shadow: 0 0 10px @gray-1;
    border: 1px solid rgb(209, 209, 209);
    background-color: rgba(247, 0, 0, 0.936);
  }
  .add-btn{
    justify-content: space-between;
    position: relative;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: @primary-0;
    font-size: 2rem;
    cursor: pointer;
    .icon{
      color:#fff;
      transition: .2s;
      width:20px;
      height:20px;
    }
    .rote-icon{
      transform: rotate(135deg);
    }
    .popup-btn{
      font-size: small;
      position: absolute;
    }
    .left-btn{
      top: -50%;
      left: -80%;
      animation: show-popup-left .2s linear;
    }
    .right-btn{
      top: -50%;
      right: -80%;
      animation: show-popup-right .2s linear;
    }
    @keyframes show-popup-left {
      0%{
        opacity: 0;
        top: 0;
        left: 0;
      }
      100%{
        top: -50%;
        left: -80%;
      }
    }
    @keyframes show-popup-right {
      0%{
        opacity: 0;
        top: 0;
        right: 0;
      }
      100%{
        top: -50%;
        right: -80%;
      }
    }
  }
}
</style>
