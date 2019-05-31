<template>
  <div>
    <tables ref="tables" v-model="tableData" :columns="columns"/>
  </div>
</template>

<script>
import Tables from '@/components/tables';
import {
  getScoreListByCode,
} from '@/api/teacher';

export default {
  name: 'studentScore',
  components: {
    Tables,
  },
  data() {
    return {
      code: this.$route.query.code,
      columns: [
        {
          title: '序号', type: 'index', align: 'center',
        },
        {
          title: '课程', key: 'courseName', align: 'center',
        },
        {
          title: '成绩', key: 'achievement', align: 'center',
        }],
      tableData: [],
    };
  },
  methods: {
    getScoreList() {
      getScoreListByCode(this.code).then((res) => {
        if (res.responseCode === '200') {
          this.tableData = res.data.list;
        } else {
          this.tableData = [];
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
