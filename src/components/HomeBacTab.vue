<template>
    <div class="bacTab" v-loading.fullscreen.lock="fullscreenLoading">
        <div class="tabNav">
          <span class="navItem" :class="{ active: showBtn === 'guide' }" @mouseover="showLi('guide')">服务指南</span>
          <span class="navItem" :class="{ active: showBtn === 'clubs' }" @mouseover="showLi('clubs')">社团一览</span>
        </div>
        <ul class="tabList">
            <li class="tab-li" v-show="showBtn === 'guide'">
                <a @click="navigateTo(item)" class="tabItem" v-for="item in tabPlane.server" :key="item.title" :style="{ backgroundColor: item.color }">
                    <i class="tab_icon pa" :class="getIcon(item)"></i>
                    <p class="tab_title">{{ item.title }}</p>
                </a>
            </li>
            
            <li class="tab-li" v-show="showBtn === 'clubs'">
                <a class="tabItem" v-for="item in paginatedItems" :key="item.title" :style="{ backgroundColor: item.color }">
                    <i class="tab_icon pa" :class="getIcon(item)"></i>
                    <p class="tab_title">{{ item.title }}</p>
                </a>
                <div>
                    <span class="circle c3" :class="{ active: currentPage === 1 }" @mousemove="changePage(1)"></span>
                    <span class="circle c2" :class="{ active: currentPage === 2 }" @mousemove="changePage(2)"></span>
                    <span class="circle c1" :class="{ active: currentPage === 3 }" @mousemove="changePage(3)"></span>
                </div>
            </li>
        </ul>

        
    </div>
