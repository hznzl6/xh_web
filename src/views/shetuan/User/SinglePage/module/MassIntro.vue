<template>
    <div>
        <div class="Mass-Intro_backColor">
            <div class="Mass-Intro_banner">
                
            </div>
            <div class="Mass-Intro_MainContainer">
                <div class="Mass-Intro_MainContainer_left">
                    <span style="float: right;cursor: pointer;" @click="editIntro">编辑此页</span>
                    <div class="Mass-Intro_MainContainer_leftTitle">
                        社团信息
                    </div>
                    <div v-html="MassIntro.info" class="Intro_html"></div>
                    <div class="Mass-Intro_MainContainer_leftTitle">
                        通知公告
                    </div>
                    <div class="Mass-Intro_MainContainer_leftTitle">
                        新闻动态
                    </div>
                    <div class="Mass-Intro_MainContainer_leftTitle">
                        讨论区
                    </div>
                </div>
                <div class="Mass-Intro_MainContainer_right">
                    <div style="display: flex;justify-content: flex-end;">
                        <el-button size="mini" @click="refresh">返回</el-button>
                        <el-button size="mini" @click="OpenApllyModal">申请入社</el-button>
                    </div>
                    <div class="Mass-Intro_MainContainer_rightTitle">负责人: {{ MassIntro.manager }}</div>
                    <div class="Mass-Intro_MainContainer_rightTitle">指导老师: {{ MassIntro.teacher }}</div>
                    <div class="Mass-Intro_MainContainer_rightTitle">所属单位：<span>社团联合会</span></div>

                </div>

                <!-- 移动端底部申请 -->
                <!-- <div class="Mass-Intro_MainContainer_MoveApply">
                    <div @click="OpenApllyModal" class="Mass-Intro_MainContainer_MoveApply_Btn">立即申请</div>
                </div> -->

                <!-- 申请蒙版 -->
                <!-- <div v-if="ApplyModal" class="Apply-Modal">
                    <div class="Apply-Modal_content">
                        <i @click="OffApplyModal" class="user-icon_cancel"></i>
                        <h3>申请入社</h3>
                        <ul class="Apply-Modal_content_Form">
                            
                            <p @click="FormEditMess" style="text-align: left;">修改</p>
                            <el-input disabled v-model="mass">
                                <template slot="prepend">社团：</template>
                            </el-input>
                            <el-input disabled v-model="userInfo.name">
                                <template slot="prepend">姓名：</template>
                            </el-input>
                            <el-input disabled v-model="userInfo.sno">
                                <template slot="prepend">学号：</template>
                            </el-input>
                            <el-input disabled v-model="userInfo.belong">
                                <template slot="prepend">班级：</template>
                            </el-input>
                            <el-input disabled v-model="userInfo.phone">
                                <template slot="prepend">手机：</template>
                            </el-input>
                            <el-select v-model="userInfo.department" placeholder="请选择部门">
                                <el-option v-for="item in departments" :key="item" :label="item.label" :value="item"></el-option>
                            </el-select>
                            <el-input  @input="updateCount" v-model="userInfo.intro" placeholder="输入你的介绍" type="textarea">
                                
                            </el-input>
                            <div style="text-align: right;">{{ count }} / 100</div>
                            <div @click="FormApply" class="Apply-Modal_content_Form_ApplyBtn">申请</div>
                            
                            <li class="Apply-Modal_content_FormItem">{{ userInfo.name }}</li>
                            <li class="Apply-Modal_content_FormItem">{{ userInfo.sno}}</li>
                            <li class="Apply-Modal_content_FormItem">{{ userInfo.belong}}</li>
                            <li class="Apply-Modal_content_FormItem">{{ userInfo.phone}}</li>
                        </ul>
                    </div>
                </div> -->
            </div>
        </div>
    </div>
