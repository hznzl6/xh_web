<template>
    <div style="width: 100%;" v-loading="loading">
        <!-- 顶部导航 -->
        <header class="user-top_header">
            <!-- <p>{{ isLogined }}</p>
            <button @click="updateIsLogined">更新登录状态</button> -->
            <a href="/" class="t_slogan fl"></a>
            <a @click="UserNav_move = !UserNav_move" class="t_menu fr"></a>
            <el-dropdown trigger="click" class="user-top_header_avatar">
                <!-- <a class="user-avatar_cover" :style="{backgroundImage: 'url(' + user.avatar_cover + ')'}"></a> -->
                
                <a class="user-avatar_cover" :style="{backgroundImage: 'url(' + QQAvatar + ')'}"></a>
                <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item>主页</el-dropdown-item>
                    <el-dropdown-item>我的消息</el-dropdown-item>
                    <el-dropdown-item divided>
                        <span @click="logout_account">退出账号</span>
                    </el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
            <!-- <span class="user-top_navRight_item" @click="openMessage">快去登陆</span> -->
            <!-- <h2 @click="openMessage">弹框</h2> -->
            
            <!-- 左边导航 -->
            <ul class="user-top_navLeft">
                <li @click="navLinkto(item)" class="user-top_navLeft_item" v-for="item in navLeft" :key="item.title">{{ item.title }}</li>
            </ul>
            <!-- 右边导航 -->
            <ul class="user-top_navRight">
                
            </ul>

            <!-- 移动端导航 -->
            <el-drawer
            title="互联网信息协会"
            :visible.sync="UserNav_move"
            direction="rtl"
            :before-close="handleClose"
            size="60%">
            <ul>
                <li @click="navLinkto(item)" style="padding: 10px 12px 0;margin: 10px 0;text-align: left;" v-for="item in navLeft" :key="item.title">{{ item.title }}</li>
            </ul>
            </el-drawer>

            <!-- 原登陆蒙版 -->
            <!-- <div v-if="show" class="modal" @click.self="closeMessage">
                <div class="modal-content">
                    <i @click="offMessage" class="user-icon_cancel"></i>
                    <div :class="{'card': true, 'flipped': isFlipped}">
                        <div class="component-Login">
                            <span @click="toggleFlip" style="cursor: pointer;color: #00A1D6;font-size: 13px;">没有账号？去注册</span>
                            <el-card>
                                <component :is="LoginView"></component>
                            </el-card>
                        </div>
                        <div class="component-Register">
                            <span @click="toggleFlip" style="cursor: pointer;color: #00A1D6;font-size: 13px;">有账号了？去登陆</span>
                            <el-card>
                                <component :is="RegisterView"></component>
                            </el-card>
                        </div>
                    </div>
                    <p style="font-size: 11px;">登陆或完成注册即代表你同意<span style="color: #00A1D6;cursor: pointer;">用户协议</span>和<span style="color: #00A1D6;cursor: pointer;">隐私政策</span></p>
                </div>
            </div> -->
            <div v-if="show" class="User-modal">
                <div class="User-modal-content">
                    <!-- pc端 -->
                    <h2>【提示】个人信息完善</h2>
                    <div class="User-Perfect_pc">
                        <el-steps :active="StepActive" align-center finish-status="success">
                            <el-step title="步骤 1" description="补充必要的个人信息 务必真实哦"></el-step>
                            <el-step title="步骤 2" description="确认信息"></el-step>
                        </el-steps>
                        <div v-show="StepActive == 0">
                            <div class="User-Perfect_pc_left">
                                <p class="User-Perfect_pc_inputTitle">学号:</p>
                                <el-input disabled v-model="PerfectInfo.sno" class="User-Perfect_pc_input" placeholder="输入你的学号"></el-input>
                                <p class="User-Perfect_pc_inputTitle">昵称:</p>
                                <el-input v-model="PerfectInfo.nickname" class="User-Perfect_pc_input" placeholder="输入你的昵称"></el-input>
                                <p class="User-Perfect_pc_inputTitle">姓名:</p>
                                <el-input v-model="PerfectInfo.name" class="User-Perfect_pc_input" placeholder="输入你的姓名"></el-input>
                                <p class="User-Perfect_pc_inputTitle">班级:</p>
                                <!-- <el-input v-model="PerfectInfo.belong" class="User-Perfect_pc_input" placeholder="输入你的班级"></el-input> -->
                                <el-autocomplete v-model="PerfectInfo.belong" class="User-Perfect_pc_input" placeholder="输入你的班级（例:22级计应3班）" :fetch-suggestions="querySearch" @select="handleSelect">
                                
                                </el-autocomplete>
                            </div>
                            <div class="User-Perfect_pc_right">
                                <p class="User-Perfect_pc_inputTitle">QQ:</p>
                                <el-input v-model="PerfectInfo.qq" class="User-Perfect_pc_input" placeholder="输入你的qq"></el-input>
                                <p class="User-Perfect_pc_inputTitle">手机:</p>
                                <el-input v-model="PerfectInfo.phone" class="User-Perfect_pc_input" placeholder="输入你的手机" :disabled="perfet_havePhone"></el-input>
                                <p class="User-Perfect_pc_inputTitle">邮箱:</p>
                                <el-input v-model="PerfectInfo.mail" class="User-Perfect_pc_input" placeholder="输入你的邮箱" :disabled="perfet_haveMail">
                                    <template slot="append">@qq.com</template>
                                </el-input>
                            </div>
                            <div class="User-Perfect_pc_btn">
                                <el-button @click="nextStep">下一步</el-button>
                            </div>
                        </div>
                        <div v-show="StepActive == 1">
                            <div class="User-Perfect_pc_left">
                                <p class="User-Perfect_pc_inputTitle">学号:</p>
                                <el-input v-model="PerfectInfo.sno" disabled class="User-Perfect_pc_input" placeholder="输入你的学号"></el-input>
                                <p class="User-Perfect_pc_inputTitle">昵称:</p>
                                <el-input v-model="PerfectInfo.name" disabled class="User-Perfect_pc_input" placeholder="输入你的昵称"></el-input>
                                <p class="User-Perfect_pc_inputTitle">姓名:</p>
                                <el-input v-model="PerfectInfo.nickname" disabled class="User-Perfect_pc_input" placeholder="输入你的姓名"></el-input>
                                <p class="User-Perfect_pc_inputTitle">班级:</p>
                                <el-input v-model="PerfectInfo.belong" disabled class="User-Perfect_pc_input" placeholder="输入你的班级"></el-input>
                            </div>
                            <div class="User-Perfect_pc_right">
                                <p class="User-Perfect_pc_inputTitle">QQ:</p>
                                <el-input v-model="PerfectInfo.qq" disabled class="User-Perfect_pc_input" placeholder="输入你的qq">{{ PerfectInfo.qq }}</el-input>
                                <p class="User-Perfect_pc_inputTitle">手机:</p>
                                <el-input v-model="PerfectInfo.phone" disabled class="User-Perfect_pc_input" placeholder="输入你的手机">{{ PerfectInfo.phone }}</el-input>
                                <p class="User-Perfect_pc_inputTitle">邮箱:</p>
                                <el-input v-model="PerfectInfo.mail" disabled class="User-Perfect_pc_input" placeholder="输入你的邮箱">{{ PerfectInfo.mail }}</el-input>
                            </div>
                            <div class="User-Perfect_pc_btn">
                                <el-button disabled @click="previousStep">上一步</el-button>
                                <el-button @click="savePerfet">保存</el-button>
                            </div>
                        </div>
                    </div>
                    <!-- 移动端 -->
                    <div class="User-Perfect_move">
                        <el-steps :active="StepActive" align-center finish-status="success">
                            <el-step title="步骤 1" description="补充必要的个人信息 务必真实哦"></el-step>
                            <el-step title="步骤 2" description="确认信息"></el-step>
                        </el-steps>
                        <div v-show="StepActive == 0">
                            <p class="User-Perfect_move_inputTitle">学号:</p>
                            <el-input disabled v-model="PerfectInfo.sno" class="User-Perfect_move_input" placeholder="输入你的学号"></el-input>
                            <p class="User-Perfect_move_inputTitle">昵称:</p>
                            <el-input v-model="PerfectInfo.nickname" class="User-Perfect_move_input" placeholder="输入你的昵称" maxlength="12"></el-input>
                            <p class="User-Perfect_move_inputTitle">姓名:</p>
                            <el-input v-model="PerfectInfo.name" class="User-Perfect_move_input" placeholder="输入你的姓名" maxlength="5"></el-input>
                            <p class="User-Perfect_move_inputTitle">班级:</p>
                            <!-- <el-input v-model="PerfectInfo.belong" class="User-Perfect_move_input" placeholder="输入你的班级"></el-input> -->
                            <el-autocomplete v-model="PerfectInfo.belong" class="User-Perfect_move_input" placeholder="输入你的班级（例:22级计应3班）" :fetch-suggestions="querySearch" @select="handleSelect">
                                
                            </el-autocomplete>

                            <p class="User-Perfect_move_inputTitle">QQ:</p>
                            <el-input v-model="PerfectInfo.qq" class="User-Perfect_move_input" placeholder="输入你的qq" maxlength="13"></el-input>
                            <p class="User-Perfect_move_inputTitle">手机:</p>
                            <el-input :disabled="perfet_havePhone" v-model="PerfectInfo.phone" class="User-Perfect_move_input" placeholder="输入你的手机" maxlength="11"></el-input>
                            <p class="User-Perfect_move_inputTitle">邮箱:</p>
                            <el-input :disabled="perfet_haveMail" v-model="PerfectInfo.mail" class="User-Perfect_move_input" placeholder="输入你的邮箱"></el-input>
                            <div class="User-Perfect_move_btn">
                                <el-button @click="nextStep">下一步</el-button>
                            </div>
                        </div>
                        <div v-show="StepActive == 1">
                            <p class="User-Perfect_move_inputTitle">学号:</p>
                            <el-input disabled v-model="PerfectInfo.sno" class="User-Perfect_move_input" placeholder="输入你的学号"></el-input>
                            <p class="User-Perfect_move_inputTitle">昵称:</p>
                            <el-input disabled v-model="PerfectInfo.nickname" class="User-Perfect_move_input" placeholder="输入你的昵称"></el-input>
                            <p class="User-Perfect_move_inputTitle">姓名:</p>
                            <el-input disabled v-model="PerfectInfo.name" class="User-Perfect_move_input" placeholder="输入你的姓名"></el-input>
                            <p class="User-Perfect_move_inputTitle">班级:</p>
                            <el-input disabled v-model="PerfectInfo.belong" class="User-Perfect_move_input" placeholder="输入你的班级"></el-input>

                            <p class="User-Perfect_move_inputTitle">QQ:</p>
                            <el-input disabled v-model="PerfectInfo.qq" class="User-Perfect_move_input" placeholder="输入你的qq"></el-input>
                            <p class="User-Perfect_move_inputTitle">手机:</p>
                            <el-input disabled v-model="PerfectInfo.phone" class="User-Perfect_move_input" placeholder="输入你的手机"></el-input>
                            <p class="User-Perfect_move_inputTitle">邮箱:</p>
                            <el-input disabled v-model="PerfectInfo.mail" class="User-Perfect_move_input" placeholder="输入你的邮箱"></el-input>
                            <div class="User-Perfect_move_btn">
                                <el-button disabled @click="previousStep">上一步</el-button>
                                <el-button @click="savePerfet">保存</el-button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        <div class="user-pure_container">
            <div class="user-main_container">
                <!-- 内嵌背景 -->
                <div class="user-inliner_banner">
                    <div class="user-inliner_info">
                        <a class="user-avatar_cover" :style="{backgroundImage: 'url(' + QQAvatar + ')'}"></a>
                        <div class="user-basic_info">
                            <p class="user_name">{{ this.user.name }}</p>
                            <p class="user_info">{{ this.user.info }}</p>
                        </div>
                    </div>
                </div>
                <!-- 二级导航 -->
                <div class="user-inliner_nav">
                    <!-- <a class="user-inliner_navItem_icon" :style="{backgroundImage: 'url(' + user.avatar_cover + ')'}"></a> -->
                    <li class="user-inliner_navItem" v-for="item in inliner_nav" :key="item.name" :class="{'active': isActive(item.id)}" @click="activate(item.id)">
                        <a class="user-inliner_navItem_icon" :class="getIcon(item)"></a>
                        <span class="user-inliner_navItem_name">{{ item.name }}</span>
                    </li>
                </div>
                <!-- 导航内容 局部加载 -->
                <div class="user-inliner_content">
                    <!-- 左边部分 -->
                    <div class="user-inliner_content_leftMain">
                        <!-- 主页 社团 功能 设置组件 -->
                        <div>
                            <UserHome v-show="activeIndex == '0'"/>
                            <UserMass v-show="activeIndex == '1'"/>
                            <UserFunc v-show="activeIndex == '2'"/>
                            <UserSeti v-show="activeIndex == '3'"/>
                        </div>
                    </div>
                    <!-- 右半部分 侧边栏 -->
                    <div class="user-inliner_content_rightSideBar">
                        <!-- 申请卡片 -->
                        <div class="user-rightSideBar_application">
                            <div class="user-application_icon user_i_auth"></div>
                            <div class="user-application_title">
                                <p>点此申请加入社团</p>
                                <span>加入社团 关注更多消息</span>
                            </div>
                        </div>
                        <!-- 活动卡片 -->
                        <div class="user-rightSideBar_activity">
                            <div class="user-rightSideBar_activity_title">
                                <h2 @click="ActivityCenter"><i class="user-icon-activity"></i>活动中心<i class="user-icon-r_arrow"></i></h2>
                            </div>
                            <div class="user-rightSideBar_activity_BtnGroup">
                                <div class="user-rightSideBar_activity_btn">我的活动</div>
                                <div @click="OpenDialogMyCheck" class="user-rightSideBar_activity_btn">我的审核</div>
                            </div>
                        </div>
                        <!-- 公告卡片 -->
                        <div class="user-rightSideBar_broadcast">
                            <div class="user-rightSideBar_section">公告</div>
                        </div>
                        <!-- 个人信息卡片 -->
                        <!-- <div class="user-rightSideBar_personalInfo">
                            <div class="user-rightSideBar_section">个人资料</div>
                        </div> -->
                    </div>
                </div>
            </div>

            <el-dialog title="我的审核" :visible.sync="dialogMyCheck">
                <el-table style="height: 550px;overflow-y: auto;" :data="MyCheckData">
                    <el-table-column prop="apply_time" label="日期"></el-table-column>
                    <el-table-column prop="mass" label="社团"></el-table-column>
                    <el-table-column prop="info" label="内容"></el-table-column>
                    <el-table-column prop="status" label="状态">
                        <template slot-scope="scope">
                            <el-tag
                            v-if="scope.row.status === 0"
                            type="warning"
                            >
                            待审核
                            </el-tag>
                            <el-tag
                            v-else-if="scope.row.status === 1"
                            type="success"
                            >
                            通过
                            </el-tag>
                            <el-tag
                            v-else-if="scope.row.status === 2"
                            type="danger"
                            >
                            驳回
                            </el-tag>
                        </template>
                    </el-table-column>
                </el-table>
            </el-dialog>

            <el-dialog title="活动中心" :visible.sync="dialogActivityCenter">
                <el-table style="height: 550px;overflow-y: auto;" :data="allActivity">
                    <el-table-column prop="event" label="活动名称"></el-table-column>
                    <el-table-column prop="mass" label="社团"></el-table-column>
                    <el-table-column prop="describes" label="介绍"></el-table-column>
                    <el-table-column prop="start_time" label="开始时间"></el-table-column>
                    <el-table-column prop="start_time" label="结束时间"></el-table-column>
                    <el-table-column prop="status" label="状态">
                        <template>
                            <el-tag type="info">即将开始</el-tag>
                            <el-tag type="info">已结束</el-tag>
                            <el-tag type="success">进行中</el-tag>
                            <el-tag type="info">未报名</el-tag>
                            <el-tag type="success">已报名</el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column
                    label="操作">
                    <template>
                        <div>
                            <el-button style="width: 100%;text-align: center;margin: 0;padding: 0;" type="text" size="small">查看</el-button>
                            <el-button style="width: 100%;text-align: center;margin: 0;padding: 0;" type="text" size="small">报名</el-button>
                        </div>
                    </template>
                    </el-table-column>
                </el-table>
            </el-dialog>

        </div>
    </div>
