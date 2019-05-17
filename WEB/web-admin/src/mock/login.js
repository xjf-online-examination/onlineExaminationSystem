import {
  getParams,
} from '@/libs/util';

const USER_MAP = {
  teacher: {
    name: '',
    user_id: '1',
    access: [teacher],
    user_type: 2,
    token: '',
    avatar: 'https://file.iviewui.com/dist/a0e88e83800f138b94d2414621bd9704.png',
  },
  student: {
    name: '',
    user_id: '2',
    access: ['student'],
    user_type: 1,
    token: '',
    avatar: 'https://avatars0.githubusercontent.com/u/20942571?s=460&v=4',
  },
};

export const getUserInfo = (req) => {
  const params = getParams(req.url);
  const role = params == 2 ? 'teacher' : 'student';
  return USER_MAP[role];
};
