<template>
  <div class="mncont">
    <!-- 标题 -->
    <div class="pgTitle">
      <p>个人中心</p>
    </div>
    <div class="personOuter">
      <el-form :model="infoForm" class="info-form">
        <el-form-item label="账号：" :label-width="labelW">
          <el-input v-model="infoForm.username" disabled></el-input>
        </el-form-item>
        <el-form-item label="姓名：" :label-width="labelW" required>
          <el-input v-model="infoForm.name"></el-input>
        </el-form-item>
        <el-form-item label="手机：" :label-width="labelW" required>
          <el-input v-model="infoForm.phone"></el-input>
        </el-form-item>
        <el-form-item label="邮箱：" :label-width="labelW">
          <el-input v-model="infoForm.email"></el-input>
        </el-form-item>
        <el-form-item label="所属角色：" :label-width="labelW">
          <el-input v-model="infoForm.roleName" disabled></el-input>
        </el-form-item>
        <el-form-item class="btnsdv">
          <el-button type="primary" @click="editSure">确认</el-button>
          <el-button type="primary" @click="changePasswordShow">修改密码</el-button>
        </el-form-item>
      </el-form>
    </div>
    <!-- 修改密码 -->
    <el-dialog title="修改密码" :visible.sync="editBoxShow" class="myBox" width="500px">
      <el-form :model="editForm">
        <el-form-item label="请输入原密码：" :label-width="formLabelWidth" required>
          <el-input type="password" v-model="editForm.oldPassword"></el-input>
        </el-form-item>
        <el-form-item label="请输入新密码：" :label-width="formLabelWidth" required>
          <el-input type="password" v-model="editForm.newPassword"></el-input>
        </el-form-item>
        <el-form-item label="请确认新密码：" :label-width="formLabelWidth" required>
          <el-input type="password" v-model="editForm.rePassword"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="changePasswordSure">确 认</el-button>
        <el-button @click="editBoxShow = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>

</template>

<script>
  export default {
    name: "PersonalCenter",
    data: function () {
      return {
        labelW: '80px',
        formLabelWidth: '150px',
        editBoxShow: false,
        infoForm: {},
        editForm: {}
      };
    },
    methods: {
      changePasswordShow(){
        let self = this;
        self.editForm = {};
        self.editBoxShow = true;
      },
      editSure() { //修改资料
        let self = this;
        self.post('/api/system/user/update', self.infoForm, function(){
          self.$message.success('修改成功');
        });
      },
      changePasswordSure() { //修改密码，确认
        let self = this;
        self.post('/api/system/user/password/change', self.editForm, function(){
          self.$message.success('修改密码成功');
          self.editBoxShow = false;
          setTimeout(function(){
            localStorage.removeItem('token');
            self.cookieUtil.clearCookie();
            window.location.href = '/login?msg=登录已过期,请重新登录';
          }, 1000);
        });
      },
      searchUser(){
        let self = this;
        self.get('/api/system/user/current',function(data){
          self.infoForm = data;
        });
      }
    },
    created() {
      //实例创建完成后,页面渲染前执行
      this.searchUser();
    },

    mounted() {
      //页面渲染完成后执行  cameraStatus

    }
  };
</script>

<style scoped>
  .mncont {
    padding-bottom: 10px;
  }
  .personOuter {
    padding: 40px 30px;
  }
  .personOuter /deep/ .el-input__inner{
    width: 350px;
  }
  .btnsdv {
    padding: 40px 0 0 81px;
  }
  .btnsdv .el-button {
    width: 120px;
    margin: 0 40px 0 0;
  }
</style>






