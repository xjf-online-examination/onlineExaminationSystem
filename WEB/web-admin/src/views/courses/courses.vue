<template>
  <div>
    <Form ref="classSearch" :model="searchData" inline>
      <FormItem prop="classCode" label="试卷编号" label-position="left" class="search-flex">
        <Input type="text" v-model="searchData.classCode" placeholder="试卷编号"/>
      </FormItem>
      <FormItem prop="className" label="试卷名称" label-position="left" class="search-flex">
        <Input type="text" v-model="searchData.className" placeholder="试卷名称"/>
      </FormItem>
      <FormItem prop="className" label="所属课程" label-position="left" class="search-flex">
        <Input type="text" v-model="searchData.className" placeholder="所属课程"/>
      </FormItem>
      <FormItem>
        <Button type="primary" icon="ios-search" @click="handleSearch()">搜索</Button>
        <Button type="default" @click="handleReset('classSearch')" class="m-l-s">重置</Button>
      </FormItem>
    </Form>
    <div class="m-b-s">
      <Button type="primary" @click="onAdd()">添加</Button>
      <Button type="primary" @click="onImport()" class="m-l-s">导入</Button>
    </div>
    <tables
      ref="tables"
      search-place="top"
      v-model="tableData"
      :columns="columns"
      @on-delete="handleDelete"
    />
    <div class="table-pagenation m-t-s">
      <Page
        :total="100"
        show-elevator
        show-total
        show-sizer
        @on-change="onPageChange"
        @on-page-size-change="onPageSizeChange"
      />
    </div>
  </div>
</template>


<script>
import Tables from '@/components/tables';

export default {
  name: 'classes',
  components: {
    Tables,
  },
  data() {
    return {
      columns: [
        {
          title: '序号', type: 'index', align: 'center',
        },
        {
          title: '试卷编号', key: 'paperCode', align: 'center',
        },
        {
          title: '试卷名称', key: 'paperName', align: 'center',
        },
        {
          title: '试卷题量', key: 'paperCount', align: 'center',
        },
        {
          title: '所属课程', key: 'course', align: 'center',
        },
        {
          title: '出卷人', key: 'user', align: 'center',
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
        courseName: '',
        courseCode: '',
      },
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
      /* todo */
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
