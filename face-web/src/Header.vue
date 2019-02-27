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

  </div>
</template>

<script>
  export default {
    name: 'Header',
    data: function () {
      return {
        currentUser: {}
      }
    },
    methods: {
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
    },
    mounted() {
    }
  }
</script>
