<template>
  <div>
    <span>
      <a href @click.stop.prevent="addRow(rowData,index)">添加</a>&nbsp;
      <a href @click.stop.prevent="deleteRow(rowData,index)">删除</a>
    </span>
  </div>
</template>

<script>
export default {
  name: 'tableoperation',
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
  methods: {
    addRow() {
      const params = { type: 'add', index: this.index, rowData: this.rowData };
      this.$emit('on-custom-comp', params);
    },

    deleteRow() {
      const params = { type: 'delete', index: this.index };
      this.$emit('on-custom-comp', params);
    },
  },
};
</script>

<style lang="scss" scoped>
</style>
