import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
// import store from './store/';
import store from '@/store'
import { v4 as uuidv4 } from 'uuid';

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
  },
  {
    path: '/service',
    name: 'service',
    component: () => import('../views/navPage/ServiceView.vue'),
  },
  {
    path: '/rule',
    name: 'rule',
    component: () => import('../views/navPage/RuleView.vue'),
  },
  {
    path: '/anno',
    name: 'anno',
    component: () => import('../views/navPage/MessageAnnounce.vue'),
  },
  {
    path: '/download',
    name: 'download',
    component: () => import('../views/navPage/DownLoad.vue'),
  },
  {
    path: '/connet',
    name: 'connet',
    component: () => import('../views/navPage/ConnetView.vue'),
  },
  {
    path: '/bmgk/:name',
    name: 'bmgk',
    component: () => import('../views/bmgk/DepartmentView.vue'),
  },
  {
    path: '/notice/:title',
    name: 'notice',
    component: () => import('../views/notice/NoticeView.vue'),
  },
  {
    path: '/user/:sno',
    name: 'user',
    component: () => import('../views/shetuan/User/UserView.vue'),
  },
  {
    path: '/mass/:mass',
    name: 'mass',
    component: () => import('../views/shetuan/Mass/MassIntro.vue'),
  },
  {
    path: '/editInrto/:mass',
    name: 'editarticle',
    component: () => import('../views/Article/ArticleEdit.vue'),
  },
  {
    path: '/manager/:mass',
    name: 'manager',
    component: () => import('../views/shetuan/Manager/ManagerView.vue'),
  },
  {
    path: '/manager-login',
    name: 'managerLogin',
    component: () => import('../views/shetuan/Manager/LoginView.vue'),
  },
  {
    path: '/houtai/:account',
    name: 'houtai',
    component: () => import('../views/Houtai/HoutaiManager.vue'),
  },
  {
    path: '/houtai-login',
    name: 'houtaiLogin',
    component: () => import('../views/Houtai/LoginView.vue'),
  },
  {
    path: '/ai',
    name: 'chatai',
    redirect: '/ai/' + uuidv4(),
    component: () => import('../views/function/Ai/ChatView.vue'),
  },
  {
    path: '/ai/:uuid',
    name: 'chatid',
    component: () => import('../views/function/Ai/ChatView.vue'),
  },
  {
    path: '/about',
    name: 'about',
    component: () => import('../views/AboutView.vue'),
  },
  {
    path: '/about/:sign',
    name: 'showArticle',
    component: () => import('../views/ShowArticle.vue')
  },
  {
    path: '/404',
    name: '404',
    component: () => import('../views/ErrorView.vue')
  },
  {
    path: '*',
    redirect: '/404'
  },
  {
    path: '/test01',
    name: 'test01',
    component: () => import('../views/test/TestView.vue')
  },
  {
    path: '/content',
    name: 'content',
    component: () => import('../components/HomeContent.vue')
  },
]

const router = new VueRouter({
  mode: 'history',
  base: '',
  routes,
  store
})

router.beforeEach((to, from, next) => {
  const isLogined = localStorage.getItem('isLogined') === 'true';
  const isHoutai = localStorage.getItem('isHoutai') === 'true';
  const isManager = localStorage.getItem('isManager') === 'true';
  isManager
  // 判断是否要守卫/user目录
  if (to.path.startsWith('/user')) {
    if (isLogined) {
      next(); // 允许访问
    } else {
      // next('/'); // 未登录则重定向至根路径
      next({
        path: '/',
        query: { message: '还没有登陆哦', type: 'warning' }
      });
      // this.$message({
      //   message: '还没有登陆哦',
      //   type: 'warning'
      // })
      return;
    }
  } else if (to.path.startsWith('/houtai')) {
    if (to.path === '/houtai-login') {
      next(); // 不受路由守卫影响
    } else {
      if (isHoutai) {
        next(); // 允许访问
      } else {
        next({
          path: '/houtai-login',
          query: { message: '还没有登陆哦', type: 'warning' }
        });
        
        // this.$message({
        //   message: '还没有登陆哦',
        //   type: 'warning'
        // })
        return;
      }
    }
    
  } else if (to.path.startsWith('/manager')) {
    if (to.path === '/manager-login') {
      next(); // 不受路由守卫影响
    } else {
      if (isManager) {
        next(); // 允许访问
      } else {
        next({
          path: '/manager-login',
          query: { message: '还没有登陆哦', type: 'warning' }
        });
        
        // this.$message({
        //   message: '还没有登陆哦',
        //   type: 'warning'
        // })
        return;
      }
    }
  }else {
    next(); // 允许访问其他路径
  }
})

export default router
