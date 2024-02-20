<template>
    <div class="houtai-LoginContainer">
        <div class="houtai-LoginContainer_LoginCard">
            <div class="houtai-LoginContainer_LoginForm">
                <div class="login-form">
                    <h2>社团管理|登录</h2>
                    <el-form ref="form" :model="LoginForm" label-width="80px">
                        <el-form-item label="账号:">
                            <el-input maxlength="10" placeholder="输入账号" v-model="LoginForm.account"></el-input>
                        </el-form-item>
                        <el-form-item label="密码:">
                            <el-input maxlength="13" placeholder="输入密码" v-model="LoginForm.pwd"></el-input>
                        </el-form-item>
                        <el-form-item label="验证码:">
                            <el-col :span="16">
                                <el-input maxlength="4" placeholder="输入验证码" v-model="LoginForm.yan"></el-input>
                            </el-col>
                            <el-col :span="8">
                                <p @click="GenerateVerifyCode" style="border: 1px solid #e3e3e3;color: #9a9a9a;line-height: 14px;padding: 6px;cursor: pointer;">{{ verifyCode }}<br><span style="font-size: 9px;">点击换一个</span></p>
                            </el-col>
                        </el-form-item>
                        <div style="display: flex;justify-content: flex-end;">
                            <el-button @click="Login">登陆</el-button>
                        </div>
                    </el-form>
                    <div class="AdvertisingSpace">
                        <el-carousel :interval="5000" trigger="click" arrow="never">
                            <el-carousel-item>
                                <img src="../../../assets/img/houtai/lunbo0.jpg" alt="">
                            </el-carousel-item>
                            <el-carousel-item>
                                <img src="../../../assets/img/houtai/lunbo1.jpg" alt="">
                            </el-carousel-item>
                            <el-carousel-item>
                                <img src="../../../assets/img/houtai/lunbo2.jpg" alt="">
                            </el-carousel-item>
                        </el-carousel>
                    </div>
                    <p class="pfoot">&copy;Ganxy|2024</p>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    data() {
        return {
            LoginForm: {
                account: '',
                pwd: '',
                yan: ''
            },
            verifyCode: '',
            LoginToken: '',
            toLinkMass: ''
        }
    },
    created() {
        this.GenerateVerifyCode()
    },
    methods: {
        GenerateVerifyCode() {
            let characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
            let codeLength = 4;
            let verificationCode = '';
            
            for (let i = 0; i < codeLength; i++) {
                let randomIndex = Math.floor(Math.random() * characters.length);
                verificationCode += characters.charAt(randomIndex);
            }
            this.verifyCode = verificationCode;
        },
        Login() {
            if(this.LoginForm.account == '') {
                this.$message({
                    message: '账号不能为空',
                    type: 'warning'
                })
            } else if(this.LoginForm.pwd == '') {
                this.$message({
                    message: '密码不能为空',
                    type: 'warning'
                })
            } else if(this.LoginForm.yan == '') {
                this.$message({
                    message: '验证码不能为空',
                    type: 'warning'
                })
            } else if(this.LoginForm.yan != this.verifyCode) {
                this.$message({
                    message: '验证码不正确',
                    type: 'warning'
                })
                this.GenerateVerifyCode()
                this.LoginForm.yan = ''
            } else {
                console.log(this.LoginForm)
                const url = process.env.VUE_APP_BASE_URI + '/shetuan-manager/manager-login'
                const account = this.LoginForm.account
                const pwd = this.LoginForm.pwd
                fetch(`${url}?account=${account}&pwd=${pwd}`, {
                    method: "POST",
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                })
                .then(res => res.text())
                .then(res => {
                    if(res == 'Account not found or pwd is wrong') {
                        this.$message({
                             message: '密码错误或账号不存在',
                             type: 'warning'
                        })
                        this.LoginForm = ''
                        return
                    }
                    this.LoginToken = res
                    // console.log(res)
                    localStorage.setItem("ManagerToken", res)
                    this.$message({
                        message: '登陆成功',
                        type: 'success'
                    })
                    localStorage.setItem("isManager", "true")
                    const url2 = process.env.VUE_APP_BASE_URI + '/shetuan-manager/ReturnMass'
                    fetch(`${url2}?account=${account}`, {
                        method: "POST",
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json'
                        },
                    })
                    .then(res => res.text())
                    .then(res => {
                        this.toLinkMass = res
                        // console.log(res)
                        this.$router.push("/manager/"+this.toLinkMass)
                    })
                })
            }
           

        }
    }
    
}
</script>
<style>
.houtai-LoginContainer {
    background: url("http://ganxy03.cn/img/background-image.jpg") 100% no-repeat;
    background-size: cover;
    height: 100vh;
    width: 100%;
}
.houtai-LoginContainer_LoginCard {
    width: 780px;
    height: 100vh;
    background-color: rgba(0, 0, 0, 0.1);
    display: flex; 
    justify-content: center; /* 水平居中 */
    align-items: center; /* 垂直居中 */
}
.houtai-LoginContainer_LoginForm {
    height: calc(100vh - 200px);
    width: 100%;
    /* background-color: antiquewhite; */
}
.login-form {
    width: 80%;
    min-height: calc(100vh - 240px);
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ddd;
    border-radius: 5px;
    background-color: #f4f4f4;
    text-align: center;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}
.login-form h2 {
    margin-bottom: 20px;
    color: #333;
}
.AdvertisingSpace {
    width: calc(100% - 40px);
    height: calc(100vh - 300px - 240px);
    padding: 20px;
    /* background-color: aquamarine; */
}

.AdvertisingSpace img {
    height: 300px;
}
/* height: 300px; */
@media screen and (max-width: 780px) {
    .houtai-LoginContainer_LoginCard {
        width: 96%;
        margin: 0 auto;
    }
    .login-form {
        min-height: calc(100vh - 300px)
    }
    .AdvertisingSpace {
        height: calc(100vh - 320px - 320px);
    }
    .AdvertisingSpace img {
        height: auto;
        width: 100%;
    }
    .AdvertisingSpace .el-carousel__container {
        height: 240px;
    }
}

@media screen and (max-width: 415px) {
    .AdvertisingSpace {
        display: none;
    }
    .login-form {
        position: relative;
    }
    .pfoot {
        position: absolute;
        bottom: 10px;
        width: calc(100% - 40px);
    }
}
</style>

