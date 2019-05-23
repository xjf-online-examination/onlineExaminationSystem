<template>
  <div class="user-avatar-dropdown">
    <label>{{userName}},你好</label>
    <a class="m-l-md" @click="logout">退出</a>
  </div>
</template>

<script>
import './user.less';
import { mapActions } from 'vuex';

export default {
  name: 'User',
  props: {
    userName: {
      type: String,
      default: '',
    },
  },
  methods: {
    ...mapActions([
      'handleLogOut',
    ]),
    logout() {
      this.handleLogOut().then(() => {
        this.$router.push({
          name: 'login',
        });
      });
    },
  },
};
</script>
