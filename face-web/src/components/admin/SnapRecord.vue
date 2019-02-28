<template>
  <div class="mncont">
    <!-- 标题 -->
    <div class="pgTitle">
      <p>抓拍历史</p>
    </div>
    <!-- form表单 -->
    <el-form :inline="true" :model="searchForm" class="demo-form-inline my-form" label-width="80px">
      <el-form-item label="姓名搜索：">
        <el-input v-model="searchForm.personName" placeholder="全部"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="search(true)"><i class="my-icon-search my-icon"></i>搜索</el-button>
        <el-button type="primary" plain @click="resetSearch"><i class="my-icon-reset my-icon"></i>重置</el-button>
      </el-form-item>
    </el-form>
    <!-- 数据表 -->
    <el-row :gutter="20">
      <!--<el-col :span="6" v-for="r in recordList" style="margin-bottom:10px;position: relative;height: 50%!important;">-->
        <!--<el-card :body-style="{ padding: '0px' }">-->
          <!--<img :src="r.snapImage" class="image">-->
          <!--<div style="padding: 14px;text-align: center;">-->
            <!--<span>{{r.personName}}</span>-->
            <!--<div class="bottom clearfix">-->
              <!--<time class="time">{{r.createTime}}</time>-->
            <!--</div>-->
          <!--</div>-->
        <!--</el-card>-->
      <!--</el-col>-->
      <template v-for="r in recordList">
        <el-col :span="6" style="margin-bottom:10px;position: relative;height: 50%!important;">
          <el-card :body-style="{ padding: '0px' }">
            <img :src="r.snapImage" class="image1">
            <img :src="r.personImage ? r.personImage : '/static/warn.jpg'" class="image1">
            <div style="padding: 14px;text-align: center;">
              <span>{{r.similarPoint}}</span>
              <span>{{r.personName}}</span>
              <div class="bottom clearfix">
                <time class="time">{{r.createTime}}</time>
              </div>
            </div>
          </el-card>
        </el-col>
      </template>
    </el-row>
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

  export default {
    name: "SnapRecord",
    data: function () {
      return {
        formLabelWidth: '100px',
        recordList: [],
        page: {
          currentPage: 1,
          pageSize: 8,
          totalPage: 0,
          offset: 0
        },
        searchForm: {
          personName: null,
        }
      };
    },
    methods: {
      handleCurrentChange: function (currentPage) {
        this.page.offset = (currentPage - 1) * this.page.pageSize;
        this.search();
      },
      searchRecordList(resetPage) {
        let self = this;
        if (resetPage) {
          self.page.offset = 0;
        }
        let url = '/api/face/admin/snap/record/list/page?offset=' + self.page.offset + '&limit=' + self.page.pageSize;
        self.post(url, self.searchForm, function (data) {
          self.recordList = data.rows;
          self.page.totalPage = data.total;  //信息总条数
        });
      },
      search(resetPage) {
        this.searchRecordList(resetPage);
      },
      resetSearch() {
        this.page = {
          currentPage: 1,
          pageSize: 8,
          totalPage: 0,
          offset: 0
        };
        this.searchForm = {
          personName: null,
        };
        this.search();
      }
    },
    created() {
      //实例创建完成后,页面渲染前执行
      this.search();
    },

    mounted() {
      //页面渲染完成后执行  cameraStatus
    }
  };
</script>
<style>
  .time {
    font-size: 10px;
    color: #999;
  }

  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image1 {
    width: 50%;
    float: left;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }

  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>





