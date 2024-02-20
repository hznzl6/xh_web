<template>
    <div>
        <el-container>
            <el-header>
                社团
                <i @click="navMenu_Open" class="el-icon-s-unfold navMenu_Icon"></i>
                <el-dropdown trigger="click" style="float: right;">
                    <a class="Mass-Manager_avatar"></a>
                    <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item>个人中心</el-dropdown-item>
                            <el-dropdown-item>修改密码</el-dropdown-item>
                            <el-dropdown-item>退出登陆</el-dropdown-item>
                        </el-dropdown-menu>
                </el-dropdown>
                
                <!-- 移动端菜单 -->
                <el-drawer
                    :visible.sync="navMenu"
                    direction="ltr"
                    :before-close="navMenu_Close"
                    size="40%"
                    style="text-align: left;">
                    <el-menu
                        default-active="2-1"
                        class="el-menu-vertical_move"
                        @open="handleOpen"
                        @close="handleClose"
                        background-color="#545c64"
                        text-color="#fff"
                        active-text-color="#ffd04b">
                        <div class="el-menu-vertical_moveTitle">
                            社团管理
                        </div>
                        <el-menu-item index="1">
                            <i class="el-icon-user"></i>
                            <span slot="title">主页</span>
                        </el-menu-item>
                        <el-submenu index="2">
                            <template slot="title">
                                <i class="el-icon-menu"></i>
                                <span>社团管理</span>
                            </template>
                            <el-menu-item-group>
                                <el-menu-item @click="activeIndex = '2-1'" index="2-1">社团主页</el-menu-item>
                                <el-menu-item @click="activeIndex = '2-2'" index="2-2">部门管理</el-menu-item>
                                <el-menu-item @click="activeIndex = '2-3'" index="2-3">社团成员</el-menu-item>
                                <el-menu-item @click="activeIndex = '2-4'" index="2-4">社团活动</el-menu-item>
                                <el-menu-item @click="activeIndex = '2-5'" index="2-5">文章管理</el-menu-item>
                                <el-menu-item @click="activeIndex = '2-6'" index="2-6">消息广播</el-menu-item>
                                <el-menu-item @click="activeIndex = '2-7'" index="2-7">学时申报</el-menu-item>
                            </el-menu-item-group>
                        </el-submenu>
                        <el-menu-item @click="activeIndex = '3'" index="3">
                            <i class="el-icon-document"></i>
                            <span slot="title">介绍文档</span>
                        </el-menu-item>
                    </el-menu>
                </el-drawer>
            </el-header>
            <el-container>
                <el-aside width="200px">
                    <el-menu
                        default-active="2-1"
                        class="el-menu-vertical"
                        @open="handleOpen"
                        @close="handleClose"
                        background-color="#545c64"
                        text-color="#fff"
                        active-text-color="#ffd04b"
                        style="text-align: left;">
                        <el-menu-item index="1">
                            <i class="el-icon-user"></i>
                            <span slot="title">首页</span>
                        </el-menu-item>
                        <el-submenu index="2">
                            <template slot="title">
                                <i class="el-icon-menu"></i>
                                <span>社团管理</span>
                            </template>
                            <el-menu-item-group>
                                <el-menu-item @click="activeIndex = '2-1'" index="2-1">社团主页</el-menu-item>
                                <el-menu-item @click="activeIndex = '2-2'" index="2-2">部门管理</el-menu-item>
                                <el-menu-item @click="activeIndex = '2-3'" index="2-3">社团成员</el-menu-item>
                                <el-menu-item @click="activeIndex = '2-4'" index="2-4">社团活动</el-menu-item>
                                <el-menu-item @click="activeIndex = '2-5'" index="2-5">文章管理</el-menu-item>
                                <el-menu-item @click="activeIndex = '2-6'" index="2-6">消息广播</el-menu-item>
                                <el-menu-item @click="activeIndex = '2-7'" index="2-7">学时申报</el-menu-item>
                            </el-menu-item-group>
                        </el-submenu>
                        <el-menu-item @click="activeIndex = '3'" index="3">
                            <i class="el-icon-document"></i>
                            <span slot="title">介绍文档</span>
                        </el-menu-item>
                    </el-menu>
                </el-aside>
                <el-container>
                    <el-main>
                        <MassHome v-show="activeIndex == '2-1'"/>
                        <MassDepartment v-show="activeIndex == '2-2'"/>
                        <MassMember v-show="activeIndex == '2-3'"/>
                        <MassActivity v-show="activeIndex == '2-4'"/>
                        <MassArticle v-show="activeIndex == '2-5'"/>
                        <MassBroadcast v-show="activeIndex == '2-6'"/>
                        <MassCreditTime v-show="activeIndex == '2-7'"/>
                        <DocumentView  v-show="activeIndex == '3'"/>
                        <el-footer>&copy;Ganxy|2024</el-footer>
                    </el-main>
                    
                </el-container>
            </el-container>
        </el-container>
    </div>
