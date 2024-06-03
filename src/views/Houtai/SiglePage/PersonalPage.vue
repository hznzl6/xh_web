<!-- <template>
    <div>
        <h2>个人信息</h2>
    </div>
</template>

<script>
export default {
    data() {
        return {
            
        }
    }
}
</script> -->
<template>
    <div class="Personal-container">
        <el-row class="container pc">
            <el-col :span="6">
                <el-card class="card">
                    <div slot="header" class="card-header">
                        个人资料
                    </div>
                    <div class="card-content">
                        <img src="http://ganxy03.cn/img/avatar.png" alt="Avatar" class="avatar">
                        <ul class="infoList">
                            <li class="infoItem">
                                <span><i class="el-icon-user"></i>登陆名称</span>
                                <!-- <span>{{ baseInfo.name }}</span> -->
                                <span>Test</span>
                            </li>
                            <li class="infoItem">
                                <span><i class="el-icon-phone-outline"></i>邮箱</span>
                                <span>{{ baseInfo.mail }}</span>
                            </li>
                            <li class="infoItem">
                                <span><i class="el-icon-star-off"></i>所属社团</span>
                                <span></span>
                            </li>
                            <li class="infoItem">
                                <span><i class="el-icon-date"></i>注册时间</span>
                                <span>{{ baseInfo.time}}</span>
                            </li>
                        </ul>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="18">
                <el-card class="card">
                    <div slot="header" class="card-header">
                        基本资料
                    </div>
                    <div class="innerBox">
                        <div class="headTitle">
                            <div @click="changePoint('base')" class="title" slot="header">
                                基本资料
                            </div>
                            <div @click="changePoint('pwd')" class="title" slot="header">
                                修改密码
                            </div>
                        </div>
                        <div class="formTable">
                            <el-form label-width="80px" v-if="this.currentPoint === 'base'">
                                <el-form-item label="学号:">
                                    <el-input disabled v-model="baseInfo.sno"></el-input>
                                </el-form-item>
                                <el-form-item label="姓名:">
                                    <el-input disabled v-model="baseInfo.name"></el-input>
                                </el-form-item>
                                <el-form-item label="邮箱:">
                                    <el-input v-model="baseInfo.mail" disabled></el-input>
                                </el-form-item>
                                <el-form-item label="性别:">
                                    <template style="text-align: left;">
                                        <el-radio v-model="baseInfo.sex" label="男">男</el-radio>
                                        <el-radio v-model="baseInfo.sex" label="女">女</el-radio>
                                    </template>
                                </el-form-item>
                                <el-form-item label="班级:">
                                    <el-input v-model="baseInfo.belong"></el-input>
                                </el-form-item>
                                <div style="display: flex;justify-content: flex-start;margin: 0 0 0 80px;">
                                    <el-button type="primary"><i class="el-icon-check"></i>保存</el-button>
                                    <el-button type="danger"><i class="el-icon-close"></i>关闭</el-button>
                                </div>
                            </el-form>

                            <el-form label-width="80px" v-if="this.currentPoint === 'pwd'">
                                <el-form-item label="旧密码:">
                                    <el-input type="password" v-model="pwd.oldPwd"></el-input>
                                </el-form-item>
                                <el-form-item label="新密码:">
                                    <el-input type="password" v-model="pwd.newPwd"></el-input>
                                </el-form-item>
                                <el-form-item label="确认密码:">
                                    <el-input type="password" v-model="pwd.confirmPwd"></el-input>
                                </el-form-item>
                                <div style="display: flex;justify-content: flex-start;margin: 0 0 0 80px;">
                                    <el-button @click="savePwd" type="primary"><i class="el-icon-check"></i>保存</el-button>
                                    <el-button @click="close" type="danger"><i class="el-icon-close"></i>关闭</el-button>
                                </div>
                            </el-form>
                        </div>
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>
<script>
import axios from 'axios'
export default {
    data() {
        return {
            currentPoint: 'base',
            baseInfo: {},
            pwd: {
                oldPwd: '',
                newPwd: '',
                confirmPwd: ''
            }
        }
    },
    created() {
        this.getPersonInfo()
    },
    methods: {
        changePwd() {
            const url = '/api/user-account/update/pwd'
            const data = {
                sno: this.baseInfo.sno,
                old: this.pwd.oldPwd,
                nuw: this.pwd.newPwd,
            }
            axios.post(`${url}?sno=${data.sno}&old=${data.old}&nuw=${data.nuw}`, {},{
                headers: {
                    'verifyCode': '2024'
                }
            }).then(res => {
                if (res.data === 'Update pwd success') {
                    this.$message({
                        message: '修改成功',
                        type: 'success'
                    })
                    setTimeout(function() {
                        location.reload()
                    }.bind(this), 1500)
                } else if(res.data == 'old pwd is wrong'){
                    this.$message({
                        message: '原密码错误',
                        type: 'warning'
                    })
                }
            })
        },
        savePwd() {
            if (this.pwd.oldPwd === '') {
                this.$message({
                    message: '请输入旧密码',
                    type: 'warning'
                })
            } else if (this.pwd.newPwd === '') {
                this.$message({
                    message: '请输入新密码',
                    type: 'warning'
                })
            } else if (this.pwd.newPwd !== this.pwd.confirmPwd) {
                this.$message({
                    message: '两次输入密码不一致',
                    type: 'warning'
                })
            } else if (this.pwd.oldPwd === this.pwd.newPwd) {
                this.$message({
                    message: '新密码不能与旧密码相同',
                    type: 'warning'
                })
            } else {
                this.changePwd()
            }
        },
        close() {
            this.currentPoint = 'base'
        },
        changePoint(param) {
            this.currentPoint = param
        },
        getPersonInfo() {
            const url = '/api/user-account/info/get'

            axios.post(`${url}?sno=${localStorage.getItem('Sno')}`, {

                },
                {
                headers: {
                    'verifyCode': '2024'
                }
            }).then((res) => {
                this.baseInfo = res.data
            })
        }
    }
}
</script>
<style> 
.Personal-container {
    width: calc(100% - 24px);
    min-height: calc(100vh - 100px - 24px - 52px);
    /* background-color: aqua; */
    margin: 12px;
    overflow-y: auto;
}
.Personal-container .move {
    display: none;
}
.Personal-container .card {
    margin: 0 6px;
    min-height: 60vh;
}

