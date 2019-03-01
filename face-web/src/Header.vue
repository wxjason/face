<template>
  <div class="header">
    <el-row>
      <el-col :span="18">
        <img src="./assets/img/index-logo.png" class="logo"/>
        <span class="sysName">后台管理系统</span>
      </el-col>
      <el-col :span="6">
        <div class="hd1">
          <el-button type="text" @click="$router.push('/personal/center')"><i
            class="my-icon-user my-icon"></i>{{currentUser.name}}（{{currentUser.roleName}}）
          </el-button>
          <el-button type="text" @click="logout"><i class="my-icon-exit my-icon"></i>退出</el-button>
        </div>
      </el-col>
    </el-row>
    <!-- 未读警报小弹框 -->
    <div class="msgBoxlt" v-if="msgShow">
      <i class="msgclose" @click="closeMsg"></i>
      <div class="imgdv">
        <img :src="snap.snapImage"/>
      </div>
      <div class="infodv">
        <p class="sitep" v-bind:title="snap.similarPoint + snap.personName">{{snap.similarPoint + snap.personName}}</p>
        <p class="timep" v-bind:title="snap.createTime">{{snap.createTime}}</p>
        <p class="sitep" v-bind:title="snap.deviceName">{{snap.deviceName}}</p>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'Header',
    data: function () {
      return {
        currentUser: {},
        websocket: null,
        msgShow: false,
        snap:{}
      }
    },
    methods: {
      closeMsg(){
        this.msgShow = false;
      },
      initWebSocket: function () { //初始化weosocket
        const wsuri = this.conf.wsUrl + localStorage.username;//ws地址
        this.websocket = new WebSocket(wsuri);
        this.websocket.onopen = this.websocketOnOpen;
        this.websocket.onerror = this.websocketOnError;
        this.websocket.onmessage = this.websocketOnMessage;
        this.websocket.onclose = this.websocketOnClose;
      },

      websocketOnOpen: function () {
      },
      websocketOnError: function (e) { //错误
        console.log("WebSocket连接发生错误");
      },
      websocketOnMessage: function (m) { //数据接收
        let data;
        try {
          data = JSON.parse(m.data);
        } catch (e) {
        }
        if (data) {
          this.snap = data;
          this.msgShow = true;
        }
      },
      websocketSend: function (agentData) {//数据发送
        this.websocket.send(agentData);
      },
      websocketOnClose: function () { //关闭
        console.log("WebSocket连接关闭");
      },
      logout() {
        localStorage.removeItem('token');
        this.cookieUtil.clearCookie();
        window.location.href = '/login';
      },
      searchCurrentUser() {
        let self = this;
        self.get('/api/system/user/current', function (data) {
          self.currentUser = data;
          localStorage.setItem('userId', data.userId);
        }, function () {
          window.location.href = '/login?msg=该用户不存在';
        });
      },
      searchMenuPermissions(){
        let self = this;
        let url = '/api/system/menu/list/func';
        if (self.$route.path === '/') {
          self.get(url, function (data) {
            data.forEach(function (d) {
              switch (d.menuUrl) {
                case '/admin/home':
                  self.$router.push('/home');
                  return false;
                default:
              }
            });
          });
        }
      }
    },
    created() {
      this.searchCurrentUser();
      this.searchMenuPermissions();
      //页面刚进入时开启长连接
      this.initWebSocket();
    },
    mounted() {
    }
  }
</script>

<style>
  /* 右侧未读警报列表 */

  .jingbDv h2 {
    height: 50px;
    line-height: 50px;
    font-size: 16px;
    text-align: left;
    padding: 0 20px;
    background: #1D1D38;
    margin-top: -50px;
  }

  .jingbDv .titdv {
    float: right;
  }

  .jingbDv .jingbScrl {
    height: 100%;
    overflow: hidden;
    overflow-y: auto;
    padding: 0 10px 60px 10px;
  }

  .jingbList li {
    border-bottom: 1px solid #304161;
    padding: 20px 10px;
    overflow: hidden;
  }

  .imgdv, .infodv {
    float: left;
  }

  .imgdv {
    width: 75px;
    height: 60px;
    overflow: hidden;
    margin: 0 15px 0 0;
    border: 1px solid #ccc;
  }

  .jingbList li:first-child .imgdv {
    border: 1px solid #1492FF;
  }

  .imgdv img {
    width: 100%;
    height: 100%;
  }

  .infodv p {
    width: 150px;
    line-height: 20px;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
  }

  .sitep {
    color: #111;
  }

  .btndv {
    float: right;
    padding: 18px 0 0 0;
    margin: 0 0 0 10px;
  }

  .btndv .obtn {
    width: 70px;
    height: 26px;
    line-height: 24px;
    border-radius: 2px;
    font-size: 12px;
  }

  /* 小弹框样式 */
  .msgBoxlt {
    position: fixed;
    top: 80px;
    right: 15px;
    z-index: 10;
    width: 300px;
    height: 100px;
    color: #111;
    background: #fff;
    border: 1px solid #ccc;
    padding: 20px;
  }

  .msgclose {
    position: absolute;
    top: -6px;
    right: -6px;
    width: 21px;
    height: 21px;
    display: inline-block;
    background: url('./assets/img/msgltclose.png') no-repeat center;
    cursor: pointer;
  }

  .msgBoxlt .imgdv {
    border: 1px solid #1492FF;
  }
</style>
