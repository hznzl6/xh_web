<template>
    <div>
        <el-table
        stripe
        :data="ArticleData"
        class="table">
            <el-table-column
            prop="mass"
            label="社团名称">
            </el-table-column>
            <el-table-column>
                <template slot="header">
                    <div style="display: flex;justify-content: space-between;">
                        <el-button @click="openList" size="mini">审核列表</el-button>
                        <el-select v-model="selectValue"></el-select>
                    </div>
                </template>
                <el-table-column
                prop="title"
                label="文章标题">
                </el-table-column>
                <!-- <el-table-column
                prop="content"
                label="内容">
                </el-table-column> -->
                <el-table-column
                prop="post_time"
                label="最后操作时间">
                </el-table-column>
                <el-table-column
                label="操作">
                <template>
                    <el-button type="text" size="small">查看</el-button>
                </template>
                </el-table-column>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
import axios from 'axios'
import ArticleList from './module/ArticleList.vue'
export default {
    components: {
        // eslint-disable-next-line
        ArticleList
    },
    data() {
        return {
            ArticleData: [],
            selectValue: ''
        }
    },
    created() {
        this.getAll()
    },
    methods: {
        getAll() {
            const url = '/api/backstage-article/getAll'
            axios.post(url, {}, {
                headers: {
                    'verifyCode': 2024
                }
            }).then(res => {
                this.ArticleData = res.data
            })
        },
        openList() {
            const h = this.$createElement;
            this.$msgbox({
            title: '文章审核',
            message: h('p', null, [
                h(ArticleList), // 将自定义组件嵌入到VNode中
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
        }
    }
}
</script>