<template>
  <div class="paper-block">
    <div class="paper-preview">
      <div class="time-block">距考试结束还有：{{countDown}}</div>
      <h1 class="paper-title">{{examName}}</h1>
      <hr>
      <div class="paper-type-block">
        <template v-for="(examType, index) in examPaper">
          <div v-if="examType.studentExamQuestionsVOList.length>0" :key="index">
            <div v-if="examType.type === '1'">
              <h3>{{groupNo[index]}}、单选题（共{{examType.studentExamQuestionsVOList.length}}题）</h3>
              <div v-for="(question, index) in examType.studentExamQuestionsVOList" :key="index">
                <div class="question-block">
                  <div class="main-block">
                    <div>{{index+1}}、{{question.title}}({{question.score}}分)</div>
                    <div class="answer-block">
                      <RadioGroup vertical v-model="question.answer">
                        <Radio v-if="question.optionA" :label="'A、'+question.optionA"></Radio>
                        <Radio v-if="question.optionB" :label="'B、'+question.optionB"></Radio>
                        <Radio v-if="question.optionC" :label="'C、'+question.optionC"></Radio>
                        <Radio v-if="question.optionD" :label="'D、'+question.optionD"></Radio>
                        <Radio v-if="question.optionE" :label="'E、'+question.optionE"></Radio>
                      </RadioGroup>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div v-else-if="examType.type === '2'">
              <h3>{{groupNo[index]}}、多选题（共{{examType.studentExamQuestionsVOList.length}}题）</h3>
              <div
                v-for="(question,index) in examType.studentExamQuestionsVOList"
                v-bind:key="index"
              >
                <div class="question-block">
                  <div class="main-block">
                    <div>{{index+1}}、{{question.title}}({{question.score}}分)</div>
                    <div class="answer-block">
                      <CheckboxGroup v-model="question.answer">
                        <Checkbox
                          v-if="question.optionA"
                          :label="'A、'+question.optionA"
                          class="option-block"
                        ></Checkbox>
                        <Checkbox
                          v-if="question.optionB"
                          :label="'B、'+question.optionB"
                          class="option-block"
                        ></Checkbox>
                        <Checkbox
                          v-if="question.optionC"
                          :label="'C、'+question.optionC"
                          class="option-block"
                        ></Checkbox>
                        <Checkbox
                          v-if="question.optionD"
                          :label="'D、'+question.optionD"
                          class="option-block"
                        ></Checkbox>
                        <Checkbox
                          v-if="question.optionE"
                          :label="'E、'+question.optionE"
                          class="option-block"
                        ></Checkbox>
                      </CheckboxGroup>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!-- <div v-else-if="examType.type === '3'"></div> -->
            <div v-else-if="examType.type === '4'">
              <h3>{{groupNo[index]}}、判断题（共{{examType.studentExamQuestionsVOList.length}}题）</h3>
              <div
                v-for="(question,index) in examType.studentExamQuestionsVOList"
                v-bind:key="index"
              >
                <div class="row-block">
                  <div class="question-block">
                    <div class="main-block">
                      <div>{{index+1}}、{{question.title}}({{question.score}}分)</div>
                      <div class="answer-block">
                        <div class="option-block">
                          <RadioGroup v-model="question.answer">
                            <Radio label="是" class="option-block"></Radio>
                            <Radio label="否" class="option-block"></Radio>
                          </RadioGroup>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!-- <div v-else-if="examType.type === '5'"></div> -->
            <div v-else-if="examType.type === '6'">
              <h3>{{groupNo[index]}}、分录题（共{{examType.studentExamQuestionsVOList.length}}题）</h3>
              <div
                v-for="(question,index) in examType.studentExamQuestionsVOList"
                v-bind:key="index"
              >
                <div class="question-block">
                  <div class="main-block">
                    <div>{{index+1}}、{{question.title}}({{question.score}}分)</div>
                    <div class="answer-block">
                      <div class="option-block">
                        <Journalizing
                          type="test"
                          :index="index"
                          :data="question.journalizingData"
                          :subject-list="subjectList"
                          v-on:tablechange="cusEditFunc"
                        ></Journalizing>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </template>
      </div>
      <Button type="success" @click="exitFS" class="save-btn">提交</Button>
    </div>
    <Modal v-model="showSaveModal" :title="'提示'" @on-ok="saveExamAnswer">
      <p>是否确定提交试卷，提交后将无法再答题？</p>
    </Modal>
    <!-- <Modal v-model="showExitModal" :title="'提示'" @on-ok="exit">
      <p>考试期间不可退出全屏，退出后将自动提交试卷，是否确定退出？</p>
    </Modal>-->
  </div>
</template>