</template>
<script>
import { mapState, mapMutations } from 'vuex';

import UserHome from "@/views/shetuan/User/HomeView.vue"
import UserMass from "@/views/shetuan/User/MassView.vue"
import UserFunc from "@/views/shetuan/User/FunctionView.vue"
import UserSeti from "@/views/shetuan/User/SetingView.vue"

export default {
    components: {
        UserHome,
        UserMass,
        UserFunc,
        UserSeti,
    },
    data() {
        return {
            navLeft: [{
            title: '首页',
            link: '/'
            },
            {
            title: '部门概况',
            link: '/bmgk/部门简介'
            },
            {
            title: '服务指南'
            },
            {
            title: '规章制度'
            },
            {
            title: '信息公示'
            },
            {
            title: '下载专区'
            },
            {
            title: '联系我们',
            link: '/bmgk/联系我们'
            }
            ],
            user: {
                name: '测试账号（限制12字）',
                // avatar_cover: 'http://ganxy03.cn/img/noface.png',
                avatar_cover: '',
                info: '测试数据（限制16字）',
                // avatar_cover: 'http://ganxy03.cn/img/avatar.png'
            },
            inliner_nav: [
                {
                    id: 0,
                    name: '主页',
                    icon: 'user-i_home',
                },
                {
                    id: 1,
                    name: '社团',
                    icon: 'user-i_mass',
                },
                {
                    id: 2,
                    name: '功能',
                    icon: 'user-i_function',
                },
                {
                    id: 3,
                    name: '设置',
                    icon: 'user-i_seting',
                }
            ],
            activeIndex: 0,
            loading: false,
            show: true,
            rotate: false,
            isFlipped: false,
            UserNav_move: false,
            StepActive: 0,
            PerfectInfo: {
                sno: '',
                nickname: '',
                name: '',
                belong: '',
                qq: '',
                phone: '',
                mail: ''
            },
            dialogMyCheck: false,
            MyCheckData: [],
            perfet_haveMail: false,
            perfet_havePhone: false,
            Allbelong: [
                {
                    value: '22级计应3班'
                },
                {
                    value: '23级计应3班'
                },
            ],
            inputCode: '',
            dialogActivityCenter: false,
            allJoinMass: [],
            allActivity: []
        }
    },
    computed: {
        ...mapState(['isLogined']), // 将isLogined映射为计算属性
        ...mapState(['QQAvatar'])
    },
    created() {
        // this.getUserInfo(this.$route.params.sno)
        this.VerifyLogin()
        // console.log(this.$store.state.isLogined)
        this.GetAllCheckData()
        this.IsPerfect()
        this.PerfectInfo.sno = this.$route.params.sno
        this.getPerfectData()
        this.getAllJoinMass()
    },
    mounted() {
        setTimeout(() => {
            this.loading = false
        }, 1200);
    },
    methods: {
        ...mapMutations(['SET_IS_LOGINED']), // 将SET_IS_LOGINED映射为方法
        updateIsLogined() {
        // 调用SET_IS_LOGINED方法更新isLogined的值
            this.SET_IS_LOGINED(true); // 假设设置为true表示已登录
            localStorage.setItem("isLogined", "true")
            console.log(this.$store.state.isLogined)
        },
        getIcon(item) {
            return item.icon;
        },
        isActive(index) {
            return this.activeIndex === index;
        },
        activate(index) {
            this.activeIndex = index;
        },
        openMessage() {
            this.show = true
        },
        offMessage() {
            this.show = false
        },
        handleClose(done) {
            done();
        },
        previousStep() {
            this.StepActive = this.StepActive-1
        },
        getPerfectData() {
            const url = process.env.VUE_APP_BASE_URI + '/shetuan/getInfo'
            const sno = this.PerfectInfo.sno
            console.log(sno)
            fetch(`${url}?sno=${sno}`, {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            })
            .then(res => res.json())
            .then(res => {
                // console.log(res)
                if(res.mail != null) {
                    this.perfet_haveMail = true
                    this.PerfectInfo.mail = res.mail
                }
                if(res.phone != null) {
                    this.perfet_havePhone = true
                    this.PerfectInfo.phone = res.phone
                }
            })
        },
        SendMail() {
            const mail = this.PerfectInfo.mail + "@qq.com"
            const url = process.env.VUE_APP_BASE_URI + '/shetuan/send-mail'
            fetch(`${url}?email=${mail}`, {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-type': 'application/json'
                },
            })
            .then(res => res.text())
            .then(res => {
                if(res == 'Send success.') {
                    this.$message({
                        message: '发送成功',
                        type: 'success'
                    })
                }
            })
        },
        SendMsm() {
            const phone = this.PerfectInfo.phone
            const url = process.env.VUE_APP_BASE_URI + '/Tencent-sms/Send-sms'
            fetch(`${url}?phone=${phone}`, {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application'
                }
            })
            .then(res => res.text())
            .then(res => {
                if(res == 'Send success') {
                    this.$message({
                        message: '发送成功，注意查收',
                        type: 'success'
                    })
                } else if(res == 'Send failed') {
                    this.$message({
                        message: '手机号码格式不对哦',
                        type: 'warning'
                    })
                } else {
                    this.$message({
                        message: '接口错误 请告知站点管理员',
                        type: 'warning'
                    })
                }
            })
        },
        VerifyPhone() {
            this.$message({
                message: '发送成功 注意查收',
                type: 'success'
            })
        },
        nextStep() {
            if(this.PerfectInfo.nickname == '') {
                this.$message({
                    message: '昵称没填哦',
                    type: 'warning'
                })
            } else if(this.PerfectInfo.name == '') {
                this.$message({
                    message: '名字没填哦',
                    type: 'warning'
                })
            } else if(this.PerfectInfo.belong == '') {
                this.$message({
                    message: '班级没填哦',
                    type: 'warning'
                })
            } else if(this.PerfectInfo.qq == '') {
                this.$message({
                    message: 'QQ没填哦',
                    type: 'warning'
                })
            } else if(this.PerfectInfo.phone == '') {
                this.$message({
                    message: '手机没填哦',
                    type: 'warning'
                })
            }  else if(this.PerfectInfo.mail == '') {
                this.$message({
                    message: '邮箱没填哦',
                    type: 'warning'
                })
            } else {
                // console.log(this.PerfectInfo)
                if(this.perfet_havePhone) {
                    // this.$prompt('请验证邮箱', '提示', {
                    // confirmButtonText: '确定',
                    // cancelButtonText: '取消',
                    // }).then(({ value }) => {
                    //     console.log(value)
                    //     this.$message({
                    //         type: 'success',
                    //         message: '验证成功'
                    //     });
                    // }).catch(() => {
                    //     this.$message({
                    //         type: 'info',
                    //         message: '取消验证'
                    //     });       
                    // });
                    this.$confirm(this.PerfectInfo.mail+'@qq.com是你的邮箱吗?点击 确认 验证邮箱', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        // this.SendMsm()
                        this.SendMail()
                        this.$prompt('请输入收到的验证码', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        }).then(({ value }) => {
                            // this.$message({
                            //     type: 'success',
                            //     message: '你的验证码是: ' + value
                            // });
                            this.inputCode = value




                            // 定义的错误循环 promt
                            const showPrompt = () => {
                                this.$prompt('请输入收到的验证码', '提示', {
                                    confirmButtonText: '确定',
                                    cancelButtonText: '取消',
                                }).then(({ value }) => {
                                    // 其他逻辑
                                    this.inputCode = value

                                    const mail = this.PerfectInfo.mail + "@qq.com"
                                    const url = process.env.VUE_APP_BASE_URI +  '/shetuan/verification-mail'
                                    const code = this.inputCode
                                    // console.log(phone)
                                    // console.log(url)
                                    // console.log(code)
                                    fetch(`${url}?code=${code}&email=${mail}`, {
                                        method: 'POST',
                                        headers: {
                                            'Accept': 'application/json',
                                            'Content-Type': 'application'
                                        }
                                    })
                                    .then(res => res.text())
                                    .then(res => {
                                        // console.log(res)
                                        if(res == 'Verification success') {
                                            this.$message({
                                                type: 'success',
                                                message: '验证成功'
                                            });
                                            this.StepActive = this.StepActive+1
                                        } else if (res == 'Verification failed.') {
                                            this.$message({
                                                type: 'warning',
                                                message: '验证码错误'
                                            });
                                            setTimeout(() => {
                                                showPrompt();
                                            }, 1000);
                                        }
                                    })




                                }).catch(() => {
                                    this.$message({
                                        type: 'info',
                                        message: '已取消验证'
                                    });
                                });
                            };

                            
                            const mail = this.PerfectInfo.mail + "@qq.com"
                            const url = process.env.VUE_APP_BASE_URI +  '/shetuan/verification-mail'
                            const code = this.inputCode
                            // console.log(phone)
                            // console.log(url)
                            // console.log(code)
                            fetch(`${url}?code=${code}&email=${mail}`, {
                                method: 'POST',
                                headers: {
                                    'Accept': 'application/json',
                                    'Content-Type': 'application'
                                }
                            })
                            .then(res => res.text())
                            .then(res => {
                                // console.log(res)
                                if(res == 'Verification success') {
                                    this.$message({
                                        type: 'success',
                                        message: '验证成功'
                                    });
                                    this.StepActive = this.StepActive+1
                                } else if (res == 'Verification failed.') {
                                    this.$message({
                                        type: 'warning',
                                        message: '验证码错误'
                                    });
                                    setTimeout(() => {
                                        showPrompt();
                                    }, 1000);
                                }
                            })
                        })
                        .catch(() => {
                            this.$message({
                                type: 'warning',
                                message: '验证接口错误,请联系站点管理员'
                            });
                        });
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: '已取消验证'
                        });          
                    });
                }
                if(this.perfet_haveMail) {
                    // this.$confirm(this.PerfectInfo.phone+'是你的手机吗?点击 确认 验证手机号', '提示', {
                    //     confirmButtonText: '确定',
                    //     cancelButtonText: '取消',
                    //     type: 'warning'
                    // })
                    // .then(() => {
                    //     this.$prompt("请输入收到的验证码", "提示", {
                    //         confirmButtonText: '确定',
                    //         cancelButtonText: '取消',
                    //     })
                    //     .then(({value}) => {
                    //         this.$message({
                    //             message: '你的验证码是：'+ value,
                    //             type: 'success'
                    //         })
                    //     })
                    // })



                    this.$confirm(this.PerfectInfo.phone+'是你的手机吗?点击 确认 验证手机号', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        this.SendMsm()
                        this.$prompt('请输入收到的验证码', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        }).then(({ value }) => {
                            // this.$message({
                            //     type: 'success',
                            //     message: '你的验证码是: ' + value
                            // });
                            this.inputCode = value




                            // 定义的错误循环 promt
                            const showPrompt = () => {
                                this.$prompt('请输入收到的验证码', '提示', {
                                    confirmButtonText: '确定',
                                    cancelButtonText: '取消',
                                }).then(({ value }) => {
                                    // 其他逻辑
                                    this.inputCode = value

                                    const phone = this.PerfectInfo.phone
                                    const url = process.env.VUE_APP_BASE_URI + '/Tencent-sms/Verify-sms'
                                    const code = this.inputCode
                                    console.log(phone)
                                    console.log(url)
                                    console.log(code)
                                    fetch(`${url}?code=${code}&phone=${phone}`, {
                                        method: 'POST',
                                        headers: {
                                            'Accept': 'application/json',
                                            'Content-Type': 'application'
                                        }
                                    })
                                    .then(res => res.text())
                                    .then(res => {
                                        // console.log(res)
                                        if(res == 'Verify success') {
                                            this.$message({
                                                type: 'success',
                                                message: '验证成功'
                                            });
                                            this.StepActive = this.StepActive+1
                                        } else if (res == 'Verify failed') {
                                            this.$message({
                                                type: 'warning',
                                                message: '验证码错误'
                                            });
                                            setTimeout(() => {
                                                showPrompt();
                                            }, 1000);
                                        }
                                    })




                                }).catch(() => {
                                    this.$message({
                                        type: 'info',
                                        message: '已取消验证'
                                    });
                                });
                            };

                            
                            const phone = this.PerfectInfo.phone
                            const url = process.env.VUE_APP_BASE_URI + '/Tencent-sms/Verify-sms'
                            const code = this.inputCode
                            console.log(phone)
                            console.log(url)
                            console.log(code)
                            fetch(`${url}?code=${code}&phone=${phone}`, {
                                method: 'POST',
                                headers: {
                                    'Accept': 'application/json',
                                    'Content-Type': 'application'
                                }
                            })
                            .then(res => res.text())
                            .then(res => {
                                // console.log(res)
                                if(res == 'Verify success') {
                                    this.$message({
                                        type: 'success',
                                        message: '验证成功'
                                    });
                                    this.StepActive = this.StepActive+1
                                } else if (res == 'Verify failed') {
                                    this.$message({
                                        type: 'warning',
                                        message: '验证码错误'
                                    });
                                    setTimeout(() => {
                                        showPrompt();
                                    }, 1500);
                                }
                            })
                        })
                        .catch(() => {
                            this.$message({
                                type: 'warning',
                                message: '验证接口错误,请联系站点管理员'
                            });
                        });
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: '已取消验证'
                        });          
                    });
                }
            }
            
        },
        savePerfet() {
            // console.log(this.PerfectInfo)
            const value = {
                belong: this.PerfectInfo.belong,
                mail: this.PerfectInfo.mail + "@qq.com",
                name: this.PerfectInfo.name,
                nickname: this.PerfectInfo.nickname,
                phone: this.PerfectInfo.phone,
                qq: this.PerfectInfo.qq,
                sno: this.$route.params.sno
            }
            // console.log(value)
            const url = process.env.VUE_APP_BASE_URI + '/shetuan-user/perfect'
            fetch(url, {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(value)
            })
            .then(response => response.text())
            .then(res => {
                if(res == 'Perfect success!') {
                    this.$message({
                        message: '完善成功',
                        type: 'success'
                    })
                } else if (res == 'Sno not found') {
                    this.$message({
                        message: '学号不存在',
                        type: 'warning'
                    })
                }
                this.getUserInfo(this.$route.params.sno)
                setTimeout(() => {
                    location.reload()
                }, 1000);
                
            })
            .catch(error => {
                console.log(error)
                this.$message({
                    message: '接口错误，请联系管理员',
                    type: 'warning'
                })
            })
        },

        logout_account() {
            const sno = localStorage.getItem('Sno')
            const url = process.env.VUE_APP_BASE_URI + '/shetuan/logout'
            fetch(`${url}?sno=${sno}`, {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
            })
            .then(response => {
                if (response.status === 200) {
                    return response.text();
                }
            })
            .then(res => {
                // console.log(res)
                if(res == 'Logout success!') {
                    this.$message({
                        message: '登出成功',
                        type: 'success'
                    })
                    localStorage.setItem("isLogined", "false")
                    localStorage.removeItem("LoginToken")
                    localStorage.removeItem("Sno")
                    localStorage.removeItem("isLogined")
                    localStorage.removeItem("isPerfect")
                    this.$router.push("/");
                }
            })
        },
        VerifyLogin() {
            const value = {
                sno: localStorage.getItem("Sno"),
                token: localStorage.getItem("LoginToken")
            }
            const url= process.env.VUE_APP_BASE_URI + "/shetuan/verification-login"
            // this.$axios.post(url, { sno: '202280310314', token: value.token }, {
            //     headers: { 'Content-Type': 'application/json' }
            // })
            // .then(response => response.data)
            // .then(res => {
            //     if (res === 'Verification success!') {
            //         console.log("Auth pass");
            //     }
            // })
            // .catch(error => {
            //     console.error(error);
            // });
            fetch(`${url}?sno=${value.sno}&token=${value.token}`, {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            })
            .then(response => response.text())
            .then(res => {
                // console.log(res)
                if(res == 'Verification success!') {
                    console.log("Auth pass")
                    // localStorage.removeItem("Sno")
                }
            })
        },
        // getUserInfo(sno) {
        //     if(this.$store.state.isPerfect == "false" && this.$store.state.isPerfect == '') {
        //         console.log("false")
        //         return
        //     } else if(this.$store.state.isPerfect == true) {
        //         const url = process.env.API_BASE_URL + '/shetuan-user/getInfo'
        //         fetch(`${url}?sno=${sno}`, {
        //             method: "POST",
        //             headers: {
        //                 'Accept': 'application/json',
        //                 'Content-Type': 'application/json'
        //             },
        //         })
        //         .then(res => res.json())
        //         .then(res => {
        //             console.log(res)
        //             this.UserForm.nickname = res.nickname
        //             this.UserForm.sno = res.sno
        //             this.UserForm.name = res.name
        //             this.UserForm.belong = res.belong
        //             this.UserForm.qq = res.qq
        //             this.UserForm.phone = res.phone
        //             this.UserForm.mail = res.mail
        //             this.UserForm.create_time = res.create_time
        //             this.UserForm.status = res.status
        //         })
        //     }
            
        // },
        // getUserInfo(sno) {
        //     // const sno = localStorage.getItem("Sno")
        //     const url = 'http://localhost:40125/shetuan-user/getInfo'
        //     fetch(`${url}?sno=${sno}`, {
        //         method: "POST",
        //         headers: {
        //             'Accept': 'application/json',
        //             'Content-Type': 'application/json'
        //         }
        //     })
        //     .then(res => res.json())
        //     .then(res => {
        //         console.log(res)
        //     })
        // },
        OpenDialogMyCheck() {
            this.dialogMyCheck = true
        },
        GetAllCheckData() {
            const sno = this.$route.params.sno
            const url = process.env.VUE_APP_BASE_URI + '/shetuan-apply/apply-getAllCheckBySno'
            fetch(`${url}?sno=${sno}`, {
                method: 'POST',
                headers: {
                    'Accept': 'Application/json',
                    'Content-Type': 'Application/json'
                }
            })
            .then(res => res.json())
            .then(res => {
                this.MyCheckData = res
            })
        },
        IsPerfect() {
            const sno = this.$route.params.sno
            const url = process.env.VUE_APP_BASE_URI + '/shetuan/getInfo'
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
                // console.log(res)
                if(this.$route.params.sno !== localStorage.getItem("Sno")) {
                    this.$message({
                        message: '异常操作',
                        type: 'warning'
                    })
                    localStorage.setItem("isLogined", "false")
                    localStorage.removeItem("LoginToken")
                    localStorage.removeItem("Sno")
                    this.$router.push("/");
                    return
                }
                if(res == 'Sno not found') {
                    this.$message({
                        message: '学号未注册',
                        type: 'warning'
                    })
                    localStorage.setItem("isLogined", "false")
                    localStorage.removeItem("LoginToken")
                    localStorage.removeItem("Sno")
                    this.$router.push("/");
                    return
                }
                if(res.perfect == 1) {
                    this.show = false
                }
                
            })
        },
        // navLinkto(link) {
        //     // console.log(link)
        //     this.$router.push(link)
        // },
        navLinkto(item) {
          console.log(item)
          if(item.title === "服务指南" || item.title === "规章制度" || item.title === "信息公示" || item.title === "下载专区") {
            this.$message({
              message: '导航没内容哦',
              type: 'warning'
            })
            return
          }
          this.$router.push(item.link);
          setTimeout(function() {
            location.reload();
          }, 200);
        },
        querySearch(queryString, cb) {
            var belongs = this.Allbelong;
            var results = queryString ? belongs.filter(this.createFilter(queryString)) : belongs;
            // 调用 callback 返回建议列表的数据
            cb(results);
        },
        createFilter(queryString) {
            return (Allbelong) => {
                return (Allbelong.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
            };
        },
        handleSelect(item) {
            console.log(item);
        },
        ActivityCenter() {
            this.dialogActivityCenter = true
        },
        getAllJoinMass() {
            const sno = this.$route.params.sno
            const url = process.env.VUE_APP_BASE_URI + '/shetuan-apply/apply-getAllJoinBySno'
            fetch(`${url}?sno=${sno}`, {
                method: "POST",
                headers: {
                    'Accept': 'Application/json',
                    'Content-Type': 'Application/json'
                }
            })
            .then(res => res.json())
            .then(res => {
                this.allJoinMass = res.map(item => item.mass)

                const url2 = process.env.VUE_APP_BASE_URI + '/shetuan-activity/getAllEventByMassAndStatus'
                // const mass = this.allJoinMass[0]
                 // 使用 Promise.all 来处理每个 mass 的请求
                Promise.all(this.allJoinMass.map(mass => {
                    return fetch(`${url2}?mass=${mass}`, {
                        method: "POST",
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json'
                        }
                    }).then(res => res.json());
                })).then(responses => {
                    // 将所有请求的结果拼接到 this.allActivity 中
                    this.allActivity = [];
                    responses.forEach(response => {
                        this.allActivity = this.allActivity.concat(response);
                    });
                    // console.log(this.allActivity);
                });
            })
        }
    }
}

