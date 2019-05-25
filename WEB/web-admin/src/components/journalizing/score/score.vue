<template>
  <div class="total">
    分值：
    <input type="number" class="score-cell" v-model="value" v-on:blur="handleEdit">
  </div>
</template>

<script>
export default {
  name: 'tablescore',
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
        type: 'score', index: this.index, rowData: this.rowData, field: this.field, value: this.total,
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
  .score-cell {
    border: none;
    padding: 0 10px;
    width: 60%;
  }
}
</style>
