<template>
  <div>
    <div class="paper-preview">
      <h1 class="paper-title">试卷名称</h1>
      <hr>
      <div class="paper-type-block">
        <template v-for="(examType, index) in examPaper">
          <div
            v-if="examType.studentExamQuestionsVOList.length>0"
            :key="index"
          >
            <div v-if="examType.type === '1'">
              <h3>单选题（共{{examType.studentExamQuestionsVOList.length}}题）</h3>
              <div
                v-for="(question, index) in examType.studentExamQuestionsVOList"
                :key="index"
              >
                <div class="question-block">
                  <div class="main-block">
                    <div>{{question.title}}({{question.score}}分)</div>
                    <div class="answer-block">
                      <RadioGroup vertical>
                        <Radio
                          v-if="question.optionA!==''"
                          :label="question.optionA"
                        ></Radio>
                        <Radio
                          v-if="question.optionB!==''"
                          :label="question.optionB"
                        ></Radio>
                        <Radio
                          v-if="question.optionC!==''"
                          :label="question.optionC"
                        ></Radio>
                        <Radio
                          v-if="question.optionD!==''"
                          :label="question.optionD"
                        ></Radio>
                        <Radio
                          v-if="question.optionE!==''"
                          :label="question.optionE"
                        ></Radio>
                      </RadioGroup>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div v-else-if="examType.type === '2'">
              <h3>多选题（共{{examType.studentExamQuestionsVOList.length}}题）</h3>
              <div
                v-for="(question,index) in examType.studentExamQuestionsVOList"
                v-bind:key="index"
              >
                <div class="question-block">
                  <div class="main-block">
                    <div>{{question.title}}({{question.score}}分)</div>
                    <div class="answer-block">
                      <CheckboxGroup v-model="question.answer">
                        <Checkbox
                          v-if="question.optionA!==''"
                          :label="question.optionA"
                          class="option-block"
                        ></Checkbox>
                        <Checkbox
                          v-if="question.optionB!==''"
                          :label="question.optionB"
                          class="option-block"
                        ></Checkbox>
                        <Checkbox
                          v-if="question.optionC!==''"
                          :label="question.optionC"
                          class="option-block"
                        ></Checkbox>
                        <Checkbox
                          v-if="question.optionD!==''"
                          :label="question.optionD"
                          class="option-block"
                        ></Checkbox>
                        <Checkbox
                          v-if="question.optionE!==''"
                          :label="question.optionE"
                          class="option-block"
                        ></Checkbox>
                      </CheckboxGroup>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!-- <div v-else-if="examType.type === '3'"></div> -->
            <div v-else-if="examType.type === '4'"></div>
            <!-- <div v-else-if="examType.type === '5'"></div> -->
            <div v-else-if="examType.type === '6'">
              <h3>分录题（共{{examType.studentExamQuestionsVOList.length}}题）</h3>
              <div
                v-for="(question,index) in examType.studentExamQuestionsVOList"
                v-bind:key="index"
              >
                <div class="question-block">
                  <label>{{index+1}}.</label>
                  <div class="main-block">
                    <div>{{question.title}}({{question.score}}分)</div>
                    <div class="answer-block">
                      <div class="option-block">
                        <Journalizing
                          type="test"
                          :data="journalizingData"
                          :subject-list="subjectList"
                        ></Journalizing>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </template>
        <!-- <div v-if="paper.map[2].length>0">
          <h3>多选题（共{{paper.map[2].length}}题）</h3>
          <div
            v-for="(question,index) in paper.map[2]"
            v-bind:key="index"
          >
            <div class="question-block">
              <label>{{index+1}}.</label>
              <div class="main-block">
                <div>{{question.title}}({{question.score}}分)</div>
                <div class="answer-block">
                  <div class="option-block">
                    <CheckboxGroup>
                      <Checkbox
                        :label="optionLabels[idx]+'.'+answer"
                        v-for="(answer,idx) in question.options"
                        v-bind:key="idx"
                        class="option-block"
                      ></Checkbox>
                    </CheckboxGroup>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div v-if="paper.map[4].length>0">
          <h3>判断题（共{{paper.map[4].length}}题）</h3>
          <div
            v-for="(question,index) in paper.map[4]"
            v-bind:key="index"
          >
            <div class="question-block">
              <label>{{index+1}}.</label>
              <div class="main-block">
                <div>{{question.title}}({{question.score}}分)</div>
                <div class="answer-block">
                  <div
                    class="option-block"
                    v-if="question.type!==6"
                  >
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
          </div>
        </div>
        <div v-if="paper.map[6].length>0">
          <h3>分录题（共{{paper.map[6].length}}题）</h3>
          <div
            v-for="(question,index) in paper.map[6]"
            v-bind:key="index"
          >
            <div class="question-block">
              <label>{{index+1}}.</label>
              <div class="main-block">
                <div>{{question.title}}({{question.score}}分)</div>
                <div class="answer-block">
                  <div class="option-block">
                    <Journalizing
                      type="test"
                      :data="journalizingData"
                      :subject-list="subjectList"
                    ></Journalizing>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div> -->
      </div>
    </div>
    <Button
      type="success"
      @click="exitFS"
    >SUBMIT</Button>
  </div>
