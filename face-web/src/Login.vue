<template>
  <div class="login-wrapper">
    <div class="loginOutoer">
      <div class="lginfo">
        <form class="m-t" role="form" method="post">
          <p class="sysName" style="margin:0;" title="电信物联网实名制认证系统">后台管理系统</p>
          <h4 style="color: red;height: 40px;line-height: 40px;text-align: center;">{{msg}}</h4>
          <div class="lgiptdv clear userdv">
            <i class="lgico userico"></i>
            <input @focus="removeCookie" v-on:input="validUser" class="form-control lgipt" name="username" v-model="user.username" type="text"
                   placeholder="请输入用户名"/>
          </div>
          <div class="lgiptdv clear">
            <i class="lgico pwdico"></i>
            <input class="form-control lgipt" v-on:input="validUser" name="password" type="password" v-model="user.password"
                   placeholder="请输入密码"/>
          </div>
          <label class="" for="rempwd">
            <input @click="rememberMe" type="checkbox" name="remember" :checked="remember" id="rempwd"/>
            记住密码
          </label>
          <br>
        </form>
        <button @click="login" class="btn lgbtn" style="margin-top: 30px;">登 录</button>
      </div>
    </div>
  </div>
</template>
<style>

</style>
<script>
  export default {
    name: 'Login',
    data: function () {
      return {
        msg: this.$route.query.msg,
        user: {
          username: !!localStorage.getItem('username') ? localStorage.getItem('username') : '',
          password: !!this.cookieUtil.getCookie() ? this.cookieUtil.getCookie().password : ''
        },
        remember: !!this.cookieUtil.getCookie()
      }
    },
    methods: {
      removeCookie: function () {
        this.remember = false;
        this.user.password = '';
        this.cookieUtil.clearCookie();
      },
      rememberMe: function () {
        this.remember = !this.remember;
      },
      validUser(){
        let self = this;
        if (!self.user.username) {
          self.msg = '用户名不能为空';
          return false;
        }
        if (!self.user.password) {
          self.msg = '密码不能为空';
          return false;
        }
        self.msg = '';
        return true;
      },
      login: function () {
        let self = this;
        if (!self.validUser()) {
          return;
        }
        const loading = self.$loading(self.loadingOptions);
        $.ajax({
          url: '/api/system/login',
          type: 'POST',
          cache: false,
          withCredentials: true,
          contentType: 'application/json',
          data: JSON.stringify(self.user),
          dataType: 'JSON',
          success: function () {
            self.$message.success('登录成功');
            localStorage.setItem('username', self.user.username);
            if (self.remember) {
              self.cookieUtil.setCookie(self.user.username, self.user.password, 7);
            } else {
              self.removeCookie();
            }
          },
          error: function (r) {
            self.msg = r.responseJSON.message;
            self.removeCookie();
          },
          complete: function (xhr, data) {
            loading.close();
            // 获取相关Http Response header
            if (data === 'success') {
              localStorage.setItem('exTime', parseInt(Date.now() / 1000) + (20 * 60));
              localStorage.setItem('token', xhr.getResponseHeader('token'));
              self.$router.push('/');
            } else {
              localStorage.removeItem('token');
            }
          }
        });
      }
    },
    created: function () {
      let self = this;
      document.onkeydown = function (e) {
        if (e.keyCode === 13) {
          self.login();
        }
      };
    },
    beforeDestroy() {
      document.onkeydown = function (e) {
      }
    }
  }
</script>

<style src="./assets/css/login.css" scoped></style>
