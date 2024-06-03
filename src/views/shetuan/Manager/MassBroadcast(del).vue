<template>
    <div class="Manager-MassBroadCast">
        <h3>站内消息</h3>
        <div class="Manager-MassBroadCast_PC">
            <div class="Manager-MassBroadCast_leftTable">
                <!-- <el-button size="mini">新建</el-button> -->
                <el-table :data="messageByMass" stripe style="height: calc(100vh - 204px);overflow-y: auto;width: 100%;margin: 0;">
                    <el-table-column
                    prop="title"
                    label="标题">
                    </el-table-column>
                    <el-table-column
                    prop="content"
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
                </el-table>
            </div>
            <div class="Manager-MassBroadCast_rightPost">
                <el-input v-model="ArticleForm.title" placeholder="输入文章标题"></el-input>
                <el-button v-if="!isUpdate"  size="mini" @click="ArticlePostBtn">发布</el-button>
                <el-button v-if="isUpdate" size="mini" @click="updateMessage">更新</el-button>
                <el-button v-if="isUpdate" size="mini" @click="backPost">返回</el-button>
                <editor
                placeholder="Type here..."
                v-model="ArticleForm.content"
                api-key="ublhvveejzddur1ry6jxrqizz5urb3ri8w38goz9kskcr0mp"
                :init="{
                    toolbar: 'undo redo | blocks fontfamily fontsize | bold italic underline strikethrough | link image media table mergetags | align lineheight | tinycomments | checklist numlist bullist indent outdent | emoticons charmap | removeformat',
                    plugins: [
                        'advlist', 'autolink', 'lists', 'link', 'image', 'charmap', 'preview',
                        'anchor', 'searchreplace', 'visualblocks', 'code', 'fullscreen',
                        'insertdatetime', 'media', 'table', 'help', 'wordcount'
                    ],
                    content_style: 'body { font-family:Helvetica,Arial,sans-serif; font-size:18px }',
                    menubar: 'file edit view insert format about tools table help',
                    directionality: 'ltr',
                    selector: 'textarea',
                    language: 'zh_CN'
                    }"
                />
            </div>
        </div>
        <div class="Manager-MassBroadCast_Move">
            <div class="Manager-MassBroadCast_leftTable">
                <el-table :data="messageByMass" stripe style="height: calc(100vh - 204px);overflow-y: auto;width: 100%;margin: 0;">
                    <el-table-column
                    prop="title"
                    label="标题">
                    </el-table-column>
                    <el-table-column
                    prop="content"
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
                </el-table>
            </div>
            <div class="Manager-MassBroadCast_rightPost">
                <el-input v-model="ArticleForm.title" placeholder="输入文章标题"></el-input>
                <el-button v-if="!isUpdate"  size="mini" @click="ArticlePostBtn">发布</el-button>
                <el-button v-if="isUpdate" size="mini" @click="updateMessage">更新</el-button>
                <el-button v-if="isUpdate" size="mini" @click="backPost">返回</el-button>
                <editor
                placeholder="Type here..."
                v-model="ArticleForm.content"
                api-key="ublhvveejzddur1ry6jxrqizz5urb3ri8w38goz9kskcr0mp"
                :init="{
                    toolbar: 'undo redo | blocks fontfamily fontsize | bold italic underline strikethrough | link image media table mergetags | align lineheight | tinycomments | checklist numlist bullist indent outdent | emoticons charmap | removeformat',
                    plugins: [
                        'advlist', 'autolink', 'lists', 'link', 'image', 'charmap', 'preview',
                        'anchor', 'searchreplace', 'visualblocks', 'code', 'fullscreen',
                        'insertdatetime', 'media', 'table', 'help', 'wordcount'
                    ],
                    content_style: 'body { font-family:Helvetica,Arial,sans-serif; font-size:18px }',
                    menubar: 'file edit view insert format about tools table help',
                    directionality: 'ltr',
                    selector: 'textarea',
                    language: 'zh_CN'
                    }"
                />
            </div>
        </div>
    </div>
