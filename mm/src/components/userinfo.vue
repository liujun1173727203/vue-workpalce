<template>
  <el-container>
    <el-header class="localUser" style="height: 100px">
      <div class="paicentTips">
        <h5>姓名：{{ name }}</h5>
        <div style="font-size: 10px">
          <span>id:{{ id }}</span>
          <span>phone:{{ phone }}</span>
        </div>
      </div>
      <div class="inform">
        <el-dropdown @command="handleCommand">
          <span class="el-dropdown-link">
            通知<i class="el-icon-arrow-down el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item
              command="a"
              v-for="(item, index) in information"
              :key="index"
              @click.native="jump(item.id)"
              >{{ item.name }}
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </el-header>
    <el-divider content-position="left" style="font-size: 12px"
      >选择医生</el-divider
    >
    <el-main class="main_contain">
      <div v-for="(item, index) in doctorList" :key="index" class="doctorTips">
        <div class="red_cricle" v-if="isShow(item.id)"></div>
        <div @click="connection(index)">
          <h5>医生姓名：{{ item.name }}</h5>
          <div style="font-size: 10px">
            <span>id:{{ item.id }}</span>
            <span>phone:{{ item.phone }}</span>
          </div>
        </div>
      </div>
    </el-main>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      information: [],
      toRead: [],
      name: "",
      id: "",
      phone: "未设置",
      doctorList: [],
    };
  },
  created() {
    this.id = window.sessionStorage.getItem("localuserid");
    this.name = window.sessionStorage.getItem("localusername");
    this.phone = window.sessionStorage.getItem("localuserphone");
    this.$global.inits(this.id);
    this.$http({
      url: "/api/get_doctors",
      method: "GET",
    })
      .then((res) => {
        this.doctorList = res.data;
        console.log(this.doctorList);
      })
      .catch((err) => {
        this.$message.error("network error");
      });
    setInterval(() => {
      this.information = this.$global.createS();
    }, 2000);
    // this.$worker.run
    console.log(this.$global.createS());
    console.log(this.information);
  },
  methods: {
    handleCommand(command) {
      // this.$message("click on item " + command);
    },
    jump(id) {
      console.log("jump");
      this.$http({
        url: "/api/finduser/" + id,
        method: "GET",
      }).then((res) => {
        console.log(res.data);
        var t = res.data;
        var remoteuser = {
          id: t.id,
          name: t.name,
          phone: t.phone,
          identity: t.identity,
        };
        window.sessionStorage.setItem("remoteuser", JSON.stringify(remoteuser));
        this.$global.delSender(t.name, t.id);
        this.$router.push("/meeting");
      });
    },
    isShow(id) {
      for (var i in this.toRead) {
        if (this.toRead[i] == id) {
          return true;
        }
      }
      return false;
    },
    connection(index) {
      console.log(this.doctorList[index]);
      window.sessionStorage.setItem(
        "remoteuser",
        JSON.stringify(this.doctorList[index])
      );
      this.$router.push("/meeting");
    },
  },
};
</script>
<style scoped>
.inform {
  background-color: aqua;
  width: 100px;
  height: 32px;
  margin-top: 32px;
  text-align: center;
  padding-top: 8px;
  border-radius: 5px;
}
h5 {
  margin: 8px;
  margin-bottom: 16px;
  margin-left: 0px;
}
.red_cricle {
  border: 1px solid red;
  border-radius: 50%;
  position: relative;
  z-index: 1;
  float: right;
  margin-bottom: -18px;
  width: 8px;
  height: 8px;
  background-color: red;
}
.localUser {
  margin-bottom: 24px;
  display: flex;
  margin-right: 10%;
  justify-content: space-between;
}
.main_contain {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: left;
}
.el-dropdown-link {
  cursor: pointer;
  color: #409eff;
}
.el-icon-arrow-down {
  font-size: 12px;
}
.doctorTips {
  border-radius: 10px;
  padding: 8px;
  padding-left: 16px;
  margin: 8px;
  width: 200px;
  height: 80px;
  background-color: aqua;

  justify-content: space-around;
}
.paicentTips {
  border-radius: 10px;
  padding: 8px;
  padding-left: 16px;
  margin: 16px;
  width: 200px;
  height: 80px;
  background-color: aqua;

  justify-content: space-around;
}
</style>