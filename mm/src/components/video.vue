<template>
  <div class="video_contain">
    <video
      class="large_video"
      src=""
      id="iv2"
      autoplay="autoplay"
      style="background-color: black"
    ></video>
    <video
      class="small_video"
      src=""
      id="iv"
      autoplay="autoplay"
      style="background-color: black"
    ></video>
  </div>
</template>
<script>
export default {
  name: "videopage",
  data() {
    return {};
  },
  props: {
    userid: "",
    reciverid: "",
    conn: "",
  },
  created() {
    if (this.conn) {
      console.log("start");
      initVideoServer(this.userid, this.reciverid);
      getLocalStream();
      setTimeout(() => {
        connect(this.reciverid);
      }, 2000);
    } else {
      initVideoServer(this.userid, this.reciverid);
      getLocalStream();
    }
  },
  methods: {},
};
var socket = null;
var socketurl = "ws://127.0.0.1:8080/videoserver/";
var pc = null;
var localStream = null;
function initVideoServer(userid, reciverid) {
  console.log(userid);
  socket = new WebSocket(socketurl + userid);
  socket.onopen = function () {
    console.log("video server connect success");
  };
  socket.onclose = function (e) {
    console.log("video server connect fail," + e.code);
  };
  socket.onmessage = function (res) {
    var data = JSON.parse(res.data);
    if (data.type == "offer") {
      if (pc) {
        console.error("pc is exsit");
        return;
      }
      pc = initPeerConnection(reciverid);
      console.log(data);
      console.log("get offer");
      var rtcs = new RTCSessionDescription(data);
      pc.setRemoteDescription(rtcs);
      pc.createAnswer(
        function (desc) {
          pc.setLocalDescription(desc);
          console.log("send answer");
          var desc = {
            reciverid: reciverid,
            desc: desc,
          };
          socket.send(JSON.stringify(desc));
        },
        function (err) {
          console.error("send answer fail");
          console.log(err);
        }
      );
    } else if (data.type == "answer") {
      if (!pc) {
        console.log("pc is not exsit");
        return;
      }
      console.log(data);
      console.log("get answer");
      var rtcs = new RTCSessionDescription(data);
      pc.setRemoteDescription(rtcs);
    } else if (data.type == "candidate") {
      console.log("get candidate");
      console.log(data);
      var candidate = new RTCIceCandidate({
        sdpMLineIndex: data.sdpMLineIndex,
        sdpMid: data.sdpMid,
        candidate: data.candidate,
      });
      pc.addIceCandidate(candidate);
      console.log("add candidate success");
    }
  };
}
function getLocalStream() {
  navigator.webkitGetUserMedia(
    { video: true, audio: true },
    function (stream) {
      localStream = stream;
      document.getElementById("iv").srcObject = stream;
      document.getElementById("iv").play();
      console.log("get stream");
    },
    function (e) {
      console.log("get localvideo faill");
      console.log(e.code);
    }
  );
}
//建立连接
function connect(reciverid) {
  console.log("connect with " + reciverid);
  pc = initPeerConnection(reciverid);
  pc.createOffer(
    function (desc) {
      console.log(desc);
      console.log("sender offer");
      pc.setLocalDescription(desc);
      var desc = {
        reciverid: reciverid,
        desc: desc,
      };
      socket.send(JSON.stringify(desc));
    },
    function (err) {
      console.log("send offer fail");
      console.log(err);
    }
  );
}

//初始化候选者，并发送信令
function initPeerConnection(id) {
  var peer = null;
  try {
    peer = new webkitRTCPeerConnection();
  } catch (e) {
    console.log("get peer fail");
    console.log(e.message);
  }
  peer.onicecandidate = function (evt) {
    console.log(evt.candidate);
    if (evt.candidate) {
      var messsage = {
        type: "candidate",
        sdpMid: evt.candidate.sdpMid,
        sdpMLineIndex: evt.candidate.sdpMLineIndex,
        candidate: evt.candidate.candidate,
      };
      var txt = {
        reciverid: id,
        desc: messsage,
      };
      console.log("send candidate");
      socket.send(JSON.stringify(txt));
    }
  };
  peer.addStream(localStream);
  peer.onaddstream = function (event) {
    console.log(event.stream);
    document.getElementById("iv2").srcObject = event.stream;
    document.getElementById("iv2").play();
    console.log("connect remote stream");
  };
  return peer;
}
</script>
<style scoped>
.video_contain {
  background-color: black;
  position: relative;
  width: 520px;
  height: 520px;
}
.large_video {
  width: 500px;
  height: 500px;
}
.small_video {
  position: absolute;
  top: 400px;
  width: 100px;
  height: 100px;
  left: 400px;
}
</style>