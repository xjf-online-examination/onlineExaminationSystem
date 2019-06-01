<template>
  <div class="demo-split">
    <Split v-model="split1">
      <div slot="left" class="demo-split-pane">
        <Form ref="questionSearch" :model="searchData" inline>
          <FormItem prop="questionsCode" label="试题编号" label-position="left" class="search-flex">
            <Input type="text" v-model="searchData.questionsCode" placeholder="试题编号"/>
          </FormItem>
          <FormItem prop="questionsTitle" label="题目" label-position="left" class="search-flex">
            <Input type="text" v-model="searchData.questionsTitle" placeholder="题目"/>
          </FormItem>
          <FormItem prop="questionsType" label="类型" label-position="left" class="search-flex">
            <Select v-model="searchData.questionsType" style="width:150px">
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
            <Button type="default" @click="handleReset('questionSearch')" class="m-l-s">重置</Button>
          </FormItem>
        </Form>
        <Tables
          ref="tables"
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
            :page-size="searchData.pageSize"
            show-total
            simple
            @on-change="onPageChange"
            @on-page-size-change="onPageSizeChange"
          />
        </div>
      </div>
      <div slot="right" class="demo-split-pane">
        <div class="paper-preview">
          <h1 class="paper-title">{{paper.name}}</h1>
          <div
            class="paper-type-block"
            v-for="(group,idx) in paper.examQuestionsTypeVOList"
            v-bind:key="idx"
          >
            <div v-if="group.type=='1'">
              <h3>{{types[idx]}}、单选题（共{{group.examQuestionsDetailsVOList.length}}题）</h3>
              <div
                v-for="(question,index) in group.examQuestionsDetailsVOList"
                v-bind:key="index"
                class="row-block"
              >
                <div class="question-block">
                  <label>{{index+1}}.</label>
                  <div class="main-block">
                    <div>{{question.title}}({{question.score}}分)</div>
                    <div class="answer-block">
                      <div class="option-block">
                        <RadioGroup>
                          <Radio
                            v-if="question.optionA!==''"
                            :label="'A.'+question.optionA"
                            class="option-block"
                          ></Radio>
                          <Radio
                            v-if="question.optionB!==''"
                            :label="'B.'+question.optionB"
                            class="option-block"
                          ></Radio>
                          <Radio
                            v-if="question.optionC!==''"
                            :label="'C.'+question.optionC"
                            class="option-block"
                          ></Radio>
                          <Radio
                            v-if="question.optionD!==''"
                            :label="'D.'+question.optionD"
                            class="option-block"
                          ></Radio>
                          <Radio
                            v-if="question.optionE!==''"
                            :label="'E.'+question.optionE"
                            class="option-block"
                          ></Radio>
                        </RadioGroup>
                      </div>
                    </div>
                  </div>
                </div>
                <Button class="del-btn" type="primary" ghost @click="handleDelete(idx,index)">删除</Button>
              </div>
            </div>
            <div v-if="group.type=='2'">
              <h3>{{types[idx]}}、多选题（共{{group.examQuestionsDetailsVOList.length}}题）</h3>
              <div v-for="(question,index) in group.examQuestionsDetailsVOList" v-bind:key="index">
                <div class="row-block">
                  <div class="question-block">
                    <label>{{index+1}}.</label>
                    <div class="main-block">
                      <div>{{question.title}}({{question.score}}分)</div>
                      <div class="answer-block">
                        <div class="option-block">
                          <CheckboxGroup>
                            <Checkbox
                              v-if="question.optionA!==''"
                              :label="'A.'+question.optionA"
                              class="option-block"
                            ></Checkbox>
                            <Checkbox
                              v-if="question.optionB!==''"
                              :label="'B.'+question.optionB"
                              class="option-block"
                            ></Checkbox>
                            <Checkbox
                              v-if="question.optionC!==''"
                              :label="'C.'+question.optionC"
                              class="option-block"
                            ></Checkbox>
                            <Checkbox
                              v-if="question.optionD!==''"
                              :label="'D.'+question.optionD"
                              class="option-block"
                            ></Checkbox>
                            <Checkbox
                              v-if="question.optionE!==''"
                              :label="'E.'+question.optionE"
                              class="option-block"
                            ></Checkbox>
                          </CheckboxGroup>
                        </div>
                      </div>
                    </div>
                  </div>
                  <Button class="del-btn" type="primary" ghost @click="handleDelete(idx,index)">删除</Button>
                </div>
              </div>
            </div>
            <div v-if="group.type=='4'">
              <h3>{{types[idx]}}、判断题（共{{group.examQuestionsDetailsVOList.length}}题）</h3>
              <div v-for="(question,index) in group.examQuestionsDetailsVOList" v-bind:key="index">
                <div class="row-block">
                  <div class="question-block">
                    <label>{{index+1}}.</label>
                    <div class="main-block">
                      <div>{{question.title}}({{question.score}}分)</div>
                      <div class="answer-block">
                        <div class="option-block">
                          <CheckboxGroup>
                            <Checkbox
                              v-if="question.optionA!==''"
                              :label="'A.'+question.optionA"
                              class="option-block"
                            ></Checkbox>
                            <Checkbox
                              v-if="question.optionB!==''"
                              :label="'B.'+question.optionB"
                              class="option-block"
                            ></Checkbox>
                            <Checkbox
                              v-if="question.optionC!==''"
                              :label="'C.'+question.optionC"
                              class="option-block"
                            ></Checkbox>
                            <Checkbox
                              v-if="question.optionD!==''"
                              :label="'D.'+question.optionD"
                              class="option-block"
                            ></Checkbox>
                            <Checkbox
                              v-if="question.optionE!==''"
                              :label="'E.'+question.optionE"
                              class="option-block"
                            ></Checkbox>
                          </CheckboxGroup>
                        </div>
                      </div>
                    </div>
                  </div>
                  <Button class="del-btn" type="primary" ghost @click="handleDelete(idx,index)">删除</Button>
                </div>
              </div>
            </div>
            <div v-if="group.type=='6'">
              <h3>{{types[idx]}}、分录题（共{{group.examQuestionsDetailsVOList.length}}题）</h3>
              <div
                v-for="(question,index) in group.examQuestionsDetailsVOList"
                v-bind:key="index"
                class="row-block"
              >
                <div class="question-block">
                  <label>{{index+1}}.</label>
                  <div class="main-block">
                    <div class="row-block">
                      <div>{{question.title}}</div>
                      <Button
                        class="del-btn"
                        type="primary"
                        ghost
                        @click="handleDelete(idx,index)"
                      >删除</Button>
                    </div>
                    <div class="answer-block">
                      <div class="option-block">
                        <Journalizing
                          type="test"
                          :data="question.journalizingData"
                          :subject-list="subjectList"
                        ></Journalizing>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <Button type="primary" @click="savePaper()" class="save-btn">保存</Button>
      </div>
    </Split>
  </div>
