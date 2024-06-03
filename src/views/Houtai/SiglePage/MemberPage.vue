<template>
    <div>
        <el-table
        stripe
        :data="memberData"
        class="table">
            <el-table-column
            prop="mass"
            label="社团名称">
            </el-table-column>
            <el-table-column>
                <template slot="header">
                    <div style="display: flex;justify-content: flex-end;">
                        <el-select v-model="selectValue"></el-select>
                    </div>
                </template>
                <el-table-column
                prop="sno"
                label="学号">
                </el-table-column>
                <el-table-column
                prop="name"
                label="姓名">
                </el-table-column>
                <el-table-column
                prop="sex"
                label="性别">
                </el-table-column>
                <el-table-column
                prop="belong"
                label="班级">
                </el-table-column>
                <el-table-column
                label="操作">
                <template slot-scope="scope">
                    <el-button @click="openEdit(scope.row)" type="text" size="small">编辑</el-button>
                    <el-button @click="deleteOne(scope.row)" type="text" size="small">删除</el-button>
                </template>
                </el-table-column>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
import axios from 'axios'

import MemberDataEditor from './module/MemberDataEditor.vue'
export default {
    components: {
        // eslint-disable-next-line
        MemberDataEditor
    },
    data() {
        return {
            memberData: [],
            selectValue: ''
        }
    },
    created() {
        // this.getAll()
    },
    methods: {
        getAll() {
            const url = '/api/manager-member/getAll'
            axios.post(url, {}, {
                headers: {
                    'verifyCode': 2024
                }
            }).then(res => {
                this.memberData = res.data
            })
        },
        openEdit(row) {
            localStorage.setItem("Member_sno", row.sno)
            const h = this.$createElement;
            this.$msgbox({
            title: '资料修改',
            message: h('p', null, [
                h(MemberDataEditor), // 将自定义组件嵌入到VNode中
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
                        const url = '/api/manager-member/update'
                        const data = {
                            sno: localStorage.getItem('Member_sno') || '',
                            name: localStorage.getItem('Member_name') || '',
                            sex: localStorage.getItem('Member_sex') || '',
                            belong: localStorage.getItem('Member_belong') || '',
                            mail: localStorage.getItem('Member_mail') || '',
                        }
                        axios.post(`${url}?sno=${data.sno}&name=${data.name}&sex=${data.sex}&belong=${data.belong}&mail=${data.mail}`, {}, {
                            headers: {
                                'verifyCode': '2024',
                            }
                        }).then(res => {
                            if(res.data == 'Update success') {
                                this.$message({
                                    message: '修改成功',
                                    type: 'success'
                                })
                                setTimeout(function() {
                                    location.reload()
                                }.bind(this), 1500)
                            }
                        })
                        // console.log("confirm")
                        this.$msgbox.close();
                    }
                }
            });
        },
        deleteOne(row) {
            this.$confirm('此操作将永久删除该成员, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                callback: action => {
                    if(action == 'cancel') {
                        // console.log('取消')
                    } else if(action == 'confirm') {
                        const url = '/api/manager-member/delete'
                        axios.post(`${url}?depart=${row.depart}&mass=${row.mass}&sno=${row.sno}`, {}, {
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
        }
    }
}
</script>