<template>
  <div class="total">
    <Input type="text" class="text-cell" v-model="total" @on-blur="handleEdit"/>
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
      total: '',
    };
  },
  methods: {
    handleEdit() {
      const params = {
        index: this.index, rowData: this.rowData, field: this.field, value: this.total,
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
      height: 40px;
      border-radius: 0;
    }
  }
}
</style>
