<template>
  <div class="home">
    <div class="cover_bg">
      <header class="top-header" :class="{ 'initial-bg': isInitialBg }">
        <a class="t_slogan fl"></a>
        <a class="t_search fr"></a>
        <a @click="UserNav_move = !UserNav_move" class="t_menu fr"></a>
        <nav class="t_nav fr" >
          <li @click="navToMenu(item)" class="t_nav-item fl" v-for="item in t_nav" :key="item.name">{{ item.title }}</li>
        </nav>
        <!-- 登陆 -->
        <div v-if="show_LoginFrame" class="modal" @click.self="closeMessage">
            <div class="modal-content">
                <i @click="offMessage" class="user-icon_cancel"></i>
                <div :class="{'card': true, 'flipped': isFlipped}">
                    <div class="component-Login">
                        <span @click="toggleFlip" style="cursor: pointer;color: #00A1D6;font-size: 13px;">没有账号？去注册</span>
                        <el-card>
                            <component :is="LoginView"></component>
                        </el-card>
                    </div>
                    <div class="component-Register">
                        <span @click="toggleFlip" style="cursor: pointer;color: #00A1D6;font-size: 13px;">有账号了？去登陆</span>
                        <el-card>
                            <component :is="RegisterView"></component>
                        </el-card>
                    </div>
                </div>
                <p style="font-size: 11px;">登陆或完成注册即代表你同意<span style="color: #00A1D6;cursor: pointer;">用户协议</span>和<span style="color: #00A1D6;cursor: pointer;">隐私政策</span></p>
            </div>
        </div>
        <!-- 移动端导航 -->
        <el-drawer
          title="互联网信息协会"
          :visible.sync="UserNav_move"
          direction="rtl"
          :before-close="handleClose"
          :modal="false"
          size="60%">
          <ul>
              <li @click="navToMenu(item)" style="padding: 10px 12px 0;margin: 10px 0;text-align: left;" v-for="item in t_nav" :key="item.title">{{ item.title }}</li>
          </ul>
        </el-drawer>
      </header>
      <div class="bar_tab pa">
        <div class="hd tc">
          <span class="hd-btn" :class="{ active: showBtn === 'guide' }" @mouseover="showLi('guide')">服务指南</span>
          <span class="hd-btn" :class="{ active: showBtn === 'clubs' }" @mouseover="showLi('clubs')">社团一览</span>
        </div>
        <!-- <i class="i1 pa" style="padding: 30px;top: 200px;"></i> -->
        <li class="tab-li" v-show="showBtn === 'guide'">
          <a @click="navigateTo(item.url)" class="tab-item fl" v-for="item in tab_item.server" :key="item.title" :style="{ backgroundColor: item.color }">
            <i class="tab_icon pa" :class="getIcon(item)"></i>
            <p class="tab_title pa">{{ item.title }}</p>
          </a>
        </li>
        <li class="tab-li" v-show="showBtn === 'clubs'">
          <a @click="navigateToMass(item)" class="tab-item fl" v-for="(item, index) in paginatedItems" :key="index"  :style="{ backgroundColor: item.color }">
            <i class="tab_icon pa" :class="getIcon(item)"></i>
            <p class="tab_title pa">{{ item.title }}</p>
          </a>
          <div>
            <span class="circle pa c3" :class="{ active: currentPage === 1 }" @mousemove="changePage(1)"></span>
            <span class="circle pa c2" :class="{ active: currentPage === 2 }" @mousemove="changePage(2)"></span>
            <span class="circle pa c1" :class="{ active: currentPage === 3 }" @mousemove="changePage(3)"></span>
          </div>
        </li>
      </div>
      <a class="bar_down pa" @click="scrollDown"></a>
    </div>
    <div class="tab_wrap">
      <div class="hd">
        <span class="hd-btn" :class="{ active: showBtn === 'guide' }" @mouseover="showLi('guide')">服务指南</span>
        <span class="hd-btn" :class="{ active: showBtn === 'clubs' }" @mouseover="showLi('clubs')">社团一览</span>
      </div>
      <li class="wrap-li pr" v-show="showBtn === 'guide'">
        <a @click="navigateTo(item.url)" class="wrap-item pr" v-for="item in tab_item.server" :key="item.title" :style="{ backgroundColor: item.color }">
          <i class="wrap_icon pa" :class="getIcon(item)"></i>
          <p class="wrap_title pr">{{ item.title }}</p>
        </a>
      </li>
      <li class="wrap-li pr" v-show="showBtn === 'clubs'">
        <a @click="navigateToMass(item)" class="wrap-item pr" v-for="(item, index) in paginatedItems" :key="index"  :style="{ backgroundColor: item.color }">
          <i class="wrap_icon pa" :class="getIcon(item)"></i>
          <p class="wrap_title pr">{{ item.title }}</p>
        </a>
        <div style="width: 100%;margin-top: 40px;">
          <span class="circle2 pa c6" :class="{ active: currentPage === 1 }" @mousemove="changePage(1)"></span>
          <span class="circle2 pa c5" :class="{ active: currentPage === 2 }" @mousemove="changePage(2)"></span>
          <span class="circle2 pa c4" :class="{ active: currentPage === 3 }" @mousemove="changePage(3)"></span>
        </div>
      </li>
    </div>


    <div class="flex-container">

      <div class="news">
        <div class="news_pc">
          <section class="section-news tl">
            <div class="tab_news fl">
              <ul class="hd">
                <li class="i" @mouseover="ShowNewsComponent('Mass')" :class="{active: showNews === 'Mass'}">
                  社团动态
                </li>
                <b class="i"></b>
                <li class="i" @mouseover="ShowNewsComponent('Web')" :class="{active: showNews === 'Web'}">
                  站点动态
                </li>
              </ul>
              <div class="news_component">
                pc
                <MassNews v-show="showNews === 'Mass' " />
                <WebNews v-show="showNews === 'Web'" />
              </div>
            </div>
            <div class="notice_wrap fr">
              <h2 style="font-weight: 700;color: #1f3214;">通知公告</h2>
              <ul class="list_notice">
                <li @click="navToNotice(item.title)" class="list_noticeItem" v-for="item in list_notice" :key="item.title">
                  <span class="list_noticeItem_title">{{ item.title }}</span>
                  <span class="list_noticeItem_time">{{ item.create_time }}</span>
                </li>
              </ul>
            </div>
          </section>
        </div>
        <div class="news_mobile">
          <section class="section-news tl">
            <div class="tab_news db">
              <ul class="hd">
                <li class="i" @mouseover="ShowNewsComponent('Mass')" :class="{active: showNews === 'Mass'}">
                  社团动态
                </li>
                <b class="i"></b>
                <li class="i" @mouseover="ShowNewsComponent('Web')" :class="{active: showNews === 'Web'}">
                  站点动态
                </li>
              </ul>
              <div class="news_component">
                <MassNews v-show="showNews === 'Mass' " />
                <WebNews v-show="showNews === 'Web'" />
              </div>
            </div>
            <div class="notice_wrap db w100">
              <ul class="list_notice">
                <h2 style="font-weight: 700;color: #1f3214;">通知公告</h2>
                <li @click="navToNotice(item.title)" class="list_noticeItem" v-for="item in list_notice" :key="item.title">
                  <span class="list_noticeItem_title">{{ item.title }}</span>
                  <span class="list_noticeItem_time">{{ item.create_time }}</span>
                </li>
              </ul>
            </div>
          </section>
        </div>
      </div>
      <div class="bg_nic"></div>
      <div class="foot">
        <p>&copy;Ganxy|2024</p>
        <p>
          <!-- <span></span> -->
          <a href="https://beian.miit.gov.cn/#/Integrated/index/">
            浙ICP备2022019862号-1
          </a>
        </p>
        <p><span>技术支持:</span>互联网信息协会</p>
      </div>

    </div>
    
  </div>
