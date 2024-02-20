<template>
    <div>
        <div class="user-Mass_left">
            <ul class="user-Mass_left_navContainer">
                <li @click="changeBtn('MyMass')" class="user-Mass_left_navItem" :class="{ active: isActive === 'MyMass' }">我的社团</li>
                <li @click="changeBtn('AllMass')" class="user-Mass_left_navItem" :class="{active: isActive === 'AllMass'}">所有社团</li>
                <li @click="changeBtn('MessageMass')" class="user-Mass_left_navItem" :class="{active: isActive === 'MessageMass'}">社团消息</li>
            </ul>
            <ul class="user-Mass_left_navBottom">
                <li class="user-Mass_left_navBottomItem">活动中心</li>
                <!-- <li class="user-Mass_left_navBottomItem">我的消息</li> -->
                <li @click="OpenDialogMyCheck" class="user-Mass_left_navBottomItem">我的审核</li>
            </ul>
        </div>
        <div class="user-Mass_right">
            <!-- 我的社团 -->
            <div v-show="isActive == 'MyMass'">
                <div class="user-Mass_right_MassTitle">
                    <h2>我的社团</h2>
                </div>
                <ul class="user-Mass_right_JoinMass">
                    <!-- <li v-for="item in allJoinMass" :key="item.mass">
                        {{ item.mass }}
                    </li> -->
                    <!-- <el-descriptions :title="item.mass" v-for="item in allJoinMass" :key="item.mass">
                        <el-descriptions-item label="部门">{{ item.department }}</el-descriptions-item>
                        <el-descriptions-item label="职位">{{ item.position }}</el-descriptions-item>
                    </el-descriptions> -->
                    <el-descriptions :title="item.mass" v-for="item in allJoinMass" :key="item.mass" :column="3" border>
                        <template slot="extra">
                            <el-dropdown trigger="click">
                                <el-button type="primary" size="small">操作</el-button>
                                <el-dropdown-menu slot="dropdown">
                                    <el-dropdown-item>开发中...</el-dropdown-item>
                                </el-dropdown-menu>
                            </el-dropdown>
                        </template>
                        <el-descriptions-item>
                        <template slot="label">
                            <i class="el-icon-s-home"></i>
                            部门
                        </template>
                        {{ item.department }}
                        </el-descriptions-item>
                        <el-descriptions-item>
                        <template slot="label">
                            <i class="el-icon-user"></i>
                            职位
                        </template>
                        {{ item.position }}
                        </el-descriptions-item>
                    </el-descriptions>
                </ul>
            </div>
            <!-- 社团列表 -->
            <ul v-show="isActive == 'AllMass'" class="user-Mass_right_MassList">
                <div class="user-Mass_right_MassTitle">
                    <h2>社团列表</h2>
                </div>
                <li v-for="(item, index) in paginatedItems" :key="index" class="user-Mass_right_MassListItem">
                    <!-- <span class="user-Mass_right_MassListItem_Title">{{ item.name }}</span> -->
                    <i class="user-Mass_right_MassListItem_Icon" :class="getIcon(item)"></i>
                    <i @click="navigatorToMass(item)" class="user-Mass_right_MassListItem_IconMore mass-icon_more"></i>
                    <p class="user-Mass_right_MassListItem_Title">{{ item.name }}</p>
                    <p class="user-Mass_right_MassListItem_Slogan">{{ item.slogan }}</p>
                </li>
                <el-pagination
                small
                layout="prev, pager, next"
                :total="totalItems"
                :current-page="currentPage"
                :page-size="itemsPerPage"
                @current-change="handleCurrentChange"
                style="text-align: left;">
                </el-pagination>
            </ul>
            <!-- 社团消息 -->
            <div v-show="isActive == 'MessageMass'">
                <div class="user-Mass_right_MassTitle">
                    <h2>社团消息</h2>
                </div>
                <el-table :data="messageByMass">
                    <el-table-column
                    prop="title"
                    label="消息标题">
                    </el-table-column>
                    <el-table-column
                    prop="create_time"
                    label="时间">
                    </el-table-column>
                    <el-table-column
                    label="查看">
                    <template slot-scope="scope">
                        <i style="cursor: pointer;font-size: 22px;" @click="viewMessage(scope.row)" class="el-icon-view"></i>
                    </template>
                    </el-table-column>
                </el-table>
            </div>
        </div>


        <el-dialog class="dialogMyCheck_move" title="我的审核" :visible.sync="dialogMyCheck">
            <el-table style="height: 550px;overflow-y: auto;" :data="MyCheckData">
                <el-table-column prop="apply_time" label="日期"></el-table-column>
                <el-table-column prop="mass" label="社团"></el-table-column>
                <el-table-column prop="info" label="内容"></el-table-column>
                <el-table-column prop="status" label="状态">
                    <template slot-scope="scope">
                        <el-tag
                        v-if="scope.row.status === 0"
                        type="warning"
                        >
                        待审核
                        </el-tag>
                        <el-tag
                        v-else-if="scope.row.status === 1"
                        type="success"
                        >
                        通过
                        </el-tag>
                        <el-tag
                        v-else-if="scope.row.status === 2"
                        type="danger"
                        >
                        驳回
                        </el-tag>
                    </template>
                </el-table-column>
            </el-table>
        </el-dialog>
        <el-dialog class="dialogMassMessage" :title="tempViewMessage.title" :visible.sync="dialogMassMessage">
            <div v-html="tempViewMessage.content"></div>
        </el-dialog>
    </div>
