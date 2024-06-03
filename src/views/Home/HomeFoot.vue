<template>
    <div class="Comment-Foot">
        <el-row class="navBottom pppc">
            <el-col class="navBlock" :span="6"></el-col>
            <el-col class="navBlock navFootBar" :span="12">
                <a class="navItem" v-for="(item, index) in navItem" :key="index">
                    {{ item.label }}
                </a>
            </el-col>
            <el-col class="navBlock share" :span="6">
                <a class="wechat style"></a>
                <a class="tel style"></a>
                <a class="mail style"></a>
                <a class="adr style"></a>
            </el-col>
        </el-row>
        <el-row class="moove">
            <el-col :span="24">
                
            </el-col>
        </el-row>
        <div class="siteInfo">
            <p>&copy;Ganxy|2024</p>
            <p>
                <a href="https://beian.miit.gov.cn/#/Integrated/index/">
                浙ICP备2022019862号-1
                </a>
            </p>
            <p><span style="font-size: 13px;">技术支持:</span>互联网信息协会</p>
        </div>
    </div>
</template>
<script>
export default {
    data() {
        return {
            navItem: [
                {
                    label: '部门概况',
                    url: '/bmgk/部门简介'
                },
                {
                    label: '服务指南',
                    url: '/service'
                },
                {
                    label: '规章制度',
                    url: '/rule'
                },
                {
                    label: '信息公示',
                    url: '/anno'
                },
                {
                    label: '下载专区',
                    url: '/download'
                },
                {
                    label: '联系我们',
                    url: '/connet'
                }
            ]
        }
    }
}
</script>
<style>
.Comment-Foot {
    width: 100%;
    min-height: 160px;
    background: url('@/assets/img/banner/bg_footer.jpg') 50% no-repeat;
    background-size: cover;
}
.Comment-Foot .moove {
    display: none;
    height: 60px;
}
.Comment-Foot .navBottom {
    height: 160px;
    width: 86%;
    margin: 0 auto;
    /* background-color: aqua; */
    display: flex;
}
.Comment-Foot .navBottom .navBlock {
    width: calc(100% - 64px * 3);
    height: 150px;
    margin: 0 32px;
    margin-top: 24px;
    /* background-color: blueviolet; */
}
.Comment-Foot .navBottom .navFootBar {
    
}
.Comment-Foot .navBottom .navFootBar .navItem {
    display: inline-block;
    width: 25%;
    line-height: 48px;
    font-size: 16px;
    color: #fff;
    position: relative;
    cursor: pointer;
}

.Comment-Foot .navBottom .navFootBar .navItem::before,
.Comment-Foot .navBottom .navFootBar .navItem::after {
    content: "";
    position: absolute;
    bottom: 0;
    width: 0;
    height: 1px;
    background-color: #ecac35;
    transition: width 0.3s;
}
.Comment-Foot .navBottom .navFootBar .navItem::before {
    left: 50%;
    transform: translateX(-50%);
}
.Comment-Foot .navBottom .navFootBar .navItem::after {
    right: 50%;
    transform: translateX(50%);
}
.Comment-Foot .navBottom .navFootBar .navItem:hover::before,
.Comment-Foot .navBottom .navFootBar .navItem:hover::after {
  width: 100%;
}
.Comment-Foot .navBottom .navFootBar .navItem:hover::before {
    transition: width 0.3s 0.15s;
}
.Comment-Foot .navBottom .navFootBar .navItem:hover::after {
    transition: width 0.3s;
}
.Comment-Foot .navBottom .navFootBar .navItem:hover {
    color: #ecac35;
}



.Comment-Foot .navBottom .share {
    width: 100%;
    height: 62px;
    margin-top: 48px;
    padding: 0 12px;
    display: flex;
    /* background-color: #ecac35; */
}
.Comment-Foot .wechat {
    background: url(@/assets/img/icon/wechat.png) 50% no-repeat;
}
.Comment-Foot .mail {
    background: url(@/assets/img/icon/mail.png) 50% no-repeat;
}
.Comment-Foot .tel {
    background: url(@/assets/img/icon/tel.png) 50% no-repeat;
}
.Comment-Foot .adr {
    background: url(@/assets/img/icon/adr.png) 50% no-repeat;
}
.Comment-Foot .style {
    width: 58px;
    height: 58px;
    border-radius: 50%;
    border: 1px solid rgba(255, 255, 255, .1);
    background-size: 40%;
    cursor: pointer;
    margin: 0 12px;
    transition: transform 0.3s, box-shadow 0.5s;
}
.Comment-Foot .navBottom .share .style:hover {
    transform: translateY(-12px);
}


.siteInfo {
    color: #787878;
    width: 40%;
    margin: 0 auto;
    display: flex;
    justify-content: space-between;
    padding-bottom: 12px;
}
.siteInfo a {
    color: #787878;
    cursor: pointer;
    text-decoration: none;
}
.siteInfo a, 
.siteInfo p {
    padding: 12px;
}
@media screen and (max-width: 1400px) {
    .Comment-Foot .navBottom {
        /* width: 100%; */
        padding: 0 24px;
    }
    .Comment-Foot .pppc {
        display: none;
    }
    .Comment-Foot .moove {
        display: block;
    }
}
@media screen and (max-width: 1200px) {
    .siteInfo {
        width: 80%;
    }
}
@media screen and (max-width: 768px) {
    .siteInfo {
        display: block;
        width: 100%;
    }
    .siteInfo a, 
    .siteInfo p {
        padding: 3px;
    }
}
</style>