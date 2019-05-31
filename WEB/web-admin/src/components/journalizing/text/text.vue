<template>
  <div class="total">
    <Input type="textarea" class="text-cell" v-model="rowData[this.field]" @on-blur="handleEdit"/>
  </div>
</template>

<script>
export default {
  name: 'tabletext',
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
      this.value = this.rowData[this.field];
      const params = {
        type: 'text', index: this.index, rowData: this.rowData, field: this.field, value: this.value,
      };
      this.$emit('on-custom-comp', params);
    },
  },
};
</script>
<style lang="less">
.total {
  font-weight: bold;
  color: #333;
  .text-cell {
    width: 100%;
    border: none;
    .ivu-input {
      border: none;
      height: -webkit-fill-available;
      border-radius: 0;
    }
  }
}
</style>
