<template>
    <div class="loginFrame" style="display: flex;" v-loading.fullscreen.lock="fullscreenLoading">
        <!-- 左边图片 height: 40vh;-->
        <div class="user-Login_left">
            <el-empty description="图片正在来的路上..."></el-empty>
        </div>
        <div class="user-Login_right">
            <div class="user-login_title">
                <span :class="{active: login_path === 'pwd'}" @click="changePath('pwd')">密码登陆</span><span :class="{active: login_path === 'mail'}" @click="changePath('mail')">邮箱注册</span>
            </div>
            <div v-show="login_path == 'pwd'">
                <p>账号:</p>
                <el-input clearable v-model="user.with_pwd.sno" placeholder="输入学号" maxlength="13"></el-input>
                <p>密码:</p>
                <el-input clearable v-model="user.with_pwd.pwd" placeholder="输入密码" type="password" maxlength="16"></el-input>
                <p>验证码:</p>
                <el-row>
                    <el-col :span="18">
                        <el-input clearable v-model="user.with_pwd.yan" placeholder="输入验证码" maxlength="6"></el-input>
                    </el-col>
                    <el-col :span="6">
                        <img style="width: 100%;height: 100%;" :src="yan_picUrl" alt="验证码" />
                    </el-col>
                </el-row>
                
                <el-row class="btnGroup">
                    <el-col :span="12">
                        <div class="login-pathIcon">
                            <i class="i-qq"></i>
                            <el-button @click="guestLogin" type="text" class="guest">游客登陆</el-button>
                            <span class="title">一键登录</span>
                        </div>
                    </el-col>
                    
                    <el-button @click="Login_pwd" style="float: right;margin-top: 10px;">登陆</el-button>
                </el-row>
            </div>
            <div v-show="login_path == 'mail'">
                <Steps :model="dynamicModel" :readonly="true" style="margin-bottom: 1rem"/>
                <RegisterOne v-if="currentStep === 1" />
                <RegisterTwo v-if="currentStep === 2" />
                <RegisterThr v-if="currentStep === 3" />
                
                <div style="display: flex;justify-content: flex-end;">
                    <el-button v-if="currentStep === 3" @click="prevStep" style="margin-top: 20px;">上一步</el-button>
                    <el-button v-if="currentStep === 3" style="margin-top: 20px;" @click="register">注册</el-button>
                    <el-button v-if="currentStep === 1" style="margin-top: 20px;" @click="nextStep_one">下一步</el-button>
                    <el-button v-if="currentStep === 2" style="margin-top: 20px;" @click="nextStep_two">下一步</el-button>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import axios from "axios"
