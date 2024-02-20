<template>
    <div style="height: calc(100vh - 100px);width: 100%;position: relative;">
        <CommonHead />
        <div class="Article_Container">
            <div class="Article_ContainerBac"></div>
            <div style="display: flex">
                <div class="Article_Container_left">
                    <div style="float: left;margin-top: 12px;"><i class="el-icon-location"></i>当前位置:</div>
                    <div class="HTML_content" v-html="Article.content"></div>
                </div>
                <div class="Article_Container_right">
                    <div class="Article_Container_rightSideBar">
                        <div class="Article-nav_list">
                            <ul class="Article-nav_content">
                                <li @click="activate(item.name)" :class="{'active': changeNav(item.name)}" class="Article-nav_contentItem" v-for="item in NavItem" :key="item.name">{{item.name}}</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <CommonFoot class="site-foot" />
    </div>
</template>

<script>
import CommonHead from '@/components/CommonHead.vue'
import CommonFoot from '@/components/CommonFoot.vue'
export default {
    components: {
        CommonHead,
        CommonFoot
    },
    data() {
        return {
            Article: {
                content: '',
                title: ''
            },
            NavItem: [
                {
                    name: '部门简介'
                },
                {
                    name: '部门设置'
                },
                {
                    name: '联系我们'
                }
            ],
            isactiveNav: '部门简介'
        }
    },
    created() {
        this.getArticle(this.$route.params.name)
    },
    methods: {
        getArticle(name) {
            this.isactiveNav = name
            const url = process.env.VUE_APP_BASE_URI + '/houtai-siteArticle/siteArticle-getArticle'
            const belong = '部门概况'
            const title = name
            fetch(`${url}?belong=${belong}&title=${title}`, {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            })
            .then(res => res.json())
            .then(res => {
                this.Article.content = res.content
                this.Article.title = res.title
            })
        },
        changeNav(name) {
            return this.isactiveNav === name
        },
        activate(name) {
            this.isactiveNav = name
            this.$router.push("/bmgk/"+name)
            location.reload()
        }
    }
}

</script>

<style>
.Article_Container {
    width: 100%;
    min-height: calc(100vh - 104px);
    /* background-color: blueviolet; */
}
.Article_ContainerBac {
    width: 100%;
    height: 380px;
    background: url(../../assets/img/banner/inset_bar.jpg) 50% no-repeat;
    background-size: cover;
}
.Article_Container_left {
    justify-content: flex-start;
    width: 65%;
    margin: 0 50px;
    min-height: 50vh;
}
.Article_Container_right {
    width: 25%;
    min-height: calc(50vh - 120px);
    margin: 60px 0;
    /* background-color: aquamarine; */
}
.Article_Container_rightSideBar {
    position: relative; /* 添加相对定位 */
    height: 220px;
    width: 100%;
    background: url(../../assets/img/banner/sidebar.jpg);
    background-size: cover;
}
.Article-nav_list {
    overflow: hidden;
    /* padding: 50px 20px 66px; */
    z-index: 6; 
    margin-top: -30px;
    min-height: 400px;
    position: relative;
}
.Article-nav_list::before {
    content: '';
    position: absolute;
    transform: skewY(-5deg);
    bottom: 0;
    top: 200px;
    right: 0;
    width: 100%;
    height: 500px;
    background: url(../../assets/img/banner/line.jpg) 50% repeat-y;
    background-size: contain;
    z-index: 0;
}
.Article-nav_content {
    position: absolute;
    top: 222px;
    left: 0;
    right: 0;
    bottom: 0;
    color: #fff;
    text-align: left;
    margin: 0 16px;
}
.Article-nav_contentItem {
    font-size: 22px;
    height: 30px;
    padding: 12px 12px;
    list-style: none;
    cursor: pointer;
    border-top: 1px solid rgba(255,255,255,.1);
}
.Article-nav_contentItem.active {
    color: #FCC740;
}
.Article-nav_contentItem:hover {
    color: #FCC740;
}
.Article-nav_contentItem:nth-child(1) {
    border-top: none;
}
.HTML_content {
    margin: 0;
    padding: 0;
}
.HTML_content p {
    text-align: left;
}
.HTML_content img {
    height: 220px;
}
.site-foot {
    position: absolute;
}
@media screen and (max-width: 768px) {
    .Article_Container_right {
        display: none;
    }
    .Article_Container_left {
        margin: 60px 16px;
        width: 100%;
        min-height: 50px;
    }
    .HTML_content img {
        height: 200px;
    }
}
</style>