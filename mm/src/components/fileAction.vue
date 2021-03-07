<template>
  <div>
    <div class="contain">
      <div class="show_file">
        <img class="img" src="../assets/文件.png" />
      </div>
      <div class="show_file">
        <img class="img" src="../assets/文件.png" />
      </div>

      <div class="show_file">
        <img class="img" src="../assets/文件.png" />
      </div>

      <div class="show_file">
        <img class="img" src="../assets/文件.png" />
      </div>

      <div class="show_file">
        <img class="img" src="../assets/文件.png" />
      </div>

      <div class="show_file">
        <img class="img" src="../assets/文件.png" />
      </div>

      <img id="add" src="../assets/add.png" @click="turnTo()" />
    </div>
    <input
      type="file"
      style="display: none"
      id="fileExport"
      @change="handleFileChange($event)"
      ref="inputer"
    />
  </div>
</template>
<script>
export default {
  name: "file",
  data() {
    return {};
  },
  methods: {
    turnTo() {
      document.getElementById("fileExport").click();
    },
    handleFileChange(event) {
      console.log(event.target.files);
      var formdata = new FormData();
      formdata.append("file", event.target.files[0]);
      formdata.append("userinfo",window.sessionStorage.getItem("localuserid"))
    //   formdata.append("userinfo", 10);
      this.upload(formdata);
    },
    upload(formdata) {
      this.$http({
        url: "/api/upload",
        method: "POST",
        headers: {
          "Content-Type": "multipart/form-data",
        },
        data: formdata,
      })
        .then((res) => {
          this.$message(res.data);
        })
        .catch((err) => {
          this.$message(err)
        });
    },
  },
};
</script>
<style scoped>
#add {
  width: 100px;
}
.contain {
  display: flex;
      height: 100px;
    overflow-x: hidden;
  flex-flow: wrap;
}
.contain::-webkit-scrollbar {
  width: 10px;
}
.show_file{
    padding-left: 8px;
}
.img {
  width: 100px;
  height: 100px;
}
</style>