<template>
  <div class="total">
    分值：
    <input type="number" class="total-cell" v-model="value" v-on:blur="handleEdit">
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
<style>
.total {
  font-weight: bold;
  color: #333;
}
</style>
