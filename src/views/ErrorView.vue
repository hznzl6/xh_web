<template>
    <div class="Down-Container">
        <div class="ContainerBac">
            <HeadNav />
        </div>
        <div style="display: flex">
            <div class="Container_left">
                <div style="float: left;margin-top: 12px;"><i class="el-icon-location"></i>当前位置:</div>
                <NoFound style="margin-bottom: 24px;" />
            </div>
            <div class="Container_right">
                <SideBar />
            </div>
        </div>
        <CommonFoot />
    </div>
</template>
<script>
import HeadNav from '../components/CommonHead.vue'
import SideBar from '../components/NavSideBar.vue'
import NoFound from '../components/NoFound.vue'
import CommonFoot from '@/components/CommonFoot.vue'
export default {
    components: {
        HeadNav,
        SideBar,
        NoFound,
        CommonFoot
    }
}
</script>
<style>
.Down-Container .ContainerBac {
    width: 100%;
    height: 560px;
    background: url(@/assets/img/banner/inset_bar.jpg) 50% no-repeat;
    background-size: cover;
}
.Down-Container .Container_left {
    justify-content: flex-start;
    width: 65%;
    margin: 0 50px;
    min-height: 50vh;
}
.Down-Container .Container_right {
    justify-content: flex-end;
    width: 320px;
    min-height: calc(50vh - 120px);
    margin: 60px 0;
}
</style>