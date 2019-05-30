/*
 * @Author: xujiafei
 * @Date: 2019-05-22 21:10:36
 * @Last Modified by: HuKai
 * @Last Modified time: 2019-05-30 15:53:20
 */

import axios from '@/libs/api.request';

/** ***********查看成绩模块 begin ************* */
export const getScoreList = code =>
  axios.request({
    url: 'students/achievement',
    data: {
      data: code
    },
    method: 'POST'
  });

/** ***********查看成绩模块 end ************* */

/** ***********考试安排模块 begin ************* */
export const examScheduleList = data =>
  axios.request({
    url: 'examSchedule/StudentExamSchedule',
    data: { data },
    method: 'POST'
  });

  export const saveExamAnswer = data =>
  axios.request({
    url: '',
    data: { data },
    method: 'POST'
  });
/** ***********考试安排模块 end ************* */
