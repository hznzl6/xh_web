<template>
    <div class="PageCard-Container">
        <div style="display: flex;margin-top: 12px;justify-content: flex-start;">
            <i class="el-icon-location"></i>
            当前位置:
            <span v-show="this.$route.fullPath == '/rule'">规章制度</span>
            <span v-show="this.$route.fullPath == '/anno'">信息公示</span>
            <span v-show="this.$route.fullPath == '/download'">下载专区</span>
        </div>
        <el-row class="cardLine" :gutter="48">
            <el-col  class="cardItem" :span="12">
                <div class="time">
                    <em>23</em>
                    <p>May</p>
                </div>
                <div class="text" @click="navTo">
                   <h3 >中华人民共和国数据安全法 （2021年6月10日第十三届全国人民代表大会常务委员会第二十九次会议通过）</h3>
                   <p>开展数据处理活动，应当遵守法律、法规，尊重社会公德和伦理，遵守商业道德和职业道德，诚实守信，履行数据安全保护义务，承担社会责任，不得危害国家安全、公共利益，不得损害个人、组织的合法权益。</p>
                </div>
            </el-col>
            <el-col class="cardItem" :span="12">
                <div class="time">
                    <em>23</em>
                    <p>May</p>
                </div>
                <div class="text">
                   <h3>互联网电子邮件服务管理办法（信息产业部2005年11月7日）</h3>
                   <p>国家对互联网电子邮件服务提供者的电子邮件服务器IP地址实行登记管理。互联网电子邮件服务提供者应当在电子邮件服务器开通前二十日将互联网电子邮件服务器所使用的IP地址向中华人民共和国信息产业部（以下简称“信息产业部”）或者省、自治区、直辖市通信管理局（以下简称“通信管理局”）登记。</p>
                </div></el-col>
        </el-row>
        <el-row class="cardLine" :gutter="48">
            <el-col class="cardItem" :span="12">
                <div class="time">
                    <em>23</em>
                    <p>May</p>
                </div>
                <div class="text">
                   <h3>中国教育和科研计算机网用户守则（试 行）(1995年12月)</h3>
                   <p>中国教育和科研计算机网络(CERNET)是国家教育委员会直接领导管理的全国性教育和科研计算机网络，是 指 通 过 接 入 互 联 网 络 进 行 国 际 联 网 的 计 算 机 信 息 网 络； 接 入 单 位， 是 指 负 责 接 入 网 络 运 行 的 单 位。</p>
                </div>
            </el-col>
            <el-col class="cardItem" :span="12">
                <div class="time">
                    <em>23</em>
                    <p>May</p>
                </div>
                <div class="text">
                   <h3>互联网电子公告服务管理规定(信息产业部2000年10月8日)</h3>
                   <p>从事互联网信息服务，拟开展电子公告服务的，应当在向省、自治区、直辖市电信管理机构或者信息产业部申请经营性互联网信息服务许可或者办理非经营性互联网信息服务备案时，提出专项申请或者专项备案。</p>
                </div></el-col>
        </el-row>
        <el-row class="cardLine" :gutter="48">
            <el-col class="cardItem" :span="12">
                <div class="time">
                    <em>23</em>
                    <p>May</p>
                </div>
                <div class="text">
                   <h3>计算机病毒防治管理办法   (2000年4月26日)</h3>
                   <p>在非经营活动中有违反本办法第五条、第六条第二、三、四项规定行为之一的，由公安机关处以一千元以下罚款。 在经营活动中有违反本办法第五条、第六条第二、三、四项规定行为之一，没有违法所得的，由公安机关对单位处以一万元以下罚款，对个人处以五千元以下罚款；</p>
                </div>
            </el-col>
            <el-col class="cardItem" :span="12">
                <div class="time">
                    <em>23</em>
                    <p>May</p>
                </div>
                <div class="text">
                   <h3>中华人民共和国计算机信息网络国际联网管理暂行规定(1996年2月1日第195号)</h3>
                   <p> 计 算 机 信 息 网 络 直 接 进 行 国 际 联 网， 必 须 使 用 邮 电 部 国 家 公 用 电 信 网 提 供 的 国 际 出 入 口 信 道。 任 何 单 位 和 个 人 不 得 自 行 建 立 或 者 使 用 其 他 信 道 进 行 国 际 联 网。</p>
                </div></el-col>
        </el-row>
       
    </div>
</template>
<script>
export default {
    data() {
        return {

        }
    },
    created() {
        // console.log(this.$route.params)
        console.log(this.$route.fullPath);

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
body {
    background-color: #f5f5f5;
}
.PageCard-Container {
    width: 100%;
    min-height: 320px;
}
.PageCard-Container .cardLine {
    display: flex;
    margin-top: 24px;
}
.PageCard-Container .cardItem {
    /* border-top: 2px solid #2e45e1; */
    border-top: 2px solid;
    border-image: linear-gradient(to right, #2e45e1, #87CEFA) 1;
    height: 190px;
    background-color: #fff;
    margin: 0 12px;
    cursor: pointer;
    padding: 42px 24px;
    transition: transform 0.3s, box-shadow 0.5s;
}

.PageCard-Container .cardItem:hover {
    box-shadow: 0 6px 20px rgba(25,40,118,.2);
    transform: translateY(-12px);
}
.PageCard-Container .cardLine .time{
    font-size: 20px;
    width: 80px;
    height: 70px;
    color: #fff;
    text-align: center;
    float: left;
    /* padding: 4px 0 14px; */
    border-radius: 4px;
    /* background-color: #2e45e1; */
    background: url(@/assets/img/icon/line.jpg) 50% repeat-y;
    background-size: 100% auto;
}
.PageCard-Container .cardLine .time em{
   
    display: block;
    font-size: 36px;
    font-family: Roboto Slab;
    font-style: normal;
}
.PageCard-Container .cardLine .time p{
    font-style: italic;
}
.PageCard-Container .cardLine .cardItem .text{
    float: right;
    width: calc(100% - 104px);
}
.PageCard-Container .cardLine .cardItem .text h3{
    /* width: 200px; */
    font-size: 18px;
    font-weight: 500;
    color: #333;
    margin-bottom: 18px;
    overflow: hidden;      /*溢出隐藏*/
    white-space: nowrap;	/*规定文本不进行换行*/
    text-overflow: ellipsis;	/*当对象内文本溢出时显示省略标记（...）*/

}
.cardItem .text p{
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 3;
    /* line-height: 22px;
    height: 64px; */
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: normal;
   
}
</style>