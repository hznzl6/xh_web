<template>
    <div class="ClubMessage-Container">
        <el-row>
            <el-col class="leftContent" :span="16">
                <ul v-if="!isShow" class="list">
                    <li v-for="item in ArticleList" :key="item.id" class="listItem">
                        <h3 class="title">{{ item.title }}</h3>
                        <div class="author">
                            <img class="avatar" src="http://ganxy03.cn/img/avatar.png" alt="">
                            <h4 class="name">{{ item.sno }}</h4>
                            <span class="time">{{ item.post_time }}</span>
                        </div>
                        <div class="articleBox">
                            <div @click="showArticle(item)" class="cover"></div>
                            
                            <div style="width: 60%;overflow: hidden;margin-left: 5%;text-align: left;" v-html="item.content">
                                
                            </div>
                        </div>
                    </li>
                </ul>

                <div v-else>
                    <div style="display: flex;justify-content: flex-start;">
                        <el-button @click="isShow = false" size="mini">返回</el-button>
                    </div>
                    <ArticleShow />
                </div>
            </el-col>
            <el-col class="rightBar" :span="8">
                <el-card class="itemBar_stastic">
                    <el-row>
                        <el-col :span="12">
                            <h4 class="title">浏览量</h4>
                            <p class="num">159</p>
                        </el-col>
                        <el-col :span="12">
                            <h4 class="title">讨论量</h4>
                            <p class="num">452</p>
                        </el-col>
                    </el-row>
                </el-card>
                <el-card class="itemBar_topic">
                    <h3 class="title">话题</h3>
                </el-card>
            </el-col>
        </el-row>

        
    </div>
</template>
<script>
import axios from 'axios'
import ArticleShow from './module/ArticleShow.vue'
export default {
    components: {
        ArticleShow
    },
    data() {
        return {
            ArticleList: [],
            isShow: false
        }
    },
    created() {
        this.getAll()
    },
    methods: {
        getAll() {
            const url = '/api/manager-article/getArticles'
            axios.post(`${url}?mass=互联网社`, {}, {
                headers: {
                    'verifyCode': '2024'
                }
            }).then(res => {
                this.ArticleList = res.data
                console.log(res.data)
            })
        },
        showArticle(row) {
            localStorage.setItem('ArticleMass', row.mass)
            localStorage.setItem('ArticleTitle', row.title)
            this.isShow = true
        }
    }
}
</script>
<style>
body {
    background-color: #f4f6f9;
}
.ClubMessage-Container {
    height: calc(100vh - 100px - 24px - 52px);
    width: 70%;
    margin: 12px auto;
    /* background-color: aqua; */
    overflow-y: auto;
}
.ClubMessage-Container .leftContent {
    height: calc(100vh - 100px - 24px - 52px);
    background-color: #fff;
    overflow-y: auto;
}
.ClubMessage-Container .leftContent .list {
    height: calc(100vh - 100px - 24px - 52px - 24px);
    width: calc(100% - 24px);
    padding: 0;
    margin: 12px;
    list-style: none;
}
.ClubMessage-Container .leftContent .list .listItem {
    min-height: 120px;
    width: 100%;
    /* background-color: #09408E; */
    margin-bottom: 12px;
}
.ClubMessage-Container .leftContent .list .listItem .title {
    text-align: left;
}
.ClubMessage-Container .leftContent .list .listItem .author {
    margin-top: 3px;
    display: flex;
    justify-content: flex-start;
    line-height: 24px;
}
.ClubMessage-Container .leftContent .list .listItem .author .avatar {
    width: 24px;
    height: 24px;
}
.ClubMessage-Container .leftContent .list .listItem .author .name {
    margin-left: 6px;
}
.ClubMessage-Container .leftContent .list .listItem .author .time {
    color: #8491A5;
    font-size: 13px;
    margin-left: 12px;
    line-height: 24px;
}
.ClubMessage-Container .leftContent .list .listItem .articleBox {
    margin-top: 3px;
    height: 150px;
    background-color: #95b1da;
    display: flex;
}

.ClubMessage-Container .leftContent .list .listItem .articleBox .cover {
    width: 30%;
    height: 150px;
    background: url(http://ganxy03.cn/img/background-image.jpg) 50% no-repeat;
    background-size: cover;
}
.ClubMessage-Container .rightBar {
    height: calc(100vh - 100px - 24px - 52px);
    /* background-color: aquamarine; */
}
.ClubMessage-Container .rightBar .itemBar_stastic {
    margin: 0 12px;
    height: 120px;
}
.ClubMessage-Container .rightBar .itemBar_stastic .title {
    color: #09408E;
    font-size: 14px;
    margin-top: 12px;
}
.ClubMessage-Container .rightBar .itemBar_stastic .num {
    font-size: 24px;
    font-weight: 700;
    color: #191B1F;
    margin-top: 3px;
}
.ClubMessage-Container .rightBar .itemBar_topic {
    margin: 12px 12px;
    height: 160px;
}
.ClubMessage-Container .rightBar .itemBar_topic .el-card__body {
    padding: 6px 16px;
    text-align: left;
    font-size: 14px;
}

@media screen and (max-width: 768px) {
    .ClubMessage-Container {
        width: 90%;
    }
    .ClubMessage-Container .rightBar {
        display: none;
    }
    .ClubMessage-Container .leftContent {
        width: 100%;
    }
}
</style>