</template>

<script>
import { mapState, mapMutations } from 'vuex';

import MassNews from "@/views/news/MassNews.vue"
import WebNews from "@/views/news/WebNews.vue"

import LoginView from '@/views/shetuan/Login/LoginView.vue'
import RegisterView from "@/views/shetuan/Register/RegisterView.vue"
export default {
  components: {
    MassNews,
    WebNews,
    LoginView,
    RegisterView
  },
  data() {
    return {
      bing: {
        title: '',
        url: '',
        copyright: '',
        copyrightlink: ''
      },
      device: {
        Width: 0,
        Height: 0
      },
      t_nav: [
        {
          title: '部门概况',
          link: '/bmgk/部门简介'
        },
        {
          title: '服务指南'
        },
        {
          title: '规章制度',
          link: 'gzzd/'
        },
        {
          title: '信息公示'
        },
        {
          title: '下载专区'
        },
        {
          title: '联系我们',
          link: '/bmgk/联系我们'
        }
      ],
      showBtn: 'guide',
      tab_item: {
        server: [
          {
            icon: 'i1',
            title: '社员账户',
            color: '#5EB3BF',
            url: '/user'
          },
          {
            icon: 'i2',
            title: 'Ai问答系统',
            color: '#635CBD',
            url: '/ai'
          },
          // {
          //   icon: 'i3',
          //   title: '在线办公工具',
          //   color: '#7D6CB5'
          // },
          {
            icon: 'i4',
            title: '社团管理系统',
            color: '#3C98B3',
            url: '/manager-login'
          },
          {
            icon: 'i5',
            title: '后台管理系统',
            color: '#386FB2',
            url: '/houtai-login'
          }
        ],
        clubs: [
          {
            icon: '',
            title: '互联网社',
            color: '#2AB2D9',
            url: '/mass'
          },
          {
            icon: '',
            title: '花绳俱乐部',
            color: '#3790AA',
            url: '/mass'
          },
          {
            icon: '',
            title: '爱乐社',
            color: '#56AAB6',
            url: '/mass'
          },
          {
            icon: '',
            title: '新梦想',
            color: '#3790AA',
            url: '/mass'
          },
          {
            icon: '',
            title: '书画社',
            color: '#386FB2',
            url: '/mass'
          },
          {
            icon: '',
            title: '心理社',
            color: '#4E978E',
            url: '/mass'
          },
          {
            icon: '',
            title: '习茶社',
            color: '#635CBD',
            url: '/mass'
          },
          {
            icon: '',
            title: '武术社',
            color: '#3790AA',
            url: '/mass'
          },
          {
            icon: '',
            title: '台球社',
            color: '#2AB2D9',
            url: '/mass'
          },
          {
            icon: '',
            title: '语言社',
            color: '#635CBD',
            url: '/mass'
          },
          {
            icon: '',
            title: '汉服社',
            color: '#3790AA',
            url: '/mass'
          },
          {
            icon: '',
            title: '口才社',
            color: '#2AB2D9',
            url: '/mass'
          },
          {
            icon: '',
            title: '棋社',
            color: '#2AB2D9',
            url: '/mass'
          },
          {
            icon: '',
            title: '摄影社',
            color: '#5EB3BF',
            url: '/mass'
          },
          {
            icon: '',
            title: '读书社',
            color: '#2AB2D9',
            url: '/mass'
          },
          {
            icon: '',
            title: '舞蹈社',
            color: '#635CBD',
            url: '/mass'
          },
          {
            icon: '',
            title: '动漫社',
            color: '#56AAB6',
            url: '/mass'
          },
          {
            icon: '',
            title: '羽毛球社',
            color: '#56AAB6',
            url: '/mass'
          },
          {
            icon: '',
            title: '乒乓球社',
            color: '#5EB3BF',
            url: '/mass'
          },
          {
            icon: '',
            title: '文学社',
            color: '#635CBD',
            url: '/mass'
          },
        ]
      },
      currentPage: 1,
      itemsPerPage: 8,
      isInitialBg: true,
      showNews: 'Mass',
      UserNav_move: false,
      show_LoginFrame: false,
      isFlipped: false,
      LoginView: 'LoginView',
      RegisterView: 'RegisterView',
      list_notice: []
    }
  },
  mounted() {
    this.device.Width = window.innerWidth;
    this.device.Height = window.innerHeight;
  },
  created() {
    // localStorage.clear()
    console.log(this.$store.state.isLogined);
    // this.getBing()
    this.getNoticeTitle()
  },
  computed: {
    ...mapState(['isLogined']), // 将isLogined映射为计算属性
    paginatedItems() {
      const start = (this.currentPage - 1) * this.itemsPerPage;
      const end = start + this.itemsPerPage;
      return this.tab_item.clubs.slice(start, end);
    },
  },
  methods: {
    ...mapMutations(['SET_IS_LOGINED']), // 将SET_IS_LOGINED映射为方法
    
    getBing() {
      fetch("/bing/HPImageArchive.aspx?format=js&idx=0&n=1&mkt=zh-CN")
      .then(res => res.json())
      .then(res => {
        // console.log(res.images)
        this.bing.title = res.images[0].title
        this.bing.url = "https://cn.bing.com" + res.images[0].url
        this.bing.copyright = res.images[0].copyright
        this.bing.copyrightlink = res.images[0].copyrightlink
        // console.log(this.bing.url)
      })
    },
    showLi(button) {
      this.showBtn = button;
    },
    changePage(page) {
      this.currentPage = page;
    },
    getIcon(item) {
      return item.icon;
    },
    scrollDown() {
      const vh = window.innerHeight;
      const scrollDistance = vh - 100;
      window.scrollTo({
        top: scrollDistance,
        behavior: 'smooth', // 平滑滚动
      });
    },
    ShowNewsComponent(name) {
      this.showNews = name
    },
    navigateTo(url) {
      // console.log(url)
      if(url == '/ai' || url == '/houtai-login' || url == '/manager-login') {
        this.$router.push(url);
        return
      }
      const sno = localStorage.getItem("Sno")
      url = url + "/" + sno
      if (localStorage.getItem("isLogined") === "true") {
        this.$router.push(url);
      } else {
        this.$message({
          message: '还没有登陆哦',
          type: 'warning'
        })
        this.show_LoginFrame = true
      }
    },
    navigateToMass(item) {
      const mass = item.title
      const url = item.url + "/" + mass
      console.log(url)
      this.$router.push(url);
    },
    navToMenu(item) {
      console.log(item)
      if(item.title === "服务指南" || item.title === "规章制度" || item.title === "信息公示" || item.title === "下载专区") {
        this.$message({
          message: '导航还没内容...',
          type: 'warning'
        })
        return
      }
      this.$router.push(item.link);
      // location.reload()
    },
    handleClose(done) {
      done()
    },
    offMessage() {
        this.show_LoginFrame = false
    },
    closeMessage(event) {
        const isModalContent = event.target.classList.contains('modal-content');
        const isComponent = event.target.classList.contains('component');
        if (!isModalContent && !isComponent) {
            this.show_LoginFrame = false;
        }
    },
    toggleFlip() {
      this.isFlipped = !this.isFlipped;
    },
    getNoticeTitle() {
      const url = process.env.VUE_APP_BASE_URI + '/gonggao-notice/notice-getTitle'
      fetch(url, {
        method: "POST",
        headers: {
            'Accept': 'Application/json',
            'Content-Type': 'Application/json'
        }
      })
      .then(res => res.json())
      .then(res => {
        // console.log(res)

        this.list_notice = res
      })
    },
    navToNotice(title) {
      this.$router.push("/notice/"+title)
    }
  }
}
</script>

