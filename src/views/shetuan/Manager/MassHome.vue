<!-- <template>
    <div class="manager-home">
        <h2>社团主页</h2>
    </div>
</template>
<script>
export default {
    data() {
        return {
            
        }
    },
    created() {
        
    },
    methods: {
        
    }
}
</script>
<style>
.manager-home {
    width: 100%;
    height: calc(100vh - 120px);
    background-color: rgba(22, 55, 44, 0.6);
}
</style>
 -->
 <template>
    <div class="manager-home">
        <h2 style="font-size: 36px;padding: 6px 0;">社团主页</h2>
        <el-card class="message">
            <h2 style="font-size: 36px;padding: 6px 0;">社团信息</h2>
            <p style="font-size: 24px;text-align: left;font-weight: 500;">社团介绍</p>
            <p style="text-align: left;">  互联网信息协会创办于2011年，归属于网络小组，现社团有成员三十余人，各部长，社长共计八人。我们致力于用技术创新服务</p>
            <p style="text-align: left;">我们的slogan：热爱代码，码到世界充满爱。</p>
            <p style="font-size: 24px;text-align: left;font-weight: 500;margin-top: 20px;">部门组织</p>
            <p style="text-align: left;margin-top: 10px;">技术部：负责相关服务及技术的开发与维护</p>
            <p style="text-align: left;margin-top: 10px;">组织部：负责相关服务、活动的人员安排及统筹</p>
            <p style="text-align: left;margin-top: 10px;">宣传部负责相关服务、活动宣传</p>
            <p style="text-align: left;margin-top: 20px;">现技术部负责人：朱引航 彭清玄</p>
            <p style="text-align: left;margin-top: 10px;">现组织部负责人：王小庆 吴可</p>
            <p style="text-align: left;margin-top: 10px;">现宣传部负责人：黄家祥 王欣宇</p>
            <p style="text-align: left;margin-top: 10px;">现社团负责人：甘先懿 王文俊</p>

        </el-card>
        <el-card class="activity">
            <h2 style="font-size: 30px;">社团活动</h2>
            <el-table
                               
                                :data="MassActivity"
                                stripe>
                                <el-table-column
                                prop="apply_time"
                                label="日期"
                                width="200">
                                </el-table-column>
                                <el-table-column>
                                    <template slot="header">
                                        <el-select v-model="setlectOption" placeholder="请选择" >
                                            <el-option
                                            v-for="item in options"
                                            :key="item.label"
                                            :label="item.label"
                                            :value="item.value">

                                            </el-option>
                                        </el-select>
                                        <div style="float: right;">
                                            <el-button  size="mini">我的活动</el-button>
                                        </div>
                                    </template>
                                    <el-table-column
                                    prop="title"
                                    label="活动">
                                    </el-table-column>
                                    <el-table-column
                                    prop="sno"
                                    label="发起人">
                                    </el-table-column>
                                    <!-- <el-table-column
                                    prop="name"
                                    label="起止日期">
                                    </el-table-column> -->
                                    <el-table-column
                                    label="操作">
                                        <template slot-scope="scope">
                                            <el-button @click="applyJoin(scope.row.id)">参加</el-button>
                                        </template>
                                    </el-table-column>
                                </el-table-column>
                            </el-table>
        </el-card>
        <el-card class="notice">
            <h2 style="font-size: 30px;">社团通知</h2>
            <el-col class="leftNav" :span="6">
                                    <ul class="navContainer">
                                        <li v-for="item in annoList" :key="item.id" class="navItem">
                                            <span>{{ item.title }}</span>
                                            <template>
                                                <el-button  type="text">查看</el-button>
                                            </template>
                                        </li>
                                    </ul>
                                </el-col>
                                <!-- <el-empty v-if="annoContent == ''"></el-empty>
                                    <div v-else>{{ annoContent }}</div> -->
                             <!-- <ul>
                                <li style="list-style: none;text-align: left;line-height: 30px;">通知1</li>
                                <li style="list-style: none;text-align: left;line-height: 30px;">通知2</li>
                                <li style="list-style: none;text-align: left;line-height: 30px;">通知3</li>
                                <li style="list-style: none;text-align: left;line-height: 30px;">通知4</li>
                                <li style="list-style: none;text-align: left;line-height: 30px;">通知5</li>
                                <li style="list-style: none;text-align: left;line-height: 30px;">通知6</li>
                             </ul>        -->
        </el-card>
    </div>
</template>
<script>
import axios from 'axios'

// import JoinActivity from './module/JoinActivity.vue'
export default {
    data() {
        return {
            annoList: [],
            MassActivity: [],
            setlectOption: '即将开始',
            options: [
                {
                    label: '即将开始',
                    value: '即将开始'
                },
                {
                    label: '进行中',
                    value: '进行中'
                },
                {
                    label: '已结束',
                    value: '已结束'
                }
            ],
        }
    },
    created() {
        this.getMassActivity()
    },
    methods: {
        getMassActivity() {
            const url = '/api/backstage-activity/getActivities'
            axios.post(`${url}?mass=${localStorage.getItem('mass')}`, {}, {
                headers: {
                    'verifyCode': '2024'
                }
            }).then(res => {
                this.MassActivity = res.data
                // console.log(res.data)
            })
        },
       
        applyJoin(target) {
            const url = '/api/user-applyActivity/apply'
            axios.post(`${url}?mass=${localStorage.getItem('mass')}&sno=${this.$route.params.sno}&target=${target}`, {}, {
                headers: {
                    'verifyCode': '2024'
                }
            }).then(res => {
                if(res.data == 'Apply success') {
                    this.$message({
                        message: '申请成功！',
                        type: 'success'
                    })
                } else if(res.data == 'Have Applied') {
                    this.$message({
                        message: '你已经申请过了！',
                        type: 'warning'
                    })
                }
            })
        },
    }
}
</script>
<style>
.manager-home {
    width: 100%;
    height: calc(100vh - 120px);
    /* background-color: rgba(22, 55, 44, 0.6); */
    padding: 24px;
    padding-top: 0;
}
.message{
    height: 600px;
    width: 50%;
    float: left;
    margin-right: 24px;
    margin-left: 20px;
}
.activity{
    height: 285px;
    width: 45%;
    float: left;
    margin-bottom: 24px;
}
.notice{
    height: 285px;
    width: 45%;
    float: left;
}
.leftNav {
    margin: 12px 0;
    min-height: 330px;
    border-right: 1px solid #eae8e8;
}
.navContainer {
    padding: 0;
    margin: 0 12px;
    list-style: none;
}
.navItem {
    line-height: 39.33px;
    display: flex;
    justify-content: space-between;
    /* background-color: aqua; */
    font-size: 13px;
    padding: 0 8px;
    border-bottom: 1px solid #eae8e8;
}
</style>