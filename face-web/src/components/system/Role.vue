<template>
  <div class="mncont">
    <!-- 标题 -->
    <div class="pgTitle">
      <p>角色管理</p>
      <div class="optdv">
        <el-button v-if="permission.add" type="text" @click="addRole"><i class="my-icon-add my-icon"></i>添加角色</el-button>
      </div>
    </div>
    <!-- form表单 -->
    <el-form :inline="true" :model="searchForm" class="demo-form-inline my-form" label-width="80px">
      <el-form-item label="角色名称：">
        <el-input v-model="searchForm.roleName" placeholder=""></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="search(true)"><i class="my-icon-search my-icon"></i>搜索</el-button>
        <el-button type="primary" plain @click="resetSearch"><i class="my-icon-reset my-icon"></i>重置</el-button>
      </el-form-item>
    </el-form>
    <!-- 数据表 -->
    <el-table :data="tableData" class="myTable">
      <el-table-column
        type="index"
        label="序号"
        align="center"
        width="100"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        prop="roleName"
        label="角色名称"
        align="center"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        prop="remark"
        label="备注"
        align="center"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        prop="createTime"
        label="创建时间"
        align="center"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column v-if="permission.edit || permission.del" label="管理" width="280" align="center"
                       show-overflow-tooltip>
        <template slot-scope="scope">
          <el-button
            v-if="permission.edit"
            size="mini"
            type="text"
            @click="handleEdit(scope.row.roleId)">
            <i class="my-icon-edit my-icon"></i>编辑
          </el-button>
          <el-button
            v-if="permission.del"
            size="mini"
            type="text"
            @click="handleDelete(scope.row.roleId)">
            <i class="my-icon-del my-icon"></i>删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
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
  </div>
</template>

<script>

  const PERMISSION_URI = {
    ADD: '/system/role/add',
    UPDATE: '/system/role/update',
    DEL: '/system/role/delete',
  };
  export default {
    name: "Role",
    data: function () {
      return {
        vtest: '',
        permission:{
          add:false,
          edit: false,
          del: false,
        },
        formLabelWidth: '100px',
        page: {
          currentPage: 1,
          pageSize: 10,
          totalPage: 0,
          offset: 0
        },
        searchForm: {
          roleName: null
        },
        tableData: []
      };
    },
    methods: {
      handleCurrentChange: function (currentPage) {
        this.page.offset = (currentPage - 1) * this.page.pageSize;
        this.search();
      },
      addRole() {//新建角色
        let self = this;
        self.$router.push('/role/add');
      },
      handleEdit(roleId) {//编辑角色，打开
        let self = this;
        self.$router.push('/role/edit/' + roleId);
      },
      handleDelete(roleId) {
        this.$confirm('', '确认删除此角色？', {
          confirmButtonText: '确 认',
          cancelButtonText: '取 消',
          type: 'warning',
          center: true,
          showClose: false,
          customClass: 'delBox'
        }).then(() => {
          let self = this;
          self.get('/api/system/role/delete/' + roleId, function () {
            self.search();
            self.$message.success('删除成功');
          });
        }).catch(() => {
        });
      },
      searchRoleList(resetPage) {
        let self = this;
        if (resetPage) {
          self.page.offset = 0;
        }
        let url = '/api/system/role/list/page?offset=' + self.page.offset + '&limit=' + self.page.pageSize;
        self.post(url, self.searchForm, function (data) {
          self.tableData = data.rows;
          self.page.totalPage = data.total;  //信息总条数
        });
      },
      searchMenuPermissions(){
        let self = this;
        let url = '/api/system/menu/children/button?url=/system/role';
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
        this.searchRoleList(resetPage);
      },
      resetSearch() {
        this.page = {
          currentPage: 1,
          pageSize: 10,
          totalPage: 0,
          offset: 0
        };
        this.searchForm = {
          roleName: null
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






