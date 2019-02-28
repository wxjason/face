import Vue from 'vue';
import Router from 'vue-router';

//首页 home
import Home from '../components/Home.vue';

// admin
import Person from '../components/admin/Person.vue';

// system
import User from '../components/system/User.vue';
import Role from '../components/system/Role.vue';
import RoleAdd from '../components/system/RoleAdd.vue';
import RoleEdit from '../components/system/RoleEdit.vue';
import OperationLog from '../components/system/OperationLog.vue';

//个人中心
import PersonalCenter from '../components/system/PersonalCenter.vue';

import Login from '../Login.vue';
import App from '../App.vue';

import "babel-polyfill";

Vue.use(Router);


export default new Router({
  routes: [
    {
      path: '/login',
      name: 'Login',
      component: Login,
      meta: {
        ignoreAuth: true  // 添加该字段，表示进入这个路由是不需要登录的
      }
    },
    {
      path: '/',
      name: 'App',
      component: App,
      children: [
        {
          path: '/home',
          name: 'Home',
          component: Home
        },
        {
          path: '/admin/person',
          name: 'Person',
          component: Person
        },
        {
          path: '/user',
          name: 'User',
          component: User
        },
        {
          path: '/role',
          name: 'Role',
          component: Role
        },
        {
          path: '/role/add',
          name: 'RoleAdd',
          component: RoleAdd
        },
        {
          path: '/role/edit/:id',
          name: 'RoleEdit',
          component: RoleEdit
        },
        {
          path: '/personal/center',
          name: 'PersonalCenter',
          component: PersonalCenter
        },
        {
          path: '/operation/log',
          name: 'OperationLog',
          component: OperationLog
        }
      ]
    }


  ],
  mode: "history"
})
