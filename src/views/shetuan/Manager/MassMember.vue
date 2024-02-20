<template>
    <div class="Manager-MassMember">
        <div class="Manager-MassMember_SearchCard">
            <div class="Manager-MassMember_SearchCard_PC">
                <span>学号:</span>
                <el-input></el-input>
                <span>名字:</span>
                <el-input></el-input>
                <span>手机:</span>
                <el-input></el-input>
                <i class="el-icon-search"></i>
                <i class="el-icon-refresh-right"></i>
            </div>
            <div class="Manager-MassMember_SearchCard_Move">
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
            </div>
        </div>
        <div class="Manager-MassMember_TableMemberData">
            <el-badge :value="waitNum" style="display:block;float: left;margin: 6px 6px;">
                <el-button  @click="OpenCheckMemberDialog" size="mini" >申请列表</el-button>
            </el-badge>
            <el-button  @click="OpenAddMemberDialog" size="mini" style="display:block;float: right;margin: 6px 6px;">新增</el-button>
            <el-table
            :data="MemberData"
            stripe>
                <el-table-column
                prop="id"
                label="id"></el-table-column>
                <el-table-column
                prop="sno"
                label="学号"></el-table-column>
                <el-table-column
                prop="shetuanUser.name"
                label="名字"></el-table-column>
                <el-table-column
                prop="department"
                label="部门"></el-table-column>
                <el-table-column
                prop="position"
                label="职位"></el-table-column>
                <el-table-column
                prop="shetuanUser.phone"
                label="手机"></el-table-column>
                <el-table-column
                label="操作">
                    <template slot-scope="scope">
                        <el-button
                        size="mini"
                        style="width: 100%;float: right;"
                        @click="OperateEdit(scope.row)">编辑</el-button>
                        <el-button
                        size="mini"
                        type="danger"
                        style="width: 100%;float: right;margin-top: 5px;"
                        @click="OperateDelete(scope.row)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>


        <!-- 新增表单 -->
        <el-dialog title="新增成员" :visible.sync="dialogFormAdd">
            <el-form :model="AddmemberInfos">
                <el-form-item label-width="60px" label="学号:">
                    <el-input v-model="AddmemberInfos.sno"></el-input>
                </el-form-item>
                <el-form-item label-width="60px" label="姓名:">
                    <el-input v-model="AddmemberInfos.name"></el-input>
                </el-form-item>
                <el-form-item label-width="60px" label="部门:">
                    <el-select v-model="AddmemberInfos.department" placeholder="请选择部门">
                        <el-option v-for="item in departments" :key="item" :label="item" :value="item"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label-width="60px" label="职位:">
                    <el-select v-model="AddmemberInfos.position" placeholder="请选择职位">
                        <el-option v-for="item in positions" :key="item" :label="item" :value="item"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label-width="60px" label="手机:">
                    <el-input v-model="AddmemberInfos.phone"></el-input>
                </el-form-item>
                <div style="display: flex;justify-content: flex-end;">
                    <el-button @click="AddMemberBtn">添加</el-button>
                </div>
            </el-form>
        </el-dialog>
        <el-dialog title="修改信息" :visible.sync="dialogFormUpdate">
            <el-form ref="form" :model="memberInfos">
                <el-form-item label-width="60px" label="学号:">
                    <el-input disabled v-model="memberInfos.sno"></el-input>
                </el-form-item>
                <el-form-item label-width="60px" label="姓名:">
                    <el-input disabled v-model="memberInfos.name"></el-input>
                </el-form-item>
                <el-form-item label-width="60px" label="部门:">
                    <el-select v-model="memberInfos.department" placeholder="请选择部门">
                        <el-option v-for="item in departments" :key="item" :label="item" :value="item"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label-width="60px" label="职位:">
                    <el-select v-model="memberInfos.position" placeholder="请选择职位">
                        <el-option v-for="item in positions" :key="item" :label="item" :value="item"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label-width="60px" label="手机:">
                    <el-input disabled v-model="memberInfos.phone"></el-input>
                </el-form-item>
                <div style="display: flex;justify-content: flex-end;">
                    <el-button @click="UpdateMemberData">修改</el-button>
                </div>
            </el-form>
        </el-dialog>

        <!-- 审核表格 -->
        <el-dialog  title="申请审核" :visible.sync="dialogFormCheck">
            <el-button @click="handleApprove" size="mini" style="display:block;float: left;margin: 6px 6px;">一键通过</el-button>
            <el-button @click="handleApprove" type="danger" size="mini" style="display:block;float: left;margin: 6px 6px;">一键驳回</el-button>
            <el-table
            @selection-change="handleSelectionChange"
            :data="memberSubmit"
            style="max-height: 550px; overflow: auto;">
                <el-table-column
                type="selection"
                width="55">
                </el-table-column>
                <el-table-column
                prop="id"
                label="id"></el-table-column>
                <el-table-column
                prop="sno"
                label="学号"></el-table-column>
                <el-table-column
                prop="shetuanUser.name"
                label="名字"></el-table-column>
                <el-table-column
                prop="shetuanUser.belong"
                label="班级"></el-table-column>
                <el-table-column
                prop="shetuanUser.phone"
                label="手机"></el-table-column>
                <el-table-column
                prop="department"
                label="部门"></el-table-column>
                <el-table-column
                prop="info"
                label="自我介绍"></el-table-column>
                <el-table-column
                label="操作">
                    <template slot-scope="scope">
                        <!-- <el-button
                        size="mini"
                        style="width: 100%;float: right;"
                        @click="CheckEdit(scope.row.sno)">详情</el-button> -->
                        <el-button
                        size="mini"
                        type="success"
                        style="width: 100%;float: right;margin-top: 5px;"
                        @click="CheckPass(scope.row.shetuanUser.name, scope.row.sno, scope.row.department)">通过</el-button>
                        <el-button
                        size="mini"
                        type="danger"
                        style="width: 100%;float: right;margin-top: 5px;"
                        @click="CheckeReject(scope.row.shetuanUser.name, scope.row.sno)">驳回</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-dialog>
    </div>
