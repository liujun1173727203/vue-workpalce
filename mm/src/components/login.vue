<template>
  <div class="login">
    <div class="login-box">
      <!-- 头像区域 -->
      <img class="head-box" src="../assets/login.jpg" alt />
      <!-- 登录表单区 -->
      <el-form
        label-width="0px"
        class="login-form"
        :model="loginForm"
        :rules="LoginFormRules"
        ref="LoginFormRef"
      >
        <!-- 用户名 -->
        <el-form-item prop="account">
          <el-input
            prefix-icon="el-icon-user-solid"
            v-model="loginForm.account"
          ></el-input>
        </el-form-item>
        <!-- 密码 -->
        <el-form-item prop="password">
          <el-input
            prefix-icon="el-icon-lock"
            v-model="loginForm.password"
            type="password"
          ></el-input>
        </el-form-item>
        <!-- 按钮区域 -->
        <el-form-item class="btns">
          <el-button type="primary" @click="ValidateLogin">登录</el-button>
          <el-button type="info" @click="ResetLogin">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "login",
  data() {
    return {
      // 表单数据绑定对象
      loginForm: {
        account: "admin",
        password: "123456",
      },
      // 验证规则对象
      LoginFormRules: {
        // 验证username是否合法
        account: [
          { required: true, message: "请输入登录名称", trigger: "blur" },
          {
            min: 3,
            max: 15,
            message: "长度在 3 到 15 个字符",
            trigger: "blur",
          },
        ],
        // 验证password是否合法
        password: [
          { required: true, message: "请输入登录密码", trigger: "blur" },
          {
            min: 3,
            max: 15,
            message: "长度在 3 到 15 个字符",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    /**
     * 事件监听相关方法
     */
    // 点击重置按钮重置表单
    ResetLogin() {
      this.$refs.LoginFormRef.resetFields();
    },
    // 点击登录验证
    ValidateLogin() {
      this.$refs.LoginFormRef.validate((valid) => {
        // 判断网络请求是否发送
        if (!valid) return;
        this.$http({
          url: "/api/login",
          method: "post",
          params: {
            password: this.loginForm.password,
            username: this.loginForm.account,
          },
        })
          .then((res) => {
            if (res.data.code == 200) {
              // 登录成功
              this.$message.success("登录成功");
              this.$router.push("/userinfo");
              window.sessionStorage.setItem("localuserid", res.data.id);
              window.sessionStorage.setItem("localuserphone", res.data.phone);
              window.sessionStorage.setItem("localusername", res.data.username);
            } else {
              this.$message.error(res.data.msg);
            }
          })
          // 登录失败
          .catch((err) => {
            console.log(err);
            this.$message.error("登陆失败");
          });
      });
    },
  },
};
</script>

<style scoped>
.login {
  height: 100%;
  width: 100%;
  background-image: url("../assets/banner.jpg");
  background-size: 100%;
  position: fixed;
}
.login-box {
  height: 300px;
  width: 450px;
  background-color: rgb(144, 221, 189);
  border-radius: 3px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}
.head-box {
  width: 100px;
  height: 100px;
  border: 5px solid rgb(196, 187, 187);
  border-radius: 50%;
  padding: 3px;
  background-color: rgb(110, 104, 104);
  position: absolute;
  left: 50%;
  transform: translate(-50%, -50%);
}
.head-box img {
  width: 100%;
  height: 100%;
  border-radius: 50%;
}
.btns {
  display: flex;
  justify-content: center;
}
.login-form {
  position: absolute;
  bottom: 0;
  width: 100%;
  padding: 20px;
  box-sizing: border-box;
}
</style>
