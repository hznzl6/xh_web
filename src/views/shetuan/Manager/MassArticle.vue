<template>
    <div class="manager-article">
        <h2 style="font-size: 36px;margin: 6px 0">文章管理</h2>
        <div v-if="isPost == false && isReturn == false">
            <el-table
            class="table"
            :data="filteredData"
            stripe>
                <el-table-column
                label="标题"
                prop="title"></el-table-column>
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
                        <el-button @click="NewAdd" type="mini">发布</el-button>
                    </div>
                </template>
                <el-table-column
                    label="发布者"
                    prop="mass"></el-table-column>
                    <el-table-column
                    label="浏览量"
                    prop="hits"></el-table-column>
                    <el-table-column
                    label="发布时间"
                    prop="post_time"></el-table-column>
                    <el-table-column
                    label="更新时间"
                    prop="update_time"></el-table-column>
                    <el-table-column
                    label="状态"
                    prop="status">
                    <template slot-scope="scope">
                        <el-tag v-if="scope.row.status === 0" type="info">审核中</el-tag>
                        <el-tag v-if="scope.row.status === 1" type="success">通过</el-tag>
                        <el-tag v-if="scope.row.status === 2" type="danger">驳回</el-tag>
                    </template>
                    </el-table-column>
                    <el-table-column
                    label="操作">
                    <template slot-scope="scope">
                        <el-button @click="OperateEdit(scope.row)" style="margin: 1px 3px" size="mini">修改</el-button>
                        <el-button @click="OperateDelete(scope.row)" style="margin: 1px 3px" size="mini">删除</el-button>
                    </template>
                    </el-table-column>
                </el-table-column>
            </el-table>
        </div>
        <div v-if="isPost == true" style="background-color:white ;">
            <el-input v-model="article.title" placeholder="请输入内容" style="width:89%;float: left;"></el-input>
            <button @click="returnView" class="button" >返回</button>
            <button  @click="publish" class="button">发布</button>
            <Editor v-model="article.content" style="text-align: left;" editorStyle="height: calc(100vh - 120px - 47.33px - 12px - 42px - 16px);">
                <!-- <el-button>返回</el-button> -->
            </Editor>
        </div>
        <div v-if="isReturn == true" style="background-color:white ;">
            <el-input :disabled="!isPost" v-model="article.title" placeholder="请输入内容" style="width:89%;float: left;"></el-input>
            <button @click="returnViewTwo" class="button" >返回</button>
            <button  @click="update" class="button">更新</button>
            <Editor v-model="article.content" style="text-align: left;" editorStyle="height: calc(100vh - 120px - 47.33px - 12px - 42px - 16px);">
                <!-- <el-button>返回</el-button> -->
            </Editor>
        </div>
    </div>
</template>
<script>
import axios from 'axios'
export default {
    data() {
        return {
            selectedValue: '',
            isPost: false,
            ArticleData: [],
            input: '',
            selectData:[
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
                }
            ],
            article: {
                title: '',
                content: ''
            },
            isReturn:false,
        }
    },
    computed: {
        filteredData() {
            if(this.selectedValue === '待审核') {
                return this.ArticleData.filter(item => item.status === 0);
            } else if(this.selectedValue === '已审核') {
                return this.ArticleData.filter(item => item.status === 1);
            } else if(this.selectedValue === '被驳回') {
                return this.ArticleData.filter(item => item.status === 2);
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
        NewAdd() {
            this.isPost = true
        },
        getAll() {
            const url = '/api/manager-article/getArticles'
            axios.post(`${url}?mass=${localStorage.getItem('mass')}`,{
                
            },{
                headers:{
                    'verifyCode ': '2024',
                }
            }).then(res => {
                this.ArticleData = res.data
                // console.log(res.data)
            })
        },
        editor(row) {
            console.log(row)
        },
        del(row) {
            console.log(row)
        },
        handleChange(value){
            // console.log(value)
            localStorage.setItem('selectValue', value)
            this.selectedValue = value
            // console.log(this.selectedValue)
            location.reload()
        },
        returnView() {
            this.isPost = false
        },
        update() {
            const url = '/api/manager-article/update'
            const data = {
                title: this.article.title,
                content: this.article.content,
                mass: this.$route.params.mass
            };
            axios.post(url, data, {
                headers: {
                    'verifyCode': 2024
                }
            }).then(res => {
                if(res.data == 'Update success') {
                    this.$message({
                        message: '修改成功',
                        type: 'success'
                    })
                    location.reload()
                }
            })
        },
        publish() {
            const data = {
                title: this.article.title,
                content: this.article.content,
                mass: this.$route.params.mass
            };
            const url = "/api/manager-article/post";
            axios.post(url, data, {
              headers: {
                  'verifyCode': '2024', // 删除多余空格
              }
                })
        this.$confirm('确定发布此文章吗？', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).then(() => {
            if (this.article.title !== "" && this.article.content !== "") {
            this.$message({
                type: 'success',
                message: '发布成功!'
            });location.reload()
            } else if (this.article.title === "" ) {
            // console.log(this.article);
            this.$message({
                type: 'error',
                message: '标题不能为空'
            });
            this.isPost = false;
            }else if (this.article.content === "" ) {
            // console.log(this.article);
            this.$message({
                type: 'error',
                message: '内容不能为空'
            });
            this.isPost = false;
            }
        })
        .catch(() => {
            this.$message({
            type: 'info',
            message: '已取消发布'
            });          
        });
        },
        OperateDelete(row) {
          const index = this.ArticleData.findIndex(item => item.id === row.id);
          this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
            if (index !== -1) {
                this.$message({
            type: 'success',
            message: '删除成功!'
          });
            
            this.ArticleData.splice(index, 1);
            }
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
        },
        OperateEdit(row){
            this.article.title = row.title,
            this.article.content = row.content,
            this.isReturn =true
        },
        returnViewTwo(){
            this.isReturn = false
            this.article.title = ''
            this.article.content = ''
        },
    }
}

</script>
<style>
.manager-article {
    width: 100%;
    height: calc(100vh - 120px);
    padding: 24px;
    padding-top: 0;
}
.manager-article .el-table__body-wrapper {
    height: calc(100vh - 120px - 48px - 72px - 111.33px);
}
.button{
    height: 40px;
    width: 5%;
    border: 1px solid #b6b4b4;
    background-color: white;
    font-size: 12px;
    border-radius: 3px;
}
</style>