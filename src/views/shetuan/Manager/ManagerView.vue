<template>
    <div>
        <el-container class="bacColor">
            <div class="headNav" :class="{ 'initial-bg': isInitialBg }">
                <a @click="navToHome" class="slogan"></a>
                <a class="search"></a>
                <nav class="navBar" >
                <li @click="navToMenu(item)" class="navItem" v-for="item in navItem" :key="item.title">{{ item.title }}</li>
                </nav>
            </div>
            <div class="headnav">
                <i @click="navMenu_Open" class="el-icon-s-unfold navMenu_Icon" style="color: #fff;"></i> 
                <el-drawer
                    :append-to-body="true"
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
                        <el-menu-item @click="linkTo('1')" index="1">
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
                                <!-- <el-menu-item @click="activeIndex = '2-7'" index="2-7">学时申报</el-menu-item> -->
                            </el-menu-item-group>
                        </el-submenu>
                        <el-menu-item @click="activeIndex = '3'" index="3">
                            <i class="el-icon-document"></i>
                            <span slot="title">介绍文档</span>
                        </el-menu-item>
                    </el-menu>
                </el-drawer>
            </div>
           
        </el-container>

        <el-container>
            <el-aside width="200px">
                <el-menu
                    :default-active="activeIndex"
                    class="el-menu-vertical"
                    @open="handleOpen"
                    @close="handleClose"
                    background-color="#545c64"
                    text-color="#fff"
                    active-text-color="#ffd04b"
                    style="text-align: left;">
                    <el-menu-item @click="linkTo('1')" index="1">
                        <i class="el-icon-user"></i>
                        <span slot="title" >首页</span>
                    </el-menu-item>
                    <el-submenu index="2">
                        <template slot="title">
                            <i class="el-icon-menu"></i>
                            <span>社团管理</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item @click="linkTo('2-1')" index="2-1">社团主页</el-menu-item>
                            <el-menu-item @click="linkTo('2-2')" index="2-2">部门管理</el-menu-item>
                            <el-menu-item @click="linkTo('2-3')" index="2-3">社团成员</el-menu-item>
                            <el-menu-item @click="linkTo('2-4')" index="2-4">社团活动</el-menu-item>
                            <el-menu-item @click="linkTo('2-5')" index="2-5">文章管理</el-menu-item>
                            <el-menu-item @click="linkTo('2-6')" index="2-6">消息广播</el-menu-item>
                            <!-- <el-menu-item @click="linkTo('2-7')" index="2-7">学时申报</el-menu-item> -->
                        </el-menu-item-group>
                    </el-submenu>
                    <el-menu-item @click="linkTo('3')" index="3">
                        <i class="el-icon-document"></i>
                        <span slot="title">介绍文档</span>
                    </el-menu-item>
                </el-menu>
            </el-aside>
            <el-container>
                <el-main>
                    <PersonalPage v-show=" activeIndex == '1'" />
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
    </div>
</template>
<script>
import '@/assets/css/manHead.css'
import PersonalPage from '@/views/shetuan/Manager/PersonalPage.vue'
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
        PersonalPage,
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
            activeIndex: '',
            navItem: [
                {
                    title: '部门概况',
                    link: '/bmgk/部门简介'
                },
                {
                    title: '服务指南',
                    link: '/service'
                },
                {
                    title: '规章制度',
                    link: '/rule'
                },
                {
                    title: '信息公示',
                    link: '/anno'
                },
                {
                    title: '下载专区',
                    link: '/download'
                },
                {
                    title: '联系我们',
                    link: '/connet'
                }
            ],
            isInitialBg: false
        }
    },
    created() {
        this.getCurrentPage()
        // console.log(this.activeIndex)
        localStorage.setItem('mass', this.$route.params.mass)
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
        },
        getCurrentPage() {
            if(localStorage.getItem('ManagerPath') != null) {
                const path = localStorage.getItem('ManagerPath')
                // console.log(path)
                if(path == '/mass/home') {
                    this.activeIndex = '2-1'
                } else if(path == '/mass/depart') {
                    this.activeIndex = '2-2'
                } else if(path == '/mass/memeber') {
                    this.activeIndex = '2-3'
                } else if(path == '/mass/activity') {
                    this.activeIndex = '2-4'
                } else if(path == '/mass/article') {
                    this.activeIndex = '2-5'
                } else if(path == '/mass/boradcast') {
                    this.activeIndex = '2-6'
                } else if(path == '/mass/credit') {
                    this.activeIndex = '2-7'
                } else if(path == '/mass/myself') {
                    this.activeIndex = '1'
                }else if(path == '/mass/introduce') {
                    this.activeIndex = '3'
                }
            } else {
                this.activeIndex = '2-1'
                localStorage.setItem('ManagerPath', '/mass/home')
            }
            
        },
        linkTo(path) {
            this.activeIndex = path

            // 这是个bug 先这么改
            localStorage.removeItem('selectValue')
            location.reload()

            
            if(path == '2-1') {
                localStorage.setItem('ManagerPath', '/mass/home')
            } else if(path == '2-2') {
                localStorage.setItem('ManagerPath', '/mass/depart')
            } else if(path == '2-3') {
                localStorage.setItem('ManagerPath', '/mass/memeber')
            } else if(path == '2-4') {
                localStorage.setItem('ManagerPath', '/mass/activity')
            } else if(path == '2-5') {
                localStorage.setItem('ManagerPath', '/mass/article')
            } else if(path == '2-6') {
                localStorage.setItem('ManagerPath', '/mass/boradcast')
            } else if(path == '2-7') {
                localStorage.setItem('ManagerPath', '/mass/credit')
            }
            else if(path == '1') {
                localStorage.setItem('ManagerPath', '/mass/myself')
            }
            else if(path == '3') {
                localStorage.setItem('ManagerPath', '/mass/introduce')
            }

            // location.reload()
        },
        navToMenu(item) {
            this.$router.push(item.link).catch(err => {
                this.$message({
                    message: '当前页面就是哦',
                    type: 'warning'
                })
                console.log(err)
            })
        },
        navToHome() {
            this.$router.push('/').catch(err => {
                this.$message({
                    message: '当前页面就是哦',
                    type: 'warning'
                })
                console.log(err)
            })
        },
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
    /* background: url("http://ganxy03.cn/img/background-image.jpg");
    background-size: cover; */
    padding: 0;
    background-color: #D3DCE6;
    /* background-color: #9e11d5; */
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
.bacColor {
    /* background-color: #3c8dbc; */
    background: linear-gradient(to right, #2f4050, #9e11d5); /* 渐变背景色 */
}
.headnav{
    display: none;
    width: 100%;
    height: 60px;
    z-index: 99;
}






/* 表格的统一样式 */
.table {
    width: 100%;
    height: calc(100vh - 120px - 48px - 33.33px);
    /* overflow-y: auto; */
}
.el-table__body-wrapper {
    height: calc(100vh - 120px - 48px - 33.33px - 87px - 24px);
    overflow-y: auto;
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
    .headNav{
        display: none;
    }
    .headnav{
        display: block;
    }
}
</style>