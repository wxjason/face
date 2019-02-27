<template>
  <div class="menu">
    <el-menu :unique-opened="true">
      <template v-for="(menu1, index1) in menuList">
        <el-menu-item v-if="menu1.children.length == 0" :index="index1 + ''" @click="toUrl(menu1.menuUrl)"><i
          :class="menu1.menuIcon + ' lft-icon'"></i>{{menu1.menuName}}
        </el-menu-item>
        <el-submenu v-else :index="index1 + ''">
          <template slot="title"><i :class="menu1.menuIcon + ' lft-icon'"></i>{{menu1.menuName}}</template>
          <template v-for="(menu2, index2) in menu1.children">
            <el-menu-item :index="index1 + '-' + index2" @click="toUrl(menu2.menuUrl)">{{menu2.menuName}}</el-menu-item>
          </template>
        </el-submenu>
      </template>
    </el-menu>
  </div>
</template>

<script>
  export default {
    name: "Menu",
    data: function () {
      return {
        menuList: [],
      };
    },
    methods: {
      toUrl(url) {
        this.$router.push(url);
      },
      searchMenus() {
        let self = this;
        self.get('/api/system/menu/tree/menu', function (data) {
          self.menuList = data;
        });
      },
    },
    created: function () {
      this.searchMenus();
    }
  };
</script>