</script>
<style>
.user-top_header {
    height: 60px;
    width: 100%;
    background-color: #fff;
    border-bottom: 1px solid #a0a3a8;
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
}
.user-top_header .t_slogan, .t_menu {
  width: 60px;
  height: 60px;
}
.user-top_header .t_menu {
    background: url(../../../assets/img/user/icon/i_menu.png) 50% no-repeat;
    /* margin-right: 8px; */
    display: none;
}
.user-top_header_avatar {
    width: 50px;
    height: 50px;
    float: right;
    border-radius: 50%;
    margin-top: 5px;
    margin-right: 20px;
    cursor: pointer;
    background-size: cover;
}
.user-top_navLeft {
    display: flex;
    justify-content: flex-start;
    align-items: center;
}
.user-top_navLeft_item {
    display: inline-flex;
    list-style: none;
    font-size: 16px;
    padding: 0 8px;
    line-height: 60px;
    cursor: pointer;
}
.user-top_navLeft .user-top_navLeft_item:hover {
    color: #00a1d6;
    border-bottom: 2px solid #00a1d6;
}
.user-top_navRight {
    display: flex;
    justify-content: flex-end;
    align-items: center;
}
.user-top_navRight_item {
    float: right;
    line-height: 60px;
    cursor: pointer;
    padding: 0 8px;
}
.user-top_header .el-drawer__header {
    font-size: 20px;
}






