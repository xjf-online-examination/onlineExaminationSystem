<template>
  <div>
    <Form ref="schedulerSearch" :model="searchData" inline>
      <FormItem prop="examPaperCode" label="试卷编号" label-position="left" class="search-flex">
        <Input type="text" v-model="searchData.examPaperCode" placeholder="试卷编号"/>
      </FormItem>
      <FormItem prop="status" label="考试状态" label-position="left" class="search-flex">
        <Select v-model="searchData.status" style="width:150px">
          <Option value="1">未开始</Option>
          <Option value="2">进行中</Option>
          <Option value="3">已结束</Option>
        </Select>
      </FormItem>
      <FormItem>
        <Button type="primary" icon="ios-search" @click="handleSearch()">搜索</Button>
        <Button type="default" @click="handleReset('schedulerSearch')" class="m-l-s">重置</Button>
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
      <Form ref="schedulerForm" :model="scheduler" :rules="rules">
        <FormItem prop="title" label="考试安排名称">
          <Input type="text" v-model="scheduler.title" placeholder="请输入考试安排名称"/>
        </FormItem>
        <FormItem prop="examPaperCode" label="试卷编号">
          <Input type="text" v-model="scheduler.examPaperCode" placeholder="请输入试卷编号"/>
        </FormItem>
        <FormItem prop="time" label="考试开始时间">
          <DatePicker
            type="datetime"
            v-model="scheduler.time"
            placeholder="请选择考试开始时间"
            style="width: 200px"
            @on-change="setStartTime"
            :options="options"
          ></DatePicker>
        </FormItem>
        <FormItem prop="duration" label="考试时长(分)">
          <InputNumber v-model="scheduler.duration"></InputNumber>
        </FormItem>
      </Form>
      <div slot="footer">
        <Button type="text" @click="cancel('schedulerForm')">取消</Button>
        <Button type="primary" @click="save('schedulerForm')">确定</Button>
      </div>
    </Modal>
    <Modal v-model="showDeleteModal" :title="'提示'" @on-ok="deleteScheduler">
      <p>是否删除该考试安排，删除后无法恢复？</p>
    </Modal>
  </div>
</template>


<script>
import Tables from '@/components/tables';
import {
  getSchedulerList, addScheduler, editScheduler, deleteScheduler,
} from '@/api/teacher';

export default {
  name: 'scheduler',
  components: {
    Tables,
  },
  data() {
    return {
      options: {
        disabledDate(date) {
          return date && date.valueOf() < Date.now() - 86400000;
        },
      },
      columns: [
        {
          title: '序号', type: 'index', align: 'center',
        },
        {
          title: '考试安排名称', key: 'examScheduleName', align: 'center',
        },
        {
          title: '考试时间', key: 'startTime', align: 'center',
        },
        {
          title: '考试时长', key: 'duration', align: 'center',
        },
        {
          title: '试卷编号', key: 'examPaperCode', align: 'center',
        },
        {
          title: '试卷名称', key: 'examPaperName', align: 'center',
        },
        {
          title: '考试班级', key: 'className', align: 'center',
        },
        {
          title: '考试状态', key: 'statusStr', align: 'center',
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
        duration: 0,
        time: '',
      },
      modalVisible: false,
      modalTitle: '',
      isAdd: true,
      showDeleteModal: false,
      selectIndex: 0,
      classList: [],
      rules: {
        title: [
          { required: true, message: '考试安排名称不能为空', trigger: 'blur' },
        ],
        examPaperCode: [
          { required: true, message: '试卷编号不能为空', trigger: 'blur' },
        ],
        time: [
          {
            required: true, message: '考试时间不能为空', trigger: 'blur', type: 'date',
          },
        ],
        duration: [{
          required: true, message: '考试时长不能为空', trigger: 'blur', type: 'number',
        },
        ],
      },
    };
  },
  methods: {
    handleSearch() {
      this.getSchedulerList(this.searchData);
    },
    handleReset(name) {
      this.$refs[name].resetFields();
      this.getSchedulerList(this.searchData);
    },
    onEdit(index) {
      this.modalVisible = true;
      this.modalTitle = '修改';
      this.isAdd = false;
      this.scheduler = Object.assign({}, this.tableData.list[index]);
      this.scheduler.time = new Date(this.scheduler.startTime);
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
    save(name) {
      this.$refs[name].validate((valid) => {
        if (valid) {
          if (this.isAdd) {
            addScheduler(this.scheduler).then((res) => {
              console.log(res);
              if (res.responseCode === '201') {
                this.getSchedulerList();
                this.$Notice.success({ title: '添加成功' });
              } else {
                this.$Notice.error({ title: '添加失败' });
              }
              this.$refs[name].resetFields();
              this.modalVisible = false;
            });
          } else {
            console.log(this.scheduler);
            editScheduler(this.scheduler).then((res) => {
              console.log(res);
              if (res.responseCode === '201') {
                this.getSchedulerList();
                this.$Notice.success({ title: '修改成功' });
              } else {
                this.$Notice.error({ title: '修改失败' });
              }
              this.$refs[name].resetFields();
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
    deleteScheduler() {
      deleteScheduler(this.tableData.list[this.selectIndex].id).then((res) => {
        if (res.responseCode === '204') {
          this.$Notice.success({ title: '删除成功' });
          this.getSchedulerList();
        } else {
          this.$Notice.error({ title: '删除失败' });
        }
      });
    },
    getSchedulerList() {
      getSchedulerList(this.searchData).then((res) => {
        if (res.responseCode === '200') {
          this.tableData = res.data;
          this.tableData.list.map((rowData) => {
            rowData.title = rowData.examScheduleName;
            rowData.statusStr = rowData.status == 1 ? '未开始' : (rowData.status == 2 ? '进行中' : '已结束');
            return rowData;
          });
        } else {
          this.tableData = { list: [], count: 0 };
        }
      });
    },
    setStartTime(value) {
      this.scheduler.startTime = value;
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
