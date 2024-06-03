<template>
    <div class="manager-broadcast">
        <h2 style="font-size: 36px;margin: 6px 0">广播管理</h2>
        <div v-if="isPost == false && isReture == false">
            <el-table :data="BroadcastData" stripe class="table">
                <el-table-column
                prop="title"
                label="标题">
                </el-table-column>
                <el-table-column>
                <template slot="header">
                    <div style="display: flex;justify-content: space-between;">
                        <el-select v-model="selectedValue"></el-select>
                        <el-button @click="NewAdd" type="mini">新增</el-button>
                    </div>
                </template>
                <el-table-column
                    prop="mass"
                    label="内容">
                    </el-table-column>
                    <el-table-column
                    prop="create_time"
                    label="日期">
                    </el-table-column>
                    <el-table-column
                    label="操作">
                        <template slot-scope="scope">
                            <el-button
                            size="mini"
                            style="width: 100%;float: right;"
                            @click="OperateEdit(scope.row)">修改</el-button>
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
        <div v-if="isPost == true" style="background-color:white ;">
            <el-input
            placeholder="请输入标题"
            v-model="article.title"
            clearable style="width:89%;float: left;">
            </el-input>
            <button @click="returnView" class="button" >返回</button>
            <button  @click="publish" class="button">发布</button>
             <Editor v-model="article.content" style="text-align: left;" editorStyle="height: calc(100vh - 120px - 47.33px - 12px - 42px - 16px);">
                <!-- <el-button>返回</el-button> -->
            </Editor>
        </div>
        <div v-if="isReture == true" style="background-color:white ;">
            <el-input
            :disabled="!isPost"
            placeholder="请输入标题"
            v-model="article.title"
            clearable style="width:89%;float: left;">
            </el-input>
            <button @click="returnViewTwo" class="button" >返回</button>
            <button @click="OperateEditTwo" class="button" >修改</button>
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
            isReture:false,
            BroadcastData:[],
            article: {
                title: '',
                content: ''
            },
        }
    },
    
    created() {
        this.getAll()
    },
    methods: {
        NewAdd() {
            this.isPost = true
        },
        returnView() {
            this.isPost = false
        },
        getAll() {
            const url = '/api/manager-broadcast/getBroadcasts'
            axios.post(`${url}?mass=${this.$route.params.mass}`,{
                
            },{
                headers:{
                    'verifyCode ': '2024',
                }
            }).then(res => {
                this.BroadcastData = res.data
                console.log(res.data)
            })
        },
        publish() {
            const data = {
                title: this.article.title,
                content: this.article.content,
                mass: this.$route.params.mass
            };
            const url = "/api/manager-broadcast/post";
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
          const index = this.BroadcastData.findIndex(item => item.id === row.id);
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
            
            this.BroadcastData.splice(index, 1);
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
            this.isReture =true
        },
        returnViewTwo(){
            this.isReture = false
            this.article.title = ''
            this.article.content = ''
        },
        OperateEditTwo(){
            const data = {
                title: this.article.title,
                content: this.article.content,
                mass: this.$route.params.mass
            };
            const url = "/api/manager-article/update";
            axios.post(url, data, {
                headers: {
                    'verifyCode': '2024', // 删除多余空格
                }
            }).then(res => {
                if(res.data == 'Update success' && this.article.content != '') {
                    this.$message({
                        message: '修改成功',
                        type: 'success'
                    })
                    location.reload()
                }
                else if (this.article.content === "" ) {
            // console.log(this.article);
            this.$message({
                type: 'error',
                message: '内容不能为空'
            });
            this.isPost = false;
            }
            })
        },
    }
    
}

</script>
<style>
.manager-broadcast {
    width: 100%;
    height: calc(100vh - 120px);
    /* background-color: rgba(22, 55, 44, 0.6); */
    padding: 24px;
    padding-top: 0;
}
.button{
    height: 40px;
    width: 5%;
    border: 1px solid #b6b4b4;
    background-color: white;
    font-size: 12px;
    border-radius: 3px;
}
/* .button:hover{
    color: white;
    background-color: rgb(25, 175, 240);
} */
</style>