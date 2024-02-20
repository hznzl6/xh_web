<template>
    <div style="display: flex;">
        <!-- 左边图片 height: 40vh;-->
        <div class="user-Login_left">
            <el-empty description="图片正在来的路上..."></el-empty>
        </div>
        <div class="user-Login_right">
            <div class="user-login_title">
                <span :class="{active: login_path === 'pwd'}" @click="changePath('pwd')">密码登陆</span><span :class="{active: login_path === 'mail'}" @click="changePath('mail')">邮箱登陆</span>
            </div>
            <div v-show="login_path == 'pwd'">
                <p>账号:</p>
                <el-input clearable v-model="user.with_pwd.sno" placeholder="输入学号" maxlength="13"></el-input>
                <p>密码:</p>
                <el-input clearable v-model="user.with_pwd.pwd" placeholder="输入密码" type="password" maxlength="16"></el-input>
                <p>验证码:</p>
                <el-input clearable v-model="user.with_pwd.yan" placeholder="输入验证码" maxlength="6"></el-input>
                <img style="float: left;margin-top: 10px;" :src="yan_picUrl" alt="验证码" />
                <el-button @click="Login_pwd" style="float: right;margin-top: 10px;">登陆</el-button>
            </div>
            <div v-show="login_path == 'mail'">
                <p>邮箱:</p>
                <el-input clearable v-model="user.account" placeholder="输入邮箱" maxlength="13"></el-input>
                <p>验证码:</p>
                <el-input clearable v-model="user.yan" placeholder="输入验证码" maxlength="6"></el-input>
                <el-button style="float: right;margin-top: 10px;" disabled>开发中..</el-button>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    data() {
        return {
            user: {
                with_pwd: {
                    sno: '',
                    pwd: '',
                    yan: ''
                },
                with_mail: {
                    mail: '',
                    pwd: '',
                    yan: ''
                }
            },
            login_path: 'pwd',
            yan_picUrl: '',
            yan_captcha: ''
        }
    },
    mounted() {
        this.generateCaptcha()
    },
    methods: {
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
                const url = process.env.VUE_APP_BASE_URI + '/shetuan/login'
                fetch(`${url}?pwd=${value.pwd}&sno=${value.sno}`, {
                    method: "POST",
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                })
                .then(response => {
                    if (response.status === 200) {
                        this.$message({
                            message: '登陆成功',
                            type: 'success'
                        })
                        return response.text();
                    } else {
                        throw new Error('密码错误或账号不存在');
                    }
                })
                .then(res => {
                    // console.log(res)
                    localStorage.setItem("LoginToken", res)
                    localStorage.setItem("isLogined", "true")
                    localStorage.setItem("Sno", this.user.with_pwd.sno)
                    const sno =localStorage.getItem("Sno")
                    const url = "/user" + "/" + sno
                    this.$router.push(url);
                })
                .catch(error => {
                    // console.error(error);
                    this.$message({
                        message: error.message,
                        type: 'error'
                    });
                });
                // else {
                //         this.$message({
                //             message: '密码错误或账号不存在',
                //             type: 'warning'
                //         })
                //     }
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
    }
}
</script>
<style>
.user-Login_left {

    width: 42%;
    height: 32vh;
    border-right: 1px solid #e5e9ef;
    /* background-color: aqua; */
}
.user-Login_right {
    width: calc(58% - 1px);
    height: 32vh;
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