<style>
* {
  padding: 0;
  margin: 0;
}
.cover_bg {
  /* position: fixed; */
  top: 0;
  width: 100%;
  height: 100vh;
  background: url(../assets/img/banner/banner1.jpg) 40% no-repeat;
  background-size: cover;
  z-index: -1;
}
.top-header {
  height: 100px;
  width: 100%;
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  /* background: linear-gradient(to bottom, rgba(0, 0, 0, 0.5), rgba(194, 194, 194, 0.1)); */
  z-index: 99;
}
.initial-bg {
  background: linear-gradient(to bottom, rgba(0, 0, 0, 0.5), rgba(194, 194, 194, 0.1));
}
.t_slogan {
  width: 100px;
  height: 100px;
  background: url(../assets/img/icon/t_slogan.png) 50% no-repeat;
}
.t_search {
  width: 100px;
  height: 100px;
  background: url(../assets/img/icon/t_search.png) 50% no-repeat;
}
.t_search::before {
  content: "";
  position: absolute;
  right: 100px;
  width: 1px;
  height: 100px;
  background-image: url(../assets/img/icon/t_line.png);
  /* linear-gradient(to right, rgb(170, 170, 170), rgb(170, 170, 170)) */
}
.t_menu {
  width: 100px;
  height: 100px;
  background: url(../assets/img/icon/i_menu.png) 50% no-repeat;
  display: none;
}
.t_nav {
  height: 100px;
  position: relative;
  padding: 0;
  /* background-color: blueviolet; */
}
.t_nav-item {
  line-height: 100px;
  padding: 0 20px;
  height: 100px;
  color: #fff;
  cursor: pointer;
  list-style: none;
  position: relative;
}
.t_nav-item::before,
.t_nav-item::after {
  content: "";
  position: absolute;
  bottom: 0;
  width: 0;
  height: 2px;
  background-color: #ecac35;
  transition: width 0.3s;
}
.t_nav-item::before {
  left: 50%;
  transform: translateX(-50%);
}
.t_nav-item::after {
  right: 50%;
  transform: translateX(50%);
}
.t_nav-item:hover::before,
.t_nav-item:hover::after {
  width: 100%;
}
.t_nav-item:hover::before {
  transition: width 0.3s 0.15s;
}
.t_nav-item:hover::after {
  transition: width 0.3s;
}
.t_nav-item:hover {
  color: #ecac35;
  /* border-bottom: 2px solid #ecac35; */
}

