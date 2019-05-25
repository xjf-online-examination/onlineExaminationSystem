<template>
  <div class="total">
    <input type="textarea" class="total-cell" v-model="total" v-on:blur="handleEdit">
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
<style>
.total {
  font-weight: bold;
  color: #333;
}
</style>
