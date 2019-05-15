<template>
  <Form ref="loginForm" :model="form" :rules="rules" @keydown.enter.native="handleSubmit">
    <div class="login-form-title">
      <label>用户登录</label>
      <div class="split-border"></div>
    </div>
    <FormItem prop="userName">
      <Input v-model="form.userName" placeholder class="login-form-input">
        <span slot="prefix">
          <img src="@/assets/images/icon-user.png" class="login-form-icon">
        </span>
      </Input>
    </FormItem>
    <FormItem prop="password">
      <Input type="password" v-model="form.password" placeholder>
        <span slot="prefix">
          <img src="@/assets/images/icon-password.png" class="login-form-icon">
        </span>
      </Input>
    </FormItem>
    <FormItem prop="role" class="login-form-role">
      <RadioGroup v-model="form.role" size="large">
        <Radio label="教师"></Radio>
        <Radio label="学生"></Radio>
      </RadioGroup>
    </FormItem>
    <FormItem>
      <Button @click="handleSubmit" type="primary" class="login-form-btn">登录</Button>
    </FormItem>
  </Form>
</template>
<script>
export default {
  name: 'LoginForm',
  props: {
    userNameRules: {
      type: Array,
      default: () => [
        { required: true, message: '账号不能为空', trigger: 'blur' },
      ],
    },
    passwordRules: {
      type: Array,
      default: () => [
        { required: true, message: '密码不能为空', trigger: 'blur' },
      ],
    },
  },
  data() {
    return {
      form: {
        userName: 'super_admin',
        password: '',
        role: '教师',
      },
    };
  },
  computed: {
    rules() {
      return {
        userName: this.userNameRules,
        password: this.passwordRules,
      };
    },
  },
  methods: {
    handleSubmit() {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          this.$emit('on-success-valid', {
            userName: this.form.userName,
            password: this.form.password,
          });
        }
      });
    },
  },
};
</script>
<style lang="less">
.login-form-title {
  font-size: 22px;
  color: #20a7fa;
  width: 100%;
  padding: 10%;
  display: flex;
  flex-direction: column;
  align-items: center;
  .split-border {
    width: 280px;
    height: 2px;
    border-radius: 50%;
    background: #20a7fa;
    margin-top: 5px;
  }
}
.login-form-icon {
  width: 20px;
  height: 20px;
  margin-top: 6px;
}

.login-form-btn {
  width: 100%;
  font-size: 20px;
  color: #fff;
  background: #20a7fa;
  border-color: #20a7fa;
}
.login-form-role {
  margin-top: -10px;
  margin-bottom: 15px;
  .ivu-radio-inner:after {
    background-color: #20a7fa;
  }
  .ivu-radio-checked .ivu-radio-inner {
    border-color: #20a7fa;
  }
}
</style>
