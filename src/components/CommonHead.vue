<template>
    <div class="headNav" :class="{ 'initial-bg': isInitialBg }">
        <a @click="navToHome" class="slogan"></a>
        <a class="search"></a>
        <nav class="navBar" >
          <li @click="navToMenu(item)" class="navItem" v-for="item in navItem" :key="item.title">{{ item.title }}</li>
        </nav>
        <i class="el-icon-s-fold"></i>
    </div>
</template>
<script>
import '@/assets/css/headNav.css'
export default {
    data() {
        return {
            isInitialBg: true,
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
        }
    },
    methods: {
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