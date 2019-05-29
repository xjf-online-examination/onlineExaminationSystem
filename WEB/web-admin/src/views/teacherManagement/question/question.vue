<template>
  <div>
    <Form ref="questionSearch" :model="searchData" inline>
      <FormItem prop="code" label="试题编号" label-position="left" class="search-flex">
        <Input type="text" v-model="searchData.code" placeholder="试题编号"/>
      </FormItem>
      <FormItem prop="title" label="题目" label-position="left" class="search-flex">
        <Input type="text" v-model="searchData.title" placeholder="题目"/>
      </FormItem>
      <FormItem prop="courseCode" label="课程编号" label-position="left" class="search-flex">
        <Input type="text" v-model="searchData.courseCode" placeholder="课程编号"/>
      </FormItem>
      <FormItem prop="type" label="类型" label-position="left" class="search-flex">
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
        <Button type="default" @click="handleReset('questionSearch')" class="m-l-s">重置</Button>
      </FormItem>
    </Form>
    <div class="m-b-s flex-display">
      <Button type="primary" @click="onAdd()">添加</Button>
      <Upload
        class="m-l-s"
        :action="baseUrl+'/examQuestions/import'"
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
    <tables
      ref="tables"
      search-place="top"
      v-model="tableData.list"
      :columns="columns"
      key="listTable"
    >
      <!-- <template slot-scope="scope">
        <strong>{{ scope.row.type |questionType }}</strong>
      </template>-->
    </tables>
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

    <Modal
      v-model="modalVisible"
      :title="modalTitle"
      :closable="false"
      :mask-closable="false"
      width="1000"
    >
      <Form ref="questionForm" :model="question" :rules="rules">
        <FormItem prop="courseCode" label="课程编号">
          <Input type="text" v-model="question.courseCode" placeholder="请输入课程编号"/>
        </FormItem>
        <FormItem prop="type" label="题目类型">
          <Select v-model="question.type">
            <Option value="1">单选题</Option>
            <Option value="2">多选题</Option>
            <!-- <Option :value="3">不定向选择题</Option> -->
            <Option value="4">判断题</Option>
            <!-- <Option :value="5">简答题</Option> -->
            <Option value="6">分录</Option>
          </Select>
        </FormItem>
        <FormItem prop="title" label="题目">
          <Input type="textarea" v-model="question.title" placeholder="请输入题目"/>
        </FormItem>
        <FormItem
          label-position="top"
          label="选项"
          v-if="question.type==='1'||question.type==='2'||question.type=='3'||question.type==='4'"
        >
          <Row>
            <Col span="24">
              <FormItem v-for="(item, index) in options" :key="index" :label="optionLabels[index]">
                <Row>
                  <Col span="16">
                    <Input
                      type="textarea"
                      v-model="question['option'+optionLabels[index]]"
                      placeholder="请输入选项"
                    />
                  </Col>
                  <Col span="4" offset="1">
                    <Button @click="handleRemoveOption(index)">删除</Button>
                  </Col>
                </Row>
              </FormItem>
              <FormItem>
                <Row>
                  <Col span="6" offset="1" style="margin-top:5px;">
                    <Button type="dashed" long @click="handleAddOption" icon="md-add">添加选项</Button>
                    <label class="err-info">{{errMsg}}</label>
                  </Col>
                </Row>
              </FormItem>
            </Col>
          </Row>
        </FormItem>
        <FormItem label="答案">
          <RadioGroup v-model="question.singleAnswer" v-if="question.type==='1'">
            <Radio
              :label="item"
              v-for="(item,index) in optionLabels.slice(0,options.length)"
              v-bind:key="index"
            ></Radio>
          </RadioGroup>
          <CheckboxGroup v-model="multipleAnswer" v-if="question.type==='2'||question.type==='3'">
            <Checkbox
              :label="item"
              v-for="(item,index) in optionLabels.slice(0,options.length)"
              v-bind:key="index"
            ></Checkbox>
          </CheckboxGroup>
          <RadioGroup v-model="question.yesNoAnswer" v-if="question.type==='4'">
            <Radio label="是"></Radio>
            <Radio label="否"></Radio>
          </RadioGroup>
          <Journalizing
            v-if="question.type==='6'"
            type="answer"
            :data="journalizingData"
            :subject-list="subjectList"
            v-on:tablechange="cusEditFunc"
            v-on:rowedit="handleRowEdit"
          ></Journalizing>
          <label class="err-info">{{operateError}}</label>
        </FormItem>
        <FormItem label="分值">
          <Input type="text" v-model="question.score" placeholder="请输入分值"/>
          <Journalizing
            class="m-t-s"
            v-if="question.type==='6'"
            type="score"
            :data="journalizingData"
            v-on:tablechange="cusEditFunc"
          ></Journalizing>
        </FormItem>
      </Form>
      <div slot="footer">
        <Button type="text" @click="cancel('questionForm')">取消</Button>
        <Button type="primary" @click="save('questionForm')">确定</Button>
      </div>
    </Modal>
  </div>
