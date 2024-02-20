<template>
    <div class="AiContainer">
        <el-container>
            <el-header style="height: 100px;">
              <CommonHead />
            </el-header>
            <el-container>
                    <el-aside width="200px">
                      <div @click="newChat" style="color: #fff;margin: 5px;border: 1px solid rgba(255,255,255,.6);padding: 6px;border-radius: 5px;cursor: pointer;"><i class="el-icon-plus" style="padding: 0 10px 0 0;"></i>开始新的对话</div>
                      <div style="text-align: left;margin: 6px 12px;border-bottom: 2px solid #e3e3e3;padding: 12px 0;">
                        历史记录
                      </div>
                      <ul style="width: 100%;height: 640px;overflow-y: auto;">
                        <li style="margin: 0 12px;list-style: none;padding: 6px 0;cursor: pointer;" v-for="item in historyList" :key="item.uuid">
                          <p @click="show(item.uuid)">{{ item.uuid }}</p>
                        </li>
                      </ul>
                      <div style="position: relative;min-height: calc(100vh - 60px - 50px - 50px - 680px);">
                        <div @click="viewUseCode" style="color: #fff;margin: 5px;border: 1px solid rgba(255,255,255,.6);padding: 6px;border-radius: 5px;cursor: pointer;position: absolute;bottom: 0;width: calc(100% - 22px)"><i class="el-icon-search" style="padding: 0 10px 0 0;"></i>查看使用码</div>
                        <div @click="mianze" style="color: #fff;margin: 5px;border: 1px solid rgba(255,255,255,.6);padding: 6px;border-radius: 5px;cursor: pointer;position: absolute;bottom: 45px;width: calc(100% - 22px)"><i class="el-icon-info" style="padding: 0 10px 0 0;"></i>免责声明</div>
                      </div>
                    </el-aside>
                <el-container>
                    <el-main>
                      <div class="dialogContainer" ref="dialogContainer">
                        <div v-for="(message, index) in dialogue" :key="index">
                          <div v-if="message.role === 'user'" class="dialogue-user">
                            {{ message.message }}
                          </div>
                          <div v-if="message.role === 'ai'" class="dialogue-ai">
                            <div v-html="message.message"></div>
                          </div>
                        </div>
                      </div>
                      <div class="AiContainer-inputFrame">
                        <el-input @keydown.enter.native="SendMessage" v-model="question" placeholder="发消息给AI"></el-input>
                        <el-button @click="SendMessage">发送</el-button>
                      </div>
                    </el-main>
                    <el-footer>&copy;Ganxy|2024</el-footer>
                </el-container>
            </el-container>
        </el-container>
    </div>
