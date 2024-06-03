<template>
    <div>
        <div>
            <el-form ref="form" label-width="80px">
                <el-form-item label="学号">
                    {{ memberData.sno }}
                </el-form-item>
                <el-form-item label="姓名">
                    <el-input @change="handInput" v-model="memberData.name"></el-input>
                </el-form-item>
                <el-form-item label="性别">
                    <el-input @change="handInput" v-model="memberData.sex"></el-input>
                </el-form-item>
                <el-form-item label="班级">
                    <el-input @change="handInput" v-model="memberData.belong"></el-input>
                </el-form-item>
                <el-form-item label="邮箱">
                    <el-input @change="handInput" v-model="memberData.mail"></el-input>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            memberData: {}
        }
    },
    created() {
        this.getMember()
    },
    methods: {
        getMember() {
            const url = '/api/manager-member/getOne'
            axios.post(`${url}?sno=${localStorage.getItem("Member_sno")}`, {}, {
                headers: {
                    'verifyCode': '2024', // 删除多余空格
                }
            }).then(res => {
                this.memberData = res.data
            })
        },
        handInput() {
            localStorage.setItem('Member_sno', this.memberData.sno)
            localStorage.setItem('Member_name', this.memberData.name)
            localStorage.setItem('Member_sex', this.memberData.sex)
            localStorage.setItem('Member_belong', this.memberData.belong)
            localStorage.setItem('Member_mail', this.memberData.mail)
        },
    }
}
</script>