.user-pure_container {
    width: 100%;
    min-height: calc(100vh - 61px) ;
    background-color: #f4f5f7;
}

.user-main_container {
    width: 80%;
    min-height: calc(100vh - 71px);
    /* background-color: aquamarine; */
    margin: 0 auto;
    padding-bottom: 10px;
}

.user-inliner_banner {
    height: 180px;
    width: 100%;
    background-color: antiquewhite;
    background: url(../../../assets/img/banner/user_banner1.png) 50% no-repeat;
    background-size: cover;
}
.user-inliner_info {
    width: calc(100% - 40px);
    height: 60px;
    position: relative;
    top: 120px;
    /* bottom: -120px; */
    padding: 0 20px;
    /* background-color: rgba(244, 221, 192, 0.6); */
}
.user-avatar_cover {
    width: 50px;
    height: 50px;
    float: left;
    /* display: inline-block; */
    border-radius: 50%;
    background-size: cover;
}
.user-basic_info {
    float: left;
    position: relative;
    left: 10px;
    max-width: 600px;
    top: 5px;
    height: 50px;
    color: #fff;
}
.user-basic_info .user_name {
    font-size: 20px;
    font-weight: 700;
    text-align: left;
}
.user-basic_info .user_info {
    font-size: 13px;
    text-align: left;
}

