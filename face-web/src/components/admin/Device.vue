<template>
  <div class="mncont">
    <!-- 标题 -->
    <div class="pgTitle">
      <p>设备管理</p>
      <div class="optdv">
        <el-button v-if="permission.add" type="text" @click="addUser"><i class="my-icon-add my-icon"></i>添加设备</el-button>
      </div>
    </div>
    <!-- form表单 -->
    <el-form :inline="true" :model="searchForm" class="demo-form-inline my-form" label-width="80px">
      <el-form-item label="设备名称：">
        <el-input v-model="searchForm.deviceName" placeholder="全部"></el-input>
      </el-form-item>
      <el-form-item label="设备状态：">
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
    <el-table :data="tableData" class="myTable">
      <el-table-column
        type="index"
        label="序号"
        align="center"
        width="100"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        prop="deviceName"
        label="设备名称"
        align="center"
        min-width="100"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        prop="streamUrl"
        label="设备播放地址"
        align="center"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        prop="status"
        label="状态"
        align="center"
        show-overflow-tooltip>
        <template slot-scope="scope">
          <span :class="scope.row.status == 1 ? 'greenDot' : 'orgDot'">{{scope.row.status == 1 ? '启用' : '停用'}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="createTime"
        label="创建时间"
        align="center"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column v-if="permission.del || permission.enable || permission.disable" label="管理" width="200" align="center"
                       show-overflow-tooltip>
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            class="greenFont"
            v-if="scope.row.status == 0 && permission.enable"
            @click="enableDevice(scope.row.id)">
            <i class="my-icon-start my-icon"></i>启用
          </el-button>
          <el-button
            size="mini"
            type="text"
            class="orgFont"
            v-if="scope.row.status == 1 && permission.disable"
            @click="disableDevice(scope.row.id)">
            <i class="my-icon-stop my-icon"></i>停用
          </el-button>
          <el-button
            v-if="permission.del"
            size="mini"
            type="text"
            @click="handleDelete(scope.row)">
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
    <!-- 添加设备 -->
    <el-dialog title="添加设备" :visible.sync="addBoxShow" class="myBox" width="500px">
      <el-form :model="addForm">
        <el-form-item label="设备名称：" :label-width="formLabelWidth" required>
          <el-input v-model="addForm.deviceName"></el-input>
        </el-form-item>
        <el-form-item label="视频地址：" :label-width="formLabelWidth" required>
          <el-input type="textarea" v-model="addForm.streamUrl"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="addSure">确 认</el-button>
        <el-button @click="addBoxShow = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>

  const PERMISSION_URI = {
    ADD: '/face/admin/device/add',
    DEL: '/face/admin/device/delete',
    ENABLE: '/face/admin/device/enable',
    DISABLE: '/face/admin/device/disable',
  };
  export default {
    name: "Device",
    data: function () {
      return {
        vtest: '',
        permission:{
          add:false,
          edit: false,
          del: false,
          enable: false,
          disable: false,
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
          deviceName: null,
          status: null
        },
        addForm: {
          deviceName: null,
          streamUrl: null
        },
        editForm: {
          deviceName: null,
          streamUrl: null
        },
        tableData: []
      };
    },
    methods: {
      handleCurrentChange: function (currentPage) {
        this.page.offset = (currentPage - 1) * this.page.pageSize;
        this.search();
      },
      addUser(){
        this.addForm = {
          deviceName: null,
          streamUrl: null
        };
        this.addBoxShow = true
      },
      addSure() {//添加账号，确定
        let self = this;
        let url = '/api/face/admin/device/add';
        self.post(url, self.addForm, function () {
          self.$message.success('添加成功');
          self.search();
          self.addBoxShow = false;
        });
      },
      enableDevice(deviceId) {
        let self = this;
        let url = '/api/face/admin/device/enable/' + deviceId;
        self.get(url, function () {
          self.search();
          self.$message.success('启用成功');
        });
      },
      disableDevice(deviceId) {
        let self = this;
        let url = '/api/face/admin/device/disable/' + deviceId;
        self.get(url, function () {
          self.search();
          self.$message.success('停用成功');
        });
      },
      handleDelete(device) {
        if (device.status === 1) {
          this.$message.warning('该设备启用中,请停用后再删除');
          return;
        }
        this.$confirm('', '确认删除此设备？', {
          confirmButtonText: '确 认',
          cancelButtonText: '取 消',
          type: 'warning',
          center: true,
          showClose: false,
          customClass: 'delBox'
        }).then(() => {
          let self = this;
          let url = '/api/face/admin/device/delete/' + device.id;
          self.get(url, function () {
            self.search();
            self.$message.success('删除成功');
          });
        }).catch(() => {
        });
      },
      searchDeviceList(resetPage) {
        let self = this;
        if (resetPage) {
          self.page.offset = 0;
        }
        let url = '/api/face/admin/device/list/page?offset=' + self.page.offset + '&limit=' + self.page.pageSize;
        self.post(url, self.searchForm, function (data) {
          self.tableData = data.rows;
          self.page.totalPage = data.total;  //信息总条数
        });
      },
      searchMenuPermissions(){
        let self = this;
        let url = '/api/system/menu/children/button?url=/face/admin/device';
        self.get(url, function (data) {
          data.forEach(function(d){
            switch (d.menuUrl) {
              case PERMISSION_URI.ADD:
                self.permission.add = true;
                break;
              case PERMISSION_URI.DEL:
                self.permission.del = true;
                break;
              case PERMISSION_URI.ENABLE:
                self.permission.enable = true;
                break;
              case PERMISSION_URI.DISABLE:
                self.permission.disable = true;
                break;
              default:
            }
          });
        });
      },
      search(resetPage) {
        this.searchDeviceList(resetPage);
      },
      resetSearch() {
        this.page = {
          currentPage: 1,
          pageSize: 10,
          totalPage: 0,
          offset: 0
        };
        this.searchForm = {
          deviceName: null,
          status: null
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






