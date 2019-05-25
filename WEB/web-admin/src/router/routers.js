import Main from '@/components/main';

/**
 * iview-admin中meta除了原生参数外可配置的参数:
 * meta: {
 *  title: { String|Number|Function }
 *         显示在侧边栏、面包屑和标签栏的文字
 *         使用'{{ 多语言字段 }}'形式结合多语言使用，例子看多语言的路由配置;
 *         可以传入一个回调函数，参数是当前路由对象，例子看动态路由和带参路由
 *  hideInBread: (false) 设为true后此级路由将不会出现在面包屑中，示例看QQ群路由配置
 *  hideInMenu: (false) 设为true后在左侧菜单不会显示该页面选项
 *  notCache: (false) 设为true后页面在切换标签后不会缓存，如果需要缓存，无需设置这个字段，而且需要设置页面组件name属性和路由配置的name一致
 *  access: (null) 可访问该页面的权限数组，当前路由设置的权限会影响子路由
 *  icon: (-) 该页面在左侧菜单、面包屑和标签导航处显示的图标，如果是自定义图标，需要在图标名称前加下划线'_'
 *  beforeCloseName: (-) 设置该字段，则在关闭当前tab页时会去'@/router/before-close.js'里寻找该字段名对应的方法，作为关闭前的钩子函数
 * }
 */

export default [{
  path: '/login',
  name: 'login',
  meta: {
    title: 'Login - 登录',
    hideInMenu: true,
  },
  component: () => import('@/views/login/login.vue'),
},
{
  path: '/',
  name: '_home',
  redirect: '/home',
  component: Main,
  children: [{
    path: '/home',
    name: 'home',
    meta: {
      hideInMenu: false,
      title: '首页',
      notCache: true,
      icon: 'md-home',
    },
    component: () => import('@/views/home/home'),
  }],
},
{
  path: '/teacher',
  name: 'teacherManagement',
  meta: {
    hideInBread: true,
    access: ['teacher'],
  },
  component: Main,
  children: [{
    path: 'teacher',
    name: 'teacher',
    meta: {
      icon: 'ios-people',
      title: '教师管理',
    },
    component: () => import('@/views/teacherManagement/teachers/teachers'),
  }, {
    path: 'course',
    name: 'teacherCourse',
    meta: {
      icon: 'ios-people',
      title: '所授课程',
      hideInMenu: true,
      notCache: true,
    },
    component: () => import('@/views/teacherManagement/teachers/course'),
  }],
},
{
  path: '/student',
  name: 'studentManagement',
  meta: {
    hideInBread: true,
    access: ['teacher'],
  },
  component: Main,
  children: [{
    path: 'student',
    name: 'student',
    meta: {
      icon: 'ios-school',
      title: '学生管理',
    },
    component: () => import('@/views/teacherManagement/students/students'),
  }, {
    path: 'score',
    name: 'studentScore',
    meta: {
      icon: 'ios-school',
      title: '学生成绩',
      hideInMenu: true,
      notCache: true,
    },
    component: () => import('@/views/teacherManagement/students/score'),
  }],
},
{
  path: '/class',
  name: 'classManagement',
  meta: {
    hideInBread: true,
    access: ['teacher'],
  },
  component: Main,
  children: [{
    path: 'class',
    name: 'class',
    meta: {
      icon: 'ios-bug',
      title: '班级管理',
    },
    component: () => import('@/views/teacherManagement/classes/classes'),
  }],
},
{
  path: '/course',
  name: 'courseManagement',
  meta: {
    hideInBread: true,
    access: ['teacher'],
  },
  component: Main,
  children: [{
    path: 'course',
    name: 'course',
    meta: {
      icon: 'ios-book',
      title: '课程管理',
    },
    component: () => import('@/views/teacherManagement/courses/courses'),
  }],
},
{
  path: '/paper',
  name: 'paperManagement',
  meta: {
    hideInBread: true,
    access: ['teacher'],
  },
  component: Main,
  children: [{
    path: 'paper',
    name: 'paper',
    meta: {
      icon: 'ios-paper',
      title: '试卷管理',
    },
    component: () => import('@/views/teacherManagement/paper/paper'),
  },
  {
    path: 'question',
    name: 'paperQuestions',
    meta: {
      icon: 'ios-paper',
      title: '试卷试题',
      hideInMenu: true,
      notCache: true,
    },
    component: () => import('@/views/teacherManagement/paper/question'),
  },
  ],
},
{
  path: '/question',
  name: 'questionManagement',
  meta: {
    hideInBread: true,
    access: ['teacher'],
  },
  component: Main,
  children: [{
    path: 'question',
    name: 'question',
    meta: {
      icon: 'ios-list-box',
      title: '试题管理',
    },
    component: () => import('@/views/teacherManagement/question/question'),
  }],
},
{
  path: '/scheduler',
  name: 'schedulerManagement',
  meta: {
    hideInBread: true,
    access: ['teacher'],
  },
  component: Main,
  children: [{
    path: 'scheduler',
    name: 'scheduler',
    meta: {
      icon: 'ios-list-box',
      title: '考试安排',
    },
    component: () => import('@/views/teacherManagement/scheduler/scheduler'),
  }],
},
// {
//   path: '/mark',
//   name: 'markManagement',
//   meta: {
//     hideInBread: false,
//     access: ['teacher'],
//   },
//   component: Main,
//   children: [{
//     path: 'mark',
//     name: 'mark',
//     meta: {
//       icon: 'md-checkbox-outline',
//       title: '阅卷',
//     },
//     component: () => import('@/views/teacherManagement/mark/mark'),
//   }],
// },
// {
//   path: '/report',
//   name: 'reportManagement',
//   meta: {
//     hideInBread: false,
//     access: ['teacher'],
//   },
//   component: Main,
//   children: [{
//     path: 'report',
//     name: 'report',
//     meta: {
//       icon: 'ios-stats-outline',
//       title: '报表',
//     },
//     component: () => import('@/views/teacherManagement/report/report'),
//   }],
// },
{
  path: '/achievement',
  name: 'achievementManagement',
  meta: {
    hideInBread: false,
    access: ['student'],
  },
  component: Main,
  children: [{
    path: 'achievement',
    name: 'achievement',
    meta: {
      icon: 'md-checkbox-outline',
      title: '查看成绩',
    },
    component: () => import('@/views/studentManagement/achievement/achievement'),
  }],
},
{
  path: '/testScheduler',
  name: 'testScheduler',
  meta: {
    hideInBread: false,
    access: ['student'],
  },
  component: Main,
  children: [{
    path: 'test',
    name: 'test',
    meta: {
      icon: 'ios-stats-outline',
      title: '考试安排',
    },
    component: () => import('@/views/studentManagement/test/test'),
  }],
},
];
