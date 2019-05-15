<style lang="less">
@import "./login.less";
</style>

<template>
  <!-- <div class="login">
    <div class="login-con">
      <Card icon="log-in" title="欢迎登录" :bordered="false">
        <div class="form-con">
          <login-form @on-success-valid="handleSubmit"></login-form>
        </div>
      </Card>
    </div>
  </div>-->
  <div class="login">
    <div class="app-title">赤峰学院经济与管理学院</div>
    <div class="login-block">
      <div class="login-box">
        <login-form @on-success-valid="handleSubmit"></login-form>
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
  methods: {
    ...mapActions([
      'handleLogin',
      'getUserInfo',
    ]),
    handleSubmit({ userName, password }) {
      this.handleLogin({ userName, password }).then((res) => {
        this.getUserInfo().then((res) => {
          this.$router.push({
            name: this.$config.homeName,
          });
        });
      });
    },
  },
};
</script>

<style>
</style>
