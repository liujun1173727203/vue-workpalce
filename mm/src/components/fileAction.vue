<template>
  <div>
    <div class="contain">
      <div
        class="show_file"
        v-for="(item, index) in fileNameList"
        :key="index"
        @click="download(index)"
      >
        <img class="img" src="../assets/文件.png" />
        <p class="img_info">{{ item.split("-")[2] }}</p>
      </div>
      <div v-if="isshow" class="show_file" @click="turnTo()">
        <img id="add" class="img" src="../assets/add.png" />
        <p class="img_info">添加文件</p>
      </div>
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
    return {
      isshow:false,
      file_id:'',
      fileNameList: [],
    };
  },
  created() {
    if(window.sessionStorage.getItem("localuseridentity")==0){
      this.file_id =JSON.parse(window.sessionStorage.getItem("remoteuser")).id
    }else{
      this.file_id = window.sessionStorage.getItem("localuserid")
      this.isshow =true
    }
    this.checkfile();
  },
  methods: {
    checkfile() {
      this.$http({
        url: "/api/checkload",
        params: {
          id:this.file_id,
        },
      }).then((res) => {
        this.fileNameList = res.data;
      });
    },
    download(index) {
      this.$confirm('选择操作',{
        distinguishCancelAndClose:true,
        confirmButtonText:'下载',
        cancelButtonText:'删除',
      }).then(()=>{
        var a = document.createElement("a");
            a.href = "/api/download?name=" + this.fileNameList[index];
            console.log(a.href);
            a.click();
      })
      .catch(action=>{
        if(action ==="cancel"){
          this.$http({
            url:'/api/deletefile',
            params:{
              name:this.fileNameList[index]
            }
          }).then(res=>{
            this.$message(res.data)
          })
          this.$router.go(0)
        }
      })
      
    },
    turnTo() {
      document.getElementById("fileExport").click();
    },
    handleFileChange(event) {
      console.log(event.target.files);
      var formdata = new FormData();
      formdata.append("file", event.target.files[0]);
      formdata.append("userinfo", window.sessionStorage.getItem("localuserid"));
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
        .catch((err) => {});
      this.$router.go(0);
    },
  },
};
</script>
<style scoped>
.contain {
  display: flex;
  height: 100px;
  overflow-x: hidden;
  flex-flow: wrap;
}
.contain::-webkit-scrollbar {
  width: 10px;
}
.show_file {
  padding-left: 8px;
}
.img {
  width: 80px;
  height: 80px;
}
.img_info {
  margin: 0px;
  margin-top: -7px;
  height: 20px;
  width: 80px;
  text-align: center;
  word-break: break-all;
}
</style>