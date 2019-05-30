<template>
  <div>
    这个是考试页面
    <Button
      type="success"
      @click="exitFS"
    >SUBMIT</Button>
  </div>
</template>

<script>
export default {
  name: "",
  props: {},
  data() {
    return {
      isFullscreen: ""
    };
  },
  created() {},
  mounted() {
    // let isFullscreen =
    //   document.fullscreenElement ||
    //   document.mozFullScreenElement ||
    //   document.webkitFullscreenElement ||
    //   document.fullScreen ||
    //   document.mozFullScreen ||
    //   document.webkitIsFullScreen;
    // isFullscreen = !!isFullscreen;
    // document.addEventListener("fullscreenchange", () => {
    //   this.$emit("input", !this.value);
    //   this.$emit("on-change", !this.value);
    // });
    // document.addEventListener("mozfullscreenchange", () => {
    //   this.$emit("input", !this.value);
    //   this.$emit("on-change", !this.value);
    // });
    // document.addEventListener("webkitfullscreenchange", () => {
    //   this.$emit("input", !this.value);
    //   this.$emit("on-change", !this.value);
    // });
    // document.addEventListener("msfullscreenchange", () => {
    //   this.$emit("input", !this.value);
    //   this.$emit("on-change", !this.value);
    // });
    // this.$emit("input", isFullscreen);
    this.handleFullscreen();
  },
  computed: {},
  methods: {
    handleFullscreen() {
      let main = document.body;
      if (main.requestFullscreen) {
        main.requestFullscreen();
      } else if (main.mozRequestFullScreen) {
        main.mozRequestFullScreen();
      } else if (main.webkitRequestFullScreen) {
        main.webkitRequestFullScreen();
      } else if (main.msRequestFullscreen) {
        main.msRequestFullscreen();
      }
    },
    exitFS() {
      console.log("11111 :", 11111);
      if (document.exitFullscreen) {
        document.exitFullscreen();
      } else if (document.mozCancelFullScreen) {
        document.mozCancelFullScreen();
      } else if (document.webkitCancelFullScreen) {
        document.webkitCancelFullScreen();
      } else if (document.msExitFullscreen) {
        document.msExitFullscreen();
      }
      this.$router.push({ path: "/examScheduler/examScheduler" });
    }
  }
};
</script>

<style lang='scss' scoped>
</style>
