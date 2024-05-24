<template>
  <el-form
    :model="ruleForm"
    :rules="rules"
    ref="ruleForm"
    label-width="120px"
    class="demo-ruleForm"
  >
    <el-form-item label="分类" prop="region">
      <el-select v-model="ruleForm.region" placeholder="请选择分类">
        <el-option label="餐饮" value="餐饮"></el-option>
        <el-option label="生活缴费" value="生活缴费"></el-option>
        <el-option label="购物" value="购物"></el-option>
        <el-option label="交通" value="交通"></el-option>
        <el-option label="其他" value="其他"></el-option>
      </el-select>
    </el-form-item>

    <el-form-item label="金额" prop="name">
      <el-input v-model="ruleForm.name"></el-input>
    </el-form-item>

    <el-form-item label="时间" required>
      <el-col :span="11">
        <el-form-item prop="date1">
          <el-date-picker
            type="date"
            placeholder="请选择日期"
            v-model="ruleForm.date1"
            style="width: 100%"
          ></el-date-picker>
        </el-form-item>
      </el-col>
      <el-col class="line" :span="2">-</el-col>
      <el-col :span="11">
        <el-form-item prop="date2">
          <el-time-picker
            placeholder="请选择时间"
            v-model="ruleForm.date2"
            style="width: 100%"
          ></el-time-picker>
        </el-form-item>
      </el-col>
    </el-form-item>
    <el-form-item label="备注(可选填)" prop="desc">
      <el-input type="textarea" v-model="ruleForm.desc"></el-input>
    </el-form-item>

    <div style="display: flex; justify-content: center; margin-top: 20px">
      <el-button type="primary" @click="submitForm('ruleForm')">确认</el-button>
      <el-button @click="resetForm('ruleForm')">清除</el-button>
    </div>
  </el-form>
</template>


<script>
import axios from "axios";
export default {
  props: {
    type: String,
  },
  data() {
    return {
      labelPosition: "income",
      formLabelAlign: {
        name: "",
        region: "",
        type: "",
      },
      ruleForm: {
        name: "",
        region: "",
        date1: "",
        date2: "",
        delivery: false,
        type: [],
        resource: "",
        desc: "",
      },
      rules: {
        name: [
          {
            required: true,
            message: "请输入账单金额",
            trigger: "blur",
          },
          {
            min: 1,
            max: 6,
            message: "输入无效请重新输入",
            trigger: "blur",
          },
        ],
        region: [
          {
            required: true,
            message: "请选择账单的分类!",
            trigger: "change",
          },
        ],
        date1: [
          {
            type: "date",
            required: true,
            message: "请输入日期",
            trigger: "change",
          },
        ],
        date2: [
          {
            type: "date",
            required: true,
            message: "请输入时间",
            trigger: "change",
          },
        ],
        type: [
          {
            type: "array",
            required: true,
            message: "Please select at least one activity type",
            trigger: "change",
          },
        ],
        resource: [
          {
            required: true,
            message: "Please select activity resource",
            trigger: "change",
          },
        ],
        desc: [
          {
            required: false,
            message: "Please input activity form",
            trigger: "blur",
          },
        ],
      },
    };
  },

  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.ruleForm.type = this.type;
          axios
            .post("/insert", {
              type: this.ruleForm.type,
              amount: this.ruleForm.name,
              date: this.ruleForm.date1,
              category: this.ruleForm.region,
              remark: this.ruleForm.desc,
            })
            .then((response) => {
              console.log(response);
              alert("创建成功");
              this.$router.push("/bill");
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },

    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  },
};
</script>