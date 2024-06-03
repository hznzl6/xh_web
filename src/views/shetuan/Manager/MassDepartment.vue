<template>
    <div class="manager-depart">
        <h2 style="font-size: 36px;padding: 6px 0;">部门管理</h2>
        <!-- <div style="display: flex;justify-content: flex-end;margin-bottom: 6px;">
            <el-select></el-select>
            <div>
                <el-button>新建部门</el-button>
                <el-button>入社申请</el-button>
            </div>
        </div> -->
        <el-table
            :data="departData"
            class="table">
            <el-table-column
                prop="department"
                label="部门">
                
            </el-table-column>
            <el-table-column>
                <template slot="header">
                    <div style="display: flex;justify-content: space-between;">
                        <el-select v-model="selectedValue"></el-select>
                        <el-button @click="NewDepartment" type="mini">新建</el-button>
                    </div>
                </template>
                <el-table-column
                    prop="priority"
                    label="优先级">
                </el-table-column>
                <el-table-column
                    prop="nums"
                    label="数量">
                </el-table-column>
                <el-table-column
                    label="操作">
                    <template slot-scope="scope">
                        <el-button style="margin: 1px 3px;" size="mini" @click="delDepart(scope.row)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table-column>
            
        </el-table>
    </div>
</template>
<script>
import axios from 'axios'
export default {
    data() {
        return {
            departData: [],
            selectedValue: ''
        }
    },
    created() {
        this.getAll()
    },
    methods: {
        getAll() {
            const url = '/api/manager-department/getDeparts'
            // const mass = this.$route.params
            // console.log(mass.mass)
            axios.post(`${url}?mass=${this.$route.params.mass}`, {

            },{
                headers: {
                    'verifyCode ': '2024'
                }
            }).then(res => {
                this.departData = res.data
                // console.log(res.data)
            })
        },
        addDepart(name) {
            const data = {
                mass: this.$route.params,
                depart: name
            }
            // console.log(data)
            const url = '/api/manager-department/new'
            axios.post(`${url}?mass=${this.$route.params.mass}&depart=${data.depart}`, {}, {
                headers: {
                    'verifyCode ': '2024'
                }
            }).then(res => {
                if(res.data == 'Create success') {
                    this.getAll()
                    this.$message({
                        type: 'success',
                        message: '新建成功'
                    })
                } else {
                    this.$message({
                        type: 'warning',
                        message: '部门已存在'
                    })
                }
            })
        },
        delDepart(row) {
            // console.log(row)
            if(row.priority == 1) {
                this.$message({
                    type: 'warning',
                    message: '没有权限删除'
                })
            } else if(row.nums > 0) {
                this.$message({
                    type: 'warning',
                    message: '部门不为空'
                })
            } else {
                this.$confirm('此操作将永久删除该部门, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning',
                    callback: action => {
                        if(action == 'confirm') {
                            const url = '/api/manager-department/delete'
                            axios.post(`${url}?mass=${row.mass}&depart=${row.department}`, {}, {
                                headers: {
                                    'verifyCode ': '2024'
                                }
                            }).then(res => {
                                if(res.data == 'Delete success') {
                                    this.getAll()
                                    this.$message({
                                        type: 'success',
                                        message: '删除成功'
                                    })
                                }
                            })
                        } else if(action == 'cancel') {
                            console.log('取消')
                        }
                    }
                })
            }
        },
        NewDepartment() {
            this.$prompt('请输入要新建的部门', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                callback: (action, value) => {
                    if(action == 'cancel') {
                        console.log('取消')
                    } else if(action == 'confirm') {
                        // console.log('确认')
                        // console.log(value._data.inputValue)
                        this.addDepart(value._data.inputValue)
                    }
                }
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '取消输入'
                });       
            });
        },
        
    }
}

</script>
<style>
.manager-depart {
    width: 100%;
    height: calc(100vh - 120px);
    /* background-color: rgba(22, 55, 44, 0.6); */
    padding: 24px;
    padding-top: 0;
}
</style>