<template>
  <el-container class="outer">
    <el-header height="70px">
      <!-- 头部 -->
      <Header></Header>
    </el-header>
    <el-container class="second-outer">
      <el-aside width="220px">
        <!-- 左侧菜单 -->
        <Menu></Menu>
      </el-aside>
      <el-container>
        <el-main id="el-main">
          <!-- 主体 -->
          <router-view></router-view>
        </el-main>
        <el-footer>
          <!-- 脚部 -->
          <Footer></Footer>
        </el-footer>
      </el-container>
    </el-container>
  </el-container>
</template>

<script>
  import Header from './Header';
  import Menu from './Menu';
  import Footer from './Footer';

  export default {
    name: 'App',
    components: {Header, Menu, Footer}
  }


</script>

