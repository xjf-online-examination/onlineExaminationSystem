<template>
  <div>
    <Form ref="paperSearch" :model="searchData" inline>
      <FormItem prop="code" label="试卷编号" label-position="left" class="search-flex">
        <Input type="text" v-model="searchData.code" placeholder="试卷编号"/>
      </FormItem>
      <FormItem prop="name" label="试卷名称" label-position="left" class="search-flex">
        <Input type="text" v-model="searchData.name" placeholder="试卷名称"/>
      </FormItem>
      <FormItem prop="courseCode" label="课程编号" label-position="left" class="search-flex">
        <Select v-model="searchData.courseCode" filterable placeholder="课程编号">
          <Option
            v-for="item in courseList"
            :value="item.code"
            :key="item.id"
          >{{ item.name +" : "+ item.code }}</Option>
        </Select>
      </FormItem>
      <FormItem prop="teacherName" label="出卷人" label-position="left" class="search-flex">
        <Input type="text" v-model="searchData.teacherName" placeholder="出卷人"/>
      </FormItem>
      <FormItem>
        <Button type="primary" icon="ios-search" @click="handleSearch()">搜索</Button>
        <Button type="default" @click="handleReset('paperSearch')" class="m-l-s">重置</Button>
      </FormItem>
    </Form>
    <Button type="primary" @click="onAdd()" class="m-b-s">添加</Button>
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
    <Modal v-model="modalVisible" :title="modalTitle" :closable="false" :mask-closable="false">
      <Form ref="paperForm" :model="paper" :rules="rules">
        <FormItem prop="code" label="试卷编号">
          <Input type="text" v-model="paper.code" placeholder="请输入试卷编号"/>
        </FormItem>
        <FormItem prop="name" label="试卷名称">
          <Input type="text" v-model="paper.name" placeholder="请输入试卷名称"/>
        </FormItem>
        <FormItem prop="courseCode" label="所属课程">
          <Select v-model="paper.courseCode" filterable placeholder="请选择所属课程">
            <Option v-for="item in courseList" :value="item.code" :key="item.id">{{ item.name }}</Option>
          </Select>
        </FormItem>
        <!-- <FormItem label="创建方式" prop="type">
          <RadioGroup v-model="paper.type">
            <Radio :label="1">自动</Radio>
            <Radio :label="2">手动</Radio>
          </RadioGroup>
        </FormItem>-->
      </Form>
      <div slot="footer">
        <Button type="text" @click="cancel('paperForm')">取消</Button>
        <Button type="primary" @click="save('paperForm')">确定</Button>
      </div>
    </Modal>
    <Modal v-model="showDeleteModal" :title="'提示'" @on-ok="deletePaper">
      <p>是否删除该试卷，删除后无法恢复？</p>
    </Modal>
  </div>
</template>


<script>
import Tables from '@/components/tables';
import {
  getPaperList, automaticPaper, editPaper, deletePaper, getAllCourseList, getPaperById,
} from '@/api/teacher';

export default {
  name: 'paper',
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
          title: '试卷编号', key: 'code', align: 'center',
        },
        {
          title: '试卷名称', key: 'name', align: 'center',
        },
        {
          title: '试卷题量', key: 'number', align: 'center',
        },
        {
          title: '所属课程', key: 'courseName', align: 'center',
        },
        {
          title: '出卷人', key: 'teacherName', align: 'center',
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
      tableData: {
        list: [],
        count: 0,
      },
      searchData: {
        code: '',
        name: '',
        courseCode: '',
        teacherName: '',
        currentPage: 1,
        pageSize: 10,
      },
      paper: {
        name: '',
        code: '',
        courseCode: '',
        type: '2',
      },
      modalVisible: false,
      modalTitle: '',
      isAdd: true,
      showDeleteModal: false,
      selectIndex: 0,
      courseList: [],
      rules: {
        code: [
          { required: true, message: '试卷编号不能为空', trigger: 'blur' },
        ],
        name: [
          { required: true, message: '试卷姓名不能为空', trigger: 'blur' },
        ],
        courseCode: [
          { required: true, message: '课程不能为空', trigger: 'change' },
        ],
      },
    };
  },
  methods: {
    handleSearch() {
      this.getPaperList(this.searchData);
    },
    handleReset(name) {
      this.$refs[name].resetFields();
      this.getPaperList(this.searchData);
    },
    onEdit(index) {
      // this.modalVisible = true;
      // this.modalTitle = '修改';
      // this.isAdd = false;
      // this.getPaperById(this.tableData.list[index].id).then((res) => {
      //   if (res.responseCode === '200') {
      //     this.paper = res.data;
      //   } else {
      //     this.paper = {};
      //   }
      //   this.modalVisible = true;
      // });
      this.$router.push({
        name: 'paperQuestions',
        query: {
          id: this.tableData.list[index].id,
          status: 'edit',
        },
      });
    },
    onDelete(index) {
      this.showDeleteModal = true;
      this.selectIndex = index;
    },
    onPageChange(params) {
      this.searchData.currentPage = params;
      this.getPaperList(this.searchData);
    },
    onPageSizeChange(params) {
      this.searchData.pageSize = params;
      this.getPaperList(this.searchData);
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
            if (this.paper.type === '1') {
              automaticPaper(this.paper).then((res) => {
                console.log(res);
                if (res.responseCode === '201') {
                  this.getPaperList();
                  this.$Notice.success({ title: '添加成功' });
                } else {
                  this.$Notice.error({ title: '添加失败' });
                }
                this.modalVisible = false;
              });
            } else {
              this.$router.push({
                name: 'paperQuestions',
                query: {
                  ...this.paper,
                  status: 'add',
                },
              });
              this.modalVisible = false;
              // addPaper(this.paper).then((res) => {
              //   console.log(res);
              //   if (res.responseCode === '201') {
              //     this.getPaperList();
              //     this.$Message.success('添加成功');
              //   } else {
              //     this.$Message.success('添加失败');
              //   }
              // });
            }
          } else {
            editPaper(this.course).then((res) => {
              console.log(res);
              if (res.responseCode === '201') {
                this.getPaperList();
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
    deletePaper() {
      deletePaper(this.tableData.list[this.selectIndex].id).then((res) => {
        if (res.responseCode === '204') {
          this.$Notice.success({ title: '删除成功' });
          this.getCourseList();
        } else {
          this.$Notice.error({ title: '删除失败' });
        }
      });
    },
    getPaperList() {
      getPaperList(this.searchData).then((res) => {
        if (res.responseCode === '200') {
          this.tableData = res.data;
        } else {
          this.tableData = { list: [], count: 0 };
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
    getPaperById(id) {
      return new Promise((resolve, reject) => {
        getPaperById(id).then((res) => {
          resolve(res);
        });
      });
    },
  },
  mounted() {
    this.getPaperList();
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
