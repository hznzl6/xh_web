<template>
    <div class="HomePage-Conatainer">
        <div class="intro">
            <blockquote class="text-intro">
                <h4>欢迎使用社团管理系统</h4>
                <p>本项目还处于开发过程中...</p>
                <p>敬请期待 ☛☛<a style="color: #409EFF;">项目地址</a>☚☚     更多技术详见 ☛☛<a style="color: #409EFF;">技术文档</a>☚☚</p>
                <p>剩余工作预计两周内完成</p>
            </blockquote>
            <el-row class="text-info pc">
                <el-col :span="6">
                    <h2>视频演示</h2>
                    <p>可使用微信扫码观看视频演示</p>
                    <img src="@/assets/img/user/code.png" alt="">
                </el-col>
                <el-col class="infoItem" :span="10">
                    <h2>社团管理系统-用户系统</h2>
                    <div class="text-content">
                        <!-- <p>此模块为用户系统，实现了诸如加入的社团信息、社团概览、社团站内消息、AI问答、在线聊天及省一选择练习等功能..</p> -->
                        <p>该用户系统模块提供了丰富的功能，包括社团信息的加入方式、各社团概况、站内消息浏览、AI问答功能、在线聊天以及省级选择练习等，满足了用户在校园生活中的各种需求和兴趣。</p>
                        <!-- <p>界面干净简洁、功能丰富，上手容易</p> -->
                        <p>界面设计简洁大方，操作简单易上手，为用户提供了良好的交互体验。为了进一步增强用户参与度和个性化体验，可以考虑拓展社团互动功能，增加个性化推荐系统，让用户更方便地发现与之相关的内容和活动。</p>
                        <p>此外，系统也可增加活动日历功能，方便用户查看社团活动安排，提前规划参与。同时，开设用户反馈渠道，及时了解用户需求，持续优化系统功能，确保用户满意度和忠诚度。</p>
                    </div>
                    <el-button style="margin-top: 12px;"><i class="el-icon-document"></i>查看文档</el-button>
                    <el-button style="margin-top: 12px;"><i class="el-icon-github"></i>获取源码</el-button>
                </el-col>
                <el-col :span="8">
                    <h2 style="text-align: left;">技术选型:</h2>
                    <div style="padding: 0;" class="text-content">
                        <p>1. 核心框架: Vue2、SPring Boot、JPA... </p>
                        <p>2. 更多插件: Element、Swagger-UI、Axios、Vue-router...</p>
                        <p>3. 外部接口: 讯飞星火API...</p>
                    </div>
                </el-col>
            </el-row>

            <el-row class="text-info move">
                <el-col :span="24">
                    <h2>视频演示</h2>
                    <p>可使用微信扫码观看视频演示</p>
                    <img src="@/assets/img/user/code.png" alt="">
                </el-col>
                <el-col class="infoItem" :span="24">
                    <h2>社团管理系统-用户系统</h2>
                    <div class="text-content">
                        <!-- <p>此模块为用户系统，实现了诸如加入的社团信息、社团概览、社团站内消息、AI问答、在线聊天及省一选择练习等功能..</p> -->
                        <p>该用户系统模块提供了丰富的功能，包括社团信息的加入方式、各社团概况、站内消息浏览、AI问答功能、在线聊天以及省级选择练习等，满足了用户在校园生活中的各种需求和兴趣。</p>
                        <!-- <p>界面干净简洁、功能丰富，上手容易</p> -->
                        <p>界面设计简洁大方，操作简单易上手，为用户提供了良好的交互体验。为了进一步增强用户参与度和个性化体验，可以考虑拓展社团互动功能，增加个性化推荐系统，让用户更方便地发现与之相关的内容和活动。</p>
                        <p>此外，系统也可增加活动日历功能，方便用户查看社团活动安排，提前规划参与。同时，开设用户反馈渠道，及时了解用户需求，持续优化系统功能，确保用户满意度和忠诚度。</p>
                    </div>
                    <el-button style="margin-top: 12px;"><i class="el-icon-document"></i>查看文档</el-button>
                    <el-button style="margin-top: 12px;"><i class="el-icon-github"></i>获取源码</el-button>
                </el-col>
                <el-col :span="24">
                    <h2 style="text-align: left;">技术选型:</h2>
                    <div style="padding: 0;" class="text-content">
                        <p>1. 核心框架: Vue2、SPring Boot </p>
                        <p>2. 更多插件: ElementUI、Echat.js、WebSocket...</p>
                        <p>3. 外部接口: 讯飞星火API、QQ互联API、及时天气API...</p>
                    </div>
                </el-col>
            </el-row>
        </div>
        <div class="module">
            
        </div>
    </div>
</template>
<script>

export default {
    components: {
        
    }
}
</script>
<style>
.HomePage-Conatainer {
    width: calc(100% - 24px);
    min-height: calc(100vh - 100px - 24px - 52px);
    /* background-color: aqua; */
    margin: 12px;
    overflow-y: auto;
}
.HomePage-Conatainer .intro {
    min-height: 50vh;
    width: 100%;
    color: #676C6A;
    background-color: #fff;
    padding: 12px 0 0 0;
}
.HomePage-Conatainer .intro .move {
    display: none
}
.HomePage-Conatainer .intro .move .infoItem {
    padding: 0;
}
.HomePage-Conatainer .text-intro {
    min-height: 80px;
    border-left: 5px solid #eee;
    margin: 0 0 0 12px;
    font-size: 14px;
    text-align: left;
    padding: 6px 12px;
    line-height: 1.5;
    /* background-color: rgb(158, 232, 20); */
}
.HomePage-Conatainer .text-info {
    margin: 24px 24px 0 24px;
    padding: 12px 0;
    border-top: 1px solid #eee;
}
.HomePage-Conatainer .text-info .infoItem {
    /* margin: 0 48px; */
    padding: 0 48px;
    position: relative;
}
.text-info .text-content {
    text-align: left;
    text-indent: 2em;
    line-height: 1.5;
}


/* span {
    position: relative;
} */
.text-info .el-icon-github::before {
    /* background: url(@/assets/img/icon/github-fill.png) 50% no-repeat;
    width: 14px;
    height: 14px; */
    content: "";
    display: inline-block;
    width: 14px;
    height: 14px;
    background: url('@/assets/img/icon/github-fill.png') 50% no-repeat;
    position: relative;
    top: 0.665px;
}

@media screen and (max-width: 768px) {
    .HomePage-Conatainer .intro .pc {
        display: none;
    }
    .HomePage-Conatainer .intro .move {
        display: block;
    }
}
</style>