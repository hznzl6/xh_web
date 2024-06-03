<template>
    <div>
        <el-table
        stripe
        :data="activityData"
        class="table">
            <el-table-column
            prop="mass"
            label="社团名称">
            </el-table-column>
            <el-table-column>
                <template slot="header">
                    <div style="display: flex;justify-content: space-between;">
                        <!-- <el-badge v-if="filteredData != 0" size="mini" :value="filteredData">
                            <el-button @click="openList" size="mini">审核列表</el-button>
                        </el-badge> -->
                        <el-button @click="openList" size="mini">审核列表</el-button>
                        <el-select v-model="selectValue"></el-select>
                    </div>
                </template>
                <el-table-column
                prop="title"
                label="活动">
                </el-table-column>
                <el-table-column
                prop="description"
                label="描述">
                </el-table-column>
                <el-table-column
                prop="sno"
                label="负责人">
                </el-table-column>
                <el-table-column
                prop="remark"
                label="备注">
                </el-table-column>
                <el-table-column
                label="状态">
                <template slot-scope="scope">
                    <el-tag v-if="scope.row.status === 0" type="infd">未审核</el-tag>
                    <el-tag v-if="scope.row.status === 1" type="success">已审核</el-tag>
                    <el-tag v-if="scope.row.status === 2" type="danger">驳回</el-tag>
                </template>
                </el-table-column>
                <!-- <el-table-column
                label="操作">
                <template>
                    <el-button type="text" size="small">编辑</el-button>
                </template>
                </el-table-column> -->
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
import axios from 'axios'

import ActivityList from './module/ActivityList'
export default {
    components: {
        // eslint-disable-next-line
        ActivityList
    },
    data() {
        return {
            activityData: [],
            selectValue: ''
        }
    },
    computed: {
        // filteredData() {
        //     // console.log("123:"+this.activityData.filter(item => item.status === 0).length)
        //     return this.activityData.filter(item => item.status === 0).length;
        // }
    },
    created() {
        this.getAll()
    },
    methods: {
        openList() {
            const h = this.$createElement;
            this.$msgbox({
            title: '活动审核',
            message: h('p', null, [
                h(ActivityList), // 将自定义组件嵌入到VNode中
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