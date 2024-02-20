<template>
    <div class="Manager-MassDepartment">
        <div class="Manager-MassDepartment_searchCard">
            <span>部门名称:</span><el-input></el-input>
            <i class="el-icon-search"></i>
            <i class="el-icon-refresh-right"></i>
        </div>
        <div class="Manager-MassDepartment_ListTable">
            <el-button
            size="mini"
            type="primary"
            style="float: left;"
            @click="NewDepartment"
            >新增</el-button>
            <el-table default-expand-all :data="DepartmentData" style="width: 100%;" row-key="label" :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
                <el-table-column prop="label" label="部门" >
                </el-table-column>
                <el-table-column prop="priority" label="排序" >
                </el-table-column>
                <el-table-column prop="nums" label="人数" >
                </el-table-column>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button type="danger" size="mini" @click="Deletedepartment(scope)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
    </div>
</template>
<script>
export default {
    data() {
        return {
            DepartmentData: [
                // {
                //     label: '互联网社',
                //     priority: 1,
                //     nums: 0,
                //     children: [
                //         {
                //             label: '技术部',
                //             priority: 2,
                //             nums: 0
                //         },
                //         {
                //             label: '组织部',
                //             priority: 2,
                //             nums: 0
                //         },
                //         {
                //             label: '宣传部',
                //             priority: 2,
                //             nums: 0
                //         }
                //     ]
                // }
            ]
        }
    },
    created() {
        this.getAllDepartmentByMass()
    },
    methods: {
        // Deletedepartment(row) {
        //     console.log(row.name)
        // },
        getAllDepartmentByMass() {
            // const mass = '互联网社'
            const mass = this.$route.params.mass
            const url = process.env.VUE_APP_BASE_URI + '/Shetaun-department/getAllDepartmentByMass'
            fetch(`${url}?mass=${mass}`, {
                method: "POST",
                headers: {
                    'Accept': 'Application/json',
                    'Content-Type': 'Application/json'
                }
            })
            .then(res => res.json())
            .then(res => {
                this.DepartmentData = res
            })
        },
        NewDepartment() {
            this.$prompt('输入新增的部门', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(({ value }) => {
                console.log(value)
                // const mass = '互联网社'
                const mass = this.$route.params.mass
                const url = process.env.VUE_APP_BASE_URI + '/Shetaun-department/createDepartment'
                fetch(`${url}?department=${value}&mass=${mass}`, {
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
                this.$message({
                    type: 'success',
                    message: '添加成功!'
                });
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消添加'
                });          
            });
        },
        Deletedepartment(scope) {
            // console.log(scope.row.priority)
            if(scope.row.priority == 1) {
                this.$message({
                    message: '父级单位没有权限删除',
                    type: 'warning'
                })
                return
            }
            if(scope.row.nums != 0) {
                this.$message({
                    message: '部门人数不为0 没有权限删除',
                    type: 'warning'
                })
                return
            }
            this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                console.log(scope.row)
                // const mass = '互联网社'
                const mass = this.$route.params.mass
                const department = scope.row.label
                const url = process.env.VUE_APP_BASE_URI + '/Shetaun-department/deleteDepartment'
                fetch(`${url}?mass=${mass}&department=${department}`, {
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

                this.$message({
                    type: 'success',
                    message: '删除成功!'
                });
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });          
            });
        }
    }
}

</script>
<style>
.Manager-MassDepartment {
    margin: 24px 24px;
    height: calc(100vh - 168px);
    /* background-color: rgb(255, 255, 255, 0.6); */
}
.Manager-MassDepartment_searchCard {
    height: 50px;
    width: calc(100% - 24px);
    border-radius: 5px;
    background-color: rgb(255, 255, 255, 0.6);
    padding: 0 12px;
}
.Manager-MassDepartment_searchCard span {
    float: left;
    line-height: 50px;
}
.Manager-MassDepartment_searchCard .el-input {
    float: left;
    width: 200px;
    margin-top: 5px;
}
.Manager-MassDepartment_searchCard .el-icon-search {
    font-size: 20px;
    float: left;
    margin-left: 16px;
    margin-top: 12px;
    cursor: pointer;
}
.Manager-MassDepartment_searchCard .el-icon-refresh-right {
    font-size: 20px;
    float: left;
    margin-left: 16px;
    margin-top: 12px;
    cursor: pointer;
}


.Manager-MassDepartment_ListTable {
    width: 100%;
    height: calc(100vh - 168px - 60px);
    margin-top: 10px;
    border-radius: 5px;
    background-color: rgb(255, 255, 255, 0.6);
}
@media screen and (max-width: 480px) {
    .el-message-box {
        width: 96%;
    }
}
@media screen and (max-width: 390px) {
    .Manager-MassDepartment_searchCard .el-input {
        width: 180px;
    }
    .Manager-MassDepartment_searchCard .el-icon-search,.el-icon-refresh-right {
        margin-left: 8px;
    }
}
</style>