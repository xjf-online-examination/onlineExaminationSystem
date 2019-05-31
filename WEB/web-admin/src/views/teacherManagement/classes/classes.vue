<template>
  <div>
    <Form ref="classSearch" :model="searchData" inline>
      <FormItem prop="code" label="班级编号" label-position="left" class="search-flex">
        <Input type="text" v-model="searchData.code" placeholder="班级编号"/>
      </FormItem>
      <FormItem prop="name" label="班级名称" label-position="left" class="search-flex">
        <Input type="text" v-model="searchData.name" placeholder="班级名称"/>
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
      <Form ref="classForm" :model="classes" :rules="rules">
        <FormItem prop="code" label="班级编号">
          <Input type="text" v-model="classes.code" placeholder="请输入班级编号"/>
        </FormItem>
        <FormItem prop="name" label="班级名称">
          <Input type="text" v-model="classes.name" placeholder="请输入班级名称"/>
        </FormItem>
      </Form>
      <div slot="footer">
        <Button type="text" @click="cancel('classForm')">取消</Button>
        <Button type="primary" @click="save('classForm')">确定</Button>
      </div>
    </Modal>
    <Modal v-model="showDeleteModal" :title="'提示'" @on-ok="deleteClass">
      <p>是否删除该班级，删除后无法恢复？</p>
    </Modal>
  </div>
</template>


<script>
import Tables from '@/components/tables';
import {
  getClassList, addClass, editClass, deleteClass,
} from '@/api/teacher';

export default {
  name: 'classes',
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
          title: '班级编号', key: 'code', align: 'center',
        },
        {
          title: '班级名称', key: 'name', align: 'center',
        },
        {
          title: '人数', key: 'number', align: 'center',
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
              // h('a', {
              //   props: {
              //     type: 'text',
              //     size: 'small',
              //   },
              //   style: {
              //     'margin-left': '10px',
              //   },
              //   on: {
              //     click: () => {
              //       this.onDelete(params.index);
              //     },
              //   },
              // }, '删除'),
            ]),
          ],
        }],
      tableData: {
        list: [],
        count: 0,
      },
      searchData: {
        name: '',
        code: '',
        currentPage: 1,
        pageSize: 10,
      },
      classes: {
        name: '',
        code: '',
      },
      modalVisible: false,
      modalTitle: '',
      isAdd: true,
      showDeleteModal: false,
      selectIndex: 0,
      rules: {
        code: [
          { required: true, message: '班级编号不能为空', trigger: 'blur' },
        ],
        name: [
          { required: true, message: '班级姓名不能为空', trigger: 'blur' },
        ],
      },
    };
  },
  methods: {
    handleSearch() {
      this.getClassList(this.searchData);
    },
    handleReset(name) {
      this.$refs[name].resetFields();
      this.getClassList(this.searchData);
    },
    onEdit(index) {
      this.modalVisible = true;
      this.modalTitle = '修改';
      this.isAdd = false;
      this.classes = Object.assign({}, this.tableData.list[index]);
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
    onAdd() {
      this.modalVisible = true;
      this.modalTitle = '添加';
      this.isAdd = true;
    },
    save(name) {
      this.$refs[name].validate((valid) => {
        if (valid) {
          if (this.isAdd) {
            addClass(this.classes).then((res) => {
              console.log(res);
              if (res.responseCode === '201') {
                this.getClassList();
                this.$Notice.success({ title: '添加成功' });
              } else {
                this.$Notice.success({ title: '添加失败' });
              }
              this.modalVisible = false;
            });
          } else {
            editClass(this.classes).then((res) => {
              console.log(res);
              if (res.responseCode === '201') {
                this.getClassList();
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
      this.modalVisible = false;
      this.$refs[name].resetFields();
    },
    deleteClass() {
      deleteClass(this.tableData.list[this.selectIndex].id).then((res) => {
        if (res.responseCode === '204') {
          this.$Notice.success({ title: '删除成功' });
          this.getClassList();
        } else {
          this.$Notice.error({ title: '删除失败' });
        }
      });
    },
    getClassList() {
      getClassList(this.searchData).then((res) => {
        if (res.responseCode === '200') {
          this.tableData = res.data;
        } else {
          this.tableData = { list: [], count: 0 };
        }
      });
    },
  },
  mounted() {
    this.getClassList();
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
