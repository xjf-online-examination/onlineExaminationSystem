/*
 * @Author: xujiafei
 * @Date: 2019-05-16 14:40:30
 * @Last Modified by: xujiafei
 * @Last Modified time: 2019-05-17 12:58:13
 */
import axios from '@/libs/api.request';
// 登录
const USER_MAP = {
  teacher: {
    name: '',
    access: ['teacher'],
    token: '',
    user_type: 2,
    avatar: './assets/images/teacher.png',
  },
  student: {
    name: '',
    access: ['student'],
    token: '',
    user_type: 1,
    avatar: './assets/images/student.png',
  },
};
export const login = ({
  userName,
  password,
  userType,
}) => {
  const data = {
    username: userName,
    password,
    userType: userType === '教师' ? 2 : 1,
  };
  return axios.request({
    url: 'user/login',
    data: {
      data,
    },
    method: 'post',
  });
};
export const logout = token => axios.request({
  url: 'user/logout',
  data: {
    securityKey: token,
  },
  method: 'post',
});
export const getUserInfo = (userType) => {
  const role = userType === 1 ? 'teacher' : 'student';
  return new Promise((resolve, reject) => {
    resolve(USER_MAP[role]);
  });
};
// export const getUserInfo = userType => axios.request({
//   url: 'get_info',
//   params: {
//     userType,
//   },
//   method: 'get',
// });
