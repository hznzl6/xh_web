<template>
    <div class="aiask" v-loading.fullscreen.lock="fullscreenLoading">
        <h1 class="ai">AI问答</h1>
       <ComtentView v-if="Messages == ''"/>

       <div v-else class="chatArea">
            <ul class="message_forChat">
                <li v-for="(message, index) in Messages" :key="index" :class="index % 2 === 0 ? 'Role_User' : 'Role_AI'">{{ message.content }}</li>
            </ul>
        </div>
        <div style="position:fixed;width: 100%;bottom: 0;">
            <div class="input">
                <el-input
                type="textarea"
                :autosize="{ minRows: 1, maxRows: 8}"
                placeholder="请输入内容"
                v-model="inputMessage"
                resize="none"
                @keyup.native.enter="SendMessage">
                </el-input>
                <!-- <div class="outline"><i class="el-icon-picture-outline" style="position: absolute;bottom: 2px;right: 100px;"></i></div>
                <div class="outline"><i class="el-icon-microphone" style="position: absolute;bottom: 2px;right: 70px;"></i></div> -->
                <el-button class="el-icon-s-promotion position" style="position: absolute;bottom: 0;right: 20px;" @click="SendMessage" type="text">
                    
                </el-button>
            </div>
        </div>
    </div>
</template>
<script>
import { v4 as uuidv4 } from 'uuid';
import axios from 'axios'
import ComtentView from '@/views/function/Ai/ComtentView.vue'
export default {
    components: {
        ComtentView
    },
    data() {
        return {
            inputMessage: '',
            Messages: [],
            fullscreenLoading: false
        }
    },
    created() {
        if(localStorage.getItem('UUID') == null) {
            this.generateUUID()
        }
    },
    methods: {
        generateUUID() {
            const uuid = uuidv4();
            // 在这里可以将UUID存储到本地存储或做其他操作
            // console.log(uuid);
            localStorage.setItem('UUID', uuid)
        },
        SendMessage() {
            this.fullscreenLoading = true
            // console.log(this.inputMessage)
            this.Messages.push({ "role": "user", "content": this.inputMessage })
            const url = '/api/ai-message/sendQuestion'
            // axios.post(`${url}?question=${this.inputMessage}&uuid=${localStorage.getItem('UUID')}`, {}, {
            //     headers: {
            //         'verifyCode': '2024'
            //     }
            // })
            axios.post(`${url}?question=${encodeURIComponent(JSON.stringify(this.Messages.map(item => {
                return { 
                    role: item.role, 
                    content: item.content 
                };
            })))}&uuid=${localStorage.getItem('UUID')}`, {}, {
                headers: {
                    'verifyCode': '2024'
                }
            })
            .then(res => {
                // this.message = res.data
                // this.Messages.push({ "role": "assistant", "content": res.data })
                if (res.data.includes("科大讯飞自主研发的认知智能大模型")) {
                    this.Messages.push({ role: "assistant", content: "我是Ganxy喵~" });
                } else {
                    this.Messages.push({ role: "assistant", content: res.data });
                }
                this.fullscreenLoading = false
            })
            this.inputMessage = ''
        }
    }
}
</script>
<style>
.aiask .input{
    /* display: block; */
    /* display: inline-block; */
    /* position: fixed; */
    position: relative;
    bottom: 55px;
    width: 60%;
    left: calc(10% + 50px);
    /* background-color: aqua; */
    /* margin: 0 90px; */
    /* display: flex;
    justify-content: center;
    align-items: center; */
    /* background-color: #fff; */
}
.aiask .input i{
    margin: 0 10px;
    /* text-align: right; */
    /* display: block; */
    /* vertical-align: middle; */
}
.aiask .outline{
    font-size: 20px;
}
.aiask .outline:hover{
    cursor: pointer;
}
.aiask .position{
    font-size: 25px;
    color: rgb(27, 5, 99);
}
.aiask .position:hover{
    cursor: pointer;
}
/* .el-icon-picture-outline:before{
    position: absolute;bottom: 2px;right: 100px;width: 50px;height: 20px;
} */
.aiask .el-icon-search{
    position: absolute;
    bottom: 2px;
    right: 0;
}
.aiask #story{
    /* 去除边框 */
border: none;
resize: none;
/* 去除选中后的边框 */
outline:none;
/* margin: 0 auto; */
/* padding:0 auto; */
padding: 12px;
}
 textarea {
    /* bottom: 10px; */
    /* margin-top: 10px; */
    font-size: 16px;
    max-height: 150px;
    overflow: hidden;
    width: 100%;
    padding: 0;
    margin: 0;
    border: none;
  }

  
/* .aiask{
    background-color: #fff;
} */
.aiask{
    position: relative;
}
.aiask .ai{
    margin: 12px 0 28px ;
}




.el-textarea__inner{
    overflow-y: auto;
    padding-bottom: 24px;
    padding-right: 16px;
}



.aiask .chatArea {
    height: calc(100vh - 160px - 44px - 24px);
}
.aiask .message_forChat li {
    padding: 12px 6px;
    border-radius: 10px;
    list-style: none;
}
.aiask .Role_User {
    text-align: right;
    background-color: rgba(0, 0, 0, 0.1);
}
.aiask .Role_AI {
    text-align: left;
    background-color: rgba(247, 247, 248, 0.1);
}

.aiask .Role_User, .aiask .Role_AI {
    opacity: 0;
}
.aiask .Role_User {
    animation: slideRight 1s forwards;
}
.aiask .Role_AI {
    animation: slideLeft 1s forwards;
}
@keyframes slideRight {
    from {
      transform: translateX(100%);
      opacity: 0;
}
    to {
      transform: translateX(0);
      opacity: 1;
    }
}
@keyframes slideLeft {
    from {
      transform: translateX(-100%);
      opacity: 0;
    }
    to {
      transform: translateX(0);
      opacity: 1;
    }
}
</style>