<template>
    <div>
        <div class="user-seting_left">

        </div>
        <div class="user-seting_right">
            <div class="user-seting_right_SetingTitle">
                <h2>设置</h2>
            </div>
            <el-empty></el-empty>
        </div>
    </div>
</template>

<style>
.user-seting_left {
    float: left;
    width: 30%;
    height: 520px;
    border-right: 1px solid #e5e9ef;
}
.user-seting_right {
    float: right;
    width: calc(70% - 1px);
    min-height: 600px;
}
.user-seting_right_SetingTitle {
    text-align: left;
    padding: 6px 0;
    padding-left: 6px;
    margin: 0 20px;
    border-bottom: 1px solid #e5e9ef;
}
@media screen and (max-width: 480px) {
    
}
</style>