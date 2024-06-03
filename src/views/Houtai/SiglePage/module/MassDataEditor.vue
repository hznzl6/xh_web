<template>
    <div>
        <el-form ref="form" label-width="80px">
            <el-form-item label="社团名称">
                {{ massData.mass }}
            </el-form-item>
            <el-form-item label="负责人">
                <el-input @change="handInput" v-model="massData.manager"></el-input>
            </el-form-item>
            <el-form-item label="Slogan">
                <el-input @change="handInput" v-model="massData.slogan"></el-input>
            </el-form-item>
            <el-form-item label="指导老师">
                <el-input @change="handInput" v-model="massData.teacher"></el-input>
            </el-form-item>
            <el-form-item label="简介">
                {{ massData.info }}
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            massData: {}
        }
    },
    created() {
        this.getMass()
    },
    methods: {
        getMass() {
            const url = '/api/backstage-massList/getOne'
            axios.post(`${url}?mass=${localStorage.getItem("editMass")}`, {} , {
                headers: {
                    'verifyCode': '2024', // 删除多余空格
                }
            }).then(res => {
                this.massData = res.data
            })
        },
        handInput() {
            localStorage.setItem('Mass_mass', this.massData.mass)
            localStorage.setItem('Mass_manager', this.massData.manager)
            localStorage.setItem('Mass_slogan', this.massData.slogan)
            localStorage.setItem('Mass_teacher', this.massData.teacher)
        },
        updateMass() {
            const url = '/api/backstage-massList/updateOne'
            axios.post(url, this.massData, {
                headers: {
                    'verifyCode': '2024', // 删除多余空格
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
        }
    }
}
</script>