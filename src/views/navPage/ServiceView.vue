<template>
    <div class="ServiceIndex">
        <div class="ContainerBac">
            <headNav />
        </div>
        <div>
            <div class="showList">
                <div style="text-align: left;margin-top: 12px;"><i class="el-icon-location"></i>当前位置:
                    <span v-show="this.$route.fullPath == '/service'">服务指南</span>
                </div>
                <h2>服务列表</h2>
                <div class="services">
                    <div v-for="item in List.services" :key="item" class="service">
                        <i :class="getIcon(item)"></i>
                        <h2>{{ item.title }}</h2>
                        <p>{{ item.describe }}</p>
                    </div>
                </div>
                <h2>社团列表</h2>
                <div class="clubs">
                    <div v-for="item in List.clubs" :key="item" class="club">
                        <i :class="getIcon(item)"></i>
                        <h2 >{{ item.title }}</h2>
                        <p>{{ item.slogan }}</p>
                    </div>
                </div>
            </div>
        </div>
        <CommonFoot />
    </div>
</template>
<script>
import '@/assets/css/iconLibrary.css'
import headNav from '../../components/CommonHead.vue'
import CommonFoot from '@/components/CommonFoot.vue'
export default {
    components: {
        headNav,
        CommonFoot
    },
    data() {
        return {
            List: {
                services: [
                    {
                        icon: 'commune',
                        title: '社员账户',
                        color: '#5EB3BF',
                        url: '/user',
                        describe: '社团成员的用户中心'
                    },
                    {
                        icon: 'ali-ai',
                        title: 'Ai问答系统',
                        color: '#635CBD',
                        url: '/ai',
                        describe: '接入数据大模型的人工智能'
                    },
                    {
                        icon: 'shetuan',
                        title: '社团管理系统',
                        color: '#3C98B3',
                        url: '/manager-login',
                        describe: '社团管理的后台'
                    },
                    {
                        icon: 'backstage',
                        title: '后台管理系统',
                        color: '#386FB2',
                        url: '/houtai-login',
                        describe: '网站管理的后台'
                    }
                ],
                clubs: [
                    {
                        icon: 'internet',
                        title: '互联网社',
                        color: '#386FB2',
                        url: '/',
                        slogan: '热爱代码 敲到世界充满爱'
                    },
                    {
                        icon: 'skipping',
                        title: '花绳俱乐部',
                        color: '#386FB2',
                        url: '/',
                        slogan: '热爱代码 敲到世界充满爱'
                    },
                    {
                        icon: 'Music',
                        title: '爱乐社',
                        color: '#386FB2',
                        url: '/',
                        slogan: '热爱代码 敲到世界充满爱'
                    },
                    {
                        icon: 'dream',
                        title: '新梦想',
                        color: '#386FB2',
                        url: '/',
                        slogan: '热爱代码 敲到世界充满爱'
                    },
                    {
                        icon: 'Painting',
                        title: '书画社',
                        color: '#386FB2',
                        url: '/',
                        slogan: '热爱代码 敲到世界充满爱'
                    },
                    {
                        icon: 'psychology',
                        title: '心理社',
                        color: '#386FB2',
                        url: '/',
                        slogan: '热爱代码 敲到世界充满爱'
                    },
                    {
                        icon: 'tea',
                        title: '习茶社',
                        color: '#386FB2',
                        url: '/',
                        slogan: '热爱代码 敲到世界充满爱'
                    },
                    {
                        icon: 'martial',
                        title: '武术社',
                        color: '#386FB2',
                        url: '/',
                        slogan: '热爱代码 敲到世界充满爱'
                    },
                    {
                        icon: 'billiards',
                        title: '台球社',
                        color: '#386FB2',
                        url: '/',
                        slogan: '热爱代码 敲到世界充满爱'
                    },
                    {
                        icon: 'language',
                        title: '语言社',
                        color: '#386FB2',
                        url: '/',
                        slogan: '热爱代码 敲到世界充满爱'
                    },
                    {
                        icon: 'Hanfu',
                        title: '汉服社',
                        color: '#386FB2',
                        url: '/',
                        slogan: '热爱代码 敲到世界充满爱'
                    },
                    {
                        icon: 'eloquence',
                        title: '口才社',
                        color: '#386FB2',
                        url: '/',
                        slogan: '热爱代码 敲到世界充满爱'
                    },
                    {
                        icon: 'chess',
                        title: '围棋社',
                        color: '#386FB2',
                        url: '/',
                        slogan: '热爱代码 敲到世界充满爱'
                    },
                    {
                        icon: 'photography',
                        title: '摄影社',
                        color: '#386FB2',
                        url: '/',
                        slogan: '热爱代码 敲到世界充满爱'
                    },
                    {
                        icon: 'read',
                        title: '读书社',
                        color: '#386FB2',
                        url: '/',
                        slogan: '热爱代码 敲到世界充满爱'
                    },
                    {
                        icon: 'dance',
                        title: '舞蹈社',
                        color: '#386FB2',
                        url: '/',
                        slogan: '热爱代码 敲到世界充满爱'
                    },
                    {
                        icon: 'anime',
                        title: '动漫社',
                        color: '#386FB2',
                        url: '/',
                        slogan: '热爱代码 敲到世界充满爱'
                    },
                    {
                        icon: 'badminton',
                        title: '羽毛球社',
                        color: '#386FB2',
                        url: '/',
                        slogan: '热爱代码 敲到世界充满爱'
                    },
                    {
                        icon: 'pingpong',
                        title: '乒乓球社',
                        color: '#386FB2',
                        url: '/',
                        slogan: '热爱代码 敲到世界充满爱'
                    },
                    {
                        icon: 'literature',
                        title: '文学社',
                        color: '#386FB2',
                        url: '/',
                        slogan: '热爱代码 敲到世界充满爱'
                    }
                ]
            }
        }
    },
    methods: {
        getIcon(item) {
            return item.icon;
        }
    }
}
</script>
<style>
.ServiceIndex {
    min-height: 100vh;
    background-color: #f2f2f2;
}
.ServiceIndex .ContainerBac {
    width: 100%;
    height: 560px;
    background: url(@/assets/img/banner/inset_bar.jpg) 50% no-repeat;
    background-size: cover;
}
.ServiceIndex .showList {
    width: 70%;
    margin: 0 auto;
}
.services, .clubs {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
}
.service, .club {
    height: 100px;
    /* width: calc(25% - 20px); */
    padding: 20px;
    flex: 1 0 25%;
    margin: 10px 16px;
    background-color: #f9f9f9;
    border-radius: 8px;
    text-align: center;
    transition: transform 0.3s;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    cursor: pointer;
    position: relative;
}
/* .service i{
    display: none;
}
.club i{
    display: block;
} */
.service:hover, .club:hover {
    transform: scale(1.1);
    box-shadow: 0 6px 8px rgba(0, 0, 0, 0.2);
    
}

.service h2, .club h2 {
    color: #333;
    margin-top: 20px;
}
.service p, .club p {
    color: #666;
}





@media screen and (max-width: 768px) {
    .ServiceIndex .showList {
        width: 96%;
    }
    .service, .club {
        width: calc(50% - 20px - 20px - 20px); /* 在小屏幕下，每行显示两个元素 */
        margin: 10px;
    }
    .service:hover, .club:hover {
        transform: scale(1.1);
    }
}

</style>