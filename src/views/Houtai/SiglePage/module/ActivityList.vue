<template>
    <div>
        <div v-if="!isView">
            
            <el-select v-model="selectValue">
                <el-option
                v-for="item in selectData"
                :key="item.value"
                :label="item.label"
                :value="item.value"></el-option>
            </el-select>
            <el-table
            :data="filteredData"
            class="table">
                <el-table-column
                prop="mass"
                label="社团名称"></el-table-column>
                <el-table-column>
                    <el-table-column
                    prop="title"
                    label="活动名称"></el-table-column>
                    <el-table-column
                    label="操作">
                    <template slot-scope="scope">
                        <el-button @click="viewArticle(scope.row)" style="margin: 1px 3px;" size="mini">查看</el-button>
                        <el-button @click="passArticle(scope.row)" style="margin: 1px 3px;" size="mini">通过</el-button>
                        <el-button @click="rejectArticle(scope.row)" style="margin: 1px 3px;" size="mini">驳回</el-button>
                    </template>
                    </el-table-column>
                </el-table-column>
            </el-table>
        </div>
        
        <div v-else>
            <el-button @click="isView = false" size="mini">返回</el-button>
            <el-form ref="form" :model="viewData" label-width="80px">
                <el-form-item label="社团名称:">
                    <p style="line-height: 40px;">{{ viewData.mass }}</p>
                </el-form-item>
                <el-form-item label="活动名称:">
                    <p style="line-height: 40px;">{{ viewData.title }}</p>
                </el-form-item>
                <el-form-item label="活动描述:">
                    <p style="line-height: 40px;">{{ viewData.description }}</p>
                </el-form-item>
                <el-form-item label="负责人:">
                    <p style="line-height: 40px;">{{ viewData.sno }}</p>
                </el-form-item>
                <el-form-item label="备注:">
                    <p style="line-height: 40px;">{{ viewData.remark }}</p>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>
<script>
import axios from 'axios'
export default {
    data() {
        return {
            isView: false,
            viewData: {},
            activityData: [],
            selectValue: 0,
            selectData: [
                {
                    label: '待审核',
                    value: 0
                },{
                    label: '审核通过',
                    value: 1
                },{
                    label: '审核驳回',
                    value: 2
                }
            ]
        }
    },
    computed: {
        filteredData() {
            return this.activityData.filter(item => item.status === this.selectValue);
        }
    },
    created() {
        this.getAll()
    },
    methods: {
        viewArticle(row) {
            this.isView = true
            this.viewData = row
        },
        passArticle(row) {
            // console.log(row)
            const url = '/api/backstage-activity/pass'
            axios.post(`${url}?mass=${row.mass}&title=${row.title}`, {}, {
                headers: {
                    "verifyCode": 2024
                }
            }).then(res => {
                if(res.data == 'Pass success') {
                    this.getAll()
                    this.$message({
                        message: '通过成功',
                        type: 'success'
                    })
                }
            })

        },
        rejectArticle(row) {
            // console.log(row)
            const url = '/api/backstage-activity/reject'
            axios.post(`${url}?mass=${row.mass}&title=${row.title}`, {}, {
                headers: {
                    "verifyCode": 2024
                }
            }).then(res => {
                if(res.data == 'Reject success') {
                    this.getAll()
                    this.$message({
                        message: '驳回成功',
                        type: 'success'
                    })
                }
            })
        },
        getAll() {
            const url = '/api/manager-applyActivity/getAll'
            axios.post(url, {}, {
                headers: {
                    'verifyCode': 2024
                }
            }).then(res => {
                this.activityData = res.data
            })
        }
    }
}
</script>

<style>
</style>