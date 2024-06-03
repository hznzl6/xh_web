<template>
    <!-- <div>404</div> -->
    <div class="notFound">
        <!-- <HeadNav /> -->
        <div class="content">
            <p>Sorry,there are some problems with this page.</p>
            <el-button type="primary"><i class="el-icon-right"></i>返回主页</el-button>
        </div>
    </div>
</template>

<script>
// import HeadNav from '../components/CommonHead.vue'
export default {
    components: {
        // HeadNav
    }
}
</script>

<style>
body {
    background-color: #f5f5f5;
}
.notFound {
    height: 55vh;
    background: url(@/assets/img/error/404.png) 50% no-repeat;
    background-size: cover;
    position: relative;
    background-color: #f5f5f5;
}

.content {
    width: 100%;
    position: absolute;
    top: calc(22.5vh + 220px);
    font-weight: 700;
}
.content p {
    margin-bottom: 12px;
}
</style>