</template>
<script>
import marked from 'marked';
import { v4 as uuidv4 } from 'uuid';
import CommonHead from '@/components/CommonHead.vue';
export default {
  components: {
    CommonHead
  },
  data() {
    return {
      question: '',
      dialogue: [],
      uuid: '',
      historyList: []
    }
  },
  created() {
    // this.getAllMessage()
    // this.GenerateUUID()
    this.GetHistoryByUseCode()
    this.showRecord(this.$route.params.uuid)
    this.uuid = this.$route.params.uuid
    console.log(this.$route.params.uuid)
  },
  mounted() {
    this.scrollToBottom();
  },
  methods: {
    mianze() {
      this.$alert('<p style="text-indent: 2em">本服务仅供个人学习、学术研究目的使用，未经许可，请勿分享、传播输入及生成的文本、图片内容。</p><p style="text-indent: 2em">您确认并知悉本服务生成的所有内容都是由人工智能模型生成，所以可能会出现意外和错误的情况，请确保检查事实。</p><p style="text-indent: 2em">本服务来自于法律法规允许的包括但不限于公开互联网等信息积累，因互联网的开放性属性，不排除其中部分信息具有瑕疵、不合理或引发不快。遇有此情形的，欢迎并感谢您随时通过 2192767718@qq.com 邮箱举报。</p><p style="text-align: right;">Ganxy<br>02-16</span></p>', '免责声明', {
        dangerouslyUseHTMLString: true,
        callback: action => {
          this.$message({
            type: 'info',
            message: `action: ${ action }`
          });
        }
      });
    },
    viewUseCode() {
      this.$alert('55D6D8', '标题名称', {
        confirmButtonText: '确定',
        callback: action => {
          this.$message({
            type: 'info',
            message: `action: ${ action }`
          });
        }
      });
    },
    newChat() {
      const newUuid = uuidv4(); // 生成新的uuid
      this.$router.push(`/ai/${newUuid}`); // 将新的uuid作为参数传递给新会话页面
      setTimeout(() => {
        location.reload();
      }, 300);
    },
    scrollToBottom() {
      this.$nextTick(() => {
        setTimeout(() => {
          const container = this.$refs.dialogContainer;
          if (container) {
            container.scrollTop = container.scrollHeight;
          }
        }, 100);
      });
    },
    showRecord(uuid) {
      const url = process.env.VUE_APP_BASE_URI + '/ai-message/getByUUID'
      fetch(`${url}?uuid=${uuid}`, {
        method: "POST",
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
      })
      .then(res => res.json())
      .then(res => {
        this.dialogue = res
        if(this.dialogue != null) {
          // // 保存用户发送的问题
          // this.dialogue.push({ role: 'user', message: res.message });
          // // 保存AI返回的消息
          // this.dialogue.push({ role: 'ai', message: res.message });
          // 保存用户发送的问题
          const userMessage = { role: 'user', message: this.question };
          if (userMessage.message) {
            this.dialogue.push(userMessage);
          }

          // 保存AI返回的消息
          const aiMessage = { role: 'ai', message: res.message };
          if (aiMessage.message) {
            this.dialogue.push(aiMessage);
          }
        }
      })
    },
    show(uuid) {
      this.$router.push("/ai/"+uuid)
      // location.reload()
      setTimeout(() => {
        location.reload()
      }, 300);
    },
    GetHistoryByUseCode() {
      const url = process.env.VUE_APP_BASE_URI + '/ai-message/getHistoryByUseCode'
      const useCode = '55D6D8'
      fetch(`${url}?useCode=${useCode}`, {
        method: "POST",
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
      })
      .then(res => res.json())
      .then(res => {
        // console.log(res)
        this.historyList = res
      })
    },
    GenerateUUID() {
      this.uuid = uuidv4()
      localStorage.setItem("UUID", this.uuid)
    },
    SendMessage() {
      const url = process.env.VUE_APP_BASE_URI + '/ai-message/sendQuestion'
      const question = this.question
      const initiator = 'Ganxy'
      const use_code = '55D6D8'
      const uuid = this.uuid
      if(uuid === '' || uuid === 'undefined') {
        const generatedUuid  = uuidv4()
        this.uuid = generatedUuid
        fetch(`${url}?question=${question}&initiator=${initiator}&use_code=${use_code}&uuid=${generatedUuid}`, {
          method: "GET",
          headers: {
            'Accept': 'text/plain;charset=UTF-8',
            'Content-Type': 'application/json'
          },
        })
        .then(res => res.text())
        .then(answer => {
          const html = marked(answer)
          console.log(html);
          if(this.dialogue != null) {
            // 保存用户发送的问题
            this.dialogue.push({ role: 'user', message: this.question });
            // 保存AI返回的消息
            this.dialogue.push({ role: 'ai', message: html });
          }
          // 重置输入框
          this.question = '';
        })
        .catch(error => {
          console.error(error);
          // 错误处理
        });
        this.$router.push("/ai/"+generatedUuid)
        return
      }
      fetch(`${url}?question=${question}&initiator=${initiator}&use_code=${use_code}&uuid=${uuid}`, {
        method: "GET",
        headers: {
          'Accept': 'text/plain;charset=UTF-8',
          'Content-Type': 'application/json'
        },
      })
      .then(res => res.text())
      .then(answer => {
        const html = marked(answer)
        console.log(html);
        if(this.dialogue != null) {
          // 保存用户发送的问题
          this.dialogue.push({ role: 'user', message: this.question });
          // 保存AI返回的消息
          this.dialogue.push({ role: 'ai', message: html });
        }
        // 重置输入框
        this.question = '';
      })
      .catch(error => {
        console.error(error);
        // 错误处理
      });
    },
    // getMessageByUUID() {
    //   const url = process.env.VUE_APP_BASE_URI + '/ai-message/getByUUID'
    //   fetch(url, {
    //     method: "POST",
    //     headers: {
    //       'Accept': 'application/json',
    //       'Content-Type': 'application/json'
    //     },
    //   })
    //   .then(res => res.json())
    //   .then(res => {
    //     this.dialogue = res
    //     this.dialogue.push({ role: 'user', message: res.message });
    //     this.dialogue.push({ role: 'ai', message: res.message });
    //   })
    // }
  }
}
</script>
<style>
  .AiContainer .el-header {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
  }
  .AiContainer .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    height: 60px;
    line-height: 60px;
  }
  
  .AiContainer .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    min-height: calc(100vh - 100px);
  }
  
  .AiContainer .el-main {
    padding: 0;
    margin: 0;
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    min-height: calc(100vh - 160px);
    position: relative;
  }
  .AiContainer-inputFrame {
    position: absolute;
    width: 100%;
    bottom: 0;
    display: flex;
  }
  .AiContainer-inputFrame .el-input {
    justify-content: flex-start;
    width: calc(100% - 40px);
  }
  .AiContainer-inputFrame .el-button {
    justify-content: flex-end;
  }

  .dialogue-user {
    text-align: right;
    background-color: #FFF;
    padding: 10px;
    margin: 10px;
  }
  .dialogue-ai {
    text-align: left;
    background-color: #D3DCE6;
    padding: 10px;
    margin: 10px;
  }
  .dialogContainer {
    height: calc(100vh - 160px);
    overflow-y: auto;
    
  }
  /* 灰色  444654   */
  @media screen and (max-width: 768px) {
    .AiContainer .el-aside {
        display: none;
    }
    .AiContainer .el-footer, .AiContainer .el-main {
        width: 100%;
    }
  }
