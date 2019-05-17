<style lang="less">
@import "./login.less";
</style>

<template>
  <div class="login">
    <div class="app-title">赤峰学院经济与管理学院</div>
    <div class="login-block">
      <div class="login-box">
        <login-form @on-success-valid="handleSubmit" :err-msg="errMsg"></login-form>
      </div>
    </div>
  </div>
</template>

<script>
import LoginForm from '_c/login-form';
import { mapActions } from 'vuex';

export default {
  components: {
    LoginForm,
  },
  data() {
    return {
      errMsg: '',
    };
  },
  methods: {
    ...mapActions([
      'handleLogin',
      'getUserInfo',
    ]),
    handleSubmit({ userName, password, userType }) {
      this.handleLogin({ userName, password, userType }).then((res) => {
        console.log(res);
        if (res.responseCode === '404') {
          this.errMsg = '用户名或密码错误';
        } else {
          this.getUserInfo().then((res) => {
            this.$router.push({
              name: this.$config.homeName,
            });
          });
        }
      });
    },
  },
};
</script>

<style>
</style>
