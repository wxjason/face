<template>
  <div class="mncont">
    <!-- 标题 -->
    <div class="pgTitle">
      <p>人员管理</p>
      <div class="optdv">
        <el-button v-if="permission.add" type="text" @click="addUser"><i class="my-icon-add my-icon"></i>添加人员</el-button>
      </div>
    </div>
    <!-- form表单 -->
    <el-form :inline="true" :model="searchForm" class="demo-form-inline my-form" label-width="80px">
      <el-form-item label="人员姓名：">
        <el-input v-model="searchForm.personName" placeholder="全部"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="search(true)"><i class="my-icon-search my-icon"></i>搜索</el-button>
        <el-button type="primary" plain @click="resetSearch"><i class="my-icon-reset my-icon"></i>重置</el-button>
      </el-form-item>
    </el-form>
    <!-- 数据表 -->
    <el-row :gutter="20">
      <template v-for="p in personList">
        <el-col v-on:mouseout.native="settingShow = false"
                v-on:mouseover.native="settingShow = true"
                :span="3" style="margin-bottom:10px;position: relative;height: 50%!important;">
          <el-card :body-style="{ padding: '0px' }">
            <img :src="p.personImage" class="image">
            <div style="padding: 14px;text-align: center;">
              <span>{{p.personName}}</span>
              <div class="bottom clearfix">
                <time class="time">{{p.createTime}}</time>
              </div>
              <el-button
                style="position: absolute; top: 0; right: 30px;"
                v-if="settingShow && permission.edit"
                size="mini"
                title="编辑"
                @click="handleEdit(p.id)"
                type="text">
                <i class="my-icon-edit my-icon"></i>
              </el-button>
              <el-button
                style="position: absolute; top: 0; right: 10px;"
                v-if="settingShow && permission.del"
                size="mini"
                title="删除"
                @click="handleDelete(p.id)"
                type="text">
                <i class="my-icon-del my-icon"></i>
              </el-button>
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
    <!-- 添加人员 -->
    <el-dialog title="添加人员" :visible.sync="addBoxShow" class="myBox" width="500px">
      <el-form :model="addForm">
        <el-form-item label="姓名：" :label-width="formLabelWidth" required>
          <el-input v-model="addForm.personName"></el-input>
        </el-form-item>
        <el-form-item label="人员正脸照：" required>
          <el-upload
            class="avatar-uploader"
            action=""
            :multiple="false"
            accept="image/*"
            :show-file-list="false"
            :before-upload="beforeAvatarUpload">
            <img v-if="imageBase64" :src="imageBase64" class="avatar">
            <i class="avatar-uploader-icon el-icon-plus" v-else></i>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="addSure">确 认</el-button>
        <el-button @click="addBoxShow = false">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 编辑人员 -->
    <el-dialog title="编辑人员" :visible.sync="editBoxShow" class="myBox" width="500px">
      <el-form :model="editForm">
        <el-form-item label="姓名：" :label-width="formLabelWidth" required>
          <el-input v-model="editForm.personName"></el-input>
        </el-form-item>
        <el-form-item label="人员正脸照：" required>
          <el-upload
            class="avatar-uploader"
            action=""
            :multiple="false"
            accept="image/*"
            :show-file-list="false"
            :before-upload="beforeAvatarUpload">
            <img v-if="imageBase64" :src="imageBase64" class="avatar">
            <i class="avatar-uploader-icon el-icon-plus" v-else></i>
          </el-upload>
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
    name: "Person",
    data: function () {
      return {
        settingShow: false,
        imageBase64: null,
        permission:{
          add:false,
          edit: false,
          del: false,
        },
        passwordType: 'password',
        addBoxShow: false,
        editBoxShow: false,
        formLabelWidth: '100px',
        personList: [],
        page: {
          currentPage: 1,
          pageSize: 16,
          totalPage: 0,
          offset: 0
        },
        searchForm: {
          personName: null,
        },
        addForm: {
          personName: null,
          personImage: null
        },
        editForm: {
          personName: null,
          personImage: null
        }
      };
    },
    methods: {
      beforeAvatarUpload(file){
        this.addForm.personImage = "";
        this.imageBase64 = "";
        const type = file.type;
        const isLt2M = file.size / 1024 / 1024 < 1;
        const isIMG = (type === 'image/jpeg') || (type === 'image/jpg') || (type === 'image/png');
        if (!isIMG) {
          this.$message.error('上传图片只能是 JPEG,JPG,PNG 格式!');
          return false;
        }
        if (!isLt2M) {
          this.$message.error('上传图片大小不能超过 1MB!');
          return false;
        }
        let self = this;
        const loading = self.$loading(self.loadingOptions);
        let reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = function(evt){ //读取完文件之后会回来这里
          loading.close();
          self.imageBase64 = evt.target.result;
          self.addForm.personImage = self.imageBase64.substring(self.imageBase64.indexOf(",") + 1);
        };
        return false;
      },
      handleCurrentChange: function (currentPage) {
        this.page.offset = (currentPage - 1) * this.page.pageSize;
        this.search();
      },
      addUser(){
        this.addForm = {
          personName: null,
          personImage: null
        };
        this.imageBase64 = '';
        this.addBoxShow = true
      },
      addSure() {//添加人员，确定
        let self = this;
        let url = '/api/face/admin/person/add';
        self.post(url, self.addForm, function () {
          self.$message.success('添加成功');
          self.search();
          self.addBoxShow = false;
        });
      },
      handleEdit(personId) {//编辑人员，打开
        let self = this;
        let url = '/api/face/admin/person/detail/' + personId;
        self.get(url, function (data) {
          self.editForm = data;
          self.imageBase64 = data.personImage;
          self.editForm.personImage = self.imageBase64.substring(self.imageBase64.indexOf(',') + 1);
          self.editForm.createTime = null;
          self.editBoxShow = true;
        });
      },
      editSure() {//编辑人员，确定
        let self = this;
        let url = '/api/face/admin/person/update';
        self.post(url, self.editForm, function () {
          self.$message.success('修改成功');
          self.search();
          self.editBoxShow = false;
        });
      },
      handleDelete(personId) {
        this.$confirm('', '确认删除此人员？', {
          confirmButtonText: '确 认',
          cancelButtonText: '取 消',
          type: 'warning',
          center: true,
          showClose: false,
          customClass: 'delBox'
        }).then(() => {
          let self = this;
          let url = '/api/face/admin/person/delete/' + personId;
          self.get(url, function () {
            self.search();
            self.$message.success('删除成功');
          });
        }).catch(() => {
        });
      },
      searchPersonList(resetPage) {
        let self = this;
        if (resetPage) {
          self.page.offset = 0;
        }
        let url = '/api/face/admin/person/list/page?offset=' + self.page.offset + '&limit=' + self.page.pageSize;
        self.post(url, self.searchForm, function (data) {
          self.personList = data.rows;
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
        this.searchPersonList(resetPage);
      },
      resetSearch() {
        this.page = {
          currentPage: 1,
          pageSize: 16,
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
      this.searchMenuPermissions();
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

  .image {
    width: 100%;
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





