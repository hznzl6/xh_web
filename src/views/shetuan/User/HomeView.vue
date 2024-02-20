<template>
    <div>
        <!-- 左半部分 -->
        <div class="user-component_home_left">
            <!-- 基本信息 -->
            <div class="user-component_home_basicInfo">
                <div class="user-component_home_sectionTitle">基本信息<span>修改</span></div>
                <ul>
                    <li>昵称:<span>{{ this.UserForm.nickname }}</span></li>
                    <li>学号:<span>{{ this.UserForm.sno }}</span></li>
                    <li>姓名:<span>{{ this.UserForm.name }}</span></li>
                    <li>班级:<span>{{ this.UserForm.belong }}</span></li>
                    <li>qq:<span>{{ this.UserForm.qq }}</span></li>
                    <li>邮箱:<span>{{ this.UserForm.mail }}</span></li>
                    <li>手机:<span>{{ this.UserForm.phone }}</span></li>
                    <li>注册时间:<span>{{ this.UserForm.create_time }}</span></li>
                    <li>账号状态:<span>
                        <el-tag type="success" v-if="this.UserForm.status == 0">正常</el-tag>
                        <el-tag type="warning" v-else>异常</el-tag>
                    </span></li>
                </ul>
            </div>
            <!-- AI用量 -->
            <div>
                <div class="user-component_home_sectionTitle">AI用量<span @click="OpenAIMessageForIntro">详情</span></div>
                <div id="echart-ai_use" style="width: 100%;height:320px;"></div>
            </div>
        </div>
        <div class="user-component_home_broadcast_move">
            <div class="user-component_home_sectionTitle">公告<span>详情</span></div>
            <el-empty></el-empty>
        </div>
        <!-- 右半部分 -->
        <div class="user-component_home_right">
            <!-- 账号安全 -->
            <div>
                <div class="user-component_home_sectionTitle">账号安全<span @click="OpenAccountMore">详情</span></div>
                <ul class="user-component_home_accountSecure">
                    <li>账号：暂无</li>
                    <li>密码：<span @click="EnsureUpdatePwd">修改</span></li>
                    <li>上一次登陆时间：<span style="float: right;color: #2c3e50;">{{ this.LoginHistory[1].login_time }}</span></li>
                    <li>上一次登陆地址：<span style="float: right;color: #2c3e50;">{{ this.LoginHistory[1].city }}</span></li>
                </ul>
            </div>
            <!-- 登陆历史 -->
            <div class="user-component_home_LoginHistory">
                <div class="user-component_home_sectionTitle borderTop">登陆历史</div>
                <ul class="user-component_home_LoginHistory_container">
                    <!-- <li class="user-component_home_LoginHistory_item" v-for="item in LoginHistory" :key="item.time">
                        <div class="ip">{{ item.ip }}</div>
                        <div class="time">{{ item.time }}</div>
                    </li> -->
                    <el-table class="user-component_home_LoginHistory_item" :data="paginatedItems" stripe>
                        <el-table-column
                        prop="login_time"
                        label="日期">
                        </el-table-column>
                        <el-table-column
                        prop="login_ip"
                        label="IP">
                        </el-table-column>
                        <el-table-column
                        prop="city"
                        label="地址">
                        </el-table-column>
                    </el-table>
                    <el-pagination
                    small
                    layout="prev, pager, next"
                    :total="totalItems"
                    :current-page="currentPage"
                    :page-size="itemsPerPage"
                    @current-change="handleCurrentChange"
                    style="float: left;">
                    </el-pagination>
                </ul>
            </div>
        </div>
    </div>
