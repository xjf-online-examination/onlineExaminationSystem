/*
 * @Author: xujiafei
 * @Date: 2019-05-17 16:48:58
 * @Last Modified by: xujiafei
 * @Last Modified time: 2019-05-31 00:46:29
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
export const taught = id => axios.request({
  url: 'teacher/taught',
  data: {
    data: id,
  },
  method: 'post',
});
export const addCourseForTeacher = ({
  ...course
}) => axios.request({
  url: 'teacher/saveTaught',
  data: {
    data: course,
  },
  method: 'post',
});
export const deleteTaught = id => axios.request({
  url: 'teacher/deleteTaught',
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
export const downloadStudentTemplate = () => axios.request({
  url: 'students/download',
  method: 'get',
  responseType: 'blob',
});
export const importStudents = () => axios.request({
  url: 'students/import',
  method: 'post',
});
export const getScoreListByCode = sno => axios.request({
  url: 'students/achievement',
  data: {
    data: sno,
  },
  method: 'post',
});
/** ***********学生管理模块 end ************* */

/** ***********班级管理模块 start *********** */
export const getClassList = ({
  name,
  code,
  currentPage,
  pageSize,
}) => {
  const data = {
    name,
    code,
    currentPage,
    pageSize,
  };
  return axios.request({
    url: 'class/list',
    data: {
      data,
    },
    method: 'POST',
  });
};
export const addClass = ({
  name,
  code,
}) => {
  const data = {
    name,
    code,
  };
  return axios.request({
    url: 'class/save',
    data: {
      data,
    },
    method: 'post',
  });
};
export const editClass = ({
  id,
  name,
  code,
}) => {
  const data = {
    id,
    name,
    code,
  };
  return axios.request({
    url: 'class/modify',
    data: {
      data,
    },
    method: 'post',
  });
};
export const deleteClass = id => axios.request({
  url: 'class/delete',
  data: {
    data: id,
  },
  method: 'post',
});
export const getAllClasses = () => axios.request({
  url: 'class/listAll',
  data: {},
  method: 'post',
});
/** ***********班级管理模块 end ************* */


/** ***********课程管理模块 start *********** */
export const getCourseList = ({
  name,
  code,
  classId,
  currentPage,
  pageSize,
}) => {
  const data = {
    name,
    code,
    classId,
    currentPage,
    pageSize,
  };
  return axios.request({
    url: 'course/list',
    data: {
      data,
    },
    method: 'POST',
  });
};
export const addCourse = data => axios.request({
  url: 'course/save',
  data: {
    data,
  },
  method: 'post',
});
export const editCourse = ({
  id,
  classIdList,
  name,
  code,
}) => {
  const data = {
    id,
    classIdList,
    name,
    code,
  };
  return axios.request({
    url: 'course/modify',
    data: {
      data,
    },
    method: 'post',
  });
};

export const deleteCourse = id => axios.request({
  url: 'course/delete',
  data: {
    data: id,
  },
  method: 'post',
});
export const getAllClassCourses = () => axios.request({
  url: 'course/listClassCourse',
  data: {},
  method: 'post',
});
export const getAllCourseList = () => axios.request({
  url: 'course/listCourse',
  data: {},
  method: 'post',
});
/** ***********班级管理模块 end ************* */


/** ***********试卷管理模块 start *********** */
export const getPaperList = data => axios.request({
  url: 'examPaper/list',
  data: {
    data,
  },
  method: 'POST',
});
export const automaticPaper = data => axios.request({
  url: 'examPaper/automatic',
  data: {
    data,
  },
  method: 'post',
});
export const editPaper = data => axios.request({
  url: 'examPaper/modify',
  data: {
    data,
  },
  method: 'post',
});
export const deletePaper = id => axios.request({
  url: 'examPaper/delete',
  data: {
    data: id,
  },
  method: 'post',
});
export const getPaperById = id => axios.request({
  url: 'examPaper/get',
  data: {
    data: id,
  },
  method: 'post',
});
export const getPaperDetailForTeacher = id => axios.request({
  url: 'examPaper/teacherExamPaper',
  data: {
    data: id,
  },
  method: 'post',
});
/** ***********试卷管理模块 end ************* */

/** ***********考试安排模块 start *********** */
export const getSchedulerList = data => axios.request({
  url: 'examSchedule/list',
  data: {
    data,
  },
  method: 'POST',
});
export const addScheduler = data => axios.request({
  url: 'examSchedule/save',
  data: {
    data,
  },
  method: 'post',
});
export const editScheduler = data => axios.request({
  url: 'examSchedule/modify',
  data: {
    data,
  },
  method: 'post',
});
export const deleteScheduler = id => axios.request({
  url: 'examSchedule/delete',
  data: {
    data: id,
  },
  method: 'post',
});
/** ***********考试安排模块 end ************* */

/** ***********试题模块模块 end ************* */
export const getQuestionList = data => axios.request({
  url: 'examQuestions/list',
  data: {
    data,
  },
  method: 'POST',
});
export const listPage = data => axios.request({
  url: 'examQuestions/listPage',
  data: {
    data,
  },
  method: 'POST',
});
export const getQuestionById = id => axios.request({
  url: 'examQuestions/get',
  data: {
    data: id,
  },
  method: 'POST',
});
export const addQuestion = data => axios.request({
  url: 'examQuestions/save',
  data: {
    data,
  },
  method: 'post',
});
export const editQuestion = data => axios.request({
  url: 'examQuestions/modify',
  data: {
    data,
  },
  method: 'post',
});
export const deleteQuestion = id => axios.request({
  url: 'examQuestions/delete',
  data: {
    data: id,
  },
  method: 'post',
});
export const listSubjectOne = () => axios.request({
  url: 'examQuestions/listSubjectOne',
  method: 'get',
});
export const downloadQuestionsTemplate = () => axios.request({
  url: 'examQuestions/download',
  method: 'get',
  responseType: 'blob',
});
/** ***********试题模块模块 end ************* */
