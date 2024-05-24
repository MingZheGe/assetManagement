<template>
<!-- Form -->
<el-dialog :title="formTitle" :visible.sync="dialogFormVisible">
  <el-form
    :model="ruleForm"
    :rules="rules"
    ref="ruleForm"
    label-width="120px"
    class="demo-ruleForm"
  >
    <el-form-item label="分类" prop="region">
      <el-select v-model="ruleForm.region" placeholder="请选择分类">
        <el-option label="个人住房贷款" value="houseLoan"></el-option>
        <el-option label="个人汽车贷款" value="carLoan"></el-option>
        <el-option label="经营性贷款" value="businessLoan"></el-option>
        <el-option label="消费性贷款" value="costLoan"></el-option>
        <el-option label="其他类贷款" value="otherLoan"></el-option>
      </el-select>
    </el-form-item>

    <el-form-item label="贷款金额(元)" prop="money">
      <el-input v-model="ruleForm.money"></el-input>
    </el-form-item>
    <el-form-item label="贷款期限" prop="last">
      <el-input v-model="ruleForm.last"></el-input>
    </el-form-item>
    <el-form-item label="还款方式" prop="way">
      <el-select v-model="ruleForm.way" placeholder="请选择分类">
        <el-option label="等额本息还款" value="等额本息还款"></el-option>
        <el-option label="等额本金还款" value="等额本金还款"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="年化利率(%)" prop="perct">
      <el-input v-model="ruleForm.perct"></el-input>
    </el-form-item>

    <el-form-item label="贷款日期" required>
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

    <el-form-item label="备注" prop="desc">
      <el-input v-model="ruleForm.desc"></el-input>
    </el-form-item>
 
  </el-form>
  <div slot="footer" class="dialog-footer" style="justify-content: center;">
    <el-button @click="dialogFormVisible = false">取 消</el-button>
    <el-button type="primary" @click="submitForm('ruleForm')">确 定</el-button>
  </div>
</el-dialog>
 

</template>
    
    
    <script>
    import axios from 'axios';
    import {categoryes} from '../types/types'

export default {

  props:{
    ifVisible:Boolean,
    title:String,
    id:Number,

  },

  watch: {
    dialogFormVisible(newValue, oldValue) {
      if (newValue !== oldValue) {
        console.log("向父组件传递改变的值原来",oldValue)
        console.log("向父组件传递改变的值现在",newValue)
        this.$emit('dialog-form-visible-changed', newValue);
      }
    },
    ifVisible(newValue, oldValue) {
      if (newValue !== oldValue) {
        console.log("子组件得到值",oldValue);
        this.dialogFormVisible = this.ifVisible
      }
    }
  },

  computed: {
    formTitle() {
      // 根据父组件传递的值动态返回不同的标题
      return this.title === 'bill' ? '新建账单' : '新建贷款';
    }
  },

  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.ruleForm.type = this.type;
          
          console.log(this.ruleForm)
          axios
            .post("/addLoan", {
              id:this.id,
              loanType:this.iconType[this.ruleForm.region].name,
              type:"expend",
              annualInterestRate:this.ruleForm.perct,
              loanAmount:this.ruleForm.money,
              category:this.ruleForm.region,
              monthPayment:'000',
              repaymentMethod:this.ruleForm.way,
              totalInterestPaid:'000',
              loanDate:this.ruleForm.date1,
              remark:this.ruleForm.desc
            })
            .then((response) => {
              this.$message({
        message: "创建成功",
        type: "success",
      });
              this.dialogFormVisible = false
              this.$emit('submitForm',"true")
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },

    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.dialogFormVisible = false
    },
  },

    data() {
      return {
        iconType: categoryes,
        dialogTableVisible: false,
        dialogFormVisible: false,
        isDialogFormVisible: this.dialogFormVisible,
        form: {
          money: '',
          last:'',
          way:'',
          perct:'',
          region: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          desc: ''
        },
        formLabelWidth: '120px',

        labelPosition: "income",
      formLabelAlign: {
        name: "",
        region: "",
        type: "",
      },
      ruleForm: {
        money: '',
          last:'',
          way:'',
          perct:'',
          region: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          desc: ''
      },
      rules: {
        money: [
          {
            required: true,
            message: "请输入贷款金额",
            trigger: "blur",
          },
          /*
          {
            min: 1,
            max: 6,
            message: "输入无效请重新输入",
            trigger: "blur",
          },
          */
        ],
        region: [
          {
            required: true,
            message: "请选择贷款类型!",
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
        last: [
          {
            required: true,
            message: "请输入期限",
            trigger: "change",
          },
        ],
        way: [
          {
            required: true,
            message: "请输入还款方式",
            trigger: "change",
          },
        ],
        perct: [
          {
            required: true,
            message: "请输入年化利率",
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
        desc: [
          {
            required: false,
            message: "Please input activity form",
            trigger: "blur",
          },
        ],
      },
      };


      


      
    }
  };
</script>
    
 <style lang="less" scoped>


    .el-dialog__wrapper {
    
      overflow-y: hidden;
    }



</style>
    