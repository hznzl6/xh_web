<template>
    <div>
        <el-table
        :data="joinActivityData">
        
            <el-table-column
            prop="target"
            label="活动名称"></el-table-column>
            <el-table-column
            label="状态">
            <template slot-scope="scope">
                <el-tag v-if="scope.row.status == 0" type="info">审核中</el-tag>
                <el-tag v-if="scope.row.status == 1" type="success">审核通过</el-tag>
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
            joinActivityData: []
        }
    },
    created() {
        this.getJoinAll()
    },
    methods: {
        getJoinAll() {
            const url = '/api/user-applyActivity/getJoinActivity'
            axios.post(`${url}?sno=202280310316&mass=${localStorage.getItem('mass')}`, {}, {
                headers: {
                    'verifyCode': '2024'
                }
            }).then(res => {
                this.joinActivityData = res.data
                console.log(res.data)
            })
        }
    }
}
</script>