<template>
    <div class="MyClub-container">
        <el-row class="Clubs-show pc">
            <el-col :span="6">
                <el-card class="card">
                    <h3 class="title">我的社团</h3>
                    <el-descriptions title="互联网社" :column="3" border>
                        <template slot="extra">
                            <el-button type="primary" size="small">查看</el-button>
                        </template>
                        <el-descriptions-item>
                        <template slot="label">
                            <i class="el-icon-s-home"></i>
                            部门
                        </template>
                        技术部
                        </el-descriptions-item>
                        <el-descriptions-item>
                        <template slot="label">
                            <i class="el-icon-user"></i>
                            职位
                        </template>
                        成员
                        </el-descriptions-item>
                    </el-descriptions>
                </el-card>
            </el-col>
            <el-col :span="18">
                <el-card class="card">
                    <h3 class="title">社团信息</h3>
                    <div class="innerBox">
                        <div class="headTitle">
                            <div @click="changePoint('intro')" class="title" slot="header">
                                社团信息
                            </div>
                            <div @click="changePoint('activity')" class="title" slot="header">
                                社团活动
                            </div>
                            <div @click="changePoint('anno')" class="title" slot="header">
                                社团通知
                            </div>
                        </div>
                        <div>
                            <el-collapse v-if="this.currentPoint == 'intro'" accordion>
                                <Fieldset legend="社团" style="text-align: left;">
                                    <p v-html="MassInfo.info" class="m-0">
                                        
                                    </p>
                                </Fieldset>
                            </el-collapse>

                            <el-table
                                v-if="this.currentPoint == 'activity'"
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
                                            <el-button @click="getJoinActivity" size="mini">我的活动</el-button>
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


                            <el-row class="anno" v-if="this.currentPoint == 'anno'">
                                <el-col class="leftNav" :span="6">
                                    <ul class="navContainer">
                                        <li v-for="item in annoList" :key="item.id" class="navItem">
                                            <span>{{ item.title }}</span>
                                            <template>
                                                <el-button @click="showAnno(item)" type="text">查看</el-button>
                                            </template>
                                        </li>
                                    </ul>
                                </el-col>
                                <el-col :span="18">
                                    <el-empty v-if="annoContent == ''"></el-empty>
                                    <div v-else>{{ annoContent }}</div>
                                </el-col>
                            </el-row>
                        </div>
                    </div>
                </el-card>
            </el-col>
        </el-row>
        <el-row class="Clubs-show move">
            <el-col :span="24">
                <el-card class="card">
                    <h2 class="title">我的社团</h2>
                    <el-descriptions title="爱乐社" :column="3" border>
                        <template slot="extra">
                            <el-button type="primary" size="small">查看</el-button>
                        </template>
                        <el-descriptions-item>
                        <template slot="label">
                            <i class="el-icon-s-home"></i>
                            部门
                        </template>
                        技术部
                        </el-descriptions-item>
                        <el-descriptions-item>
                        <template slot="label">
                            <i class="el-icon-user"></i>
                            职位
                        </template>
                        部长
                        </el-descriptions-item>
                    </el-descriptions>
                </el-card>
            </el-col>
            <el-col :span="24">
                <el-card class="card">
                    <h3 class="title">社团信息</h3>
                    <div class="innerBox">
                        <div class="headTitle">
                            <div @click="changePoint('intro')" class="title" slot="header">
                                社团信息
                            </div>
                            <div @click="changePoint('activity')" class="title" slot="header">
                                社团活动
                            </div>
                            <div @click="changePoint('anno')" class="title" slot="header">
                                社团通知
                            </div>
                        </div>
                        <div>
                            <el-collapse v-if="this.currentPoint == 'intro'" accordion>
                                <el-collapse-item>
                                    <template slot="title">
                                        管理人员<i class="header-icon el-icon-user"></i>
                                    </template>
                                    <p>在界面中一致：所有的元素和结构需保持一致，比如：设计样式、图标和文本、元素的位置等。</p>
                                    <p>在界面中一致：所有的元素和结构需保持一致，比如：设计样式、图标和文本、元素的位置等。</p>
                                </el-collapse-item>
                                <el-collapse-item>
                                    <template slot="title">
                                        成立时间<i class="header-icon el-icon-date"></i>
                                    </template>
                                    <p>在界面中一致：所有的元素和结构需保持一致，比如：设计样式、图标和文本、元素的位置等。</p>
                                    <p>在界面中一致：所有的元素和结构需保持一致，比如：设计样式、图标和文本、元素的位置等。</p>
                                </el-collapse-item>
                            </el-collapse>

                            <el-table
                                v-if="this.currentPoint == 'activity'"
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
                                            <el-button size="mini">我的活动</el-button>
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
                                    <el-table-column
                                    prop="name"
                                    label="起止日期">
                                    </el-table-column>
                                    <el-table-column
                                    label="操作">
                                        <template>
                                            <el-button></el-button>
                                        </template>
                                    </el-table-column>
                                </el-table-column>
                            </el-table>


                            <el-row class="anno" v-if="this.currentPoint == 'anno'">
                                <el-col class="leftNav" :span="6">
                                    <ul class="navContainer">
                                        <li v-for="item in annoList" :key="item.id" class="navItem">
                                            <span>{{ item.title }}</span>
                                            <template>
                                                <el-button @click="showAnno(item)" type="text">查看</el-button>
                                            </template>
                                        </li>
                                    </ul>
                                </el-col>
                                <el-col :span="18">
                                    <el-empty v-if="annoContent == ''"></el-empty>
                                    <div v-else>{{ annoContent }}</div>
                                </el-col>
                            </el-row>
                        </div>
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
import axios from 'axios'