</template>

<script>
export default {
    data() {
        return {
            isActive: 'MessageMass',
            MassList: [
                {
                    icon: 'mass-icon_club01',
                    name: '互联网社',
                    slogan: '码到世界充满爱',
                },
                {
                    icon: 'mass-icon_club01',
                    name: '花绳俱乐部',
                    slogan: '不属于社团哦',
                },
                {
                    icon: 'mass-icon_club01',
                    name: '爱乐社',
                    slogan: '由社团管理员更新',
                },
                {
                    icon: 'mass-icon_club01',
                    name: '新梦想',
                    slogan: '由社团管理员更新',
                },
                {
                    icon: 'mass-icon_club01',
                    name: '书画社',
                    slogan: '由社团管理员更新',
                },
                {
                    icon: 'mass-icon_club01',
                    name: '心理社',
                    slogan: '由社团管理员更新',
                },
                {
                    icon: 'mass-icon_club01',
                    name: '习茶社',
                    slogan: '由社团管理员更新',
                },
                {
                    icon: 'mass-icon_club01',
                    name: '武术社',
                    slogan: '由社团管理员更新',
                },
                {
                    icon: 'mass-icon_club01',
                    name: '台球社',
                    slogan: '由社团管理员更新',
                },
                {
                    icon: 'mass-icon_club01',
                    name: '语言社',
                    slogan: '由社团管理员更新',
                },
                {
                    icon: 'mass-icon_club01',
                    name: '汉服社',
                    slogan: '由社团管理员更新',
                },
                {
                    icon: 'mass-icon_club01',
                    name: '口才社',
                    slogan: '由社团管理员更新',
                },
                {
                    icon: 'mass-icon_club01',
                    name: '棋社',
                    slogan: '由社团管理员更新',
                },
                {
                    icon: 'mass-icon_club01',
                    name: '摄影社',
                    slogan: '由社团管理员更新',
                },
                {
                    icon: 'mass-icon_club01',
                    name: '读书社',
                    slogan: '由社团管理员更新',
                },
                {
                    icon: 'mass-icon_club01',
                    name: '舞蹈社',
                    slogan: '由社团管理员更新',
                },
                {
                    icon: 'mass-icon_club01',
                    name: '动漫社',
                    slogan: '由社团管理员更新',
                },
                {
                    icon: 'mass-icon_club01',
                    name: '羽毛球社',
                    slogan: '由社团管理员更新',
                },
                {
                    icon: 'mass-icon_club01',
                    name: '乒乓球',
                    slogan: '由社团管理员更新',
                },
                {
                    icon: 'mass-icon_club01',
                    name: '文学社',
                    slogan: '由社团管理员更新',
                },
            ],
            currentPage: 1,
            itemsPerPage: 8,
            totalItems: 0,
            allJoinMass: [],
            dialogMyCheck: false,
            MyCheckData: [],
            messageByMass: [],
            tempViewMessage: {
                title: '',
                content: ''
            },
            dialogMassMessage: false
        }
    },
    created() {
        this.totalItems = this.MassList.length
        this.getAllJoinMassBySno()
        this.GetAllCheckData()
        this.getAllMessageByMass()
    },
    computed: {
        paginatedItems() {
            const start = (this.currentPage - 1) * this.itemsPerPage;
            const end = start + this.itemsPerPage;
            return this.MassList.slice(start, end);
        },
    },
    methods: {
        handleCurrentChange(val) {
            this.currentPage = val;
        },
        changeBtn(obj) {
            this.isActive = obj
            console.log(this.isActive)
        },
        getIcon(item) {
            return item.icon;
        },
        navigatorToMass(item) {
            const url = "/mass/" + item.name
            // console.log(url)
            this.$router.push(url)
        },
        getAllJoinMassBySno() {
            const sno = this.$route.params.sno
            // console.log(sno)
            const url = process.env.VUE_APP_BASE_URI + '/shetuan-apply/apply-getAllJoinBySno'
            fetch(`${url}?sno=${sno}`, {
                method: "POST",
                headers: {
                    'Accept': 'Application/json',
                    'Content-Type': 'Application/json'
                }
            })
            .then(res => res.json())
            .then(res => {
                this.allJoinMass = res
                // console.log(this.allJoinMass)
                // console.log(res)
            })
        },
        OpenDialogMyCheck() {
            this.dialogMyCheck = true
        },
        GetAllCheckData() {
            const sno = this.$route.params.sno
            const url = process.env.VUE_APP_BASE_URI + '/shetuan-apply/apply-getAllCheckBySno'
            fetch(`${url}?sno=${sno}`, {
                method: 'POST',
                headers: {
                    'Accept': 'Application/json',
                    'Content-Type': 'Application/json'
                }
            })
            .then(res => res.json())
            .then(res => {
                this.MyCheckData = res
            })
        },
        getAllMessageByMass() {
            const url = process.env.VUE_APP_BASE_URI + '/shetuan-broadcast/getAll-broadcast'
            const mass = '互联网社'
            fetch(`${url}?mass=${mass}`, {
                method: "POST",
                headers: {
                    'Accept': 'Application/json',
                    'Content-Type': 'Application/json'
                },
            })
            .then(res => res.json())
            .then(res => {
                this.messageByMass = res
            })
        },
        viewMessage(row) {
            // console.log(row)
            this.dialogMassMessage =true
            this.tempViewMessage.title = row.title
            this.tempViewMessage.content = row.content
        }
    }
}
</script>

