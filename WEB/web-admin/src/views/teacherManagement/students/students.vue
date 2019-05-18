<template>
  <div>
    <Form ref="classSearch" :model="searchData" inline>
      <FormItem prop="classCode" label="学号" label-position="left" class="search-flex">
        <Input type="text" v-model="searchData.studentNo" placeholder="学号"/>
      </FormItem>
      <FormItem prop="className" label="姓名" label-position="left" class="search-flex">
        <Input type="text" v-model="searchData.name" placeholder="姓名"/>
      </FormItem>
      <FormItem prop="className" label="班级" label-position="left" class="search-flex">
        <Input type="text" v-model="searchData.className" placeholder="班级"/>
      </FormItem>
      <FormItem>
        <Button type="primary" icon="ios-search" @click="handleSearch()">搜索</Button>
        <Button type="default" @click="handleReset('classSearch')" class="m-l-s">重置</Button>
      </FormItem>
    </Form>
    <div class="m-b-s">
      <Button type="primary" @click="onAdd()">添加</Button>
      <Button type="primary" @click="onImport()" class="m-l-s">导入</Button>
      <Button type="primary" @click="onDownload()" class="m-l-s">下载模板</Button>
    </div>
    <tables ref="tables" v-model="tableData.list" :columns="columns" @on-delete="handleDelete"/>
    <div class="table-pagenation m-t-s">
      <Page
        :total="tableData.count"
        show-elevator
        show-total
        show-sizer
        @on-change="onPageChange"
        @on-page-size-change="onPageSizeChange"
      />
    </div>
    <Modal v-model="modalVisible" :title="modalTitle" @on-ok="save">
      <Form ref="classSearch" :model="student" :rule="rules">
        <FormItem prop="jobNoRules" label="学号">
          <Input type="text" v-model="student.sno" placeholder="请输入工号"/>
        </FormItem>
        <FormItem prop="name" label="姓名">
          <Input type="text" v-model="student.name" placeholder="请输入姓名"/>
        </FormItem>
        <FormItem prop="name" label="班级">
          <Input type="text" v-model="student.classId" placeholder="请输入姓名"/>
        </FormItem>
      </Form>
    </Modal>
    <Modal v-model="showDeleteModal" :title="'提示'" @on-ok="deleteStudent">
      <p>是否删除该学生，删除后无法恢复？</p>
    </Modal>
  </div>
</template>

<script>
import Tables from '@/components/tables';
import {
  getStudentList, addStudent, editStudent, deleteStudent, resetStudentPassword,
} from '@/api/teacher';

export default {
  name: 'students',
  components: {
    Tables,
  },
  data () {
    return {
      columns: [
        {
          title: '序号', type: 'index', align: 'center',
        },
        {
          title: '学号', key: 'sno', align: 'center',
        },
        {
          title: '姓名', key: 'name', align: 'center',
        },
        {
          title: '班级', key: 'className', align: 'center',
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
                    this.onScore(params.index);
                  },
                },
              }, '成绩'),
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
              }, '修改密码'),
            ]),
          ],
        }],
      tableData: {
        list: [],
        count: 0,
      },
      searchData: {
        classId: '',
        sno: '',
        name: '',
        currentPage: 1,
        pageSize: 10,
      },
      student: {
        sno: '',
        name: '',
        classId: '',
      },
      modalVisible: false,
      modalTitle: '',
      isAdd: true,
      showDeleteModal: false,
      selectIndex: 0,
    };
  },
  methods: {
    handleDelete (params) {
      console.log(params);
    },
    exportExcel () {
      this.$refs.tables.exportCsv({
        filename: `table-${(new Date()).valueOf()}.csv`,
      });
    },
    handleSearch () {
      console.log(this.searchData);
    },
    handleReset (name) {
      this.$refs[name].resetFields();
      console.log(this.searchData);
    },
    onEdit (index) {
      this.modalVisible = true;
      this.modalTitle = '修改';
      this.isAdd = false;
      this.student = this.tableData.list[index];
    },
    onResetPwd (index) {
      console.log(index);
      resetStudentPassword(this.tableData.list[index].id).then((res) => {
        if (res.responseCode === '201') {
          this.getStudentList();
          this.$Message.success('密码重置成功');
        }
      });
    },
    onDelete (index) {
      this.showDeleteModal = true;
      this.selectIndex = index;
    },
    onPageChange (params) {
      console.log(params);
      /* todo */
    },
    onPageSizeChange (params) {
      console.log(params);
      /* todo */
    },
    onAdd () {
      this.modalVisible = true;
      this.modalTitle = '添加';
      this.isAdd = true;
    },
    save () {
      console.log(this.teacher);
      if (this.isAdd) {
        addStudent(this.student).then((res) => {
          console.log(res);
          if (res.responseCode === '201') {
            this.getStudentList();
            this.$Message.success('添加成功');
          } else {
            this.$Message.success('添加失败');
          }
        });
      } else {
        editStudent(this.student).then((res) => {
          console.log(res);
          if (res.responseCode === '201') {
            this.getStudentList();
            this.$Message.success('修改成功');
          } else {
            this.$Message.success('修改失败');
          }
        });
      }
    },
    deleteStudent () {
      deleteStudent(this.tableData.list[this.selectIndex].id).then((res) => {
        if (res.responseCode === '204') {
          this.$Message.success('删除成功');
          this.getStudentList();
        } else {
          this.$Message.error('删除失败');
        }
      });
    },
    getStudentList () {
      getStudentList(this.searchData).then((res) => {
        if (res.responseCode === '200') {
          this.tableData = res.data;
        } else {
          this.tableData = { list: [], count: 0 };
        }
      });
    },
  },
  mounted () {
    this.getStudentList();
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
