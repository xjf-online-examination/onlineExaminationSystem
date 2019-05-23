/*
 * @Author: xujiafei
 * @Date: 2019-05-22 21:10:36
 * @Last Modified by: xujiafei
 * @Last Modified time: 2019-05-23 10:54:40
 */

import axios from '@/libs/api.request';

/** ***********查看成绩模块 begin ************* */
export const getScoreList = code => axios.request({
  url: 'students/achievement',
  data: {
    data: code,
  },
  method: 'POST',
});


/** ***********查看成绩模块 end ************* */
