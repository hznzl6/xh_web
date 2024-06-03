<template>
    <div>
        <el-select v-model="selectedValue">
            <el-option
            v-for="item in selectData"
            :key="item.value"
            :label="item.label"
            :value="item.value">
            </el-option>
        </el-select>
        <el-table
        stripe
        :data="filteredData"
        style="width: 100%;"
        class="table">
            <el-table-column
            prop="sno"
            label="学号">
            </el-table-column>
            <el-table-column
            prop="depart"
            label="部门">
            </el-table-column>
            <el-table-column
            prop="description"
            label="介绍">
            </el-table-column>
            <el-table-column
            label="操作">
            <template slot-scope="scope">
                <div v-if="scope.row.status == 0" style="">
                    <el-button @click="agreeJoin(scope.row)" style="width: 100%;margin: 3px 0;" size="mini">通过</el-button>
                    <el-button @click="rejectJoin(scope.row)" style="width: 100%;margin: 3px 0;" size="mini">驳回</el-button>
                </div>
            </template>
            </el-table-column>
            <el-table-column
            label="状态">
            <template slot-scope="scope">
                <el-tag size="mini" v-if="scope.row.status == 0">未审核</el-tag>
                <el-tag size="mini" v-if="scope.row.status == 1">已审核</el-tag>
            </template>
            </el-table-column>
        </el-table>
    </div>
</template>
<script>
import axios from 'axios'
export default {
    data() {
        return {
            ApplyData: [],
            selectedValue: 0,
            selectData: [
                {
                    label: '待审核',
                    value: 0
                },
                {
                    label: '已审核',
                    value: 1
                },
                {
                    label: '被驳回',
                    value: 2
                },
            ],
        }
    },
    created() {
        this.getAll()
    },
    computed: {
        filteredData() {
            return this.ApplyData.filter(item => item.status === this.selectedValue);
        }
    },
    methods: {
        agreeJoin(row) {
            console.log(row)
            const url = '/api/user-applyMass/agree'
            axios.post(`${url}?mass=${row.mass}&sno=${row.sno}&depart=${row.depart}`, {
                
            },{
                headers: {
                    'verifyCode ': '2024'
                }
            }).then(res => {
                if(res.data == 'Agree success') {
                    this.getAll()
                    this.$message({
                        type: 'success',
                        message: '审核通过'
                    })
                }
            })
        },
        rejectJoin(row) {
            console.log(row)
            const url = '/api/user-applyMass/reject'
            axios.post(`${url}?mass=${row.mass}&sno=${row.sno}`, {
                
            },{
                headers: {
                    'verifyCode ': '2024'
                }
            }).then(res => {
                if(res.data == 'Reject success') {
                    this.getAll()
                    this.$message({
                        type: 'success',
                        message: '审核驳回'
                    })
                }
            })
        },
        getAll() {
            // console.log(this.$route.params.mass)
            // console.log(localStorage.getItem('mass'))
            const url = '/api/user-applyMass/getApplies'
            axios.post(`${url}?mass=${localStorage.getItem('mass')}`, {
                
            },{
                headers: {
                    'verifyCode ': '2024'
                }
            }).then(res => {
                this.ApplyData = res.data
                // console.log(res.data)
            })
        }
    }
}
</script>