.top-header .el-drawer__header {
    font-size: 20px;
}






.bar_tab {
  /* background-color: aquamarine; */
  right: 120px;
  top: 20vh;
  list-style: none;
}
.hd {
  /* background-color: rgba(166, 122, 233, 0.5); */
  padding: 0 10px;
}
.hd-btn {
  width: 120px;
  height: 40px;
  cursor: pointer;
  color: #fff;
  padding: 10px 20px;
  border-radius: 20px;
  position: relative;
  z-index: 1;
}
.hd-btn::before,
.hd-btn::after {
  content: "";
  position: absolute;
  bottom: 0;
  width: 0;
  height: 40px;
  background-color: #ecac35;
  border-radius: 20px;
  transition: width 0.3s;
  z-index: -1;
}
.hd-btn::before {
  left: 50%;
  transform: translateX(-50%);
}
.hd-btn::after {
  right: 50%;
  transform: translateX(50%);
}
.hd-btn.active::before,
.hd-btn.active::after {
  width: 100%;
}
.hd-btn.active::before {
  transition: width 0.3s 0.15s;
}
.hd-btn.active::after {
  transition: width 0.3s;
}
.hd-btn.active {
  /* background-color: #ecac35; */
}
.tab-li {
  margin-top: 20px;
  width: 300px;
  flex-wrap: wrap;
  /* position: relative; */
}
.tab-item {
  width: 120px;
  height: 95px;
  padding: 10px;
  margin: 5px;
  border-radius: 5px;
  position: relative;
  cursor: pointer;
  background-color: cornflowerblue;
  transition: transform 0.3s ease-in-out;
}
.tab-item:hover {
  transform: scale(1.1); /* 放大到1.2倍 */
}
.tab_icon {
  padding: 20px;
  padding-bottom: 55px;
  margin-left: -20px;
  animation: none;
}
.tab-item:hover .tab_icon {
  animation: ani_shake 0.6s linear;
}
@keyframes ani_shake {
  0% {
    transform: rotate(0deg);
  }
  20% {
    transform: rotate(-15deg);
  }
  40% {
    transform: rotate(15deg);
  }
  60% {
    transform: rotate(-7.5deg);
  }
  80% {
    transform: rotate(7.5deg);
  }
  100% {
    transform: rotate(0deg);
  }
}
.tab_title {
  color: #fff;
  width: 120px;
  top: 65px;
}
.circle {
  width: 10px;
  height: 10px;
  border-radius: 50%;
  background-color: #fff;
  bottom: -20px;
  cursor: pointer;
}
.circle.active {
  background-color: #ecac35;
}

