import axios from '@/libs/api.request';

export const getTeacherList = params => axios.request({
  url: 'get_table_data',
  method: 'POST',
  data: params,
});
export const addTeacher = params => axios.request({
  url: 'get_table_data',
  method: 'POST',
  data: params,
});

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