</template>
<script>
export default {
    data() {
        return {
            memberSubmit: [],
            MemberData: [],
            memberInfos: {
                sno: '',
                name: '',
                phone: '',
                department: '',
                position: ''
            },
            AddmemberInfos: {
                sno: '',
                name: '',
                phone: '',
                department: '',
                position: ''
            },
            dialogFormAdd: false,
            dialogFormUpdate: false,
            dialogFormCheck: false,
            waitNum: 0,
            selectedCheckData: [],
            departments: [],
            positions: ["成员", "副部长", "部长"],
            UpdateTemp_beforData: []
        }
    },
    created() {
        this.getAllCheckByMass()
        this.getAllPassByMass()
        this.getJustDepartment()
    },
    methods: {
        getJustDepartment() {
            // const mass = '互联网社'
            const mass = this.$route.params.mass
            const url = process.env.VUE_APP_BASE_URI + '/Shetaun-department/getJustDepartmentByMass'
            fetch(`${url}?mass=${mass}`, {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            })
            .then(res => res.json())
            .then(res => {
                this.departments = res
                // console.log(this.departments)
                // console.log(res)
            })
        },
        OpenAddMemberDialog() {
            this.$message({
                message: '暂不允许社团添加成员',
                type: 'warning'
            })
            // this.dialogFormAdd = true
        },
        OpenCheckMemberDialog() {
            this.dialogFormCheck = true
        },
        getAllCheckByMass() {
            // memberSubmit
            // const mass = '互联网社'
            const mass = this.$route.params.mass
            const url = process.env.VUE_APP_BASE_URI + '/shetuan-apply/apply-getWaitApply'
            fetch(`${url}?mass=${mass}`, {
                method: "POST",
                headers: {
                    'Accept': 'Application/json',
                    'Content-Type': 'Application/json'
                }
            })
            .then(res => res.json())
            .then(res => {
                this.memberSubmit = res
                this.waitNum = Object.keys(res).length
            })
        },
        handleApprove() {
            this.$message({
                message: '正在努力开发中...',
                type: 'warning'
            })
            // 打印选中的数据
            // console.log(this.selectedCheckData);
        },
        handleSelectionChange(selection) {
            this.selectedCheckData = selection;
        },
        CheckEdit(sno) {
            console.log(sno)
        },
        CheckeReject(name, sno) {
            this.$confirm('确定要驳回 '+name+' 的申请吗?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                // const mass = '互联网社'
                const mass = this.$route.params.mass
                const url = process.env.VUE_APP_BASE_URI + '/shetuan-apply/apply-reject'
                fetch(`${url}?sno=${sno}&mass=${mass}`, {
                    method: "POST",
                    headers: {
                        'Accept': 'Application/json',
                        'Content-Type': 'Application/json'
                    }
                })
                .then(res => res.text())
                .then(res => {
                    console.log(res)
                })
                location.reload()
                this.$message({
                    type: 'success',
                    message: '驳回成功!'
                });
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消驳回'
                });
            });
        },
        CheckPass(name, sno, department) {
            console.log(department)
            this.$confirm('确定要通过 '+name+' 的申请吗?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                // const mass = '互联网社'
                const mass = this.$route.params.mass
                const url = process.env.VUE_APP_BASE_URI + '/shetuan-apply/apply-agree'
                fetch(`${url}?mass=${mass}&sno=${sno}&department=${department}`, {
                    method: "POST",
                    headers: {
                        'Accept': 'Application/json',
                        'Content-Type': 'Application/json'
                    }
                })
                .then(res => res.text())
                .then(res => {
                    console.log(res)
                })
                this.getAllCheckByMass()
                location.reload()
                this.$message({
                    type: 'success',
                    message: '审核成功!'
                });
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消通过'
                });
            });
        },
        getAllPassByMass() {
            // const mass = '互联网社'
            const mass = this.$route.params.mass
            const url = process.env.VUE_APP_BASE_URI + '/shetuan-apply/apply-getAllPassByMass'
            fetch(`${url}?mass=${mass}`, {
                method: "POST",
                headers: {
                    'Accept': 'Application/json',
                    'Content-Type': 'Application/json'
                }
            })
            .then(res => res.json())
            .then(res => {
                this.MemberData = res
            })
        },
        OperateEdit(row) {
            this.dialogFormUpdate = true
            // console.log(row)
            this.memberInfos.sno = row.sno
            this.memberInfos.name = row.shetuanUser.name
            this.memberInfos.department = row.department
            this.memberInfos.position = row.position
            this.memberInfos.phone = row.shetuanUser.phone

            this.UpdateTemp_beforData = row
        },
        OperateDelete(row) {
            // console.log(row)
            const mass = row.mass
            const name = row.shetuanUser.name
            const sno = row.sno
            const department = row.department
            this.$confirm('确定要删除 '+name+' 吗?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                const url = process.env.VUE_APP_BASE_URI + '/shetuan-apply/apply-deleteMember'
                fetch(`${url}?mass=${mass}&sno=${sno}&department=${department}`, {
                    method: "POST",
                    headers: {
                        'Accept': 'Application/json',
                        'Content-Type': 'Application/json'
                    }
                })
                .then(res => res.text())
                .then(res => {
                    console.log(res)
                    this.$message({
                        message: '删除成功',
                        type: 'success'
                    })
                    location.reload()
                })
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消通过'
                });
            });
        },
        UpdateMemberData() {
            // console.log(this.UpdateTemp_beforData)
            const valueBefore = {
                // mass: '互联网社',
                mass: this.$route.params.mass,
                sno: this.UpdateTemp_beforData.sno,
                department: this.UpdateTemp_beforData.department,
                position: this.UpdateTemp_beforData.position
            }
            const value = {
                // mass: '互联网社',
                mass: this.$route.params.mass,
                sno: this.memberInfos.sno,
                department: this.memberInfos.department,
                position: this.memberInfos.position
            }
            // console.log(valueBefore)
            // console.log(value)
            const url = process.env.VUE_APP_BASE_URI + '/shetuan-apply/apply-updateInfo'
            fetch(`${url}?department=${value.department}&mass=${value.mass}&sno=${value.sno}&position=${value.position}&beforeDepartment=${valueBefore.department}`, {
                method: "POST",
                headers: {
                    'Accept': 'Application/json',
                    'Content-Type': 'Application/json'
                }
            })
            .then(res => res.text())
            .then(res => {
                console.log(res)
                
                this.dialogFormUpdate = false

                this.$message({
                    message: '修改成功',
                    type: 'success'
                })
                location.reload()
            })
        },
        AddMemberBtn() {
            console.log(this.AddmemberInfos)
        }
    }
}
</script>
<style>
.Manager-MassMember {
    margin: 24px 24px;
    height: calc(100vh - 164px);
    /* background-color: rgb(95, 141, 187, 0.7); */
}
.Manager-MassMember_SearchCard .Manager-MassMember_SearchCard_PC {
    display: flex;
    height: 50px;
    width: 100%;
    justify-content: left;
    border-radius: 5px;
    background-color: rgb(255, 255, 255, 0.6);
}
.Manager-MassMember_SearchCard .Manager-MassMember_SearchCard_PC span {
    margin: 0 12px;
    line-height: 50px;
}
.Manager-MassMember_SearchCard .Manager-MassMember_SearchCard_PC .el-input {
    width: 160px;
    margin-top: 10px;
}
.Manager-MassMember_SearchCard .Manager-MassMember_SearchCard_PC i {
    font-size: 20px;
    margin: 13px 6px;
    cursor: pointer;
}
.Manager-MassMember_SearchCard .el-input__inner {
    height: 30px;
}
.Manager-MassMember_SearchCard .Manager-MassMember_SearchCard_Move {
    width: calc(100% - 20px);
    height: 100px;
    padding: 0 10px;
    background-color: rgb(255, 255, 255, 0.6);
    display: none;
}
.Manager-MassMember_SearchCard .Manager-MassMember_SearchCard_Move span {
    line-height: 30px;
    margin: 0 6px;
}
.Manager-MassMember_SearchCard .Manager-MassMember_SearchCard_Move .el-input {
    width: 120px;
}
.Manager-MassMember_SearchCard .Manager-MassMember_SearchCard_Move i {
    font-size: 20px;
    margin: 3px 6px;
    cursor: pointer;
}


.Manager-MassMember_TableMemberData {
    width: 100%;
    margin-top: 15px;
    background-color: rgb(255, 255, 255, 0.6);
}
.Manager-MassMember_TableMemberData .el-table {
    background-color: rgb(255, 255, 255, 0.6);
    border-radius: 5px;
}
.Manager-MassMember_TableMemberData .el-table__body-wrapper {
    max-height: 580px;
    overflow-y: auto;
}


.el-dialog__body {
    padding: 0 10px;
    /* min-height: 550px; */
}
.el-table__body-wrapper {
    min-height: 550px;
}
@media screen and (max-width: 480px) {
    .Manager-MassMember_SearchCard .Manager-MassMember_SearchCard_PC {
        display: none;
    }
    .Manager-MassMember_SearchCard .Manager-MassMember_SearchCard_Move {
        display: block;
    }
    .Manager-MassMember_TableMemberData .el-table__body-wrapper {
        max-height: 460px;
    }
    .el-dialog {
        width: 96%;
    }
}
@media screen and (max-width: 390px) {
    .Manager-MassMember_SearchCard .Manager-MassMember_SearchCard_Move span  {
        margin: 0 2px;
    }
}
</style>