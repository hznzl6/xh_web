<template>
    <el-row>
        <el-col :span="20">
            <a name="timu">
                <ul v-for="item in filteredData" :key="item.id" style="margin-top: 48px;">
                    <h3><span>{{ item.stem }}:</span>{{ item.question }}</h3>
                    <p class="answer">{{ item.a }}</p>
                    <p class="answer">{{ item.b }}</p>
                    <p class="answer">{{ item.c }}</p>
                    <p class="answer">{{ item.d }}</p>
                    <div style="display: flex;justify-content: space-between;">
                        <p v-if="isAnswer">答案：{{ item.answer }}</p>
                        <div v-else>
                            <el-button @click="isAnswer = !isAnswer" type="text">
                                <span>显示答案</span>
                            </el-button>
                        </div>
                    </div>
                </ul>
            </a>
        </el-col>

        <el-col :span="4" class="tihao">
            <el-row style="height:calc(99.8vh - 100px - 50px - 350px);border: 1px solid #EBEEF5;border-radius: 10px;">
                <ul style="padding: 0;">
                    <li  v-for="(item, index) in qusList" @click="selectIndex(item.id)" :key="index" style="cursor: pointer;width: 40px;text-align: center;" :class="{active: item.id === currentIndex}" class="tihao-box">
                        {{ index + 1 }}
                        <!-- {{ item.id }} -->
                    </li>
                </ul>
                <div style="display: flex;justify-content: flex-end;">
                    <el-button @click="getAll">换一套</el-button>
                </div>
                </el-row>
                <el-row style="height: 140px;border: 1px solid #EBEEF5;margin: 10px 0;border-radius: 10px;">
                    <p style="font-size: 20px;margin-top: 10px;">答题时间</p>
                    <p style="font-size: 30px;margin-top: 20px;">倒计时: {{ minutes }}:{{ seconds }}</p>

                </el-row>
                <el-row style="height:160px;border: 1px solid #EBEEF5;margin: 10px 0;border-radius: 10px;">
                    <el-col :span="8" class="circumstance">
                        <p>已做题</p>
                    <p>233</p>
                </el-col>
                    <el-col :span="8" class="circumstance">
                        <p>总题数</p>
                    <p>233</p>
                </el-col>
                    <el-col :span="8" class="circumstance">
                        <p>正确率</p>
                    <p>34%</p>
                </el-col>
            </el-row>
        </el-col>
    </el-row>
</template>
<script>
import axios from 'axios';

export default {
    data(){
        return{
            countdown: 1800 ,
            radio: '',
            currentIndex: 0,
            qusList: [ ],
            isAnswer: false
        }
    },
    created() {
        this.getAll()
    },
    computed: {
        minutes() {
            return Math.floor(this.countdown / 60); // 获取分钟数
        },
        seconds() {
            return this.countdown % 60; // 获取秒数
        },
        filteredData() {
            return this.qusList.filter(item => item.id === this.currentIndex);
        }
    },
    mounted() {
        this.timer = setInterval(() => {
        if (this.countdown > 0) {
            this.countdown--;
        } else {
            clearInterval(this.timer);
            // 倒计时结束后的操作
            alert('倒计时结束！');
        }
        }, 1000); // 每隔一秒减少一秒
    },
    methods: {
        getAll() {
            const url = '/api/backstage-bank/getRandom'
            axios.post(url, {}, {
                headers: {
                    'verifyCode': 2024
                }
            }).then(res => {
                this.qusList = res.data
                this.currentIndex = res.data[0].id
            })
        },
        selectIndex(id) {
            this.currentIndex = id
            this.isAnswer = false
        }
    },
    beforeDestroy() {
        clearInterval(this.timer); // 组件销毁前清除定时器
    }
}
</script>
<style>

.timu-box{
    height: 150px;

}

.timu {
  /* width: 500px; */
  /* border-radius: 10px; */
  height: calc(99.8vh - 100px - 50px);
  background-color: white;
  float: left;
  text-align: left;
}
.timu h3 {
    font-size: 32px;
}
.timu .answer {
    font-size: 24px;
}

.tihao{
  /* width: 299px; */
  height: calc(99.8vh - 100px - 50px);
  background-color: white;
  float: left;
  border-left-style: solid;
  border-left-color: #e3e3e3;
  border-left-width: 1px;
}
.xuanzhe{
  cursor: pointer;
}
.tihao-box{
  width: 40px;
  height: 40px;
  background-color: #e3e3e3;
  cursor: pointer;
  list-style: none;
  display: inline-block;
  margin-left: 5px;
  margin-block: 5px;
  line-height: 40px;
  border-left-style: solid;
  border-left-color: #bab9b9;
  border-left-width: 1px;
  
}
.tihao-box:hover {
    background-color: #bab9b9;
    color: #fff;
}
.tihao-box.active {
    background-color: brown;
    color: #fff;
}
.circumstance{
    /* background-color: aqua; */
    margin-top: 16PX;
    height: 100px;
    border-radius: 10px;
}
.circumstance :nth-child(1){
    border-right: 1px solid #ebeef5;
    height: 50px;

}
.selected{
  background-color: brown;
}
</style>