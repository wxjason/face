<template>
  <div class="mncont">
    <!-- 标题 -->
    <div class="pgTitle backTitle">
      <p><i class="backbtn" @click="$router.push('/role')"></i>编辑角色</p>
    </div>
    <div class="roleDv">
      <el-form :model="editForm" class="addRole-form">
        <el-form-item label="角色名称：">
          <el-input v-model="editForm.roleName" placeholder=""></el-input>
        </el-form-item>
        <el-form-item label="备注：">
          <el-input type="textarea" resize="none" v-model="editForm.remark" placeholder=""></el-input>
        </el-form-item>
        <p class="roletop">选择角色权限：</p>
        <div class="roleouter">
          <el-tree
            ref="menuTree"
            node-key="id"
            :default-expanded-keys="hasMenuList"
            :data="menuList"
            :props="props"
            :show-checkbox="true">
          </el-tree>
        </div>
        <el-form-item class="btnsdv">
          <el-button type="primary" @click="editSure">确认</el-button>
          <el-button type="primary" plain @click="goBack">取消</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
  export default {
    name: "RoleEdit",
    data: function () {
      return {
        editForm: {
          roleName: null,
          remark: null
        },
        props: {
          id: 'id',
          label: 'menuName',
          children: 'children'
        },
        menuList: [],
        hasMenuList:[]
      };
    },
    methods: {
      searchMenuList() {
        let self = this;
        self.get('/api/system/menu/tree', function (data) {
          self.disabledMenu(data);
          self.menuList = data;
          self.searchRole();
        });
      },
      disabledMenu(dataList) {
        let self = this;
        dataList.forEach(function (d) {
          if (d.menuName && d.menuName.indexOf('菜单') > -1) {
            d.disabled = true;
          }
          self.disabledMenu(d.children);
        });
      },
      editSure() {
        let self = this;
        let url = '/api/system/role/update';
        self.post(url, self.editForm, function () {
          let bindData = {};
          bindData.roleId = self.$route.params.id;
          let menuIds = self.$refs.menuTree.getHalfCheckedKeys();
          menuIds.push(...self.$refs.menuTree.getCheckedKeys());
          bindData.menuIds = menuIds;
          self.post('/api/system/role/menu/bind', bindData, function () {
            self.$message.success('修改成功');
            self.$router.push('/role');
          })
        });
      },
      goBack() {
        this.$router.push('/role');
      },
      arraySort(a, b){
        return a - b;
      },
      searchRole(){
        let self = this;
        let url = '/api/system/role/detail/' + self.$route.params.id;
        self.get(url, function (data) {
          self.hasMenuList = data.menuIds;
          self.$refs.menuTree.setCheckedKeys(data.menuIds, true);
          let menuIds = self.common.arrayIntersection(data.menuIds.sort(self.arraySort), self.$refs.menuTree.getCheckedKeys().sort(self.arraySort));
          self.$refs.menuTree.setCheckedKeys(menuIds);
          data.menuIds = null;
          self.editForm = data;
        });
      }
    },
    created() {
      //实例创建完成后,页面渲染前执行
      this.searchMenuList();
    },

    mounted() {
      //页面渲染完成后执行  cameraStatus
    }
  };
</script>

<style src="../../assets/css/role.css" scoped></style>
<style scoped>
  .mncont{
    padding-bottom: 10px;
  }
</style>