.user-inliner_nav {
    height: 66px;
    width: 100%;
    background-color: #fff;
    border-radius: 0 0 5px 5px;
}
.user-inliner_navItem {
    float: left;
    list-style: none;
    padding: 0 20px;
    line-height: 66px;
    cursor: pointer;
    font-size: 13px;
    position: relative;
}
.user-inliner_navItem:hover {
    color: #00a1d6;
    border-bottom: 3px solid #00a1d6;
    border-bottom-left-radius: 3px;
    border-bottom-right-radius: 3px;
}
.user-inliner_navItem:hover .user-inliner_navItem_name {
    font-weight: 700;
}
.user-inliner_navItem.active {
  color: #00a1d6;
  border-bottom: 3px solid #00a1d6;
  border-bottom-left-radius: 3px;
  border-bottom-right-radius: 3px;
}
.user-inliner_navItem.active .user-inliner_navItem_name {
    font-weight: 700;
}
.user-inliner_navItem_icon {
    display: inline-block;
    width: 32px;
    height: 32px;
    position: relative;
    top: 12px;
    /* background-color: aquamarine; */
}
.user-inliner_navItem_name {
    padding-left: 5px;
}




.user-i_home {
    background: url(../../../assets/img/user/icon/i_home.png) 50% no-repeat;
}
.user-i_seting {
    background: url(../../../assets/img/user/icon/i_seting.png) 50% no-repeat;
}
.user-i_mass {
    background: url(../../../assets/img/user/icon/i_mass.png) 50% no-repeat;
}
.user-i_function {
    background: url(../../../assets/img/user/icon/i_function.png) 50% no-repeat;
}
.user_i_auth {
    background: url(../../../assets/img/user/icon-auth.png) 50% no-repeat;
}
.user-icon-activity {
    background: url(../../../assets/img/user/icon-activity.png) 5% no-repeat;
    position: absolute;
    margin-top: 8px;
    margin-left: -35px;
    width: 32px;
    height: 32px;
}
.user-icon-r_arrow {
    background: url(../../../assets/img/user/icon-r_arrow.png) 5% no-repeat;
    background-size: 80%;
    position: absolute;
    margin-top: 8px;
    width: 32px;
    height: 32px;
}