</style>
<!-- <template>
  <div class="dialog-container">
    <div class="dialog-header">
      Header
    </div>
    <div class="dialog-content">
      <div v-for="message in messages" :key="message.id" class="dialog-message">
        <div v-if="message.type === 'user'" class="user-message">
          {{ message.text }}
        </div>
        <div v-else class="ai-message">
          {{ message.text }}
        </div>
      </div>
    </div>
    <div class="dialog-input">
      <el-input v-model="inputText" placeholder="发消息给AI"></el-input>
      <el-button @click="sendMessage">发送</el-button>
    </div>
    <div class="dialog-footer">
      Footer
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      messages: [
        { id: 1, text: "Hello!", type: 'ai' },
        { id: 2, text: "Hi there!", type: 'user' },
      ],
      inputText: '',
    };
  },
  methods: {
    sendMessage() {
      if (this.inputText.trim() !== '') {
        this.messages.push({ id: Date.now(), text: this.inputText, type: 'user' });
        // 在这里调用AI模型处理输入消息并返回回复
        this.inputText = '';
      }
    },
  },
};
</script>
<style scoped>
.dialog-container {
  display: flex;
  flex-direction: column;
  height: 100vh;
}
.dialog-header, .dialog-footer {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  height: 60px;
  line-height: 60px;
}
.dialog-content {
  flex-grow: 1;
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  padding: 10px;
  overflow-y: auto;
}
.dialog-message {
  margin-bottom: 10px;
}
.user-message {
  text-align: left;
}
.ai-message {
  text-align: right;
}
.dialog-input {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #fff;
  padding: 10px;
}
</style> -->