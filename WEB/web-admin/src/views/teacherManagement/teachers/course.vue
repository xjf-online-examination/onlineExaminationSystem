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
    <tables ref="tables" search-place="top" v-model="tableData.list" :columns="columns"/>
    <div class="table-pagenation m-t-s" v-if="tableData.count>0">
      <Page
        :total="tableData.count"
        show-elevator
        show-total
        show-sizer
        @on-change="onPageChange"
        @on-page-size-change="onPageSizeChange"
      />
    </div>
    <Modal v-model="modalVisible" :title="modalTitle" @on-ok="ok">
      <Form ref="classSearch" :model="course">
        <FormItem prop="teacherCode" label="请选择授课，可多选">
          <Select v-model="course.course" multiple style="width:260px">
            <Option
              v-for="item in courseList"
              :value="item"
              :key="item"
            >{{ item.className+" : "+item.courseName }}</Option>
          </Select>
        </FormItem>
      </Form>
    </Modal>
  </div>
</template>

<script>
import Tables from '@/components/tables';
import {
  getAllCourses, taught, addCourseForTeacher,
} from '@/api/teacher';

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
      tableData: {
        list: [],
        count: 0,
      },
      course: {
        course: {},
      },
      courseList: [],
      modalVisible: false,
      modalTitle: '',
      showDeleteModal: false,
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
    save() {
      addCourseForTeacher(this.course).then((res) => {
        if (res.responseCode === '201') {
          this.getTeacherList();
          this.$Message.success('添加成功');
        }
      });
    },
    getCourseList() {
      taught(this.id).then((res) => {
        if (res.responseCode === '200') {
          this.tableData = res.data;
        } else {
          this.tableData = {
            list: [],
            count: 0,
          };
        }
      });
    },
    getAllCourses() {
      getAllCourses(this.id).then((res) => {
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
    this.getAllCourses();
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
