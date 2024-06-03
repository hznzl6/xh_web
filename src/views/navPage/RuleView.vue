<template>
    <div class="Rule-Container">
        <div class="ContainerBac">
            <HeadNav />
        </div>
        <div style="display: flex">
            <div class="Container_left">
                <PageCard />
            </div>
            <div class="Container_right">
                <SideBar />
            </div>
        </div>
        <CommonFoot />
    </div>
</template>

<script>
import HeadNav from '../../components/CommonHead.vue'
import SideBar from '../../components/NavSideBar.vue'
import PageCard from '../../components/PageLeftCard.vue'
import CommonFoot from '@/components/CommonFoot.vue'
export default {
    components: {
        HeadNav,
        SideBar,
        PageCard,
        CommonFoot
    }
}
</script>

<style>
.Rule-Container .ContainerBac {
    width: 100%;
    height: 560px;
    background: url(@/assets/img/banner/inset_bar.jpg) 50% no-repeat;
    background-size: cover;
}
.Rule-Container .Container_left {
    justify-content: flex-start;
    width: 65%;
    margin: 0 50px;
    min-height: 50vh;
}
.Container_right {
    justify-content: flex-end;
    width: 320px;
    min-height: calc(50vh - 120px);
    margin: 60px 0;
}
</style>