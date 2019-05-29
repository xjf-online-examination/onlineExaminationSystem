<template>
  <div class="cell-bg" @click="edit=true">
    <span v-if="!edit" :class="isNegative ? 'font-negative' : 'font-normal' ">{{positiveNumber}}</span>
    <Poptip trigger="focus" v-if="edit" style="width:100%">
      <Input v-model="value" type="number" @on-blur="handleEdit" autofocus/>
      <div slot="content">{{ formatNumber }}</div>
    </Poptip>
  </div>
</template>

<script>
export default {
  name: 'tableMoney',
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
      edit: false,
      money: '',
      isNegative: false,
      positiveNumber: '',
    };
  },
  methods: {
    handleEdit() {
      if (this.value !== '') {
        const val = parseInt(this.value);
        if (val < 0) {
          this.isNegative = true;
        } else {
          this.isNegative = false;
        }
        this.money = val * 100;
        this.positiveNumber = Math.abs(this.money);
      }
      this.edit = false;
      const params = {
        type: 'money', index: this.index, rowData: this.rowData, field: this.field, value: this.money,
      };
      this.$emit('on-custom-comp', params);
    },
  },
  computed: {
    formatNumber() {
      if (this.value === '') return 0;
      function parseNumber(str) {
        const re = /(?=(?!)(d{3})+$)/g;
        return str.replace(re, ',');
      }
      return parseNumber(this.value);
    },
  },
};
</script>
<style lang="less">
.cell-bg {
  background: url("../../../assets/images/table_bg.png");
  height: 40px;
  font-size: 13px;
  text-align: right;
  span {
    margin-right: -8px;
    letter-spacing: 13px;
    font-weight: bold;
  }
  .font-negative {
    color: #f00;
  }
  .font-normal {
    color: #000;
  }
  .ivu-poptip-rel {
    width: 100%;
    input {
      width: 100%;
      height: 40px;
      border: none;
      border-radius: 0;
    }
  }
}
</style>
