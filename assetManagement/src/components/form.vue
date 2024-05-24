<template>
  <!-- Form -->
  <el-dialog :title="formTitle" :visible.sync="dialogFormVisible">
    <el-radio-group v-model="labelPosition" size="normal" @change="agreeChange">
      <el-radio-button label="income" @click="this.selectType = 'income'"
        >收入</el-radio-button
      >
      <el-radio-button label="expend" @click="this.selectType = 'expend'"
        >支出</el-radio-button
      >
    </el-radio-group>
    <!---<template   v-if="selectType === 'income'">-->
      <el-form
        :model="ruleForm"
        :rules="rules"
        ref="ruleForm"
        label-width="120px"
        class="demo-ruleForm"
      >
        <el-form-item label="分类" prop="region">
          <el-select v-model="ruleForm.region" placeholder="请选择分类">
            <el-option
              v-for="option in this.selectOptions"
              :label="option.label"
              :value="option.value"
              :key="option.value"
            ></el-option>
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
        </el-form-item>
        <el-form-item label="备注(可选填)" prop="desc">
          <el-input type="textarea" v-model="ruleForm.desc"></el-input>
        </el-form-item>
      </el-form>

    <div slot="footer" class="dialog-footer" style="justify-content: center">
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="submitForm('ruleForm')"
        >确 定</el-button
      >
    </div>
  </el-dialog>
</template>
    
    
    <script>
import axios from "axios";

export default {
  props: {
    ifVisible: Boolean,
    title: String,
    id: Number,
  },

  watch: {
    selectType: {
      handler(newSortord, oldSortord) {
        console.log(`sortord changed from ${oldSortord} to ${newSortord}`);
      },
      immediate: true, // This will make the watch handler run immediately on component creation.
    },
    dialogFormVisible(newValue, oldValue) {
      if (newValue !== oldValue) {
        console.log("向父组件传递改变的值原来", oldValue);
        console.log("向父组件传递改变的值现在", newValue);
        this.$emit("dialog-form-visible-changed", newValue);
      }
    },
    ifVisible(newValue, oldValue) {
      if (newValue !== oldValue) {
        console.log("子组件得到值", oldValue);
        this.dialogFormVisible = this.ifVisible;
      }
    },
  },

  computed: {
    formTitle() {
      // 根据父组件传递的值动态返回不同的标题
      return this.title === "bill" ? "新建账单" : "新建贷款";
    },
  },

  methods: {
    agreeChange:function(val){
      if(val=="income")
      this.selectOptions = this.incomeOptions
      if(val=="expend")
      this.selectOptions = this.expendOptions
    },

    submitForm(formName) {



      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.ruleForm.type = this.labelPosition;
          console.log(this.ruleForm);

          axios
            .post("/insert", {
              id: this.id,
              type: this.ruleForm.type,
              amount: this.ruleForm.name,
              date: this.ruleForm.date1,
              category: this.ruleForm.region,
              remark: this.ruleForm.desc,
            })
            .then((response) => {
              this.$message({
                message: "创建成功",
                type: "success",
              });
              this.dialogFormVisible = false;
            });
          this.$emit("submitForm", "true");
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },

    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.dialogFormVisible = false;
    },
  },

  data() {
    return {
      selectType:"income",
      selectOptions: [
      { label: "工资收入", value: "wages" },
        { label: "利息收入", value: "interest" },
        { label: "奖金收入", value: "bonus" },
      ],
      incomeOptions: [
        { label: "工资收入", value: "wages" },
        { label: "利息收入", value: "interest" },
        { label: "奖金收入", value: "bonus" },
      ],
      expendOptions: [
        { label: "餐饮", value: "food" },
        { label: "生活缴费", value: "life" },
        { label: "购物", value: "shopping" },
        { label: "交通", value: "transport" },
        { label: "其他", value: "other" },
      ],
      dialogTableVisible: false,
      dialogFormVisible: false,
      isDialogFormVisible: this.dialogFormVisible,
      form: {
        name: "",
        region: "",
        date1: "",
        date2: "",
        delivery: false,
        type: [],
        resource: "",
        desc: "",
      },
      formLabelWidth: "120px",

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
};
</script>
    
 <style lang="less" scoped>
.buttonGroup {
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
.el-radio-group {
  margin-left: 330px;
  margin-bottom: 20px;
}
</style>
    