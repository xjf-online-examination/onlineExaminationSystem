<template>
  <div class="user-avatar-dropdown">
    <Dropdown @on-click="handleClick">
      <label>{{userName}}, 你好</label>
      <Icon :size="18" type="md-arrow-dropdown"></Icon>
      <DropdownMenu slot="list">
        <DropdownItem name="message">
          修改密码
          <Badge style="margin-left: 10px" :count="messageUnreadCount"></Badge>
        </DropdownItem>
        <DropdownItem name="logout">退出登录</DropdownItem>
      </DropdownMenu>
    </Dropdown>
  </div>
</template>

<script>
import './user.less';
import { mapActions } from 'vuex';

export default {
  name: 'User',
  data() {
    return {
      userName: this.$store.state.user.userName,
    };
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
    resetPwd() {
      this.$router.push({
        name: 'message_page',
      });
    },
    handleClick(name) {
      switch (name) {
        case 'logout': this.logout();
          break;
        case 'resetPwd': this.resetPwd();
          break;
        default: break;
      }
    },
  },
};
</script>
