/*
 * @Author: xujiafei
 * @Date: 2019-05-18 02:31:58
 * @Last Modified by: xujiafei
 * @Last Modified time: 2019-05-23 18:18:16
 */
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
    <tables
      ref="tables"
      search-place="top"
      v-model="tableData"
      :columns="columns"
      @on-delete="handleDelete"
    />
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
    <Journalizing></Journalizing>
    <Modal v-model="modalVisible" :title="modalTitle" :closable="false" :mask-closable="false">
      <Form ref="classSearch" :model="question" :rules="rules">
        <FormItem prop="courseCode" label="课程编号">
          <Input type="text" v-model="question.code" placeholder="请输入课程编号"/>
        </FormItem>
        <FormItem prop="type" label="题目类型">
          <Select v-model="question.type">
            <Option value="1">单选题</Option>
            <Option value="2">多选题</Option>
            <Option value="3">不定向选择题</Option>
            <Option value="4">判断题</Option>
            <Option value="5">简答题</Option>
            <Option value="6">分录</Option>
          </Select>
        </FormItem>
        <FormItem prop="courseCode" label="题目">
          <Input type="textarea" v-model="question.code" placeholder="请输入题目"/>
        </FormItem>
        <FormItem
          prop="courseCode"
          label-position="top"
          label="选项"
          v-if="question.type==='1'||question.type==='2'||question.type=='3'||question.type==='4'"
        >
          <Row>
            <Col span="24">
              <FormItem
                v-for="(item, index) in options.items"
                :key="index"
                :label="optionLabels[index]"
                :prop="'items.' + index + '.value'"
                :rules="{required: true, message: '选项' + optionLabels[index] +'不能为空', trigger: 'blur'}"
              >
                <Row>
                  <Col span="16">
                    <Input type="textarea" v-model="item.value" placeholder="请输入选项"></Input>
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
        <FormItem prop="type" label="答案">
          <RadioGroup v-model="question.singleAnswer" v-if="question.type==='1'">
            <Radio label="A"></Radio>
            <Radio label="B"></Radio>
            <Radio label="C"></Radio>
            <Radio label="D"></Radio>
            <Radio label="E"></Radio>
          </RadioGroup>
          <CheckboxGroup
            v-model="question.multipleAnswer"
            v-if="question.type==='2'||question.type==='3'"
          >
            <Checkbox label="A"></Checkbox>
            <Checkbox label="B"></Checkbox>
            <Checkbox label="C"></Checkbox>
            <Checkbox label="D"></Checkbox>
            <Checkbox label="E"></Checkbox>
          </CheckboxGroup>
          <RadioGroup v-model="question.singleAnswer" v-if="question.type==='4'">
            <Radio label="是"></Radio>
            <Radio label="否"></Radio>
          </RadioGroup>
        </FormItem>
      </Form>
    </Modal>
  </div>
</template>


<script>
import Tables from '@/components/tables';

import Journalizing from '@/components/journalizing';

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
      tableData: [
        {
          no: 1, classCode: '191816', className: '19级财经1班', count: 35,
        },
      ],
      searchData: {
        code: '',
        title: '',
        courseCode: '',
        type: '',
      },
      question: {
        code: '',
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
      options: {
        items: [{
          value: '',
        }],
      },
      errMsg: '',
    };
  },
  methods: {
    handleDelete(params) {
      console.log(params);
    },
    exportExcel() {
      this.$refs.tables.exportCsv({
        filename: `table-${(new Date()).valueOf()}.csv`,
      });
    },
    handleSearch() {
      console.log(this.searchData);
    },
    handleReset(name) {
      this.$refs[name].resetFields();
      console.log(this.searchData);
    },
    onEdit(index) {
      console.log(index);
      /* todo */
    },
    onDelete(index) {
      console.log(index);
      /* TODO */
    },
    onPageChange(params) {
      console.log(params);
      /* todo */
    },
    onPageSizeChange(params) {
      console.log(params);
      /* todo */
    },
    onAdd() {
      this.modalVisible = true;
      this.modalTitle = '添加';
      this.isAdd = true;
    },
    handleAddOption() {
      if (this.options.items.length === 5) {
        this.errMsg = '选项不能大于5个';
      } else {
        this.errMsg = '';
        this.options.items.push({
          value: '',
        });
      }
    },
    handleRemoveOption(index) {
      // TODO:
      this.options.items.splice(index, 1);
    },
  },
  mounted() {
    // this.getTeacherList();
  },
  computed: {
    rules() {
      return {
        courseCode: this.courseCodeRules,
        type: this.typeRules,
      };
    },
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
