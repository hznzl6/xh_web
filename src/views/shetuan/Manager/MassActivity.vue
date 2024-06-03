<template>
    <div class="manager-activity" >
        <h2 style="font-size: 36px;margin: 6px 0">活动管理</h2>
        <div>
            <el-table
            :data="filteredData"
            class="table">
                <el-table-column                    
                    prop="title"
                    label="活动名称">
                </el-table-column>
                <el-table-column>
                    <template slot="header">
                        <div style="display: flex;justify-content: space-between;">
                            <el-select @change="handleChange" :value="selectedValue">
                                <el-option
                                v-for="item in selectData"
                                :key="item.value"
                                :label="item.label"
                                :value="item.label">
                                </el-option>
                            </el-select>
                            <el-button type="mini" @click="NewAdd">申请活动</el-button>
                        </div>
                    </template>
                        <el-table-column
                        label="描述"
                        prop="description"></el-table-column>
                        <el-table-column
                        label="负责人"
                        prop="person"></el-table-column>
                        <el-table-column
                        label="电话"
                        prop="phone"></el-table-column>
                        <!-- <el-table-column
                        label="申请经费"
                        prop="expenditure"></el-table-column> -->
                        <el-table-column
                        label="开始时间"
                        prop="start_time"></el-table-column>
                        <el-table-column
                        label="结束时间"
                        prop="end_time"></el-table-column>
                        <!-- <el-table-column
                        label="报名人员"
                        prop="sign"></el-table-column>
                        <el-table-column
                        label="实际参与人员"
                        prop="actual"></el-table-column> -->
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
                </el-table-column>
                
            </el-table>
        </div>
    </div>
</template>
<script>
import axios from 'axios'
import ApplyForm from './module/ActivityApplyForm.vue'
export default {
    components: {
        // eslint-disable-next-line
        ApplyForm
    },
    data() {
        return {
            selectedValue: '待审核',
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
            ActivityData: [],
        }
    },
    computed: {
        filteredData() {
            if(this.selectedValue === '待审核') {
                return this.ActivityData.filter(item => item.status === 0);
            } else if(this.selectedValue === '已审核') {
                return this.ActivityData.filter(item => item.status === 1);
            } else if(this.selectedValue === '被驳回') {
                return this.ActivityData.filter(item => item.status === 2);
            }
            return this.ActivityData;
        }
    },
    created() {
        this.getAll()
        if(localStorage.getItem('selectValue') !== null) {
            this.selectedValue = localStorage.getItem('selectValue')
        }
    },
    methods: {
        getAll(){
            const url = `/api/manager-applyActivity/getActivities`
            // console.log(this.$route.params.mass)
            axios.post(`${url}?mass=${this.$route.params.mass}`,{

            },{
                headers:{
                    'verifyCode ': '2024',
                }
            }).then(res => {
                this.ActivityData = res.data
                // console.log(res.data)
            })
        },
        NewAdd(){
            const h = this.$createElement;
            this.$msgbox({
            title: '活动申请表单',
            message: h('p', null, [
                h(ApplyForm), // 将自定义组件嵌入到VNode中
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
                        // console.log("confirm")
                        this.$msgbox.close();
                    }
                }
            });
        },
        EditArticle(row){
            console.log(row)
        },
        handleChange(value){
            // console.log(value)
            localStorage.setItem('selectValue', value)
            this.selectedValue = value
            // console.log(this.selectedValue)
            location.reload()
        }
    }
}
</script>
<style>
.manager-activity {
    width: 100%;
    height: calc(100vh - 120px);
    /* background-color: rgba(22, 55, 44, 0.6); */
    padding: 24px;
    padding-top: 0;
}
.manager-activity .el-table__body-wrapper {
    height: calc(100vh - 120px - 48px - 72px - 111.33px);
}
</style>
