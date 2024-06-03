<template>
    <div class="notice">
        <h2 style="text-align: left;">通知公告</h2>
        <a v-for="item in ArticleList" :key="item.id">
            <div class="time">
                <em>{{ item.day }}</em>
                <p>{{ item.month }}</p>
            </div>
            <div class="text">
                <p class="title" @click="navTo">{{ item.title }}</p>
                <p class="content">{{ item.content }}</p>
            </div>
        </a>
    </div>
</template>

<script>
export default {
    data() {
        return {
            ArticleList: [
                {
                    id: 1,
                    day:'2',
                    month:'May',
                    title:'关于九龙湖校区常州科技楼网络割接的通知',
                    content:'尊敬的各位老师、同学：  网络与信息中心计划于2024年3月3日8:00-3月3日24:00对九龙湖校区常州科技楼汇聚机房割接，届时会影响常州科技楼的有线网和无线网使用。 给您造成的不便，敬请谅解'
                },
                {
                    id: 2,
                    day:'3',
                    month:'May',
                    title:'关于信息化系统维护的通知',
                    content:'尊敬的各位老师、同学：由于Chrome等浏览器的版本限制，近期部分HTTP协议系统打开异常，为解决此问题，网络与信息中心定于2024年2月2日20:00至次日09:30对信息门户、研究生综合管理系统、教务综合管理系统、学工系统、人事系统、基金会系统、因公出国、双证打印等系统进行停服维护。系统维护期间，各系统暂停服务，请各位老师同学提前做好工作安排。维护完成后系统在使用过程中如有异常请及时和我们反馈，由此造成的不便，敬请...'
                },
                {
                    id: 3,
                    day:'4',
                    month:'May',
                    title:'南京航空航天大学同行来我校交流访问',
                    content:'尊敬的各位老师、同学：网络与信息中心计划于2024年2月1日23:00-2月2日06:00对四牌楼和丁家桥校区进行宿舍无线网升级，届时会影响四牌楼和丁家桥校区宿舍无线网使用。给您造成的不便，敬请谅解！网络与信息中心2024年1月31'
                },
                {
                    id: 4,
                    day:'5',
                    month:'May',
                    title:'网络与信息中心赴人文学院开展信息化服务宣讲',
                    content:'尊敬的各位老师、同学：网络与信息中心计划于2024年2月2日23:00-2月3日07:00对九龙湖进行无线网升级，届时会影响九龙湖校区无线网使用。给您造成的不便，敬请谅解！网络与信息中心2024年1月31'
                },
                {
                    id: 5,
                    day:'5',
                    month:'May',
                    title:'网络与信息中心赴人文学院开展信息化服务宣讲',
                    content:'尊敬的各位老师、同学：网络与信息中心计划于2024年2月2日23:00-2月3日07:00对九龙湖进行无线网升级，届时会影响九龙湖校区无线网使用。给您造成的不便，敬请谅解！网络与信息中心2024年1月31'
                },
            ]
        }
    },
    computed: {
        truncatedContent() {
            return this.ArticleList.map(item => item.content.slice(0, 30));
        }
    },
    methods: {
        navTo() {
            if (this.$route.path !== '/content') {
            this.$router.push('/content');
            }
        }
    }
}
</script>
<style>
.notice {
    padding: 12px;
}
.notice a {
    display: flex;
    margin: 12px 0;
}
.notice a .time {
    width: 86px;
    height: 85px;
    font-size: 18px;
    border: 1px solid #8dac9a;
    border-radius: 4px;
    padding: 0 8px;
    line-height: 37px;
    text-align: center;
    color: #546f44;
    font-weight: 700;
}
.notice a .time p {
    border-top: 1px solid #a2bcbb;
}
.notice a .text {
    width: 100%;
    min-height: 90px;
    text-align: left;
    margin: 0 0 0 12px;
    position: relative;
}
.notice a .text .title {
    font-weight: 700;
    cursor: pointer;
    position: absolute;
    top: 0;
}
.notice a .text .title:hover {
    color: #546f44;
}
.notice a .text .content {
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 2; /* 显示两行 */
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: normal;
    margin: 6px 0 0 0;
    position: absolute;
    bottom: 6px;
}
</style>