</template>

<script>
import { getExamPaper, saveExamAnswer } from "@/api/student";
import Journalizing from "@/components/journalizing/table";
export default {
  name: "",
  props: {},
  components: {
    Journalizing
  },
  data() {
    return {
      isFullscreen: "",
      examPaper: [],
      journalizingData: [],
      subjectList: []
    };
  },
  created() {},
  mounted() {
    //禁止内容选择
    document.onselectstart = function() {
      return false;
    };
    // //禁止内容拖放
    document.ondragstart = function() {
      return false;
    };
    // //禁止右键弹出菜单
    document.oncontextmenu = function() {
      return false;
    };

    document.onkeydown = function() {
      if (
        window.event.keyCode == 116 || //屏蔽 F5
        window.event.keyCode == 122 || //屏蔽 F11
        (window.event.shiftKey && window.event.keyCode == 121)
      ) {
        //shift+F10
        window.event.keyCode = 0;
        window.event.returnValue = false;
      }
      if (window.event.altKey && window.event.keyCode == 115) {
        //屏蔽Alt+F4
        window.showModelessDialog(
          "about:blank",
          "",
          "dialogWidth:1px;dialogheight:1px"
        );
        return false;
      }
    };

    let param = this.$route.query;
    let Id = "";
    try {
      Id = param.examPaperId;
      timer = setTimeout(() => {
        this.$Message.warning({ content: "离考试结束还有10分钟", duration: 5 });
      }, 60 * 1000 * (param.duration - 10));
    } catch (error) {}
    this.getExamPaperWithId(Id);
    this.handleFullscreen();
  },
  computed: {},
  methods: {
    getExamPaperWithId(Id) {
      getExamPaper(Id)
        .then(res => {
          if (res.responseCode === "200") {
            console.log("res :", res.data);
            this.examPaper = res.data;
          } else {
          }
        })
        .catch(err => {
          console.log("err :", err);
        });
    },
    handleFullscreen() {
      let main = document.body;
      if (main.requestFullscreen) {
        main.requestFullscreen();
      } else if (main.mozRequestFullScreen) {
        main.mozRequestFullScreen();
      } else if (main.webkitRequestFullScreen) {
        main.webkitRequestFullScreen();
      } else if (main.msRequestFullscreen) {
        main.msRequestFullscreen();
      }
    },
    exitFS() {
      const answerSaveDetailsDTOList = this.handleAnswer();
      console.log("answerSaveDetailsDTOList :", answerSaveDetailsDTOList);
      try {
        if (document.exitFullscreen) {
          document.exitFullscreen();
        } else if (document.mozCancelFullScreen) {
          document.mozCancelFullScreen();
        } else if (document.webkitCancelFullScreen) {
          document.webkitCancelFullScreen();
        } else if (document.msExitFullscreen) {
          document.msExitFullscreen();
        }
      } catch (error) {
        console.log("error :", error);
      }
      // this.$router.push({ path: "/examScheduler/examScheduler" });
    },
    // 处理答案
    handleAnswer() {
      return this.examPaper.reduce((o, item) => {
        item.studentExamQuestionsVOList.forEach(ele => {
          let obj = {};
          if (ele.type !== "6") {
            obj = {
              questionsNo: ele.questionsNo,
              questionsType: ele.type
            };
            if (this.isArray(ele.answer)) {
              const arr = ele.answer.reduce((e, v) => {
                let str = "";
                str = v.split("、")[0];
                e.push(str);
                return e;
              }, []);
              obj.answer = arr.join(",");
            } else {
              obj.answer = ele.answer.split("、")[0];
            }
          } else {
            obj = {
              questionsNo: ele.questionsNo,
              questionsType: ele.type,
              studentEntryAnswerSaveDTOList: []
            };
          }
          o.push(obj);
        });
        return o;
      }, []);
    },
    isArray(obj) {
      return Object.prototype.toString.call(obj) === "[object Array]";
    }
  }
};
</script>

<style lang="less" scoped>
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
</style>
