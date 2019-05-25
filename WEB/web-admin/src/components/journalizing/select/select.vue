<template>
  <div class="total">
    <Select v-model="value" @on-change="handleEdit">
      <Option v-for="item in objects" :value="item.value" :key="item.value">{{ item.name }}</Option>
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
      total: '',
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
<style>
.total {
  font-weight: bold;
  color: #333;
}
</style>
