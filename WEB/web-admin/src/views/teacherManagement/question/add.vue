<template>
  <div>
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
        v-if="question.type==='1'||question.type==='2'||question.type=='3'"
      >
        <Row>
          <Col span="24">
            <FormItem v-for="(item, index) in options" :key="index" :label="optionLabels[index]">
              <Row>
                <Col span="16">
                  <Input type="textarea" v-model="options[index]" placeholder="请输入选项"/>
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
        <Journalizing
          class="m-t-s"
          v-if="question.type==='6'"
          type="score"
          :data="journalizingData"
          v-on:tablechange="cusEditFunc"
        ></Journalizing>
        <InputNumber :min="0" v-model="question.score" v-else placeholder="请输入分值"></InputNumber>
      </FormItem>
    </Form>
    <div slot="footer">
      <Button type="text" @click="cancel('questionForm')">取消</Button>
      <Button type="primary" @click="save('questionForm')">确定</Button>
    </div>
  </div>
</template>

<script>
import Journalizing from '@/components/journalizing/table';
import {
  addQuestion, editQuestion, listSubjectOne, getQuestionById,
} from '@/api/teacher';

export default {
  name: 'questionAdd',
  components: {
    Journalizing,
  },
  data() {
    return {
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
      options: [''],
      optionLabels: ['A', 'B', 'C', 'D', 'E'],
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
        score: [
          {
            required: true, message: '分数不能为空', trigger: 'blur', type: 'number',
          },
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
    save(name) {
      this.$refs[name].validate((valid) => {
        if (valid) {
          this.options.forEach((option, index) => {
            this.question[`option${this.optionLabels[index]}`] = option;
          });
          if (this.isAdd) {
            if (this.question.type === '6') {
              this.question.entryStandardAnswerDetailsDTOList = this.journalizingData;
              console.log(this.journalizingData);
            }
            if (this.question.type === '2') {
              this.question.multipleAnswer = this.multipleAnswer.toString();
            }

            addQuestion(this.question).then((res) => {
              this.question = {
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
              };
              this.multipleAnswer = [];
              this.options = [''];
              this.journalizingData = [];
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
            delete this.question.entryStandardAnswerDetailsVOList;
            editQuestion(this.question).then((res) => {
              this.question = {
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
              };
              this.multipleAnswer = [];
              this.options = [''];
              this.journalizingData = [];
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
        this.options.push('');
      }
    },
    handleRemoveOption(index) {
      // TODO:
      this.options.splice(index, 1);
    },
  },
  mounted() {
    this.listSubjectOne();
    if (this.$route.query.status !== 'add') {
      getQuestionById(this.$route.query.id).then((res) => {
        if (res.responseCode === '200') {
          this.options = [];
          this.question = res.data;
          this.journalizingData = res.data.entryStandardAnswerDetailsVOList;
          console.log(this.journalizingData);
          if (res.data.type === '2') {
            this.multipleAnswer = res.data.multipleAnswer.split(',');
          }
          this.options.push('');
        } else {
          this.question = {};
        }
        this.modalVisible = true;
      });
    } else {
      const data = [];
      for (let i = 0; i < 4; i += 1) {
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
    }
  },
};
</script>

<style lang="less" scoped>
</style>
