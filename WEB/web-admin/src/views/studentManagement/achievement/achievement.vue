<template>
  <div>
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
  name: 'achievement',
  components: {
    Tables,
  },
  data() {
    return {
      columns: [{
        title: '序号', type: 'index', align: 'center',
      },
      {
        title: '试卷名称', key: 'name', align: 'center',
      },
      {
        title: '考试试卷', key: 'time', align: 'center',
      },
      {
        title: '分数', key: 'score', align: 'center',
      }],
      tableData: [
        {
          no: 1, name: '大学英语', time: '2019-05-05', count: 88,
        },
      ],
    };
  },
};
</script>

<style lang="less" scoped>
</style>