</template>
<script>
import MassHome from '@/views/shetuan/Manager/MassHome.vue'
import MassDepartment from '@/views/shetuan/Manager/MassDepartment.vue'
import MassMember from '@/views/shetuan/Manager/MassMember.vue'
import MassActivity from '@/views/shetuan/Manager/MassActivity.vue'
import MassArticle from '@/views/shetuan/Manager/MassArticle.vue'
import MassBroadcast from '@/views/shetuan/Manager/MassBroadcast.vue'
import MassCreditTime from '@/views/shetuan/Manager/MassCreditTime.vue'
import DocumentView from '@/views/shetuan/Manager/DocumentView.vue'

  export default {
    components: {
        MassHome,
        MassDepartment,
        MassMember,
        MassActivity,
        MassArticle,
        MassBroadcast,
        MassCreditTime,
        DocumentView
    },
    data() {
        return {
            navMenu: false,
            activeIndex: '2-1'
        }
    },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      navMenu_Open() {
        this.navMenu = true
      },
      navMenu_Close(done) {
        done()
      }
    }
  }
</script>
<style>
.el-header, .el-footer {
    background-color: #b3c0d1;
    color: #333;
    text-align: center;
    line-height: 60px;
    height: 60px;
}
.navMenu_Icon {
    float: left;
    font-size: 32px;
    line-height: 60px;
    display: none;
}
.Mass-Manager_avatar {
    display: inline-block;
    width: 40px;
    height: 40px;
    margin-top: 10px;
    border-radius: 20px;
    background: url("../../../assets/img/user/no_face.png") 50% no-repeat;
    background-size: cover;
    cursor: pointer;
}
.el-main {
    min-height: calc(100vh - 60px);
    background: url("http://ganxy03.cn/img/background-image.jpg");
    background-size: cover;
    padding: 0;
}
.el-aside {
    background-color: #392f3a;
    color: #333;
    text-align: center;
    line-height: 200px;
    min-height: calc(100vh - 60px);
}
.el-menu-vertical {
    min-height: calc(100vh - 60px);
    overflow-x: hidden;
    overflow-y: hidden;
}
.el-menu-vertical_move {
    min-height: 100vh;
    overflow-x: hidden;
    overflow-y: hidden;
}
.el-menu-vertical_move .el-drawer .el-drawer__header, #el-drawer__title {
    display: none;
}
.el-menu-vertical_moveTitle {
    height: 40px;
    width: 100%;
    color: #D3DCE6;
    text-align: center;
}
.el-footer {
    width: calc(100% - 200px);
    position: absolute;
    bottom: 0;
    border-radius: 5px;
    background-color: rgb(252, 236, 220, 0.3);
}
@media screen and (max-width: 480px) {
    .navMenu_Icon {
        display: block;
    }
    .el-aside {
        display: none;
    }
    .el-footer {
        width: 100%;
        background-color: rgb(252, 236, 220, 0);
    }
}
</style>