import RegisterOne from './RegisterOne.vue'
import RegisterTwo from './RegisterTwo.vue'
import RegisterThr from './RegisterThr.vue'
export default {
    components: {
        RegisterOne,
        RegisterTwo,
        RegisterThr
    },
    data() {
        return {
            user: {
                with_pwd: {
                    sno: '',
                    pwd: '',
                    yan: ''
                }
            },
            login_path: 'pwd',
            yan_picUrl: '',
            yan_captcha: '',
            loginFrame: true,
            fullscreenLoading: false,
            currentStep: 1,
            items: [{
                label: '验证邮箱',
                to: '/steps/mail',
            },
            {
                label: '完善信息',
                to: '/steps/perfect'
            },
            {
                label: '确认信息',
                to: '/steps/comfirm'
            }],
        }
    },
    computed: {
        dynamicModel() {
            return this.items.slice(0, this.currentStep);
        },
    },
    mounted() {
        // this.verifyLogin()
        this.generateCaptcha()
    },
    created() {
        localStorage.removeItem('mail')
        localStorage.removeItem('yan')
    },
    methods: {
        register() {
            const data = {
                mail: localStorage.getItem('mail'),
                name: localStorage.getItem('name'),
                sno: localStorage.getItem('sno'),
                sex: localStorage.getItem('sex'),
                belong: localStorage.getItem('belong'),
                pwd: localStorage.getItem('pwd'),
            }

            // console.log(data)
            this.fullscreenLoading = true

            const url01 = '/api/user-account/register'
            axios.post(`${url01}?sno=${data.sno}&pwd=${data.pwd}`, {

                },
                {
                headers: {
                    'verifyCode': '2024'
                }
            }).then((res) => {
                if(res.data == 'Register success') {
                    // this.$message({
                    //     message: '注册成功',
                    //     type: 'success'
                    // })

                    const url02 = '/api/user-account/perfect'

                    axios.post(url02, data,
                        {
                        headers: {
                            'verifyCode': '2024'
                        }
                    }).then((res) => {
                        if(res.data == 'Perfect success') {
                            setTimeout(function() {
                                this.fullscreenLoading = false
                                this.$message({
                                    message: '注册成功',
                                    type: 'success'
                                });
                                localStorage.clear()
                                localStorage.setItem('needLogin', 'true')
                                setTimeout(function() {
                                    location.reload()
                                }.bind(this),1500)
                            }.bind(this), 1500)
                        }
                    })

                } else if(res.data == 'Account have exited') {
                    setTimeout(function() {
                        this.fullscreenLoading = false
                        this.$message({
                            message: '账号已存在',
                            type: 'warning'
                        });
                    }.bind(this), 1500)
                }
            })
        },
        nextStep_one() {
            if(localStorage.getItem('mail') == null) {
                this.$message({
                    message: '邮箱不能为空',
                    type: 'warning'
                })
                return
            } else if(localStorage.getItem('pwd') == null || localStorage.getItem('pwd') == '') {
                this.$message({
                    message: '密码不能为空',
                    type: 'warning'
                })
                return
            } else if(localStorage.getItem('rePwd') == null || localStorage.getItem('rePwd') == '') {
                this.$message({
                    message: '没有确认密码',
                    type: 'warning'
                })
                return
            } else if(localStorage.getItem('pwd') != localStorage.getItem('rePwd')) {
                this.$message({
                    message: '两次密码不一致',
                    type: 'warning'
                })
                return
            } else if(localStorage.getItem('yan') == null) {
                this.$message({
                    message: '验证码不能为空',
                    type: 'warning'
                })
                return
            } else if (this.currentStep < 3) {
                const url = '/api/mail/verify-mail'
                axios.post(`${url}?email=${localStorage.getItem('mail')}&code=${localStorage.getItem('yan')}`,{
                        
                    },
                    {
                    headers: {
                        'verifyCode': '2024'
                    }
                }).then((res) => {
                    if(res.data == 'Verification failed.') {
                        this.$message({
                            message: '验证码错误',
                            type: 'warning'
                        })
                    } else {
                        this.$message({
                            message: '验证成功',
                            type: 'success'
                        })
                        // localStorage.removeItem('mail')
                        localStorage.removeItem('yan')
                        this.currentStep++;
                    }
                })
            }
        },
        nextStep_two() {
            if (this.currentStep < 3) {
                this.currentStep++;
            }
        },
        prevStep() {
            if (this.currentStep > 1) {
                this.currentStep--;
            }
        },
        verifyLogin() {
            if(localStorage.getItem('Sno') != null) {
                this.$router.push('/user/' + localStorage.getItem('Sno')).catch(err => {
                    
                console.log(err)
            })
            }
        },
        changePath(path) {
            this.login_path = path
        },
        Login_pwd() {
            // console.log(this.user.with_pwd)
            if(this.user.with_pwd.sno == '') {
                this.$message({
                    message: '学号没填哦',
                    type: 'warning'
                })
            } else if(this.user.with_pwd.pwd == ''){
                this.$message({
                    message: '不能没有密码哦',
                    type: 'warning'
                })
            } else if(this.user.with_pwd.yan == ''){
                this.$message({
                    message: '验证码没填哦',
                    type: 'warning'
                })
            } else if(this.user.with_pwd.yan != this.yan_captcha){
                this.$message({
                    message: '验证码没填对哦',
                    type: 'warning'
                })
                this.user.with_pwd.yan = ''
                this.generateCaptcha()
            } else {
                // console.log(this.user.with_pwd)
                const value = {
                    pwd: this.user.with_pwd.pwd,
                    sno: this.user.with_pwd.sno
                }
                const url = '/api/user-account/login'
                this.fullscreenLoading = true
                axios.post(`${url}?sno=${value.sno}&pwd=${value.pwd}`,{
                        
                    },
                    {
                    headers: {
                        'verifyCode': '2024'
                    }
                })
            .then((response) => {
                if (response.data != 'Account is not found or pwd is wrong') {
                    localStorage.setItem("LoginToken", response.data)
                    localStorage.setItem("isLogined", "true")
                    localStorage.setItem("Sno", this.user.with_pwd.sno)
                    localStorage.removeItem("needLogin")
                    const sno =localStorage.getItem("Sno")
                    const url = "/user" + "/" + sno
                    setTimeout(function() {
                        this.fullscreenLoading = false
                        this.$router.push(url);
                        this.$message({
                            message: '登陆成功',
                            type: 'success'
                        })
                    }.bind(this), 1500)
                } else {
                    throw new Error('密码错误或账号不存在');
                }
            })
            .catch((error) => {
                console.error("登陆失败", error);

                setTimeout(function() {
                    this.fullscreenLoading = false
                    this.$message({
                        message: error.message,
                        type: 'error'
                    });
                }.bind(this), 1500)
            });
            }
        },
        generateCaptcha() {
            const img_w = 100;
            const img_h = 40;
            const char_len = 4;
            const font = '25px Arial';

            const char = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789'.split('');
            let code = '';
            for (let i = 0; i < char_len; i++) {
            code += char[Math.floor(Math.random() * char.length)];
            }
            // console.log(code)
            const canvas = document.createElement('canvas');
            canvas.width = img_w;
            canvas.height = img_h;
            const ctx = canvas.getContext('2d');

            // 背景色
            ctx.fillStyle = 'black';
            ctx.fillRect(0, 0, img_w, img_h);

            // 画点
            for (let i = 0; i < 300; i++) {
            ctx.fillStyle = `rgba(${Math.random() * 255}, ${Math.random() * 255}, ${Math.random() * 255}, 1)`;
            ctx.beginPath();
            ctx.arc(Math.random() * img_w, Math.random() * img_h, 1, 0, 2 * Math.PI);
            ctx.fill();
            }

            // 画线
            for (let i = 0; i < 10; i++) {
            ctx.strokeStyle = `rgba(${Math.random() * 255}, ${Math.random() * 255}, ${Math.random() * 255}, 1)`;
            ctx.beginPath();
            ctx.moveTo(Math.random() * img_w, Math.random() * img_h);
            ctx.lineTo(Math.random() * img_w, Math.random() * img_h);
            ctx.stroke();
            }

            // 画矩形
            ctx.fillStyle = 'rgba(144, 144, 144, 0.8)';
            ctx.fillRect(0, 0, img_w, img_h);

            // 写文字
            ctx.font = font;
            ctx.textAlign = 'left';
            ctx.textBaseline = 'middle';
            ctx.fillStyle = `rgba(${Math.random() * 100}, ${Math.random() * 100}, ${Math.random() * 100}, 1)`;
            const textWidth = ctx.measureText(code).width;
            ctx.fillText(code, (img_w - textWidth) / 2, (img_h + parseInt(font) - 20) / 2);
            // ctx.fillText(code, (textWidth), (textWidth));

            this.yan_captcha = code
            // console.log(this.captcha)
            // console.log(canvas.toDataURL('image/png'));
            this.yan_picUrl = canvas.toDataURL('image/png')
        },
        guestLogin() {
            this.user.with_pwd.sno = '202280310316'
            this.user.with_pwd.pwd = '123456'
            this.user.with_pwd.yan = this.yan_captcha
        }
    }
}
</script>
<style>
.user-Login_left {

    width: 42%;
    min-height: 32vh;
    border-right: 1px solid #e5e9ef;
    /* background-color: aqua; */
}

