<template>
    <ul class="home-article">
        <li class="ListItem">
            <div class="sss">
                <h3 @click="navTo">
                    <time datetime="">{{ ArticleList[0].time }}</time>
                    {{ ArticleList[0].title }}
                </h3>
                <p>{{ ArticleList[0].content }}</p>
            </div>
        </li>
        <li class="ListItem" v-for="item in ArticleList" :key="item.title">
            <h3 @click="navTo"><time datetime="">{{ item.time }}</time>{{ item.title }}</h3>
        </li>
    </ul>
</template>
<script>
export default {
    data() {
        return {
            ArticleList: [
                {
                    time: '12-20',
                    title: '网络与信息中心组织召开“以师生为中心”建设东大特色智慧校园专题学生座谈会',
                    content: '12月20日，网络与信息中心组织召开“以师生为中心”建设东大特色智慧校园专题学生座谈会，总结2023年以来相关工作开展情况并就校园信息系统使用进行意'
                },
                {
                    time: '12-12',
                    title: '南京航空航天大学同行来我校交流访问',
                    content: '12月20日，网络与信息中心组织召开“以师生为中心”建设东大特色智慧校园专题学生座谈会，总结2023年以来相关工作开展情况并就校园信息系统使用进行意'
                },
                {
                    time: '12-12',
                    title: '网络与信息中心赴人文学院开展信息化服务宣讲',
                    content: '12月20日，网络与信息中心组织召开“以师生为中心”建设东大特色智慧校园专题学生座谈会，总结2023年以来相关工作开展情况并就校园信息系统使用进行意'
                },
                {
                    time: '12-12',
                    title: '河南师范大学同行来我校交流访问',
                    content: '12月20日，网络与信息中心组织召开“以师生为中心”建设东大特色智慧校园专题学生座谈会，总结2023年以来相关工作开展情况并就校园信息系统使用进行意'
                },
            ]
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
.home-article {
    padding: 0;
}
.home-article .ListItem {
    list-style: none;
    padding: 12px;
}
.home-article .ListItem  time{
    float: left;
    color: #546f44;
    font-weight: 700;
    font-size: 20px;
    margin: 0 20px 0 0;
    /* margin-top: 20px; */
    

}
.home-article .ListItem .sss{
    padding-bottom: 22px;
    border-bottom: 1px dashed #b3c6da;
    margin: 0 0 12px 0;
    height: 80px;
    margin-top: 40px;
}
.home-article .ListItem  h3{
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    margin: 5px 0;
    text-align: left;
}
.home-article .ListItem  h3:hover{
    color: #546f44;
    cursor: pointer;
}
.home-article .ListItem  p{
    line-height: 24px;
    font-size: 14px;
    color: #666;
    white-space: initial;
    max-height: 45px;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 2; /* 显示两行 */
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: normal;
}
</style>