<template>
  <div style="width:100%;height:300px;">
    <v-table
      is-vertical-resize
      :vertical-resize-offset="60"
      :multiple-sort="false"
      :min-height="350"
      :title-rows="tableConfig.titleRows"
      :columns="tableConfig.columns"
      :table-data="tableData"
      :cell-merge="cellMerge"
      :cell-edit-done="cellEditDone"
      @on-custom-comp="customCompFunc"
    ></v-table>
  </div>
</template>

<script>
import 'vue-easytable/libs/themes-base/index.css';
import { VTable } from 'vue-easytable';

export default {
  name: 'Journalizing',
  components: {
    VTable,
  },
  props: {
    type: {
      type: String,
      default: 'answer',
    },
    data: {
      type: Array,
    },
  },
  data() {
    return {
      tableConfig: {
        multipleSort: false,
        columns: [
          {
            field: 'summary',
            width: 100,
            titleAlign: 'center',
            columnAlign: 'center',
            isFrozen: true,
          },
          {
            field: 'subject1', width: 100, columnAlign: 'center', isFrozen: true, isEdit: true,
          },
          {
            field: 'subject2', width: 100, columnAlign: 'center', isFrozen: true, isEdit: true,
          },
          {
            field: 'yi1', width: 20, columnAlign: 'center', isFrozen: false,
          },
          {
            field: 'qianwan1', width: 20, columnAlign: 'center',
          },
          {
            field: 'baiwan1', width: 20, columnAlign: 'center',
          },
          {
            field: 'shiwan1', width: 20, columnAlign: 'center',
          },

          {
            field: 'wan1', width: 20, columnAlign: 'center',
          },
          {
            field: 'qian1', width: 20, columnAlign: 'center',
          },
          {
            field: 'bai1', width: 20, columnAlign: 'center',
          },

          {
            field: 'shi1', width: 20, columnAlign: 'center',
          },
          {
            field: 'yuan1', width: 20, columnAlign: 'center',
          },
          {
            field: 'jiao1', width: 20, columnAlign: 'center',
          },

          {
            field: 'fen1', width: 20, columnAlign: 'center',
          },
          {
            field: 'yi2', width: 20, columnAlign: 'center', isFrozen: false,
          },
          {
            field: 'qianwan2', width: 20, columnAlign: 'center',
          },
          {
            field: 'baiwan2', width: 20, columnAlign: 'center',
          },
          {
            field: 'shiwan2', width: 20, columnAlign: 'center',
          },

          {
            field: 'wan2', width: 20, columnAlign: 'center',
          },
          {
            field: 'qian2', width: 20, columnAlign: 'center',
          },
          {
            field: 'bai2', width: 20, columnAlign: 'center',
          },

          {
            field: 'shi2', width: 20, columnAlign: 'center',
          },
          {
            field: 'yuan2', width: 20, columnAlign: 'center',
          },
          {
            field: 'jiao2', width: 20, columnAlign: 'center',
          },

          {
            field: 'fen2', width: 20, columnAlign: 'center',
          },

        ],
        titleRows: [
          [{
            fields: ['summary'], title: '摘要', titleAlign: 'center', rowspan: 2,
          },
          {
            fields: ['subject1'], title: '一级科目', titleAlign: 'center', rowspan: 2,
          },
          {
            fields: ['subject2'], title: '二级科目', titleAlign: 'center', rowspan: 2,
          },
          {
            fields: ['yi1', 'qianwan1', 'baiwan1', 'shiwan1', 'wan1', 'qian1', 'bai1', 'shi1', 'yuan1', 'jiao1', 'fen1'], title: '借方金额', titleAlign: 'center', colspan: 11,
          },

          {
            fields: ['yi2', 'qianwan2', 'baiwan2', 'shiwan2', 'wan2', 'qian2', 'bai2', 'shi2', 'yuan2', 'jiao2', 'fen2'], title: '贷方金额', titleAlign: 'center', colspan: 11,
          }],

          [
            {
              fields: ['yi1'], title: '亿', titleAlign: 'center', titleCellClassName: 'th-bg',
            },
            { fields: ['qianwan1'], title: '千', titleAlign: 'center' },
            { fields: ['baiwan1'], title: '百', titleAlign: 'center' },
            { fields: ['shiwan1'], title: '十', titleAlign: 'center' },
            { fields: ['wan1'], title: '万', titleAlign: 'center' },
            { fields: ['qian1'], title: '千', titleAlign: 'center' },
            { fields: ['bai1'], title: '百', titleAlign: 'center' },
            { fields: ['shi1'], title: '十', titleAlign: 'center' },
            { fields: ['yuan1'], title: '元', titleAlign: 'center' },
            { fields: ['jiao1'], title: '角', titleAlign: 'center' },
            { fields: ['fen1'], title: '分', titleAlign: 'center' },
            { fields: ['yi2'], title: '亿', titleAlign: 'center' },
            { fields: ['qianwan2'], title: '千', titleAlign: 'center' },
            { fields: ['baiwan2'], title: '百', titleAlign: 'center' },
            { fields: ['shiwan2'], title: '十', titleAlign: 'center' },
            { fields: ['wan2'], title: '万', titleAlign: 'center' },
            { fields: ['qian2'], title: '千', titleAlign: 'center' },
            { fields: ['bai2'], title: '百', titleAlign: 'center' },
            { fields: ['shi2'], title: '十', titleAlign: 'center' },
            { fields: ['yuan2'], title: '元', titleAlign: 'center' },
            { fields: ['jiao2'], title: '角', titleAlign: 'center' },
            { fields: ['fen2'], title: '分', titleAlign: 'center' },
          ],
        ],
      },
      tableData: [],
    };
  },
  methods: {
    cellMerge(rowIndex, rowData, field) {
      if (field === 'summary') {
        if (rowIndex === this.tableData.length - 1) {
          return {
            colSpan: 3,
            rowSpan: 1,
            content: '',
            componentName: this.type === 'answer' ? 'tabletotal' : 'tablescore',
          };
        }
        return {
          colSpan: 1,
          rowSpan: this.tableData.length - 1,
          content: '',
          componentName: this.type === 'answer' ? 'tabletext' : 'tablescore',
        };
      }
      if (field === 'subject2') {
        return {
          colSpan: 1,
          rowSpan: 1,
          content: '',
          componentName: this.type === 'answer' ? 'tableselect' : 'tablescore',
        };
      }
      if (field === 'subject2') {
        return {
          colSpan: 1,
          rowSpan: 1,
          content: '',
          componentName: this.type === 'answer' ? 'tabletext' : 'tablescore',
        };
      }
      if (field === 'yi1') {
        return {
          colSpan: 11,
          rowSpan: 1,
          content: '',
          componentName: this.type === 'answer' ? 'tablemoney' : 'tablescore',
        };
      }
      if (field === 'yi2') {
        return {
          colSpan: 11,
          rowSpan: 1,
          content: '',
          componentName: this.type === 'answer' ? 'tablemoney' : 'tablescore',
        };
      }
    },
    // 单元格编辑回调
    cellEditDone(newValue, oldValue, rowIndex, rowData, field) {
      const { length } = this.tableData;
      if (field === 'summary') {
        if (rowIndex === length - 1) {
          this.tableData[rowIndex].total = newValue;
        } else {
          this.tableData[rowIndex].summary = newValue;
        }
      }
      if (field === 'yi1') {
        this.tableData[rowIndex][field] = newValue;
        this.tableData[rowIndex].debitAmount = newValue;
      }
      if (field === 'yi2') {
        this.tableData[rowIndex].debitAmount = newValue;
        this.tableData[rowIndex][field] = newValue;
      }
    },
    customCompFunc(params) {
      if (params.type === 'total') { // 合计
        this.tableData[params.index].total = params.value;
      } else if (params.type === 'summary') { // 合计
        this.tableData[params.index].summary = params.value;
      } else if (params.type === 'money') {
        if (params.field === 'yi1') {
          if (params.index === (this.tableData.length - 1)) {
            this.tableData[params.index].debitTotal = params.value;
          } else {
            this.tableData[params.index].debitAmount = params.value;
          }
        } else if (params.field === 'yi2') {
          if (params.index === (this.tableData.length - 1)) {
            this.tableData[params.index].creditTotal = params.value;
          } else {
            this.tableData[params.index].creditAmount = params.value;
          }
        }
      } else if (params.type === 'score') {
        if (params.field === 'summary') {
          if (params.index === (this.tableData.length - 1)) {
            this.tableData[params.index].totalScore = params.value;
          } else {
            this.tableData[params.index].summaryScore = params.value;
          }
        } else if (params.field === 'yi1') {
          if (params.index === (this.tableData.length - 1)) {
            this.tableData[params.index].debitTotalScore = params.value;
          } else {
            this.tableData[params.index].debitAmountScore = params.value;
          }
        } else if (params.field === 'yi2') {
          if (params.index === (this.tableData.length - 1)) {
            this.tableData[params.index].creditTotalScore = params.value;
          } else {
            this.tableData[params.index].creditAmountScore = params.value;
          }
        }
      }
    },
  },
  mounted() {
    this.tableData = this.data;
  },
};
</script>

<style lang="less">
.v-table-body-cell {
  padding: 0 !important;
}
.th-bg {
  background: url("../../../assets/images/table_bg.png");
}
.total-cell {
  border: none;
  padding: 0 10px;
  width: 80%;
}
.icon-btn {
  width: 20px;
  height: 20px;
}
</style>
