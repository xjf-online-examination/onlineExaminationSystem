<template>
  <div>
    <!-- <Form ref="classSearch" :model="searchData" inline>
      <FormItem prop="classCode" label="班级编号" label-position="left" class="search-flex">
        <Input type="text" v-model="searchData.classCode" placeholder="班级编号"/>
      </FormItem>
      <FormItem prop="className" label="班级名称" label-position="left" class="search-flex">
        <Input type="text" v-model="searchData.className" placeholder="班级名称"/>
      </FormItem>
      <FormItem>
        <Button type="primary" icon="ios-search" @click="handleSearch()">搜索</Button>
        <Button type="default" @click="handleReset('classSearch')" class="m-l-s">重置</Button>
      </FormItem>
    </Form>-->
    <Button type="primary" @click="onAdd()" class="m-b-s">添加</Button>
    <tables ref="tables" search-place="top" v-model="tableData" :columns="columns"/>
    <Modal v-model="modalVisible" :title="modalTitle" :closable="false" :mask-closable="false">
      <Form ref="courseForm" :model="course" :rules="rules">
        <FormItem prop="classCourseIdList" label="请选择授课，可多选" label-position="top">
          <Select v-model="course.classCourseIdList" multiple style="width:260px">
            <OptionGroup
              :label="course.courseName"
              v-for="(course,index) in classCourseList"
              v-bind:key="index"
            >
              <Option
                v-for="classes in course.classNameVOList"
                :value="classes.id"
                :key="classes.id"
              >{{ classes.className}}</Option>
            </OptionGroup>
          </Select>
        </FormItem>
      </Form>
      <div slot="footer">
        <Button type="text" @click="cancel('courseForm')">取消</Button>
        <Button type="primary" @click="save('courseForm')">确定</Button>
      </div>
    </Modal>
    <Modal v-model="showDeleteModal" :title="'提示'" @on-ok="deleteTaught">
      <p>是否删除该教师授课，删除后无法恢复？</p>
    </Modal>
  </div>
</template>

<script>
import Tables from '@/components/tables';
import {
  getAllClassCourses, getAllCourseList, taught, addCourseForTeacher, deleteTaught,
} from '@/api/teacher';
import { isListEmpty } from '@/utils/validate';

export default {
  name: 'teacherCourse',
  components: {
    Tables,
  },
  data() {
    return {
      id: this.$route.query.id,
      columns: [
        {
          title: '序号', type: 'index', align: 'center',
        },
        {
          title: '班级名称', key: 'className', align: 'center',
        },
        {
          title: '课程名称', key: 'courseName', align: 'center',
        },
        {
          title: '课程编号', key: 'courseCode', align: 'center',
        },
        {
          title: '操作',
          key: 'handle',
          align: 'center',
          button: [
            (h, params) => h('div', [
              h('a', {
                props: {
                  type: 'text',
                  size: 'small',
                },
                style: {
                  'margin-left': '10px',
                },
                on: {
                  click: () => {
                    this.onDelete(params.index);
                  },
                },
              }, '删除'),
            ]),
          ],
        }],
      tableData: [],
      course: {
        teacherId: this.id,
        classCourseIdList: [],
      },
      courseList: [],
      modalVisible: false,
      modalTitle: '',
      showDeleteModal: false,
      selectIndex: 0,
      rules: {
        classCourseIdList: [
          { validator: isListEmpty, message: '请选择授课', trigger: 'blur' },
        ],
      },
    };
  },
  methods: {
    onPageChange(params) {
      this.searchData.currentPage = params;
      this.getCourseList(this.searchData);
    },
    onPageSizeChange(params) {
      this.searchData.pageSize = params;
      this.getCourseList(this.searchData);
    },
    onAdd() {
      this.modalVisible = true;
      this.modalTitle = '添加';
      this.isAdd = true;
    },
    onDelete(index) {
      this.showDeleteModal = true;
      this.selectIndex = index;
    },
    save(name) {
      this.$refs[name].validate((valid) => {
        if (valid) {
          addCourseForTeacher(this.course).then((res) => {
            if (res.responseCode === '201') {
              this.getCourseList();
              this.$Notice.success({ title: '添加成功' });
            } else {
              this.$Notice.error({ title: '添加失败' });
            }
            this.modalVisible = false;
          });
        }
      });
    },
    cancel(name) {
      this.$refs[name].resetFields();
      this.modalVisible = false;
    },
    deleteTaught() {
      deleteTaught(this.tableData[this.selectIndex].id).then((res) => {
        if (res.responseCode === '204') {
          this.$Notice.success({ title: '删除成功' });
          this.getCourseList();
        } else {
          this.$Notice.error({ title: '删除失败' });
        }
      });
    },
    getCourseList() {
      taught(this.id).then((res) => {
        if (res.responseCode === '200') {
          this.tableData = res.data;
        } else {
          this.tableData = [];
        }
      });
    },
    getAllClassCourses() {
      getAllClassCourses().then((res) => {
        if (res.responseCode === '200') {
          this.classCourseList = res.data;
        } else {
          this.classCourseList = [];
        }
      });
    },
    getAllCourseList() {
      getAllCourseList().then((res) => {
        if (res.responseCode === '200') {
          this.courseList = res.data;
        } else {
          this.courseList = [];
        }
      });
    },
  },
  mounted() {
    this.getCourseList();
    this.getAllClassCourses();
    this.getAllCourseList();
  },
};
</script>
<style lang="less" scoped>
.search-flex {
  display: inline-flex;
}
.table-pagenation {
  display: flex;
  justify-content: flex-end;
}
</style>
