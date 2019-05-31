<template>
  <div style="width:100%;">
    <v-table
      is-vertical-resize
      :vertical-resize-offset="60"
      :multiple-sort="false"
      :min-height="350"
      :title-rows="tableConfig.titleRows"
      :columns="tableConfig.columns"
      :table-data="tableData"
      :cell-merge="cellMerge"
      @on-custom-comp="customCompFunc"
      @on-custom-operation="rowEdit"
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
    subjectList: {
      type: Array,
    },
    index: {
      type: Number,
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
            field: 'subject1', width: 120, columnAlign: 'center', isFrozen: true,
          },
          {
            field: 'subject2', width: 100, columnAlign: 'center', isFrozen: true,
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
            componentName: (this.type === 'answer' || this.type === 'test') ? 'tabletotal' : 'tablescore',
          };
        }
        rowData.rows = this.tableData.length - 1;
        return {
          colSpan: 1,
          rowSpan: this.tableData.length - 1,
          content: '',
          componentName: (this.type === 'answer' || this.type === 'test') ? 'tabletext' : 'tablescore',
        };
      }
      if (field === 'subject1') {
        rowData.list = this.subjectList;
        return {
          colSpan: 1,
          rowSpan: 1,
          content: '',
          componentName: (this.type === 'answer' || this.type === 'test') ? 'tableselect' : 'tablescore',
        };
      }
      if (field === 'subject2') {
        return {
          colSpan: 1,
          rowSpan: 1,
          content: '',
          componentName: (this.type === 'answer' || this.type === 'test') ? 'tabletext' : 'tablescore',
        };
      }
      if (field === 'yi1') {
        return {
          colSpan: 11,
          rowSpan: 1,
          content: '',
          componentName: (this.type === 'answer' || this.type === 'test') ? 'tablemoney' : 'tablescore',
        };
      }
      if (field === 'yi2') {
        return {
          colSpan: 11,
          rowSpan: 1,
          content: '',
          componentName: (this.type === 'answer' || this.type === 'test') ? 'tablemoney' : 'tablescore',
        };
      }
      if (this.type === 'answer' && field === 'operation' && rowIndex === this.tableData.length - 1) {
        return {
          colSpan: 1,
          rowSpan: 1,
          content: '',
          componentName: '',
        };
      }
    },
    customCompFunc(params) {
      if (params.type === 'add' || params.type === 'delete') {
        this.$emit('rowedit', params);
      } else {
        params.outerIndex = this.index;
        this.$emit('tablechange', params);
      }
    },
    rowEdit(params) {
      this.$emit('rowedit', params);
    },
  },
  mounted() {
    this.tableData = this.data;
    if (this.type === 'answer') {
      this.tableConfig.columns.push({
        field: 'operation',
        title: '操作',
        width: 200,
        titleAlign: 'center',
        columnAlign: 'center',
        componentName: 'tableoperation',
      });
      this.tableConfig.titleRows[0].push({
        fields: ['operation'], title: '操作', titleAlign: 'center', rowspan: 2,
      });
    }
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