.Personal-container .card-header {
    text-align: center;
    font-size: 20px;
    font-weight: bold;
}
.Personal-container .card-content {
    text-align: center;
}
.Personal-container .avatar {
    width: 120px;
    height: 120px;
    border-radius: 50%;
    margin-bottom: 10px;
}
.Personal-container .infoList {
    border-top: 2px solid #edeff1;
    border-bottom: 2px solid #edeff1;
    padding: 0;
    margin: 0 12px;
    color: #676A6C;
}
.Personal-container .infoItem {
    list-style: none;
    display: flex;
    justify-content: space-between;
    height: 35px;
    line-height: 35px;
    border-top: 2px solid #e7eaec;
}
.Personal-container .infoItem:nth-child(1) {
    border-top: none;
}
.Personal-container .innerBox {
    min-height: 420px;
    border: 1px solid #fbfbfb;
    border-top: none;
}
.Personal-container .headTitle {
    display: flex;
    justify-content: flex-start;
    height: 30px;
    line-height: 30px;
    border-bottom: 2px solid #fbfbfb;

}
.Personal-container .headTitle .title {
    padding: 0 12px;
    border-right: 1px solid #f5f5f5;
}
.Personal-container .formTable {
    /* background-color: aqua; */
    min-height: 220px;
    margin: 12px 24px;
}
.Personal-container .formTable .el-input__inner {
    height: 30px;
}

@media screen and (max-width: 768px) {
    .Personal-container .pc {
        display: none
    }
    .Personal-container .move {
        display: block;
    }
}
</style>