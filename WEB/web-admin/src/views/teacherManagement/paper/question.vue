<template>
  <div class="demo-split">
    <Split v-model="split1">
      <div slot="left" class="demo-split-pane">
        <Form ref="questionsSearch" :model="searchData" inline>
          <FormItem prop="classCode" label="试题编号" label-position="left" class="search-flex">
            <Input type="text" v-model="searchData.code" placeholder="试题编号"/>
          </FormItem>
          <FormItem prop="className" label="题目" label-position="left" class="search-flex">
            <Input type="text" v-model="searchData.title" placeholder="题目"/>
          </FormItem>
          <!-- <FormItem prop="classCode" label="课程编号" label-position="left" class="search-flex">
            <Input type="text" v-model="searchData.courseCode" placeholder="课程编号"/>
          </FormItem>-->
          <FormItem label="类型" label-position="left" class="search-flex">
            <Select v-model="searchData.type" style="width:150px">
              <Option :value="1">单选题</Option>
              <Option :value="2">多选题</Option>
              <!-- <Option :value="3">不定向选择题</Option> -->
              <Option :value="4">判断题</Option>
              <!-- <Option :value="5">简答题</Option> -->
              <Option :value="6">分录</Option>
            </Select>
          </FormItem>
          <FormItem>
            <Button type="primary" icon="ios-search" @click="handleSearch()">搜索</Button>
            <Button type="default" @click="handleReset('questionsSearch')" class="m-l-s">重置</Button>
          </FormItem>
        </Form>
        <tables
          ref="tables"
          search-place="top"
          v-model="tableData.list"
          :columns="columns"
          @on-select="handleSelect"
          @on-select-cancel="handleSelectCancel"
          @on-select-all="handleSelectAll"
          @on-select-all-cancel="handleCancelAll"
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
        <div class="paper-preview">
          <h1 class="paper-title">{{paper.course.name}}</h1>
          <div v-for="(question,index) in paper.examPaperQuestionsDTO" v-bind:key="index">
            <div class="question-block">
              <label>{{index+1}}.</label>
              <div class="main-block">
                <div>{{question.title}}({{paper.type|paperTypeFilter}}:{{paper.score}}分)</div>
                <div class="answer-block">
                  <div class="option-block" v-if="question.type!==6">
                    <CheckboxGroup>
                      <Checkbox
                        :label="optionLabels[idx]+'.'+answer"
                        v-for="(answer,idx) in question.options"
                        v-bind:key="idx"
                        class="option-block"
                      ></Checkbox>
                    </CheckboxGroup>
                  </div>
                  <div class="option-block" v-if="question.type===6">
                    <Journalizing type="test" :data="journalizingData" :subject-list="subjectList"></Journalizing>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </Split>
  </div>
</template>


<script>
import Tables from '@/components/tables';

import Journalizing from '@/components/journalizing/table';
import {
  addPaper, editPaper, listPage,
} from '@/api/teacher';

export default {
  name: 'paperQuestions',
  components: {
    Tables,
    Journalizing,
  },
  data() {
    return {
      paper: {
        ...this.$route.query,
        examPaperQuestionsDTO: [],
      },
      studentAnswer: '',
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
      optionLabels: ['A', 'B', 'C', 'D', 'E'],
      options: [],
      journalizingData: [],
      subjectList: [],
      JournalizingObj: {
        summary: '',
        summaryScore: '',
        subject1: '',
        subject1Score: '',
        subject2: '',
        subject2Score: '',
        debitAmount: '',
        debitAmountScore: '',
        creditAmount: '',
        creditAmountScore: '',
        total: '',
        totalScore: '',
        debitTotal: '',
        debitTotalScore: '',
        creditTotal: '',
        creditTotalScore: '',
      },
    };
  },
  methods: {
    handleSearch() {
      this.listPage(this.searchData);
    },
    handleReset(name) {
      this.$refs[name].resetFields();
      this.listPage();
    },
    onPageChange(params) {
      this.searchData.currentPage = params;
      this.listPage();
    },
    onPageSizeChange(params) {
      this.searchData.pageSize = params;
      this.listPage();
    },
    handleSelect(selection) {
      console.info('selection', selection);
      this.paper.examPaperQuestionsDTO = selection;
      this.options = [];
      if (this.paper.examPaperQuestionsDTO) {
        this.paper.examPaperQuestionsDTO.map((question) => {
          question.options = [];
          if (question.optionA) question.options.push(question.optionA);
          if (question.optionB) question.options.push(question.optionB);
          if (question.optionC) question.options.push(question.optionC);
          if (question.optionD) question.options.push(question.optionD);
          if (question.optionE) question.options.push(question.optionE);
          return question;
        });
      }
    },
    handleSelectCancel(selection) {
      console.info('取消选择', selection);
    },
    handleSelectAll(selection) {
      console.info('全选', selection);
    },
    handleCancelAll(selection) {
      console.info('取消全选', selection);
    },
    listPage() {
      this.searchData.courseCode = this.$route.query.courseCode;
      listPage(this.searchData).then((res) => {
        if (res.responseCode === '200') {
          this.tableData = res.data;
        } else {
          this.tableData = { list: [], count: 0 };
        }
      });
    },
    getParams() {
      // 取到路由带过来的参数
      this.paper = { ...this.paper, ...this.$route.params.paper };
    },
  },
  mounted() {
    this.listPage();
  },
  // watch: {
  //   // 监测路由变化,只要变化了就调用获取路由参数方法将数据存储本组件即可
  //   $route: 'getParams',
  // },
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
.paper-preview {
  padding: 0 10px;
  .paper-title {
    text-align: center;
  }
  .question-block {
    display: flex;
    .main-block {
      display: flex;
      flex-direction: column;
      .answer-block {
        display: flex;
        align-items: center;
        .option-block {
          display: flex;
          flex: 1;
        }
      }
    }
  }
}
</style>
