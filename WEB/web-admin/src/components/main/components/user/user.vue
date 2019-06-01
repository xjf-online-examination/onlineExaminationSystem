<template>
  <div>
    <div class="user-avatar-dropdown">
      <Dropdown @on-click="handleClick">
        <label>{{userName}}, 你好</label>
        <Icon :size="18" type="md-arrow-dropdown"></Icon>
        <DropdownMenu slot="list">
          <DropdownItem name="resetPwd">修改密码</DropdownItem>
          <DropdownItem name="logout">退出登录</DropdownItem>
        </DropdownMenu>
      </Dropdown>
    </div>
    <Modal v-model="resetPasswordModal" title="修改密码">
      <Form ref="formCustom" :model="formCustom" :rules="ruleCustom" :label-width="80">
        <FormItem label="新密码" prop="password">
          <Input type="text" v-model="formCustom.password" number></Input>
        </FormItem>
        <FormItem label="确认密码" prop="confirmPassword">
          <Input type="password" v-model="formCustom.confirmPassword"></Input>
        </FormItem>
      </Form>
      <div slot="footer">
        <Button type="primary" @click="handleSubmit('formCustom')">确定</Button>
        <Button @click="handleReset('formCustom')" style="margin-left: 8px">取消</Button>
      </div>
    </Modal>
  </div>
</template>

<script>
import './user.less';
import { mapActions } from 'vuex';
import {
  getUserCode,
} from '@/libs/util';
import { modifyPwd } from '@/api/user';

export default {
  name: 'User',
  data() {
    const validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.formCustom.confirmPassword !== '') {
          // 对第二个密码框单独验证
          this.$refs.formCustom.validateField('confirmPassword');
        }
        callback();
      }
    };
    const validatePassCheck = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入确认密码'));
      } else if (value != this.formCustom.password) {
        callback(new Error('两次密码输入不一致!'));
      } else {
        callback();
      }
    };
    return {
      userName: this.$store.state.user.userName,
      userType: this.$store.state.user.userType,
      resetPasswordModal: false,
      formCustom: {
        password: '',
        confirmPassword: '',
      },
      ruleCustom: {
        password: [
          { validator: validatePass, trigger: 'blur' },
        ],
        confirmPassword: [
          { validator: validatePassCheck, trigger: 'blur' },
        ],
      },
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
      this.resetPasswordModal = true;
    },
    handleSubmit(name) {
      this.$refs[name].validate((valid) => {
        if (valid) {
          const obj = {
            userType: this.userType,
            username: this.userName,
            password: this.formCustom.password,
          };
          modifyPwd(obj).then((res) => {
            if (res.responseCode === '201') {
              this.$Notice.success({ title: '修改密码成功' });
            } else {
              this.$Notice.error({ title: '修改密码失败' });
            }
            this.resetPasswordModal = false;
          });
        }
      });
    },
    handleReset(name) {
      this.$refs[name].resetFields();
      this.resetPasswordModal = false;
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
