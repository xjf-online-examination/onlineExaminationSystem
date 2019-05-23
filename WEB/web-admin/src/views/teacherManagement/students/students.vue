<template>
  <div>
    <Form ref="searchData" :model="searchData" inline>
      <FormItem label="学号" label-position="left" class="search-flex">
        <Input type="text" v-model="searchData.sno" placeholder="学号"/>
      </FormItem>
      <FormItem label="姓名" label-position="left" class="search-flex">
        <Input type="text" v-model="searchData.name" placeholder="姓名"/>
      </FormItem>
      <FormItem label="班级" label-position="left" class="search-flex">
        <Select v-model="searchData.classId" filterable placeholder="班级">
          <Option v-for="item in classList" :value="item.id" :key="item.id">{{ item.name }}</Option>
        </Select>
      </FormItem>
      <FormItem>
        <Button type="primary" icon="ios-search" @click="handleSearch()">搜索</Button>
        <Button type="default" @click="handleReset('searchData')" class="m-l-s">重置</Button>
      </FormItem>
    </Form>
    <div class="m-b-s flex-display">
      <Button type="primary" @click="onAdd()">添加</Button>
      <Upload
        class="m-l-s"
        :action="baseUrl+'/students/import'"
        :data="uploadData"
        accept=".xlsx, .xlx"
        :show-upload-list="false"
        @on-success="importSuccess()"
        @on-error="importError()"
      >
        <Button type="primary">导入</Button>
      </Upload>
      <Button type="primary" @click="onDownload()" class="m-l-s">下载模板</Button>
    </div>
    <tables ref="tables" v-model="tableData.list" :columns="columns"/>
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
    <Modal v-model="modalVisible" :title="modalTitle" @on-ok="save">
      <Form ref="classSearch" :model="student" :rule="rules">
        <FormItem prop="jobNoRules" label="学号">
          <Input type="text" v-model="student.sno" placeholder="请输入工号"/>
        </FormItem>
        <FormItem prop="name" label="姓名">
          <Input type="text" v-model="student.name" placeholder="请输入姓名"/>
        </FormItem>
        <FormItem prop="name" label="班级">
          <Select v-model="student.classId" filterable placeholder="请选择班级">
            <Option v-for="item in classList" :value="item.id" :key="item.id">{{ item.name }}</Option>
          </Select>
        </FormItem>
      </Form>
    </Modal>
    <Modal v-model="showDeleteModal" :title="'提示'" @on-ok="deleteStudent">
      <p>是否删除该学生，删除后无法恢复？</p>
    </Modal>
    <Modal v-model="showScoreModal" title="学生成绩">
      <tables ref="tables" v-model="scoreTable" :columns="scoreTableColumns"/>
    </Modal>
  </div>
</template>

<script>
import FileSaver from 'file-saver';
import Tables from '@/components/tables';
import config from '@/config';
import {
  getStudentList, addStudent, editStudent, deleteStudent, resetStudentPassword, downloadStudentTemplate, getAllClasses,
} from '@/api/teacher';
import {
  getToken,
  getUserCode,
} from '@/libs/util';

const baseUrl = process.env.NODE_ENV === 'development' ? config.baseUrl.dev : config.baseUrl.pro;


export default {
  name: 'students',
  components: {
    Tables,
  },
  data() {
    return {
      baseUrl,
      uploadData: {
        securityKey: getToken(),
        userCode: getUserCode(),
      },
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
      classList: [],
      scoreTableColumns: [
        {
          title: '序号', type: 'index', align: 'center',
        },
        {
          title: '课程', key: 'courseName', align: 'center',
        },
        {
          title: '成绩', key: 'achievement', align: 'center',
        }],
      scoreTable: [],
      showScoreModal: false,
    };
  },
  methods: {
    handleSearch() {
      console.log(this.searchData);
      this.getStudentList(this.searchData);
    },
    handleReset(name) {
      this.searchData = {
        classId: '',
        sno: '',
        name: '',
        currentPage: 1,
        pageSize: 10,
      };
      this.getStudentList(this.searchData);
    },
    onEdit(index) {
      this.modalVisible = true;
      this.modalTitle = '修改';
      this.isAdd = false;
      this.student = this.tableData.list[index];
    },
    onResetPwd(index) {
      console.log(index);
      resetStudentPassword(this.tableData.list[index].id).then((res) => {
        if (res.responseCode === '201') {
          this.getStudentList();
          this.$Message.success('密码重置成功');
        }
      });
    },
    onDelete(index) {
      this.showDeleteModal = true;
      this.selectIndex = index;
    },
    onPageChange(params) {
      this.searchData.currentPage = params;
      this.getStudentList(this.searchData);
    },
    onPageSizeChange(params) {
      this.searchData.pageSize = params;
      this.getStudentList(this.searchData);
    },
    onScore(index) {
      // this.$router.push({
      //   name: 'studentScore',
      //   query: {
      //     id: this.tableData.list[index].id,
      //   },
      // });
      this.showScoreModal = true;
      getScoreListByCode(this.tableData.list[index].sno).then((res) => {
        if (res.responseCode === '200') {
          this.scoreTable = res.data.list;
        } else {
          this.scoreTable = [];
        }
      });
    },
    onAdd() {
      this.modalVisible = true;
      this.modalTitle = '添加';
      this.isAdd = true;
    },
    save() {
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
    deleteStudent() {
      deleteStudent(this.tableData.list[this.selectIndex].id).then((res) => {
        if (res.responseCode === '204') {
          this.$Message.success('删除成功');
          this.getStudentList();
        } else {
          this.$Message.error('删除失败');
        }
      });
    },
    getStudentList() {
      getStudentList(this.searchData).then((res) => {
        if (res.responseCode === '200') {
          this.tableData = res.data;
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
    onDownload() {
      downloadStudentTemplate().then((res) => {
        const blob = new Blob([res], {
          type: 'application/octet-stream',
        });
        // const header = headers('Content-Disposition');
        const fileName = `studentTemplate_${new Date().getTime()}.xlsx`;
        FileSaver.saveAs(blob, fileName);
      });
    },
    importSuccess(res) {
      console.log(res);
      // TODO:
    },
    importError(res) {
      console.log(res);
      // TODO:
    },
  },
  mounted() {
    this.getStudentList();
    this.getAllClasses();
  },
};
</script>
<style lang="less" scoped>
.search-flex {
  display: inline-flex;
}
.flex-display {
  display: -webkit-box;
}
.table-pagenation {
  display: flex;
  justify-content: flex-end;
}
</style>
