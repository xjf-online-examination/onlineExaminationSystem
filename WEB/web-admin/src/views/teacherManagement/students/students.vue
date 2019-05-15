<template>
  <div>
    <Form ref="classSearch" :model="searchData" inline>
      <FormItem prop="classCode" label="学号" label-position="left" class="search-flex">
        <Input type="text" v-model="searchData.studentNo" placeholder="学号"/>
      </FormItem>
      <FormItem prop="className" label="姓名" label-position="left" class="search-flex">
        <Input type="text" v-model="searchData.name" placeholder="姓名"/>
      </FormItem>
      <FormItem prop="className" label="班级" label-position="left" class="search-flex">
        <Input type="text" v-model="searchData.className" placeholder="班级"/>
      </FormItem>
      <FormItem>
        <Button type="primary" icon="ios-search" @click="handleSearch()">搜索</Button>
        <Button type="default" @click="handleReset('classSearch')" class="m-l-s">重置</Button>
      </FormItem>
    </Form>
    <div class="m-b-s">
      <Button type="primary" @click="onAdd()">添加</Button>
      <Button type="primary" @click="onImport()" class="m-l-s">导入</Button>
      <Button type="primary" @click="onDownload()" class="m-l-s">下载模板</Button>
    </div>
    <tables ref="tables" v-model="tableData" :columns="columns" @on-delete="handleDelete"/>
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
  name: 'students',
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
          title: '学号', key: 'studentNo', align: 'center',
        },
        {
          title: '姓名', key: 'name', align: 'center',
        },
        {
          title: '班级', key: 'class', align: 'center',
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
                    this.onDelete(params.index);
                  },
                },
              }, '成绩'),
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
              }, '修改密码'),
            ]),
          ],
        }],
      tableData: [
        {
          id: 1, studentNo: '190814', name: '王晓军', className: '19级财经1班',
        },
      ],
      searchData: {
        className: '',
        studentNo: '',
        name: '',
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
