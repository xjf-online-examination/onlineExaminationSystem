<template>
  <div class="total">
    合计：
    <input type="text" class="total-cell" v-model="total" v-on:blur="handleEdit">
  </div>
</template>

<script>
export default {
  name: 'tabletotal',
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
        type: 'total', index: this.index, rowData: this.rowData, field: this.field, value: this.total,
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