.bar_down {
  bottom: 20px;
  width: 82px;
  height: 82px;
  margin-left: -40px;
  background: url(../assets/img/icon/i_down.png) 50% no-repeat;
  animation: ani_down 2s linear infinite;
  cursor: pointer;
}
.bar_down::before {
  content: '';
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background: url(../assets/img/icon/i_circle.png) 50% no-repeat;
  animation: ani_circle 2s linear infinite;
}


.list_notice {
  width: 100%;
  min-height: 472px;
  /* background-color: #60b2de; */
}
.list_noticeItem {
  font-size: 20px;
  list-style: none;
  margin: 0 20px;
  padding: 12px 0;
  display: flex;
  justify-content: space-between;
  align-items: center;
  cursor: pointer;
}
.list_noticeItem_title {
  color: #1a1a1a;
  order: 1;
}
.list_noticeItem_time {
  order: 2;
}

@keyframes ani_circle {
  from {
    transform: rotate(0deg);
  }
  to {
    transform: rotate(360deg);
  }
}
@keyframes ani_down {
  0%, 100% {
    transform: translateY(10px);
  }
  50% {
    transform: translateY(-10px);
  }
}

.i1 {
  background: url(../assets/img/icon/i1.png) 100% no-repeat;
}
.i2 {
  background: url(../assets/img/icon/i2.png) 100% no-repeat;
  padding-left: 17px;
}
.i3 {
  background: url(../assets/img/icon/i3.png) 100% no-repeat;
  margin-left: -15px;
}
.i4 {
  background: url(../assets/img/icon/i4.png) 100% no-repeat;
}
.i5 {
  background: url(../assets/img/icon/i5.png) 100% no-repeat;
  padding-left: 17px;
}
.i_more {
  background: url(../assets/img/icon/i_more.png) 100% no-repeat;
}
.c1 {
  right: 120px;
}
.c2 {
  right: 150px;
}
.c3 {
  right: 180px;
}