</template>
<script>
import { mapState, mapMutations } from 'vuex';
export default {
    data() {
        return {
            UserForm: {
                nickname: '',
                sno: '',
                name: '',
                belong: '',
                qq: '',
                mail: '',
                phone: '',
                register_time: '',
                status: ''
            },
            LoginHistory: [
                {
                    ip: '124.221.138.245',
                    time: '2024-01-30 16:30:05',
                    address: '杭州'
                },
                {
                    ip: '124.221.138.245',
                    time: '2024-01-30 16:30:05',
                    address: '杭州'
                },
                {
                    ip: '124.221.138.245',
                    time: '2024-01-30 16:30:05',
                    address: '杭州'
                },
                {
                    ip: '124.221.138.245',
                    time: '2024-01-30 16:30:05',
                    address: '杭州'
                }
            ],
            list_notice: [],
            currentPage: 1,
            itemsPerPage: 4,
            totalItems: 0,
        }
    },
    computed: {
        ...mapState(['isPerfect']), // 将isLogined映射为计算属性
        ...mapState(['QQAvatar']),
        paginatedItems() {
            const start = (this.currentPage - 1) * this.itemsPerPage;
            const end = start + this.itemsPerPage;
            return this.LoginHistory.slice(start, end);
        },
    },
    mounted() {
        this.initChart();
    },
    created() {
        this.IsPerfect()
        // console.log("perfect:"+ this.$store.state.isPerfect)
        this.getUserInfo(this.$route.params.sno)
        this.getNoticeTitle()
        // this.getQQAvatar()
        this.getUserLoginInfos()
    },
    methods: {
        ...mapMutations(['SET_IS_PERFECT']),
        ...mapMutations(['SET_ISQQAVATAR']),
        handleCurrentChange(val) {
            this.currentPage = val;
        },
        initChart() {
            var myChart = this.$echarts.init(document.getElementById('echart-ai_use'))
            var option = {
                title: {
                    // text: 'ECharts-AI_use 统计示例'
                },
                tooltip: {},
                legend: {
                    data: ['用量']
                },
                xAxis: {
                    data: ['1-11', '1-12', '1-13', '1-14', '1-15']
                },
                yAxis: {},
                series: [
                    {
                        name: '用量',
                        type: 'line',
                        data: [20, 36, 10, 12, 20]
                    }
                ]
            }
            myChart.setOption(option)
        },
        IsPerfect() {
            const sno = this.$route.params.sno 
            const url =process.env.VUE_APP_BASE_URI + '/shetuan/getInfo'
            fetch(`${url}?sno=${sno}`, {
                method: 'POST',
                headers: {
                    'Accept': 'Application/json',
                    'Content-Type': 'Application/json'
                }
            })
            .then(res => res.json())
            .then(res => {
                // console.log(res.perfect)
                if(res.perfect == 1) {
                    // this.SET_IS_PERFECT(true);
                    // localStorage.set("isPerfect", "true")
                    localStorage.setItem("isPerfect", "true")
                    // console.log("1111")
                }
            })
        },
        getUserInfo(sno) {
            if(this.$store.state.isPerfect == "false" && this.$store.state.isPerfect == '') {
                // console.log("false")
                return
            } else if(this.$store.state.isPerfect == true) {
                // console.log("true")
                // const sno = this.$route.params.sno
                // console.log(sno)
                            //  http://localhost:40125/shetuan-user/getInfo
                const url = process.env.VUE_APP_BASE_URI + '/shetuan-user/getInfo'
                fetch(`${url}?sno=${sno}`, {
                    method: "POST",
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                })
                .then(res => res.json())
                .then(res => {
                    // console.log(res)
                    this.UserForm.nickname = res.nickname
                    this.UserForm.sno = res.sno
                    this.UserForm.name = res.name
                    this.UserForm.belong = res.belong
                    this.UserForm.qq = res.qq
                    this.UserForm.phone = res.phone
                    this.UserForm.mail = res.mail
                    this.UserForm.create_time = res.create_time
                    this.UserForm.status = res.status
                    const url = 'https://api.dzzui.com/api/qqname'
                    fetch(url+'?qq='+res.qq)
                    .then(res => res.json())
                    .then(res => {
                        // console.log(res)
                        this.$store.dispatch('setQQAvatar', res.imgurl)
                        // localStorage.setItem("QQAvatar", res.imgurl)
                    })
                })
            }
        },
        OpenAIMessageForIntro() {
            this.$alert('<div style="padding-right: 0 16px;"><p style="text-indent: 2em">该功能基于讯飞星火的大数据模型 不限次数 为了方便大家使用 就默认用我的码 你的码在你账户的功能>>AI 登陆后用的就是你的了</p><p>( 如果你不在乎使用数据的话 )</p><p style="text-indent: 2em"> 我知道比不上GPT 但那玩意儿有点贵 先用这个凑活 反响不错的话 我会继续加模型 你的支持就是我码代码的动力</p><p style="text-align: right;">Ganxy<br>02-09</span></p>', '详情', {
                dangerouslyUseHTMLString: true
            })
            .catch(() => {
                this.$message({
                    type: 'info',
                    message: '关闭介绍'
                });
            });
        },
        getNoticeTitle() {
            const url = process.env.VUE_APP_BASE_URI + '/gonggao-notice/notice-getTitle'
            fetch(url, {
                method: "POST",
                headers: {
                    'Accept': 'Application/json',
                    'Content-Type': 'Application/json'
                }
            })
            .then(res => res.json())
            .then(res => {
                // console.log(res)

                this.list_notice = res
            })
        },
        getUserLoginInfos() {
            const url = process.env.VUE_APP_BASE_URI + '/user-secure/getAllBySno'
            const sno = this.$route.params.sno
            fetch(`${url}?sno=${sno}`, {
                method: 'POST',
                headers: {
                    'Accept': 'Application/json',
                    'Content-Type': 'Application/json'
                }
            })
            .then(res => {
                if(res.status === 200) {
                    return res.json()
                } else if(res.status === 400) {
                    return res.text()
                }
            })
            .then(res => {
                if(res == 'Sno not found') {
                    this.$message({
                        message: '学号不存在',
                        type: 'warning'
                    })
                } else {
                    this.LoginHistory = res
                    // console.log(this.LoginHistory)
                    this.totalItems = res.length;
                    // console.log(this.totalItems)
                }
            })
        },
        OpenAccountMore() {
            this.$alert('<div style="padding-right: 0 16px;"><p style="text-indent: 2em">本站关于密码的处理是用最新的Sha3-224散列算法加密 不说最安全 也算我在尽量靠谱吧</p><p style="text-indent: 2em">更新密码目前只支持邮箱验证 短信面向开发者收费（就是我要花钱） 邮箱验证是我自己搭的服务器 没多花钱 所以不便之处 还多多担待</p><img style="width: 100%;" src="http://ganxy03.cn/img/xh/联想截图_20240210220806.png"><p style="text-align: right;">Ganxy<br>02-10</span></p>', '详情', {
                dangerouslyUseHTMLString: true
            })
            .catch(() => {
                this.$message({
                    type: 'info',
                    message: '关闭详情'
                });
            });
        },
        sendMail() {
            const url = process.env.VUE_APP_BASE_URI + '/shetuan/send-mail'
            const mail = this.UserForm.mail
            fetch(`${url}?email=${mail}`, {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
            })
            .then(res => res.text())
            .then(res => {
                if(res == 'Send success.') {
                    this.$message({
                        message: '发送成功',
                        type: 'success'
                    })
                } else if(res == 'Send failed.') {
                    this.$message({
                        message: '发送失败',
                        type: 'warning'
                    })
                } else {
                    this.$message({
                        message: '邮箱服务器错误，请联系站点管理员',
                        type: 'warning'
                    })
                }
            })
        },
        verfifyMail(code) {
            const url = process.env.VUE_APP_BASE_URI + '/shetuan/verification-mail'
            const mail = this.UserForm.mail
            fetch(`${url}?email=${mail}&code=${code}`, {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
            })
            .then(res => res.text())
            .then(res => {
                if(res == 'Verification success') {
                    this.$message({
                        message: '验证成功',
                        type: 'success'
                    })
                    this.$prompt('请输入新密码', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                    }).then(({ value }) => {
                        this.UpdatePwd(value)
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: '取消输入'
                        });       
                    });
                } else if(res == 'Verification failed.') {
                    this.$message({
                        message: '验证码错误',
                        type: 'warning'
                    })
                    this.ShowAgainPromt()
                } else {
                    this.$message({
                        message: '邮箱服务器错误，请联系站点管理员',
                        type: 'warning'
                    })
                }
            })
        },
        // 定义错误循环
        ShowAgainPromt() {
            this.$prompt('请输入邮箱验证码', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
            }).then(({ value }) => {
                this.verfifyMail(value)
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '取消输入'
                });       
            });
        },
        UpdatePwd(pwd) {
            const url = process.env.VUE_APP_BASE_URI + '/shetuan/update-pwd'
            const sno = this.$route.params.sno
            fetch(`${url}?sno=${sno}&newPwd=${pwd}`, {
                method: "PUT",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
            })
            .then(res => res.text())
            .then(res => {
                if(res == 'Password updated successfully') {
                    this.$message({
                        message: '更新成功',
                        type: 'success'
                    })
                } else if(res == 'Failed to update password') {
                    this.$message({
                        message: '更新失败',
                        type: 'warning'
                    })
                    this.ShowAgainPromt()
                } else {
                    this.$message({
                        message: '系统错误，请联系站点管理员',
                        type: 'warning'
                    })
                }
            })
        },
        OpenUpdatePwd() {
            this.sendMail()
            

            this.$prompt('请输入邮箱验证码', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
            }).then(({ value }) => {
                this.verfifyMail(value)
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '取消输入'
                });       
            });
        },
        EnsureUpdatePwd() {
            this.$confirm('更新密码需要验证邮箱哦', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.OpenUpdatePwd()
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消更新密码'
                });          
            });
        }
        // getQQAvatar() {
        //     const qq = this.UserForm.qq
        //     console.log(this.UserForm.qq)
        //     const url = 'https://api.dzzui.com/api/qqname'
        //     fetch(url+'?qq='+qq)
        //     .then(res => res.json())
        //     .then(res => {
        //         console.log(res)
        //         localStorage.setItem("QQAvatar", res.imgurl)
        //     })
        // }
    }
}
</script>
<style>
.user-component_home_broadcast_move {
    display: none;
    width: 100%;
    height: 300px;
    /* background-color: brown; */
}
.user-component_home_left{
    float: left;
    width: 56%;
    min-height: 720px;
    /* background-color: blueviolet; */
}
.user-component_home_right {
    float: right;
    width: calc(44% - 1px);
    min-height: 620px;
    border-left: 1px solid #e5e9ef;
    /* background-color: rgb(230, 112, 10); */

}

