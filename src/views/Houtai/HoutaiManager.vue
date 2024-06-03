<template>
    <div class="houtai-manager">
        <el-container class="bacColor">
            <div class="headNav" :class="{ 'initial-bg': isInitialBg }">
                <a @click="navToHome" class="slogan"></a>
                <a class="search"></a>
                <nav class="navBar" >
                <li @click="navToMenu(item)" class="navItem" v-for="item in navItem" :key="item.title">{{ item.title }}</li>
                </nav>
            </div>
            <div class="bacColor" style="height: 60px;">
                
            </div>
        </el-container>

        <el-container>
            <el-aside width="200px">
                <el-menu
                    :default-active="activeIndex"
                    @open="handleOpen"
                    @close="handleClose"
                    background-color="#545c64"
                    text-color="#fff"
                    active-text-color="#ffd04b"
                    style="text-align: left;height: calc(100vh - 60px);">
                    <el-menu-item @click="linkTo('1')" index="1">
                        <i class="el-icon-user"></i>
                        <span slot="title" >首页</span>
                    </el-menu-item>
                    <el-submenu index="2">
                        <template slot="title">
                            <i class="el-icon-menu"></i>
                            <span>后台管理</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item @click="linkTo('2-0')" index="2-0">班级管理</el-menu-item>
                            <el-menu-item @click="linkTo('2-1')" index="2-1">社团管理</el-menu-item>
                            <el-menu-item @click="linkTo('2-2')" index="2-2">成员管理</el-menu-item>
                            <el-menu-item @click="linkTo('2-3')" index="2-3">活动管理</el-menu-item>
                            <el-menu-item @click="linkTo('2-4')" index="2-4">文章管理</el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>
                    <el-menu-item @click="linkTo('3')" index="3">
                        <i class="el-icon-document-copy"></i>
                        <span slot="title">站点页面</span>
                    </el-menu-item>
                    <el-menu-item index="4">
                        <i class="el-icon-document"></i>
                        <span slot="title">介绍文档</span>
                    </el-menu-item>
                </el-menu>
            </el-aside>
            <el-container>
                <el-main>
                    <PersonalPage v-show="activeIndex == '1'"/>
                    <BelongPage v-show="activeIndex == '2-0'" />
                    <MassPage  v-show = "activeIndex == '2-1'"/>
                    <MemberPage  v-show = "activeIndex == '2-2'"/>
                    <ActivityPage  v-show = "activeIndex == '2-3'"/>
                    <ArticlePage  v-show = "activeIndex == '2-4'"/>
                    <WebsitePage  v-show = "activeIndex == '3'"/>
                    <el-footer>
                        <p style="line-height: 60px;">&copy;Ganxy|2024</p>
                    </el-footer>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
import '@/assets/css/manHead.css'

import PersonalPage from './SiglePage/PersonalPage.vue'
import BelongPage from './SiglePage/BelongPage.vue'
import MassPage from './SiglePage/MassPage.vue'
import MemberPage from './SiglePage/MemberPage.vue'
import ActivityPage from './SiglePage/ActivityPage.vue'
import ArticlePage from './SiglePage/ArticlePage.vue'
import WebsitePage from './SiglePage/WebsitePage.vue'
export default {
    components: {
        PersonalPage,
        BelongPage,
        MassPage,
        MemberPage,
        ActivityPage,
        ArticlePage,
        WebsitePage
    },
    data() {
        return {
            activeIndex: '2-0',
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
        this.getCurrentIndex()
        console.log(this.activeIndex)
    },
    methods: {
        handleOpen(key, keyPath) {
            console.log(key, keyPath);
        },
        handleClose(key, keyPath) {
            console.log(key, keyPath);
        },
        getCurrentIndex() {
            const path = localStorage.getItem('HoutaiPath')
            if(path != null ) {
                if(path == '/houtai-personal') {
                    this.activeIndex = '1'
                    console.log(this.activeIndex)
                } else if(path == '/houtai-belong') {
                    this.activeIndex = '2-0'
                } else if(path == '/houtai-mass') {
                    this.activeIndex = '2-1'
                } else if(path == '/houtai-member') {
                    this.activeIndex = '2-2'
                } else if(path == '/houtai-activity') {
                    this.activeIndex = '2-3'
                } else if(path == '/houtai-article') {
                    this.activeIndex = '2-4'
                } else if(path == '/houtai-website') {
                    this.activeIndex = '3'
                } else if(path == '/houtai-doc') {
                    this.activeIndex = '4'
                }
            } else {
                this.activeIndex = "2-1"
                localStorage.setItem('houtaiPath', '/houtai-belong')
            }
            
        },
        linkTo(index) {
            this.activeIndex = index
            if(index == '1') {
                localStorage.setItem('HoutaiPath', '/houtai-personal')
            } else if(index == '2-0') {
                localStorage.setItem('HoutaiPath', '/houtai-belong')
            } else if(index == '2-1') {
                localStorage.setItem('HoutaiPath', '/houtai-mass')
            } else if(index == '2-2') {
                localStorage.setItem('HoutaiPath', '/houtai-member')
            } else if(index == '2-3') {
                localStorage.setItem('HoutaiPath', '/houtai-activity')
            } else if(index == '2-4') {
                localStorage.setItem('HoutaiPath', '/houtai-article')
            } else if(index == '3') {
                localStorage.setItem('HoutaiPath', '/houtai-website')
            } else if(index == '4') {
                localStorage.setItem('HoutaiPath', '/houtai-doc')
            }
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
        navToMenu(item) {
            this.$router.push(item.link).catch(err => {
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
.houtai-manager .bacColor {
    /* background-color: #3c8dbc; */
    background: linear-gradient(to right, #2f4050, #9e11d5); /* 渐变背景色 */
}
.houtai-manager .table {
    height: calc(100vh - 120px - 40px);
}
</style>