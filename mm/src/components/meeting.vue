<template>
  <el-container class="contain">
    <el-aside width="40%" class="contain_left">
      <div class="info_contain">
        <div class="item_info" v-for="(item, index) in infoList" :key="index">
          <div v-if="item.type == 0" style="display: flex">
            <span class="local"
              ><p class="world">{{ item.sendername }}</p></span
            >
            <div class="tip">{{ item.msg }}</div>
          </div>
          <div v-if="item.type == 1" style="display: flex">
            <span class="remote"
              ><p class="world">{{ item.sendername }}</p></span
            >
            <div class="tip">{{ item.msg }}</div>
          </div>
        </div>
      </div>
      <div class="controller">
        <button @click="startVideo(true)">视频通话</button>
        <button @click="clear()">清空聊天记录</button>
      </div>
      <textarea
        class="input_place"
        v-model="msg"
        @focus.once="tip()"
        @keyup.enter="add()"
      />
    </el-aside>
    <el-container >
      <el-header height="120px" class="headers"
        >
        <div>
        <file></file>
        </div>
        </el-header
      >
      <el-main class="main_contain">
        <div class=" borders">
        <videopage
          v-if="buttons"
          :userid="userid"
          :reciverid="reciverid"
          :conn="conn"
        ></videopage>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>
<script>
import videopage from "./video";
import file from "./fileAction"
export default {
  components: { videopage,file},
  data() {
    return {
      buttons: false,
      userid: "",
      reciverid: "",
      conn: "",
      timer: null,
      msg: "",
      infoList: [],
    };
  },
  created() {
    this.infoList = this.$global.initInfo();
    this.$global.inits(window.sessionStorage.getItem("localuserid"));
  },
  mounted() {
    setInterval(() => {
      if (this.$global.getVideo()) {
        console.log("a");
        this.conn = false;
        this.buttons = true;
        this.userid = window.sessionStorage.getItem("localuserid");
        this.reciverid = JSON.parse(
          window.sessionStorage.getItem("remoteuser")
        ).id;
        window.sessionStorage.removeItem("isStartVideo");
      }
    }, 2000);
    this.timer = setInterval(() => {
      this.infoList = this.$global.initInfo();
    }, 500);
  },
  destroyed() {
    clearInterval(this.timer);
    this.timer = null;
  },
  methods: {
    startVideo(istrue) {
      this.conn = istrue;
      this.buttons = true;
      this.userid = window.sessionStorage.getItem("localuserid");
      this.reciverid = JSON.parse(
        window.sessionStorage.getItem("remoteuser")
      ).id;
      this.$global.sendMsg("开启视频通话");
    },
    flesh() {
      console.log("flesh");
      console.log(this.$global.initInfo());
      this.infoList = this.$global.initInfo();
    },
    tip() {
      this.$message.info("按enter键发送");
    },
    add() {
      if (this.msg.trim() == "") {
        this.$message.error("消息不能为空");
        return;
      }
      console.log("send msg");
      this.$global.sendMsg(this.msg);
      this.msg = "";
    },
    clear() {
      this.$global.removeInfo(
        JSON.parse(window.sessionStorage.getItem("remoteuser")).id
      );
    },
  },
};
</script>
<style scoped>
.main_contain {
  background-color: beige;
  padding: 0px;
  overflow: hidden;
}
.controller {
  height: 24px;
  padding-top: 4px;
  padding-bottom: 4px;
  border: 1px solid aqua;
  background-color: aqua;
}
.borders{
  width: 520px;
  height: 520px;
  border: 1px solid black;
}
.contain {
  height: 100%;
  width: 100%;
}
.contain_left {
  overflow: hidden;
  height: 100%;
  border: 3px solid aqua;
}
.info_contain {
  height: 80%;
  overflow-y: auto;
}
.info_contain::-webkit-scrollbar {
  width: 10px;
}
.headers{
  padding: 0px;
  padding-top: 8px;
  
}
.item_info {
  margin: 8px;
  display: flex;
  margin-bottom: 14px;
}
.tip {
  box-shadow: grey 0px 1px 2px 0px;
  border-radius: 4px;
  display: inline-block;
  padding: 8px;
  margin-left: 8px;
  margin-top: 8px;
  height: 20px;
  table-layout: fixed;
  word-wrap: break-all;
  word-break: normal;
  overflow: hidden;
}
.remote {
  border-radius: 50%;
  width: 50px;
  height: 50px;
  display: inline-block;
  text-align: center;
  background-color: aquamarine;
}
.local {
  border-radius: 50%;
  width: 50px;
  height: 50px;
  display: inline-block;
  text-align: center;
  background-color: blue;
}
.world {
  font-size: 12px;
  text-align: center;
}
.input_place {
  outline: none;
  border: 1px solid aqua;
  width: 99%;
  height: 18%;
  float: left;
}
</style>>
