<template>
  <div class="topbar">
    <div>
      <button v-show="back" class="back-btn" @click="onBack" />
    </div>
    <div class="title">
      {{ title }}
    </div>
    <div>
      <div v-show="finish" class="finish-btn" @click="onFinish">
        完成
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    title: {
      required: true,
      type: String,
    },
    back: {
      required: false,
      default: false,
    },
    finish: {
      required: false,
      default: false,
    },
  },
  methods: {
    onBack() {
      this.$emit('onBack');
    },
    onFinish() {
      this.$emit('onFinish');
    },
  },
}
</script>

<style scoped lang="less">
.topbar{
  font-size: large;
  padding: .5rem;
  display: flex;
  justify-content: space-between;
  div{
    min-width: 3rem;
  }
  .title{
    font-weight: bold;
    text-align: center;
  }
  .back-btn{
    font-size: 1.5rem;
    padding: .5rem 1rem;
  }
  .finish-btn{
    color: red;
  }
}
</style>
