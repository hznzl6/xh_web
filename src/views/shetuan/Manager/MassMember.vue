<template>
    <div class="manager-member">
        <h2 style="font-size: 36px;margin: 6px 0">成员管理</h2>
        <div class="Manager-MassMember_TableMemberData">
            <el-table
            :data="filteredData"
            style="width: 100%;height: calc(100vh - 120px - 31.33px - 48px);"
            stripe>

                <el-table-column
                prop="shetuanUser.name"
                label="姓名"></el-table-column>
                <el-table-column>
                <template slot="header" slot-scope="scope">
                    <div style="display: flex;justify-content: space-between;">
                        <el-select @click="scope" @change="handSelect" :value="selectedValue">
                            <el-option
                            v-for="item in departData"
                            :key="item.id"
                            :label="item.department"
                            :value="item.department">
                            </el-option>
                        </el-select>
                        <el-button @click="NewAdd" type="mini">申请列表</el-button>
                    </div>
                </template>
                <el-table-column
                prop="sno"
                label="学号"></el-table-column>
                <el-table-column
                prop="depart"
                label="部门"></el-table-column>
                <el-table-column
                prop="position"
                label="职位"></el-table-column>
                <el-table-column
                prop="phone"
                label="手机"></el-table-column>
                <el-table-column
                label="操作">
                    <template slot-scope="scope">
                        <el-button
                        size="mini"
                        style="width: 100%;float: right;"
                        @click="OperateEdit(scope.row)">编辑</el-button>
                        <el-button
                        size="mini"
                        type="danger"
                        style="width: 100%;float: right;margin-top: 5px;"
                        @click="OperateDelete(scope.row)">删除</el-button>
                    </template>
                </el-table-column>
                </el-table-column>
                
            </el-table>
        </div>
    </div>
</template>
<script>
import axios from 'axios'
import ApplyTable from './module/MemberApplyTable.vue'
export default {
    components: {
        // eslint-disable-next-line
        ApplyTable
    },
    data() {
        return {
            selectedValue: '',
            memberData: [],
            departData: []
        }
    },
    computed: {
        filteredData() {
            // return this.memberData.filter(item => item.depart === this.selectedValue);
            if (this.selectedValue === this.$route.params.mass) {
                return this.memberData;
            } else {
                return this.memberData.filter(item => item.depart === this.selectedValue);
            }
        }
    },
    created() {
        // console.log(this.$route.params.mass)
        this.getAll()
        this.getAllDepart()
        this.selectedValue = localStorage.getItem('selectValue')
    },
    methods: {
        handSelect(value) {
            console.log(value)
            localStorage.setItem('selectValue', value)
            location.reload()
        },
        getAll() {
            const url = '/api/manager-member/getMembers'
            axios.post(`${url}?mass=${localStorage.getItem('mass')}`, {
                
            },{
                headers: {
                    'verifyCode ': '2024'
                }
            }).then(res => {
                this.memberData = res.data
            })
        },
        getAllDepart() {
            const url = '/api/manager-department/getDeparts'
            axios.post(`${url}?mass=${this.$route.params.mass}`, {

            },{
                headers: {
                    'verifyCode ': '2024'
                }
            }).then(res => {
                this.departData = res.data
                // console.log(res.data)
                if(localStorage.getItem('selectValue') != null) {
                    return
                } else {
                    this.selectedValue = res.data[1].department
                }
            })
        },
        NewAdd() {
            const h = this.$createElement;
            this.$msgbox({
            title: '申请列表',
            message: h('p', null, [
                h(ApplyTable), // 将自定义组件嵌入到VNode中
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
        }
    }
}
</script>
<style>
.manager-member {
    width: 100%;
    height: calc(100vh - 120px);
    padding: 24px;
    padding-top: 0;
}
</style>