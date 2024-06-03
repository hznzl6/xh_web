<template>
    <div style="display: flex;">
        <div class="user-register_left">
            <el-empty description="图片正在来的路上..."></el-empty>
        </div>
        <div class="user-register_right">
            <div class="user-register_title">
                <span :class="{active: register_path === 'phone'}" @click="changePath('phone')">手机注册</span><span :class="{active: register_path === 'mail'}" @click="changePath('mail')">邮箱注册</span>
            </div>
           <div v-show="register_path == 'phone'">
            <p>手机:<span v-if="!counting" @click="startCountdown0" style="font-size: 13px;cursor: pointer;color: #00A1D6;float: right;">发送</span><span style="font-size: 13px;cursor: pointer;color: #00A1D6;float: right;" v-else>{{ countdown }}秒</span></p>
            <el-input clearable v-model="user.path.phone" placeholder="输入手机号" maxlength="13"></el-input>
            <p>密码:</p>
            <el-input clearable v-model="user.pwd" placeholder="输入密码" type="password" maxlength="16"></el-input>
            <p>密码:</p>
            <el-input clearable v-model="user.pwd2" placeholder="确认密码" type="password" maxlength="16"></el-input>
            <p>验证码:</p>
            <div style="display: flex;justify-content: space-between;">
                <el-input class="yanwidth" clearable v-model="user.yan" placeholder="输入验证码" maxlength="6"></el-input>
                <el-button @click="registerBtnByPhone" style="float: right;">注册</el-button>
            </div>
           </div>
           <div v-show="register_path == 'mail'">
            <p>邮箱: <span v-if="!counting" @click="startCountdown" style="font-size: 13px;cursor: pointer;color: #00A1D6;float: right;">发送</span><span style="font-size: 13px;cursor: pointer;color: #00A1D6;float: right;" v-else>{{ countdown }}秒</span></p>
            <el-input clearable v-model="user.path.mail" placeholder="输入邮箱" maxlength="13">
                <template slot="append">@qq.com</template>
            </el-input>
            <p>密码:</p>
            <el-input clearable v-model="user.pwd" placeholder="输入密码" type="password" maxlength="16"></el-input>
            <p>密码:</p>
            <el-input clearable v-model="user.pwd2" placeholder="确认密码" type="password" maxlength="16"></el-input>
            <p>验证码:</p>
            <!-- <el-input clearable v-model="user.yan" placeholder="输入验证码" maxlength="6"></el-input>
            <el-button style="float: right;margin-top: 8px;">注册</el-button> -->
            <div style="display: flex;justify-content: space-between;width: 100%;">
                <el-input class="yanwidth" clearable v-model="user.yan" placeholder="输入验证码" maxlength="6"></el-input>
                <el-button @click="registerBtnByMail" style="float: right;">注册</el-button>
            </div>
           </div>
        </div>
    </div>
