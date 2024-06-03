<template>
    <div>
        <el-table
        stripe
        :data="filteredData"
        class="table"
        :default-sort = "{prop: 'period', order: 'descending'}">
            <el-table-column
            prop="period"
            label="届数"
            sortable>
            </el-table-column>
            <el-table-column>
                <template slot="header">
                    <div style="display: flex;justify-content: space-between;">
                        <el-button @click="addNew" size="mini">新增</el-button>
                        <el-select @change="handleChange" v-model="selectValue">
                            <el-option
                            v-for="item in selectData"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value"
                            ></el-option>
                        </el-select>
                    </div>
                </template>
                <el-table-column
                prop="belong"
                label="班级"
                sortable>
                </el-table-column>
                <el-table-column
                prop="status"
                label="状态">
                <template slot-scope="scope">
                    <el-tag type="success" v-if="scope.row.status == 0">正常</el-tag>
                    <el-tag type="info" v-else>禁用</el-tag>
                </template>
                </el-table-column>
                <el-table-column
                label="操作">
                <template slot-scope="scope">
                    <el-button @click="deleteOne(scope.row)" type="text" size="small">删除</el-button>
                    <el-button @click="disableOne(scope.row)" type="text" size="small">禁用</el-button>
                </template>
                </el-table-column>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>

import axios from 'axios'

import NewClassForm from './module/NewClassForm.vue'
export default {
    components: {
        // eslint-disable-next-line
        NewClassForm
    },
    data() {
        return {
            BelongData: [],
            selectValue: 'all',
            selectData: [
                {
                    label: '全部',
                    value: 'all'
                },
                {
                    label: '2020级',
                    value: '2020'
                },
                {
                    label: '2021级',
                    value: '2021'
                },
                {
                    label: '2022级',
                    value: '2022'
                },
                {
                    label: '2023级',
                    value: '2023'
                }
            ]
        }
    },
    computed: {
        filteredData() {
            if(this.selectValue == 'all') {
                return this.BelongData;
            } else {
                return this.BelongData.filter(item => item.period === this.selectValue);
            }
        }
    },
    created() {
        this.getAll()
        if(localStorage.getItem('selectValue')) {
            this.selectValue = localStorage.getItem('selectValue')
        }
    },
    methods: {
        getAll() {
            const url = '/api/backstage-belong/getAll'
            axios.post(url, {}, {
                headers: {
                    'verifyCode': 2024
                }
            }).then(res => {
                this.BelongData = res.data
            })
        },
        addNew() {
            const h = this.$createElement;
            this.$msgbox({
            title: '班级新建',
            message: h('p', null, [
                h(NewClassForm), // 将自定义组件嵌入到VNode中
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
                        if(localStorage.getItem('newClassName')==null || localStorage.getItem('newClassIndex')==null || localStorage.getItem('newClassName') == '' || localStorage.getItem('newClassIndex') == '') {
                            this.$message({
                                message: '没有填完',
                                type: 'warning'
                            })
                            return
                        }
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
                            } else if(res.data == 'Belong have exited') {
                                this.$message({
                                    message: '该班级已存在',
                                    type: 'warning'
                                })
                            }
                            localStorage.removeItem('newClassIndex')
                            localStorage.removeItem('newClassName')
                        })
                        // console.log("confirm")
                        this.$msgbox.close();
                        location.reload()
                    }
                }
            });
        },
        deleteOne(row) {
            this.$confirm('此操作将永久删除该班级, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                callback: action => {
                    if(action == 'cancel') {
                        console.log('取消')
                    } else if(action == 'confirm') {
                        const url = '/api/backstage-belong/delete'
                        axios.post(`${url}?belong=${row.belong}`, {}, {
                            headers: {
                                "verifyCode": 2024
                            }
                        }).then(res => {
                            if(res.data == 'Delete success') {
                                this.getAll()
                                this.$message({
                                    message: '删除成功',
                                    type: 'success'
                                })
                            }
                        })
                    }
                }
            })
        },
        disableOne(row) {
            const url = '/api/backstage-belong/disable'
            axios.post(`${url}?belong=${row.belong}`, {} , {
                headers: {
                    'verifyCode': 2024
                }
            }).then(res => {
                if(res.data == 'Disable success') {
                    this.getAll()
                    this.$message({
                        message: '禁用成功',
                        type: 'success'
                    })
                }
            })
        },
        handleChange(value) {
            localStorage.setItem('selectValue', value)
            this.selectValue = localStorage.getItem('selectValue')
            location.reload()
        }
    }
}
</script>