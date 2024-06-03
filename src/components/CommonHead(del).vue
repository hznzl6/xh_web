<template>
    <header class="top-header" :class="{ 'initial-bg': isInitialBg }">
        <a style="cursor: pointer;" @click="navToMenu('/')" class="t_slogan fl"></a>
        <a class="t_search fr"></a>
        <a @click="UserNav_move = !UserNav_move" class="t_menu fr"></a>
        <nav class="t_nav fr" >
          <li @click="navToMenu(item)" class="t_nav-item fl" v-for="item in t_nav" :key="item.name">{{ item.title }}</li>
        </nav>
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
</template>

<script>
export default {
    data() {
        return {
            isInitialBg: true,
            t_nav: [
                {
                title: '部门概况',
                link: '/bmgk/部门简介'
                },
                {
                title: '服务指南',
                link: ''
                },
                {
                title: '规章制度'
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
            UserNav_move: false,
        }
    },
    methods: {
        navigateTo(url) {
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
        handleClose(done) {
            done()
        },
        navToMenu(item) {
          console.log(item)
          if(item.title === "服务指南" || item.title === "规章制度" || item.title === "信息公示" || item.title === "下载专区") {
            this.$message({
              message: '导航没内容哦',
              type: 'warning'
            })
            return
          }
          this.$router.push(item.link);
          location.reload()
        },
    }
}

</script>

<style>
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
}
@media screen and (max-width: 945px) {
  .t_nav-item {
    display: none;
  }
}
</style>