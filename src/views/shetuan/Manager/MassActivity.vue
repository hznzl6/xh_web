<!-- <template>
    <div>
        <el-card class="Manager-MassActivity">

        </el-card>
    </div>
</template>

<style>
.Manager-MassActivity {
    margin: 24px 24px;
    height: calc(100vh - 164px);
    background-color: rgb(95, 141, 187, 0.7);
}
</style> -->


 <template>
    <div class="Manager-MassActivity">
        <div class="Manager-MassActivity">
            <!-- <div class="Manager-MassActivity_SearchCard_PC">
                <span>学号:</span>
                <el-input></el-input>
                <span>名字:</span>
                <el-input></el-input>
                <span>手机:</span>
                <el-input></el-input>
                <i class="el-icon-search"></i>
                <i class="el-icon-refresh-right"></i>
            </div>
            <div class="Manager-MassActivity_SearchCard_Move">
                <div style="display: flex;justify-content: left;padding: 12px 0;">
                    <span>学号:</span>
                    <el-input></el-input>
                    <span>名字:</span>
                    <el-input></el-input>
                </div>
                <div style="display: flex;justify-content: left;">
                    <span>手机:</span>
                    <el-input></el-input>
                    <i class="el-icon-search"></i>
                    <i class="el-icon-refresh-right"></i>
                </div>
            </div> -->
            <div class="Manager-MassActivity_TableMemberData">
            <el-button  @click="OpenAddMemberDialog" size="mini" style="display:block;float: right;margin: 6px 6px;">新增</el-button>
            <el-table
            :data="ActivityData"
                stripe>
                    <el-table-column
                    label="id"
                    prop="id"></el-table-column>
                    <el-table-column
                    label="活动名称"
                    prop="event"></el-table-column>
                    <el-table-column
                    label="描述"
                    prop="describes"></el-table-column>
                    <el-table-column
                    label="负责人"
                    prop="person"></el-table-column>
                    <el-table-column
                    label="电话"
                    prop="phone"></el-table-column>
                    <el-table-column
                    label="申请经费"
                    prop="expenditure"></el-table-column>
                    <el-table-column
                    label="开始时间"
                    prop="start_time"></el-table-column>
                    <el-table-column
                    label="结束时间"
                    prop="end_time"></el-table-column>
                    <el-table-column
                    label="报名人员"
                    prop="sign"></el-table-column>
                    <el-table-column
                    label="实际参与人员"
                    prop="actual"></el-table-column>
                    <el-table-column
                    label="状态"
                    prop="status">
                    <template slot-scope="scope">
                        <el-tag v-if="scope.row.status === 1" type="success">已审核</el-tag>
                        <el-tag v-if="scope.row.status === 0" type="warning">待审核</el-tag>
                        <el-tag v-if="scope.row.status === 2" type="danger">驳回</el-tag>
                    </template>
                    </el-table-column>
                    <el-table-column
                    label="操作">
                    <template slot-scope="scope">
                        <div v-if="scope.row.status === 0">
                            <el-button style="width: 100%;text-align: center;margin: 0;padding: 0;" @click="EditArticle(scope.row)" type="text" size="small">编辑</el-button>
                        </div>
                        <div v-if="scope.row.status === 1">
                            <el-button style="width: 100%;text-align: center;margin: 0;padding: 0;" @click="EditArticle(scope.row)" type="text" size="small">查看</el-button>
                        </div>
                        <div v-if="scope.row.status === 2">
                            <el-button style="width: 100%;text-align: center;margin: 0;padding: 0;" @click="EditArticle(scope.row)" type="text" size="small">重申</el-button>
                        </div>
                    </template>
                    </el-table-column>
                </el-table>
            </div>
        </div>
        


        <!-- 新增表单 -->
        <el-dialog title="新建活动" :visible.sync="dialogFormAdd">
            <el-form :model="ActiveInfos">
                <el-form-item label-width="100px" label="活动名称:">
                    <el-input v-model="ActiveInfos.event"></el-input>
                </el-form-item>
                <el-form-item label-width="100px" label="活动描述:">
                    <el-input type="textarea" v-model="ActiveInfos.describe"></el-input>
                </el-form-item>
                <el-form-item label-width="100px" label="活动负责人:">
                    <el-input v-model="ActiveInfos.person"></el-input>
                </el-form-item>
                <el-form-item label-width="100px" label="负责人电话:">
                    <el-input v-model="ActiveInfos.phone"></el-input>
                </el-form-item>
                <el-form-item label-width="100px" label="申请经费:">
                    <el-select v-model="ActiveInfos.expenditure" placeholder="请选择经费">
                        <el-option v-for="item in expenditures" :key="item.price" :label="item.price" :value="item.price"></el-option>
                    </el-select>
                    <br><span style="font-size: 11px;color: #b1b1b1;">经费申请请提前与社联负责人沟通</span>
                </el-form-item>
                <el-form-item label-width="100px" label="活动时间:">
                    <el-date-picker
                    v-model="ActiveInfos.start_time"
                    type="datetime"
                    placeholder="选择开始时间"
                    default-time="12:00:00">
                    </el-date-picker>
                    <el-date-picker
                    v-model="ActiveInfos.end_time"
                    type="datetime"
                    placeholder="选择结束时间"
                    default-time="12:00:00">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label-width="100px" label="备注:">
                    <el-input v-model="ActiveInfos.mark" type="textarea"></el-input>
                </el-form-item>
                <div style="display: flex;justify-content: flex-end;">
                    <el-button @click="ApplyActivity">申请</el-button>
                </div>
            </el-form>
        </el-dialog>
    </div>
