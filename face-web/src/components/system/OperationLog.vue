<template>
  <div class="mncont">
    <!-- 标题 -->
    <div class="pgTitle">
      <p>操作日志</p>
      <div class="optdv">
        <el-button v-if="permission.exportExcel" type="text" @click="batchExport"><i class="my-icon-export my-icon"></i>批量导出</el-button>
      </div>
    </div>
    <!-- form表单 -->
    <el-form :inline="true" :model="searchForm" class="demo-form-inline my-form" label-width="80px">
      <el-form-item label="操作用户：">
        <el-input v-model="searchForm.username" placeholder="全部"></el-input>
      </el-form-item>
      <el-form-item label="操作功能：">
        <el-select v-model="searchForm.menuId" filterable placeholder="全部">
          <el-option label="全部" :value="null"></el-option>
          <template v-for="menu in menuList">
            <el-option :label="menu.menuName" :value="menu.id"></el-option>
          </template>
        </el-select>
      </el-form-item>
      <el-form-item label="操作时间：">
        <el-date-picker
          v-model="timeVal"
          type="datetimerange"
          align="right"
          popper-class="my-date-picker"
          format="yyyy-MM-dd HH:mm:ss"
          start-placeholder="开始时间"
          end-placeholder="结束时间"
          :default-time="['00:00:00', '23:59:59']">
        </el-date-picker>
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
        prop="name"
        label="操作人姓名"
        align="center"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        prop="username"
        label="操作用户"
        align="center"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        prop="remoteIp"
        label="操作人IP"
        align="center"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        prop="phone"
        label="手机号"
        align="center"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        prop="menu"
        label="操作功能"
        align="center"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        prop="button"
        label="操作内容"
        align="center"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column label="操作结果" align="center">
        <template slot-scope="scope">
          <span :class="scope.row.result == 1 ? 'greenDot' : 'orgDot'">{{scope.row.result == 1 ? '成功' : '失败'}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="createTime"
        label="操作时间"
        width="160"
        align="center"
        show-overflow-tooltip>
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
    EXPORT: '/system/operation/log/export'
  };
  export default {
    name: "OperationLog",
    data: function () {
      return {
        vtest: '',
        timeVal: [],
        formLabelWidth: '100px',
        permission:{
          exportExcel: false
        },
        menuList: [],
        page: {
          currentPage: 1,
          pageSize: 10,
          totalPage: 0,
          offset: 0
        },
        searchForm: {
          name: null,
          username: null,
          result: null,
          menuId: null,

        },
        tableData: []
      };
    },
    methods: {
      handleCurrentChange: function (currentPage) {
        this.page.offset = (currentPage - 1) * this.page.pageSize;
        this.search();
      },
      batchExport() {//批量导出
        let self = this;
        self.searchForm.startTime = '';
        self.searchForm.endTime = '';
        if (self.timeVal && self.timeVal[0]) {
          self.searchForm.startTime = self.common.dateToString(self.timeVal[0]);
        }
        if (self.timeVal && self.timeVal[1]) {
          self.searchForm.endTime = self.common.dateToString(self.timeVal[1]);
        }
        let url = '/api/system/operation/log/export';
        const loading = self.$loading(self.loadingOptions);
        self.post(url, self.searchForm, function (data) {
          setTimeout(function(){
            loading.close();
          }, 1000);
          window.location.href = '/api/system/operation/log/download/' + data;
        });
      },
      searchOperationLogList(resetPage) {
        let self = this;
        if (resetPage) {
          self.page.offset = 0;
        }
        self.searchForm.startTime = '';
        self.searchForm.endTime = '';
        if (self.timeVal && self.timeVal[0]) {
          self.searchForm.startTime = self.common.dateToString(self.timeVal[0]);
        }
        if (self.timeVal && self.timeVal[1]) {
          self.searchForm.endTime = self.common.dateToString(self.timeVal[1]);
        }
        let url = '/api/system/operation/log/list/page?offset=' + self.page.offset + '&limit=' + self.page.pageSize;
        self.post(url, self.searchForm, function (data) {
          self.tableData = data.rows;
          self.page.totalPage = data.total;
        });
      },
      searchMenuList() {
        let self = this;
        let url = '/api/system/menu/list/func';
        self.get(url, function (data) {
          self.menuList = data;
        });
      },
      searchMenuPermissions(){
        let self = this;
        let url = '/api/system/menu/children/button?url=/system/operation/log';
        self.get(url, function (data) {
          data.forEach(function(d){
            switch (d.menuUrl) {
              case PERMISSION_URI.EXPORT:
                self.permission.exportExcel = true;
                break;
              default:
            }
          });
        });
      },
      search(resetPage) {
        this.searchMenuList();
        this.searchOperationLogList(resetPage);
      },
      resetSearch() {
        this.page = {
          currentPage: 1,
          pageSize: 10,
          totalPage: 0,
          offset: 0
        };
        this.searchForm = {
          name: null,
          username: null,
          result: null,
          menuId: null,
        };
        this.timeVal = [];
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