.user-inliner_content {
    display: flex;
    flex-direction: row;
    margin-top: 15px;
    width: 100%;
    min-height: 620px;
    /* background-color: #f49393; */
    border-radius: 5px 5px 0 0;
    padding: 0;
}
.user-inliner_content_leftMain {
    width: 66%;
    min-height: 620px;
    /* background-color: rgb(214, 223, 31); */
    background-color: #fff;
    border-radius: 5px;
}
.user-inliner_content_rightSideBar {
    margin-left: 20px;
    width: calc(34% - 20px);
    min-height: 400px;
    /* background-color: antiquewhite; */
}
.user-rightSideBar_application {
    height: 70px;
    width: 100%;
    background-color: #fff;
    border-radius: 5px;
}
.user-application_icon {
    width: 99px;
    float: left;
    height: 70px;
    background-size: cover;
}
.user-application_title {
    float: right;;
    width: calc(100% - 99px - 40px);
    height: 50px;
    margin-right: 20px;
    margin-top: 15px;
    text-align: left;
    cursor: pointer;
    /* background-color: blueviolet; */
}
.user-application_title p {
    font-size: 18px;
    
}
.user-application_title span {
    font-size: 13px;
    color: #99a2aa;
}

.user-rightSideBar_activity {
    height: 120px;
    width: 100%;
    border-radius: 5px;
    margin-top: 15px;
    background-color: #fff;
}
.user-rightSideBar_activity_title {
    color: #00a1d6;
    height: 50px;
    width: 140px;
    margin: 0 auto;
    padding-top: 10px;
    cursor: pointer;
}
.user-rightSideBar_activity_title h2 {
    position: relative;
    bottom: 0;
    text-align: right;
    line-height: 50px;
}
.user-rightSideBar_activity_BtnGroup {
    width: 80%;
    height: 36px;
    margin: 0 auto;
    border: 1px solid #e5e9ef;
}
.user-rightSideBar_activity_btn {
    width: 50%;
    height: 21px;
    float: left;
    position: relative;
    top: 7.5px;
    cursor: pointer;
}
.user-rightSideBar_activity_btn:hover {
    color: #00a1d6;
}
.user-rightSideBar_activity_btn:nth-child(1) {
    width: calc(50% - 1px);
    border-right: 1px solid #e5e9ef;
}
.user-rightSideBar_broadcast {
    height: 250px;
    width: 100%;
    border-radius: 5px;
    margin-top: 15px;
    background-color: #fff;
}
.user-rightSideBar_personalInfo {
    height: 120px;
    width: 100%;
    border-radius: 5px;
    margin-top: 15px;
    background-color: #fff;
}
.user-rightSideBar_section {
    font-size: 14px;
    height: 40px;
    padding: 0 20px;
    line-height: 40px;
    text-align: left;
    font-weight: 700;
    border-bottom: 1px solid #e5e9ef;
}









