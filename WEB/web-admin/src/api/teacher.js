/*
 * @Author: xujiafei
 * @Date: 2019-05-17 16:48:58
 * @Last Modified by: xujiafei
 * @Last Modified time: 2019-05-19 03:07:05
 */
import axios from '@/libs/api.request';

/** ***********教师管理模块 begin ************* */
export const getTeacherList = ({
  name,
  jobNo,
  currentPage,
  pageSize,
}) => {
  const data = {
    name,
    jobNo,
    currentPage,
    pageSize,
  };
  return axios.request({
    url: 'teacher/list',
    data: {
      data,
    },
    method: 'POST',
  });
};
export const getTeacherByParams = ({
  name,
  jobNo,
}) => {
  const data = {
    name,
    jobNo,
  };
  return axios.request({
    url: 'teacher/get',
    data,
    method: 'POST',
  });
};
export const addTeacher = ({
  name,
  jobNo,
}) => {
  const data = {
    name,
    jobNo,
  };
  return axios.request({
    url: 'teacher/save',
    data: {
      data,
    },
    method: 'post',
  });
};
export const editTeacher = ({
  name,
  jobNo,
  id,
}) => {
  const data = {
    name,
    jobNo,
    id,
  };
  return axios.request({
    url: 'teacher/modify',
    data: {
      data,
    },
    method: 'post',
  });
};
export const deleteTeacher = id => axios.request({
  url: 'teacher/delete',
  data: {
    data: id,
  },
  method: 'post',
});
export const resetTeacherPassword = id => axios.request({
  url: 'teacher/resetPassword',
  data: {
    data: id,
  },
  method: 'post',
});
/** ***********教师管理模块 end ************* */


/** ***********学生管理模块 begin ************* */
export const getStudentList = ({
  name,
  sno,
  classId,
  currentPage,
  pageSize,
}) => {
  const data = {
    name,
    sno,
    classId,
    currentPage,
    pageSize,
  };
  return axios.request({
    url: 'students/list',
    data: {
      data,
    },
    method: 'POST',
  });
};
export const addStudent = ({
  name,
  sno,
  classId,
}) => {
  const data = {
    name,
    sno,
    classId,
  };
  return axios.request({
    url: 'students/save',
    data: {
      data,
    },
    method: 'post',
  });
};
export const editStudent = ({
  id,
  name,
  sno,
  classId,
}) => {
  const data = {
    id,
    name,
    sno,
    classId,
  };
  return axios.request({
    url: 'students/modify',
    data: {
      data,
    },
    method: 'post',
  });
};
export const deleteStudent = id => axios.request({
  url: 'students/delete',
  data: {
    data: id,
  },
  method: 'post',
});
export const resetStudentPassword = id => axios.request({
  url: 'students/resetPassword',
  data: {
    data: id,
  },
  method: 'post',
});

/** ***********学生管理模块 end ************* */
export const getDragList = () => axios.request({
  url: 'get_drag_list',
  method: 'get',
});

export const errorReq = () => axios.request({
  url: 'error_url',
  method: 'post',
});

export const saveErrorLogger = info => axios.request({
  url: 'save_error_logger',
  data: info,
  method: 'post',
});

export const uploadImg = formData => axios.request({
  url: 'image/upload',
  data: formData,
});

export const getOrgData = () => axios.request({
  url: 'get_org_data',
  method: 'get',
});

export const getTreeSelectData = () => axios.request({
  url: 'get_tree_select_data',
  method: 'get',
});
