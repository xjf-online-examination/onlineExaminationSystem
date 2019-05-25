<template>
  <div class="my-table">
    <Select v-model="value" @on-change="handleEdit">
      <Option
        v-for="item in rowData.list"
        :value="item.code"
        :key="item.code"
      >{{ item.code+' : '+item.name }}</Option>
    </Select>
  </div>
</template>

<script>
export default {
  name: 'tableselect',
  props: {
    rowData: {
      type: Object,
    },
    field: {
      type: String,
    },
    index: {
      type: Number,
    },
  },
  data() {
    return {
      value: '',
    };
  },
  methods: {
    handleEdit() {
      const params = {
        type: 'subject1', index: this.index, rowData: this.rowData, field: this.field, value: this.total,
      };
      this.$emit('on-custom-comp', params);
    },
  },
};
</script>
<style lang="less">
.my-table {
  .ivu-select-single .ivu-select-selection {
    height: 40px;
    border: none;
    border-radius: 0;
  }
}
</style>
