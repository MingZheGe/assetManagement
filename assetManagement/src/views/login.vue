<template>
  <div class="login">
    <div class="logo">
      <span>V-login</span>
    </div>
    <p>stay foolish,stay hungry</p>
    <div class="login-input">
      <el-form
        :model="ruleForm"
        status-icon
        :rules="rules"
        ref="ruleForm"
        class="demo-ruleForm"
      >
        <el-form-item label="" prop="email">
          <el-input
            v-model="ruleForm.email"
            clearable
            placeholder="邮箱"
          ></el-input>
        </el-form-item>
        <el-form-item label="" prop="pass">
          <el-input
            type="password"
            v-model="ruleForm.pass"
            autocomplete="off"
            placeholder="密码"
            clearable
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">
            <loading v-if="load"></loading>
            <span v-else>登录</span>
          </el-button>
        </el-form-item>
      </el-form>
      <p class="register">
        <router-link to="/register">还没有账号？注册</router-link>
      </p>
    </div>
  </div>
</template>
 
 <script>
import loading from "../components/loading";
import axios from "axios";
export default {
  components: { loading },
  data() {
    var checkEmail = (rule, value, callback) => {
      var reg = new RegExp(
        "^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"
      );
      if (!value) {
        return callback(new Error("邮箱不能为空"));
      }
      setTimeout(() => {
        if (!reg.test(value)) {
          callback(new Error("请输入正确的邮箱"));
        } else {
          callback();
        }
      }, 1000);
    };
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        if (this.ruleForm.checkPass !== "") {
          this.$refs.ruleForm.validateField("checkPass");
        }
        callback();
      }
    };
    return {
      load: false,
      ruleForm: {
        pass: "",
        email: "",
      },
      rules: {
        pass: [{ validator: '', trigger: "blur" }],
        email: [{ validator: '', trigger: "blur" }],
      },
    };
  },
  methods: {
    submitForm(formName) {
      this.load = true;
      this.$refs[formName].validate((valid) => {
                axios
                  .post("/login", {
                    username:this.ruleForm.email,
                    password:this.ruleForm.pass
                  }) //event_id是envent_id
                  .then((response) => {
                    if(response.data.message == 'success')
                    this.$router.push('/bill')
                  else
                  {
                    this.$message({
                message: "邮箱或密码错误",
                type: "error",
              });
                  }

                  });
   
       
      });
    },
  },
};
</script>
 
 <style lang='scss'>
.login {
  width: 100%;
  height: 100%;
  background: rgb(48, 49, 62);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  color: #fff;
  .logo {
    color: rgb(3, 174, 189);
    font-size: 30px;
    // font-style: italic;
    font-weight: bold;
    margin-bottom: 20px;
    img {
      width: 74px;
      vertical-align: middle;
      margin-right: 10px;
    }
  }
  .login-input {
    width: 290px;
    padding: 10px;
    margin-top: 10px;
    .el-input {
      // margin-bottom: 20px;
      .el-input__inner {
        border-radius: 30px !important;
        text-align: center;
      }
    }
    .el-button {
      border-radius: 30px !important;
      width: 270px;
      background: rgb(3, 174, 189);
      color: #fff;
      border: 0;
    }
    .register {
      text-align: right;
      margin: 10px;
      a {
        color: #fff;
      }
    }
  }
}
</style>