.User-modal {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    z-index: 99;
    display: flex;
    align-items: center;
    justify-content: center;
}
.User-modal-content {
    /* position: relative;
    top: 12vh;
    left: 18%; */
    padding: 0 20px;
    padding-top: 25px;
    padding-bottom: 29px;
    width: 64%;
    height: 70vh;
    border-radius: 5px;
    background-color: #fff;
}
.User-Perfect_pc {
    width: 100%;
    height: 68vh;
    /* background-color: #00a1d6; */
}
.User-Perfect_pc_left {
    float: left;
    height: 40vh;
    width: 50%;
    /* background-color: #99a2aa; */
}
.User-Perfect_pc_right {
    float: right;
    height: 40vh;
    width: 50%;
    /* background-color: #3d9bed; */
}
.User-Perfect_pc_inputTitle {
    font-size: 20px;
    font-weight: 700;
    text-align: left;
    margin-left: 10%;
}
.User-Perfect_pc_input {
    width: 80%;
    margin: 6px 0;
}
.User-Perfect_pc_btn {
    float: right;
    margin-right: 5%;
}
.User-Perfect_move {
    width: 100%;
    height: 68vh;
    /* background-color: #e8eb2a; */
    display: none;
}
.User-Perfect_move_inputTitle {
    text-align: left;
    margin-left: 10%;
    margin-top: 5px;
}
.User-Perfect_move_input {
    width: 80%;
}
.User-Perfect_move_btn {
    float: right;
    margin-right: 10%;
    margin-top: 6px;
}
/* .card {
    width: 100%;
    height: 100%;
    transition: transform 0.8s;
    transform-style: preserve-3d;
}
.component-Login, .component-Register {
    position: absolute;
    width: 100%;
    height: 100%;
    backface-visibility: hidden;
}
.component-Login {
    transform: rotateY(0deg);
    z-index: 2;
}
.component-Register {
    transform: rotateY(180deg);
    z-index: 1;
}
.flipped {
    transform: rotateY(180deg);
} */
/* .rotate-enter-active,
.rotate-leave-active {
  transition: transform 0.5s ease;
}
.rotate-enter,
.rotate-leave-active {
  transform: rotateY(-180deg);
} */

