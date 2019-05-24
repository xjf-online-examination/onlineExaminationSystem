<template>
  <div>
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
          <Option value="1">单选题</Option>
          <Option value="2">多选题</Option>
          <Option value="3">不定向选择题</Option>
          <Option value="4">判断题</Option>
          <Option value="5">简答题</Option>
          <Option value="6">分录</Option>
        </Select>
      </FormItem>
      <FormItem>
        <Button type="primary" icon="ios-search" @click="handleSearch()">搜索</Button>
        <Button type="default" @click="handleReset('classSearch')" class="m-l-s">重置</Button>
      </FormItem>
    </Form>
    <Button type="primary" @click="onAdd()" class="m-b-s">添加</Button>
    <tables ref="tables" search-place="top" v-model="tableData.list" :columns="columns"/>
    <div class="table-pagenation m-t-s" v-if="tableData.count>0">
      <Page
        :total="100"
        show-elevator
        show-total
        show-sizer
        @on-change="onPageChange"
        @on-page-size-change="onPageSizeChange"
      />
    </div>
    <!-- <Journalizing></Journalizing> -->
    <Modal v-model="modalVisible" :title="modalTitle" :closable="false" :mask-closable="false">
      <Form ref="questionForm" :model="question" :rules="rules">
        <FormItem prop="courseCode" label="课程编号">
          <Input type="text" v-model="question.courseCode" placeholder="请输入课程编号"/>
        </FormItem>
        <FormItem prop="type" label="题目类型">
          <Select v-model="question.type">
            <Option :value="1">单选题</Option>
            <Option :value="2">多选题</Option>
            <Option :value="3">不定向选择题</Option>
            <Option :value="4">判断题</Option>
            <Option :value="5">简答题</Option>
            <Option :value="6">分录</Option>
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
                    ></Input>
                  </Col>
                  <Col span="4" offset="1">
                    <Button @click="handleRemoveOption(index)">删除</Button>
                  </Col>
                </Row>
              </FormItem>
              <FormItem>
                <Row>
                  <Col span="6">
                    <Button type="dashed" long @click="handleAddOption" icon="md-add">添加选项</Button>
                    <label class="err-info">{{errMsg}}</label>
                  </Col>
                </Row>
              </FormItem>
            </Col>
          </Row>
        </FormItem>
        <FormItem label="答案">
          <RadioGroup v-model="question.singleAnswer" v-if="question.type===1">
            <Radio
              :label="item"
              v-for="(item,index) in optionLabels.slice(0,options.length)"
              v-bind:key="index"
            ></Radio>
          </RadioGroup>
          <CheckboxGroup
            v-model="question.multipleAnswer"
            v-if="question.type===2||question.type===3"
          >
            <Checkbox
              :label="item"
              v-for="(item,index) in optionLabels.slice(0,options.length)"
              v-bind:key="index"
            ></Checkbox>
          </CheckboxGroup>
          <RadioGroup v-model="question.singleAnswer" v-if="question.type===4">
            <Radio label="是"></Radio>
            <Radio label="否"></Radio>
          </RadioGroup>
        </FormItem>
        <FormItem label="分值">
          <Input type="test" v-model="question.score" placeholder="请输入分值"/>
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
import Tables from '@/components/tables';

import Journalizing from '@/components/journalizing';
import {
  addQuestion, editQuestion, getQuestionList,
} from '@/api/teacher';

export default {
  name: 'classes',
  components: {
    Tables,
    Journalizing,
  },
  props: {
    courseCodeRules: {
      type: Array,
      default: () => [
        { required: true, message: '工号不能为空', trigger: 'blur' },
      ],
    },
    typeRules: {
      type: Array,
      default: () => [
        { required: true, message: '教师姓名不能为空', trigger: 'blur' },
      ],
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
          title: '试题类型', key: 'type', align: 'center',
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
      },
      question: {
        courseCode: '',
        type: 1,
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
      },
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
      this.modalVisible = true;
      this.modalTitle = '修改';
      this.isAdd = false;
      this.question = Object.assign({}, this.tableData.list[index]);
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
    },
    save(name) {
      this.$refs[name].validate((valid) => {
        if (valid) {
          if (this.isAdd) {
            addQuestion(this.question).then((res) => {
              console.log(res);
              if (res.responseCode === '201') {
                this.getQustionList();
                this.$Notice.success({ title: '添加成功' });
              } else {
                this.$Notice.success({ title: '添加失败' });
              }
              this.modalVisible = false;
            });
          } else {
            editQuestion(this.question).then((res) => {
              console.log(res);
              if (res.responseCode === '201') {
                this.getQustionList();
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
        } else {
          this.tableData = { list: [], count: 0 };
        }
      });
    },
  },
  mounted() {
    this.getQuestionList();
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