</template>
<script>
import Editor from '@tinymce/tinymce-vue'
export default {
    components: {
        'editor': Editor
    },
    data() {
        return {
            ArticleForm: {
                title: '',
                content: ''
            },
            dialogFormPost: false,
            messageByMass: [],
            isUpdate: false
        }
    },
    created() {
        this.getAllMessageByMass()
    },
    methods: {
        ArticlePostBtn() {
            // console.log(this.ArticleForm)
            const url = process.env.VUE_APP_BASE_URI + '/shetuan-broadcast/post-broadcast'
            const vaule = {
                content: this.ArticleForm.content,
                title: this.ArticleForm.title,
                // mass: '互联网社'
                mass: this.$route.params.mass
            }
            fetch(url, {
                method: "POST",
                headers: {
                    'Accept': 'Application/json',
                    'Content-Type': 'Application/json'
                },
                body: JSON.stringify(vaule)
            })
            .then(res => {
                if(res.status == 200) {
                    this.$message({
                        message: '发布成功',
                        type: 'success'
                    })
                    this.ArticleForm.content = ''
                    this.ArticleForm.title = ''
                    return res.text()
                } else {
                    this.$message({
                        message: '标题已存在',
                        type: 'warning'
                    })
                    return res.text()
                }
            })
            .then(res => {
                console.log(res)
            })
        },
        updateMessage() {
            const url = process.env.VUE_APP_BASE_URI + '/shetuan-broadcast/update-broadcast'
            const vaule = {
                content: this.ArticleForm.content,
                title: this.ArticleForm.title,
                // mass: '互联网社'
                mass: this.$route.params.mass
            }
            fetch(url, {
                method: "POST",
                headers: {
                    'Accept': 'Application/json',
                    'Content-Type': 'Application/json'
                },
                body: JSON.stringify(vaule)
            })
            .then(res => {
                if(res.status == 200) return res.text()
            })
            .then(res => {
                this.$message({
                    message: '更新成功',
                    type: 'success'
                })
                location.reload()
                this.ArticleForm.content = ''
                this.ArticleForm.title = ''
                console.log(res)
                
            })
        },
        getAllMessageByMass() {
            const url = process.env.VUE_APP_BASE_URI + '/shetuan-broadcast/getAll-broadcast'
            // const mass = '互联网社'
            const mass = this.$route.params.mass
            fetch(`${url}?mass=${mass}`, {
                method: "POST",
                headers: {
                    'Accept': 'Application/json',
                    'Content-Type': 'Application/json'
                },
            })
            .then(res => res.json())
            .then(res => {
                this.messageByMass = res
                // console.log(this.messageByMass)
            })
        },
        OperateEdit(row) {
            this.ArticleForm.title = row.title
            this.ArticleForm.content = row.content
            this.isUpdate =true
            // this.$message({
            //     message: '按下Esc键 退出编辑'
            // })
        },
        OperateDelete(row) {
            // console.log(row)
            const value = {
                // mass: '互联网社',
                mass: this.$route.params.mass,
                title: row.title
            }
            // console.log(value)
            if (window.confirm('确认删除该项吗？')) {
                const url = process.env.VUE_APP_BASE_URI + '/shetuan-broadcast/delete-broadcast'
                fetch(`${url}?mass=${value.mass}&title=${value.title}`, {
                method: "POST",
                headers: {
                    'Accept': 'Application/json',
                    'Content-Type': 'Application/json'
                }
                })
                .then(res => res.text())
                .then(res => {
                    console.log(res)
                    this.$message({
                        message: '删除成功',
                        type: 'success'
                    })
                    location.reload()
                })
            }
        },
        backPost() {
            this.ArticleForm.content = ''
            this.ArticleForm.title = ''
            this.isUpdate = false
        }
    }
}
</script>
<style>
.Manager-MassBroadCast {
    position: relative;
    margin: 24px 24px;
    min-height: calc(100vh - 164px);
    background-color: rgb(255, 255, 255, 0.6);
}
.Manager-MassBroadCast .Manager-MassBroadCast_PC {
    width: 100%;
    display: block;
}
.Manager-MassBroadCast .Manager-MassBroadCast_Move {
    width: 100%;
    display: none;
}
.Manager-MassBroadCast .Manager-MassBroadCast_leftTable {
    position: absolute;
    left: 0;
    width: 50%;
    min-height: calc(100vh - 164px);
    /* background-color: rgba(196, 33, 33, 0.6); */
}
.Manager-MassBroadCast_leftTable .el-button {
    float: left;
}
.Manager-MassBroadCast_rightPost .el-button {
    float: right;
}
.Manager-MassBroadCast_rightPost .el-input {
    float: left;
    width: 220px;
}
.Manager-MassBroadCast .Manager-MassBroadCast_rightPost {
    position: absolute;
    right: 0;
    width: calc(50% - 20px);
    min-height: calc(100vh - 164px);
    border-radius: 5px;
    /* background-color: rgba(255, 255, 255, 0.6); */
}
.Manager-MassBroadCast_rightPost .tox {
    width: 100%;
    min-height: calc(100vh - 244px);
}

@media screen and (max-width: 480px) {
    .Manager-MassBroadCast .Manager-MassBroadCast_PC {
        display: none;
    }
    .Manager-MassBroadCast .Manager-MassBroadCast_Move {
        display: block;
    }
    .Manager-MassBroadCast .Manager-MassBroadCast_Move .Manager-MassBroadCast_leftTable {
        position: relative;
        left: 0;
        width: 100%;
    }
    .Manager-MassBroadCast .Manager-MassBroadCast_Move .Manager-MassBroadCast_rightPost {
        right: 0;
        width: 100%;
        margin-top: 15px;
    }
}
</style>