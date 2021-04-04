
<template>
  <div>
    <video
      hidden
      class="large_video"
      src=""
      id="iv2"
      autoplay="autoplay"
      style="background-color: black"
    ></video>
    <video
      hidden
      class="small_video"
      src=""
      id="iv"
      autoplay="autoplay"
      style="background-color: black"
    ></video>
  </div>
</template>
<script>
import audiopage from "./audiopage";
import record from "./js/record";
export default {
  components: { audiopage },
  removeInfo,
  initInfo,
  delSender,
  inits,
  sendMsg,
  getVideo,
  createS,
};
var socket = null;
function setVideo(flag) {
  window.sessionStorage.setItem("isStartVideo", flag);
}

function getVideo() {
  return window.sessionStorage.getItem("isStartVideo");
}
//初始化socket对象
function inits(userid) {
  console.log(userid);
  console.log("start websocket");
  socket = new WebSocket("ws://127.0.0.1:8080/msgServer/" + userid);
  socket.onopen = function () {
    console.log("Server open");
  };
  socket.onclose = function () {
    console.log("server close");
  };
  socket.onmessage = function (res) {
    var txt = JSON.parse(res.data);
    var a = {
      senderId: txt.senderId,
      sendername: txt.sendername,
      receivername: txt.receivername,
      receiverId: txt.receiverId,
      msg: txt.msg,
    };
    if (txt.msg == "开启视频通话") {
      setVideo("200");
    }
    if (txt.msg == "结束视频通话") {
      setVideo("500");
    }
    if (txt.msg == "开始录音") {
      audiopage.initVideoServer(
        window.sessionStorage.getItem("localuserid"),
        JSON.parse(window.sessionStorage.getItem("remoteuser")).id
      );
      audiopage.getLocalStream();
      record.start();
    } else if (txt.msg == "结束录音") {
      audiopage.close();
      record.stop();
      location.reload();
    }
    save(txt.senderId, a, 1);
    addtip(txt.sendername, txt.senderId);
  };
}
//本地向服务器发送消息，由服务器转达给目标用户
function sendMsg(messsage) {
  console.log(messsage);
  var txt = {
    senderId: window.sessionStorage.getItem("localuserid"),
    sendername: window.sessionStorage.getItem("localusername"),
    receivername: JSON.parse(window.sessionStorage.getItem("remoteuser")).name,
    receiverId: JSON.parse(window.sessionStorage.getItem("remoteuser")).id,
    msg: messsage,
  };
  socket.send(JSON.stringify(txt));
  save(txt.receiverId, txt, 0);
}
//按聊天对象id存储聊天记录
function save(id, info, typ) {
  var txt = {
    type: typ,
    senderId: info.senderId,
    sendername: info.sendername,
    receivername: info.receivername,
    receiverId: info.receiverId,
    msg: info.msg,
  };
  if (window.localStorage.getItem(id) == null) {
    window.localStorage.setItem(id, JSON.stringify(txt));
  } else {
    var late_txt = window.localStorage.getItem(id);
    window.localStorage.setItem(id, late_txt + "|||" + JSON.stringify(txt));
  }
}

//添加消息通知
function addtip(name, id) {
  var a = window.sessionStorage.getItem("tips");
  if (a) {
    var b = a.split("|||");
    if (!b.includes(name + "\\" + id)) {
      window.sessionStorage.setItem("tips", a + "|||" + name + "\\" + id);
    }
  } else {
    window.sessionStorage.setItem("tips", name + "\\" + id);
  }
}
//返回通知记录
function createS() {
  var a = window.sessionStorage.getItem("tips");
  if (a) {
    var list = [];
    var b = a.split("|||");
    for (var i in b) {
      var c = b[i].split("\\");
      var temp = {
        name: c[0],
        id: c[1],
      };
      list.push(temp);
    }
    return list;
  } else {
    return [];
  }
}

//用户查看以后，删除对应通知
function delSender(name, id) {
  console.log(name + "\\" + id);
  var a = window.sessionStorage.getItem("tips").split("|||");
  a.splice(a.indexOf(name + "\\" + id), 1);
  window.sessionStorage.setItem("tips", a.join("|||"));
}
//返回聊天记录
function initInfo() {
  var info_list = [];
  var a = window.localStorage.getItem(
    JSON.parse(window.sessionStorage.getItem("remoteuser")).id
  );
  if (a == null) {
    return [];
  } else {
    var lists = a.split("|||");
    for (var b in lists) {
      info_list.push(JSON.parse(lists[b]));
    }
    return info_list;
  }
}
//清楚聊天记录
function removeInfo(id) {
  window.localStorage.removeItem(id);
}
</script>