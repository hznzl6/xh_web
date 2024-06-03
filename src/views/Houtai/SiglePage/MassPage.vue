<template>
    <div>
        <el-table
        stripe
        :data="massList"
        class="table">
            <el-table-column
            prop="mass"
            label="社团名称">
            </el-table-column>
            <el-table-column>
                <template slot="header">
                    <div style="display: flex;justify-content: flex-end;">
                        <el-button @click="newOne" size="mini">添加</el-button>
                    </div>
                </template>
                <el-table-column
                prop="manager"
                label="负责人">
                </el-table-column>
                <el-table-column
                prop="teacher"
                label="指导老师">
                </el-table-column>
                <el-table-column
                prop="slogan"
                label="Slogan">
                </el-table-column>
                <el-table-column
                label="介绍">
                </el-table-column>
                <el-table-column
                label="操作">
                <template slot-scope="scope">
                    <el-button @click="openEdit(scope.row)" type="text" size="small">编辑</el-button>
                    <el-button @click="del(scope.row)" type="text" size="small">删除</el-button>
                </template>
                </el-table-column>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
import axios from 'axios'

import NewMassForm from './module/NewMassForm.vue'
import MassDataEditor from './module/MassDataEditor.vue'
export default {
    components: {
        // eslint-disable-next-line
        NewMassForm,
        // eslint-disable-next-line
        MassDataEditor
    },
    data() {
        return {
            massList: [],
            isInput:{
                mass:''
            }
        }
    },
    created() {
        this.getAll()
    },
    methods: {
        getAll() {
            const url = '/api/backstage-massList/getAll'
            axios.post(url, {}, {
                headers: {
                    'verifyCode': 2024
                }
            }).then(res => {
                this.massList = res.data
            })
        },
        newOne() {
            this.$prompt('请输入要添加的社团', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            }).then(({ value  }) => {
                if (value === null) {
                    this.$message({
                        type: 'info',
                        message: '添加的社团不能为空'
                    });
                } else {
                    // console.log(value);
                    const url = '/api/backstage-massList/newOne'
                    axios.post(`${url}?mass=${value}`, {} , {
                        headers: {
                            'verifyCode': '2024', // 删除多余空格
                        }
                    }).then(res => {
                        if(res.data == 'Create success') {
                            this.getAll()
                            this.$message({
                                message: '创建成功',
                                type: 'success'
                            })
                        } else if(res.data == 'Mass have exited'){
                            this.$message({
                                message: '社团已存在',
                                type: 'error'
                            })
                        }
                    })

                }
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '取消添加'
                });       
            });
        },
        openEdit(row) {
            // console.log(row)
            localStorage.setItem("editMass", row.mass)
            const h = this.$createElement;
            this.$msgbox({
            title: '资料修改',
            message: h('p', null, [
                h(MassDataEditor), // 将自定义组件嵌入到VNode中
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
                        const data = {
                            mass: localStorage.getItem('Mass_mass'),
                            manager: localStorage.getItem('Mass_manager'),
                            slogan: localStorage.getItem('Mass_slogan'),
                            teacher: localStorage.getItem('Mass_teacher')
                        }
                        const url = '/api/backstage-massList/updateOne'
                        axios.post(`${url}?mass=${data.mass}&manager=${data.manager}&slogan=${data.slogan}&teacher=${data.teacher}`, {}, {
                            headers: {
                                'verifyCode': 2024,
                            }
                        }).then(res => {
                            if(res.data == 'Update success') {
                                this.$message({
                                    message: '修改成功',
                                    type: 'success'
                                })
                                setTimeout(function() {
                                    localStorage.removeItem('Mass_mass')
                                    localStorage.removeItem('Mass_slogan')
                                    localStorage.removeItem('Mass_teacher')
                                    localStorage.removeItem('Mass_manager')
                                    location.reload()
                                }.bind(this), 1500)
                            }
                        })
                        this.$msgbox.close();
                    }
                }
            });
        },
        del(row) {
            const url = '/api/backstage-massList/deleteOne'
            axios.post(`${url}?mass=${row.mass}`, {}, {
                headers: {
                    'verifyCode': '2024'
                }
            }).then(res => {
                if(res.data == 'Delete success') {
                    this.$message({
                        message: '删除成功',
                        type: 'success'
                    })
                    setTimeout(function() {
                        location.reload()
                    }, 1500)
                }
            })
        }
    }
}
</script>