<template>
    <div class="User-Container">
        <headNav class="bacColor"/>
        <div style="display: flex;">
            <sideNav class="sideNav"/>
            <RightContent class="rightContent"/>
        </div>
        <FootBar class="footBar"/>
    </div>
</template>

<script>
import headNav from '@/components/CommonHead.vue';
import sideNav from '@/components/UserSideMenu.vue'
import RightContent from '@/components/UserRightContent.vue'
import FootBar from '@/views/shetuan/User/SinglePage/FootBar.vue'
export default {
    components: {
        headNav,
        sideNav,
        RightContent,
        FootBar,
    },
    data() {
        return {
            list: [
                { title: '产品1', description: '这是一个产品描述。' },
                { title: '产品2', description: '这是另一个产品描述。' },
                { title: '产品3', description: '第三个产品描述。' }
            ]
        };
    }
};
</script>

<style scoped>
.User-Container {
    height: 100vh;
    width: 100%;
    /* background-color: #ab3232; */
}
.User-Container .bacColor {
    /* background-color: #3c8dbc; */
    background: linear-gradient(to right, #2f4050, #9e11d5); /* 渐变背景色 */
}
.User-Container .footBar {
    position: absolute;
    bottom: 0;
    width: calc(100% - 200px);
    min-height: 30px;
    left: 200px;
    margin: 0;
}

@media screen and (max-width: 768px) {
    .User-Container .sideNav {
        display: none;
    }
    .User-Container .footBar {
        width: 100%;
        left: 0;
    }
}
</style>