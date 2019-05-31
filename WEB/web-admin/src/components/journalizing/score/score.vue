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
      value: null,
    };
  },
  methods: {
    handleEdit() {
      this.rowData[this.field] = this.value;
      const params = {
        type: 'score', index: this.index, rowData: this.rowData, field: this.field, value: this.value,
      };
      this.$emit('on-custom-comp', params);
    },
  },
  mounted() {
    if (this.field === 'summary') {
      this.value = this.rowData.summaryScore ? this.rowData.summaryScore : this.rowData.totalScore;
    } else if (this.field === 'yi1') {
      this.value = this.rowData.debitAmountScore ? this.rowData.debitAmountScore : this.rowData.debitTotalScore;
    } else if (this.field === 'yi2') {
      this.value = this.rowData.creditAmountScore ? this.rowData.creditAmountScore : this.rowData.creditTotalScore;
    } else if (this.field === 'subject1') {
      this.value = this.rowData.subject1Score;
    } else if (this.field === 'subject2') {
      this.value = this.rowData.subject2Score;
    }
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
