<template>
  <div class="demo-split">
    <Split v-model="split1">
      <div slot="left" class="demo-split-pane">
        <Form ref="classSearch" :model="searchData" inline>
          <FormItem prop="classCode" label="试题编号" label-position="left" class="search-flex">
            <Input type="text" v-model="searchData.code" placeholder="试题编号"/>
          </FormItem>
          <FormItem prop="className" label="题目" label-position="left" class="search-flex">
            <Input type="text" v-model="searchData.title" placeholder="题目"/>
          </FormItem>
          <FormItem prop="classCode" label="课程编号" label-position="left" class="search-flex">
            <Input type="text" v-model="searchData.courseCode" placeholder="课程编号"/>
          </FormItem>
          <FormItem label="类型" label-position="left" class="search-flex">
            <Select v-model="searchData.type" style="width:150px">
              <Option :value="1">单选题</Option>
              <Option :value="2">多选题</Option>
              <Option :value="3">不定向选择题</Option>
              <Option :value="4">判断题</Option>
              <Option :value="5">简答题</Option>
              <Option :value="6">分录</Option>
            </Select>
          </FormItem>
          <FormItem>
            <Button type="primary" icon="ios-search" @click="handleSearch()">搜索</Button>
            <Button type="default" @click="handleReset('classSearch')" class="m-l-s">重置</Button>
          </FormItem>
        </Form>
        <tables
          ref="tables"
          search-place="top"
          v-model="tableData.list"
          :columns="columns"
          @on-selection-change="handleSelect"
        />
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
      </div>
      <div slot="right" class="demo-split-pane">
        <h3>{{paper.course.name}}</h3>
        <div v-for="(question,index) in paper.examPaperQuestionsDTOList" v-bind:key="index">
          <div>
            <label>{{index}}.</label>
            <div>
              <div>{{question.title}}({{paper.score}})</div>
              <div
                v-for="(answer,index) in options"
                v-bind:key="index"
              >{{optionLables[index]}}.{{options[index]}}</div>
            </div>
          </div>
        </div>
      </div>
    </Split>
  </div>
</template>


<script>
import Tables from '@/components/tables';

import Journalizing from '@/components/journalizing';
import {
  addPaper, editPaper, getQuestionList,
} from '@/api/teacher';

export default {
  name: 'paperQuestions',
  components: {
    Tables,
    Journalizing,
  },
  data() {
    return {
      paper: {},
      split1: 0.5,
      columns: [
        {
          type: 'selection',
          width: 60,
          align: 'center',
        },
        {
          title: '序号', type: 'index', align: 'center',
        },
        {
          title: '试题编号', key: 'code', align: 'center',
        },
        {
          title: '试题题目', key: 'title', align: 'center',
        },
        {
          title: '试题课程', key: 'courseName', align: 'center',
        },
        {
          title: '试题类型', key: 'type', align: 'center',
        }],
      tableData: {
        list: [],
        count: 0,
      },
      searchData: {
        code: '',
        title: '',
        courseCode: '',
        type: '',
        currentPage: 1,
        pageSize: 50,
      },
    };
  },
  methods: {
    handleSearch() {
      this.getQuestionList(this.searchData);
    },
    handleReset(name) {
      this.$refs[name].resetFields();
      this.getQuestionList();
    },
    onPageChange(params) {
      this.searchData.currentPage = params;
      this.getQuestionList();
    },
    onPageSizeChange(params) {
      this.searchData.pageSize = params;
      this.getQuestionList();
    },
    handleSelect(selection, row) {
      console.info('selection', selection);
      console.info('row', row);
    },
    getQuestionList() {
      getQuestionList(this.searchData).then((res) => {
        if (res.responseCode === '200') {
          this.tableData = res.data;
        } else {
          this.tableData = { list: [], count: 0 };
        }
      });
    },
    getParams() {
      // 取到路由带过来的参数
      this.paper = this.$route.params.paper;
    },
  },
  mounted() {
    this.getQuestionList();
  },
  watch: {
    // 监测路由变化,只要变化了就调用获取路由参数方法将数据存储本组件即可
    $route: 'getParams',
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
.demo-split {
  height: 100%;
  border: 1px solid #dcdee2;
}
.demo-split-pane {
  padding: 10px;
}
</style>