.fl {
  float: left;
}

.fr {
  float: right;
}
.i {
  display: inline-block;
}
.pa {
  position: absolute;
}
.pr {
  position: relative;
}
.tc {
  text-align: center;
}
.tl {
  text-align: left;
}
.tr {
  text-align: right;
}















.tab_wrap {
  width: 100%;
  /* top: 100vh; */
  list-style: none;
  background-color: #60b2de;
  display: none;
}
.wrap-li {
  width: 90%;
  /* height: 120px; */
  flex-wrap: wrap;
  /* background-color: #546F44; */
  left: 5%;
  padding-bottom: 50px;
}
.wrap-item {
  display: inline-block;
  width: 120px;
  height: 95px;
  margin: 5px 10px;
  border-radius: 5px;
  top: 30px;
  /* background-color: cornflowerblue; */
  transition: transform 0.3s ease-in-out;
  cursor: pointer;
}
.wrap-item:hover {
  transform: scale(1.2);
}
.wrap_icon {
  padding: 20px;
  padding-bottom: 55px;
  margin-left: -20px;
}
.wrap_title {
  color: #fff;
  width: 120px;
  top: 60px;
  animation: none;
}
.wrap-item:hover .wrap_icon {
  animation: ani_shake 0.6s linear;
}
.circle2 {
  width: 10px;
  height: 10px;
  border-radius: 50%;
  background-color: #fff;
  cursor: pointer;
}
.circle2.active {
  background-color: #ecac35;
}
.c4 {
  right: 40%;
}
.c5 {
  right: 50%;
}
.c6 {
  right: 60%;
}


.flex-container {
  display: flex;
  flex-wrap: wrap;
  /* background-color: rgb(91, 243, 9);
  padding: 15px; */
}

.news {
  height: calc(100vh - 100px);
  width: 100%;
  /* background-color: blueviolet; */
  background: url(../assets/img/banner/bg_news.jpg);
}

.section-news {
  position: relative;
  top: 60px;
  max-width: 1400px;
  background-color: #ecac35;
}
.tab_news {
  /* flex: 1 0 68%; */
  width: calc(68% - 60px);
}
.tab_news .hd {
  margin: 0 20px 40px;
  list-style: none;
  font-size: 24px;
  line-height: 32px;
}