</template>
<script>
import axios from 'axios'
export default {
    data() {
        return {
            MassIntro: {},
            mass: '',
            ApplyModal: false,
            userInfo: {
                name: '',
                sno: '',
                belong: '',
                phone: '',
                intro: '',
                department: ''
            },
            departments: [],
            isPost: false,
            isPassed: false,
            isLogined: ''
        }
    },
    created() {
        // this.GetIntroByMass(this.$route.params.mass + '/' + localStorage.getItem('mass'))
        this.mass = this.$route.params.mass
        this.isLogined = localStorage.getItem("isLogined")
        this.getUserInfo()
        this.getJustDepartment()
        this.getInfo()
    },
    computed: {
        count() {
            return this.userInfo.intro.length;
        }
    },
    methods: {
        refresh() {
            location.reload()
        },
        getInfo() {
            const url = '/api/backstage-massList/getOne'
            axios.post(`${url}?mass=${localStorage.getItem('mass')}`, {}, {
                headers: {
                    'verifyCode': '2024'
                }
            
            }).then(res => {
                this.MassIntro = res.data
                console.log(res.data)
            })
        },
        getJustDepartment() {
            
        },
        updateCount() {
            
        },
        GetIntroByMass() {
            
        },
        editIntro() {
            const url = "/editInrto/" + localStorage.getItem('mass')
            this.$router.push(url)
            console.log(url)
        },
        OffApplyModal() {
            this.ApplyModal = false
        },
        OpenApllyModal() {
            this.router.push('/editInrto/')
        },
        getUserInfo() {
            
        },
        FormApply() {
            
        },
        FormEditMess() {
            this.$message({
                message: '信息到个人主页修改哦！',
                type: 'warning'
            })
        }
    }
}
</script>
<style>
.Mass-Intro_topHeader {
    height: 60px;
    width: 100%;
    line-height: 60px;
    border-bottom: 1px solid #e5e9ef;
}
.Mass-Intro_backColor {
    width: 100%;
    min-height: calc(100vh - 61px);
    background-color: #F4F5F7;
}
.Mass-Intro_banner {
    height: 380px;
    width: 75%;
    margin: 0 auto;
    background-color: rgb(60, 121, 234);
    background: url(@/assets/img/mass/banner/banner01.png) 50% no-repeat;
    background-size: cover;
}
.Mass-Intro_MainContainer {
    width: 75%;
    margin: 0 auto;
    margin-top: 10px;
    min-height: calc(100vh - 441px);
    /* background-color: aqua; */
    display: flex;
}
.Mass-Intro_MainContainer_left {
    width: calc(70% - 11px);
    min-height: 80vh;
    margin-bottom: 65px
;
    /* background-color: blueviolet; */
}
.Intro_html {
    text-align: left;
    margin: 3px 12px;
}
.Mass-Intro_html img {
    width: 100%;
}
.Mass-Intro_html h2 {
    
}
.Mass-Intro_html p {

}
.Mass-Intro_MainContainer_leftTitle {
    border-left: 4px solid #22bfa7;
    height: 30px;
    font-size: 20px;
    font-weight: 700;
    text-align: left;
    padding-left: 6px;
    line-height: 30px;
    margin-top: 6px;
    color: #666;

}
.Mass-Intro_MainContainer_right {
    width: 30%;
    height: 70vh;
    margin-left: 10px;
    border-left: 1px solid #e5e9ef;
    /* background-color: burlywood; */
}
.Mass-Intro_MainContainer_rightTitle {
    border-left: 4px solid #22bfa7;
    height: 20px;
    font-size: 16px;
    font-weight: 700;
    text-align: left;
    padding-left: 6px;
    line-height: 20px;
    margin-top: 6px;
    margin-left: 12px;
    color: #666;
}
.Mass-Intro_MainContainer_rightTitle span {
    color: #66b6f3;
    cursor: pointer;
}
.Mass-Intro_MainContainer_MoveApply {
    display: none;
    height: 60px;
    width: 96%; 
    position: fixed;
    bottom: 0;
    border-top: 1px solid #e5e9ef;
    /* background-color: #66b6f3; */
}
.Mass-Intro_MainContainer_MoveApply_Btn {
    float: right;
    width: 120px;
    height: 40px;
    margin-top: 10px;
    line-height: 40px;
    border-radius: 5px;
    color: #e5e9ef;
    background-color: #4496ec;
}


.Apply-Modal {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    z-index: 99;
    display: flex;
    justify-content: center;
    align-items: center;
}
.Apply-Modal_content {
    /* position: relative;
    top: 25vh;
    left: 27%; */
    padding: 16px 20px;
    padding-top: 25px;
    padding-bottom: 29px;
    width: 46%;
    height: 40vh;
    border-radius: 5px;
    background-color: #fff;
    overflow-y: auto;
}

.Apply-Modal_content_Form {
    position: relative;
}
.Apply-Modal_content_Form .el-input {
    margin: 4px 0;
}
.Apply-Modal_content_Form .el-textarea textarea {
    max-height: 60px;
}
.Apply-Modal_content_FormItem {
    list-style: none;
}
.Apply-Modal_content_Form_ApplyBtn {
    position: absolute;
    right: 0;
    width: 120px;
    height: 30px;
    border: 1px solid #d8dbe1;
    background-color: #F4F5F7;
    line-height: 30px;
    border-radius: 5px;
    cursor: pointer;
}
@media screen and (max-width: 480px) {
    .Mass-Intro_banner {
        width: 100%;
        height: 220px;
    }
    .Mass-Intro_MainContainer {
        min-height: calc(100vh - 291px);
        width: 96%;
    }
    .Mass-Intro_MainContainer_left {
        width: 100%;
    }
    .Mass-Intro_MainContainer_right {
        display: none;
    }
    .Mass-Intro_MainContainer_MoveApply {
        display: block;
    }
    .Apply-Modal_content {
        width: 65%;
        left: 12%;
    }
}
</style>