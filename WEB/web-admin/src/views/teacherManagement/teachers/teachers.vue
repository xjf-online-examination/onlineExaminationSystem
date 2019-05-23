<template>
  <div>
    <Form ref="classSearch" :model="searchData" inline>
      <FormItem prop="jobNo" label="工号" label-position="left" class="search-flex">
        <Input type="text" v-model="searchData.jobNo" placeholder="工号"/>
      </FormItem>
      <FormItem prop="name" label="教师姓名" label-position="left" class="search-flex">
        <Input type="text" v-model="searchData.name" placeholder="教师姓名"/>
      </FormItem>
      <FormItem>
        <Button type="primary" icon="ios-search" @click="handleSearch()">搜索</Button>
        <Button type="default" @click="handleReset('classSearch')" class="m-l-s">重置</Button>
      </FormItem>
    </Form>
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
    <Modal v-model="modalVisible" :title="modalTitle" :closable="false" :mask-closable="false">
      <Form ref="teacherForm" :model="teacher" :rules="rules">
        <FormItem prop="jobNo" label="工号">
          <Input type="text" v-model="teacher.jobNo" placeholder="请输入工号"/>
        </FormItem>
        <FormItem prop="name" label="姓名">
          <Input type="text" v-model="teacher.name" placeholder="请输入姓名"/>
        </FormItem>
      </Form>
      <div slot="footer">
        <Button type="text" @click="cancel('teacherForm')">取消</Button>
        <Button type="primary" @click="save('teacherForm')">确定</Button>
      </div>
    </Modal>
    <Modal v-model="showDeleteModal" :title="'提示'" @on-ok="deleteTeacher">
      <p>是否删除该教师，删除后无法恢复？</p>
    </Modal>
  </div>
</template>

<script>
import Tables from '@/components/tables';
import {
  getTeacherList, addTeacher, editTeacher, deleteTeacher, resetTeacherPassword,
} from '@/api/teacher';
import { isNumber } from '@/utils/validate';

export default {
  name: 'teachers',
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
          title: '工号', key: 'jobNo', align: 'center',
        },
        {
          title: '教师姓名', key: 'name', align: 'center',
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
                    this.onViewCourse(params.index);
                  },
                },
              }, '课程'),
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
                    this.onResetPwd(params.index);
                  },
                },
              }, '重置密码'),
            ]),
          ],
        }],
      tableData: {
        list: [],
        count: 0,
      },
      searchData: {
        jobNo: '',
        name: '',
        pageSize: 10,
        currentPage: 1,
      },
      teacher: {
        jobNo: '',
        name: '',
      },
      modalVisible: false,
      modalTitle: '',
      isAdd: true,
      showDeleteModal: false,
      selectIndex: 0,
      rules: {
        jobNo: [
          { required: true, message: '工号不能为空', trigger: 'blur' },
          { validator: isNumber, message: '工号只能由数字组成', trigger: 'blur' },
        ],
        name: [
          { required: true, message: '教师姓名不能为空', trigger: 'blur' },
        ],
      },
    };
  },
  methods: {
    handleSearch() {
      this.getTeacherList(this.searchData);
    },
    handleReset(name) {
      this.$refs[name].resetFields();
      this.getTeacherList(this.searchData);
    },
    onEdit(index) {
      this.modalVisible = true;
      this.modalTitle = '修改';
      this.isAdd = false;
      this.teacher = Object.assign({}, this.tableData.list[index]);
    },
    onDelete(index) {
      console.log(index);
      this.showDeleteModal = true;
      this.selectIndex = index;
    },
    onResetPwd(index) {
      console.log(index);
      resetTeacherPassword(this.tableData.list[index].id).then((res) => {
        if (res.responseCode === '201') {
          this.getTeacherList();
          this.$Notice.success({ title: '密码重置成功' });
        }
      });
    },
    onViewCourse(index) {
      this.$router.push({
        name: 'teacherCourse',
        query: {
          id: this.tableData.list[index].id,
        },
      });
    },
    onPageChange(params) {
      this.searchData.currentPage = params;
      this.getTeacherList(this.searchData);
    },
    onPageSizeChange(params) {
      this.searchData.pageSize = params;
      this.getTeacherList(this.searchData);
    },
    onAdd() {
      this.modalTitle = '添加';
      this.isAdd = true;
      this.modalVisible = true;
    },
    save(name) {
      console.info('name', name);
      this.$refs[name].validate((valid) => {
        if (valid) {
          if (this.isAdd) {
            addTeacher(this.teacher).then((res) => {
              console.log(res);
              if (res.responseCode === '201') {
                this.getTeacherList();
                this.$Notice.success({ title: '添加成功' });
              } else {
                this.$Notice.error({ title: '添加失败' });
              }
              this.modalVisible = false;
            });
          } else {
            editTeacher(this.teacher).then((res) => {
              console.log(res);
              if (res.responseCode === '201') {
                this.getTeacherList();
                this.$Notice.success({ title: '修改成功' });
              } else {
                this.$Notice.error({ title: '修改失败' });
              }
              this.modalVisible = false;
            });
          }
        }
      });
    },
    cancel(name) {
      this.modalVisible = false;
      this.$refs[name].resetFields();
    },
    deleteTeacher() {
      deleteTeacher(this.tableData.list[this.selectIndex].id).then((res) => {
        if (res.responseCode === '204') {
          this.$Notice.success({ title: '删除成功' });
          this.getTeacherList();
        } else {
          this.$Notice.error({ title: '删除失败' });
        }
      });
    },
    getTeacherList() {
      getTeacherList(this.searchData).then((res) => {
        if (res.responseCode === '200') {
          this.tableData = res.data;
        } else {
          this.tableData = [];
        }
      });
    },
  },
  mounted() {
    this.getTeacherList();
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
