<template>
    <div>
        <div class="user-function_left">

        </div>
        <div class="user-function_right">
            <div class="user-function_rightTitle">
                <h2>功能</h2>
            </div>
            <el-empty></el-empty>
        </div>
    </div>
</template>

<style>
.user-function_left {
    float: left;
    width: 30%;
    height: 520px;
    border-right: 1px solid #e5e9ef;
}
.user-function_right {
    float: right;
    width: calc(70% - 1px);
    min-height: 600px;
}
.user-function_rightTitle {
    text-align: left;
    padding: 6px 0;
    padding-left: 6px;
    margin: 0 20px;
    border-bottom: 1px solid #e5e9ef;
}
@media screen and (max-width: 480px) {
    
}
</style>