import JoinActivity from './module/JoinActivity.vue'
export default {
    components: {
        // eslint-disable-next-line
        JoinActivity
    },
    data() {
        return {
            currentPoint: 'intro',
            MassInfo: {},
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
            annoContent: '',
            annoList: [
                {
                    id: 1,
                    title: '通知',
                    content: '这是测试通知1'
                },
                {
                    id: 2,
                    title: '测试',
                    content: '这是测试通知2'
                },
                {
                    id: 3,
                    title: '活动',
                    content: '这是测试通知3'
                },
                {
                    id: 4,
                    title: '换选',
                    content: '这是测试通知4'
                },
            ],
            MassActivity: [],
            MassNotice: [],
            joinActivityData: []
        }
    },
    created() {
        this.getMassInfo()
        this.getMassActivity()
        this.getMassNotice()
    },
    methods: {
        changePoint(param) {
            this.currentPoint = param
        },
        showAnno(row) {
            // console.log(row)
            this.annoContent = row.content
        },
        getMassInfo() {
            const url = '/api/backstage-massList/getOne'
            axios.post(`${url}?mass=${localStorage.getItem('mass')}`, {}, {
                headers: {
                    'verifyCode': '2024'
                }
            }).then(res => {
                this.MassInfo = res.data
                // console.log(res.data)
            })
        },
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
        getMassNotice() {
            const url = '/api/manager-broadcast/getBroadcasts'
            axios.post(`${url}?mass=${localStorage.getItem('mass')}`, {}, {
                headers: {
                    'verifyCode': '2024'
                }
            }).then(res => {
                this.MassNotice = res.data
                console.log(res.data)
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
        getJoinActivity() {

            const h = this.$createElement;
            this.$msgbox({
            title: '我的活动',
            message: h('p', null, [
                h(JoinActivity), // 将自定义组件嵌入到VNode中
                // h('span', null, '内容可以是 '),
                // h('i', { style: 'color: teal' }, 'VNode')
            ]),
            showCancelButton: true,
            confirmButtonText: '确定',
            cancelButtonText: '取消',
                // eslint-disable-next-line
                beforeClose: (action, instance, done) => {
                    if (action === 'cancel') {
                        this.$msgbox.close();
                    } else if(action === 'confirm') {
                        const url = '/api/backstage-belong/new'
                        axios.post(`${url}?belong=${localStorage.getItem('newClassName')}&period=${localStorage.getItem('newClassIndex')}`, {}, {
                            headers: {
                                'verifyCode': 2024
                            }
                        }).then(res => {
                            if(res.data == 'Create success') {
                                this.getAll()
                                this.$message({
                                    message: '创建成功',
                                    type: 'success'
                                })
                            }
                        })
                        // console.log("confirm")
                        this.$msgbox.close();
                    }
                }
            });
        }
    }
}
</script>
<style>
.MyClub-container {
    width: calc(100% - 24px);
    min-height: calc(100vh - 100px - 24px - 52px);
    /* background-color: aqua; */
    margin: 12px;
    overflow-y: auto;
}
.MyClub-container .move {
    display: none;
}
.MyClub-container .card {
    margin: 0 6px;
    min-height: 60vh;
}
.MyClub-container .card .title {
    padding-bottom: 18px;
    border-bottom: 1px solid #EBEEF5;
}
.MyClub-container .innerBox {
    min-height: 420px;
    border: 1px solid #fbfbfb;
    border-top: none;
    margin-top: 18px;
    /* background-color: aqua; */
}

.MyClub-container .innerBox .headTitle {
    display: flex;
    justify-content: flex-start;
    height: 30px;
    line-height: 30px;
    border-bottom: 2px solid #fbfbfb;
}
.MyClub-container .innerBox .headTitle .title {
    padding: 0 12px;
    border-right: 1px solid #EBEEF5;
}

.MyClub-container .innerBox .anno {

}
.MyClub-container .innerBox .anno .leftNav {
    margin: 12px 0;
    min-height: 330px;
    border-right: 1px solid #eae8e8;
}
.MyClub-container .innerBox .anno .leftNav .navContainer {
    padding: 0;
    margin: 0 12px;
    list-style: none;
}
.MyClub-container .innerBox .anno .leftNav .navContainer .navItem {
    line-height: 39.33px;
    display: flex;
    justify-content: space-between;
    /* background-color: aqua; */
    font-size: 13px;
    padding: 0 8px;
    border-bottom: 1px solid #eae8e8;
}
@media screen and (max-width: 768px) {
    .MyClub-container .pc {
        display: none;
    }
    .MyClub-container .move {
        display: block;
    }
}
</style>