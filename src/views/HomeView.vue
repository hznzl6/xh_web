<template>
    <div class="home">
        <div class="cover_bg">
            <HeadNav class="nav" />
            <HomeBacTab class="bac_tab" />
            <a class="bar_down" @click="scrollDown"></a>
        </div>
        <div class="main_content">
            <el-row :gutter="20" class=" pc">
                <el-col :span="16">
                    <ContentLeft class="left"/>
                </el-col>
                <el-col :span="8">
                    <NoticeList class="notice"/>
                </el-col>
            </el-row>
            <div class="carouselBar">
                <CarouselBar />
            </div>
            <el-row class="move">
                <el-col :span="24">
                    <ContentLeft class="left"/>
                </el-col>
                <el-col :span="24">
                    <NoticeList class="notice"/>
                </el-col>
            </el-row>
            <el-dialog
            custom-class="loginFrame"
            :visible.sync="loginFrame"
            top="25vh"
            @close="cancelDialog">
                <LoginView />
            </el-dialog>
            <HomeFoot />
        </div>
    </div>
</template>
<script>
import '@/assets/css/homeView.css'
import HeadNav from '@/components/CommonHead.vue'
import HomeBacTab from '@/components/HomeBacTab.vue'
import ContentLeft from './Home/ContentLeft.vue'
import NoticeList from './Home/NoticeList.vue'
import HomeFoot from './Home/HomeFoot.vue'
import CarouselBar from '../components/HomeCarousel.vue'

import LoginView from '../views/shetuan/Login/LoginView.vue'
export default {
    components: {
        HeadNav,
        HomeBacTab,
        ContentLeft,
        NoticeList,
        CarouselBar,
        HomeFoot,

        LoginView
    },
    data() {
        return {
            loginFrame: false
        }
    },
    created() {
        // console.log(this.$store.state.isNeedLogin)
        // console.log(process.env.VUE_APP_BASE_URI)
    },
    mounted() {
        if(localStorage.getItem("needLogin")) {
            this.loginFrame = this.$store.state.isNeedLogin
        }
        // console.log("click:" + this.loginFrame)
    },
    methods: {
        scrollDown() {
            const vh = window.innerHeight;
            const scrollDistance = vh - 100;
            window.scrollTo({
                top: scrollDistance,
                behavior: 'smooth',
            });
        },
        cancelDialog() {
            localStorage.removeItem('needLogin')
        }
    }
}
</script>