</template>
<script>
export default {
    data() {
        return {
            ActivityData: [],
            ActiveInfos: {
                // mass: '互联网社',
                mass: this.$route.params.mass,
                event: '',
                describe: '',
                person: '',
                phone: '',
                expenditure: '',
                start_time: '',
                end_time: '',
                mark: '',
            },
            expenditures: [
                {
                    price: '0元'
                },
                {
                    price: '20元'
                },
                {
                    price: '50元'
                },
                {
                    price: '100元'
                }
            ],
            dialogFormAdd: false,
            dialogFormUpdate:false
        }
    },
    created() {
        // console.log(this.$route.params.mass)
        this.getAllActivityByMass()
    },
    methods: {
        OpenAddMemberDialog() {
            this.dialogFormAdd = true
        },
        ApplyActivity() {
            const url = process.env.VUE_APP_BASE_URI + '/shetuan-activity/applyEvent'
            fetch(url, {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(this.ActiveInfos)
            })
            .then(res => res.text())
            .then(res => {
                console.log(res)
                if(res == 'Apply success') {
                    this.$message({
                        message: '申请成功',
                        type: 'success'
                    })
                    this.dialogFormAdd = false
                } else if (res == 'Update success') {
                    this.$message({
                        message: '修改成功',
                        type: 'success'
                    })
                    this.dialogFormAdd = false
                }
            })
        },
        getAllActivityByMass() {
            // const mass = '互联网社'
            const mass = this.$route.params.mass
            const url = process.env.VUE_APP_BASE_URI + '/shetuan-activity/getAllEventByMass'
            fetch(`${url}?mass=${mass}`, {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
            })
            .then(res => res.json())
            .then(res => {
                this.ActivityData = res
            })
        }
    }
}
</script>
<style>
.Manager-MassActivity {
    margin: 12px 12px;
    height: calc(100vh - 144px);
    /* background-color: rgb(95, 141, 187, 0.7); */
}
.Manager-MassActivity_SearchCard_PC {
    display: flex;
    height: 50px;
    width: 100%;
    justify-content: left;
    border-radius: 5px;
    background-color: rgb(255, 255, 255, 0.6);
}
.Manager-MassActivity_SearchCard_PC span {
    margin: 0 12px;
    line-height: 50px;
}
.Manager-MassActivity_SearchCard_PC .el-input {
    width: 160px;
    margin-top: 10px;
}
.Manager-MassActivity_SearchCard_PC i {
    font-size: 20px;
    margin: 13px 6px;
    cursor: pointer;
}
.Manager-MassActivity_SearchCard_PC .el-input__inner {
    height: 30px;
}
.Manager-MassActivity_SearchCard_Move {
    width: calc(100% - 20px);
    height: 100px;
    padding: 0 10px;
    background-color: rgb(255, 255, 255, 0.6);
    display: none;
}
.Manager-MassActivity_SearchCard .Manager-MassActivity_SearchCard_Move span {
    line-height: 30px;
    margin: 0 6px;
}
.Manager-MassActivity_SearchCard .Manager-MassActivity_SearchCard_Move .el-input {
    width: 120px;
}
.Manager-MassActivity_SearchCard .Manager-MassActivity_SearchCard_Move i {
    font-size: 20px;
    margin: 3px 6px;
    cursor: pointer;
}


.Manager-MassActivity_TableMemberData {
    height: calc(100vh - 144px);
    width: 100%;
    margin-top: 15px;
    background-color: rgb(255, 255, 255, 0.6);
}
.Manager-MassActivity_TableMemberData .el-table {
    background-color: rgb(255, 255, 255, 0.6);
    border-radius: 5px;
}
.Manager-MassActivity_TableMemberData .el-table__body-wrapper {
    min-height: 650px;
}
@media screen and (max-width: 480px) {
    .Manager-MassActivity_SearchCard_PC {
        display: none;
    }
    .Manager-MassActivity_SearchCard_Move {
        display: block;
    }
    .Manager-MassActivity_TableMemberData .el-table__body-wrapper {
        min-height: 550px;
    }
    .el-dialog {
        width: 96%;
    }
    .el-dialog__wrapper {
        height: 100vh;
    }
    .el-dialog .el-date-editor {
        width: 96%;
    }
}
@media screen and (max-width: 390px) {
    .Manager-MassActivity_SearchCard .Manager-MassActivity_SearchCard_Move span  {
        margin: 0 2px;
    }
}
</style>