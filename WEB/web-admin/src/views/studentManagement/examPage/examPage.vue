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
import { getExamPaper, saveExamAnswer } from "@/api/student";
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
    //禁止内容选择
    document.onselectstart = function() {
      return false;
    };
    // //禁止内容拖放
    document.ondragstart = function() {
      return false;
    };
    // //禁止右键弹出菜单
    document.oncontextmenu = function() {
      return false;
    };

    document.onkeydown = function() {
      if (
        window.event.keyCode == 116 || //屏蔽 F5
        window.event.keyCode == 122 || //屏蔽 F11
        (window.event.shiftKey && window.event.keyCode == 121)
      ) {
        //shift+F10
        window.event.keyCode = 0;
        window.event.returnValue = false;
      }
      if (window.event.altKey && window.event.keyCode == 115) {
        //屏蔽Alt+F4
        window.showModelessDialog(
          "about:blank",
          "",
          "dialogWidth:1px;dialogheight:1px"
        );
        return false;
      }
    };

    let param = this.$route.query;
    let Id = "";
    try {
      if (param.examPaperId) {
        Id = param.examPaperId;
      }
    } catch (error) {}
    this.getExamPaperWithId(Id);
    this.handleFullscreen();
  },
  computed: {},
  methods: {
    getExamPaperWithId(Id) {
      getExamPaper(Id).then(res => {
        if (res.responseCode === "200") {
        } else {
        }
      });
    },
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
