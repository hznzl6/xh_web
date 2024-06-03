<template>
    <div>
        <CommonHead />
        <div class="Notice-Container">
            <div class="Notice-Container_html" v-html="list_notice.content"></div>
            <ul class="Notice-Container_messages">
                <li class="Notice-Container_messagesItem">
                    发布时间:  {{ list_notice.create_time }}
                </li>
                <li class="Notice-Container_messagesItem">
                    发布者:  {{ list_notice.publisher }}
                </li>
                <li class="Notice-Container_messagesItem">
                    点击量:  {{ list_notice.hits }}次
                </li>
                <!-- <li class="Notice-Container_messagesItem">
                    <span></span>
                    <span>{{ messages.publisher }}</span>
                </li>
                <li class="Notice-Container_messagesItem">
                    <span>发布时间:  </span>
                    <span>{{ messages.create_time }}</span>
                    
                </li>
                <li class="Notice-Container_messagesItem">
                    <span>点击量:  </span>
                    <span>{{ messages.hits }}次</span>
                </li> -->
            </ul>
        </div>
        <CommonFoot class="site-foot" />
    </div>
</template>
<script>
import CommonHead from '@/components/CommonHead.vue'
import CommonFoot from '@/components/CommonFoot.vue'
export default {
    components: {
        CommonHead,
        CommonFoot
    },
    data() {
        return {
            list_notice: [],
        }
    },
    created() {
        this.getContent(this.$route.params.title)
        this.hits()
    },
    methods: {
        getContent(title) {
            const url = process.env.VUE_APP_BASE_URI + '/gonggao-notice/notice-getContent'
            fetch(`${url}?title=${title}`, {
                method: "POST",
                headers: {
                    'Accept': 'Application/json',
                    'Content-Type': 'Application/json'
                }
            })
            .then(res => res.json())
            .then(res => {
                this.list_notice = res
            })
        },
        hits() {
            const title = this.$route.params.title
            const url = process.env.VUE_APP_BASE_URI + '/gonggao-notice/notice-hits'
            fetch(`${url}?title=${title}`, {
                method: "POST",
                headers: {
                    'Accept': 'Application/json',
                    'Content-Type': 'Application/json'
                }
            })
            .then(res => res.text())
            .then(res => {
                console.log(res)
            })
        }
    }
}
</script>

<style>
.Notice-Container {
    color: #fff;
    min-height: calc(100vh - 104px);
    width: 100%;
    background: url(../../assets/img/banner/inset_bar.jpg) 50% no-repeat;
    background-size: cover;
    display: flex;
    justify-content: center;
    align-items: center;
}
.Notice-Container_html {
    position: relative;
    width: 65%;
    padding: 0 72px;
    background-color: rgb(78, 80, 224, 0.5);
    /* min-height: calc(100vh - 204px) */
    /* min-height: 50vh; */
    height: 60vh;
    overflow-y: auto;
}
.Notice-Container_html p {
    text-align: left;
}
.Notice-Container_messages {
    position: absolute;
    display: flex;
    justify-items: center;
    top: 106px;
    /* position: absolute;
    right: 0;
    bottom: 104px; */
}
.Notice-Container_messagesItem {
    display: inline-block;
    margin: 3px 12px;
    font-size: 13px;

    /* position: relative;
    width: 240px;
    list-style: none;
    text-align: right;
    margin: 6px 12px;
    font-size: 11px;
    display: flex;
    justify-content: space-between;
    align-items: center; */
}
@media screen and (max-width: 490px) {
    .Notice-Container_html {
        padding: 0 24px;
        width: 80%;
    }
    .Notice-Container_messagesItem {
        margin: 0 12px;
        font-size: 11px;
    }
}
</style>