</template>
<script>
// import { mapState, mapMutations } from 'vuex';
import '@/assets/css/homeBacTab.css'
export default {
    data() {
        return {
            showBtn: 'guide',
            currentPage: 1,
            itemsPerPage: 8,
            tabPlane: {
                server: [
                    {
                        icon: 'sheyuan',
                        title: '社员账户',
                        color: '#5EB3BF',
                        url: '/user'
                    },
                    {
                        icon: 'wenda',
                        title: 'Ai问答系统',
                        color: '#635CBD',
                        url: '/ai'
                    },
                    {
                        icon: 'guanli',
                        title: '社团管理系统',
                        color: '#3C98B3',
                        url: '/manager'
                    },
                    {
                        icon: 'hotai',
                        title: '后台管理系统',
                        color: '#386FB2',
                        url: '/houtai'
                    }
                ],
                clubs: [
                    {
                        icon: 'hulianwang',
                        title: '互联网社',
                        color: '#2AB2D9',
                        url: '/mass'
                    },
                    {
                        icon: 'huasheng',
                        title: '花绳俱乐部',
                        color: '#3790AA',
                        url: '/mass'
                    },
                    {
                        icon: 'aiyue',
                        title: '爱乐社',
                        color: '#56AAB6',
                        url: '/mass'
                    },
                    {
                        icon: 'mengxiang',
                        title: '新梦想',
                        color: '#3790AA',
                        url: '/mass'
                    },
                    {
                        icon: 'shuhua',
                        title: '书画社',
                        color: '#386FB2',
                        url: '/mass'
                    },
                    {
                        icon: 'xinli',
                        title: '心理社',
                        color: '#4E978E',
                        url: '/mass'
                    },
                    {
                        icon: 'xicha',
                        title: '习茶社',
                        color: '#635CBD',
                        url: '/mass'
                    },
                    {
                        icon: 'wushu',
                        title: '武术社',
                        color: '#3790AA',
                        url: '/mass'
                    },
                    {
                        icon: 'taiqiu',
                        title: '台球社',
                        color: '#2AB2D9',
                        url: '/mass'
                    },
                    {
                        icon: 'yuyan',
                        title: '语言社',
                        color: '#635CBD',
                        url: '/mass'
                    },
                    {
                        icon: 'hanfu',
                        title: '汉服社',
                        color: '#3790AA',
                        url: '/mass'
                    },
                    {
                        icon: 'kochai',
                        title: '口才社',
                        color: '#2AB2D9',
                        url: '/mass'
                    },
                    {
                        icon: 'qishe',
                        title: '棋社',
                        color: '#2AB2D9',
                        url: '/mass'
                    },
                    {
                        icon: 'sheying',
                        title: '摄影社',
                        color: '#5EB3BF',
                        url: '/mass'
                    },
                    {
                        icon: 'dushu',
                        title: '读书社',
                        color: '#2AB2D9',
                        url: '/mass'
                    },
                    {
                        icon: 'wudao',
                        title: '舞蹈社',
                        color: '#635CBD',
                        url: '/mass'
                    },
                    {
                        icon: 'dongman',
                        title: '动漫社',
                        color: '#56AAB6',
                        url: '/mass'
                    },
                    {
                        icon: 'yumaoqiu',
                        title: '羽毛球社',
                        color: '#56AAB6',
                        url: '/mass'
                    },
                    {
                        icon: 'pingpang',
                        title: '乒乓球社',
                        color: '#5EB3BF',
                        url: '/mass'
                    },
                    {
                        icon: 'wenxue',
                        title: '文学社',
                        color: '#635CBD',
                        url: '/mass'
                    },
                ]
            },
            ba: '123',
            fullscreenLoading: false
        }
    },
    created() {
        // console.log(this.$store.state.isNeedLogin)
    },
    computed: {
        paginatedItems() {
            const start = (this.currentPage - 1) * this.itemsPerPage;
            const end = start + this.itemsPerPage;
            return this.tabPlane.clubs.slice(start, end);
        },
        // ...mapState(['isNeedLogin'])
    },
    methods: {
        // ...mapMutations(['SET_ISQQAVATAR']),
        showLi(button) {
            this.showBtn = button;
        },
        changePage(page) {
            this.currentPage = page;
        },
        getIcon(item) {
            return item.icon;
        },
        navigateTo(item) {
            console.log(item)
            // this.verifyLogin()
            if(item.url == '/user') {
                localStorage.setItem("needLogin", 'true');
                location.reload()
                if(localStorage.getItem('Sno') != null) {
                    this.$router.push('/user/' + localStorage.getItem('Sno')).catch(err => {
                        console.log(err)
                        localStorage.removeItem("needLogin")
                    })
                    return
                }
            } else if(item.url == '/manager') {
                this.fullscreenLoading = true
                setTimeout(function() {
                    this.fullscreenLoading = false
                    if(localStorage.getItem('isManager') != null) {
                        // this.$router.push('/manager/' + localStorage.getItem('Mass')).catch(err => {
                        this.$router.push('/manager/' + '互联网社').catch(err => {
                            console.log(err)
                        })
                        return
                    }
                    this.$router.push('/manager-login')
                }.bind(this), 1000)
            } else if(item.url == '/ai') {
                this.fullscreenLoading = true
                setTimeout(function() {
                    this.fullscreenLoading = false
                    if(localStorage.getItem('Sno') != null) {
                        this.$router.push('/user/' + localStorage.getItem('Sno')).catch(err => {
                            console.log(err)
                        })
                        localStorage.setItem('publicPath', '/function/aiAsk')
                        return
                    }
                    // this.$router.push('/manager-login')
                    this.$message({
                        message: '请先登录',
                        type: 'warning'
                    })
                }.bind(this), 1000)
            } else if(item.url == '/houtai') {
                this.fullscreenLoading = true
                setTimeout(function() {
                    this.fullscreenLoading = false
                    if(localStorage.getItem('isHoutai') != null) {
                        this.$router.push('/houtai/test').catch(err => {
                            console.log(err)
                        })
                        return
                    }
                    this.$router.push('/houtai-login')
                }.bind(this), 1000)
            }
            
            // console.log(localStorage.getItem("needLogin"))
            // console.log(this.$store.state.isNeedLogin)
            
        },
        verifyLogin() {
            if(localStorage.getItem('Sno') != null) {
                this.$router.push('/user/' + localStorage.getItem('Sno')).catch(err => {
                        
                    console.log(err)
                    localStorage.removeItem("needLogin")
                    return
                })
            }
        },
    }
}
</script>