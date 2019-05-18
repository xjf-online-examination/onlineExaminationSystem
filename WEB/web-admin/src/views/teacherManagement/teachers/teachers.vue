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
    <Modal v-model="modalVisible" :title="modalTitle" @on-ok="save">
      <Form ref="classSearch" :model="teacher" :rule="rules">
        <FormItem prop="jobNoRules" label="工号">
          <Input type="text" v-model="teacher.jobNo" placeholder="请输入工号"/>
        </FormItem>
        <FormItem prop="name" label="姓名">
          <Input type="text" v-model="teacher.name" placeholder="请输入姓名"/>
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
import {
  getTeacherList, getTeacherByParams, addTeacher, editTeacher, deleteTeacher, resetTeacherPassword,
} from '@/api/teacher';

export default {
  name: 'teachers',
  components: {
    Tables,
  },
  props: {
    jobNoRules: {
      type: Array,
      default: () => [
        { required: true, message: '工号不能为空', trigger: 'blur' },
      ],
    },
    nameRules: {
      type: Array,
      default: () => [
        { required: true, message: '教师姓名不能为空', trigger: 'blur' },
      ],
    },
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
      tableData: [

      ],
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
      getTeacherByParams(this.searchData).then((res) => {
        console.log(res);
        if (res.responseCode === '200') {
          this.tableData = [res.data];
        }
        // TODO: 搜索不到结果时
      });
    },
    handleReset(name) {
      this.$refs[name].resetFields();
      console.log(this.searchData);
    },
    onEdit(index) {
      this.modalVisible = true;
      this.modalTitle = '修改';
      this.isAdd = false;
      this.teacher = this.tableData[index];
    },
    onDelete(index) {
      console.log(index);
      this.showDeleteModal = true;
      this.selectIndex = index;
    },
    onResetPwd(index) {
      console.log(index);
      resetTeacherPassword(this.tableData[index].id).then((res) => {
        if (res.responseCode === '201') {
          this.getTeacherList();
          this.$Message.success('密码重置成功');
        }
      });
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
      this.modalVisible = true;
      this.modalTitle = '添加';
      this.isAdd = true;
    },
    save() {
      console.log(this.teacher);
      if (this.isAdd) {
        addTeacher(this.teacher).then((res) => {
          console.log(res);
          if (res.responseCode === '201') {
            this.getTeacherList();
            this.$Message.success('添加成功');
          }
        });
      } else {
        editTeacher(this.teacher).then((res) => {
          console.log(res);
          if (res.responseCode === '201') {
            this.getTeacherList();
            this.$Message.success('修改成功');
          }
        });
      }
    },
    deleteTeacher(index) {
      // TODO:
      // this.tableData.splice(this.selectIndex, 1);
      deleteTeacher(this.tableData[this.selectIndex].id).then((res) => {
        this.getTeacherList();
      });
    },
    getTeacherList() {
      getTeacherList(this.searchData).then((res) => {
        if (res.responseCode === '200') {
          this.tableData = res.data.list;
        } else {
          this.tableData = [];
        }
      });
    },
  },
  mounted() {
    this.getTeacherList();
  },
  computed: {
    rules() {
      return {
        jobNo: this.jobNoRules,
        name: this.nameRules,
      };
    },
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
