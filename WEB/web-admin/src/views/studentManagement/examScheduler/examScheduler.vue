<template>
  <div>
    <tables
      ref="tables"
      search-place="top"
      v-model="tableData"
      :columns="columns"
    />
  </div>
</template>


<script>
import Tables from "@/components/tables";
import { examScheduleList } from "@/api/student";

export default {
  name: "eaxmScheduler",
  components: {
    Tables
  },
  data() {
    return {
      options: {
        disabledDate(date) {
          return date && date.valueOf() < Date.now() - 86400000;
        }
      },
      columns: [
        {
          title: "序号",
          type: "index",
          align: "center"
        },
        {
          title: "考试安排名称",
          key: "examScheduleTitle",
          align: "center"
        },
        {
          title: "考试时间",
          key: "startTime",
          align: "center"
        },
        {
          title: "考试时长",
          key: "duration",
          align: "center"
        },
        {
          title: "试卷名称",
          key: "examPaperName",
          align: "center"
        },
        {
          title: "操作",
          key: "handle",
          align: "center",
          button: [
            (h, params) =>
              h("div", [
                h(
                  "a",
                  {
                    props: {
                      type: "text",
                      size: "small"
                    },
                    on: {
                      click: () => {
                        this.onStartExam(params.tableData[params.index]);
                      }
                    }
                  },
                  "开始考试"
                )
              ])
          ]
        }
      ],
      tableData: []
    };
  },
  methods: {
    getExamSchedule() {
      examScheduleList(this.$store.state.user.userCode).then(res => {
        if (res.responseCode === "200") {
          this.tableData = res.data;
        } else {
          this.tableData = [];
        }
      });
    },
    onStartExam(data) {
      const startTimeStamp = Date.parse(new Date(data.startTime));
      const nowTimeStamp = Date.parse(new Date());
      if (startTimeStamp > nowTimeStamp) {
        this.$Message.warning({ content: "考试时间还没有开始", duration: 5 });
      } else {
        // fullScreen
        this.$router.push({ path: "/examPage" });
      }
        this.$router.push({ path: "/examPage" });

    }
  },
  mounted() {
    this.getExamSchedule();
  }
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
