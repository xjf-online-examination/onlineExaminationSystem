import axios from '@/libs/api.request';

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
export const addTeacher = ({
  name,
  jobNo,
}) => {
  const data = {
    name,
    jobNo,
  };
  return axios.request({
    url: 'teacher',
    data: {
      data,
    },
    method: 'post',
  });
};

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
