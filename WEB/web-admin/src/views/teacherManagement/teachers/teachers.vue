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
    <tables
      ref="tables"
      search-place="top"
      v-model="tableData"
      :columns="columns"
      @on-delete="handleDelete"
    />
    <div class="table-pagenation m-t-s">
      <Page
        :total="100"
        show-elevator
        show-total
        show-sizer
        @on-change="onPageChange"
        @on-page-size-change="onPageSizeChange"
      />
    </div>
    <Modal v-model="modalVisible" :title="modalTitle" @on-ok="ok">
      <Form ref="classSearch" :model="teacher">
        <FormItem prop="teacherCode" label="工号">
          <Input type="text" v-model="teacher.teacherCode" placeholder="请输入工号"/>
        </FormItem>
        <FormItem prop="className" label="姓名">
          <Input type="text" v-model="teacher.teacherName" placeholder="请输入姓名"/>
        </FormItem>
      </Form>
    </Modal>
    <Modal v-model="showDeleteModal" :title="'提示'" @on-ok="deleteTeacher">
      <p>是否删除该教师，删除后无法恢复？</p>
    </Modal>
  </div>
</template>

<script>
import Tables from '@/components/tables';
import { getTeacherList, addTeacher } from '@/api/user';

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
          title: '工号', key: 'teacherNo', align: 'center',
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
      tableData: [
        {
          no: 1, classCode: '191816', className: '19级财经1班', count: 35,
        },
      ],
      searchData: {
        jobNo: '123321',
        name: 'xujiafei',
        pageSize: 10,
        currentPage: 1,
      },
      teacher: {
        teacherCode: '',
        teacherName: '',
      },
      modalVisible: false,
      modalTitle: '',
      showDeleteModal: false,
      selectIndex: 0,
    };
  },
  methods: {
    handleDelete(params) {
      console.log(params);
    },
    exportExcel() {
      this.$refs.tables.exportCsv({
        filename: `table-${(new Date()).valueOf()}.csv`,
      });
    },
    handleSearch() {
      console.log(this.searchData);
    },
    handleReset(name) {
      this.$refs[name].resetFields();
      console.log(this.searchData);
    },
    onEdit(index) {
      console.log(index);
      // TODO:
    },
    onDelete(index) {
      console.log(index);
      // TODO:
      this.showDeleteModal = true;
      this.selectIndex = index;
    },
    onResetPwd(index) {
      console.log(index);
      // TODO:
    },
    onPageChange(params) {
      console.log(params);
      // TODO:
    },
    onPageSizeChange(params) {
      console.log(params);
      // TODO:
    },
    onAdd() {
      // TODO:
      this.modalVisible = true;
      this.modalTitle = '添加教师';
    },
    ok() {
      // TODO:
      console.log(this.teacher);
    },
    deleteTeacher() {
      // TODO:
      this.tableData.splice(this.selectIndex, 1);
    },
  },
  mounted() {
    // addTeacher(this.searchData).then((res) => {
    //   // this.tableData = res.data;
    //   console.log(res);
    // });
    getTeacherList(this.searchData).then((res) => {
      // this.tableData = res.data;
      console.log(res);
    });
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