/* .user-icon_cancel {
    position: absolute;
    width: 32px;
    height: 32px;
    right: 16px;
    cursor: pointer;
    z-index: 999;
    background: url(../../../assets/img/user/icon/i_cancel.png) 50% no-repeat;
} */
@media screen and (max-width: 1120px) {
    .user-inliner_content_leftMain {
        width: 100%;
    }
    .user-inliner_content_rightSideBar {
        display: none;
    }
}
@media screen and (max-width: 768px) {
    .user-main_container {
        width: 96%;
    }
    .user-top_navLeft {
        display: none;
    }
    .user-top_header_avatar {
        margin-right: 5px;
    }
    .user-top_header .t_menu {
        display: block;
    }
}
@media screen and (max-width: 480px)  {
    /* 居中 */
    /* .user-inliner_navItem {
        display: inline-block;
        float: none;
    } */
    .user-inliner_navItem {
        padding: 0 10px;
    }
    .User-modal-content {
        width: 70%;
        left: 10%;
    }
    .User-Perfect_pc {
        display: none;
    }
    .User-Perfect_move {
        display: block;
    }
    .el-dialog {
        width: 96%;
    }
}
@media screen and (max-width: 390px) {
    .User-modal-content {
        height: 75vh;
        overflow-y: auto;
    }
}

</style>

