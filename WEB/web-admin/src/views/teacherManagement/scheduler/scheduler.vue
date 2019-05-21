<template>
  <div>
    <Form ref="courseSearch" :model="searchData" inline>
      <FormItem prop="code" label="试卷编号" label-position="left" class="search-flex">
        <Input type="text" v-model="searchData.examPaperCode" placeholder="试卷编号"/>
      </FormItem>
      <FormItem prop="name" label="考试状态" label-position="left" class="search-flex">
        <Input type="text" v-model="searchData.status" placeholder="考试状态"/>
      </FormItem>
      <FormItem>
        <Button type="primary" icon="ios-search" @click="handleSearch()">搜索</Button>
        <Button type="default" @click="handleReset('classSearch')" class="m-l-s">重置</Button>
      </FormItem>
    </Form>
    <div class="m-b-s">
      <Button type="primary" @click="onAdd()">添加</Button>
    </div>
    <tables ref="tables" search-place="top" v-model="tableData.list" :columns="columns"/>
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
      <Form ref="classSearch" :model="scheduler" :rule="rules">
        <!-- <FormItem prop="jobNoRules" label="考试课程">
          <Select v-model="scheduler.courseId" filterable placeholder="请选择考试课程">
            <Option v-for="item in courseList" :value="item.id" :key="item.id">{{ item.name }}</Option>
          </Select>
        </FormItem>-->
        <FormItem prop="name" label="考试安排名称">
          <Input type="text" v-model="scheduler.title" placeholder="请输入课程名称"/>
        </FormItem>
        <FormItem prop="name" label="试卷编号">
          <Input type="text" v-model="scheduler.examPaperCode" placeholder="请输入课程名称"/>
        </FormItem>
        <FormItem prop="name" label="考试开始时间">
          <DatePicker
            type="datetime"
            v-model="scheduler.startTime"
            placeholder="请选择考试开始时间"
            style="width: 200px"
          ></DatePicker>
        </FormItem>
        <FormItem prop="name" label="考试时长">
          <InputNumber v-model="scheduler.duration"></InputNumber>
        </FormItem>
      </Form>
    </Modal>
    <Modal v-model="showDeleteModal" :title="'提示'" @on-ok="deleteScheduler">
      <p>是否删除该考试安排，删除后无法恢复？</p>
    </Modal>
  </div>
</template>


<script>
import Tables from '@/components/tables';
import {
  getSchedulerList, addScheduler, editScheduler, deleteScheduler, getAllCourses,
} from '@/api/teacher';

export default {
  name: 'scheduler',
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
          title: '课程名称', key: 'code', align: 'center',
        },
        {
          title: '考试时间', key: 'startTime', align: 'center',
        },
        {
          title: '考试时长', key: 'duration', align: 'center',
        },
        {
          title: '试卷编号', type: 'examPaperCode', align: 'center',
        },
        {
          title: '试卷名称', key: 'examPaperName', align: 'center',
        },
        {
          title: '考试班级', key: 'className', align: 'center',
        },
        {
          title: '考试状态', key: 'status', align: 'center',
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
        examPaperCode: '',
        stauts: '',
        currentPage: 1,
        pageSize: 10,
      },
      scheduler: {
        title: '',
        examPaperCode: '',
        startTime: '',
        duration: '',
      },
      modalVisible: false,
      modalTitle: '',
      isAdd: true,
      showDeleteModal: false,
      selectIndex: 0,
      classList: [],
    };
  },
  methods: {
    handleSearch() {
      this.getSchedulerList(this.searchData);
    },
    handleReset(name) {
      this.searchData = {
        examPaperCode: '',
        stauts: '',
        currentPage: 1,
        pageSize: 10,
      };
      this.getSchedulerList(this.searchData);
    },
    onEdit(index) {
      this.modalVisible = true;
      this.modalTitle = '修改';
      this.isAdd = false;
      this.course = this.tableData.list[index];
    },
    onDelete(index) {
      this.showDeleteModal = true;
      this.selectIndex = index;
    },
    onPageChange(params) {
      this.searchData.currentPage = params;
      this.getSchedulerList(this.searchData);
    },
    onPageSizeChange(params) {
      this.searchData.pageSize = params;
      this.getSchedulerList(this.searchData);
    },
    onAdd() {
      this.modalVisible = true;
      this.modalTitle = '添加';
      this.isAdd = true;
    },
    save() {
      if (this.isAdd) {
        addScheduler(this.scheduler).then((res) => {
          console.log(res);
          if (res.responseCode === '201') {
            this.getSchedulerList();
            this.$Message.success('添加成功');
          } else {
            this.$Message.success('添加失败');
          }
        });
      } else {
        editScheduler(this.scheduler).then((res) => {
          console.log(res);
          if (res.responseCode === '201') {
            this.getSchedulerList();
            this.$Message.success('修改成功');
          } else {
            this.$Message.success('修改失败');
          }
        });
      }
    },
    deleteScheduler() {
      deleteScheduler(this.tableData.list[this.selectIndex].id).then((res) => {
        if (res.responseCode === '204') {
          this.$Message.success('删除成功');
          this.getSchedulerList();
        } else {
          this.$Message.error('删除失败');
        }
      });
    },
    getSchedulerList() {
      getSchedulerList(this.searchData).then((res) => {
        if (res.responseCode === '200') {
          this.tableData = res.data;
        } else {
          this.tableData = { list: [], count: 0 };
        }
      });
    },
    getAllCourses() {
      getAllCourses().then((res) => {
        if (res.responseCode === '200') {
          this.courseList = res.data;
        }
      });
    },
  },
  mounted() {
    this.getSchedulerList();
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
