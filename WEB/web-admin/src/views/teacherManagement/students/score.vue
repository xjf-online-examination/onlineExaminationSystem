<template>
  <div>
    <tables ref="tables" v-model="tableData" :columns="columns"/>
  </div>
</template>

<script>
import Tables from '@/components/tables';
import {
  getScoreListById,
} from '@/api/teacher';

export default {
  name: 'studentScore',
  components: {
    Tables,
  },
  data() {
    return {
      id: this.$route.query.id,
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
      getScoreListById(this.id).then((res) => {
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
