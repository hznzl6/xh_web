<template>
    <div class="stepsdemo-content">
        <div style="display: flex;line-height: 40px;justify-content: space-between;margin: 6px 0;">
            <p>邮箱:</p>
            <el-select v-model="mailSuffix" slot="prepend" placeholder="请选择">
                <el-option label="QQ邮箱" value="@qq.com"></el-option>
                <!-- <el-option label="163邮箱" value="@163.com"></el-option>
                <el-option label="139邮箱" value="@139.com"></el-option> -->
            </el-select>
        </div>
        <el-input @input="mailInput" class="input-with-select" v-model="mail" clearable placeholder="输入邮箱" maxlength="13">
            <el-button slot="append">{{ this.mailSuffix }}</el-button>
        </el-input>
        <p>密码:</p>
        <el-input @input="handInput" type="password" v-model="pwd" clearable placeholder="输入密码" maxlength="20">
            
        </el-input>
        <p>确认密码:</p>
        <el-input type="password" @input="handInput" v-model="rePwd" clearable placeholder="确认密码" maxlength="20">
            
        </el-input>
        <p>验证码:</p>
        <el-input @input="yanInput" v-model="yan" clearable placeholder="输入验证码" maxlength="6">
            <el-button v-if="!disableSendButton" @click="sendMail" slot="append">发送</el-button>
            <el-button :disabled="disableSendButton">@{{ countdown }}s</el-button>
        </el-input>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    data () {
        return {
            mail: '',
            pwd: '',
            rePwd: '',
            yan: '',
            yan_picUrl: '',
            yan_captcha: '',
            mailSuffix: '@qq.com',
            countdown: 120,
            disableSendButton: false
        }
    },
    created() {
        this.generateCaptcha()
    },
    methods: {
        sendMail() {
            this.generateCaptcha()
            if(this.mail == '') {
                this.$message({
                    message: '邮箱不能为空',
                    type: 'warning'
                })
                return
            }
            const html = '<div style="text-align: center;"><img style="width: 100%;max-width: 50%;max-height: 50%;height: auto;" src="' + this.yan_picUrl + '" alt="验证码" /></div>';
            this.$prompt(html, '验证', {
                dangerouslyUseHTMLString: true,
                inputPattern: /^.{1,}$/, // 输入框校验规则，这里使用任意字符
                inputErrorMessage: '验证码不能为空', // 输入框校验提示信息
                showCancelButton: true, // 显示取消按钮
                cancelButtonText: '取消', // 取消按钮文本
                confirmButtonText: '确定', // 确定按钮文本
                callback: (action, value) => {
                    if (action === 'confirm') {
                        if(value._data.inputValue != this.yan_captcha) {
                            this.generateCaptcha()
                            this.$message({
                                message: '验证码错误',
                                type: 'warning'
                            })
                            this.sendMail()
                            return
                        }
                        
                        // console.log('发送验证码')
                        const url = '/api/mail/send-mail'
                        axios.post(`${url}?email=${this.mail+this.mailSuffix}`,{
                                
                            },
                            {
                            headers: {
                                'verifyCode': '2024'
                            }
                        }).then((res) => {
                            if(res.data == 'Send success.') {
                                this.$message({
                                    message: '发送成功',
                                    type: 'success'
                                })
                                // 启动倒计时
                                this.countdown = 120;
                                this.disableSendButton = true; // 禁用发送按钮
                                const timer = setInterval(() => {
                                    if (this.countdown > 0) {
                                        this.countdown--;
                                    } else {
                                        clearInterval(timer);
                                        this.disableSendButton = false; // 恢复发送按钮可用
                                    }
                                }, 1000);
                            } else {
                                this.$message({
                                    message: '发送失败',
                                    type: 'warnging'
                                })
                            }
                        })
                    } else if (action === 'cancel') {
                        // console.log("")
                    }
                }
            });
        },
        yanInput() {
            localStorage.setItem('yan', this.yan)
        },
        mailInput() {
            localStorage.setItem('mail', this.mail+this.mailSuffix)
        },
        handInput() {
            localStorage.setItem('pwd', this.pwd)
            localStorage.setItem('rePwd', this.rePwd)
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
    .input-with-select .el-input-group__prepend {
        background-color: #fff;
    }
</style>