<script>
import { getExamPaper, saveExamAnswer } from '@/api/student';
import { listSubjectOne } from '@/api/teacher';
import Journalizing from '@/components/journalizing/table';
import { setInterval, clearInterval } from 'timers';
import {
  getUserCode,
} from '@/libs/util';

export default {
  name: '',
  props: {},
  components: {
    Journalizing,
  },
  data () {
    return {
      isFullscreen: '',
      examPaper: [],
      journalizingData: [],
      subjectList: [],
      groupNo: ['一', '二', '三', '四'],
      examName: this.$route.query.examName,
      countDown: '',
      timer1: '',
      showSaveModal: false,
      answerSaveDetailsDTOList: [],
      showExitModal: false,
    };
  },
  created () { },
  mounted () {
    this.listSubjectOne();
    // 禁止内容选择
    document.onselectstart = function () {
      return false;
    };
    // //禁止内容拖放
    document.ondragstart = function () {
      return false;
    };
    // //禁止右键弹出菜单
    document.oncontextmenu = function () {
      return false;
    };

    document.onkeydown = function () {
      if (
        window.event.keyCode == 116 // 屏蔽 F5
        || window.event.keyCode == 122 // 屏蔽 F11
        || (window.event.shiftKey && window.event.keyCode == 121)
      ) {
        // shift+F10
        window.event.keyCode = 0;
        window.event.returnValue = false;
      }
      if (window.event.altKey && window.event.keyCode == 115) {
        // 屏蔽Alt+F4
        window.showModelessDialog(
          'about:blank',
          '',
          'dialogWidth:1px;dialogheight:1px',
        );
        return false;
      }
    };

    const param = this.$route.query;
    let Id = '';
    try {
      Id = param.examPaperId;
      timer = setTimeout(() => {
        this.$Message.warning({ content: '离考试结束还有10分钟', duration: 5 });
      }, 60 * 1000 * (param.duration - 10));
    } catch (error) { }
    this.getExamPaperWithId(Id);
    this.handleFullscreen();
    this.countDown = this.calculateTime(param.duration * 60);
    let { duration } = { ...param };
    duration *= 60;
    this.timer1 = setInterval(() => {
      if (duration < 0) {
        clearInterval(timer1);
      } else {
        duration -= 1;
        this.countDown = this.calculateTime(duration);
      }
    }, 1000);
  },
  computed: {},
  methods: {
    calculateTime (duration) {
      const hour = parseInt(duration / 60 / 60);
      const minute = parseInt(duration / 60 % 60);
      const seconds = parseInt(duration % 60 % 60);
      return `${hour < 10 ? `0${hour}` : hour}:${minute < 10 ? `0${minute}` : minute}:${seconds < 10 ? `0${seconds}` : seconds}`;
    },
    getExamPaperWithId (Id) {
      getExamPaper(Id)
        .then((res) => {
          if (res.responseCode === '200') {
            console.log('res :', res.data);
            this.examPaper = res.data;
            this.examPaper.forEach((group) => {
              if (group.type === '6') {
                group.studentExamQuestionsVOList.forEach((question) => {
                  question.journalizingData = [];
                  for (let i = 0; i < 4; i += 1) { // TODO:question.row
                    question.journalizingData.push({
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
                });
              }
            });
          }
        })
        .catch((err) => {
          console.log('err :', err);
        });
    },
    handleFullscreen () {
      const main = document.body;
      if (main.requestFullscreen) {
        console.log(11);
        main.requestFullscreen();
      } else if (main.mozRequestFullScreen) {
        console.log(21);
        main.mozRequestFullScreen();
      } else if (main.webkitRequestFullScreen) {
        console.log(31);
        main.webkitRequestFullScreen();
      } else if (main.msRequestFullscreen) {
        console.log(41);
        main.msRequestFullscreen();
      }
    },
    exitFS () {
      clearInterval(this.timer1);
      this.answerSaveDetailsDTOList = this.handleAnswer();
      console.log('answerSaveDetailsDTOList :', answerSaveDetailsDTOList);
      try {
        if (document.exitFullscreen) {
          this.showExitModal = true;
          console.log(1);
          // document.exitFullscreen();
        } else if (document.mozCancelFullScreen) {
          console.log(2);
          document.mozCancelFullScreen();
        } else if (document.webkitCancelFullScreen) {
          console.log(3);
          document.webkitCancelFullScreen();
        } else if (document.msExitFullscreen) {
          console.log(4);
          document.msExitFullscreen();
        }
      } catch (error) {
        console.log('error :', error);
      }
      this.showSaveModal = true; this.saveExamAnswer(answerSaveDetailsDTOList);
    },
    // 处理答案
    handleAnswer () {
      return this.examPaper.reduce((o, item) => {
        item.studentExamQuestionsVOList.forEach((ele) => {
          let obj = {};
          if (ele.type !== '6') {
            obj = {
              questionsNo: ele.questionsNo,
              questionsType: ele.type,
            };
            if (this.isArray(ele.answer)) {
              const arr = ele.answer.reduce((e, v) => {
                let str = '';
                str = v.split('、')[0];
                e.push(str);
                return e;
              }, []);
              obj.answer = arr.join(',');
            } else {
              obj.answer = ele.answer ? ele.answer.split('、')[0] : '';
            }
          } else {
            obj = {
              questionsNo: ele.questionsNo,
              questionsType: ele.type,
              studentEntryAnswerSaveDTOList: ele.journalizingData,
            };
          }
          o.push(obj);
        });
        return o;
      }, []);
    },
    isArray (obj) {
      return Object.prototype.toString.call(obj) === '[object Array]';
    },
    cusEditFunc (params) {
      this.examPaper.forEach((group) => {
        if (group.type == '6') {
          const question = group.studentExamQuestionsVOList[params.outerIndex];
          if (params.type === 'total') { // 合计
            question.journalizingData[params.index].total = params.value;
          } else if (params.type === 'text') {
            if (params.field === 'summary') { // 摘要
              question.journalizingData[params.index].summary = params.value;
            } else if (params.field === 'subject2') { // 二级科目
              question.journalizingData[params.index].subject2 = params.value;
            }
          } else if (params.type === 'subject1') {
            question.journalizingData[params.index].subject1 = params.value;
          } else if (params.type === 'money') {
            if (params.field === 'yi1') {
              if (params.index === (question.journalizingData.length - 1)) { // 借方总额
                question.journalizingData[params.index].debitTotal = params.value;
              } else { // 借方金额
                question.journalizingData[params.index].debitAmount = params.value;
              }
            } else if (params.field === 'yi2') {
              if (params.index === (question.journalizingData.length - 1)) { // 贷方总额
                question.journalizingData[params.index].creditTotal = params.value;
              } else { // 贷方金额
                question.journalizingData[params.index].creditAmount = params.value;
              }
            }
          } else if (params.type === 'score') {
            if (params.field === 'summary') {
              if (params.index === (question.journalizingData.length - 1)) {
                question.journalizingData[params.index].totalScore = params.value;
              } else {
                question.journalizingData[params.index].summaryScore = params.value;
              }
            } else if (params.field === 'yi1') {
              if (params.index === (question.journalizingData.length - 1)) {
                question.journalizingData[params.index].debitTotalScore = params.value;
              } else {
                question.journalizingData[params.index].debitAmountScore = params.value;
              }
            } else if (params.field === 'yi2') {
              if (params.index === (question.journalizingData.length - 1)) {
                question.journalizingData[params.index].creditTotalScore = params.value;
              } else {
                question.journalizingData[params.index].creditAmountScore = params.value;
              }
            } else if (params.field === 'subject1') {
              question.journalizingData[params.index].subject1Score = params.value;
            } else if (params.field === 'subject2') {
              question.journalizingData[params.index].subject2Score = params.value;
            }
          }
        }
      });
    },
    saveExamAnswer (answers) {
      const examAnswer = {
        sno: getUserCode(),
        examScheduleId: this.$route.query.examPaperId,
        answerSaveDetailsDTOList: answers,
      };
      saveExamAnswer(examAnswer).then((res) => {
        console.log(res);
        if (res.responseCode === '200') {
          this.$Notice.success({ title: '试卷提交成功！' });
          this.$router.push({ path: '/examScheduler/examScheduler' });
        } else {
          this.$Notice.error({ title: '试卷提交失败，请检查后提交！' });
        }
      });
    },
    listSubjectOne () {
      listSubjectOne().then((res) => {
        if (res.responseCode === '200') {
          this.subjectList = res.data;
        } else {
          this.subjectList = [];
        }
      });
    },
  },
};
</script>

<style lang="less" scoped>
.paper-block {
  height: 100%;
  overflow: scroll;
  display: flex;
  justify-content: center;
  .paper-preview {
    padding: 20px 50px;
    .paper-title {
      text-align: center;
    }
    .question-block {
      display: flex;
      .main-block {
        display: flex;
        flex-direction: column;
        margin-top: 10px;
        .answer-block {
          display: flex;
          align-items: center;
          .option-block {
            display: flex;
            flex: 1;
            margin-top: 10px;
          }
          .ivu-checkbox.ivu-checkbox-checked {
            margin-left: 5px;
          }
        }
      }
    }
  }
}
.time-block {
  position: fixed;
  right: 15px;
}
.save-btn {
  margin-top: 20px;
}
</style>
