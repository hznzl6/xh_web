<template>
    <div><el-row class="contentlist" style="margin-top: 80px;">
        <el-col :span="8">
            <div class="semi" style="width: 24px;height: 24px;">
                <img class="semi-img" src="@/assets/img/ai/book.png" alt="">
            </div>
            <h2 class="title">AI创作</h2>
            <ul class="content">
                <li class="content-list">写一首赞美祖国的诗<i class="el-icon-right"></i></li>
                <li class="content-list">写一篇科幻小说<i class="el-icon-right"></i></li>
                <li class="content-list">用python写一个猜数字的游戏并运行它<i class="el-icon-right"></i></li>
            </ul>
        </el-col>
        <el-col :span="8">
            <div class="semi" style="width: 24px;height: 24px;">
                    <img class="semi-img" src="@/assets/img/ai/ding.png" alt="">
                    </div>
                    <h2 class="title">有趣的提问</h2>
                    <ul class="content">
                        <li class="content-list">有哪些有趣的科学实验<i class="el-icon-right"></i></li>
                        <li class="content-list">如何问一个让AI也答不出的问题<i class="el-icon-right"></i></li>
                        <li class="content-list">AI会代替人类工作吗<i class="el-icon-right"></i></li>
                        <li class="content-list">解锁更多AI玩法<i class="el-icon-right"></i></li>
                    </ul>
        </el-col>
        <el-col :span="8">
            <div class="semi" style="width: 24px;height: 24px;">
                    <img class="semi-img" src="@/assets/img/ai/three.png" alt="">
                    </div>
                    <h2 class="title">AI百科</h2>
                    <ul class="content">
                        <li class="content-list">用简单的术语来解释人工智能<i class="el-icon-right"></i></li>
                        <li class="content-list">莲藕排骨汤的做法<i class="el-icon-right"></i></li>
                        <li class="content-list">EDU是什么意思<i class="el-icon-right"></i></li>
                    </ul>
        </el-col>
       </el-row>
       </div>
</template>
<style>

.aiask .contentlist{
    width: 50%;
    /* margin-top: 50px; */
    margin: 0 auto;
}
.aiask .content{
    list-style: none;
}
.aiask .content-list{
    background-color: rgb(235, 234, 233);
    margin: 10px 10px 10px 0;
    border-radius: 10px;
    padding: 10px;
    /* overflow: hidden; */
    min-height: 20px;
    /* max-width: 120px; */
    width:80%;
    white-space:normal;
    word-break : break-all;
    word-wrap: break-word;
    transition: 0.9s;
}
.aiask .content-list:hover{
    cursor: pointer;
    background-color: rgb(219, 218, 218);
}
.aiask .semi{
    border-radius: var(--semi-border-radius-small);
    position: relative;
    display: inline-block;
    overflow: hidden;
}
.aiask .semi .semi-img:hover{
    cursor: pointer;

}
.aiask  .title:hover{
    cursor: pointer;
}

</style>