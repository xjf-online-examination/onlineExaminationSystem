<template>
  <div>
    <tables
      ref="tables"
      search-place="top"
      v-model="tableData"
      :columns="columns"
      @on-delete="handleDelete"
    />
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
  </div>
</template>

<script>
import Tables from '@/components/tables';
import { getScoreList } from '@/api/student';

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
        title: '课程', key: 'courseName', align: 'center',
      },
      {
        title: '试卷名称', key: 'examPaperName', align: 'center',
      },
      {
        title: '考试时间', key: 'startTime', align: 'center',
      },
      {
        title: '分数', key: 'achievement', align: 'center',
      }],
      tableData: {
        list: [],
        count: 0,
      },
    };
  },
  methods: {
    getScoreList() {
      getScoreList(this.$store.state.user.userCode).then((res) => {
        if (res.responseCode === '200') {
          this.tableData = res.data;
        } else {
          this.tableData = {
            list: [],
            count: 0,
          };
        }
      });
    },
  },
  mounted() {
    this.getScoreList();
  },
};
</script>

<style lang="less" scoped>
.table-pagenation {
  display: flex;
  justify-content: flex-end;
}
</style>