.user-Login_right {
    width: calc(58% - 1px);
    min-height: 32vh;
    padding-left: 20px;
    /* background-color: antiquewhite; */
}
.user-login_title {
    margin-bottom: 12px;
}
.user-login_title span.active {
    color: #00A1D6;
}
.user-login_title span {
    font-size: 18px;
    padding: 0 16px;
    cursor: pointer;
}
.user-login_title span:nth-child(1) {
    border-right: 1px solid #e5e9ef;
}
.user-Login_right p {
    text-align: left;
    margin-top: 12px;
}
.user-Login_right p:nth-child(1) {
    margin-top: 0;
}
.btnGroup {
    /* height: 64px; */
    /* background-color: #00A1D6; */
}
.login-pathIcon {
    height: 64px;
    display: flex;
    justify-content: flex-start;
}
.login-pathIcon .title {
    position: absolute;

    color: #cfcfd0;
    font-size: 10px;
}
.login-pathIcon .guest {
    font-size: 12px;
    position: absolute;
    left: 48px;
    top: 16px;
}
.i-qq {
    background: url(@/assets/img/icon/qq.png) 50% no-repeat;
    background-size: cover;
    position: absolute;
    padding: 16px;
    margin: 12px 0 0 0;
    cursor: pointer;
}
.i-qq:hover {
    background: url(@/assets/img/icon/qqa.png) 50% no-repeat;
    background-size: cover;
}



@media screen and (max-width: 1120px) {
    .user-Login_left {
        display: none;
    }
    .user-Login_right {
        width: 100%;
    }
}
@media screen and (max-width: 480px) {
    .user-login_title span {
        font-size: 14px;
    }
    .user-Login_right p {
        margin-top: 3px;
    }

}
</style>