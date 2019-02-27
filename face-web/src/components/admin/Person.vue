<template>
  <div class="mncont">
    <!-- 标题 -->
    <div class="pgTitle">
      <p>人员管理</p>
      <div class="optdv">
        <el-button v-if="permission.add" type="text" @click="addUser"><i class="my-icon-add my-icon"></i>添加用户</el-button>
      </div>
    </div>
    <!-- form表单 -->
    <el-form :inline="true" :model="searchForm" class="demo-form-inline my-form" label-width="80px">
      <el-form-item label="用户名：">
        <el-input v-model="searchForm.username" placeholder="全部"></el-input>
      </el-form-item>
      <el-form-item label="姓名：">
        <el-input v-model="searchForm.name" placeholder="全部"></el-input>
      </el-form-item>
      <el-form-item label="所属角色：">
        <el-select v-model="searchForm.roleId" placeholder="全部">
          <el-option label="全部" :value="null"></el-option>
          <template v-for="role in roleList">
            <el-option :label="role.roleName" :value="role.roleId"></el-option>
          </template>
        </el-select>
      </el-form-item>
      <el-form-item label="状态：">
        <el-select v-model="searchForm.status" placeholder="全部">
          <el-option label="全部" :value="null"></el-option>
          <el-option label="启用" :value="1"></el-option>
          <el-option label="停用" :value="0"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="search(true)"><i class="my-icon-search my-icon"></i>搜索</el-button>
        <el-button type="primary" plain @click="resetSearch"><i class="my-icon-reset my-icon"></i>重置</el-button>
      </el-form-item>
    </el-form>
    <!-- 数据表 -->
    <!-- 分页 -->
    <div class="pagedv">
      <el-pagination
        @current-change="handleCurrentChange"
        :current-page.sync="page.currentPage"
        :page-size="page.pageSize"
        layout="total, prev, pager, next"
        :total="page.totalPage">
      </el-pagination>
    </div>
    <!-- 添加用户 -->
    <el-dialog title="添加用户" :visible.sync="addBoxShow" class="myBox" width="500px">
      <el-form :model="addForm">
        <el-form-item label="用户名：" :label-width="formLabelWidth" required>
          <el-input v-model="addForm.username"></el-input>
        </el-form-item>
        <el-form-item label="密码：" :label-width="formLabelWidth" required>
          <el-input v-model="addForm.password" :type="passwordType">
            <el-button slot="append" @mouseover.native="changePasswordType('text')"
                       @mouseout.native="changePasswordType('password')" icon="el-icon-view"></el-button>
          </el-input>
        </el-form-item>
        <el-form-item label="姓名：" :label-width="formLabelWidth" required>
          <el-input v-model="addForm.name"></el-input>
        </el-form-item>
        <el-form-item label="手机号：" :label-width="formLabelWidth" required>
          <el-input v-model="addForm.phone"></el-input>
        </el-form-item>
        <el-form-item label="邮箱：" :label-width="formLabelWidth">
          <el-input v-model="addForm.email"></el-input>
        </el-form-item>
        <el-form-item label="所属角色：" :label-width="formLabelWidth" required>
          <el-select v-model="addForm.roleId" placeholder="请选择所属角色">
            <template v-for="role in roleList">
              <el-option :label="role.roleName" :value="role.roleId"></el-option>
            </template>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="addSure">确 认</el-button>
        <el-button @click="addBoxShow = false">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 编辑用户 -->
    <el-dialog title="编辑用户" :visible.sync="editBoxShow" class="myBox" width="500px">
      <el-form :model="editForm">
        <el-form-item label="用户名：" :label-width="formLabelWidth" required>
          <el-input v-model="editForm.username" disabled></el-input>
        </el-form-item>
        <el-form-item label="姓名：" :label-width="formLabelWidth" required>
          <el-input v-model="editForm.name"></el-input>
        </el-form-item>
        <el-form-item label="手机号：" :label-width="formLabelWidth" required>
          <el-input v-model="editForm.phone"></el-input>
        </el-form-item>
        <el-form-item label="邮箱：" :label-width="formLabelWidth">
          <el-input v-model="editForm.email"></el-input>
        </el-form-item>
        <el-form-item label="所属角色：" :label-width="formLabelWidth" required>
          <el-select v-model="editForm.roleId" filterable placeholder="请选择所属角色">
            <template v-for="role in roleList">
              <el-option :label="role.roleName" :value="role.roleId"></el-option>
            </template>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="editSure">确 认</el-button>
        <el-button @click="editBoxShow = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>

  const PERMISSION_URI = {
    ADD: '/face/admin/person/add',
    UPDATE: '/face/admin/person/update',
    DEL: '/face/admin/person/delete',
  };
  export default {
    name: "User",
    data: function () {
      return {
        vtest: '',
        permission:{
          add:false,
          edit: false,
          del: false,
          enable: false,
          disable: false,
          passwordReset: false
        },
        passwordType: 'password',
        addBoxShow: false,
        editBoxShow: false,
        currentPage: 3,
        formLabelWidth: '100px',
        roleList: [],
        page: {
          currentPage: 1,
          pageSize: 10,
          totalPage: 0,
          offset: 0
        },
        searchForm: {
          username: null,
          name: null,
          phone: null,
          status: null,
          roleId: null
        },
        addForm: {
          username: null,
          password: '123456',
          name: null,
          phone: null,
          email: null,
          roleId: null
        },
        editForm: {
          userId: null,
          name: null,
          phone: null,
          email: null,
          roleId: null
        },
        tableData: []
      };
    },
    methods: {
      changePasswordType(type) {
        this.passwordType = type;
      },
      handleCurrentChange: function (currentPage) {
        this.page.offset = (currentPage - 1) * this.page.pageSize;
        this.search();
      },
      addUser(){
        this.addForm = {
          username: null,
          password: '123456',
          name: null,
          phone: null,
          email: null,
          roleId: null
        };
        this.addBoxShow = true
      },
      addSure() {//添加账号，确定
        let self = this;
        let url = '/api/face/admin/person/add';
        self.post(url, self.addForm, function () {
          self.$message.success('添加成功');
          self.search();
          self.addBoxShow = false;
        });
      },
      handleEdit(userId) {//编辑账号，打开
        let self = this;
        let url = '/api/face/admin/person/detail/' + userId;
        self.get(url, function (data) {
          self.editForm = data;
          self.search();
          self.editBoxShow = true;
        });
      },
      editSure() {//编辑账号，确定
        let self = this;
        let url = '/api/face/admin/person/update';
        self.post(url, self.editForm, function () {
          self.$message.success('修改成功');
          self.search();
          self.editBoxShow = false;
        });
      },
      enableUser(userId) {
        let self = this;
        let url = '/api/face/admin/person/enable/' + userId;
        self.get(url, function () {
          self.search();
          self.$message.success('启用成功');
        });
      },
      disableUser(user) {
        if (localStorage.getItem('userId') === user.userId) {
          this.$message.warning('不可停用当前登录用户');
          return;
        }
        let self = this;
        let url = '/api/face/admin/person/disable/' + user.userId;
        self.get(url, function () {
          self.search();
          self.$message.success('停用成功');
        });
      },
      handleReset(userId) {  //重置密码
        this.$confirm('', '是否重置密码？', {
          confirmButtonText: '确 认',
          cancelButtonText: '取 消',
          type: 'warning',
          center: true,
          showClose: false,
          customClass: 'delBox resetBox'
        }).then(() => {
          let self = this;
          let url = '/api/face/admin/person/password/reset/' + userId;
          self.get(url, function (data) {
            const h = self.$createElement;
            self.$msgbox({
              center: true,
              customClass: 'delBox resetedBox',
              message: h('p', null, [
                h('span', null, '密码重置成功，新密码为： '),
                h('i', {style: 'color: red'}, data)
              ]),
              confirmButtonText: '确定',
            }).then(action => {
              let self = this;
              self.$message.success('重置成功');
            });
          });
        }).catch(() => {
        });
      },
      handleDelete(user) {
        if (localStorage.getItem('userId') === user.userId) {
          this.$message.warning('不可删除当前登录用户');
          return;
        }
        if (user.status === 1) {
          this.$message.warning('该用户启用中,请停用后再删除');
          return;
        }
        this.$confirm('', '确认删除此用户？', {
          confirmButtonText: '确 认',
          cancelButtonText: '取 消',
          type: 'warning',
          center: true,
          showClose: false,
          customClass: 'delBox'
        }).then(() => {
          let self = this;
          let url = '/api/face/admin/person/delete/' + user.userId;
          self.get(url, function () {
            self.search();
            self.$message.success('删除成功');
          });
        }).catch(() => {
        });
      },
      searchRoleList() {
        let self = this;
        let url = '/api/system/role/list';
        self.post(url, {}, function (data) {
          self.roleList = data;
        });
      },
      searchUserList(resetPage) {
        let self = this;
        if (resetPage) {
          self.page.offset = 0;
        }
        let url = '/api/face/admin/person/list/page?offset=' + self.page.offset + '&limit=' + self.page.pageSize;
        self.post(url, self.searchForm, function (data) {
          self.tableData = data.rows;
          self.page.totalPage = data.total;  //信息总条数
        });
      },
      searchMenuPermissions(){
        let self = this;
        let url = '/api/system/menu/children/button?url=/face/admin/person';
        self.get(url, function (data) {
          data.forEach(function(d){
            switch (d.menuUrl) {
              case PERMISSION_URI.ADD:
                self.permission.add = true;
                break;
              case PERMISSION_URI.UPDATE:
                self.permission.edit = true;
                break;
              case PERMISSION_URI.DEL:
                self.permission.del = true;
                break;
              default:
            }
          });
        });
      },
      search(resetPage) {
        this.searchRoleList();
        this.searchUserList(resetPage);
      },
      resetSearch() {
        this.page = {
          currentPage: 1,
          pageSize: 10,
          totalPage: 0,
          offset: 0
        };
        this.searchForm = {
          username: null,
          name: null,
          phone: null,
          status: null,
          roleId: null
        };
        this.search();
      }
    },
    created() {
      //实例创建完成后,页面渲染前执行
      this.searchMenuPermissions();
      this.search();
    },

    mounted() {
      //页面渲染完成后执行  cameraStatus
    }
  };
</script>