<style>
.user-Mass_left {
    float: left;
    width: 30%;
    height: 520px;
    border-right: 1px solid #e5e9ef;
    /* background-color: saddlebrown; */
}
.user-Mass_left_navContainer {
    padding: 0 10px;
}
.user-Mass_left_navItem {
    font-size: 16px;
    list-style: none;
    margin: 6px 0;
    padding: 3px 0;
    font-weight: 400;
    border-radius: 5px;
    cursor: pointer;
}
.user-Mass_left_navItem.active {
    background-color: coral !important;
    color: #fff !important;
}
.user-Mass_left_navBottom {
    position: absolute;
    bottom: 0;
    padding-left: 12px;
    display: none;
}
.user-Mass_left_navBottomItem {
    list-style: none;
    font-size: 13px;
    font-weight: 400;
    padding: 2px 0;
    color: #00a1d6;
    cursor: pointer;
}
.user-Mass_right {
    float: right;
    width: calc(70% - 1px);
    min-height: 600px;
    /* background-color: aquamarine; */
}
.user-Mass_right_JoinMass {
    width: calc(100% - 40px);
    height: 120px;
    margin: 0 20px;
    /* background-color: #00a1d6; */
}
.user-Mass_right_MassList {
}
.user-Mass_right_MassTitle {
    text-align: left;
    padding: 6px 0;
    padding-left: 6px;
    margin: 0 20px;
    border-bottom: 1px solid #e5e9ef;
    margin-bottom: 12px;
}
.user-Mass_right_MassListItem {
    list-style: none;
    height: 50px;
    padding: 2px 20px;
    /* background-color: #00a1d6; */
}
.user-Mass_right_MassListItem_Icon {
    float: left;
    padding: 20px;
}
.user-Mass_right_MassListItem_IconMore {
    margin-top: 5px;
    padding: 16px;
    float: right;
    cursor: pointer;
}
.user-Mass_right_MassListItem_Title {
    font-size: 18px;
    text-align: left;
}
.user-Mass_right_MassListItem_Slogan {
    font-size: 13px;
    text-align: left;
}
.dialogMyCheck_move .el-dialog {
    width: 96%;
}
.dialogMassMessage .el-dialog {
    height: 550px;overflow-y: auto;
}
.dialogMassMessage .el-dialog div p {
    text-align: left;
}
.mass-icon_more {
    background:  url(../../../assets/img/user/icon/mass/i_more.png) 50% no-repeat;
}
.mass-icon_club01 {
    background:  url(../../../assets/img/user/icon/mass/i_mass.png) 50% no-repeat;
}
@media screen and (max-width: 480px) {
    .user-Mass_left_navBottom {
        display: block;
    }
}
</style>