</template>


<script>
import Tables from '@/components/tables';

import Journalizing from '@/components/journalizing/table';
import {
  automaticPaper, editPaper, listPage, getPaperDetailForTeacher,
} from '@/api/teacher';
import { mapMutations } from 'vuex';

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
        examPaperQuestionsDTOList: [],
        examQuestionsTypeVOList: [],
      },
      studentAnswer: '',
      split1: 0.4,
      columns: [
        {
          type: 'selection',
          width: 50,
          align: 'center',
        },
        {
          title: '序号', type: 'index', align: 'center', width: 60,
        },
        {
          title: '试题编号', key: 'code', align: 'center',
        },
        {
          title: '试题题目', key: 'title', align: 'center',
        },
        {
          title: '试题课程', key: 'courseName', align: 'center', width: 100,
        },
        {
          title: '试题类型', key: 'typeName', align: 'center', width: 100,
        }],
      tableData: {
        list: [],
        count: 0,
      },
      searchData: {
        questionsCode: '',
        questionsTitle: '',
        questionsType: '',
        courseCode: '',
        currentPage: 1,
        pageSize: 10,
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
      questionList: [],
      types: ['一', '二', '三', '四', '五', '六'],
    };
  },
  methods: {
    ...mapMutations([
      'closeTag',
    ]),
    handleSearch() {
      this.listPage();
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
    handleSelect(selection, question) {
      console.info('选择', selection);
      this.addQuestion(question);
    },
    handleSelectCancel(selection, question) {
      console.info('取消选择', selection);
      this.removeQuestion(question);
    },
    handleSelectAll(selection) {
      console.info('全选', selection);
      selection.forEach((select) => {
        this.addQuestion(select);
      });
    },
    handleCancelAll(selection) {
      console.info('取消全选', selection);
      this.tableData.list.forEach((select) => {
        this.removeQuestion(select);
      });
    },
    listPage() {
      this.searchData.courseCode = this.$route.query.courseCode;
      listPage(this.searchData).then((res) => {
        if (res.responseCode === '200') {
          this.tableData = res.data;
          this.tableData.list.map((row) => {
            if (row.type === '1') row.typeName = '单选题';
            else if (row.type === '2') row.typeName = '多选题';
            else if (row.type === '4') row.typeName = '判断题';
            else if (row.type === '6') row.typeName = '分录';
            else row.typeName = '';
            return row;
          });
        } else {
          this.tableData = { list: [], count: 0 };
        }
      });
    },
    getPaperDetail() {
      getPaperDetailForTeacher(this.$route.query.id).then((res) => {
        if (res.responseCode === '200') {
          this.paper = res.data;
          this.journalizingData = [];
          for (let i = 0; i < 4; i += 1) {
            this.journalizingData.push({
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
            });
          }
          if (this.paper.examQuestionsTypeVOList.length > 0) {
            this.paper.examQuestionsTypeVOList.forEach((group) => {
              if (group.type === '6') {
                if (group.examQuestionsDetailsVOList.length > 0) {
                  group.examQuestionsDetailsVOList.forEach((question) => {
                    question.journalizingData = this.journalizingData;
                  });
                }
              }
            });
          }
          console.log(this.paper);
        }
      });
    },
    addQuestion(selection) {
      const { type } = selection;
      if (type === '6') {
        selection.journalizingData = [];
        for (let i = 0; i < 4; i++) {
          selection.journalizingData.push({
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
          });
        }
      }
      if (this.paper.examQuestionsTypeVOList.length === 0) {
        this.paper.examQuestionsTypeVOList = [];
        this.paper.examQuestionsTypeVOList.push({
          type,
          examQuestionsDetailsVOList: [],
        });
        this.paper.examQuestionsTypeVOList[0].examQuestionsDetailsVOList.push({
          ...selection,
        });
      } else {
        let groupExsit = false;
        this.paper.examQuestionsTypeVOList.forEach((group) => {
          if (group.type === type) { // 已经存在该类型
            for (let idx = 0; idx < group.examQuestionsDetailsVOList.length; idx++) {
              if (selection.id === group.examQuestionsDetailsVOList[idx].id) {
                group.examQuestionsDetailsVOList.splice(idx, 1);
                break;
              }
            }
            group.examQuestionsDetailsVOList.push({ ...selection });
            groupExsit = true;
          }
        });
        if (!groupExsit) { // 不存在该分类
          this.paper.examQuestionsTypeVOList.push({
            type,
            examQuestionsDetailsVOList: [],
          });
          this.paper.examQuestionsTypeVOList[this.paper.examQuestionsTypeVOList.length - 1].examQuestionsDetailsVOList.push(selection);
        }
      }
    },
    removeQuestion(selection) {
      const { type } = selection;
      this.paper.examQuestionsTypeVOList.forEach((group, index) => {
        if (group.type === type) {
          for (let idx = 0; idx < group.examQuestionsDetailsVOList.length; idx++) {
            if (selection.id === group.examQuestionsDetailsVOList[idx].id) {
              group.examQuestionsDetailsVOList.splice(idx, 1);
              if (group.examQuestionsDetailsVOList.length === 0) {
                this.paper.examQuestionsTypeVOList.splice(index, 1);
              }
              break;
            }
          }
        }
      });
    },
    handleDelete(idx, index) {
      this.paper.examQuestionsTypeVOList[idx].examQuestionsDetailsVOList.splice(index, 1);
      if (this.paper.examQuestionsTypeVOList[idx].examQuestionsDetailsVOList.length === 0) {
        this.paper.examQuestionsTypeVOList.splice(idx, 1);
      }
    },
    savePaper() {
      if (this.paper.examQuestionsTypeVOList.length > 0) {
        this.paper.examPaperQuestionsDTOList = [];
        let questionsNo = 0;
        this.paper.examQuestionsTypeVOList.forEach((group) => {
          if (group.examQuestionsDetailsVOList.length > 0) {
            group.examQuestionsDetailsVOList.forEach((question) => {
              questionsNo++;
              this.paper.examPaperQuestionsDTOList.push({
                examQuestionsId: question.id,
                questionsNo,
              });
            });
          }
        });
        delete this.paper.examQuestionsTypeVOList;
        if (this.$route.query.status === 'add') {
          automaticPaper(this.paper).then((res) => {
            if (res.responseCode === '201') {
              this.$Notice.success({ title: '保存成功！' });
              this.closeTag(this.$route);
            } else {
              this.$Notice.error({ title: '保存失败！' });
            }
          });
        } else {
          editPaper(this.paper).then((res) => {
            if (res.responseCode === '201') {
              this.$Notice.success({ title: '修改成功！' });
              this.closeTag(this.$route);
              this.$router.push({
                name: 'paper',
              });
            } else {
              this.$Notice.error({ title: '修改失败！' });
            }
          });
        }
      } else {
        this.$Notice.error({ title: '试题不能为空!' });
      }
    },
  },
  mounted() {
    this.listPage();
    if (this.$route.query.status === 'edit') {
      this.getPaperDetail();
    }
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
.paper-preview {
  padding: 0 10px;
  .paper-title {
    text-align: center;
  }
  .paper-type-block {
    margin-top: 20px;
    .row-block {
      display: flex;
      justify-content: space-between;
      .question-block {
        display: flex;
        margin-top: 5px;
        .main-block {
          display: flex;
          flex-direction: column;
          .answer-block {
            display: flex;
            align-items: center;
            margin-top: 10px;
            .option-block {
              display: flex;
              flex: 1;
            }
          }
        }
      }
      .del-btn {
        height: 30px;
      }
    }
  }
}
.save-btn {
  margin-top: 40px;
  position: absolute;
  right: 20px;
}
.ivu-split-pane.left-pane,
.ivu-split-pane.right-pane {
  overflow: scroll !important;
}
</style>