</template>


<script>
import FileSaver from 'file-saver';
import Tables from '@/components/tables';
import config from '@/config';
import Journalizing from '@/components/journalizing/table';
import {
  addQuestion, editQuestion, getQuestionList, listSubjectOne, getQuestionById, downloadQuestionsTemplate,
} from '@/api/teacher';
import { Promise, promised } from 'q';

const baseUrl = process.env.NODE_ENV === 'development' ? config.baseUrl.dev : config.baseUrl.pro;
export default {
  name: 'classes',
  components: {
    Tables,
    Journalizing,
  },
  filters: {
    /* 格式化时间戳 */
    questionType(value) {
      if (!value) return '';
      let result = '';
      if (value === '1') result = '单选题';
      else if (value === '2') result = '多选题';
      else if (value === '4') result = '判断题';
      else result = '分录';
      return result;
    },
  },
  data() {
    return {
      columns: [
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
          title: '试题类型',
          align: 'center',
          key: 'typeName',
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
                    this.onDetail(params.index);
                  },
                },
              }, '详情'),
            ]),
          ],
        }],
      tableData: { list: [], count: 0 },
      searchData: {
        code: '',
        title: '',
        courseCode: '',
        type: '',
        currentPage: 1,
        pageSize: 10,
      },
      question: {
        courseCode: '',
        type: '1',
        title: '',
        optionA: '',
        optionB: '',
        optionC: '',
        optionD: '',
        optionE: '',
        singleAnswer: '',
        multipleAnswer: '',
        yesNoAnswer: '',
      },
      multipleAnswer: [],
      modalVisible: false,
      modalTitle: '',
      isAdd: true,
      optionLabels: ['A', 'B', 'C', 'D', 'E'],
      options: [1],
      errMsg: '',
      rules: {
        courseCode: [
          { required: true, message: '课程编号不能为空', trigger: 'blur' },
        ],
        type: [
          {
            required: true, message: '课程类型不能为空', trigger: 'change',
          },
        ],
        title: [
          { required: true, message: '题目不能为空', trigger: 'change' },
        ],
      },
      journalizingData: [],
      subjectList: [],
      journalizingObj: {
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
      operateError: '',
    };
  },
  methods: {
    handleSearch() {
      console.log(this.searchData);
      this.getQuestionList(this.searchData);
    },
    handleReset(name) {
      this.$refs[name].resetFields();
      this.getQuestionList(this.searchData);
    },
    onEdit(index) {
      this.modalTitle = '修改';
      this.isAdd = false;
      this.getQuestionById(this.tableData.list[index].id).then((res) => {
        if (res.responseCode === '200') {
          this.options = [];
          this.question = res.data;
          delete this.question.entryStandardAnswerDetailsVOList;
          if (res.data.type === '2') {
            this.multipleAnswer = res.data.multipleAnswer.split(',');
          }
          if (res.data.optionA) this.options.push(1);
          if (res.data.optionB) this.options.push(2);
          if (res.data.optionC) this.options.push(3);
          if (res.data.optionD) this.options.push(4);
          if (res.data.optionE) this.options.push(5);
        } else {
          this.question = {};
        }
        this.modalVisible = true;
      });
    },
    onDelete(index) {
      this.showDeleteModal = true;
      this.selectIndex = index;
    },
    onPageChange(params) {
      this.searchData.currentPage = params;
      this.getQuestionList(this.searchData);
    },
    onPageSizeChange(params) {
      this.searchData.pageSize = params;
      this.getQuestionList(this.searchData);
    },
    onAdd() {
      this.modalVisible = true;
      this.modalTitle = '添加';
      this.isAdd = true;
      const data = [];
      for (let i = 0; i < 4; i++) {
        data.push({
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
        this.journalizingData = data;
      }
    },
    save(name) {
      this.$refs[name].validate((valid) => {
        if (valid) {
          if (this.isAdd) {
            if (this.question.type === '6') {
              this.question.entryStandardAnswerDetailsDTOList = this.journalizingData;
              console.log(this.journalizingData);
            }
            if (this.question.type === '2') {
              this.question.multipleAnswer = this.multipleAnswer.toString();
            }

            addQuestion(this.question).then((res) => {
              console.log(res);
              if (res.responseCode === '201') {
                this.getQuestionList();
                this.$Notice.success({ title: '添加成功' });
              } else {
                this.$Notice.success({ title: '添加失败' });
              }
              this.modalVisible = false;
            });
          } else {
            if (this.question.type === '6') {
              this.question.entryStandardAnswerDetailsDTOList = this.journalizingData;
            }
            if (this.question.type === '2') {
              this.question.multipleAnswer = this.multipleAnswer.toString();
            }
            editQuestion(this.question).then((res) => {
              console.log(res);
              if (res.responseCode === '201') {
                this.getQuestionList();
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
    handleAddOption() {
      if (this.options.length === 5) {
        this.errMsg = '选项不能大于5个';
      } else {
        this.errMsg = '';
        this.options.push(this.options.length + 1);
      }
    },
    handleRemoveOption(index) {
      // TODO:
      this.options.splice(index, 1);
    },
    getQuestionList() {
      getQuestionList(this.searchData).then((res) => {
        if (res.responseCode === '200') {
          this.tableData = res.data;
          if (this.tableData.list.length > 0) {
            this.tableData.list.map((row) => {
              if (row.type === '1') row.typeName = '单选题';
              else if (row.type === '2') row.typeName = '多选题';
              else if (row.type === '4') row.typeName = '判断题';
              else if (row.type === '6') row.typeName = '分录';
              else row.typeName = '';
              return row;
            });
          }
        } else {
          this.tableData = { list: [], count: 0 };
        }
      });
    },
    listSubjectOne() {
      listSubjectOne().then((res) => {
        if (res.responseCode === '200') {
          this.subjectList = res.data;
        } else {
          this.subjectList = [];
        }
      });
    },
    cusEditFunc(params) {
      if (params.type === 'total') { // 合计
        this.journalizingData[params.index].total = params.value;
      } else if (params.type === 'text') {
        if (params.field === 'summary') { // 摘要
          this.journalizingData[params.index].summary = params.value;
        } else if (params.field === 'subject2') { // 二级科目
          this.journalizingData[params.index].subject2 = params.value;
        }
      } else if (params.type === 'subject1') {
        this.journalizingData[params.index].subject1 = params.value;
      } else if (params.type === 'money') {
        if (params.field === 'yi1') {
          if (params.index === (this.journalizingData.length - 1)) { // 借方总额
            this.journalizingData[params.index].debitTotal = params.value;
          } else { // 借方金额
            this.journalizingData[params.index].debitAmount = params.value;
          }
        } else if (params.field === 'yi2') {
          if (params.index === (this.journalizingData.length - 1)) { // 贷方总额
            this.journalizingData[params.index].creditTotal = params.value;
          } else { // 贷方金额
            this.journalizingData[params.index].creditAmount = params.value;
          }
        }
      } else if (params.type === 'score') {
        if (params.field === 'summary') {
          if (params.index === (this.journalizingData.length - 1)) {
            this.journalizingData[params.index].totalScore = params.value;
          } else {
            this.journalizingData[params.index].summaryScore = params.value;
          }
        } else if (params.field === 'yi1') {
          if (params.index === (this.journalizingData.length - 1)) {
            this.journalizingData[params.index].debitTotalScore = params.value;
          } else {
            this.journalizingData[params.index].debitAmountScore = params.value;
          }
        } else if (params.field === 'yi2') {
          if (params.index === (this.journalizingData.length - 1)) {
            this.journalizingData[params.index].creditTotalScore = params.value;
          } else {
            this.journalizingData[params.index].creditAmountScore = params.value;
          }
        } else if (params.field === 'subject1') {
          this.journalizingData[params.index].subject1Score = params.value;
        } else if (params.field === 'subject2') {
          this.journalizingData[params.index].subject2Score = params.value;
        }
      }
    },
    handleRowEdit(params) {
      if (params.type === 'add') {
        this.journalizingData.splice(params.index, 0, this.journalizingObj);
      } else if (this.journalizingData.length === 1) {
        this.operateError = '不能再删除了!';
      } else {
        this.operateError = '';
        this.journalizingData.splice(params.index, 1);
      }
    },
    getQuestionById(id) {
      return new Promise((resolve, reject) => {
        getQuestionById(id).then((res) => {
          resolve(res);
        });
      });
    },
    onDownload() {
      downloadQuestionsTemplate().then((res) => {
        const blob = new Blob([res], {
          type: 'application/octet-stream',
        });
        // const header = headers('Content-Disposition');
        const fileName = `questionsTemplate_${new Date().getTime()}.xlsx`;
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
    this.getQuestionList();
    this.listSubjectOne();
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
