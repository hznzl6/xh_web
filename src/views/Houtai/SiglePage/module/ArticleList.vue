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
                label="社团">
                </el-table-column>
                <el-table-column>
                <el-table-column
                prop="title"
                label="文章标题"></el-table-column>
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
            <div v-html="html"></div>
        </div>
    </div>
</template>
<script>
import axios from 'axios'
export default {
    data() {
        return {
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
            ],
            ArticleList: [],
            isView: false,
            html: ''
        }
    },
    computed: {
        filteredData() {
            return this.ArticleList.filter(item => item.status === this.selectValue);
        }
    },
    created() {
        this.getAll()
    },
    methods: {
        rejectArticle(row) {
            const url = '/api/backstage-article/reject'
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
        passArticle(row) {
            const url = '/api/backstage-article/pass'
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
        viewArticle(row) {
            this.isView = true
            this.html = row.content
            // console.log(row)
        },
        getAll() {
            const url = '/api/backstage-article/getAll'
            axios.post(url, {}, {
                headers: {
                    'verifyCode': 2024
                }
            }).then(res => {
                this.ArticleList = res.data
            })
        }
    }
}
</script>