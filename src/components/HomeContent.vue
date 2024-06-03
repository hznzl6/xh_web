<template>
    <div style="height: calc(100vh - 100px);width: 100%;position: relative;">
        
        <div class="Content-Container">
            <div class="ContainerBac">
                <HeadNav />
            </div>
            <div style="display: flex">
                <div class="Container_left">
                   
                </div>
                <div class="Container_right">
                    <SideBar />
                </div>
            </div>
        </div>
        <CommonFoot class="site-foot" />
    </div>
</template>

<script>
import HeadNav from '../components/CommonHead.vue'
import SideBar from '../components/NavSideBar.vue'
import CommonFoot from '@/components/CommonFoot.vue'
export default {
    components: {
        HeadNav,
        SideBar,
        CommonFoot
    }
}
</script>
<style>
.Content-Container .ContainerBac {
    width: 100%;
    height: 560px;
    background: url(@/assets/img/banner/inset_bar.jpg) 50% no-repeat;
    background-size: cover;
}
.Content-Container .Container_left {
    justify-content: flex-start;
    width: 65%;
    margin: 0 50px;
    min-height: 50vh;
}
.Content-Container .Container_right {
    justify-content: flex-end;
    width: 320px;
    min-height: calc(50vh - 120px);
    margin: 60px 0;
}
</style>