.user-component_home_basicInfo {
    height: 320px;
    width: 100%;
    /* background-color: aqua; */
}
.user-component_home_sectionTitle {
    font-weight: 700;
    text-align: left;
    padding: 0 20px;
    font-size: 18px;
    margin-top: 20px;
}
.user-component_home_sectionTitle span {
    float: right;
    font-size: 13px;
    font-weight: 400;
    margin-top: 5px;
    cursor: pointer;
    margin: 0 5px;
    margin-top: 5px;
}
.user-component_home_sectionTitle span:hover {
    color: #00a1d6;
}
.user-component_home_basicInfo li {
    list-style: none;
    text-align: left;
    margin: 10px 30px 0 30px;
    padding-bottom: 5px;
    font-size: 14px;
    font-family: '宋体';
    border-bottom: 1px solid #e5e9ef;
}
.user-component_home_basicInfo li span {
    margin-left: 6px;
}

.user-component_home_accountSecure li{
    list-style: none;
    text-align: left;
    margin: 10px 30px 0 30px;
    padding-bottom: 5px;
    font-size: 14px;
    font-family: '宋体';
    border-bottom: 1px solid #e5e9ef;
}
.user-component_home_accountSecure li span {
    cursor: pointer;
    float: right;
    /* font-family: "黑体"; */
}
.user-component_home_accountSecure li span:hover {
    color: #00a1d6;
}

.borderTop {
    margin: 0 20px;
    margin-top: 20px;
    border-top: 1px solid #e5e9ef;
    padding: 0;
    padding-top: 8px;
}
.user-component_home_LoginHistory_container {
    margin-top: 12px;
}
.user-component_home_LoginHistory_item {
    padding: 0 20px;
    width: calc(100% - 20px);
}


@media screen and (max-width: 625px) {
    .user-component_home_left {
        float: none;
        width: 100%;
    }
    .user-component_home_right {
        float: none;
        width: calc(100% - 40px);
        border-top: 1px solid #e5e9ef;
        border-left: none;
        padding: 0;
        margin: 0 20px;
    }
    .user-component_home_broadcast_move {
        display: block;
    }
}
</style>