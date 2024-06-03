<template>
    <div class="content-left">
        <h2 style="text-align: left;margin-bottom: 12px;">社团动态</h2>
        <el-row :gutter="20" class="pc">
            <el-col :span="12">
                <CarouselModule/>
            </el-col>
            <el-col :span="12">
                <ArticleList />
            </el-col>
        </el-row>
        <el-row class="move">
            <el-col :span="24">
                <CarouselModule/>
            </el-col>
            <el-col :span="24">
                <ArticleList />
            </el-col>
        </el-row>
    </div>
</template>

<script>
import CarouselModule from './CarouselModule.vue';
import ArticleList from './ArticleList.vue'
export default {
    components: {
        CarouselModule,
        ArticleList,
    }
}
</script>

<style>
.content-left .pc {
    display: block;
    padding: 0;
}
.content-left .move {
    display: none;
}
@media screen and (max-width: 878px) {
    .content-left .pc {
        display: none;
    }
    .content-left .move {
        display: block;
    }
}
</style>