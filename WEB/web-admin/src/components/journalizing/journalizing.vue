<template>
  <div style="width:100%;height:300px;">
    <Table :columns="columns11" :data="data10" border height="500" size="small"></Table>
  </div>
</template>

<script>
export default {
  name: 'Journalizing',
  data() {
    return {
      columns11: [
        {
          title: '摘要',
          key: 'name',
          align: 'center',
          width: 100,
          fixed: 'left',
        },
        {
          title: '一级科目',
          align: 'center',
          width: 100,
          fixed: 'left',
        },
        {
          title: '二级科目',
          align: 'center',
          width: 100,
          fixed: 'left',
        },
        {
          title: '借方金额',
          key: 'from',
          align: 'center',
          width: 600,
          children: [
            {
              title: '亿',
              key: 'yi',
              align: 'center',
              className: 'no-padder',
            },
            {
              title: '千',
              key: 'qianwan',
              align: 'center',
              className: 'no-padder',
            },
            {
              title: '百',
              key: 'baiwan',
              align: 'center',
              className: 'no-padder',
            },
            {
              title: '十',
              key: 'shiwan',
              align: 'center',
              className: 'no-padder',
            },
            {
              title: '万',
              key: 'wan',
              align: 'center',
              className: 'no-padder',
            },
            {
              title: '千',
              key: 'qian',
              align: 'center',
              className: 'no-padder',
            },
            {
              title: '百',
              key: 'bai',
              align: 'center',
              className: 'no-padder',
            },
            {
              title: '十',
              key: 'shi',
              align: 'center',
              className: 'no-padder',
            },
            {
              title: '元',
              key: 'yuan',
              align: 'center',
              className: 'no-padder',
            },
            {
              title: '角',
              key: 'jiao',
              align: 'center',
              className: 'no-padder',
            },
            {
              title: '分',
              key: 'fen',
              align: 'center',
              className: 'no-padder',
            },
          ],
        },
        {
          title: '贷方金额',
          key: 'to',
          align: 'center',
          width: 600,
          children: [
            {
              title: '亿',
              key: 'yi',
              align: 'center',
              className: 'no-padder',
            },
            {
              title: '千',
              key: 'qianwan',
              align: 'center',
              className: 'no-padder',
            },
            {
              title: '百',
              key: 'baiwan',
              align: 'center',
              className: 'no-padder',
            },
            {
              title: '十',
              key: 'shiwan',
              align: 'center',
              className: 'no-padder',
            },
            {
              title: '万',
              key: 'wan',
              align: 'center',
              className: 'no-padder',
            },
            {
              title: '千',
              key: 'qian',
              align: 'center',
              className: 'no-padder',
            },
            {
              title: '百',
              key: 'bai',
              align: 'center',
              className: 'no-padder',
            },
            {
              title: '十',
              key: 'shi',
              align: 'center',
              className: 'no-padder',
            },
            {
              title: '元',
              key: 'yuan',
              align: 'center',
              className: 'no-padder',
            },
            {
              title: '角',
              key: 'jiao',
              align: 'center',
              className: 'no-padder',
            },
            {
              title: '分',
              key: 'fen',
              align: 'center',
              className: 'no-padder',
            },
          ],
        },
      ],
      data10: [],
    };
  },
  mounted() {
    const data = [];
    for (let i = 0; i < 20; i++) {
      data.push({
        key: i,
        name: '分录摘要',
        from: '我',
        to: '你',

      });
    }
    this.data10 = data;
  },
};
</script>

<style lang="less">
.no-padder .ivu-table-cell {
  padding: 0;
}
</style>
