<template>
    <div class="UserSide-Container" v-loading.fullscreen.lock="fullscreenLoading">
        <!-- <el-radio-group v-model="isCollapse" style="margin-bottom: 20px;">
            <el-radio-button :label="false">展开</el-radio-button>
            <el-radio-button :label="true">收起</el-radio-button>
        </el-radio-group> -->
        <el-menu :default-active="activeIndex" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" :collapse="isCollapse">
            <div class="user-panel">
                <img @click="changeMenu('/system/personal')" class="avatar" :src=account.avatarUrl alt="">
                <div class="account">
                    <h4 class="name">{{ account.nickname }}</h4>
                    <ul class="status">
                        <li>在线</li>
                        <li @click="logout"><i class="el-icon-download" style="transform: rotate(-90deg);color: #ed5565;font-weight: 700;"></i>注销</li>
                    </ul>
                </div>
            </div>
            <el-menu-item @click="changeMenu('/system/home')" index="1">
                <i class="el-icon-s-home"></i>
                <span slot="title">首页</span>
            </el-menu-item>
            <el-submenu index="2">
                <template slot="title">
                    <i class="el-icon-office-building"></i>
                    <span slot="title">社团</span>
                </template>
                <el-menu-item @click="changeMenu('/system/myClub')" index="2-1">我的社团</el-menu-item>
                <el-menu-item @click="changeMenu('/system/massList')" index="2-2">社团一览</el-menu-item>
                <el-menu-item @click="changeMenu('/system/clubMessage')" index="2-3">社团消息</el-menu-item>
            </el-submenu>
            
            <el-submenu index="3">
                <template slot="title">
                    <i class="el-icon-menu"></i>
                    <!-- <span slot="title">功能</span> -->
                    <span slot="title">社区</span>
                </template>
                <el-menu-item @click="changeMenu('/function/aiAsk')" index="3-1">AI问答</el-menu-item>
                <!-- <el-menu-item @click="changeMenu('/function/onlineChat')" index="3-2">在线聊天</el-menu-item> -->
                <el-menu-item @click="changeMenu('/function/firthSelect')" index="3-3">省一题库</el-menu-item>
            </el-submenu>
            <!-- <el-menu-item index="4">
                <i class="el-icon-setting"></i>
                <span slot="title">导航四</span>
            </el-menu-item> -->
        </el-menu>
    </div>
</template>
<script>
import axios from 'axios'
  export default {
    data() {
      return {
        isCollapse: false,
        account: {
            nickname: 'Admin',
            avatarUrl: 'http://ganxy03.cn/img/avatar.png'
        },
        activeIndex: '1',
        fullscreenLoading: false
      }
    },
    created() {
        this.activeMenu()
    },
    methods: {
      activeMenu() {
        if(localStorage.getItem("publicPath") == '/system/home') {
            this.activeIndex = '1'
        } else if(localStorage.getItem("publicPath") == '/system/personal') {
            this.activeIndex = '0'
        } else if(localStorage.getItem("publicPath") == '/system/myClub') {
            this.activeIndex = '2-1'
        } else if(localStorage.getItem("publicPath") == '/system/massList') {
            this.activeIndex = '2-2'
        } else if(localStorage.getItem("publicPath") == '/system/clubMessage') {
            this.activeIndex = '2-3'
        } else if(localStorage.getItem("publicPath") == '/function/aiAsk') {
            this.activeIndex = '3-1'
        } else if(localStorage.getItem("publicPath") == '/function/onlineChat') {
            this.activeIndex = '3-2'
        } else if(localStorage.getItem("publicPath") == '/function/firthSelect') {
            this.activeIndex = '3-3'
        }
      },
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      changeMenu(path) {
        if(localStorage.getItem("publicPath") == path) {
            return
        }
        localStorage.setItem("publicPath", path)
        location.reload()
      },
      logout() {
        this.$confirm('此操作将退出登陆, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {

            const url = '/api/user-account/logout'
            this.fullscreenLoading = true
            axios.post(`${url}?sno=${localStorage.getItem('Sno')}`,{
                    
                },
                {
                headers: {
                    'verifyCode': '2024'
                }
            })
            .then(res => {
                if(res.data == 'Logout success') {
                    localStorage.clear()
                    setTimeout(function() {
                        this.fullscreenLoading = false
                        // location.href = '/'
                        this.$router.push('/');
                        this.$message({
                            type: 'success',
                            message: '退出成功'
                        });
                    }.bind(this), 1500)
                    
                }
            })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消'
          });          
        });
      }
    }
  }
</script>
<style>
.UserSide-Container {
    color: #fff;
    width: 200px;
    height: calc(100vh - 100px);
    background-color: #9f9f9f;
    /* background-color: blueviolet; */
    text-align: left;
}
.el-menu-vertical-demo {
    height: calc(100vh - 100px);
    background-color: #2f4050;
}
.el-menu-vertical-demo .user-panel {
    width: calc(100% - 12px);
    height: 75px;
    /* background-color: blueviolet; */
    cursor: default;
    display: flex;
    align-items: center;
    padding-left: 12px;
}
.el-menu-vertical-demo .user-panel .avatar {
    width: 50px;
    height: 50px;
    border-radius: 50%;
    cursor: pointer;
    display: flex;
    align-items: center;
}
.el-menu-vertical-demo .user-panel .account {
    margin-left: 12px;
}
.el-menu-vertical-demo .user-panel .name {
    font-size: 20px;
}
.el-menu-vertical-demo .user-panel .status {
    padding: 0;
    display: flex;
    font-size: 11px;
    line-height: 20px;
}
.el-menu-vertical-demo .user-panel .status li {
    list-style: none;
    margin: 0 6px;
    cursor: pointer;
}
.el-menu-item {
    color: #A7B1C2;
    background-color: #2f4050;
}
.el-submenu__title {
    color: #A7B1C2;
    background-color: #2f4050;
}
.el-menu-item-group__title {
    background-color: #2f4050;
}
.el-submenu__title:hover {
    color: #fff;
    background-color: #293846;
}
.el-menu-item.is-active {
    background-color: #2f4050;
}
.el-menu-item:hover {
    color: #fff;
    background-color: #293846;
}
.el-submenu.is-active .el-submenu__title {
    background-color: #2f4050;
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
}
</style>