</template>
<script>
export default {
    data() {
        return {
            user: {
                sno: '',
                path: {
                    phone: '',
                    mail: ''
                },
                pwd: '',
                pwd2: '',
                yan: ''
            },
            register_path: 'phone',
            countdown: 120, // 倒计时时间，单位秒
            counting: false, // 是否在倒计时过程中
        }
    },
    methods: {
        changePath(path) {
            this.register_path = path
        },
        registerBtnByPhone() {
            if(this.user.path.phone == '') {
                this.$message({
                    message: '手机没填哦',
                    type: 'warning'
                })
            } else if (this.user.pwd == '') {
                this.$message({
                    message: '密码没填哦',
                    type: 'warning'
                })
            } else if (this.user.pwd2 == '') {
                this.$message({
                    message: '密码没完哦',
                    type: 'warning'
                })
            } else if (this.user.yan == '') {
                this.$message({
                    message: '验证码没填哦',
                    type: 'warning'
                })
            } else {
                const phone = this.user.path.phone
                const yan = this.user.yan
                const url = process.env.VUE_APP_BASE_URI + '/Tencent-sms/Verify-sms'
                fetch(`${url}?code=${yan}&phone=${phone}`, {
                    method: "POST",
                    headers: {
                        'Accept': 'application/json',
                        'Content-type': 'application/json'
                    },
                })
                .then(res => res.text())
                .then(res => {
                    console.log(res)
                    if(res == 'Verify success') {
                        this.$prompt('输入你的学号（后面不允许更改哦）', '提示', {
                            confirmButtonText: '确定',
                            cancelButtonText: '取消',
                            type: 'warning'
                        }).then(({ value }) => {
                            this.$confirm(value + ' 是你的学号, 确定噢?不给改的', '提示', {
                                confirmButtonText: '确定',
                                cancelButtonText: '取消',
                                type: 'warning'
                            }).then(() => {
                                // console.log(value)
                                this.user.sno = value
                                const url =process.env.VUE_APP_BASE_URI + '/shetuan/registerByPhone'
                                const data = {
                                    phone: this.user.path.phone,
                                    sno: this.user.sno,
                                    pwd: this.user.pwd
                                }
                                console.log(data)
                                fetch(url, {
                                    method: "POST",
                                    headers: {
                                        'Accept': 'application/json',
                                        'Content-type': 'application/json'
                                    },
                                    body: JSON.stringify(data)
                                })
                                .then(res => res.text())
                                .then(res => {
                                    if(res == 'Register success!') {
                                        this.$message({
                                            type: 'success',
                                            message: '注册成功!'
                                        });
                                        setTimeout(() => {
                                            location.reload();
                                        }, 2000);
                                    } else if (res == 'The sno already exists!') {
                                        this.$message({
                                            type: 'warning',
                                            message: '学号已注册!'
                                        });
                                        setTimeout(() => {
                                            location.reload();
                                        }, 2000);
                                    }
                                })
                            }).catch(() => {
                                this.$message({
                                    type: 'info',
                                    message: '已取消确定'
                                });          
                            });
                        }).catch(() => {
                            this.$message({
                                type: 'info',
                                message: '取消输入'
                            });
                        });
                    } else {
                        this.$message({
                            message: '验证码不存在或错误',
                            type: 'warning'
                        })
                    }
                })
            }
        },
        registerBtnByMail() {
            if(this.user.path.mail == '') {
                this.$message({
                    message: '邮箱没填哦',
                    type: 'warning'
                })
            } else if (this.user.pwd == '') {
                this.$message({
                    message: '密码没填哦',
                    type: 'warning'
                })
            } else if (this.user.pwd2 == '') {
                this.$message({
                    message: '密码没完哦',
                    type: 'warning'
                })
            } else if (this.user.yan == '') {
                this.$message({
                    message: '验证码没填哦',
                    type: 'warning'
                })
            } else {
                const mail = this.user.path.mail + '@qq.com'
                const yan = this.user.yan
                const url = process.env.VUE_APP_BASE_URI + '/shetuan/verification-mail'
                fetch(`${url}?code=${yan}&email=${mail}`, {
                    method: "POST",
                    headers: {
                        'Accept': 'application/json',
                        'Content-type': 'application/json'
                    },
                })
                .then(res => res.text())
                .then(res => {
                    console.log(res)
                    if(res == 'Verification success') {
                        this.$prompt('输入你的学号（后面不允许更改哦）', '提示', {
                            confirmButtonText: '确定',
                            cancelButtonText: '取消',
                            type: 'warning'
                        }).then(({ value }) => {
                            this.$confirm(value + ' 是你的学号, 确定噢?不给改的', '提示', {
                                confirmButtonText: '确定',
                                cancelButtonText: '取消',
                                type: 'warning'
                            }).then(() => {
                                // console.log(value)
                                this.user.sno = value
                                const url =process.env.VUE_APP_BASE_URI + '/shetuan/registerByMail'
                                const data = {
                                    mail: this.user.path.mail + '@qq.com',
                                    sno: this.user.sno,
                                    pwd: this.user.pwd
                                }
                                console.log(data)
                                fetch(url, {
                                    method: "POST",
                                    headers: {
                                        'Accept': 'application/json',
                                        'Content-type': 'application/json'
                                    },
                                    body: JSON.stringify(data)
                                })
                                .then(res => res.text())
                                .then(res => {
                                    if(res == 'Register success!') {
                                        this.$message({
                                            type: 'success',
                                            message: '注册成功!'
                                        });
                                        setTimeout(() => {
                                            location.reload();
                                        }, 2000);
                                    } else if (res == 'The sno already exists!') {
                                        this.$message({
                                            type: 'warning',
                                            message: '学号已注册!'
                                        });
                                        setTimeout(() => {
                                            location.reload();
                                        }, 2000);
                                    }
                                })
                            }).catch(() => {
                                this.$message({
                                    type: 'info',
                                    message: '已取消确定'
                                });          
                            });
                        }).catch(() => {
                            this.$message({
                                type: 'info',
                                message: '取消输入'
                            });
                        });
                        // const url = 'http://localhost:40125/shetuan/registerByMail'
                        // const value = {
                        //     mail: this.user.path.mail + '@qq.com',
                        //     pwd: 'this.user.pwd',
                        //     sno: 'this.user.sno'
                        // }
                    } else {
                        this.$message({
                            message: '验证码不存在或错误',
                            type: 'warning'
                        })
                    }
                })
            }
        },
        startCountdown0() {
            if(this.user.path.phone != '') {
                const phone = this.user.path.phone
                const url = process.env.VUE_APP_BASE_URI + '/Tencent-sms/Send-sms'
                fetch(`${url}?phone=${phone}`, {
                    method: "POST",
                    headers: {
                        'Accept': 'application/json',
                        'Content-type': 'application/json'
                    },
                })
                .then(res => res.text())
                .then(res => {
                    if(res == 'Send success') {
                        this.$message({
                            message: '发送成功',
                            type: 'success'
                        })
                    }
                })
                this.counting = true; // 开始倒计时
                this.countdown = 120; // 重置倒计时时间
                // 定时器，每秒减少1秒倒计时时间
                const timer = setInterval(() => {
                this.countdown--;
                
                // 倒计时结束
                if (this.countdown === 0) {
                    clearInterval(timer); // 清除定时器
                    this.counting = false; // 停止倒计时
                }
                }, 1000);
            } else {
                this.$message({
                    message: '手机没填哦',
                    type: 'warning'
                })
            }
        },
        startCountdown() {
            if(this.user.path.mail != '') {
                const mail = this.user.path.mail + '@qq.com'
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
                this.counting = true; // 开始倒计时
                this.countdown = 120; // 重置倒计时时间
                // 定时器，每秒减少1秒倒计时时间
                const timer = setInterval(() => {
                this.countdown--;
                
                // 倒计时结束
                if (this.countdown === 0) {
                    clearInterval(timer); // 清除定时器
                    this.counting = false; // 停止倒计时
                }
                }, 1000);
            } else {
                this.$message({
                    message: '邮箱没填哦',
                    type: 'warning'
                })
            }
        },
        // verifyMail() {
        //     const mail = this.user.path.mail + '@qq.com'
        //     const yan = this.user.yan
        //     const url = 'http://localhost:40125/shetuan/verification-mail'
        //     fetch(`${url}?code=${yan}&email=${mail}`, {
        //         method: "POST",
        //         headers: {
        //             'Accept': 'application/json',
        //             'Content-type': 'application/json'
        //         },
        //     })
        //     .then(res => res.text())
        //     .then(res => {
        //         if(res == 'Verification success') {
        //             // console.log(this.verifyStatus)
        //             this.verifyStatus = true
        //             // console.log(this.verifyStatus)
        //         } else {
        //             this.verifyStatus = false
        //         }
        //     })
        // }
    }
}
</script>
<style>
.user-register_left {
    width: 42%;
    height: 32vh;
    border-right: 1px solid #e5e9ef;
}
.user-register_right {
    width: calc(58% - 1px);
    height: 32vh;
    padding-left: 20px;
}
.user-register_title {
    margin-bottom: 12px;
}
.user-register_title span {
    font-size: 18px;
    padding: 0 16px;
    cursor: pointer;
}
.user-register_title span:nth-child(1) {
    border-right: 1px solid #e5e9ef;
}
.user-register_title span.active {
    color: #00A1D6;
}
.user-register_right p {
    text-align: left;
    margin-top: 8px;
}
.user-register_right p:nth-child(1) {
    margin-top: 0;
}
.user-register_right .el-input-group__append {
    padding: 0;
}
.yanwidth .el-input__inner {
    width: 120px;
    float: left;
}
@media screen and (max-width: 1120px) {
    .user-register_left {
        display: none;
    }
    .user-register_right {
        width: 100%;
    }
}
@media screen and (max-width: 480px) {
    .user-register_title span {
        font-size: 14px;
    }
    .user-register_right p {
        margin-top: 3px;
    }
}
</style>