.tab_news .hd b {
  height: 20px;
  width: 28px;
  background: url(../assets/img/icon/i_slash.png) 50% no-repeat;

}

.tab_news .hd li.active {
  font-weight: 700;
  color: #546F44;
}

.notice_wrap {
  /* flex: 1 0 32%; */
  width: 32%;
  height: 472px;
  /* background-color: aquamarine; */
  /* right: 10px; */
  /* position: relative; */
}


.bg_nic {
  width: 100%;
  height: 256px;
  background: url(../assets/img/banner/bg_nic.jpg) 50% no-repeat;
}
.foot {
  height: 80px;
  width: 100%;
  color: #bbb;
  padding: 12px 0;
  font-size: 14px;
  background: url(../assets/img/banner/bg_footer.jpg) 50% no-repeat;
  /* background-color: chocolate; */
}
.foot p {
  line-height: 25px;
}
.foot p span {
  font-size: 11px;
  margin: 0 5px;
}
.foot p a {
  color: #bbb;
  text-decoration: none;
}
.foot p a:hover {
  color: #fff;
}



















.news_pc {
  display: block;
}
.news_component {
  min-height: 400px;
}
.news_slab {
  display: none;
}
.news_mobile {
  display: none;
  padding: 0;
}
.news_mobile .section-news {
  top: 0;
}
.notice_wrap {
  opacity: 0.6;
}

.w100 {
  width: 100%;
  opacity: 0.4;
}
.db {
  display: block;
}












/* 登陆 */
.modal {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    z-index: 99;
}
.modal-content {
    position: relative;
    top: 25vh;
    left: 27%;
    padding: 0 20px;
    padding-top: 25px;
    padding-bottom: 29px;
    width: 46%;
    height: 40vh;
    border-radius: 5px;
    background-color: #fff;
}
.card {
    width: 100%;
    height: 100%;
    transition: transform 0.8s;
    transform-style: preserve-3d;
}
.component-Login, .component-Register {
    position: absolute;
    width: 100%;
    height: 100%;
    backface-visibility: hidden;
}
.component-Login {
    transform: rotateY(0deg);
    z-index: 2;
}
.component-Register {
    transform: rotateY(180deg);
    z-index: 1;
}
.flipped {
    transform: rotateY(180deg);
}
.user-icon_cancel {
    position: absolute;
    width: 32px;
    height: 32px;
    right: 16px;
    cursor: pointer;
    z-index: 999;
    background: url(../assets//img/user/icon/i_cancel.png) 50% no-repeat;
}



@media screen and (max-width: 1110px) {
  .t_nav-item {
    padding: 0 10px;
    font-size: 14px;
  }
}

@media screen and (max-width: 1000px) {
  .t_nav-item {
    padding: 0 5px;
  }
  .bar_tab {
    display: none;
  }
  .tab_wrap {
    display: block;
  }
  .news {
    height: 620px;
  }
  .news_pc {
    display: none;
  }
  .news_slab {
    display: block;
  }
  /* .tab_news {
    float: none;
    width: 100%;
  }
  .notice_wrap {
    float: none;
    display: block;
    top: 500px;
    width: 100%;
    right: 0;
  }
  .foot {
    position: relative;
    top: 0px;
  } */
}

@media screen and (max-width: 945px) {
  .t_nav-item {
    display: none;
  }
  .t_search {
    display: none;
  }
  .t_menu {
    display: block;
  }
}

@media screen and (max-width: 768px) {
  .news_slab {
    display: none;
  }
  .news_mobile {
    display: block;
  }
  .news_mobile {

  }
  .tab_news {
    width: 100%;
  }
  .tab_news ul {
    text-align: center;
  }
  .news_component {
    width: 100%;
  }
  .foot {
    position: relative;
    top: 65px;
  }
}
@media screen and (max-width: 480px) {
  .cover_bg {
    background: url(../assets/img/banner/banner1\(move\).png) 40% no-repeat;
  }
  .modal-content {
    width: 65%;
    left: 12%;
  }
  .el-message-box__wrapper .el-message-box {
    width: 96%;
  }
  .foot {
    position: relative;
    top: 465px;
  }
}
</style>
