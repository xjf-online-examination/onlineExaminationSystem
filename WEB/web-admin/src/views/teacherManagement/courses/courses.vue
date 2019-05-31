<template>
  <div>
    <Form ref="courseSearch" :model="searchData" inline>
      <FormItem prop="code" label="课程编号" label-position="left" class="search-flex">
        <Input type="text" v-model="searchData.code" placeholder="课程编号"/>
      </FormItem>
      <FormItem prop="name" label="课程名称" label-position="left" class="search-flex">
        <Input type="text" v-model="searchData.name" placeholder="课程名称"/>
      </FormItem>
      <FormItem prop="classId" label="所属班级" label-position="left" class="search-flex">
        <Select v-model="searchData.classId" filterable placeholder="所属班级">
          <Option v-for="item in classList" :value="item.id" :key="item.id">{{ item.name }}</Option>
        </Select>
      </FormItem>
      <FormItem>
        <Button type="primary" icon="ios-search" @click="handleSearch()">搜索</Button>
        <Button type="default" @click="handleReset('courseSearch')" class="m-l-s">重置</Button>
      </FormItem>
    </Form>
    <div class="m-b-s">
      <Button type="primary" @click="onAdd()">添加</Button>
    </div>
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
    <Modal v-model="modalVisible" :title="modalTitle" :closable="false" :mask-closable="false">
      <Form ref="courseForm" :model="course" :rules="rules">
        <FormItem prop="code" label="课程编号">
          <Input type="text" v-model="course.code" placeholder="请输入课程编号"/>
        </FormItem>
        <FormItem prop="name" label="课程名称">
          <Input type="text" v-model="course.name" placeholder="请输入课程名称"/>
        </FormItem>
        <FormItem prop="classId" label="班级">
          <Select v-model="course.classIdList" multiple filterable placeholder="请选择班级">
            <Option v-for="item in classList" :value="item.id" :key="item.id">{{ item.name }}</Option>
          </Select>
        </FormItem>
      </Form>
      <div slot="footer">
        <Button type="text" @click="cancel('courseForm')">取消</Button>
        <Button type="primary" @click="save('courseForm')">确定</Button>
      </div>
    </Modal>
    <Modal v-model="showDeleteModal" :title="'提示'" @on-ok="deleteCourse">
      <p>删除课程会把该课程对应的试题，试卷，考试信息，考试成绩删除。确认删除？</p>
    </Modal>
  </div>
</template>


<script>
import Tables from '@/components/tables';
import {
  getCourseList, addCourse, editCourse, deleteCourse, getAllClasses,
} from '@/api/teacher';
import { isEmpty } from '@/utils/validate';

export default {
  name: 'course',
  components: {
    Tables,
  },
  data() {
    return {
      columns: [
        {
          title: '序号', type: 'index', align: 'center',
        },
        {
          title: '课程编号', key: 'code', align: 'center',
        },
        {
          title: '课程名称', key: 'name', align: 'center',
        },
        {
          title: '所属班级', key: 'classNameString', align: 'center',
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
                on: {
                  click: () => {
                    this.onEdit(params.index);
                  },
                },
              }, '修改'),
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
      tableData: { list: [], count: 0 },
      searchData: {
        name: '',
        code: '',
        classId: '',
        currentPage: 1,
        pageSize: 10,
      },
      course: {
        name: '',
        code: '',
        classIdList: '',
      },
      modalVisible: false,
      modalTitle: '',
      isAdd: true,
      showDeleteModal: false,
      selectIndex: 0,
      classList: [],
      rules: {
        code: [
          { required: true, message: '课程编号不能为空', trigger: 'blur' },
        ],
        name: [
          { required: true, message: '课程姓名不能为空', trigger: 'blur' },
        ],
        classIdList: [
          {
            required: true, message: '班级不能为空', trigger: 'change', type: 'Array',
          },
        ],
      },
    };
  },
  methods: {
    handleSearch() {
      this.getCourseList(this.searchData);
    },
    handleReset(name) {
      this.$refs[name].resetFields();
      this.getCourseList(this.searchData);
    },
    onEdit(index) {
      this.modalVisible = true;
      this.modalTitle = '修改';
      this.isAdd = false;
      this.course = Object.assign({}, this.tableData.list[index]);
    },
    onDelete(index) {
      this.showDeleteModal = true;
      this.selectIndex = index;
    },
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
    save(name) {
      this.$refs[name].validate((valid) => {
        if (valid) {
          if (this.isAdd) {
            addCourse(this.course).then((res) => {
              console.log(res);
              if (res.responseCode === '201') {
                this.getCourseList();
                this.$Notice.success({ title: '添加成功' });
              } else {
                this.$Notice.success({ title: '添加失败' });
              }
              this.modalVisible = false;
            });
          } else {
            editCourse(this.course).then((res) => {
              console.log(res);
              if (res.responseCode === '201') {
                this.getCourseList();
                this.$Notice.success({ title: '修改成功' });
              } else {
                this.$Notice.success({ title: '修改失败' });
              }
              this.modalVisible = false;
            });
          }
        }
      });
    },
    cancel(name) {
      this.$refs[name].resetFields();
      this.modalVisible = false;
    },
    deleteCourse() {
      deleteCourse(this.tableData.list[this.selectIndex].id).then((res) => {
        if (res.responseCode === '204') {
          this.$Notice.success({ title: '删除成功' });
          this.getCourseList();
        } else {
          this.$Notice.error({ title: '删除失败' });
        }
      });
    },
    getCourseList() {
      getCourseList(this.searchData).then((res) => {
        if (res.responseCode === '200') {
          this.tableData = res.data;
          this.tableData.list.forEach((rowData) => {
            rowData.classNameString = '';
            rowData.classIdList = [];
            rowData.className.map((item, index) => {
              rowData.classIdList.push(item.id);
              rowData.classNameString += item.name;
              if (index !== rowData.className.length - 1) {
                rowData.classNameString += '、';
              }
            });
          });
        } else {
          this.tableData = { list: [], count: 0 };
        }
      });
    },
    getAllClasses() {
      getAllClasses().then((res) => {
        if (res.responseCode === '200') {
          this.classList = res.data;
        }
      });
    },
  },
  mounted() {
    this.getCourseList();
    this.getAllClasses();
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
